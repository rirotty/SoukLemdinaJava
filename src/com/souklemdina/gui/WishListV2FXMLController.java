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
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author hatem
 */
public class WishListV2FXMLController implements Initializable {
    @FXML
    private ScrollPane scrP;
    @FXML
    private VBox vbox;
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
    
       
        Wishlist w = new Wishlist();
        w = ws.getWishListById(user.getId());

        lstlw = lws.findAllLigneByIdWishList(w.getId());
        for (int i = 0; i < lstlw.size(); i++) {
            Produit p = new Produit();
            LigneWishlistProduit lwp = new LigneWishlistProduit();

            p = ps.getProduitById(lstlw.get(i).getIdProduit());
            lwp.setIdLigneWishlist(lstlw.get(i).getId());
            lwp.setImage(p.getImage());
            lwp.setIdProduit(p.getId());
            lwp.setIdWishlist(lstlw.get(i).getIdWishlist());
            lwp.setLibelle(p.getLibelle());
            lwp.setPrix(p.getPrix());
            lwp.setQuantiteEnStock(p.getQuqntite());
lwp.setDescription(p.getDescription());
            lstlwp.add(lwp);
            
        }
        //for(int i = 0; i < lstlw.size(); i++)
            for(LigneWishlistProduit l : lstlwp)
        {
            HBox hbox = new HBox();
       VBox v1 = new VBox();
            Button btnPanier = new Button("Ajouter au panier");
            Button btnSupp = new Button("Supprimer");
            ImageView img = new ImageView();
            javafx.scene.image.Image img1= new javafx.scene.image.Image("http://localhost/SoukLemdina/web/uploads/images/" + l.getImage(),100,100,true,true);
            img.setImage(img1); 
           HBox h11= new HBox();
       Label lab = new Label();
       lab.setText(l.getLibelle()+"\n"+l.getDescription());
       Label lab2 = new Label();
       lab2.setText(l.getPrix()+"\n"+l.getQuantiteEnStock());
      h11.getChildren().addAll(lab);
       h11.getChildren().add(lab2);
     hbox.getChildren().add(h11);
        v1.getChildren().add(hbox);// cadre.getChildren().add(v1);
           v1.getChildren().add(h11) ; 
        VBox btnVbox = new VBox();
         Label lOk= new Label();
       btnVbox.getChildren().addAll(btnPanier,btnSupp,lOk);
       btnVbox.setAlignment(Pos.TOP_LEFT);
       
       
      
       //VBox cadre = new VBox();
         
       HBox btw = new HBox();
       /*     GridPane gp = new GridPane();
            gp.setHgap(10);
        gp.setVgap(10);
        gp.setPadding(new Insets(0, 20, 0, 20));  */
       hbox.getChildren().add(img);
       hbox.getChildren().add(lab);
       hbox.getChildren().add(lab2);
       hbox.getChildren().add(btnVbox);
       hbox.setSpacing(40);
              vbox.getChildren().add(v1);
              
              
              Separator sp = new Separator();
              sp.setMaxWidth(150);
              //vbox.setSpacing(30);
              hbox.setSpacing(20);
              btnVbox.setSpacing(5);
          //  gp.getChildren().add(vbox);
            
            Separator separator2 = new Separator();
            separator2.setOrientation(Orientation.VERTICAL);

            separator2.setLayoutX(5);
            vbox.setSpacing(separator2.getLayoutX());


hbox.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;"
                    + "-fx-border-width: 2;" + "-fx-border-insets: 5;"
                    + "-fx-border-radius: 2;" + "-fx-border-color: blue;" + "-fx-width: 100%;");
       btnPanier.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                                Produit p = new Produit();
p=ps.getProduitById(l.getIdProduit());
                    PanierServices pans= new PanierServices();

                    if (!pans.prodExistant(user, p)){
                    pans.ajouterPanier(user, p);
                    lOk.setText("Ajouté au panier");}
                    else 
                    {
                    lOk.setText("Produit existe deja dans votre panier");
                    }
                }
            });
       
       btnSupp.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                                LigneWishlist lwl = new LigneWishlist();
lwl=lws.getLigneWishlistById(l.getIdLigneWishlist());
lws.delete(lwl);
vbox.getChildren().remove(sp);
vbox.getChildren().remove(v1);

//lOk.setText("Supprimé de la liste");
                }
            });
        }
       
    }    
    
}
