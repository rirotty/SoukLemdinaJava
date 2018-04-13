/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.souklemdina.gui;

import com.jfoenix.controls.JFXButton;
import com.souklemdina.entities.Evenement;
import com.souklemdina.services.EvenementServices;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class AfficheBackFXMLController implements Initializable {

    @FXML
    private JFXButton deleteAd;
    @FXML
    private JFXButton retourAd;
    @FXML
    private TableView<Evenement> listS;
    Evenement e = new Evenement();
    EvenementServices es = new EvenementServices();
    ArrayList<Evenement> ls = new ArrayList<>();
    ObservableList<Evenement> items = FXCollections.observableArrayList();
    @FXML
    private AnchorPane anchvv;

    /**
     * Initializes the controller class.
     */
    @Override

    public void initialize(URL url, ResourceBundle rb) {
        ls = es.FindBySignal();
        for (int i = 0; i < ls.size(); i++) {

            items.add(ls.get(i));
            //   System.out.println(" 98888" + lw.get(i).getNomWorkshop());
        }
        TableColumn<Evenement, String> nomWorkshop = new TableColumn<>("Nom de l'événement");
        nomWorkshop.setMinWidth(30);
        nomWorkshop.setCellValueFactory(new PropertyValueFactory<>("nomEvenement"));
        System.out.println(" 98888" + nomWorkshop);
        TableColumn<Evenement, Integer> nbSignal = new TableColumn<>("Nb Signal");
        nbSignal.setMinWidth(30);
        nbSignal.setCellValueFactory(new PropertyValueFactory<>("nbSignal"));
        System.out.println(" 98888" + nbSignal);

        listS.setItems(items);
        listS.getColumns().addAll(nomWorkshop, nbSignal);
    }

    @FXML
    public void handleBoutonSupprimer(MouseEvent event) {
        Evenement ls = new Evenement();
        Alert alert = null;
        int selectedIndex = listS.getSelectionModel().getSelectedIndex();
        if (selectedIndex < 0) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Alert!!!");
            alert.setContentText("Aucun événement séléctionné!!!");
            alert.showAndWait();
        } else {
            Evenement e = listS.getItems().get(listS.getSelectionModel().getSelectedIndex());
//            ParticipantWorkService psw = new ParticipantWorkService();
//            ArrayList<ParticipantWork> lp = psw.getWorkshopById(w.getId());
//            lp.forEach(e -> psw.delete(e.getId()));
            es.delete(e.getId());
            alert.setTitle("Evenement écrasé");
            alert.showAndWait();

        }
    }

    @FXML
    void goTo(ActionEvent e) throws IOException {

        AnchorPane p = FXMLLoader.load(getClass().getResource("EventStat.fxml"));
        this.anchvv.getChildren().setAll(p);

    }

    // TODO
}
