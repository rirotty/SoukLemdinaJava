/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.souklemdina.gui;

import com.souklemdina.entities.Commande;
import com.souklemdina.services.CommandeServices;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hatem
 */
public class BackOfficeCommandeFXMLController implements Initializable {
    @FXML
    private TableView<Commande> tblView;
ObservableList<Commande> items = FXCollections.observableArrayList();
        Commande c = new Commande();
        CommandeServices cs = new CommandeServices();
    @FXML
    private AnchorPane ancp;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODOCommandeServices cs = new CommandeServices();
        ArrayList<Commande> lstCommande = new ArrayList();
        lstCommande = cs.findAlll();

        TableColumn<Commande, Integer> Id_Commande = new TableColumn<>("Id Commande");
        Id_Commande.setMinWidth(20);
        Id_Commande.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Commande, Timestamp> dt_Commande = new TableColumn<>("Date Commande");
        dt_Commande.setMinWidth(20);
        dt_Commande.setCellValueFactory(new PropertyValueFactory<>("dateCommande"));
        TableColumn<Commande, Timestamp> dt_max = new TableColumn<>("Date maximale pour supprimer la commande");
        dt_max.setMinWidth(20);
        dt_max.setCellValueFactory(new PropertyValueFactory<>("dateMax"));
   
    Id_Commande.prefWidthProperty().bind(tblView.widthProperty().multiply(0.333));
                dt_Commande.prefWidthProperty().bind(tblView.widthProperty().multiply(0.3));
                dt_max.prefWidthProperty().bind(tblView.widthProperty().multiply(0.366));


        tblView.setItems(items);
        tblView.getColumns().addAll(Id_Commande, dt_Commande,dt_max);
     for (int i = 0; i < lstCommande.size(); i++) {
            items.add(lstCommande.get(i));
      
        }
    
    }
    
    
    @FXML
    private void getLigne(MouseEvent event) throws IOException {
       // System.out.println(tableCommande.getSelectionModel().isEmpty());
        if (!tblView.getSelectionModel().isEmpty())
        {Integer idC = 0;
        Integer idP = 0;
        //AfficherLigneDeCommandeFXMLController lcfxml = new AfficherLigneDeCommandeFXMLController();
idC=tblView.getSelectionModel().getSelectedItem().getId();
//idP=tableCommande.getSelectionModel().getSelectedItem().get

FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("AfficherLigneDeCommandeFXML.fxml"));
      
        AnchorPane p = loader.load();
                BackOfficeLigneCommandeFXMLController c = loader.getController();        
c.getIdCommande(idC);
       this.ancp.getChildren().setAll(p);
//System.out.println(id);
    }}
    
}
