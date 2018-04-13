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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ACER
 */
public class AcceuilController implements Initializable {

    @FXML
    private JFXButton btnAfficheM;
    @FXML
    private JFXButton btnAfficheV;
    @FXML
    private AnchorPane anch_pane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.btnAfficheM.setStyle("-fx-background-color: #40a84a");
        this.btnAfficheM.setMinWidth(100);
        this.btnAfficheM.setTextFill(Color.valueOf("#e3e3e3"));
        this.btnAfficheV.setStyle("-fx-background-color: #40a84a");
        this.btnAfficheV.setMinWidth(100);
        this.btnAfficheV.setTextFill(Color.valueOf("#e3e3e3"));
    }

    @FXML
    public void handleBoutonAfficheM(ActionEvent event) throws IOException {
        ScrollPane pageAffiche = FXMLLoader.load(getClass().getResource("Afficher1.fxml"));
        this.anch_pane.getChildren().setAll(pageAffiche);
    }

    @FXML
    public void handleBoutonAfficheV(ActionEvent event) throws IOException {
        AnchorPane pageAffiche = FXMLLoader.load(getClass().getResource("AfficherA1.fxml"));
        this.anch_pane.getChildren().setAll(pageAffiche);
    }

    public void handleBoutonBack(ActionEvent event) throws IOException {
        ScrollPane pageAffiche = FXMLLoader.load(getClass().getResource("BackLocal.fxml"));
        this.anch_pane.getChildren().setAll(pageAffiche);
    }

}
