/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.souklemdina.gui;

import com.souklemdina.entities.ParticipantWork;
import com.souklemdina.entities.Workshop;
import com.souklemdina.services.ParticipantWorkService;
import com.souklemdina.services.WorkshopServices;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class AdminAffController implements Initializable {

    @FXML

    private Button sup;
    @FXML
    private Button ret;
    @FXML
    private TableView<Workshop> listeW;
    Workshop w = new Workshop();
    WorkshopServices ws = new WorkshopServices();
    ArrayList<Workshop> lw = new ArrayList<>();
    ArrayList<ParticipantWork> lp = new ArrayList<>();
    ObservableList<Workshop> items = FXCollections.observableArrayList();
    @FXML
    private AnchorPane anxc;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        lw = ws.FindBySignal();
        for (int i = 0; i < lw.size(); i++) {

            items.add(lw.get(i));
            System.out.println(" 98888" + lw.get(i).getNomWorkshop());
        }
        TableColumn<Workshop, String> nomWorkshop = new TableColumn<>("Nom du Workshop");
        nomWorkshop.setMinWidth(30);
        nomWorkshop.setCellValueFactory(new PropertyValueFactory<>("nomWorkshop"));
        System.out.println(" 98888" + nomWorkshop);
        TableColumn<Workshop, Integer> nbSignal = new TableColumn<>("Nb Signal");
        nbSignal.setMinWidth(30);
        nbSignal.setCellValueFactory(new PropertyValueFactory<>("nbSignal"));
        System.out.println(" 98888" + nbSignal);

        listeW.setItems(items);
        listeW.getColumns().addAll(nomWorkshop, nbSignal);
    }

    @FXML
    public void handleBoutonSupprimer() {
        Workshop ls = new Workshop();
        Alert al = null;
        int selectedIndex = listeW.getSelectionModel().getSelectedIndex();
        if (selectedIndex < 0) {
            al = new Alert(Alert.AlertType.ERROR);
            al.setTitle("Alert!!!");
            al.setContentText("Didn't select any item.");
            al.showAndWait();
        } else {
            Workshop w = listeW.getItems().get(listeW.getSelectionModel().getSelectedIndex());
            ParticipantWorkService psw = new ParticipantWorkService();
            ArrayList<ParticipantWork> lp = psw.getWorkshopById(w.getId());
            lp.forEach(e -> psw.delete(e.getId()));
            ws.delete(w.getId());
        }

    }
     @FXML
    void goToAffichage(ActionEvent e) throws IOException{
          
        AnchorPane p = FXMLLoader.load(getClass().getResource("ListeWork.fxml"));
            this.anxc.getChildren().setAll(p);
     
                        
                       
}}
