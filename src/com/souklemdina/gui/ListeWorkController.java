/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.souklemdina.gui;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.souklemdina.entities.FosUser;
import com.souklemdina.entities.ParticipantWork;
import com.souklemdina.entities.Workshop;
import com.souklemdina.services.ParticipantWorkService;
import com.souklemdina.services.WorkshopServices;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
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
import javafx.scene.control.Separator;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class ListeWorkController implements Initializable {

    @FXML
    private VBox V1;
    @FXML
    private AnchorPane Anc;
    FosUser u = SessionUser.getUser();
    @FXML
    private JFXTextField recheche;
    @FXML
    private MenuButton menuchoix;
    @FXML
    private MenuItem affiche1;
    @FXML
    private MenuItem affiche2;
    @FXML
    private JFXComboBox<String> typerecherche;
    private ParticipantWork pw = new ParticipantWork();
    private ParticipantWorkService pws = new ParticipantWorkService();
    private WorkshopServices ws = new WorkshopServices();
    List<String> listN = new ArrayList<>();
    ObservableList<String> typeList = FXCollections.observableArrayList("patisserie Traditionnelle", "Haute couture", "autre");
    VBox cadre = new VBox();
    Workshop w = new Workshop();
//    @FXML
//    private Label iddd;
//    @FXML
//    private ImageView id;
    @FXML
    private Label iddd;
    @FXML
    private ImageView id;
    @FXML
    private Button aj;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Anc.prefHeight(Double.max(1000, 1000));
        V1.prefHeightProperty().bind(Anc.widthProperty());

        Workshop ww = new Workshop();
        WorkshopServices ws = new WorkshopServices();
//ObservableList<String> items = FXCollections.observableArrayList();
        List<Workshop> listWorks = new ArrayList<>();
        listWorks = ws.aff();

        List<String> listN = new ArrayList<>();
        listWorks = ws.aff();
        for (Workshop w : listWorks) {

            String s = w.getDateDebut().toString().substring(0, 10);
            String s1 = w.getDateFin().toString().substring(0, 10);
            Button participer = new Button("Participer");

            participer.setStyle("-fx-border-style: solid inside;"
                    + "-fx-background-color:grey;" + "-fx-border-color: #07d7aa;" + "-fx-text-fill:#07d7aa;");
            if (w.getIdUser() == u.getId()) {
                participer.setVisible(false);
            }
            if (w.getNbPlace() <= ws.nbParticipant(w)) {
                participer.setDisable(true);
            }
            if (ws.switchP(w, u.getId())) {
                participer.setText("Ne Plus Participer");
            }

            Button modif = new Button("Modifier");
            modif.setStyle("-fx-border-style: solid inside;"
                    + "-fx-background-color:grey;" + "-fx-border-color: #07d7aa;" + "-fx-border-color: #07d7aa;" + "-fx-text-fill:#07d7aa;");
            if (w.getIdUser() != u.getId()) {
                modif.setVisible(false);
            }
            Button sup = new Button("Supprimer");
            sup.setStyle("-fx-border-style: solid inside;"
                    + "-fx-background-color:#ea3706;" + "-fx-border-color: #07d7aa;" + "-fx-border-color: #07d7aa;" + "-fx-text-fill:#07d7aa;");

            if (w.getIdUser() != u.getId()) {
                sup.setVisible(false);
            }
            Label nom = new Label();
            nom.setText(w.getNomWorkshop().toString());
            nom.setStyle("-fx-font-weight: bold");
            nom.setFont(javafx.scene.text.Font.font("Arial", 25));

            nom.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    try {
                        FXMLLoader root = new FXMLLoader(getClass().getResource("PageWork.fxml"));
                        AnchorPane x = root.load();
                        PageWorkController workC = root.getController();
                        V1.getChildren().setAll(x);
                        workC.setWorkshop(w);
                        workC.affichage();

                    } catch (IOException ex) {
                        Logger.getLogger(PageWorkController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            });
            ImageView img1 = new ImageView();
//          img1.setImage(new Image("http://localhost/SoukLemdina/web/uploads/images/"+w.getImage()));
//         System.out.println("http://localhost/SoukLemdina/web/uploads/images/"+w.getImage());
            javafx.scene.image.Image img = new javafx.scene.image.Image("http://localhost/SoukLemdina/web/uploads/images/" + w.getImage(), 300, 220, true, true);
            img1.setImage(img);
            Label des = new Label();
            des.setText(w.getDescription());
            des.setWrapText(true);
            Label place = new Label();
            place.setText(String.valueOf(w.getNbPlace()));
            Label prix = new Label();
            prix.setText(String.valueOf(w.getPrix()));
            Label deb = new Label();
            deb.setText(s);
            Label fin = new Label();
            fin.setText(s1);
            Label type = new Label();
            type.setText(w.getType());

            Text add = new Text("Adresse: ");
            add.setStyle("-fx-font-weight: regular");
            Text nb = new Text("Places: ");
            Text ty = new Text("type: ");
            Text p = new Text("Prix: ");
            Text dd = new Text("Début: ");
            Text df = new Text("Fin: ");
            HBox hh = new HBox();
            VBox cadre = new VBox();
            HBox Bot = new HBox();
            VBox h1 = new VBox();
            HBox h2 = new HBox();
            VBox vv = new VBox();
            VBox vvv = new VBox();
            Bot.setSpacing(35);
            h1.getChildren().add(nom);

            h2.getChildren().add(h1);

            h2.getChildren().add(nb);
            h2.getChildren().add(place);
            h2.getChildren().add(p);
            h2.getChildren().add(prix);

            h2.getChildren().add(dd);
            h2.getChildren().add(deb);
            h2.getChildren().add(df);
            h2.getChildren().add(fin);
            //h2.getChildren().add(img1);

            Separator soo = new Separator(Orientation.VERTICAL);

            hh.getChildren().addAll(h2, soo);
            hh.setAlignment(Pos.TOP_RIGHT);
            Bot.getChildren().addAll(participer, modif, sup, img1);
            Bot.setAlignment(Pos.TOP_LEFT);
            vvv.getChildren().add(ty);
            vvv.getChildren().add(type);
            cadre.getChildren().add(h1);
            cadre.getChildren().add(h2);
            cadre.getChildren().add(vv);
            cadre.getChildren().add(vvv);
            cadre.getChildren().add(hh);

            cadre.getChildren().add(Bot);
            // cadre.getTransforms().addAll(scale);
            V1.getChildren().add(cadre);

            final Separator separator = new Separator();
            separator.setMaxWidth(40);
            h2.setSpacing(20);
            Separator separator2 = new Separator();
            separator2.setOrientation(Orientation.VERTICAL);

            separator2.setLayoutX(20);
            vv.setSpacing(40);

            V1.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;"
                    + "-fx-border-width: 2;" + "-fx-border-insets: 10;"
                    + "-fx-border-radius: 5;" + "-fx-border-color: orange;" + "-fx-width: 100%;");
            System.out.println("vsss");

            cadre.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;"
                    + "-fx-border-width: 2;" + "-fx-border-insets: 5;"
                    + "-fx-border-radius: 2;" + "-fx-border-color: grey;" + "-fx-width: 100%;");

            des.setMinHeight(Control.USE_COMPUTED_SIZE);
            Calendar cal2 = Calendar.getInstance();
            cal2.setTimeInMillis(w.getDateDebut().getTime());

            cal2.add(Calendar.DAY_OF_YEAR, -7);
            System.out.println(new Timestamp(cal2.getTime().getTime()));

            if (new Timestamp(new Date().getTime()).after(new Timestamp(cal2.getTime().getTime()))) {
                modif.setDisable(true);

            } else {

                modif.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        try {
                            FXMLLoader root = new FXMLLoader(getClass().getResource("UpdateWork.fxml"));
                            AnchorPane x = root.load();
                            UpdateWorkController workC = root.getController();
                            V1.getChildren().setAll(x);
                            workC.setWorkshop(w);
                            workC.afficher();

                        } catch (IOException ex) {
                            Logger.getLogger(UpdateWorkController.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                });
            }
            Calendar cal = Calendar.getInstance();

            cal.setTimeInMillis(w.getDateDebut().getTime());
            cal.add(Calendar.HOUR, -72);

            if (new Timestamp(new Date().getTime()).before(new Timestamp(cal.getTime().getTime()))) {
                sup.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {

                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

                        alert.setTitle("Alert!!!!!!!!!!");
                        alert.setHeaderText("Voulez vous supprimé ce workshop?");
                        Optional<ButtonType> res = alert.showAndWait();

                        if (res.get() == ButtonType.OK) {
                            ArrayList<ParticipantWork> l = pws.getWorkshopById(w.getId());
                            l.forEach(e -> pws.delete(e.getId()));
                            ws.delete(w);
                            V1.getChildren().remove(separator);
                            V1.getChildren().remove(cadre);
                            Alert alertSec = new Alert(Alert.AlertType.INFORMATION);
                            alertSec.setHeaderText("L'événement choisi a été supprimé");
                            alertSec.showAndWait();

                        }

                    }

                });
            } else {
                sup.setDisable(true);
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

            listN.add(w.getNomWorkshop());
            TextFields.bindAutoCompletion(recheche, listN);
            recheche.validate();
        }

    }

    @FXML
    private void choix(MouseEvent event) {
        typerecherche.setVisible(true);
    }

    private void Chercher(MouseEvent event) {
        Workshop we = new Workshop();
        V1.getChildren().clear();
        List<Workshop> ls = new ArrayList<>();
        ls = ws.getWorkshopByNom(we.getNomWorkshop());
        for (Workshop w : ls) {
            ws.aff();
            Button participer = new Button("Participer");
            if (w.getNbPlace() <= ws.nbParticipant(w)) {
                participer.setDisable(true);
            }
            if (ws.switchP(w, u.getId())) {
                participer.setText("Ne Plus Participer");
            }

            Button signaler = new Button("Signaler");
            Button modif = new Button("Modifier");
            Button sup = new Button("Supprimer");

            Label nom = new Label();
            nom.setText(w.getNomWorkshop());
            nom.setStyle("-fx-font-weight: bold");
            Label des = new Label();
            des.setText(w.getDescription());

            des.setWrapText(true);

            Label place = new Label();
            place.setText(String.valueOf(w.getNbPlace()));
            Label prix = new Label();
            prix.setText(String.valueOf(w.getPrix()));
            Label deb = new Label();
            deb.setText(w.getDateDebut().toString());
            Label fin = new Label();
            fin.setText(w.getDateFin().toString());
            Label type = new Label();
            type.setText(w.getType());

            Text Des = new Text("Description: ");

            Text add = new Text("Adresse: ");
            add.setStyle("-fx-font-weight: regular");
            Text nb = new Text("Places: ");
            Text ty = new Text("type: ");
            Text p = new Text("Prix: ");
            Text dd = new Text("Début: ");
            Text df = new Text("Fin: ");
            HBox Bot = new HBox();
            VBox h1 = new VBox();
            HBox h2 = new HBox();
            VBox vv = new VBox();
            VBox vvv = new VBox();

            h1.getChildren().add(nom);

            h2.getChildren().add(h1);

            h2.getChildren().add(nb);
            h2.getChildren().add(place);
            h2.getChildren().add(p);
            h2.getChildren().add(prix);

            h2.getChildren().add(dd);
            h2.getChildren().add(deb);
            h2.getChildren().add(df);
            h2.getChildren().add(fin);

            Bot.getChildren().add(participer);
            Bot.getChildren().add(modif);
            Bot.getChildren().add(signaler);
            Bot.getChildren().add(sup);

            vv.getChildren().add(Des);
            vv.getChildren().add(des);
            vvv.getChildren().add(ty);
            vvv.getChildren().add(type);

            cadre.getChildren().add(h1);

            cadre.getChildren().add(h2);
            cadre.getChildren().add(vv);
            cadre.getChildren().add(vvv);
            cadre.getChildren().add(Bot);
            // cadre.getTransforms().addAll(scale);
            V1.getChildren().add(cadre);

            final Separator separator = new Separator();
            separator.setMaxWidth(40);
            h2.setSpacing(20);
            Separator separator2 = new Separator();
            separator2.setOrientation(Orientation.VERTICAL);

            separator2.setLayoutX(20);
            vvv.setSpacing(separator2.getLayoutX());

            V1.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;"
                    + "-fx-border-width: 2;" + "-fx-border-insets: 10;"
                    + "-fx-border-radius: 5;" + "-fx-border-color: orange;" + "-fx-width: 100%;");
            System.out.println("vsss");

            cadre.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;"
                    + "-fx-border-width: 2;" + "-fx-border-insets: 5;"
                    + "-fx-border-radius: 2;" + "-fx-border-color: #00fa9a;" + "-fx-width: 100%;");

            des.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;"
                    + "-fx-border-width: 2;" + "-fx-border-insets: 10;"
                    + "-fx-border-radius: 5;" + "-fx-border-color: orange;");
            des.setMinHeight(Control.USE_COMPUTED_SIZE);

            modif.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        FXMLLoader root = new FXMLLoader(getClass().getResource("UpdateWork.fxml"));
                        AnchorPane x = root.load();
                        UpdateWorkController workC = root.getController();
                        V1.getChildren().setAll(x);
                        workC.setWorkshop(w);
                        workC.afficher();

                    } catch (IOException ex) {
                        Logger.getLogger(UpdateWorkController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            });
        }
    }

    @FXML
    private void affiche1(ActionEvent event) {
        Workshop wee = new Workshop();

        typerecherche.setPromptText("");
        typerecherche.setPromptText("Choisir TYPE");
        typerecherche.getItems().clear();
        List<Workshop> lst = new ArrayList<>();
//        lst = ws.getWorkshopBytype("patisserie Traditionnelle");
//           System.out.println("+++++++++++++"+lst);

        typerecherche.setItems(typeList);
//        for (Workshop w : lst) {
//            typerecherche.getItems().add(w.getType());
//        }

        typerecherche.setOnAction((ActionEvent e) -> {

            System.out.println("azedrgyjiko");
            cadre.getChildren().clear();
            V1.getChildren().clear();

            System.out.println("dfghjkl");
            List<Workshop> ls = new ArrayList<>();

            ls = ws.getWorkshopBytype(this.typerecherche.getValue());
            for (Workshop w : ls) {
                System.out.println("   aaaaaaaaaaaaaff");
                String s = w.getDateDebut().toString().substring(0, 10);
                String s1 = w.getDateFin().toString().substring(0, 10);
                Button participer = new Button("Participer");

                participer.setStyle("-fx-border-style: solid inside;"
                        + "-fx-background-color:grey;" + "-fx-border-color: #07d7aa;" + "-fx-text-fill:#07d7aa;");
                if (w.getIdUser() == u.getId()) {
                    participer.setVisible(false);
                }
                if (w.getNbPlace() <= ws.nbParticipant(w)) {
                    participer.setDisable(true);
                }
                if (ws.switchP(w, u.getId())) {
                    participer.setText("Ne Plus Participer");
                }

                Button modif = new Button("Modifier");
                modif.setStyle("-fx-border-style: solid inside;"
                        + "-fx-background-color:grey;" + "-fx-border-color: #07d7aa;" + "-fx-border-color: #07d7aa;" + "-fx-text-fill:#07d7aa;");
                if (w.getIdUser() != u.getId()) {
                    modif.setVisible(false);
                }
                Button sup = new Button("Supprimer");
                sup.setStyle("-fx-border-style: solid inside;"
                        + "-fx-background-color:#ea3706;" + "-fx-border-color: #07d7aa;" + "-fx-border-color: #07d7aa;" + "-fx-text-fill:#07d7aa;");

                if (w.getIdUser() != u.getId()) {
                    sup.setVisible(false);
                }
                Label nom = new Label();
                nom.setText(w.getNomWorkshop());
                nom.setStyle("-fx-font-weight: bold");
                nom.setFont(javafx.scene.text.Font.font("Arial", 25));

                nom.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        try {
                            FXMLLoader root = new FXMLLoader(getClass().getResource("PageWork.fxml"));
                            AnchorPane x = root.load();
                            PageWorkController workC = root.getController();
                            Anc.getChildren().setAll(x);
                            workC.setWorkshop(w);
                            workC.affichage();

                        } catch (IOException ex) {
                            Logger.getLogger(PageWorkController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                });
                ImageView img1 = new ImageView();
//          img1.setImage(new Image("http://localhost/SoukLemdina/web/uploads/images/"+w.getImage()));
//         System.out.println("http://localhost/SoukLemdina/web/uploads/images/"+w.getImage());
                javafx.scene.image.Image img = new javafx.scene.image.Image("http://localhost/SoukLemdina/web/uploads/images/" + w.getImage(), 400, 250, true, true);
                img1.setImage(img);
                Label des = new Label();
                des.setText(w.getDescription());
                des.setWrapText(true);
                Label place = new Label();
                place.setText(String.valueOf(w.getNbPlace()));
                Label prix = new Label();
                prix.setText(String.valueOf(w.getPrix()));
                Label deb = new Label();
                deb.setText(s);
                Label fin = new Label();
                fin.setText(s1);
                Label type = new Label();
                type.setText(w.getType());

                Text add = new Text("Adresse: ");
                add.setStyle("-fx-font-weight: regular");
                Text nb = new Text("Places: ");
                Text ty = new Text("type: ");
                Text p = new Text("Prix: ");
                Text dd = new Text("Début: ");
                Text df = new Text("Fin: ");
                HBox hh = new HBox();
                VBox cadre = new VBox();
                HBox Bot = new HBox();
                VBox h1 = new VBox();
                HBox h2 = new HBox();
                VBox vv = new VBox();
                VBox vvv = new VBox();
                Bot.setSpacing(35);
                h1.getChildren().add(nom);

                h2.getChildren().add(h1);

                h2.getChildren().add(nb);
                h2.getChildren().add(place);
                h2.getChildren().add(p);
                h2.getChildren().add(prix);

                h2.getChildren().add(dd);
                h2.getChildren().add(deb);
                h2.getChildren().add(df);
                h2.getChildren().add(fin);
                //h2.getChildren().add(img1);

                Separator soo = new Separator(Orientation.VERTICAL);

                hh.getChildren().addAll(h2, soo, img1);
                hh.setAlignment(Pos.TOP_RIGHT);
                Bot.getChildren().addAll(participer, modif, sup);
                Bot.setAlignment(Pos.TOP_LEFT);
                vvv.getChildren().add(ty);
                vvv.getChildren().add(type);
                cadre.getChildren().add(h1);
                cadre.getChildren().add(h2);
                cadre.getChildren().add(vv);
                cadre.getChildren().add(vvv);
                cadre.getChildren().add(hh);

                cadre.getChildren().add(Bot);
                // cadre.getTransforms().addAll(scale);
                V1.getChildren().add(cadre);

                final Separator separator = new Separator();
                separator.setMaxWidth(40);
                h2.setSpacing(20);
                Separator separator2 = new Separator();
                separator2.setOrientation(Orientation.VERTICAL);

                separator2.setLayoutX(20);
                vv.setSpacing(40);

                V1.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;"
                        + "-fx-border-width: 2;" + "-fx-border-insets: 10;"
                        + "-fx-border-radius: 5;" + "-fx-border-color: orange;" + "-fx-width: 100%;");
                System.out.println("vsss");

                cadre.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;"
                        + "-fx-border-width: 2;" + "-fx-border-insets: 5;"
                        + "-fx-border-radius: 2;" + "-fx-border-color: grey;" + "-fx-width: 100%;");

                des.setMinHeight(Control.USE_COMPUTED_SIZE);
                Calendar cal2 = Calendar.getInstance();
                cal2.setTimeInMillis(w.getDateDebut().getTime());

                cal2.add(Calendar.DAY_OF_YEAR, -7);
                System.out.println(new Timestamp(cal2.getTime().getTime()));

                if (new Timestamp(new Date().getTime()).after(new Timestamp(cal2.getTime().getTime()))) {
                    modif.setDisable(true);

                } else {

                    modif.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            try {
                                FXMLLoader root = new FXMLLoader(getClass().getResource("UpdateWork.fxml"));
                                AnchorPane x = root.load();
                                UpdateWorkController workC = root.getController();
                                V1.getChildren().setAll(x);
                                workC.setWorkshop(w);
                                workC.afficher();

                            } catch (IOException ex) {
                                Logger.getLogger(UpdateWorkController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        }
                    });
                }
                Calendar cal = Calendar.getInstance();

                cal.setTimeInMillis(w.getDateDebut().getTime());
                cal.add(Calendar.HOUR, -72);

                if (new Timestamp(new Date().getTime()).before(new Timestamp(cal.getTime().getTime()))) {
                    sup.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {

                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

                            alert.setTitle("Alert!!!!!!!!!!");
                            alert.setHeaderText("Voulez vous supprimé ce workshop?");
                            Optional<ButtonType> res = alert.showAndWait();

                            if (res.get() == ButtonType.OK) {
                                ArrayList<ParticipantWork> l = pws.getWorkshopById(w.getId());
                                l.forEach(e -> pws.delete(e.getId()));
                                ws.delete(w);
                                V1.getChildren().remove(separator);
                                V1.getChildren().remove(cadre);
                                Alert alertSec = new Alert(Alert.AlertType.INFORMATION);
                                alertSec.setHeaderText("Le workshop choisi a été supprimé");
                                alertSec.showAndWait();

                            }

                        }

                    });
                } else {
                    sup.setDisable(true);
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
        });
    }
//      @FXML
//    void goGo(ActionEvent e) throws IOException{
//          
//                 
//                  FXMLLoader loader = new FXMLLoader(getClass().getResource("AjoutWorkshop.fxml"));
//                  Parent  homePage = loader.load();
//                        
//        Scene homePage_scene = new Scene(homePage);
//
//        Stage app_stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
//
//        app_stage.setScene(homePage_scene);
//
//        app_stage.show();
//     
//                        
//                       
//    }

    @FXML
    void goToAj(ActionEvent e) throws IOException {

        AnchorPane l = FXMLLoader.load(getClass().getResource("AjoutWorkshop.fxml"));
        this.Anc.getChildren().setAll(l);

    }

}
