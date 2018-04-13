/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.souklemdina.gui;

import com.souklemdina.entities.Commande;
import com.souklemdina.entities.LigneDeCommande;
import com.souklemdina.entities.Produit;
import com.souklemdina.services.CommandeServices;
import com.souklemdina.services.LigneDeCommandeServices;
import com.souklemdina.services.ProduitServices;
import com.souklemdina.util.LigneDeCommandeProduit;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hatem
 */
public class BackOfficeLigneCommandeFXMLController implements Initializable {
    @FXML
    private TableView<LigneDeCommandeProduit> tblView;
    private Integer idC;
ObservableList<LigneDeCommandeProduit> items = FXCollections.observableArrayList();
    LigneDeCommandeServices lcs = new LigneDeCommandeServices();
    ProduitServices ps = new ProduitServices();
    ArrayList<LigneDeCommande> lstLigneCommande = new ArrayList();
    ArrayList<Produit> lstProduit = new ArrayList<Produit>();
    ArrayList<LigneDeCommandeProduit> lstlcp = new ArrayList<>();
    CommandeServices cs = new CommandeServices();
    Commande cmd = new Commande();
    @FXML
    private Button btncmd;
    @FXML
    private AnchorPane ancp;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
    }    
    public void affLigneCommande() {
        lstLigneCommande = lcs.getLignesDeCommandeByIdCommande(idC);
        for (int i = 0; i < lstLigneCommande.size(); i++) {
            lstProduit.add(ps.getProduitById(lstLigneCommande.get(i).getIdProduit()));
        }
               // System.out.println(lstProduit);

        //System.out.println(lstLigneCommande);
        for (int i = 0; i < lstLigneCommande.size(); i++) {

            LigneDeCommandeProduit lcp = new LigneDeCommandeProduit();

            lcp.setIdLigneDeCommande(lstLigneCommande.get(i).getId());
            lcp.setImage(lstProduit.get(i).getImage());
            lcp.setLibelle(lstProduit.get(i).getLibelle());
            lcp.setPrix(lstProduit.get(i).getPrix());
            lcp.setDescription(lstProduit.get(i).getDescription());
            lcp.setDateLivraison(lstLigneCommande.get(i).getDateLivraison());
            lcp.setQuantiteCommander(lstLigneCommande.get(i).getQuntite());
            lcp.setIdCommande(lstLigneCommande.get(i).getIdCommande());
            lcp.setAdresse(lstLigneCommande.get(i).getAdresse());
            lcp.setAdresse2(lstLigneCommande.get(i).getAdresse2());
            lcp.setVille(lstLigneCommande.get(i).getVille());
            lcp.setCodePostal(lstLigneCommande.get(i).getCodePostal());
            lcp.setNumTel(lstLigneCommande.get(i).getNumTel());

            double promo = (lstProduit.get(i).getPrix()*(lstProduit.get(i).getPromotion()))/100;
            // double prix=(lstLigneCommande.get(i).getPrixTotal()*lstLigneCommande.get(i).getQuntite())-promo;
            lcp.setPrixU(lstProduit.get(i).getPrix()-promo);
            lcp.setPrixTotal(lstLigneCommande.get(i).getPrixTotal());
            lstlcp.add(lcp);
        }
        cmd = cs.getCommandeByIdCommande(idC);

        
        
        TableColumn<LigneDeCommandeProduit, Integer> idCm = new TableColumn<>("Id Commande");
        idCm.setMinWidth(20);
        idCm.setCellValueFactory(new PropertyValueFactory<>("idCommande"));
        TableColumn<LigneDeCommandeProduit, String> libelle = new TableColumn<>("Libelle");
        libelle.setMinWidth(20);
        libelle.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        TableColumn<LigneDeCommandeProduit, String> desc = new TableColumn<>("Description");
        desc.setMinWidth(20);
        desc.setCellValueFactory(new PropertyValueFactory<>("description"));
        TableColumn<LigneDeCommandeProduit, Double> prixU = new TableColumn<>("Prix Unitaire");
        prixU.setMinWidth(20);
        prixU.setCellValueFactory(new PropertyValueFactory<>("prixU"));
        TableColumn<LigneDeCommandeProduit, Double> prixT = new TableColumn<>("Prix Total");
        prixT.setMinWidth(20);
        prixT.setCellValueFactory(new PropertyValueFactory<>("prixTotal"));

        TableColumn<LigneDeCommandeProduit, Double> adresse = new TableColumn<>("Adresse");
        adresse.setMinWidth(20);
        adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        //adresse.setCellFactory(TextFieldTableCell.<LigneDeCommandeProduit>forTableColumn());

        TableColumn<LigneDeCommandeProduit, Double> adresse2 = new TableColumn<>("Adresse 2");
        adresse2.setMinWidth(20);
        adresse2.setCellValueFactory(new PropertyValueFactory<>("adresse2"));
        TableColumn<LigneDeCommandeProduit, Double> ville = new TableColumn<>("Ville");
        ville.setMinWidth(20);
        ville.setCellValueFactory(new PropertyValueFactory<>("ville"));
        TableColumn<LigneDeCommandeProduit, Double> codePostal = new TableColumn<>("Code Postal");
        codePostal.setMinWidth(20);
        codePostal.setCellValueFactory(new PropertyValueFactory<>("codePostal"));
        TableColumn<LigneDeCommandeProduit, Double> numTel = new TableColumn<>("numero Tel");
        numTel.setMinWidth(20);
        numTel.setCellValueFactory(new PropertyValueFactory<>("numTel"));
        
        tblView.setItems(items);
        tblView.getColumns().addAll(idCm, libelle, desc, prixU, prixT, adresse, adresse2, ville, codePostal, numTel);

        for (int i = 0; i < lstLigneCommande.size(); i++) {

            items.add(lstlcp.get(i));
            //System.out.println(tableCommande.getSelectionModel().selectedItemProperty());
            //System.out.println();
        }
    }
    
    
    public void getIdCommande(Integer id) {
        idC = id;
        affLigneCommande();
    }
    
    @FXML
    private void goToCommande(ActionEvent event) throws IOException {
      
        AnchorPane p = null;
                     try {
                         p = FXMLLoader.load(getClass().getResource("BackOfficeCommandeFXML.fxml"));
                     } catch (IOException ex) {
                         Logger.getLogger(PaypalFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                     }
       this.ancp.getChildren().setAll(p);

    }
}
