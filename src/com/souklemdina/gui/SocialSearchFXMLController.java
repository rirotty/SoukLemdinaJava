/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.souklemdina.gui;

import com.jfoenix.controls.JFXButton;
import com.souklemdina.entities.FosUser;
import com.souklemdina.entities.Profile;
import com.souklemdina.services.PostServices;
import com.souklemdina.services.ProfileServices;
import com.souklemdina.util.SessionUser;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
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
public class SocialSearchFXMLController implements Initializable {

    private final ProfileServices ps = new ProfileServices();
    private final PostServices pss = new PostServices();
    private FosUser user;
    private Profile p;
    @FXML
    private VBox pane_vbox;
    @FXML
    private TextField txf_seek;
    @FXML
    private JFXButton btn_seek;
    @FXML
    private AnchorPane pane;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.user = SessionUser.getUser();
        this.p = this.ps.findByIdUser(this.user.getId());
//        this.showContent("");
        this.txf_seek.setOnAction((event) -> {
            showContent(this.txf_seek.getText());
        });
        this.btn_seek.setOnAction((event) -> {
            showContent(this.txf_seek.getText());
        });
    }

    private void showContent(String seeken) {
        List<FosUser> foundUsers = new ArrayList<>();
        List<Profile> foundProfiles = new ArrayList<>();
        if (seeken.equals("")) {
            foundProfiles = ps.findAll();
        } else {
            foundUsers = ps.findByLike(seeken);
            if (foundUsers != null) {
                for (FosUser foundUser : foundUsers) {
                    foundProfiles.add(this.ps.findByIdUser(foundUser.getId()));
                }
            }
        }
        this.pane_vbox.setAlignment(Pos.CENTER);
        this.pane_vbox.setSpacing(30);

        List<AnchorPane> keepList = new ArrayList<>();
        keepList.add(this.pane);
        this.pane_vbox.getChildren().retainAll(keepList);
        if (!foundProfiles.isEmpty()) {
            foundProfiles.stream()
                    .filter(e -> !Objects.equals(e.getIdUser(), this.user.getId()))
                    .forEach(e -> {
                        Circle cir_profile = new Circle();
                        cir_profile.setVisible(true);
                        cir_profile.setRadius(60);
                        cir_profile.setStroke(Color.web("#b3b3b3"));
                        cir_profile.setStrokeWidth(1.5);
                        if (e.getImage() == null) {
                            Image img = new Image("http://localhost/SoukLemdina/web/Template/images/icons/avatar.jpg");
                            cir_profile.setFill(new ImagePattern(img));
                        } else {
                            Image img = new Image("http://localhost/SoukLemdina/web/uploads/images/" + e.getImage());
                            cir_profile.setFill(new ImagePattern(img));
                        }
                        HBox HorizontalContainer = new HBox();
                        HorizontalContainer.setSpacing(20);
                        HorizontalContainer.setMaxWidth(this.pane_vbox.getWidth() - 200);
                        VBox titTxt = new VBox();
                        titTxt.setSpacing(30);
                        Label tit = new Label(ps.findFOSById(e.getIdUser()).getFirstname() + " " + ps.findFOSById(e.getIdUser()).getLastname());
                        tit.setStyle("-fx-font-weight: bold;");
                        tit.setTextFill(Color.web("#ffffff"));
                        tit.setMaxWidth(400);
                        tit.setWrapText(true);
                        Label txt = new Label(e.getTagline());
                        txt.setTextFill(Color.web("#ffffff"));
                        txt.setMaxWidth(250);
                        txt.setWrapText(true);

                        VBox btnsPost = new VBox();
                        btnsPost.setSpacing(10);
                        btnsPost.setAlignment(Pos.CENTER);

                        JFXButton btn_follow = new JFXButton();
                        btn_follow.setStyle("-fx-background-color: #40a84a");
                        btn_follow.setMinWidth(100);
                        btn_follow.setTextFill(Color.valueOf("#e3e3e3"));
                        if (this.ps.isFollowing(this.p, e)) {
                            btn_follow.setText("Ne Plus Suivre");
                        } else {
                            btn_follow.setText("Suivre");
                        }
                        btn_follow.setOnAction((event) -> {
                            if (this.ps.isFollowing(this.p, e)) {
                                this.ps.unFollow(this.p, e);
                                btn_follow.setText("Suivre");
                            } else {
                                this.ps.follow(this.p, e);
                                btn_follow.setText("Ne Plus Suivre");
                            }
                        });
                        btn_follow.setWrapText(true);

                        JFXButton btn_check = new JFXButton();
                        btn_check.setStyle("-fx-background-color: #40a84a");
                        btn_check.setMinWidth(100);
                        btn_check.setMaxWidth(200);
                        btn_check.setTextFill(Color.valueOf("#e3e3e3"));
                        btn_check.setText("Consulter");
                        btn_check.setOnAction((event) -> {
                            this.pane_vbox.getChildren().clear();
                            this.pane_vbox.setAlignment(Pos.CENTER);
                            HBox btnPrec = new HBox();
                            btnPrec.setAlignment(Pos.BOTTOM_LEFT);
                            JFXButton btnP = new JFXButton();
                            btnPrec.setAlignment(Pos.CENTER);
                            btnPrec.setSpacing(10);
                            btnPrec.getChildren().add(btnP);
                            btnPrec.getChildren().add(btn_follow);
                            btnP.setStyle("-fx-background-color: #40a84a");
                            btnP.setMinWidth(100);
                            btnP.setMaxWidth(200);
                            btnP.setTextFill(Color.valueOf("#e3e3e3"));
                            btnP.setText("Prec");
                            btnP.setOnAction((ev) -> {
                                this.pane_vbox.getChildren().clear();
                                this.pane_vbox.getChildren().add(this.pane);
                                this.showContent(seeken);
                            });
                            Label lab = new Label();
                            Label lab2 = new Label();
                            Label name = new Label(ps.findFOSById(e.getIdUser()).getFirstname() + " " + ps.findFOSById(e.getIdUser()).getLastname());
                            Label tag = new Label(e.getTagline());
                            tag.setWrapText(true);
                            Circle cir = new Circle();
                            cir.setVisible(true);
                            cir.setRadius(60);
                            cir.setStroke(Color.web("#b3b3b3"));
                            cir.setStrokeWidth(1.5);
                            if (e.getImage() == null) {
                                Image img = new Image("http://localhost/SoukLemdina/web/Template/images/icons/avatar.jpg");
                                cir.setFill(new ImagePattern(img));
                            } else {
                                Image img = new Image("http://localhost/SoukLemdina/web/uploads/images/" + e.getImage());
                                cir.setFill(new ImagePattern(img));
                            }

                            this.pane_vbox.getChildren().add(lab2);
                            this.pane_vbox.getChildren().add(cir);
                            this.pane_vbox.getChildren().add(name);
                            this.pane_vbox.getChildren().add(tag);
                            this.pane_vbox.getChildren().add(btnPrec);
                            this.pane_vbox.getChildren().add(lab);
                            Label lb = new Label();
                            if (this.pss.findByIdUser(e.getIdUser()) == null) {
                                lb.setText("Encore pas de publications");
                                this.pane_vbox.setAlignment(Pos.CENTER);
                                this.pane_vbox.getChildren().add(lb);
                            } else {
                                this.pss.findByIdUser(e.getIdUser())
                                        .stream()
                                        .forEach(pe -> {
                                            HBox PostsContainer = new HBox();
                                            PostsContainer.setSpacing(20);
                                            PostsContainer.setMaxWidth(this.pane_vbox.getWidth() - 200);
                                            ImageView img_post = new ImageView();
                                            img_post.setFitWidth(180);
                                            img_post.setFitHeight(180);
                                            img_post.setImage(new Image("http://localhost/SoukLemdina/web/uploads/images/" + pe.getImage()));
                                            VBox titTxt2 = new VBox();
                                            titTxt2.setSpacing(30);
                                            Label tit2 = new Label(pe.getTitre());
                                            tit2.setStyle("-fx-font-weight: bold;");
                                            tit2.setTextFill(Color.web("#ffffff"));
                                            tit2.setMinWidth(200);
                                            tit2.setWrapText(true);
                                            Label txt2 = new Label(pe.getTexte());
                                            txt2.setTextFill(Color.web("#ffffff"));
                                            txt2.setMinWidth(200);
                                            txt2.setWrapText(true);
                                            titTxt2.getChildren().add(tit2);
                                            titTxt2.getChildren().add(txt2);
                                            Label dat = new Label(new Date(pe.getDate().getTime()).toString());
                                            titTxt2.getChildren().add(dat);
                                            JFXButton btn_signal = new JFXButton("Signaler");
                                            btn_signal.setStyle("-fx-background-color: #40a84a");
                                            btn_signal.setMinWidth(100);
                                            btn_signal.setMaxWidth(200);
                                            btn_signal.setTextFill(Color.valueOf("#e3e3e3"));
                                            btn_signal.setOnAction((ev) -> {
                                                btn_signal.setDisable(true);
                                                this.pss.signaler(pe);
                                            });
                                            PostsContainer.getChildren().add(btn_signal);
                                            PostsContainer.getChildren().add(img_post);
                                            PostsContainer.getChildren().add(titTxt2);
                                            PostsContainer.setAlignment(Pos.CENTER);
                                            this.pane_vbox.getChildren().add(PostsContainer);
                                        });
                            }
                        });
                        btn_check.setWrapText(true);

                        btnsPost.getChildren().add(btn_follow);
                        btnsPost.getChildren().add(btn_check);

                        titTxt.getChildren().add(tit);
                        titTxt.getChildren().add(txt);
                        HorizontalContainer.getChildren().add(btnsPost);
                        HorizontalContainer.getChildren().add(cir_profile);
                        HorizontalContainer.getChildren().add(titTxt);
                        HorizontalContainer.setAlignment(Pos.CENTER_LEFT);
                        this.pane_vbox.getChildren().add(HorizontalContainer);
                    });
        }
    }

}
