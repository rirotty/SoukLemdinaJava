/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.souklemdina.gui;

import com.jfoenix.controls.JFXTextField;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.DirectionsPane;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import com.lynden.gmapsfx.service.directions.DirectionStatus;
import com.lynden.gmapsfx.service.directions.DirectionsResult;
import com.lynden.gmapsfx.service.directions.DirectionsService;
import com.lynden.gmapsfx.service.directions.DirectionsServiceCallback;
import com.lynden.gmapsfx.service.geocoding.GeocoderStatus;
import com.lynden.gmapsfx.service.geocoding.GeocodingResult;
import com.lynden.gmapsfx.service.geocoding.GeocodingService;
import com.souklemdina.entities.Workshop;
import com.souklemdina.services.WorkshopServices;
import com.souklemdina.util.SessionUser;
import com.souklemdina.util.UploadFile;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class UpdateWorkController implements Initializable, MapComponentInitializedListener, DirectionsServiceCallback {

    private String newPhoto = null;
    private FileChooser file;
    private File selected_photo = null;
    private String oldPhoto;
    @FXML
    private TextArea description;
    @FXML
    private TextField nom;
    @FXML
    private JFXTextField adresse;
    private TextField image;
    @FXML
    private TextField nbrePlace;
    @FXML
    private TextField prix;
    @FXML
    private DatePicker dateDebut;
    @FXML
    private DatePicker dateFin;
    @FXML
    private Button ajouter;
    @FXML
    private ComboBox type;
    @FXML
    private Label titre;
    ObservableList<String> typeList = FXCollections.observableArrayList("patisserie Traditionelle", "Haute couture", "autre");
    private Workshop w;
    @FXML
    private Button aj;
    @FXML
    private Button annuler;
    @FXML
    private JFXTextField lat;
    private GeocodingService geocodingService;
    private MarkerOptions markerOptions;
    private GoogleMap map;
    protected DirectionsPane directionsPane;
    List<String> l = new ArrayList();
    protected DirectionsService directionsService;
    String[] s;
    protected StringProperty from = new SimpleStringProperty();
    GoogleMapView mapView = new GoogleMapView();
    @FXML
    private JFXTextField lg;
    @FXML
    private AnchorPane anxc;

    //
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mapView.addMapInializedListener(this);

        mapView.addMapInializedListener(this);

        from.bindBidirectional(adresse.textProperty());

        adresse.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (!newPropertyValue) {
                    adresse.validate();
                }
            }
        });
    }

    @FXML
    public void update(ActionEvent event) throws Exception {

        WorkshopServices ws = new WorkshopServices();
        Alert alert = new Alert(Alert.AlertType.ERROR);
        if ((nom.getText().isEmpty() || description.getText().isEmpty()
                || nbrePlace.getText().isEmpty())) {
            alert.setHeaderText("Veuillez remplir tous les champs");
            alert.showAndWait();
        }
        if ((dateDebut.getValue().isAfter(dateFin.getValue()))) {
            alert.setHeaderText("verifiez vos dates");
            alert.showAndWait();
        } else {
            this.w = ws.findbyid(SessionUser.getUser().getId());
            this.w.setNomWorkshop(nom.getText());
            this.w.setAdresse(adresse.getText());
            this.w.setType(type.getValue().toString());
            this.w.setNbPlace(Integer.parseInt(nbrePlace.getText()));
            this.w.setPrix(Double.parseDouble(prix.getText()));
            this.w.setDescription(description.getText());
            this.w.setDateDebut(new Timestamp(dateDebut.getValue().getYear() - 1900, dateDebut.getValue().getMonthValue() - 1, dateDebut.getValue().getDayOfMonth(), 0, 0, 0, 0));
            this.w.setDateFin(new Timestamp(dateFin.getValue().getYear() - 1900, dateDebut.getValue().getMonthValue() - 1, dateDebut.getValue().getDayOfMonth(), 0, 0, 0, 0));
            this.w.setUpdatedAt((new Timestamp(new Date().getTime())));
            if (this.selected_photo != null) {
                this.newPhoto = UploadFile.uploadImage(this.selected_photo.getAbsolutePath(), this.w.getImage());
                this.w.setImage(this.newPhoto);
            }
            System.out.println(this.newPhoto);
            ws.update(this.w);
            
            AnchorPane p = FXMLLoader.load(getClass().getResource("ListeWork.fxml"));
            this.anxc.getChildren().setAll(p);

        }
    }

    @Override
    public void directionsReceived(DirectionsResult dr, DirectionStatus ds
    ) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setWorkshop(Workshop w) {
        this.w = w;
    }

    public void afficher() {
        nom.setText(w.getNomWorkshop());
        adresse.setText(w.getAdresse());
        type.setValue("patisserie traditionnelle");
        type.setItems(typeList);
        nbrePlace.setText(String.valueOf(w.getNbPlace()));
        prix.setText(String.valueOf(w.getPrix()));
        description.setText(w.getDescription());
        System.out.println("++" + String.valueOf(w.getNbPlace()));
    }

    @FXML
    void UpPhoto(ActionEvent event) throws Exception {
        this.file = new FileChooser(); //pour choisir la photo
        this.file.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Images", "*.jpg"));
        this.file.setTitle("Choisir une photo du produit");
        this.selected_photo = file.showOpenDialog((Stage) aj.getScene().getWindow());
    }

    @FXML
    void goToAffichage(ActionEvent e) throws IOException {

        AnchorPane p = FXMLLoader.load(getClass().getResource("ListeWork.fxml"));
            this.anxc.getChildren().setAll(p);

    }

    @FXML
    private void fromOnKeyTypedEvent(KeyEvent event) {
        try {
            geocodingService = new GeocodingService();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        geocodingService.geocode(adresse.getText(), (GeocodingResult[] results, GeocoderStatus status) -> {
            l.clear();
            //int i;                 
            for (int i = 0; i < results.length; i++) {
                s = new String[results.length];
                s[i] = results[i].getFormattedAddress();
                System.out.println(results[i].getJSObject());
                l.add(results[i].getFormattedAddress());

            }

            for (GeocodingResult result : results) {

                TextFields.bindAutoCompletion(adresse, s);
                lg.setText(result.getGeometry().getLocation().getLatitude() + "");
                lat.setText(result.getGeometry().getLocation().getLongitude() + "");

            }

            TextFields.bindAutoCompletion(adresse, t -> {

                return l;

            });

        });
    }

    @Override
    public void mapInitialized() {
        geocodingService = new GeocodingService();
        MapOptions options = new MapOptions();

        options.center(new LatLong(34.3055732, 11.255412))
                .zoomControl(true)
                .zoom(6)
                .overviewMapControl(false)
                .mapType(MapTypeIdEnum.ROADMAP);
        GoogleMap map = mapView.createMap(options);
        directionsService = new DirectionsService();
        directionsPane = mapView.getDirec();
    }
}
