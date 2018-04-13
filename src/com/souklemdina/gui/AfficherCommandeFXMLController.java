/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.souklemdina.gui;

import com.souklemdina.entities.Commande;
import com.souklemdina.entities.FosUser;
import com.souklemdina.services.CommandeServices;
import com.souklemdina.util.SessionUser;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hatem
 */
public class AfficherCommandeFXMLController implements Initializable {
    @FXML
    private TableView<Commande> tableCommande = new TableView<>();
    ObservableList<Commande> items = FXCollections.observableArrayList();
        Commande c = new Commande();
        FosUser u = SessionUser.getUser();
    @FXML
    private AnchorPane ancp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //System.out.println(AfficherLigneDeCommandeFXMLController.X);
        CommandeServices cs = new CommandeServices();
        ArrayList<Commande> lstCommande = new ArrayList();
        lstCommande = cs.findAll(u.getId());

        TableColumn<Commande, Integer> Id_Commande = new TableColumn<>("Id Commande");
        Id_Commande.setMinWidth(20);
        Id_Commande.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Commande, Timestamp> dt_Commande = new TableColumn<>("Date Commande");
        dt_Commande.setMinWidth(20);
        dt_Commande.setCellValueFactory(new PropertyValueFactory<>("dateCommande"));
        TableColumn<Commande, Timestamp> dt_max = new TableColumn<>("Date maximale pour supprimer votre commande");
        dt_max.setMinWidth(20);
        dt_max.setCellValueFactory(new PropertyValueFactory<>("dateMax"));
        
        TableColumn<Commande, Commande> pdf = new TableColumn<>("Télécharger en PDF");
        pdf.setMinWidth(20);
        pdf.setCellValueFactory(e -> new ReadOnlyObjectWrapper<>(e.getValue()));
        pdf.setCellFactory(param -> {
            return new TableCell<Commande, Commande>() {
                private final Button updateButton = new Button("Télécharger");

                @Override
                protected void updateItem(Commande c, boolean empty) {
                    super.updateItem(c, empty);

                    if (c == null) {
                        setGraphic(null);
                        return;
                    }
                   
                    setGraphic(updateButton);
                    updateButton.setOnAction(new EventHandler<ActionEvent>() {

                        @Override
                        public void handle(ActionEvent event) {
                            try {
                       
                                FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("AfficherLigneDeCommandeFXML.fxml"));
      
        AnchorPane p = loader.load();
                                        AfficherLigneDeCommandeFXMLController cl = loader.getController();

       ancp.getChildren().setAll(p);
                                  int i = getTableView().getItems().indexOf(c);
                            // int j = getTableView().getItems().indexOf(c);
                          int  idC = getTableView().getItems().get(i).getId();
cl.getIdCommande(idC);
                                cl.pdf();   
                              
                            } catch (IOException ex) {
                                Logger.getLogger(AfficherCommandeFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }

                    }
                    );
                }
            };
        });

                Id_Commande.prefWidthProperty().bind(tableCommande.widthProperty().multiply(0.333));
                dt_Commande.prefWidthProperty().bind(tableCommande.widthProperty().multiply(0.333));
                pdf.prefWidthProperty().bind(tableCommande.widthProperty().multiply(0.333));


        tableCommande.setItems(items);
        tableCommande.getColumns().addAll(Id_Commande, dt_Commande,pdf);
        
      /*  tableCommande.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Commande>(){
       @Override
            public void changed (ObservableValue<? extends Commande> observable, Commande oldValue, Commande newValue) {
     FXMLLoader loader = new FXMLLoader(getClass().getResource("OnSelection.fxml"));
                try {
                    // Load the another FXML file 
                    Parent newParent = loader.load();
                    OnSelectionController subController = loader.getController();
                    // Set the String property
                    // If you want to use data from the current selection: newValue contains the currently selected Person
                    // TODO: Get value from DB
                    subController.textToDisplay.set("value from DB for Person" + newValue.getName());

                    // newParent contains the root of your other FXML file, do anything that you want to do with it (e.g. add to the current node graph)
                    // Now I just simply open it in a new window
                    Stage newStage = new Stage();
                    Scene newScene = new Scene(newParent);
                    newStage.setScene(newScene);
                    newStage.show();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
*/
        for (int i = 0; i < lstCommande.size(); i++) {
            items.add(lstCommande.get(i));
            //System.out.println(tableCommande.getSelectionModel().selectedItemProperty());
            //System.out.println();
        }
    } 
    


    @FXML
    private void getLigne(MouseEvent event) throws IOException {
       // System.out.println(tableCommande.getSelectionModel().isEmpty());
        if (!tableCommande.getSelectionModel().isEmpty())
        {Integer idC = 0;
        Integer idP = 0;
        //AfficherLigneDeCommandeFXMLController lcfxml = new AfficherLigneDeCommandeFXMLController();
idC=tableCommande.getSelectionModel().getSelectedItem().getId();
//idP=tableCommande.getSelectionModel().getSelectedItem().get
FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("AfficherLigneDeCommandeFXML.fxml"));
      
        AnchorPane p = loader.load();
                                     AfficherLigneDeCommandeFXMLController c = loader.getController();        
c.getIdCommande(idC);
       this.ancp.getChildren().setAll(p);
       
       


                
                
//System.out.println(id);
    }}
    
   
    @FXML
    private void goToPanier(ActionEvent event) throws IOException {
        AfficherLigneDeCommandeFXMLController.X=0;
        
        AnchorPane p = null;
                     try {
                         p = FXMLLoader.load(getClass().getResource("AjoutPanier.fxml"));
                     } catch (IOException ex) {
                         Logger.getLogger(PaypalFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                     }
       this.ancp.getChildren().setAll(p);

    }
     
    
}
