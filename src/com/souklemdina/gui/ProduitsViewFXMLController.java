/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.souklemdina.gui;

import com.jfoenix.controls.JFXTextField;
import com.souklemdina.entities.LigneWishlist;
import com.souklemdina.entities.Wishlist;
import com.souklemdina.entities.Produit;
import com.souklemdina.services.LigneWishlistServices;
import com.souklemdina.services.PanierServices;
import com.souklemdina.services.ProduitServices;
import com.souklemdina.services.WishlistServices;
import com.souklemdina.util.SessionUser;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.ImageViewBuilder;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class ProduitsViewFXMLController implements Initializable {

    @FXML
    private TableView<Produit> tbl_prod = new TableView<>();
    @FXML
    private JFXTextField txtSearch;
    int prodId;
    @FXML
    private AnchorPane page;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        TableColumn<Produit, String> colLibelle = new TableColumn("Libelle");
        TableColumn<Produit, String> colDescription = new TableColumn("Description");
        TableColumn<Produit, Double> colPrix = new TableColumn("Prix");
        TableColumn<Produit, Integer> colPromotion = new TableColumn("Promotion");
        TableColumn<Produit, Image> colPhoto = new TableColumn("Photo");
        TableColumn<Produit, Integer> colQuantite = new TableColumn("Quantité");
        TableColumn<Produit, String> colType = new TableColumn("Type");
        TableColumn<Produit, String> colCategorie = new TableColumn("Catégorie");
        ProduitServices ps = new ProduitServices();
        colLibelle.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colPrix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        colPromotion.setCellValueFactory(new PropertyValueFactory<>("promotion"));
        colQuantite.setCellValueFactory(new PropertyValueFactory<>("quqntite"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colCategorie.setCellValueFactory(new PropertyValueFactory<>("categorie"));
        colPhoto.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Produit, javafx.scene.image.Image>, ObservableValue<javafx.scene.image.Image>>() {

            public ObservableValue<javafx.scene.image.Image> call(TableColumn.CellDataFeatures<Produit, javafx.scene.image.Image> p) {
                System.out.println("file:D:\\Programs\\wamp64\\www\\SoukLemdina\\web\\uploads\\images\\" + p.getValue().getImage());
                return new SimpleObjectProperty<>(new javafx.scene.image.Image("file:D:\\Programs\\wamp64\\www\\SoukLemdina\\web\\uploads\\images\\" + p.getValue().getImage(), 100, 100, true, true, true));
            }
        });
        colPhoto.setCellFactory(new Callback<TableColumn<Produit, javafx.scene.image.Image>, TableCell<Produit, javafx.scene.image.Image>>() {

            public TableCell<Produit, javafx.scene.image.Image> call(TableColumn<Produit, javafx.scene.image.Image> p) {
                return new TableCell<Produit, javafx.scene.image.Image>() {

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
        TableColumn<Produit, Produit> colCommander = new TableColumn<>("Ajouter au panier");
        colCommander.setMinWidth(20);
        colCommander.setCellValueFactory(e -> new ReadOnlyObjectWrapper<>(e.getValue()));
        colCommander.setCellFactory(param -> {
            return new TableCell<Produit, Produit>() {
                private final Button updateButton = new Button("Ajouter");

                @Override
                protected void updateItem(Produit p, boolean empty) {
                    super.updateItem(p, empty);

                    if (p == null) {
                        setGraphic(null);
                        return;
                    }

                    setGraphic(updateButton);
                    updateButton.setOnAction(new EventHandler<ActionEvent>() {

                        @Override
                        public void handle(ActionEvent event) {
                            Integer id = getTableView().getItems().indexOf(p);
                            PanierServices psss = new PanierServices();
                            ProduitServices prr = new ProduitServices();
                            psss.ajouterPanier(SessionUser.getUser(), prr.getProduitById(getTableView().getItems().get(id).getId()));
                            /*   
                            try {
                                FXMLLoader loader = new FXMLLoader();
                                loader.setLocation(getClass().getResource("AjoutPanier.fxml"));
                                Parent tableViewParent = loader.load();
                                Scene tableViewScene = new Scene(tableViewParent);
                                AjoutPanierController pan = loader.getController();
                                
                                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                window.setScene(tableViewScene);

                                window.show();
                            } catch (IOException ex) {
                                Logger.getLogger(ProduitsViewFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                            }*/

                        }

                    }
                    );
                }
            };
        });

        TableColumn<Produit, Produit> colWishlist = new TableColumn<>("Ajouter au wishlist");
        colWishlist.setMinWidth(20);
        colWishlist.setCellValueFactory(e -> new ReadOnlyObjectWrapper<>(e.getValue()));
        colWishlist.setCellFactory(param -> {
            return new TableCell<Produit, Produit>() {
                private final Button updateButton = new Button("Ajouter");

                @Override
                protected void updateItem(Produit p, boolean empty) {
                    super.updateItem(p, empty);

                    if (p == null) {
                        setGraphic(null);
                        return;
                    }

                    setGraphic(updateButton);
                    updateButton.setOnAction(new EventHandler<ActionEvent>() {

                        @Override
                        public void handle(ActionEvent event) {
                            int i = getTableView().getItems().indexOf(p);
                            ProduitServices prr = new ProduitServices();
                            Integer idP = getTableView().getItems().get(i).getId();
                            Integer id = getTableView().getItems().indexOf(p);
                            WishlistServices wsss = new WishlistServices();
                            if (wsss.getWishListById(SessionUser.getUser().getId()) != null){
                                LigneWishlistServices ls = new LigneWishlistServices();
                                LigneWishlist lw = new LigneWishlist();
                                lw.setIdProduit(idP);
                                lw.setIdWishlist(wsss.getWishListById(SessionUser.getUser().getId()).getId());
                                ls.create(lw);
                            } else {
                                Wishlist  www = new Wishlist();
                                www.setIdUser(SessionUser.getUser().getId());
                                wsss.create(www);
                                LigneWishlistServices ls = new LigneWishlistServices();
                                LigneWishlist lw = new LigneWishlist();
                                lw.setIdProduit(idP);
                                lw.setIdWishlist(wsss.getWishListById(SessionUser.getUser().getId()).getId());
                                ls.create(lw);
                            }
                            /*   
                            try {
                                FXMLLoader loader = new FXMLLoader();
                                loader.setLocation(getClass().getResource("AjoutPanier.fxml"));
                                Parent tableViewParent = loader.load();
                                Scene tableViewScene = new Scene(tableViewParent);
                                AjoutPanierController pan = loader.getController();
                            
                            
                                
                                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                window.setScene(tableViewScene);

                                window.show();
                            } catch (IOException ex) {
                                Logger.getLogger(ProduitsViewFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                            }*/

                        }

                    }
                    );
                }
            };
        });
        tbl_prod.getColumns().addAll(colPhoto, colLibelle, colDescription, colType, colCategorie, colPrix, colQuantite, colPromotion, colCommander, colWishlist);

        tbl_prod.sort();

        tbl_prod.setItems(ps.findAll());

        tbl_prod.setOnMouseClicked(e -> {
            Produit p = tbl_prod.getSelectionModel().getSelectedItem();

            prodId = p.getId();
        });

    }

    @FXML
    private void SearchAction(ActionEvent event) {
        ProduitServices ps = new ProduitServices();
        this.tbl_prod.setItems(ps.Search(txtSearch.getText()));

    }

    @FXML
    private void MaBoutiqueAction(ActionEvent event) throws IOException {
        ScrollPane ppp = FXMLLoader.load(getClass().getResource("ProduitAdd.fxml"));
        page.getChildren().setAll(ppp);
    }

}
