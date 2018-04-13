/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.souklemdina.gui;

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
import com.souklemdina.entities.FosUser;
import com.souklemdina.entities.ParticipantWork;
import com.souklemdina.entities.Profile;
import com.souklemdina.entities.Workshop;
import com.souklemdina.services.FosUserServices;
import com.souklemdina.services.ParticipantWorkService;
import com.souklemdina.services.WorkshopServices;
import com.souklemdina.util.SessionUser;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class PageWorkController implements Initializable, MapComponentInitializedListener, DirectionsServiceCallback {

    private MarkerOptions markerOptions;
    private GoogleMap map;
private GeocodingService geocodingService;
    private ParticipantWork pw = new ParticipantWork();
    private ParticipantWorkService pws = new ParticipantWorkService();
    private FosUser u = SessionUser.getUser();
    private FosUserServices us = new FosUserServices();
    @FXML
    private VBox V1;
    @FXML
    private Label nomWork;
    @FXML
    private Label rating;
    @FXML
    private Button participer;
    @FXML
    private ImageView imageW;
    @FXML
    private Label desc;
    @FXML
    private Label f;
    @FXML
    private Label nomForm;
    @FXML
    private ImageView photoForm;
    @FXML
    private Label ty;
    @FXML
    private Label deb;
    @FXML
    private Label fin;
    @FXML
    private Label prix;
    @FXML
    private Rating r1;
    @FXML
    private GoogleMapView addresse;
    @FXML
    private HBox h1;
    @FXML
    private HBox h2;
    Profile p = new Profile(5);
    Workshop w = new Workshop();

    WorkshopServices ws = new WorkshopServices();

    ObservableList<String> typeList = FXCollections.observableArrayList("patisserie Traditionnelle", "Haute couture", "autre");
    @FXML
    private Button signaler;
    @FXML
    private HBox v;
    @FXML
    private Label email;
    @FXML
    private Label phone;
    @FXML
    private Button list;
    @FXML
    private AnchorPane anc;
    @FXML
    private ImageView ii;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        addresse.addMapInializedListener(this);
    }

    public void setWorkshop(Workshop w) {
        this.w = w;
    }

    public void affichage() {
        String s = w.getDateDebut().toString().substring(0, 10);
        String s1 = w.getDateFin().toString().substring(0, 10);
        nomWork.setText(w.getNomWorkshop());
        nomWork.setFont(javafx.scene.text.Font.font("Arial", 25));

        //addresse.setT(w.getAdresse());
        ty.setText(w.getType());

        prix.setText(String.valueOf(w.getPrix()));
        desc.setText(w.getDescription());
        participer.setAlignment(Pos.CENTER);
        signaler.setAlignment(Pos.CENTER);
        photoForm.setImage(new Image("http://localhost/SoukLemdina/web/uploads/images/" + ws.getProfilByIdUser(w.getIdUser()).getImage()));
        System.out.println("ee" + ws.getProfilByIdUser(w.getIdUser()).getImage());
        imageW.setFitHeight(196);
        imageW.setFitWidth(341);
        System.out.println(w.getIdUser());
        System.out.println("idu" + w.getAdresse());
        nomForm.setText("nom: " + ws.getUserById(w.getIdUser()).getUsername());
        email.setText("email: " + ws.getUserById(w.getIdUser()).getEmail());
        phone.setText("num Tel: " + ws.getUserById(w.getIdUser()).getPhone());
        deb.setText(s);
        fin.setText(s1);
        V1.setSpacing(20);
        h1.setSpacing(15);
        h2.setSpacing(15);
        nomWork.setText(w.getNomWorkshop());
        r1.setRating(ws.getRateWork(w));
        imageW.setImage(new Image("http://localhost/SoukLemdina/web/uploads/images/" + w.getImage()));
        System.out.println("http://localhost/SoukLemdina/web/uploads/images/" + w.getImage());
        r1.setDisable(false);
        r1.setPartialRating(false);
        r1.setMax(5);

        r1.setOnMouseClicked(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                System.out.println("//////////////////////" + r1.getRating());

                w.setRating((int) r1.getRating());

            }
        });

        r1.ratingProperty().addListener(new ChangeListener<Number>() {
            @Override

            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                w.setRating((int) r1.getRating());
                System.out.println(" chouffffffffffffffffff" + r1.getRating());
                ws.addWRarte(w);
                r1.setRating(ws.getRateWork(w));
            }

        });
        if (w.getIdUser() != u.getId()) {
            list.setVisible(false);
        }
        if (w.getNbPlace() <= ws.nbParticipant(w)) {
            participer.setDisable(true);
        }

        if (ws.switchP(w, u.getId())) {
            participer.setText("Ne Plus Participer");
        }

        participer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (participer.getText().equals("Participer")) {
                    pws.participer(w, u);
                    participer.setText("Ne plus participer");
                } else {
                    pws.pasParticiper(w, u);
                    participer.setText("Participer");
                }
            }
        });

    }

    @FXML
    void goTolista(ActionEvent e) throws IOException {

        AnchorPane p = FXMLLoader.load(getClass().getResource("ListePartic.fxml"));
        this.V1.getChildren().setAll(p);

    }

    @FXML
    void goToli(MouseEvent e) throws IOException {

        AnchorPane p = FXMLLoader.load(getClass().getResource("ListeWork.fxml"));
        this.anc.getChildren().setAll(p);

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
        GoogleMap map = addresse.createMap(options);
        geocodingService.geocode(w.getAdresse(),
                (GeocodingResult[] results, com.lynden.gmapsfx.service.geocoding.GeocoderStatus status) -> {
                    LatLong l = new LatLong(results[0].getGeometry().getLocation().getLatitude(),
                            results[0].getGeometry().getLocation().getLongitude());
                    Marker myMarker = null;
                    markerOptions = new MarkerOptions();
                    markerOptions.position(l)
                            .title(w.getNomWorkshop())
                            .visible(true);
                    
                    myMarker = new Marker(markerOptions);
                    InfoWindowOptions infoOptions = new InfoWindowOptions();
                    infoOptions.content(w.getNomWorkshop())
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
