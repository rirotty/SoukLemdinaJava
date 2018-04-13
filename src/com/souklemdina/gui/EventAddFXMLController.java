/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.souklemdina.gui;

import com.jfoenix.controls.JFXButton;
import java.io.File;
import com.souklemdina.entities.Evenement;
import com.souklemdina.util.UploadFile;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.DirectionsPane;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.service.directions.DirectionStatus;
import com.lynden.gmapsfx.service.directions.DirectionsResult;
import com.lynden.gmapsfx.service.directions.DirectionsService;
import com.lynden.gmapsfx.service.directions.DirectionsServiceCallback;
import com.lynden.gmapsfx.service.geocoding.GeocodingResult;
import com.lynden.gmapsfx.service.geocoding.GeocodingService;
import com.souklemdina.services.EvenementServices;
import com.souklemdina.util.SessionUser;
import com.souklemdina.util.UploadFile;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
 * @author asus
 */
public class EventAddFXMLController implements Initializable, MapComponentInitializedListener, DirectionsServiceCallback {

    private GeocodingService geocodingService;
    protected DirectionsPane directionsPane;

    List<String> l = new ArrayList();
    protected DirectionsService directionsService;
    String[] s;
    protected StringProperty from = new SimpleStringProperty();

    GoogleMapView mapView = new GoogleMapView();
    private String newPhoto;
    @FXML
    private JFXTextField nonevent;
    @FXML
    private JFXTextField adresse;
    @FXML
    private JFXTextField prix;
    @FXML
    private JFXTextField nbplace;
    @FXML
    private JFXDatePicker datedebut;
    @FXML
    private JFXTimePicker heure;
    @FXML
    private TextArea description;
    @FXML
    private JFXDatePicker datefin;
    private JFXButton photo;
    @FXML
    private JFXComboBox<String> type;
    ObservableList<String> typelist = FXCollections.observableArrayList("Culturel", "musical", "autres", "vide dressing");
    @FXML
    private JFXButton add;
    @FXML
    private TextField lon;
    @FXML
    private TextField lat;
    @FXML
    private JFXButton annuler;
    @FXML
    private AnchorPane anch;
    @FXML
    private JFXButton ajouterphoto;

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

        EvenementServices es = new EvenementServices();

        type.setValue("Culturel");
        //type.getItems().add("Culturel");
        type.setItems(typelist);
        prix.setText("20");

    }

    @FXML
    private void fromOnKeyTypedEvent(KeyEvent event) {
        try {
            geocodingService = new GeocodingService();
        } catch (Exception e) {
//            System.out.println(e.getMessage());
        }
        geocodingService.geocode(adresse.getText(),
                (GeocodingResult[] results, com.lynden.gmapsfx.service.geocoding.GeocoderStatus status) -> {
                    l.clear();
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

    @FXML
    public void EventAdd(ActionEvent e) {
        EvenementServices es = new EvenementServices();
        if (nonevent.getText().isEmpty()
                || adresse.getText().isEmpty()
                || description.getText().isEmpty()
                || nbplace.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setHeaderText("Veuillez remplir tous les champs");
            alert.setContentText("Veuillez remplir tous les champs");
            alert.showAndWait();
        }
        if (new Timestamp(datedebut.getValue().getYear() - 1900, datedebut.getValue().getMonthValue() - 1, datedebut.getValue().getDayOfMonth(), 0, 0, 0, 0)
                .after(new Timestamp(datefin.getValue().getYear() - 1900, datefin.getValue().getMonthValue() - 1, datefin.getValue().getDayOfMonth(), 0, 0, 0, 0))) {

            Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setHeaderText("Alerte Date");
            alert.setContentText("Veuillez verifier les dates");
            alert.showAndWait();
        } else {

            Evenement ev = new Evenement(Integer.SIZE, nonevent.getText(),
                    new Timestamp(datedebut.getValue().getYear() - 1900, datedebut.getValue().getMonthValue() - 1, datedebut.getValue().getDayOfMonth(), 0, 0, 0, 0),
                    new Timestamp(datefin.getValue().getYear() - 1900, datefin.getValue().getMonthValue() - 1, datefin.getValue().getDayOfMonth(), 0, 0, 0, 0),
                    adresse.getText(),
                    Integer.parseInt(prix.getText()), description.getText(), Integer.parseInt(nbplace.getText()),
                    type.getValue(),
                    new Timestamp(0, 0, 0, heure.getValue().getHour(), heure.getValue().getMinute(), 0, 0),
                    this.newPhoto);
            ev.setIdUser(SessionUser.getUser().getId());
            es.create(ev);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);

            alert.setHeaderText("Event Added");
            alert.setContentText("Event successfully added");
            alert.showAndWait();

            System.out.println("l'evenement " + ev.getNomEvenement() + " a été ajouté avec succés");

            Parent homePage;
            try {
                AnchorPane pp = FXMLLoader.load(getClass().getResource("EventAffFXML.fxml"));
                this.anch.getChildren().setAll(pp);

            } catch (IOException ex) {
                Logger.getLogger(EventAddFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    @FXML
    void selectPhoto(ActionEvent event) throws Exception {
        FileChooser file = new FileChooser(); //pour choisir la photo
        file.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Images", "*.jpg"));
        file.setTitle("Choisir une photo du produit");
        File selected_photo
                = file.showOpenDialog((Stage) ajouterphoto.getScene().getWindow());

        this.newPhoto = selected_photo.getAbsolutePath();
        System.out.println(newPhoto);
        this.newPhoto = UploadFile.uploadImage(newPhoto, null);
        System.out.println(this.newPhoto);
    }

    @FXML
    private void goToAffichage(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        AnchorPane pp = FXMLLoader.load(getClass().getResource("EventAffFXML.fxml"));
        this.anch.getChildren().setAll(pp);

    }

    @Override
    public void directionsReceived(DirectionsResult dr, DirectionStatus ds) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
