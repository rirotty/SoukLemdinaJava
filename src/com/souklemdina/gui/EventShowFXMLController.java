/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.souklemdina.gui;

import com.jfoenix.controls.JFXButton;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.InfoWindow;
import com.lynden.gmapsfx.javascript.object.InfoWindowOptions;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import com.lynden.gmapsfx.service.directions.DirectionStatus;
import com.lynden.gmapsfx.service.directions.DirectionsResult;
import com.lynden.gmapsfx.service.directions.DirectionsServiceCallback;
import com.lynden.gmapsfx.service.geocoding.GeocodingResult;
import com.lynden.gmapsfx.service.geocoding.GeocodingService;
import com.souklemdina.entities.Evenement;
import com.souklemdina.entities.FosUser;
import com.souklemdina.services.EvenementServices;
import com.souklemdina.services.ParticipantsEventsServices;
import com.souklemdina.util.SessionUser;

import java.io.IOException;
import java.net.URL;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class EventShowFXMLController implements Initializable, MapComponentInitializedListener, DirectionsServiceCallback {
    
    private GeocodingService geocodingService;
    private MarkerOptions markerOptions;
    private GoogleMap map;
    private GoogleMapView mapView = new GoogleMapView();
    ParticipantsEventsServices Epe = new ParticipantsEventsServices();
    FosUser f = SessionUser.getUser();

//    List<String>les=new ArrayList<>();
    @FXML
    private Label nom;
    @FXML
    private HBox sh;
    public static Evenement EventSelected;
    @FXML
    private HBox sh1;
    private Evenement evs;
    @FXML
    private HBox sh2;
    @FXML
    private AnchorPane anchp;

    /**
     * Initializes the controller class.
     */
    public void setEvenement(Evenement evs) {
        this.evs = evs;
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mapView.addMapInializedListener(this);
        
    }
    
    public void showEv() {
        String sd = evs.getDateDebut().toString().substring(0, 10);
        String sf = evs.getDateFin().toString().substring(0, 10);
        EvenementServices es = new EvenementServices();
        ImageView photo = new ImageView(new Image("http://localhost/SoukLemdina/web/uploads/images/" + evs.getPhoto()));
        photo.setFitHeight(200);
        photo.setFitWidth(454);
        
        nom.setText(evs.getNomEvenement());
        nom.setAlignment(Pos.CENTER);
        Label dd = new Label();
        Label df = new Label();
        Label ty = new Label();
        Label des = new Label();
        //Label ph = new Label();
        Label ad = new Label();
        Label pr = new Label();
        Label pl = new Label();
        Text Des = new Text("Description: ");
        Text add = new Text("Adresse: ");
        Text nb = new Text("Places: ");
        GoogleMapView map = new GoogleMapView();
     
        Text typ = new Text("type: ");
        Text p = new Text("Prix: ");
        Text dds = new Text("Début: ");
        Text dfs = new Text("Fin: ");
        add.setFill(Color.DARKCYAN.saturate());
        nb.setFill(Color.AQUAMARINE);
        typ.setFill(Color.AQUAMARINE);
        p.setFill(Color.AQUAMARINE);
        dds.setFill(Color.AQUAMARINE);
        dfs.setFill(Color.AQUAMARINE);
        Des.setFill(Color.DARKCYAN);
        JFXButton part = new JFXButton("Participer");
        JFXButton list = new JFXButton("Retour à la liste ");
        dd.setText(sd);
        df.setText(sf);

        ty.setText(evs.getType());
        pr.setText(String.valueOf(evs.getPrix()));
        pl.setText(String.valueOf(evs.getNbPlace()));
        
        des.setText(evs.getDescription());
        des.setWrapText(true);
        
        ad.setText(evs.getAdresse());
        //ph.setText(evs.getPhoto());
        
        sh.getChildren().addAll(dds, dd, dfs, df, typ, ty, p, pr, nb, pl);
        sh.setSpacing(30);
        sh.setAlignment(Pos.CENTER);
        VBox vb = new VBox();
        VBox vb1 = new VBox();
        Separator s1 = new Separator(Orientation.VERTICAL);
        vb1.getChildren().addAll(add, map);
        vb.getChildren().addAll(Des, des, photo);
        
        vb.setSpacing(10);
        // sh1.getChildren().add(ad);
        sh1.getChildren().addAll(vb1, s1, vb);
        sh1.setSpacing(5);

        // sh1.setAlignment(Pos.CENTER);
        sh2.getChildren().addAll(part, list);
        
        sh2.setAlignment(Pos.CENTER);
        
        part.setStyle("-fx-background-color: green; -fx-text-fill: white;");
        list.setStyle("-fx-background-color: grey; -fx-text-fill: white;");
        nom.setFont(javafx.scene.text.Font.font("Courier", 20));
        nom.setStyle("-fx-text-fill: #07d7aa ;-fx-font-weight: bold");
        
        if (evs.getNbPlace() <= es.nbParticipant(evs)) {
            part.setDisable(true);
        }
        if (es.ChangeOn(evs, f.getId())) {
            part.setText("annuler participation");
        }

        //  nom.setStyle("-fx-font:20px");
        part.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (part.getText().equals("Participer")) {
                    Epe.participer(evs, f);
                    part.setText("Ne plus participer");
                } else {
                    Epe.pasParticiper(evs, f);
                    part.setText("Participer");
                }
            }
        });        
        
        
        
        
        list.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            
            public void handle(ActionEvent event) {
                FXMLLoader root = new FXMLLoader(getClass().getResource("EventAffFXML.fxml"));
                try {
                    AnchorPane v = root.load();
                    anchp.getChildren().setAll(v);
                } catch (IOException ex) {
                    
                    Logger.getLogger(EventAffFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
                EventAffFXMLController afev = root.getController();
            }
            
        });
    }


    
    @Override
    public void mapInitialized() {
        Double lon = new Double(0);
        Double lat = new Double(0);
        geocodingService = new GeocodingService();
        MapOptions options = new MapOptions();
        
        options.center(new LatLong(34.3055732, 11.255412))
                .zoomControl(true)
                .zoom(10)
                .overviewMapControl(false)
                .mapType(MapTypeIdEnum.ROADMAP);
        GoogleMap map = mapView.createMap(options);
        geocodingService.geocode(evs.getAdresse(),
                (GeocodingResult[] results, com.lynden.gmapsfx.service.geocoding.GeocoderStatus status) -> {
                    LatLong l = new LatLong(results[0].getGeometry().getLocation().getLatitude(),
                            results[0].getGeometry().getLocation().getLongitude());
                    Marker myMarker = null;
                    markerOptions = new MarkerOptions();
                    markerOptions.position(l)
                            .title(evs.getNomEvenement())
                            .visible(true);
                    
                    myMarker = new Marker(markerOptions);
                    InfoWindowOptions infoOptions = new InfoWindowOptions();
                    infoOptions.content(evs.getNomEvenement())
                            .position(l);
                    System.out.println(map.getCenter());
                    
                    InfoWindow window = new InfoWindow(infoOptions);
                    window.open(map, myMarker);
                    map.addMarker(myMarker);
                });
        
    }
    
    @Override
    public void directionsReceived(DirectionsResult dr, DirectionStatus ds) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
