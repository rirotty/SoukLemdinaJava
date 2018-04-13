/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.souklemdina.interfaces;

import com.souklemdina.entities.LigneDeCommande;
import java.util.ArrayList;

import java.util.List;

/**
 *
 * @author asus
 */
public interface ILigneDeCommandeServices {
        public void create(LigneDeCommande lc);

    public ArrayList<LigneDeCommande> findAll();

    public void update(LigneDeCommande lc);

    public void delete(LigneDeCommande lc);
    
}
