/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.souklemdina.interfaces;

import com.souklemdina.entities.Commande;
import java.util.List;

/**
 *
 * @author asus
 */
public interface ICommandeServices {
     public void create(Commande c);

    public List<Commande> findAll();

    public void update(Commande c);

    public void delete(Commande c);   
}
