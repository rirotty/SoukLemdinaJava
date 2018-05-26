/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.souklemdina.gui;

import com.jfoenix.controls.JFXTextField;
import com.souklemdina.entities.Local;
import com.souklemdina.entities.Location;
import com.souklemdina.services.LocalServices;
import com.souklemdina.services.LocationServices;
import com.souklemdina.util.SessionUser;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ACER
 */
public class MesLocationsController implements Initializable {

    @FXML
    private TableColumn<Local, String> colLocal;
//    @FXML
//    private TableColumn<Location, Date> colDateDebut;
//    @FXML
//    private TableColumn<Location, Date> colDateFin;
    @FXML
    private JFXTextField type;
    @FXML
    private JFXTextField adresse;
    @FXML
    private JFXTextField telephone;
    @FXML
    private JFXTextField dteDeb;
    @FXML
    private JFXTextField dteFin;
    @FXML
    private TableView<Local> tableLocal;

    private ObservableList<Location> locations;
    private ObservableList<Local> data;
    @FXML
    private AnchorPane anch_pane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        data = FXCollections.observableArrayList();
        setCellTable();
        AfficherLocation();
        setCellToText();

    }

    public void AfficherLocation() {
        LocationServices ls = new LocationServices();
        //recuperer idUser + aff date ds tableview
        locations = ls.afficherLocationUser(SessionUser.getUser().getId());
        for (Location locat : locations) {
            int id = locat.getIdLocal();
            LocalServices locs = new LocalServices();
            Local loc = locs.afficherLocalUn(id);
            data.add(loc);
        }
        tableLocal.setItems(data);

    }

    public void setCellTable() {
        colLocal.setCellValueFactory(new PropertyValueFactory<>("type"));
//        colDateDebut.setCellValueFactory(new PropertyValueFactory<>("dateDebutLocation"));
//   
//        colDateFin.setCellValueFactory(new PropertyValueFactory<>("dateFinLocation"));

    }

    public void setCellToText() {
        tableLocal.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {

                LocalServices locs = new LocalServices();
                Local l = tableLocal.getItems().get(tableLocal.getSelectionModel().getSelectedIndex());
//                Local loc = locs.afficherLocalUn(l.getId());
                LocationServices ls = new LocationServices();
                ObservableList<Location> location = ls.afficherLocation(l.getId());
                System.out.println(location);
// recherche + idUser
                dteDeb.setText(location.get(0).getDateDebut().toString());
                dteFin.setText(location.get(0).getDateFin().toString());
                type.setText(l.getType());
                adresse.setText(l.getAdresse());
                telephone.setText(l.getTelephone());
            }
        });
    }

    @FXML
    private void handleBoutonRetour(ActionEvent event) throws IOException {
        ScrollPane pageAffiche = FXMLLoader.load(getClass().getResource("Afficher1.fxml"));
        this.anch_pane.getChildren().setAll(pageAffiche);
    }

    @FXML
    private void handleBoutonDetails(ActionEvent event) {
        if (tableLocal.getSelectionModel().getSelectedIndex() < 0) {
            AlertBox.display("Alerte", "veuillez sélectionner un local ");
        } else {
            setCellToText();
        }
    }

    @FXML
    private void handleBoutonSupprimer(ActionEvent event) {
        if (tableLocal.getSelectionModel().getSelectedIndex() < 0) {
            AlertBox.display("Alerte", "veuillez sélectionner un local ");
        } else {
            LocationServices ls = new LocationServices();
            Local l = tableLocal.getItems().get(tableLocal.getSelectionModel().getSelectedIndex());
            int idLocal = l.getId(); // suppresion avec idUser + idLocal
            ls.supprimerLocation(idLocal);
        }
    }
}
