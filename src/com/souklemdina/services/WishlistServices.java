/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.souklemdina.services;

import com.souklemdina.entities.Wishlist;
import com.souklemdina.interfaces.IWishlistServices;
import com.souklemdina.util.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hatem
 */
public class WishlistServices implements IWishlistServices{
Connection conn = DataSource.getInstance().getConnection();
    Statement stmt;
    @Override
    public void create(Wishlist w) {
try {
            String req = "INSERT INTO wishlist (idUser) VALUES (?)";

            PreparedStatement st = conn.prepareStatement(req);
            st.setInt(1, w.getIdUser());
            
            
            
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProduitServices.class.getName()).log(Level.SEVERE, null, ex);
        }      }
    
    public Wishlist getWishListById(Integer w)
    {
        Wishlist wish = new Wishlist();
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from wishlist where idUser =" + w);
           
                while (rs.next()) {
                    
                    wish.setId(rs.getInt("id"));
                    wish.setIdUser(rs.getInt("idUser"));
                   
                
            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(FosUserServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return wish;
    }
    
}
