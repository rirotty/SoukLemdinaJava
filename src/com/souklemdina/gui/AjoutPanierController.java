/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.souklemdina.gui;

import com.paypal.api.payments.Payment;
import com.souklemdina.entities.Commande;
import com.souklemdina.entities.FosUser;
import com.souklemdina.entities.LigneDeCommande;
import com.souklemdina.entities.Panier;
import com.souklemdina.entities.Produit;
import com.souklemdina.services.CommandeServices;
import com.souklemdina.services.LigneDeCommandeServices;
import com.souklemdina.services.PanierServices;
import com.souklemdina.services.ProduitServices;
import com.souklemdina.util.LigneWishlistProduit;
import com.souklemdina.util.SessionUser;
import com.sun.deploy.net.HttpRequest;
import com.sun.deploy.net.HttpResponse;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.ImageViewBuilder;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.converter.IntegerStringConverter;

/**
 * FXML Controller class
 *
 * @author hatem
 */
public class AjoutPanierController implements Initializable {

    @FXML
    private Button btnPaiement;
    @FXML
    private TableColumn<Panier, Image> produitPh;
    @FXML
    private TableColumn<Panier, String> libelle;
    @FXML
    private TableColumn<Panier, String> description;
    @FXML
    private TableColumn<Panier, Integer> qte;
    @FXML
    private TableColumn<Panier, Double> prix;
    private Button btnDelete;
    @FXML
    private Button btnArriere;
//   l
    
    public static Double P=0.;


    /*@FXML
     private TableColumn<Panier,Panier> Supprimer;*/
    @FXML
    private TableView<Panier> tablePanier = new TableView<>();
    final ObservableList<Integer> ratingSample = FXCollections.observableArrayList();

    ObservableList<Panier> items = FXCollections.observableArrayList();
    ObservableList<Integer> items2 = FXCollections.observableArrayList();
    PanierServices pan = new PanierServices();
    ProduitServices pr = new ProduitServices();
    LigneDeCommandeServices lcs = new LigneDeCommandeServices();
    CommandeServices cs = new CommandeServices();

    FosUser user = SessionUser.getUser();
    @FXML
    private VBox vbPanier;
    @FXML
    private AnchorPane ancp;
        ArrayList<Panier> lstPanier = new ArrayList<>();
    @FXML
    private Label l;
    @FXML
    private Label l2;
    @FXML
    private VBox vbox22;
    @FXML
    private Label lbltot;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //  items2.add(1);
        // items2.add(2);
        
        P=0.;
        
        Panier panier = new Panier();
        Produit prod = new Produit();
        ProduitServices ps = new ProduitServices();


        /*   ArrayList<Integer> nbr=new ArrayList<>();
         nbr.add(1) ;
         nbr.add(2);
         nbr.add(3);
         items2.add(nbr);*/
       // ArrayList lstProduit = new ArrayList();
        // lstProduit.addAll(pan.getPanier(user));
        lstPanier = pan.getPanierRetourListPanier(user);
//        System.out.println(lstPanier.get(1).getPrix());

        ArrayList<Commande> c = new ArrayList<>();
        ArrayList<Commande> c2 = new ArrayList<>();
        ArrayList<LigneDeCommande> lc = new ArrayList<>();
        ArrayList<LigneDeCommande> lstlc = new ArrayList<>();
        ArrayList<LigneDeCommande> lstlc2 = new ArrayList<>();
        ArrayList<Commande> lstc = new ArrayList<>();
        ArrayList<Produit> lstp = new ArrayList<>();
       

        // System.out.println(quantite);
//prod1 = p.keySet();
        produitPh.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Panier, javafx.scene.image.Image>, ObservableValue<javafx.scene.image.Image>>() {

            public ObservableValue<javafx.scene.image.Image> call(TableColumn.CellDataFeatures<Panier, javafx.scene.image.Image> p) {
                // System.out.println("C:\\wamp64\\www\\SoukLemdina2\\web\\uploads\\images" + p.getValue().getImage());
                return new SimpleObjectProperty<>(new javafx.scene.image.Image("http://localhost/SoukLemdina/web/uploads/images/" + p.getValue().getImage(), 100, 100, true, true, true));
            }
        });
        produitPh.setCellFactory(new Callback<TableColumn<Panier, javafx.scene.image.Image>, TableCell<Panier, javafx.scene.image.Image>>() {

            public TableCell<Panier, javafx.scene.image.Image> call(TableColumn<Panier, javafx.scene.image.Image> p) {
                return new TableCell<Panier, javafx.scene.image.Image>() {

                    @Override
                    protected void updateItem(javafx.scene.image.Image i, boolean empty) {
                        super.updateItem(i, empty);
                        setText(null);
                        setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                        ImageView imageView = (i == null || empty) ? null : ImageViewBuilder.create().image(i).build();
                        setGraphic(imageView);
                    }
                };
            }
        });

        //  TableColumn<Panier,String> produitPh = new TableColumn<>("Produit");
        //produitPh.setMinWidth(20);
        //produitPh.setCellValueFactory(new PropertyValueFactory<>("image"));
        //  TableColumn<Panier,String> libelle = new TableColumn<>("Libelle");
        libelle.setMinWidth(20);
        libelle.setCellValueFactory(new PropertyValueFactory<>("libelle"));

        // TableColumn<Panier,String> description = new TableColumn<>("Description");
        description.setMinWidth(20);
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        description.setCellFactory(TextFieldTableCell.forTableColumn());

        // TableColumn<Panier,Double> prix = new TableColumn<>("Prix");
        prix.setMinWidth(20);
        prix.setCellValueFactory(new PropertyValueFactory<>("prix"));

        /*  TableColumn<Panier,String> qte = new TableColumn<>("Qauntité");
         qte.setCellValueFactory(new PropertyValueFactory<>("items2"));

         qte.setMinWidth(20);*/
        // TableColumn<Panier, Integer> qte = new TableColumn<>("qte");
        qte.setCellValueFactory(new PropertyValueFactory<>("qauntiteCommander"));
        qte.setMinWidth(20);
        qte.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        TableColumn<Panier, Panier> supp = new TableColumn<>("Supprimer");
        supp.setMinWidth(20);
        supp.setCellValueFactory(e -> new ReadOnlyObjectWrapper<>(e.getValue()));
        supp.setCellFactory(param -> {
            return new TableCell<Panier, Panier>() {
                private final Button updateButton = new Button("Supprimer");
                

                @Override
                protected void updateItem(Panier c, boolean empty) {
                    super.updateItem(c, empty);

                    if (c == null) {
                        setGraphic(null);
                        return;
                    }

                    setGraphic(updateButton);
                    updateButton.setOnAction(new EventHandler<ActionEvent>() {

                        @Override
                        public void handle(ActionEvent event) {

                            int i = getTableView().getItems().indexOf(c);
                            
                            Produit p = new Produit();
                            p=ps.getProduitById(getTableView().getItems().get(i).getId());
                            pan.supprimerProduitPanier(user, p.getId());
                                        P=P-getTableView().getItems().get(i).getPrix();
                                        lbltot.setText(String.valueOf(P)+"DT");

                            getTableView().getItems().remove(c);

                            l.setText("Sous-total (" + items.size() + " articles)");
     //   System.out.println(P);
                            
                        }

                    }
                    );
                }
            };
        });

        tablePanier.getSelectionModel().setCellSelectionEnabled(false);
        tablePanier.setItems(items);
        tablePanier.getColumns().add(supp);
        tablePanier.setEditable(true);
        tablePanier.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
//        this.btnDelete.setDisable(true);
        // prod1.forEach(a->items.add(a.getId().toString()));
        for (int i = 0; i < lstPanier.size(); i++) {
            //  items.add(p.keySet().toString() + " " + p.values().toString());
            items.add(lstPanier.get(i));

            //items2.add(lstPanier.get(i).getQauntiteCommander());
            //ratingSample.add(lstPanier.get(i).getQauntiteCommander());
            //   System.out.println(lstPanier.get(i).getDescription());
            // System.out.println(tablePanier.getItems());

        }
        ArrayList<LigneDeCommande> lc2 = new ArrayList<>();
        ArrayList<Produit> lp2 = new ArrayList<>();
        lc2=lcs.getTop5IdProduit();
        
        for(int i=0;i<lc2.size();i++)
        {
           lp2.add(ps.getProduitById(lc2.get(i).getIdProduit()));
        }
            for(Produit l : lp2)
        {
            HBox hbox22 = new HBox();
            VBox vbox23 = new VBox();
            ImageView img = new ImageView();
            javafx.scene.image.Image img22= new javafx.scene.image.Image("http://localhost/SoukLemdina/web/uploads/images/" + l.getImage(),100,100,true,true);
            img.setImage(img22);
            hbox22.getChildren().add(img);
            Label l22 = new Label();
            l22.setText(l.getLibelle()+"\nType "+l.getType());
            vbox23.getChildren().add(l22);
            Button btnajoutP= new Button();
                        Label l2 = new Label();

            btnajoutP.setText("Ajouter au panier");
            btnajoutP.setOnAction(new EventHandler<ActionEvent>() {

                        @Override
                        public void handle(ActionEvent event) {

                             Produit p = new Produit();
p=ps.getProduitById(l.getId());
                    PanierServices pans= new PanierServices();

                    if (!pans.prodExistant(user, p)){
                    pans.ajouterPanier(user, p);
                                                l2.setText("Ajouté au panier");
                                                items.clear();
                                                reload();
                    }
                      else 
                    {
                    l2.setText("Produit existe deja");
                    }

                        }

                    }
                    );
   
            vbox23.getChildren().add(btnajoutP);
            vbox23.getChildren().add(l2);
            hbox22.getChildren().add(vbox23);
vbox22.getChildren().add(hbox22);
hbox22.setSpacing(10);
hbox22.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;"
                    + "-fx-border-width: 2;" + "-fx-border-insets: 5;"
                    + "-fx-border-radius: 2;" + "-fx-border-color: #4d88a8;" + "-fx-width: 100%;");
        }
        
        for(int j=0;j<items.size();j++)
            
                                P=P+items.get(j).getPrix();
        lbltot.setText(String.valueOf(P)+"DT");
        //System.out.println(P);
        
        l.setText("Sous-total (" + items.size() + ")");
        //l1.setText("Ou");
        //btnArriere = new Button("Continuer vos achats");
//        vbPanier.getChildren().add(l);
        //vbPanier.getChildren().add(btnPaiement);
        //vbPanier.getChildren().add(l2);
        //vbPanier.getChildren().add(btnArriere);
        //vbPanier.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;"
//                + "-fx-border-width: 0;" + "-fx-border-insets: 5;"
//                + "-fx-border-radius: 2;" + "-fx-border-color: blue;" + "-fx-width: 100%;" );
        /*
         // System.out.println(pan.getPanier(user));
         ArrayList lstProduit = new ArrayList();
         lstProduit.addAll(pan.getPanier(user));
         //  lstProduit = pan.getPanier(user);
         System.out.println(pan.getPanier(user));

         lc.findAll().forEach(a->items.add(a.getId().toString()));//items.add("aaaaaaa");
         /* System.out.println("ajout effectué avec succés");
         items.clear();
         for (int i=0;i<lstProduit.size();i++)
         {items.add(pan.getPanier(user).get(i).toString());
       
         }
         lstPanier.setItems(items);

         System.out.println(lstPanier.getItems());
         */
        /* for (int i=0;i<10;i++)
         {
         //items.add.forEach(a->items.add(a.toString()));;
         items.add("aaaa");
         }*/
        //   (lstProduit.get(i).toString())
    }
    /*
     @FXML
     public void userClickedOnTable() {
     if (tablePanier.getSelectionModel().getSelectedItem() != null) {
     this.btnDelete.setDisable(false);
     }
     }*/

    @FXML
    public void ModifierQuantite(CellEditEvent event) throws IOException {

        P=0.;
        Panier panier = tablePanier.getSelectionModel().getSelectedItem();
        panier.setQauntiteCommander((Integer) event.getNewValue());
        pan.modifierProduitPanier(user, panier);
        //System.out.println(tablePanier.getSelectionModel().getSelectedItem().getQauntiteCommander());
        lstPanier=pan.getPanierRetourListPanier(user);
        items.clear();
        for (int i = 0; i < lstPanier.size(); i++) {
            items.add(lstPanier.get(i));
            
        }
          tablePanier.setItems(items);

        //tablePanier.refresh();
          
          for(int j=0;j<items.size();j++)
          {
              P=P+items.get(j).getPrix();
              lbltot.setText(String.valueOf(P)+"DT");
          }
        System.out.println(P);
       
    }

    /* @FXML
     private void supprimerLignesDePanier() {

     ObservableList<Panier> selectedRows /*,allPanier*/
    ;
        //allPanier = tablePanier.getItems();

        //this gives us the rows that were selected
       // selectedRows = tablePanier.getSelectionModel().getSelectedItems();
        // System.out.println(selectedRows);
        //loop over the selected rows and remove the Person objects from the table
       // for (int i = 0; i < selectedRows.size(); i++) {
       //     pan.supprimerProduitPanier(user, selectedRows.get(i));
       // }
       // items.removeAll(selectedRows);
       //         l.setText("Sous-total ("+items.size()+" articles)");

            //System.out.println("yo");

  //  }

    @FXML
    private void goToCommande(ActionEvent event) throws IOException {
        
        AnchorPane p = null;
                     try {
                         p = FXMLLoader.load(getClass().getResource("CommandeFXML.fxml"));
                     } catch (IOException ex) {
                         Logger.getLogger(PaypalFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                     }
       this.ancp.getChildren().setAll(p);

        

    }

    @FXML
    private void goToWishList(ActionEvent event) throws IOException {
        
        AnchorPane p = null;
                     try {
                         p = FXMLLoader.load(getClass().getResource("WishListV2FXML.fxml"));
                     } catch (IOException ex) {
                         Logger.getLogger(PaypalFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                     }
       this.ancp.getChildren().setAll(p);

        

    }
    
    	
private void reload()
{P=0.;
            lstPanier = pan.getPanierRetourListPanier(user);

    for (int i = 0; i < lstPanier.size(); i++) {
            items.add(lstPanier.get(i));

    P=P+items.get(i).getPrix();

        }
        lbltot.setText(String.valueOf(P)+"DT");
}
}
