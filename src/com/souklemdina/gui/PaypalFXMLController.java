/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.souklemdina.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebView;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Details;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.Transaction;
import com.paypal.base.Constants;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import com.souklemdina.entities.Commande;
import com.souklemdina.entities.FosUser;
import com.souklemdina.entities.LigneDeCommande;
import com.souklemdina.entities.Panier;
import com.souklemdina.services.CommandeServices;
import com.souklemdina.services.LigneDeCommandeServices;
import com.souklemdina.services.PanierServices;
import com.souklemdina.util.SessionUser;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebEngine;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author hatem
 */
public class PaypalFXMLController implements Initializable {

        private static ArrayList infos = new ArrayList();

    @FXML
    private WebView wbv;
WebEngine engine;
    @FXML
    private AnchorPane anch_pane;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        //   WebView wbv = new WebView();
         engine = wbv.getEngine();

        String clientId = "AYSq3RDGsmBLJE-otTkBtM-jBRd1TCQwFf9RGfwddNXWz0uFU9ztymylOhRS";
        String clientSecret = "EGnHDxD_qRPdaLdZz8iCr8N7_MzF-YHPTkjs6NKYQvQSBngp4PTTVWkPZRbL";

       // Set payer details
        Payer payer = new Payer();
        payer.setPaymentMethod("paypal");

// Set redirect URLs
        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl("http://localhost:3000/cancel");
        redirectUrls.setReturnUrl("http://localhost:3000/process");
        

// Set payment details
        Details details = new Details();
        details.setShipping("0");
        details.setSubtotal(String.valueOf(AjoutPanierController.P));
        details.setTax("0");

// Payment amount
        Amount amount = new Amount();
        amount.setCurrency("USD");
// Total must be equal to sum of shipping, tax and subtotal.
        amount.setTotal(String.valueOf(AjoutPanierController.P));
        amount.setDetails(details);

// Transaction information
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setDescription("This is the payment transaction description.");

// Add transaction to a list
        List<Transaction> transactions = new ArrayList<Transaction>();
        transactions.add(transaction);

// Add payment details
        Payment payment = new Payment();
        payment.setIntent("sale");
        payment.setPayer(payer);
        payment.setRedirectUrls(redirectUrls);
        payment.setTransactions(transactions);
// Create payment
        try {
            APIContext apiContext = new APIContext(clientId, clientSecret, "sandbox");

            Payment createdPayment = payment.create(apiContext);

            Iterator links = createdPayment.getLinks().iterator();
            while (links.hasNext()) {
                Links link = (Links) links.next();
                if (link.getRel().equalsIgnoreCase("approval_url")) {
                    // REDIRECT USER TO 
                    engine.load(link.getHref());
                    //   System.out.println(link.toJSON());
                 /*   HttpRequest req;
                HttpResponse resp;
                System.out.println(req(www.google.com));*/
                   // System.out.println(link.getHref());
              //  System.out.println(createdPayment.getPayer());

                    //System.out.println(link.getHref());
                }
            }

        } catch (PayPalRESTException e) {
            System.err.println(e.getDetails());
        }
        
        
   engine.getLoadWorker().stateProperty().addListener((observable, oldValue, newValue) -> {
        //System.out.println(engine.locationProperty().getValue());
                 String payId = "";

if (engine.locationProperty().getValue().startsWith("http://localhost:3000/process?"))
{
    payId=engine.locationProperty().getValue().substring(40, engine.locationProperty().getValue().indexOf("&"));
        System.out.println("waaaaaaa"+payId);
        
          payment.setId(payId);
            APIContext apiContext = new APIContext(clientId, clientSecret, "sandbox");

PaymentExecution paymentExecution = new PaymentExecution();
paymentExecution.setPayerId("AVAD6N4Y8CC92");
try {
  Payment createdPayment = payment.execute(apiContext, paymentExecution);
 // System.out.println(createdPayment);
  ajouterCommandeEtLigneCommande();
  AnchorPane p = null;
                     try {
                         p = FXMLLoader.load(getClass().getResource("AfficherCommandeFXML.fxml"));
                     } catch (IOException ex) {
                         Logger.getLogger(PaypalFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                     }
       this.anch_pane.getChildren().setAll(p);
} catch (PayPalRESTException e) {
  System.err.println(e.getDetails());
}
ajouterCommandeEtLigneCommande();
FXMLLoader loader = new FXMLLoader();

              
       
       
}   
            
        });
    
       

       /* try {
            APIContext apiContext = new APIContext(clientId, clientSecret, "sandbox");
            Payment createdPayment = payment.create(apiContext);
            PaymentExecution paymentExecution = new PaymentExecution();

            paymentExecution.setPayerId(engine.getUserAgent());

            payment.execute(apiContext, paymentExecution);
            System.out.println(createdPayment);

    // For debug purposes only:      System.out.println(createdPayment.toString());
        } catch (PayPalRESTException e) {
    // Handle errors
        } catch (Exception ex) {
    // Handle errors
        }*/
      /* engine.getLoadWorker().stateProperty().addListener((observable, oldValue, newValue) -> {
        System.out.println(engine.locationProperty().getValue());
                 String payId = "";

if (engine.locationProperty().getValue().startsWith("http://localhost:3000/process?"))
{
    payId=engine.locationProperty().getValue().substring(40, engine.locationProperty().getValue().indexOf("&"));
        System.out.println("waaaaaaa"+payId);
}   
            
        });*/
    }
    

    public void getInfos(String adresse, String adresse2, String ville, int codePostal, String numTel) {
        PaypalFXMLController.infos.add(adresse);
        PaypalFXMLController.infos.add(adresse2);
        PaypalFXMLController.infos.add(ville);
        PaypalFXMLController.infos.add(codePostal);
        PaypalFXMLController.infos.add(numTel);
        for (int i = 0; i < 4; i++) {

                System.out.println(PaypalFXMLController.infos.get(i));
        //  System.out.println(infos);
            //  return infos;
        }

    }
    
    @FXML
    private void getUrl(){
    }
     
    private void ajouterCommandeEtLigneCommande()  {
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
            lc.setAdresse((String) CommandeFXMLController.adresse);
                    lc.setAdresse2((String) CommandeFXMLController.adresse2);
            lc.setVille((String) CommandeFXMLController.ville);
            lc.setCodePostal(((Integer) CommandeFXMLController.codePostal));
            lc.setNumTel((String) CommandeFXMLController.numTel);
            lcs.create(lc);

        }

}}
