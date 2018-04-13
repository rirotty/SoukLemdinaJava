/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.souklemdina.gui;

import com.souklemdina.entities.Commande;
import com.souklemdina.entities.FosUser;
import com.souklemdina.entities.LigneDeCommande;
import com.souklemdina.entities.Panier;
import com.souklemdina.services.CommandeServices;
import com.souklemdina.services.LigneDeCommandeServices;
import com.souklemdina.services.PanierServices;
import com.souklemdina.util.SessionUser;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hatem
 */
public class PaiementFXMLController implements Initializable {

    @FXML
    private Button btnConfirmer;
    private static ArrayList infos = new ArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO 

    }

    @FXML
    private void ajouterCommandeEtLigneCommande(ActionEvent event) throws IOException {
        PanierServices pan = new PanierServices();
        ArrayList<Panier> lstPanier = new ArrayList<>();
        FosUser user = SessionUser.getUser();
        lstPanier = pan.getPanierRetourListPanier(user);
        CommandeServices cs = new CommandeServices();
        LigneDeCommandeServices lcs = new LigneDeCommandeServices();

        Commande c = new Commande();
        c.setIdUser(user.getId());

        c.setDateCommande(new Timestamp(System.currentTimeMillis()));

        Timestamp timestamp = new Timestamp(new Date().getTime());

        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(timestamp.getTime());

        cal.add(Calendar.MINUTE, 2);
        c.setDateMax(new Timestamp(cal.getTime().getTime()));
        cs.create(c);
        for (int i = 0; i < lstPanier.size(); i++) {
//            infos.getInfos(null, null, null, i, null));
            LigneDeCommande lc = new LigneDeCommande();
            lc.setIdProduit(lstPanier.get(i).getId());
            lc.setIdCommande(cs.getLastIdCommande());
            lc.setPrixTotal(((lstPanier.get(i).getPrix() - (lstPanier.get(i).getPrix() * lstPanier.get(i).getPromotion()) / 100)) * lstPanier.get(i).getQauntiteCommander());
            lc.setQuntite(lstPanier.get(i).getQauntiteCommander());
            lc.setDateLivraison(new Date());
            lc.setAdresse((String) PaiementFXMLController.infos.get(0));
            lc.setAdresse2((String) PaiementFXMLController.infos.get(1));
            lc.setVille((String) PaiementFXMLController.infos.get(2));
            lc.setCodePostal(((Integer) PaiementFXMLController.infos.get(3)));
            lc.setNumTel((String) PaiementFXMLController.infos.get(4));
            lcs.create(lc);

        }

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("AfficherCommandeFXML.fxml"));
        Parent tableViewParent = loader.load();
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);

        window.show();
    }

    public void getInfos(String adresse, String adresse2, String ville, int codePostal, String numTel) {
        PaiementFXMLController.infos.add(adresse);
        PaiementFXMLController.infos.add(adresse2);
        PaiementFXMLController.infos.add(ville);
        PaiementFXMLController.infos.add(codePostal);
        PaiementFXMLController.infos.add(numTel);
        for (int i = 0; i < 4; i++) {

            System.out.println(PaiementFXMLController.infos.get(i));
        //  System.out.println(infos);
            //  return infos;
        }

    }

}
