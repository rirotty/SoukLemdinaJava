/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.souklemdina.services;

import com.souklemdina.entities.FosUser;
import com.souklemdina.entities.Panier;
import com.souklemdina.entities.Produit;
import com.souklemdina.interfaces.IPanierServices;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hatem
 */
public class PanierServices implements IPanierServices, Serializable {

    public Boolean prodExistant(FosUser user, Produit pr)
    {
        ArrayList prodExistant = new ArrayList();
        prodExistant = getPanier(user);
                Boolean exist = false;

        for (int j = 0; j < prodExistant.size(); j++) {
            if (prodExistant.get(j).toString().startsWith(pr.getId().toString() + " ")) {
                exist = true;
            }
        }
        return exist;
    }
    
    @Override
    public void ajouterPanier(FosUser user, Produit pr) {
        Integer qte = 1;
        String fileName = user.getId() + ".dat";

        ArrayList<String> prod = new ArrayList<String>();
        prod.add(pr.getId().toString() + " " + qte);

        ArrayList prodExistant = new ArrayList();

        prodExistant = getPanier(user);
        Boolean exist = false;
        for (int j = 0; j < prodExistant.size(); j++) {
            if (prodExistant.get(j).toString().startsWith(pr.getId().toString() + " ")) {
                exist = true;
            }
        }
        if (!exist) {
            try {
                FileWriter fw = new FileWriter(fileName, true);
                Writer output = new BufferedWriter(fw);
                int sz = prod.size();
                for (int i = 0; i < sz; i++) {
                    output.write(prod.get(i).toString() + "\n");
                }
                output.close();
            } catch (IOException ex) {
                Logger.getLogger(PanierServices.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }

    @Override
    public void supprimerProduitPanier(FosUser user, Integer pan) {
        String fileName = user.getId() + ".dat";

        ArrayList prod = new ArrayList();
        prod = getPanier(user);
        for (int i = 0; i < prod.size(); i++) {
            if (prod.get(i).toString().startsWith(String.valueOf(pan))) {
                prod.remove(i);

            }
        }

        try {
            deletefile(user);
            FileWriter fw = new FileWriter(fileName, true);
            Writer output = new BufferedWriter(fw);

            for (int j = 0; j < prod.size(); j++) {
                output.write(prod.get(j).toString() + "\n");
            }
            output.close();
        } catch (IOException ex) {
            Logger.getLogger(PanierServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deletefile(FosUser user) {
        String fileName = user.getId() + ".dat";

        Path p = Paths.get(fileName);
        try {
            Files.delete(p);
        } catch (IOException ex) {
            Logger.getLogger(PanierServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void modifierProduitPanier(FosUser user, Panier pan) {
        String fileName = user.getId() + ".dat";

        ArrayList prod = new ArrayList();
        prod = getPanier(user);
        String prodMod = (String) (String.valueOf(pan.getId())+ " " + String.valueOf(pan.getQauntiteCommander()));
        for (int i = 0; i < prod.size(); i++) {
            if (prod.get(i).toString().startsWith(String.valueOf(pan.getId()))) {

                prod.set(i, prodMod);

            }
            try {
                deletefile(user);
                FileWriter fw = new FileWriter(fileName, true);
                Writer output = new BufferedWriter(fw);

                for (int j = 0; j < prod.size(); j++) {
                    output.write(prod.get(j).toString() + "\n");
                }
                output.close();
            } catch (IOException ex) {
                Logger.getLogger(PanierServices.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @Override
    public ArrayList getPanier(FosUser user) {
        String fileName = user.getId() + ".dat";
        String line;
        ArrayList prod = new ArrayList();

        try {
            BufferedReader input = new BufferedReader(new FileReader(fileName));
            if (!input.ready()) {
                throw new IOException();
            }
            while ((line = input.readLine()) != null) {
                prod.add(line);
            }
            input.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Fichier inexistant");
        } catch (IOException ex) {
            System.out.println("Vous n'avez aucun produit dans votre panier");
        }
      
        return prod;
    }

    @Override
    public ArrayList<Panier> getPanierRetourListPanier(FosUser user) {
        String fileName = user.getId() + ".dat";
        String line;
        Produit prod = new Produit();
        ProduitServices pr = new ProduitServices();
        ArrayList<Panier> lstPanier = new ArrayList<>();

        try {
            BufferedReader input = new BufferedReader(new FileReader(fileName));
            if (!input.ready()) {
                throw new IOException();
            }
            while ((line = input.readLine()) != null) {
                String[] temp;
                Integer idProd = 0;
                Integer quantite = 0;
                String delimiter = " ";

                temp = line.split(delimiter);
                idProd = Integer.parseInt(temp[0]);

                quantite = Integer.parseInt(temp[1]);
                    prod = pr.getProduitById(idProd);
                if ( pr.getProduitById(idProd).getId()==idProd) {
                            Panier panier = new Panier();

                    panier.setId(prod.getId());
                    panier.setLibelle(prod.getLibelle());
                    panier.setDescription(prod.getDescription());
                    panier.setQuqntite(prod.getQuqntite());
                    panier.setPromotion(prod.getPromotion());
                    panier.setNbSignal(prod.getNbSignal());
                    panier.setType(prod.getType());
                    panier.setCategorie(prod.getCategorie());
                    panier.setPrix(prod.getPrix()*quantite);
                    panier.setUpdatedAt(prod.getUpdatedAt());
                    panier.setImage(prod.getImage());
                    panier.setQauntiteCommander(quantite);

                    lstPanier.add(panier);
                    
                }
                                    

            }
            input.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Fichier inexistant");
        } catch (IOException ex) {
            System.out.println("Vous n'avez aucun produit dans votre panier");
        }
      
        return lstPanier;
    }

}


/* String fileName = user.getId()+".bin";
 ArrayList<Produit> arraylist= new ArrayList<Produit>();
 try
 {
 FileInputStream fis = new FileInputStream(fileName);
 ObjectInputStream ois = new ObjectInputStream(fis);
 arraylist = (ArrayList) ois.readObject();
 //System.out.println(arraylist);
 ois.close();
 fis.close();
 }catch(IOException ioe){
 ioe.printStackTrace();
 return;
 }catch(ClassNotFoundException c){
 System.out.println("Class not found");
 c.printStackTrace();
 return;
 }
 for(Produit tmp: arraylist){
 System.out.println(tmp);
 }
 for(int i=0; i<arraylist.size(); i++) {
 System.out.println(arraylist.get(i)); 
 }*/
/*
 HashMap<Integer, ListProduitServices> pan = new HashMap<>();

 public void ajouterUser(FosUser user) {
 pan.put(user.getId(), new ListProduitServices());
 }
        
 @Override
 public void ajouterPanier(FosUser user, Produit pr) {

 if (pan.containsKey(user.getId())) {
 pan.get(user.getId()).ajouterProduit(pr);
 } else {
 ajouterUser(user);
 pan.get(user.getId()).ajouterProduit(pr);

 }
 }

 @Override
 public void supprimerProduitPanier(FosUser user,Produit pr) {
 pan.get(user.getId()).getListP().remove(pr);
 }

 @Override
 public void modifierProduitPanier(FosUser user,Produit prod, Integer qte) {
            
 Object prodID = pan.get(user.getId()).getListP().stream().findAny(prod.getId()); //8aaaaaltaaaaaaaaaaa
        
 pan.get(user.getId()).ModifierPanier(prod, qte, prodID);
 }
 @Override
 public void afficherPanier(FosUser user) {
 pan.get(user.getId()).getListP().forEach(e->System.out.println(e));
 }

 */
