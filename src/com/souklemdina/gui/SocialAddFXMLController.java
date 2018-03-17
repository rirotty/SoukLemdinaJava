/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.souklemdina.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author ramyk
 */
public class SocialAddFXMLController implements Initializable {

    @FXML
    private ImageView logo;
    @FXML
    private TextArea txarAbout;
    @FXML
    private TextArea txarTag;
    @FXML
    private Label labAbout;
    @FXML
    private Label labTag;
    @FXML
    private Button butImg;
    @FXML
    private Label labImg;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    
    
}
