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
import com.lynden.gmapsfx.service.directions.DirectionStatus;
import com.lynden.gmapsfx.service.directions.DirectionsResult;
import com.lynden.gmapsfx.service.directions.DirectionsService;
import com.lynden.gmapsfx.service.directions.DirectionsServiceCallback;
import com.lynden.gmapsfx.service.geocoding.GeocodingResult;
import com.lynden.gmapsfx.service.geocoding.GeocodingService;
import com.souklemdina.entities.LigneDeCommande;
import com.souklemdina.services.LigneDeCommandeServices;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author hatem
 */
public class CommandeFXMLController implements Initializable, MapComponentInitializedListener, DirectionsServiceCallback {
    public static String adresse;
            public static String adresse2;
                    public static String ville;
                            public static int codePostal;
                                    public static String numTel;
    
    private GeocodingService GeocodingService;
   
    @FXML
    private TextField txtVille;
    @FXML
    private TextField txtCodePostal;
    @FXML
    private TextField txtNumeroTel;
    @FXML
    private Button btnPaiement;
    @FXML
    private Button btnPanier;
    @FXML
    private Button btnAnnuler;
//AfficherLigneDeCommandeFXMLController X;
    LigneDeCommandeServices lcs = new LigneDeCommandeServices();
    private GeocodingService geocodingService;
    protected DirectionsPane directionsPane;

    List<String> l = new ArrayList();
    protected DirectionsService directionsService;
    String[] s;
    protected StringProperty from = new SimpleStringProperty();

    GoogleMapView mapView = new GoogleMapView();
    @FXML
    private Label lon;
    @FXML
    private Label lat;
    
    @FXML
    private JFXTextField txtAdresse2;
    @FXML
    private JFXTextField txtAdr;
    @FXML
    private AnchorPane ancp;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
            // TODO
            //k=0;
            //System.out.println(k);
        
          /*  FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("AfficherLigneDeCommandeFXML.fxml"));
            AfficherLigneDeCommandeFXMLController lc = loader.getController();
            */
            //k=lc.flag();
        btnAnnuler.setVisible(false);
        lon.setVisible(false);
        lat.setVisible(false);
        if(AfficherLigneDeCommandeFXMLController.X==1)
        {
            LigneDeCommande lc= new LigneDeCommande();
            lc=lcs.getLigneDeCommandeById(AfficherLigneDeCommandeFXMLController.K);
            txtAdr.setText(lc.getAdresse());
            txtAdresse2.setText(lc.getAdresse2());
            txtVille.setText(lc.getVille());
            txtCodePostal.setText(String.valueOf(lc.getCodePostal()));
            txtNumeroTel.setText(lc.getNumTel());
            btnPaiement.setText("Modifier");
            btnAnnuler.setVisible(true);
        }
                        System.out.println(AfficherLigneDeCommandeFXMLController.K);

        txtAdr.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (!newPropertyValue) {
                    txtAdr.validate();
                }
            }
        });
    }
    @FXML
    private void fromOnKeyTypedEvent(KeyEvent event) {
        try {
            geocodingService = new GeocodingService();
        } catch (Exception e) {
//            System.out.println(e.getMessage());
        }
        geocodingService.geocode(txtAdr.getText(),
                (GeocodingResult[] results, com.lynden.gmapsfx.service.geocoding.GeocoderStatus status) -> {
                    l.clear();
                    for (int i = 0; i < results.length; i++) {
                        s= new String[results.length];
                        s[i] = results[i].getFormattedAddress();
                        System.out.println(results[i].getJSObject());
                        l.add(results[i].getFormattedAddress());

                    }

                    for (GeocodingResult result : results) {

                        TextFields.bindAutoCompletion(txtAdr, s);
                        lon.setText(result.getGeometry().getLocation().getLatitude() + "");
                        lat.setText(result.getGeometry().getLocation().getLongitude() + "");
                    }
TextFields.bindAutoCompletion(this.txtAdr, t->{
    return l;
});
                    


                });
    }

    public void mapInitialized() {
        GeocodingService geocodingService = new GeocodingService();
        MapOptions options = new MapOptions();

        options.center(new LatLong(34.3055732, 11.255412))
                .zoomControl(true)
                .zoom(6)
                .overviewMapControl(false)
                .mapType(MapTypeIdEnum.ROADMAP);
        GoogleMap map = mapView.createMap(options);
        DirectionsService directionsService = new DirectionsService();
        directionsPane = mapView.getDirec();

    }


    @FXML
    private void goToPaiement(ActionEvent event) throws IOException {
        if (AfficherLigneDeCommandeFXMLController.X==0)
        {
     
               // PageLogicFXMLController p = loader.getController();
               adresse=txtAdr.getText();
               adresse2=txtAdresse2.getText();
               ville=txtVille.getText();
               codePostal=Integer.parseInt(txtCodePostal.getText());
               numTel=txtNumeroTel.getText();
                  AnchorPane p = null;
                     try {
                         p = FXMLLoader.load(getClass().getResource("PaypalFXML.fxml"));
                     } catch (IOException ex) {
                         Logger.getLogger(PaypalFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                     }
       this.ancp.getChildren().setAll(p);
        }
        else
        {
            
            LigneDeCommande lc= new LigneDeCommande();
            lc=lcs.getLigneDeCommandeById(AfficherLigneDeCommandeFXMLController.K);
            
            lc.setAdresse(txtAdr.getText());
            lc.setAdresse2(txtAdresse2.getText());
            lc.setVille(txtVille.getText());
            lc.setCodePostal(Integer.parseInt(txtCodePostal.getText()));
            lc.setNumTel(txtNumeroTel.getText());
            lcs.update(lc);
            
            AfficherLigneDeCommandeFXMLController.X=0;
            
        AnchorPane p = null;
                     try {
                         p = FXMLLoader.load(getClass().getResource("AfficherCommandeFXML.fxml"));
                     } catch (IOException ex) {
                         Logger.getLogger(PaypalFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                     }
       this.ancp.getChildren().setAll(p);
        }
        
    }
    @FXML
    private void cancel(ActionEvent event) throws IOException {
        AfficherLigneDeCommandeFXMLController.X=0;
          AnchorPane p = null;
                     try {
                         p = FXMLLoader.load(getClass().getResource("AfficherCommandeFXML.fxml"));
                     } catch (IOException ex) {
                         Logger.getLogger(PaypalFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                     }
       this.ancp.getChildren().setAll(p);

    }
    @FXML
    private void goToPanier(ActionEvent event) throws IOException {
        AfficherLigneDeCommandeFXMLController.X=0;
          AnchorPane p = null;
                     try {
                         p = FXMLLoader.load(getClass().getResource("AjoutPanier.fxml"));
                     } catch (IOException ex) {
                         Logger.getLogger(PaypalFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                     }
       this.ancp.getChildren().setAll(p);

    }

    @Override
    public void directionsReceived(DirectionsResult dr, DirectionStatus ds) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
