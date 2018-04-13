/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.souklemdina.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;

import com.lynden.gmapsfx.service.directions.DirectionStatus;
import com.lynden.gmapsfx.service.directions.DirectionsResult;
import com.lynden.gmapsfx.service.directions.DirectionsServiceCallback;
import com.souklemdina.entities.Evenement;
import com.souklemdina.entities.FosUser;
import com.souklemdina.services.EvenementServices;
import com.souklemdina.services.ParticipantsEventsServices;
import com.souklemdina.util.SessionUser;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class EventAffFXMLController implements Initializable, MapComponentInitializedListener, DirectionsServiceCallback {

    @FXML
    private VBox vbox1;
    @FXML
    private ScrollPane s1;
    private Rating rat;
    private GoogleMapView mapView;
    @FXML
    private MenuButton choice;
    @FXML
    private MenuItem aff1;
    @FXML
    private JFXTextField recherche;
    @FXML
    private JFXComboBox<String> type;
    ObservableList<String> typel = FXCollections.observableArrayList("Culturel", "musical", "autres", "vide dressing");

    List<String> li = new ArrayList<>();
    EvenementServices es = new EvenementServices();
    ParticipantsEventsServices Epe = new ParticipantsEventsServices();
    FosUser f = SessionUser.getUser();
    Evenement evv = new Evenement();
    @FXML
    private AnchorPane a;
    @FXML
    private JFXButton Aj;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // TODO
        List<Evenement> le = new ArrayList<>();
        le = es.afficherev();

        for (Evenement evv : le) {
            String sd = evv.getDateDebut().toString().substring(0, 10);
            String sf = evv.getDateFin().toString().substring(0, 10);

            Rating rat = new Rating();
            Rating rat2 = new Rating();

            Label nom = new Label();
            Label address = new Label();
            Label dated = new Label();
            Label datef = new Label();
            Label type = new Label();
            Label prix = new Label();
            Label heure = new Label();
            Label nbplace = new Label();
            ImageView photo = new ImageView(new Image("http://localhost/SoukLemdina/web/uploads/images/" + evv.getPhoto()));
            photo.setFitHeight(200);
            photo.setFitWidth(200);

            Text add = new Text("Adresse: ");
            Text nb = new Text("Places: ");
            Text ty = new Text("Type: ");
            Text p = new Text("Prix: ");
            Text dd = new Text("Début: ");
            Text df = new Text("Fin: ");
            Text h = new Text("Heure: ");

            add.setFill(Color.DARKORANGE);
            nb.setFill(Color.DARKORANGE);
            ty.setFill(Color.DARKORANGE);
            p.setFill(Color.DARKORANGE);
            dd.setFill(Color.DARKORANGE);
            df.setFill(Color.DARKORANGE);
            h.setFill(Color.DARKORANGE);
            nom.setText(evv.getNomEvenement());
            address.setText(evv.getAdresse());
            prix.setText(String.valueOf(evv.getPrix()));
            nbplace.setText(String.valueOf(evv.getNbPlace()));
            System.out.println(sd);
            dated.setText(sd);
            System.out.println(sf);
            datef.setText(sf);
            type.setText(evv.getType());
            heure.setText(evv.getHeure().toString().substring(11, 16));

            HBox h1 = new HBox();
            HBox btn = new HBox();
            VBox v1 = new VBox();
            VBox v2 = new VBox();
            VBox rv = new VBox();
            HBox hv1 = new HBox();
            Button bt1 = new Button("participer");

            if (evv.getIdUser() == f.getId()) {
                bt1.setVisible(false);
            }
            if (evv.getNbPlace() <= es.nbParticipant(evv)) {
                bt1.setDisable(true);
            }
            if (es.ChangeOn(evv, f.getId())) {
                bt1.setText("annuler participation");
            }

            JFXButton bt = new JFXButton("Afficher Détails");
            Button bt2 = new Button("modifier");
            Button bt3 = new Button("Supprimer");
            Button sig = new Button("Signaler");
            final Separator sep = new Separator();
            vbox1.setSpacing(25);
            sep.setMaxWidth(Double.MAX_EXPONENT);
            h1.setSpacing(10);

            rat.setRating(es.getRateEvents(evv));
            rat.setDisable(true);
            //System.out.println(" /////////" + es.getRateEvents(e));
            rat.setPartialRating(false);
            rat.setMax(5);
            // System.out.println("BEFOOOOOOOORE" + e.getRating());

            rat2.setOnMouseClicked(new EventHandler<Event>() {
                @Override
                public void handle(Event event) {
//                    System.out.println("---------");
//                    System.out.println("  ///*/*/*/" + rat2.getRating());

                }

            });

            rat2.ratingProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                    // rat.setRating(e.getRating());
                    evv.setRating((int) rat2.getRating());

                    System.out.println("ppppppppppp" + rat2.getRating());
                    System.out.println("    AFTEEEEER" + (evv.getRating() + (int) rat2.getRating()));
                    evv.setRating(evv.getRating() + (int) rat2.getRating());
                    es.addRarting(evv);
                    rat.setRating(es.getRateEvents(evv));
                }

            });

            hv1.getChildren().add(nom);
//            v1.getChildren().add(Des);
//            v1.getChildren().add(description);
            v1.getChildren().add(rat);
            v1.getChildren().add(rat2);
            v2.getChildren().add(add);
            v2.getChildren().add(address);
            h1.getChildren().add(p);
            h1.getChildren().add(prix);
            h1.getChildren().add(nb);
            h1.getChildren().add(nbplace);
            h1.getChildren().add(ty);
            h1.getChildren().add(type);
            h1.getChildren().add(dd);
            h1.getChildren().add(dated);
            h1.getChildren().add(df);
            h1.getChildren().add(datef);
            h1.getChildren().add(h);
            h1.getChildren().add(heure);
            h1.getChildren().add(photo);
            btn.getChildren().add(bt1);
            btn.getChildren().add(bt);
            btn.setSpacing(10);
            btn.getChildren().add(bt2);
            btn.getChildren().add(bt3);
            btn.getChildren().add(sig);
            vbox1.getChildren().add(hv1);
            vbox1.getChildren().add(v1);
            vbox1.getChildren().add(v2);
            vbox1.getChildren().add(h1);
            vbox1.getChildren().add(btn);
            vbox1.getChildren().add(sep);
            btn.setAlignment(Pos.CENTER);
            nom.setFont(javafx.scene.text.Font.font("Courier", 20));
            nom.setStyle("-fx-text-fill: #20b2aa");
            address.setFont(javafx.scene.text.Font.font("Courier", 15));

            bt1.setStyle("-fx-background-color: green; -fx-text-fill: white;");
            sig.setStyle("-fx-background-color: green; -fx-text-fill: black;");

            bt3.setStyle("-fx-background-color: red; -fx-text-fill: white;");
            vbox1.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;"
                    + "-fx-border-width: 2;" + "-fx-border-insets: 5;" + "-fx-background-color:white;"
                    + "-fx-border-radius: 5;" + "-fx-border-color: black;" + "-fx-border-height: 70");
            sig.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {

                }
            });
            bt1.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    if (bt1.getText().equals("Participer")) {
                        Epe.participer(evv, f);
                        bt1.setText("Ne plus participer");
                    } else {
                        Epe.pasParticiper(evv, f);
                        bt1.setText("Participer");
                    }
                }
            });
            /////////////////////////BUTTOOOOOOOOOOOOOOONS//////////////////////

            bt.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    Parent homePage;
                    try {
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("EventShowFXML.fxml"));
                        AnchorPane pp = loader.load();
                        EventShowFXMLController shev = loader.getController();
                        a.getChildren().setAll(pp);
                        shev.setEvenement(evv);
                        shev.showEv();

                    } catch (IOException ex) {
                        Logger.getLogger(EventAffFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });

            Calendar cald = Calendar.getInstance();
            cald.setTimeInMillis(evv.getDateDebut().getTime());

            cald.add(Calendar.DAY_OF_YEAR, -7);

            bt2.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        FXMLLoader root = new FXMLLoader(getClass().getResource("UpdateEventFXML.fxml"));
                        AnchorPane AN = root.load();
                        UpdateEventFXMLController Even = root.getController();
                        vbox1.getChildren().setAll(AN);
                        Even.setEvenement(evv);
                        Even.show();

                    } catch (IOException ex) {
                        Logger.getLogger(UpdateEventFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            });
            if (new Timestamp(new Date().getTime()).after(new Timestamp(cald.getTime().getTime()))) {
                bt2.setDisable(true);

            }
            Calendar cal = Calendar.getInstance();

            cal.setTimeInMillis(evv.getDateDebut().getTime());
            cal.add(Calendar.HOUR, -72);

            if (new Timestamp(new Date().getTime()).before(new Timestamp(cal.getTime().getTime()))) {
                bt3.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {

                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

                        alert.setTitle("Alert!!!!!!!!!!");
                        alert.setHeaderText("etes vous sure de bien vouloir supprimer cet evenement");
                        Optional<ButtonType> res = alert.showAndWait();

                        if (res.get() == ButtonType.OK) {

                            es.delete(evv.getId());
                            vbox1.getChildren().remove(hv1);
                            vbox1.getChildren().remove(v1);
                            vbox1.getChildren().remove(h1);
                            vbox1.getChildren().remove(btn);
                            vbox1.getChildren().remove(v2);
                            vbox1.getChildren().remove(sep);
                            Alert alertSec = new Alert(Alert.AlertType.INFORMATION);
                            alertSec.setHeaderText("Evenement suprrimé avec succées");
                            alertSec.showAndWait();

                        }

                    }

                });
            } else {
                bt3.setDisable(true);
            }

            li.add(evv.getNomEvenement());
            TextFields.bindAutoCompletion(recherche, li);
            recherche.validate();

        }

    }

    private void choix(MouseEvent event) {
        type.setVisible(true);

    }

    ///////////////////////////////////////////////FILTERRRRRRRRRRRRRR/////////////////////////////////////////////////////////// 
    @FXML
    private void aff1(ActionEvent event) {

        // Evenement ee = new Evenement();
        type.setPromptText("Choisir un type");
        type.getItems().clear();

        type.setItems(typel);
        type.setOnAction((ActionEvent e) -> {

            vbox1.getChildren().clear();

            List<Evenement> ls = new ArrayList<>();
            ls = es.getEvenementBytype(this.type.getValue());
            for (Evenement evv : ls) {

                System.out.println("   aaaaaaaaaaaaaff");
                String sd = evv.getDateDebut().toString().substring(0, 10);
                String sf = evv.getDateFin().toString().substring(0, 10);

//                es.afficherev();
//                GoogleMapView mapView = new GoogleMapView();
                Rating rat = new Rating();
                Rating rat2 = new Rating();

                Label nom = new Label();
                Label address = new Label();
                Label dated = new Label();
                Label datef = new Label();
                Label type = new Label();
                Label prix = new Label();
                Label nbplace = new Label();
                Text h = new Text("Heure: ");
                Label heure = new Label(evv.getHeure().toString().substring(11, 16));
                ImageView photo = new ImageView(new Image("http://localhost/SoukLemdina/web/uploads/images/" + evv.getPhoto()));
                System.out.println("http://localhost/SoukLemdina/web/uploads/images/" + evv.getPhoto());
                photo.setFitHeight(200);
                photo.setFitWidth(200);

                Text Des = new Text("Description: ");
                Text add = new Text("Adresse: ");
                Text nb = new Text("Places: ");

                Text ty = new Text("type: ");
                Text p = new Text("Prix: ");
                Text dd = new Text("Début: ");
                Text df = new Text("Fin: ");
                //Des.setFill(Color.DARKORANGE);
                add.setFill(Color.DARKORANGE);
                h.setFill(Color.DARKORANGE);
                nb.setFill(Color.DARKORANGE);
                ty.setFill(Color.DARKORANGE);
                p.setFill(Color.DARKORANGE);
                dd.setFill(Color.DARKORANGE);
                df.setFill(Color.DARKORANGE);

                // Label heure = new Label();
                nom.setText(evv.getNomEvenement());
                System.out.println(evv.getNomEvenement());
                address.setText(evv.getAdresse());
                //description.setText(e.getDescription());
                prix.setText(String.valueOf(evv.getPrix()));
                nbplace.setText(String.valueOf(evv.getNbPlace()));
                dated.setText(sd);
                datef.setText(sf);
                type.setText(evv.getType());
                //  heure.setText(e.getHeure().toString());

                //vbox1.getChildren().add(b1);
                HBox h1 = new HBox();
                HBox btn = new HBox();
                VBox v1 = new VBox();
                VBox v2 = new VBox();
                HBox hv1 = new HBox();
                Button bt1 = new Button("participer");
                if (evv.getIdUser() == f.getId()) {
                    bt1.setVisible(false);
                }
                if (evv.getNbPlace() <= es.nbParticipant(evv)) {
                    bt1.setDisable(true);
                }
                if (es.ChangeOn(evv, f.getId())) {
                    bt1.setText("annuler participation");
                }

                JFXButton bt = new JFXButton("Afficher Détails");
                Button bt2 = new Button("modifier");
                Button bt3 = new Button("Supprimer");
                Button sig = new Button("Signaler");

                final Separator sep = new Separator();
                vbox1.setSpacing(25);
                sep.setMaxWidth(Double.MAX_EXPONENT);
                h1.setSpacing(20);

                rat.setRating(es.getRateEvents(evv));
                rat.setDisable(true);
                System.out.println(" /////////" + es.getRateEvents(evv));
                rat.setPartialRating(false);
                rat.setMax(5);

                // System.out.println("BEFOOOOOOOORE" + evv.getRating());
                rat2.setOnMouseClicked(new EventHandler<Event>() {
                    @Override
                    public void handle(Event event) {
                        // System.out.println("  ///*/*/*/" + rat2.getRating());

                    }

                });

                rat2.ratingProperty().addListener(new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                        evv.setRating((int) rat2.getRating());

//                        System.out.println("ppppppppppp" + rat2.getRating());
//                        System.out.println("    AFTEEEEER" + (evv.getRating() + (int) rat2.getRating()));
                        evv.setRating(evv.getRating() + (int) rat2.getRating());
                        es.addRarting(evv);
                        rat.setRating(es.getRateEvents(evv));
                    }

                });

                hv1.getChildren().add(nom);
//            v1.getChildren().add(Des);
//            v1.getChildren().add(description);
                v1.getChildren().add(rat);
                v1.getChildren().add(rat2);

                // v2.getChildren().add(mapView);
                v2.getChildren().add(add);
                v2.getChildren().add(address);
                h1.getChildren().add(p);
                h1.getChildren().add(prix);
                h1.getChildren().add(nb);
                h1.getChildren().add(nbplace);
                h1.getChildren().add(ty);
                h1.getChildren().add(type);
                h1.getChildren().add(dd);

                h1.getChildren().add(dated);
                h1.getChildren().add(df);
                h1.getChildren().add(datef);
                h1.getChildren().add(h);
                h1.getChildren().add(heure);
                h1.getChildren().add(photo);
                btn.getChildren().add(bt1);
                btn.getChildren().add(bt);

                btn.getChildren().add(bt2);
                btn.getChildren().add(bt3);
                btn.getChildren().add(sig);

                vbox1.getChildren().add(hv1);
                vbox1.getChildren().add(v1);
                vbox1.getChildren().add(v2);
                vbox1.getChildren().add(h1);
                vbox1.getChildren().add(btn);
                vbox1.getChildren().add(sep);
                btn.setAlignment(Pos.CENTER);
                btn.setSpacing(10);

                nom.setFont(javafx.scene.text.Font.font("Courier", 20));

                nom.setStyle("-fx-text-fill: #20b2aa");
                address.setFont(javafx.scene.text.Font.font("Courier", 15));

                bt1.setStyle("-fx-background-color: green; -fx-text-fill: white;");

                bt3.setStyle("-fx-background-color: red; -fx-text-fill: white;");

                vbox1.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;"
                        + "-fx-border-width: 2;" + "-fx-border-insets: 5;" + "-fx-background-color:#ffffff;"
                        + "-fx-border-radius: 5;" + "-fx-border-color: black;" + "-fx-border-height: 70;");

/////////////////////////////BUTTONS///////////////////////////////////////////////////////////////
                bt1.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        if (bt1.getText().equals("Participer")) {
                            Epe.participer(evv, f);
                            bt1.setText("Ne plus participer");
                        } else {
                            Epe.pasParticiper(evv, f);
                            bt1.setText("Participer");
                        }
                    }
                });

                bt.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Parent homePage;
                        try {
                            FXMLLoader loader = new FXMLLoader();
                            loader.setLocation(getClass().getResource("EventShowFXML.fxml"));
                            AnchorPane xc = loader.load();
                            EventShowFXMLController shev = loader.getController();

                            shev.setEvenement(evv);
                            shev.showEv();
                            a.getChildren().setAll(xc);

                        } catch (IOException ex) {
                            Logger.getLogger(EventAffFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
                Calendar cal2 = Calendar.getInstance();
                cal2.setTimeInMillis(evv.getDateDebut().getTime());

                cal2.add(Calendar.DAY_OF_YEAR, -7);
                System.out.println(new Timestamp(cal2.getTime().getTime()));

                bt2.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        try {
                            FXMLLoader root = new FXMLLoader(getClass().getResource("UpdateEventFXML.fxml"));
                            AnchorPane x = root.load();
                            UpdateEventFXMLController Evente = root.getController();
                            vbox1.getChildren().setAll(x);
                            Evente.setEvenement(evv);
                            Evente.show();

                        } catch (IOException ex) {
                            Logger.getLogger(UpdateEventFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                });

                if (new Timestamp(new Date().getTime()).after(new Timestamp(cal2.getTime().getTime()))) {
                    bt2.setDisable(true);

                }

                Calendar cal = Calendar.getInstance();

                cal.setTimeInMillis(evv.getDateDebut().getTime());
                cal.add(Calendar.HOUR, -72);

                if (new Timestamp(new Date().getTime()).before(new Timestamp(cal.getTime().getTime()))) {
                    bt3.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {

                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

                            alert.setTitle("Alert!!!!!!!!!!");
                            alert.setHeaderText("etes vous sure de bien vouloir supprimer cet evenement");
                            Optional<ButtonType> res = alert.showAndWait();

                            if (res.get() == ButtonType.OK) {

                                es.delete(evv.getId());
                                vbox1.getChildren().remove(hv1);
                                vbox1.getChildren().remove(v1);
                                vbox1.getChildren().remove(h1);
                                vbox1.getChildren().remove(btn);
                                vbox1.getChildren().remove(v2);
                                vbox1.getChildren().remove(sep);
                                Alert alertSec = new Alert(Alert.AlertType.INFORMATION);
                                alertSec.setHeaderText("Evenement suprrimé avec succées");
                                alertSec.showAndWait();

                            }

                        }

                    });
                } else {
                    bt3.setDisable(true);
                }

            }
//

        });
    }

// a
    @Override
    public void mapInitialized() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void directionsReceived(DirectionsResult dr, DirectionStatus ds) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @FXML
    void goToAj(ActionEvent e) throws IOException {

        AnchorPane lp = FXMLLoader.load(getClass().getResource("EventAddFXML.fxml"));
        this.a.getChildren().setAll(lp);

    }

}
