/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.souklemdina.gui;

import com.souklemdina.util.data;
import com.souklemdina.util.room;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author ramyk
 */
public class NewFXMain extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("LoginFXML.fxml"));
        stage.setTitle("Register & Login to Souk Lemdina");
//        Parent root = FXMLLoader.load(getClass().getResource("BackFXML.fxml"));
//        stage.setTitle("Gestion du Souk");

//        Parent root = FXMLLoader.load(getClass().getResource("HomeFXML.fxml"));
//        stage.setTitle("Souk Lemdina App");
//        
//        stage.setOnCloseRequest(e-> {
//            //e.consume();
//            room.th.stop();
//            System.exit(0);
//        });
        
        stage.setResizable(false);
        Scene scene = new Scene(root);
        
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
