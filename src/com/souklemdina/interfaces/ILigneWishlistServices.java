/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.souklemdina.interfaces;

import com.souklemdina.entities.LigneWishlist;
import java.util.List;

/**
 *
 * @author asus
 */
public interface ILigneWishlistServices {
    public void create(LigneWishlist lw);

    public List<LigneWishlist> findAll();


    public void delete(LigneWishlist lw);    
}
