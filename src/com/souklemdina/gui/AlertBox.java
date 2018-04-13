/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.souklemdina.gui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author ACER
 */
public class AlertBox {

    public static void display(String titre, String msg) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(titre);
        window.setMaxWidth(500);
        window.setMaxHeight(250);
        Label lb = new Label();
        lb.setText(msg);
      
        Button btnClose = new Button("OK");
        btnClose.setOnAction(e-> window.close());
        VBox layout = new VBox();
        layout.getChildren().addAll(lb,btnClose);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }
}
