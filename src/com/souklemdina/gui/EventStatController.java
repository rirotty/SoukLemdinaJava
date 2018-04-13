/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.souklemdina.gui;

import com.jfoenix.controls.JFXButton;
import com.souklemdina.services.EvenementServices;
import com.souklemdina.util.DataSource;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class EventStatController implements Initializable {

    @FXML
    private ScrollPane scrollStat;
    @FXML
    private PieChart pie1;
    EvenementServices es = new EvenementServices();
    private PieChart pie2;
    @FXML
    private JFXButton signal;
    @FXML
    private AnchorPane anchv;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        loadchart();
       
    }

    public EventStatController() {
        Connection conn = DataSource.getInstance().getConnection();
    }

    private void loadchart() {

        TYPEE();

    }

    public void TYPEE() {

        // new FadeInUpTransition(commBar).play();
        //new FadeInDowntransition(UserComm).play();
        pie1.getData().clear();
        System.out.println("++++++++++++" + es.moyEvnType());
        pie1.setData(es.moyEvnType());
        pie1.setAnimated(true);
        pie1.setVisible(true);
        pie1.setTitle("EVENEMENTS CLASSES PAR TYPE");

        System.out.println(pie1);
    }

  
    
@FXML
    void gotoAd(ActionEvent e) throws IOException {

        AnchorPane p = FXMLLoader.load(getClass().getResource("AfficheBackFXML.fxml"));
        this.anchv.getChildren().setAll(p);

    }
}
