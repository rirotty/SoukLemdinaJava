/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.souklemdina.gui;

import com.souklemdina.entities.FosUser;
import com.souklemdina.entities.LigneWishlist;
import com.souklemdina.entities.Produit;
import com.souklemdina.entities.Wishlist;
import com.souklemdina.services.LigneWishlistServices;
import com.souklemdina.services.PanierServices;
import com.souklemdina.services.ProduitServices;
import com.souklemdina.services.WishlistServices;
import com.souklemdina.util.LigneWishlistProduit;
import com.souklemdina.util.SessionUser;
import java.io.IOException;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.ImageViewBuilder;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author hatem
 */
public class WishListFXMLController implements Initializable {

    @FXML
    private TableView<LigneWishlistProduit> tableWishList;
    ObservableList<LigneWishlistProduit> items = FXCollections.observableArrayList();

    @FXML
    private TableColumn<LigneWishlistProduit, Image> produitImage;
    @FXML
    private TableColumn<LigneWishlistProduit, String> produitDesc;
    @FXML
    private Button bntAjout;
    @FXML
    private Button bntPanier;
    @FXML
    private Button bntSupp;
    ArrayList<LigneWishlistProduit> lstlwp = new ArrayList<>();
    List<LigneWishlist> lstlw = new ArrayList<>();

    ArrayList<Produit> lstp = new ArrayList<Produit>();
    WishlistServices ws = new WishlistServices();
    LigneWishlistServices lws = new LigneWishlistServices();
    ProduitServices ps = new ProduitServices();

    FosUser user = SessionUser.getUser();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //tableWishList.setStyle("-fx-table-cell-border-color: transparent;");

        Wishlist w = new Wishlist();
        w = ws.getWishListById(user.getId());
        //LigneWishlist lw = new LigneWishlist();

        lstlw = lws.findAllLigneByIdWishList(w.getId());
        //System.out.println(lstlw);
        for (int i = 0; i < lstlw.size(); i++) {//System.out.println(lstlw.get(i).getIdProduit());
            Produit p = new Produit();
            LigneWishlistProduit lwp = new LigneWishlistProduit();

            p = ps.getProduitById(lstlw.get(i).getIdProduit());
            lwp.setIdLigneWishlist(lstlw.get(i).getId());
            lwp.setImage(p.getImage());
            lwp.setIdProduit(p.getId());
            lwp.setIdWishlist(lstlw.get(i).getIdWishlist());
            lwp.setDesc(p.getLibelle() + "\n" + p.getDescription() + "\n" + p.getPrix() + " DT \nQuantité en Stock : " + p.getQuqntite());

            //  System.out.println(lwp.getIdProduit());
            lstlwp.add(lwp);
            //System.out.println(lstlw.get(i).getIdProduit());
        }
        produitImage.setMinWidth(50);
        produitImage.setCellValueFactory(new PropertyValueFactory<>("image"));

        produitDesc.setMinWidth(50);
        produitDesc.setCellValueFactory(new PropertyValueFactory<>("desc"));
        tableWishList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        produitImage.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<LigneWishlistProduit, Image>, ObservableValue<Image>>() {

            public ObservableValue<Image> call(TableColumn.CellDataFeatures<LigneWishlistProduit, Image> p) {
                System.out.println("C:\\wamp64\\www\\SoukLemdina2\\web\\uploads\\images"+p.getValue().getImage());
                return new SimpleObjectProperty<>(new Image("file:C:\\wamp64\\www\\SoukLemdina2\\web\\uploads\\images\\" + p.getValue().getImage(), 100, 100, true, true, true));
            }
        });
        produitImage.setCellFactory(new Callback<TableColumn<LigneWishlistProduit, Image>, TableCell<LigneWishlistProduit, Image>>() {

            public TableCell<LigneWishlistProduit, Image> call(TableColumn<LigneWishlistProduit, Image> p) {
                return new TableCell<LigneWishlistProduit, Image>() {

                    @Override
                    protected void updateItem(Image i, boolean empty) {
                        super.updateItem(i, empty);
                        setText(null);
                        setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                        ImageView imageView = (i == null || empty) ? null : ImageViewBuilder.create().image(i).build();
                        setGraphic(imageView);
                    }
                };
            }
        });
        /*        produitImage.setCellFactory(param -> {
         //Set up the ImageView
         final ImageView imageview = new ImageView();
         imageview.setFitHeight(50);
         imageview.setFitWidth(50);
         TableCell<LigneWishlistProduit, Image> cell = new TableCell<LigneWishlistProduit, Image>() {
         public void updateItem(Image item, boolean empty) {
         if (item != null) {
         imageview.setImage(item);
         }
         }
         };
         // Attach the imageview to the cell
         cell.setGraphic(imageview);
         return cell;
         });*/
//        this.bntAjout.setDisable(true);
        //    this.bntSupp.setDisable(true);

        tableWishList.setItems(items);
        for (int i = 0; i < lstlwp.size(); i++) {
            items.add(lstlwp.get(i));
        }
    }
    /*  @FXML
     public void userClickedOnTable()
     {
     if (tableWishList.getSelectionModel().getSelectedItem()!=null)
     //    this.bntAjout.setDisable(false);
     this.bntSupp.setDisable(false);
     }*/

    @FXML
    public void ajouterAuPanier() {
        PanierServices pans = new PanierServices();

        ObservableList<LigneWishlistProduit> listSelected;
        listSelected = tableWishList.getSelectionModel().getSelectedItems();
        for (int i = 0; i < listSelected.size(); i++) {
            Produit p = new Produit();
            p = ps.getProduitById(listSelected.get(i).getIdProduit());
            pans.ajouterPanier(user, p);
        }

        // System.out.println();
    }

    @FXML
    private void supprimerLignesDePanier() {

        ObservableList<LigneWishlistProduit> selectedRows /*,allPanier*/;
        //allPanier = tablePanier.getItems();

        //this gives us the rows that were selected
        selectedRows = tableWishList.getSelectionModel().getSelectedItems();
        // System.out.println(selectedRows);
        //loop over the selected rows and remove the Person objects from the table
        for (int i = 0; i < selectedRows.size(); i++) {
            LigneWishlist lw = new LigneWishlist();
            lw.setId(selectedRows.get(i).getIdLigneWishlist());
            lw.setIdProduit(selectedRows.get(i).getIdProduit());
            lw.setIdWishlist(selectedRows.get(i).getIdWishlist());
           // System.out.println(lw.getIdProduit());
            // System.out.println(lw.getIdWishlist());
            lws.delete(lw);
        }
        items.removeAll(selectedRows);
            //System.out.println("yo");

    }
    
    @FXML
    private void goToPanier(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("AjoutPanier.fxml"));
        Parent tableViewParent = loader.load();
                Scene tableViewScene = new Scene(tableViewParent);
                Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
                        window.setScene(tableViewScene);
 
                window.show();

    }
}
