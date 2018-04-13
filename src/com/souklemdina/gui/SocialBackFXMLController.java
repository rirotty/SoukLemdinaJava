/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.souklemdina.gui;

import com.jfoenix.controls.JFXButton;
import com.souklemdina.entities.Profile;
import com.souklemdina.services.PostServices;
import com.souklemdina.services.ProfileServices;
import java.net.URL;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

/**
 * FXML Controller class
 *
 * @author rkader
 */
public class SocialBackFXMLController implements Initializable {

    private final ProfileServices ps = new ProfileServices();
    private final PostServices pss = new PostServices();
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
        this.showContent();
    }

    public void showContent() {
        this.pss.getSignal().stream()
                .forEach(e -> {
                    Profile userProfile = ps.findByIdUser(e.getIdUser());
                    Circle cir_profile = new Circle();
                    cir_profile.setVisible(true);
                    cir_profile.setRadius(40);
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
                    img_post.setFitWidth(100);
                    img_post.setFitHeight(100);
                    img_post.setImage(new Image("http://localhost/SoukLemdina/web/uploads/images/" + e.getImage()));
                    VBox titTxt = new VBox();
                    titTxt.setSpacing(30);
                    Label tit = new Label(e.getTitre());
                    tit.setStyle("-fx-font-weight: bold;");
                    tit.setTextFill(Color.web("#ffffff"));
                    tit.setMinWidth(200);
                    tit.setWrapText(true);
                    HBox btnsPost = new HBox();
                    btnsPost.setSpacing(10);
                    btnsPost.setAlignment(Pos.BOTTOM_LEFT);
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
                            this.pane_vbox.getChildren().clear();
                            this.pane_vbox.setAlignment(Pos.BASELINE_LEFT);
                            this.showContent();
                        }
                    });

                    JFXButton btn_reinit = new JFXButton();
                    btn_reinit.setStyle("-fx-background-color: #40a84a");
                    btn_reinit.setMinWidth(100);
                    btn_reinit.setText("Réinit");
                    btn_reinit.setTextFill(Color.valueOf("#e3e3e3"));
                    btn_reinit.setWrapText(true);
                    btn_reinit.setOnAction((event) -> {
                        this.pss.unSignaler(e);
                        this.pane_vbox.getChildren().clear();
                        this.pane_vbox.setAlignment(Pos.BASELINE_LEFT);
                        this.showContent();
                    });

                    btnsPost.getChildren().add(btn_reinit);
                    btnsPost.getChildren().add(btn_delete);

                    Label dat = new Label(new Date(e.getDate().getTime()).toString());
                    dat.setTextFill(Color.web("#ffffff"));
                    Label nbs;
                    if (e.getNbSignal()!=null)
                        nbs = new Label("Nombre de Signals: " + e.getNbSignal().toString());
                    else
                        nbs = new Label("Nombre de Signals: " + "0");
                    dat.setTextFill(Color.web("#ffffff"));
                    titTxt.getChildren().add(tit);
                    titTxt.getChildren().add(dat);
                    titTxt.getChildren().add(nbs);
                    titTxt.getChildren().add(btnsPost);
                    HorizontalContainer.getChildren().add(cir_profile);
                    HorizontalContainer.getChildren().add(img_post);
                    HorizontalContainer.getChildren().add(titTxt);
                    HorizontalContainer.setAlignment(Pos.CENTER);
                    Label lab1 = new Label();
                    Label lab2 = new Label();
                    this.pane_vbox.setAlignment(Pos.BASELINE_LEFT);
                    this.pane_vbox.getChildren().add(lab1);
                    this.pane_vbox.getChildren().add(HorizontalContainer);
                    this.pane_vbox.getChildren().add(lab2);
                });
    }
}
