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
import com.souklemdina.util.DataSource;
import com.souklemdina.util.SessionUser;
import com.souklemdina.util.UploadFile;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author rkader
 */
public class SocialAddFXMLController implements Initializable {

    private String newPhotoName = null;
    @FXML
    private TextArea txarTag;
    @FXML
    private TextArea txarAbout;
    @FXML
    private JFXButton btn_browse;
    @FXML
    private JFXButton btn_reinit;
    @FXML
    private JFXButton btn_save;
    @FXML
    private Circle img;
    @FXML
    private Label labTag;
    @FXML
    private Label labAbout;
    @FXML
    private DatePicker daten;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image im = new Image("http://localhost/SoukLemdina/web/Template/images/icons/avatar.jpg");
        img.setFill(new ImagePattern(im));
        txarTag.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) { // when focus lost
                if (!txarTag.getText().matches("^.{0,255}$")) {
                    // when it not matches the pattern
                    // wrong validation logic goes here
                    txarTag.setStyle("-fx-background-color:orangered;");
                    if ("Créer un slogan pour votre profile".equals(labTag.getText())) {
                        labTag.setText(labTag.getText() + " (> 255 caracters)");
                    }
                } else {
                    txarTag.setStyle("-fx-background-color:grey;");
                    labTag.setText("Créer un slogan pour votre profile");
                }
            }
        });

        txarAbout.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) { // when focus lost
                if (!txarAbout.getText().matches("^.{0,255}$")) {
                    // when it not matches the pattern
                    // wrong validation logic goes here
                    txarAbout.setStyle("-fx-background-color:orangered;");
                    if ("Parlez-nous de vous en quelques mots".equals(labAbout.getText())) {
                        labAbout.setText(labAbout.getText() + " (> 255 caracters)");
                    }
                } else {
                    txarAbout.setStyle("-fx-background-color:grey;");
                    labAbout.setText("Parlez-nous de vous en quelques mots");
                }
            }
        });
    }

    @FXML
    private void handleBrowseButton(ActionEvent event) {
        try {
//            ProcessBuilder pb = new ProcessBuilder("ant -f /mnt/Data/Studies/ESPRIT/2ndSem/PiDev/JavaSprint/SoukLemdinaJava/ImageCropper jfxsa-run");
            Process pr = Runtime.getRuntime().exec("ant -f /mnt/Data/Studies/ESPRIT/2ndSem/PiDev/JavaSprint/SoukLemdinaJava/ImageCropper jfxsa-run");
//            p = pb.start();     // Start the process.
            pr.waitFor();                // Wait for the process to finish.
//            System.out.println("CropScript executed successfully");
            File f = new File(System.getProperty("user.home") + "/Desktop/" + System.getProperty("user.name") + ".jpg");
//            System.out.println(f.getAbsolutePath());
            if (txarTag.getText().matches("^.{0,255}$") && txarAbout.getText().matches("^.{0,255}$")) {
                //Here User Connected Session Getting Logic
                FosUser user = new FosUser(3);
                ProfileServices ps = new ProfileServices();
                Profile p = ps.findByIdUser(user.getId());
                if (p == null) {
                    p = new Profile(this.txarTag.getText(), this.txarAbout.getText(), user.getId());
                    this.newPhotoName = UploadFile.uploadImage(f.getAbsolutePath(), p.getImage());
                    f.delete();
//                    System.out.println(f.delete());
//                    System.out.println(newPhotoName);
                    Image im = new Image("http://localhost/SoukLemdina/web/uploads/images/" + newPhotoName);
                    img.setFill(new ImagePattern(im));
                    p.setImage(newPhotoName);
                    p.setUpdatedAt(new Timestamp(new Date().getTime()));
                    ps.create(p);
                } else {
                    this.newPhotoName = UploadFile.uploadImage(f.getAbsolutePath(), p.getImage());
                    f.delete();
                    Image im = new Image("http://localhost/SoukLemdina/web/uploads/images/" + newPhotoName);
                    img.setFill(new ImagePattern(im));
                    p.setImage(newPhotoName);
                    p.setUpdatedAt(new Timestamp(new Date().getTime()));
                    ps.update(p);
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Erreur d'entrée");
                alert.setHeaderText(null);
                alert.setContentText("Le slogan et la section \"AboutMe\" du profile ne doivent pas dépasser les 255 caractères.");
                alert.showAndWait();
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @FXML
    private void handleReinitButton(ActionEvent event) {
        this.txarTag.setText("");
        this.txarAbout.setText("");
    }

    @FXML
    private void handleSaveButton(ActionEvent event) throws IOException {
        //Here User Connected Session Getting Logic
        FosUser user = SessionUser.getUser();
        ProfileServices ps = new ProfileServices();
        Profile p = ps.findByIdUser(user.getId());
        if (txarTag.getText().matches("^.{0,255}$") && txarAbout.getText().matches("^.{0,255}$")) {
            if (p == null) {
                p = new Profile(this.txarTag.getText(), this.txarAbout.getText(), user.getId());
                p.setUpdatedAt(new Timestamp(new Date().getTime()));
                ps.create(p);
                String sql = "UPDATE fos_user SET datenaiss=? WHERE id=?";
                PreparedStatement pst;
                int rowsUpdated = 0;
                try {
                    pst = DataSource.getInstance().getConnection().prepareStatement(sql);
                    pst.setTimestamp(1, new Timestamp(this.daten.getValue().getYear() - 1900, this.daten.getValue().getMonthValue() - 1, this.daten.getValue().getDayOfMonth(), 0, 0, 0, 0));
                    pst.setInt(2, p.getIdUser());
                    rowsUpdated = pst.executeUpdate();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }

                if (rowsUpdated == 1) {
                    System.out.println("- The date was updated successfully!");
                } else {
                    System.err.println("ERROR: rowsUpdatedCount isn't normal -- " + rowsUpdated);
                }
            } else {
                p.setAboutMe(this.txarAbout.getText());
                p.setTagline(this.txarTag.getText());
                p.setIdUser(user.getId());
                ps.update(p);
                String sql = "UPDATE fos_user SET datenaiss=? WHERE id=?";
                PreparedStatement pst;
                int rowsUpdated = 0;
                try {
                    pst = DataSource.getInstance().getConnection().prepareStatement(sql);
                    pst.setTimestamp(1, new Timestamp(this.daten.getValue().getYear() - 1900, this.daten.getValue().getMonthValue() - 1, this.daten.getValue().getDayOfMonth(), 0, 0, 0, 0));
                    pst.setInt(2, p.getIdUser());
                    rowsUpdated = pst.executeUpdate();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }

                if (rowsUpdated == 1) {
                    System.out.println("- The date was updated successfully!");
                } else {
                    System.err.println("ERROR: rowsUpdatedCount isn't normal -- " + rowsUpdated);
                }
            }
            SessionUser.getUser().setDatenaiss(new Timestamp(this.daten.getValue().getYear() - 1900, this.daten.getValue().getMonthValue() - 1, this.daten.getValue().getDayOfMonth(), 0, 0, 0, 0));
            SessionUser.setProfile(p);
            Parent home_page_parent = FXMLLoader.load(getClass().getResource("HomeFXML.fxml"));
            Scene home_page_scene = new Scene(home_page_parent);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_stage.hide(); //optional
            app_stage.setScene(home_page_scene);
            app_stage.show();
            System.out.println(SessionUser.getProfile().getAboutMe() + " " + SessionUser.getUser().getFirstname());
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Erreur d'entrée");
            alert.setHeaderText(null);
            alert.setContentText("Le slogan et la section \"AboutMe\" du profile ne doivent pas dépasser les 255 caractères.");
            alert.showAndWait();
        }
    }

}
