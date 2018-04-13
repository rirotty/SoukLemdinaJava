/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.souklemdina.gui;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.souklemdina.entities.Commande;
import com.souklemdina.entities.LigneDeCommande;
import com.souklemdina.entities.Produit;
import com.souklemdina.services.CommandeServices;
import com.souklemdina.services.LigneDeCommandeServices;
import com.souklemdina.services.ProduitServices;
import com.souklemdina.util.LigneDeCommandeProduit;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author hatem
 */
public class AfficherLigneDeCommandeFXMLController implements Initializable {

    @FXML
    private Button btnCommande;

    @FXML
    private TableView<LigneDeCommandeProduit> tableLigneDeCommande = new TableView<>();
    ObservableList<LigneDeCommandeProduit> items = FXCollections.observableArrayList();
    private Integer idC;
    LigneDeCommandeServices lcs = new LigneDeCommandeServices();
    ProduitServices ps = new ProduitServices();
    ArrayList<LigneDeCommande> lstLigneCommande = new ArrayList();
    ArrayList<Produit> lstProduit = new ArrayList<Produit>();
    ArrayList<LigneDeCommandeProduit> lstlcp = new ArrayList<>();
    CommandeServices cs = new CommandeServices();
    Commande cmd = new Commande();

    public static Integer X = 0;
    public static Integer K = 0;
    @FXML
    private Label labelNotif;
    @FXML
    private ImageView alert;
    @FXML
    private ImageView ok;
    @FXML
    private AnchorPane ancp;
    @FXML
    private Button btnAcc;

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

        TableColumn<LigneDeCommandeProduit, LigneDeCommandeProduit> modifier = new TableColumn<>("Modifier Lieu De Livraison");
        modifier.setMinWidth(20);
        modifier.setCellValueFactory(e -> new ReadOnlyObjectWrapper<>(e.getValue()));
        modifier.setCellFactory(param -> {
            return new TableCell<LigneDeCommandeProduit, LigneDeCommandeProduit>() {
                private final Button updateButton = new Button("Modifier");

                @Override
                protected void updateItem(LigneDeCommandeProduit c, boolean empty) {
                    super.updateItem(c, empty);

                    if (c == null) {
                        setGraphic(null);
                        return;
                    }
                    if (new Timestamp(new Date().getTime()).after(cmd.getDateMax())) {
                        setGraphic(updateButton);
                        updateButton.setDisable(true);
                        labelNotif.setText("Vous ne pouvez plus modifier ou supprimer vos lignes de commande");
                        labelNotif.setAlignment(Pos.CENTER);
                        labelNotif.setStyle("-fx-background-color:transparent;");
                        alert.setVisible(true);
                        ok.setVisible(false);
                    } else if (new Timestamp(new Date().getTime()).before(cmd.getDateMax())) {
                        String s = cmd.getDateMax().toString().substring(0, 19);
                        labelNotif.setText("Dernier delais pour modifier ou supprimer vos lignes de commande est " + s);
                        labelNotif.setAlignment(Pos.CENTER);
                        labelNotif.setStyle("-fx-background-color:transparent;");
                        ok.setVisible(true);
                        alert.setVisible(false);
                    }
                    setGraphic(updateButton);
                    updateButton.setOnAction(new EventHandler<ActionEvent>() {

                        @Override
                        public void handle(ActionEvent event) {
                            X = 1;
                            int i = getTableView().getItems().indexOf(c);
                            // int j = getTableView().getItems().indexOf(c);
                            K = getTableView().getItems().get(i).getIdLigneDeCommande();
                            /*int j = getTableView().getItems().get(i).getIdCommande();
                             Commande c = new Commande();
                             c=cs.getCommandeByIdCommande(j);*/

                            //System.out.println(flag());
                            
                           
                                AnchorPane p = null;
                     try {
                         p = FXMLLoader.load(getClass().getResource("CommandeFXML.fxml"));
                     } catch (IOException ex) {
                         Logger.getLogger(PaypalFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                     }
       ancp.getChildren().setAll(p);
                           
                        }

                    }
                    // event -> getTableView().getItems().remove(c)
                    );
                }
            };
        });
        TableColumn<LigneDeCommandeProduit, LigneDeCommandeProduit> Supp = new TableColumn<>("Supprimer Ligne De Commande");
        Supp.setMinWidth(20);
        Supp.setCellValueFactory(e -> new ReadOnlyObjectWrapper<>(e.getValue()));
        Supp.setCellFactory(param -> {
            return new TableCell<LigneDeCommandeProduit, LigneDeCommandeProduit>() {
                private final Button suppButton = new Button("Supprimer");

                @Override
                protected void updateItem(LigneDeCommandeProduit s, boolean empty) {
                    super.updateItem(s, empty);

                    if (s == null) {
                        setGraphic(null);
                        return;
                    }

                    if (new Timestamp(new Date().getTime()).after(cmd.getDateMax())) {
                        setGraphic(suppButton);
                        suppButton.setDisable(true);
                    }
                    setGraphic(suppButton);
                    suppButton.setOnAction(new EventHandler<ActionEvent>() {

                        @Override
                        public void handle(ActionEvent event) {

                            int i = getTableView().getItems().indexOf(s);
                            int idL = getTableView().getItems().get(i).getIdLigneDeCommande();
                            LigneDeCommande lcmd = new LigneDeCommande();
                            lcmd = lcs.getLigneDeCommandeById(idL);
                            lcs.delete(lcmd);
                            getTableView().getItems().remove(s);
                            //System.out.println(getTableView().getItems().isEmpty());
                            if (getTableView().getItems().isEmpty()) {
                                cs.delete(cmd);
                           
                                 AnchorPane p = null;
                     try {
                         p = FXMLLoader.load(getClass().getResource("AfficherCommandeFXML.fxml"));
                     } catch (IOException ex) {
                         Logger.getLogger(PaypalFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                     }
       ancp.getChildren().setAll(p);
                            }

                           // X=1;
                            //System.out.println(flag());
                        }

                    }
                    // event -> getTableView().getItems().remove(c)
                    );
                }
            };
        });
                //tableLigneDeCommande.setEditable(true);
       /* System.out.println(idC);
         cmd=cs.getCommandeByIdCommande(idC);
         if(new Timestamp(new Date().getTime()).after(cmd.getDateMax()))
         {
         .setText("aaaa");
         }*/
        tableLigneDeCommande.setItems(items);
        tableLigneDeCommande.getColumns().addAll(idCm, libelle, desc, prixU, prixT, adresse, adresse2, ville, codePostal, numTel, modifier, Supp);

        for (int i = 0; i < lstLigneCommande.size(); i++) {

            items.add(lstlcp.get(i));
            //System.out.println(tableCommande.getSelectionModel().selectedItemProperty());
            //System.out.println();
        }

    }

    public void getIdCommande(Integer id) {
        idC = id;
        affLigneCommande();
        //System.out.println(AfficherLigneDeCommandeFXMLController.idC);
        //   
        // System.out.println(lstLigneCommande);

    }

    @FXML
    private void goToCommande(ActionEvent event) throws IOException {
        
               AnchorPane p = null;
                     try {
                         p = FXMLLoader.load(getClass().getResource("AfficherCommandeFXML.fxml"));
                     } catch (IOException ex) {
                         Logger.getLogger(PaypalFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                     }
       ancp.getChildren().setAll(p);
                            }

    

    public int flag() {
        return X;
    }
    
     
    public void pdf() throws IOException {
        affLigneCommande();
        try {
            WritableImage image = tableLigneDeCommande.snapshot(new SnapshotParameters(), null);
            

// TODO: probably use a file chooser here
            File file = new File("LigneDeCommande.png");
            //File file1 = new File("chart1.png");
          


            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
            //ImageIO.write(SwingFXUtils.fromFXImage(image1, null), "png", file1);
           



            OutputStream filex = new FileOutputStream(new File(System.getProperty("user.home") + "/Desktop/LigneDeCommande.pdf"));

            Document document = new Document();

            PdfWriter.getInstance(document, filex);

            document.open();

            Image img = Image.getInstance("LigneDeCommande.png");
            //Image img1 = Image.getInstance("chart1.png");
          



            document.add(img);
          


            document.add(new Paragraph(new Date().toString()));


            document.close();
            filex.close();
                    Desktop.getDesktop().open(new File(System.getProperty("user.home") + "/Desktop/LigneDeCommande.pdf"));

        } catch (Exception e) {

            e.printStackTrace();

        }

    }
    
}
