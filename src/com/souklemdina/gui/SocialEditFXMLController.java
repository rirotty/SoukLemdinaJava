/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.souklemdina.gui;

import com.jfoenix.controls.JFXButton;
import com.souklemdina.entities.FosUser;
import com.souklemdina.entities.Profile;
import com.souklemdina.services.ProfileServices;
import com.souklemdina.util.SessionUser;
import com.souklemdina.util.UploadFile;
import java.io.File;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

/**
 * FXML Controller class
 *
 * @author ramyk
 */
public class SocialEditFXMLController implements Initializable {

    private String newPhotoName = null;
    @FXML
    private TextArea txarAbout;
    @FXML
    private TextArea txarTag;
    @FXML
    private Label labAbout;
    @FXML
    private Label labTag;
    @FXML
    private JFXButton btn_browse;
    @FXML
    private JFXButton btn_erase;
    @FXML
    private JFXButton btn_reinit;
    @FXML
    private Circle cir;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        FosUser user = SessionUser.getUser();
        ProfileServices ps = new ProfileServices();
        Profile p = ps.findByIdUser(user.getId());
        if (p.getImage() == null) {
            Image img = new Image("http://localhost/SoukLemdina/web/Template/images/icons/avatar.jpg");
//            img_preview.setImage(img);
            cir.setFill(new ImagePattern(img));
        } else {
            Image img = new Image("http://localhost/SoukLemdina/web/uploads/images/" + p.getImage());
//            img_preview.setImage(img);
            cir.setFill(new ImagePattern(img));
        }
        if (p.getTagline() != null) {
            txarTag.setText(p.getTagline());
        }
        if (p.getAboutMe() != null) {
            txarAbout.setText(p.getAboutMe());
        }

        //validators (example for textarea)
//        txarTag.focusedProperty().addListener((obs, oldVal, newVal)
//                -> System.out.println(newVal ? "Focused" : "Unfocused"));
        txarTag.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) { // when focus lost
                if (!txarTag.getText().matches("^.{0,255}$")) {
                    // when it not matches the pattern
                    // wrong validation logic goes here
                    txarTag.setStyle("-fx-background-color:orangered;");
                    if ("Slogan du profile".equals(labTag.getText())) {
                        labTag.setText(labTag.getText() + " (> 255 caracters)");
                    }
                } else {
                    txarTag.setStyle("-fx-background-color:grey;");
                    labTag.setText("Slogan du profile");
                }
            }
        });

        txarAbout.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) { // when focus lost
                if (!txarAbout.getText().matches("^.{0,255}$")) {
                    // when it not matches the pattern
                    // wrong validation logic goes here
                    txarAbout.setStyle("-fx-background-color:orangered;");
                    if ("Parlez-nous de vous".equals(labAbout.getText())) {
                        labAbout.setText(labAbout.getText() + " (> 255 caracters)");
                    }
                } else {
                    txarAbout.setStyle("-fx-background-color:grey;");
                    labAbout.setText("Parlez-nous de vous");
                }
            }
        });

    }

    @FXML
    private void handleBrowseBtn(ActionEvent event) {
        try {
            //Here User Connected Session Getting Logic
            FosUser user = SessionUser.getUser();
            ProfileServices ps = new ProfileServices();
            Profile p = ps.findByIdUser(user.getId());
//            ProcessBuilder pb = new ProcessBuilder("ant -f /mnt/Data/Studies/ESPRIT/2ndSem/PiDev/JavaSprint/SoukLemdinaJava/ImageCropper jfxsa-run");
            Process pr = Runtime.getRuntime().exec("ant -f /mnt/Data/Studies/ESPRIT/2ndSem/PiDev/JavaSprint/SoukLemdinaJava/ImageCropper jfxsa-run");
//            p = pb.start();     // Start the process.
            pr.waitFor();                // Wait for the process to finish.
//            System.out.println("CropScript executed successfully");
            File f = new File(System.getProperty("user.home") + "/Desktop/" + System.getProperty("user.name") + ".jpg");
//            System.out.println(f.getAbsolutePath());
            this.newPhotoName = UploadFile.uploadImage(f.getAbsolutePath(),p.getImage());
            f.delete();
//            System.out.println(f.delete());
//            System.out.println(newPhotoName);
            p.setImage(newPhotoName);
            p.setUpdatedAt(new Timestamp(new Date().getTime()));
            Image img = new Image("http://localhost/SoukLemdina/web/uploads/images/" + newPhotoName);
//            img_preview.setImage(img);
            cir.setFill(new ImagePattern(img));
            ps.update(p);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @FXML
    private void handleEraseBtn(ActionEvent event) {
        //Here User Connected Session Getting Logic
        FosUser user = SessionUser.getUser();
        ProfileServices ps = new ProfileServices();
        Profile p = ps.findByIdUser(user.getId());
        this.newPhotoName = null;
        p.setImage(newPhotoName);
        p.setUpdatedAt(new Timestamp(new Date().getTime()));
        Image img = new Image("http://localhost/SoukLemdina/web/Template/images/icons/avatar.jpg");
//        img_preview.setImage(img);
        cir.setFill(new ImagePattern(img));
        ps.update(p);
    }

    @FXML
    private void handleReinitBtn(ActionEvent event) {
        this.txarTag.setText("");
        this.txarAbout.setText("");
    }

    @FXML
    private void handleSaveBtn(ActionEvent event) {
        //Here User Connected Session Getting Logic
        FosUser user = SessionUser.getUser();
        ProfileServices ps = new ProfileServices();
        Profile p = ps.findByIdUser(user.getId());
        if (txarTag.getText().matches("^.{0,255}$") && txarAbout.getText().matches("^.{0,255}$")) {
            p.setTagline(this.txarTag.getText());
            p.setAboutMe(this.txarAbout.getText());
            ps.update(p);
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Erreur d'entrée");
            alert.setHeaderText(null);
            alert.setContentText("Le slogan et la section \"AboutMe\" du profile ne doivent pas dépasser les 255 caractères.");
            alert.showAndWait();
        }
    }

}
