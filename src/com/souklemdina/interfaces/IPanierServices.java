/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.souklemdina.interfaces;

import com.souklemdina.entities.FosUser;
import com.souklemdina.entities.Panier;
import com.souklemdina.entities.Produit;
import java.util.ArrayList;

/**
 *
 * @author hatem
 */
public interface IPanierServices {
           public void ajouterPanier(FosUser user,Produit pr);   
           public void supprimerProduitPanier(FosUser user,Integer pan);    
           public void modifierProduitPanier(FosUser user, Panier pan);    
           public ArrayList getPanier(FosUser user);    
           public ArrayList<Panier> getPanierRetourListPanier(FosUser user);
             }  


