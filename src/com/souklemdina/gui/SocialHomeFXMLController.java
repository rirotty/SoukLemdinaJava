/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.souklemdina.gui;

import com.jfoenix.controls.JFXButton;
import com.souklemdina.entities.FosUser;
import com.souklemdina.entities.Post;
import com.souklemdina.entities.Profile;
import com.souklemdina.services.PostServices;
import com.souklemdina.services.ProfileServices;
import com.souklemdina.util.SessionUser;
import com.souklemdina.util.UploadFile;
import java.io.File;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

/**
 * FXML Controller class
 *
 * @author rkader
 */
public class SocialHomeFXMLController implements Initializable {

    private final ProfileServices ps = new ProfileServices();
    private final PostServices pss = new PostServices();
    private FosUser user;
    private Profile p;
    private List<Post> posts;
    private Timestamp newUpdated;
    private String postPhoto;
    @FXML
    private AnchorPane pane;
    @FXML
    private JFXButton btn_browse;
    @FXML
    private TextField txfTitre;
    @FXML
    private TextArea txarTexte;
    @FXML
    private JFXButton btn_save;
    @FXML
    private ImageView img_preview;
    @FXML
    private Label lab_titre;
    @FXML
    private Label lab_texte;
    @FXML
    private VBox pane_vbox;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Here User Connected Session Getting Logic
        this.user = SessionUser.getUser();
        this.p = ps.findByIdUser(this.user.getId());
        this.postPhoto = null;

        this.showContent(false);

        txfTitre.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) { // when focus lost
                if (!txfTitre.getText().matches("^.{1,50}$")) {
                    // when it not matches the pattern
                    // wrong validation logic goes here
                    txfTitre.setStyle("-fx-background-color:orangered;");
                    if ("Titre de la publication".equals(lab_titre.getText())) {
                        lab_titre.setText(lab_titre.getText() + " (> 50 caracters)");
                    }
                } else {
                    txfTitre.setStyle("-fx-background-color:white;");
                    lab_titre.setText("Titre de la publication");
                }
            }
        });

        txarTexte.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) { // when focus lost
                if (!txarTexte.getText().matches("^.{1,255}$")) {
                    // when it not matches the pattern
                    // wrong validation logic goes here
                    txarTexte.setStyle("-fx-background-color:orangered;");
                    if ("Ecrire ici".equals(lab_texte.getText())) {
                        lab_texte.setText(lab_texte.getText() + " (> 255 caracters)");
                    }
                } else {
                    txarTexte.setStyle("-fx-background-color:grey;");
                    lab_texte.setText("Ecrire ici");
                }
            }
        });
    }

    @FXML
    private void handleBrowseBtn(ActionEvent event) {
        try {
//            ProcessBuilder pb = new ProcessBuilder("ant -f /mnt/Data/Studies/ESPRIT/2ndSem/PiDev/JavaSprint/SoukLemdinaJava/ImageCropper jfxsa-run");
            Process pr = Runtime.getRuntime().exec("ant -f /mnt/Data/Studies/ESPRIT/2ndSem/PiDev/JavaSprint/SoukLemdinaJava/ImageCropper jfxsa-run");
//            p = pb.start();     // Start the process.
            pr.waitFor();                // Wait for the process to finish.
//            System.out.println("CropScript executed successfully");
            File f = new File(System.getProperty("user.home") + "/Desktop/" + System.getProperty("user.name") + ".jpg");
//            System.out.println(f.getAbsolutePath());
            this.postPhoto = UploadFile.uploadImage(f.getAbsolutePath(), this.postPhoto);
            f.delete();
            Image im = new Image("http://localhost/SoukLemdina/web/uploads/images/" + postPhoto);
            System.out.println(postPhoto);
            img_preview.setImage(im);
            this.newUpdated = new Timestamp(new Date().getTime());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void handleSaveBtn() {
        //Here User Connected Session Getting Logic
        Post p = null;
        if (txfTitre.getText().matches("^.{1,50}$") && txarTexte.getText().matches("^.{1,255}$") && postPhoto != null) {
            p = new Post(this.newUpdated, txarTexte.getText(), txfTitre.getText(), 0, postPhoto, this.newUpdated, user.getId());
            pss.create(p);
            txfTitre.clear();
            txarTexte.clear();
            postPhoto = null;
            img_preview.setImage(new Image("http://localhost/SoukLemdina/web/uploads/images/noSelected.png"));
            this.showContent(false);
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Erreur d'entrée");
            alert.setHeaderText(null);
            alert.setContentText("Informations manquantes!");
            alert.showAndWait();
        }
    }

    private void handleUpdateBtn(Integer id) {
        //Here User Connected Session Getting Logic
        this.user = SessionUser.getUser();
        Post p = pss.findById(id);
        if (txfTitre.getText().matches("^.{1,50}$") && txarTexte.getText().matches("^.{1,255}$") && postPhoto != null) {
            p.setImage(postPhoto);
            p.setTexte(txarTexte.getText());
            p.setTitre(txfTitre.getText());
            pss.update(p);
            txfTitre.clear();
            txarTexte.clear();
            postPhoto = null;
            img_preview.setImage(new Image("http://localhost/SoukLemdina/web/uploads/images/noSelected.png"));
            this.showContent(false);
            this.btn_save.setText("Save");
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Erreur d'entrée");
            alert.setHeaderText(null);
            alert.setContentText("Informations manquantes!");
            alert.showAndWait();
        }
    }

    private void showContent(Boolean cancelbtn) {
        this.posts = null;
        this.posts = pss.findAllFollowed(this.p.getId(), this.p.getIdUser());
        this.pane_vbox.setAlignment(Pos.CENTER);
        this.pane_vbox.setSpacing(30);

        List<AnchorPane> keepList = new ArrayList<>();
        keepList.add(this.pane);
        this.pane_vbox.getChildren().retainAll(keepList);

        JFXButton btn_cancel = new JFXButton();
        btn_cancel.setVisible(cancelbtn);
        btn_cancel.setStyle("-fx-background-color: #40a84a");
        btn_cancel.setMinWidth(100);
        btn_cancel.setText("Annuler");
        btn_cancel.setTextFill(Color.valueOf("#e3e3e3"));
        btn_cancel.setWrapText(true);
        btn_cancel.setOnAction((event) -> {
            this.txfTitre.setText("");
            this.txarTexte.setText("");
            this.img_preview.setImage(new Image("http://localhost/SoukLemdina/web/uploads/images/noSelected.png"));
            this.postPhoto = null;
            this.btn_save.setText("Save");
            this.btn_save.setOnAction((ev) -> {
                this.handleSaveBtn();
            });
            this.showContent(false);
        });
        if (cancelbtn) {
            this.pane_vbox.getChildren().add(btn_cancel);
        }

        posts.stream().forEach(e -> {
            Profile userProfile = ps.findByIdUser(e.getIdUser());
            Circle cir_profile = new Circle();
            cir_profile.setVisible(true);
            cir_profile.setRadius(60);
            cir_profile.setStroke(Color.web("#b3b3b3"));
            cir_profile.setStrokeWidth(1.5);
            if (userProfile.getImage() == null) {
                Image img = new Image("http://localhost/SoukLemdina/web/Template/images/icons/avatar.jpg");
//            img_preview.setImage(img);
                cir_profile.setFill(new ImagePattern(img));
            } else {
                Image img = new Image("http://localhost/SoukLemdina/web/uploads/images/" + userProfile.getImage());
//            img_preview.setImage(img);
                cir_profile.setFill(new ImagePattern(img));
            }

            HBox HorizontalContainer = new HBox();
            HorizontalContainer.setSpacing(20);
            HorizontalContainer.setMaxWidth(this.pane_vbox.getWidth() - 200);
            ImageView img_post = new ImageView();
            img_post.setFitWidth(180);
            img_post.setFitHeight(180);
            img_post.setImage(new Image("http://localhost/SoukLemdina/web/uploads/images/" + e.getImage()));
            VBox titTxt = new VBox();
            titTxt.setSpacing(30);
            Label tit = new Label(e.getTitre());
            tit.setStyle("-fx-font-weight: bold;");
            tit.setTextFill(Color.web("#ffffff"));
            tit.setMinWidth(200);
            tit.setWrapText(true);
            Label txt = new Label(e.getTexte());
            txt.setTextFill(Color.web("#ffffff"));
            txt.setMinWidth(200);
            txt.setWrapText(true);
            HBox btnsPost = new HBox();
            btnsPost.setSpacing(10);
            btnsPost.setAlignment(Pos.BOTTOM_LEFT);
            if (this.p.getIdUser() == e.getIdUser()) {
                JFXButton btn_delete = new JFXButton();
                btn_delete.setStyle("-fx-background-color: #40a84a");
                btn_delete.setMinWidth(100);
                btn_delete.setText("Effacer Post");
                btn_delete.setTextFill(Color.valueOf("#e3e3e3"));
                btn_delete.setWrapText(true);
                btn_delete.setOnAction((event) -> {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirmation");
                    alert.setHeaderText(null);
                    alert.setContentText("Voulez-vous vraiment supprimer cette publication?");
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK) {
                        pss.deleteById(e.getId());
                        System.out.println(e.getId());
                        this.showContent(false);
                    }
                });

                JFXButton btn_modify = new JFXButton();
                btn_modify.setStyle("-fx-background-color: #40a84a");
                btn_modify.setMinWidth(100);
                btn_modify.setText("Modifier");
                btn_modify.setTextFill(Color.valueOf("#e3e3e3"));
                btn_modify.setWrapText(true);
                btn_modify.setOnAction((event) -> {
                    this.txfTitre.setText(e.getTitre());
                    this.txarTexte.setText(e.getTexte());
                    this.img_preview.setImage(new Image("http://localhost/SoukLemdina/web/uploads/images/" + e.getImage()));
                    this.postPhoto = e.getImage();
                    this.btn_save.setText("Modifier");
                    this.btn_save.setOnAction((ev) -> {
                        this.handleUpdateBtn(e.getId());
                    });
                    this.showContent(true);
                });

                btnsPost.getChildren().add(btn_modify);
                btnsPost.getChildren().add(btn_delete);
            } else {
                JFXButton btn_signal = new JFXButton("Signaler");
                btn_signal.setStyle("-fx-background-color: #40a84a");
                btn_signal.setMinWidth(100);
                btn_signal.setMaxWidth(200);
                btn_signal.setTextFill(Color.valueOf("#e3e3e3"));
                btn_signal.setOnAction((ev) -> {
                    btn_signal.setDisable(true);
                    this.pss.signaler(e);
                });
                btnsPost.getChildren().add(btn_signal);
            }
            Label dat = new Label(new Date(e.getDate().getTime()).toString());
            dat.setTextFill(Color.web("#ffffff"));
            titTxt.getChildren().add(tit);
            titTxt.getChildren().add(dat);
            titTxt.getChildren().add(txt);
            titTxt.getChildren().add(btnsPost);
            HorizontalContainer.getChildren().add(cir_profile);
            HorizontalContainer.getChildren().add(img_post);
            HorizontalContainer.getChildren().add(titTxt);
            HorizontalContainer.setAlignment(Pos.CENTER);
            Label lab1 = new Label();
            this.pane_vbox.getChildren().add(HorizontalContainer);
            this.pane_vbox.getChildren().add(lab1);
        });
    }

}
