/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.souklemdina.gui;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author ramyk
 */
public class NewFXMain extends Application {

    @Override
    public void start(Stage stage) throws IOException {
//        Parent root = FXMLLoader.load(getClass().getResource("LoginFXML.fxml"));
//        stage.setTitle("Register & Login to Souk Lemdina");
//        Parent root = FXMLLoader.load(getClass().getResource("BackFXML.fxml"));
//        stage.setTitle("Gestion du Souk");
        Parent root = FXMLLoader.load(getClass().getResource("HomeFXML.fxml"));
        stage.setTitle("Souk Lemdina App");

        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);

        stage.getIcons().add(new Image(getClass().getResourceAsStream("app.png")));
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
