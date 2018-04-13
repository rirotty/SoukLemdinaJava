/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.souklemdina.gui;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.HiddenSidesPane;

/**
 * FXML Controller class
 *
 * @author rkader
 */
public class SocialFXMLController implements Initializable {

    @FXML
    private AnchorPane bar_browse;
    @FXML
    private HiddenSidesPane pan_hidden;
    @FXML
    private AnchorPane anch_content;
    @FXML
    private AnchorPane pan_home;
    @FXML
    private AnchorPane pan_search;
    @FXML
    private AnchorPane pan_profile;
    @FXML
    private AnchorPane pan_edit;
    @FXML
    private JFXButton btn_home;
    @FXML
    private JFXButton btn_search;
    @FXML
    private JFXButton btn_profile;
    @FXML
    private JFXButton btn_edit;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pan_hidden.setContent(anch_content);
        pan_hidden.setTop(bar_browse);
        ScrollPane p = new ScrollPane();
        try {
            p = FXMLLoader.load(getClass().getResource("SocialHomeFXML.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(SocialFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
            pan_home.getChildren().setAll(p);
            pan_home.setVisible(true);
    }

    @FXML
    private void handleButtonAction(MouseEvent event) throws IOException {
        if (event.getTarget() == btn_home) {
            ScrollPane p = FXMLLoader.load(getClass().getResource("SocialHomeFXML.fxml"));
            pan_home.getChildren().setAll(p);
            pan_home.setVisible(true);
            pan_search.setVisible(false);
            pan_profile.setVisible(false);
            pan_edit.setVisible(false);
        } else if (event.getTarget() == btn_search) {
            ScrollPane p = FXMLLoader.load(getClass().getResource("SocialSearchFXML.fxml"));
            pan_search.getChildren().setAll(p);
            pan_home.setVisible(false);
            pan_search.setVisible(true);
            pan_profile.setVisible(false);
            pan_edit.setVisible(false);
        } else if (event.getTarget() == btn_profile) {
            ScrollPane p = FXMLLoader.load(getClass().getResource("SocialProfileFXML.fxml"));
            p.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
            pan_profile.getChildren().setAll(p);
            pan_home.setVisible(false);
            pan_search.setVisible(false);
            pan_profile.setVisible(true);
            pan_edit.setVisible(false);
        } else if (event.getTarget() == btn_edit) {
            AnchorPane p = FXMLLoader.load(getClass().getResource("SocialEditFXML.fxml"));
            pan_edit.getChildren().setAll(p);
            pan_home.setVisible(false);
            pan_search.setVisible(false);
            pan_profile.setVisible(false);
            pan_edit.setVisible(true);
        }
    }

}
