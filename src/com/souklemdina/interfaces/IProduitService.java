/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.souklemdina.interfaces;

import com.souklemdina.entities.Produit;
import java.util.List;

/**
 *
 * @author asus
 */
public interface IProduitService {
        public void create(Produit p);

    public List<Produit> findAll();
    
    public List<Produit> findByIdUser(Integer idUser);
    
    public Produit findById(Integer id);

    public void update(Produit p);

    public void delete(Produit p);
    
}
