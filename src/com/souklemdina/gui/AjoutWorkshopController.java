/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.souklemdina.gui;

import com.jfoenix.controls.JFXButton;
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
public class AjoutWorkshopController implements Initializable, MapComponentInitializedListener, DirectionsServiceCallback {

    private GeocodingService geocodingService;
    private MarkerOptions markerOptions;
    private GoogleMap map;
    protected DirectionsPane directionsPane;
    List<String> l = new ArrayList();
    protected DirectionsService directionsService;
    String[] s;
    protected StringProperty from = new SimpleStringProperty();
    GoogleMapView mapView = new GoogleMapView();

    private String newPhoto;

    @FXML
    private TextArea description;
    @FXML
    private TextField nom;

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

    ObservableList<String> typeList = FXCollections.observableArrayList("patisserie Traditionnelle", "Haute couture", "autre");
    @FXML
    private Label titre;
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    @FXML
    private JFXButton ajouterPhoto;
    @FXML
    private TextField lon;
    @FXML
    private TextField lat;
    @FXML
    private JFXTextField adresse;
    @FXML
    private Button btnAnnuler;
    @FXML
    private AnchorPane anxc;

    /**
     * Initializes the controller class.
     *
     * @param url
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
//                dateDebut.focusedProperty().addListener((arg0, oldValue, newValue) -> {
//            if (!newValue) { // when focus lost
//                if (!(dateDebut.getValue()).isBefore(LocalDate.of(new Date().getYear()-1900, new Date().getMonth()-1, new Date().getDate()))) {
//
//                    alert.setTitle("Alert!!!!!!!!!!");
//                    alert.setHeaderText("la date doit etre sup à la date d'aujourd'hui");;
//                    
//                    if (dateFin.getValue().isBefore(LocalDate.of(dateDebut.getValue().getYear()-1900, dateDebut.getValue().getMonthValue()-1, dateDebut.getValue().getDayOfMonth()))) {
//                        // Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//                        alert.setTitle("Alert!!!!!!!!!!");
//                        alert.setHeaderText("la date debut doit etre sup à la date fin");
//                    }
//                }
//            }
//        });
        WorkshopServices ws = new WorkshopServices();
        //Workshop w = new Workshop();
        //nom.setText(w.getNomWorkshop());
        type.setValue("patisserie traditionnelle");
        type.setItems(typeList);
        //prix.setText(String.valueOf(w.getPrix()));
        // nbrePlace.setText(String.valueOf(w.getNbPlace()));

    }

    @FXML
    public void AjouterWorkshop(ActionEvent e) throws IOException {
        WorkshopServices ws = new WorkshopServices();

        if ((nom.getText().isEmpty() || adresse.getText().isEmpty() || description.getText().isEmpty()
                || nbrePlace.getText().isEmpty())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Veuillez remplir tous les champs");
            alert.showAndWait();
        }
        if ((dateDebut.getValue().isAfter(dateFin.getValue()))) {
            alert.setHeaderText("verifiez vos dates");
            alert.showAndWait();
        } else {

            Workshop w = new Workshop(Integer.SIZE, nom.getText(), adresse.getText(), type.getValue().toString(),
                    new Timestamp(dateDebut.getValue().getYear() - 1900, dateDebut.getValue().getMonthValue() - 1, dateDebut.getValue().getDayOfMonth(), 0, 0, 0, 0),
                    new Timestamp(dateFin.getValue().getYear() - 1900, dateFin.getValue().getMonthValue() - 1, dateFin.getValue().getDayOfMonth(), 0, 0, 0, 0),
                    this.newPhoto, new Timestamp(new Date().getTime()),
                    Integer.parseInt(nbrePlace.getText()), Float.parseFloat(prix.getText()), description.getText());
            ws.create(w);
            System.out.println("ajout effectué avec succés");
            nom.clear();
            adresse.clear();
            description.clear();
            dateDebut.setValue(null);
            dateFin.setValue(null);
            prix.clear();
            nbrePlace.clear();

            type.setValue(null);
            goToAffichage(e);
        }
    }

    /*public void EventAdd(ActionEvent w) {
       WorkshopServices ws = new WorkshopServices();

}*/
    @FXML
    void selectPhoto(ActionEvent event) throws Exception {
        FileChooser file = new FileChooser(); //pour choisir la photo
        file.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Images", "*.jpg"));
        file.setTitle("Choisir une photo du produit");
        File selected_photo = file.showOpenDialog((Stage) ajouter.getScene().getWindow());

        this.newPhoto = selected_photo.getAbsolutePath();
        System.out.println(newPhoto);
        this.newPhoto = UploadFile.uploadImage(newPhoto, null);
        System.out.println(this.newPhoto);
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
                lon.setText(result.getGeometry().getLocation().getLatitude() + "");
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

    @Override
    public void directionsReceived(DirectionsResult dr, DirectionStatus ds) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
