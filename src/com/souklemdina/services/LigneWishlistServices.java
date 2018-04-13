/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.souklemdina.services;

import com.souklemdina.entities.Commande;
import com.souklemdina.entities.LigneWishlist;
import com.souklemdina.interfaces.ILigneWishlistServices;
import com.souklemdina.util.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hatem
 */
public class LigneWishlistServices implements ILigneWishlistServices{
 Connection conn = DataSource.getInstance().getConnection();
    Statement stmt;
    @Override
    public void create(LigneWishlist lw) {
try {
            String req = "INSERT INTO ligne_wishlist (idWishlist,idProduit) VALUES (?,?)";

            PreparedStatement st = conn.prepareStatement(req);
            st.setInt(1, lw.getIdWishlist());
            
            st.setInt(2, lw.getIdProduit());
            
            
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProduitServices.class.getName()).log(Level.SEVERE, null, ex);
        }       }

    @Override
    public List<LigneWishlist> findAll() {
ArrayList<LigneWishlist> listLigneWishlist = new ArrayList<>();
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from ligne_wishlist");
            while (rs.next()) {
                System.out.println(rs.getString(2) + " (" + rs.getString(3) + ")");
                listLigneWishlist.add(new LigneWishlist(
                        rs.getInt("id"),
                        rs.getInt("idWishlist"),
                        rs.getInt("idProduit")
                      
               ));
            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(FosUserServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listLigneWishlist;     }

    @Override
    public void delete(LigneWishlist lw) {
try {
            String req= "DELETE FROM `ligne_wishlist` WHERE `id` = ? and `idWishlist` = ?";
            PreparedStatement st = conn.prepareStatement(req);
            st.setInt(1, lw.getId());
            st.setInt(2, lw.getIdWishlist());
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FosUserServices.class.getName()).log(Level.SEVERE, null, ex);
        }       }
    
    public LigneWishlist getLigneWishlistById(Integer lw)
    {
        LigneWishlist LigneWishlistById = new LigneWishlist();
        try {
 stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from ligne_wishlist where ligne_wishlist.id ="+lw);
            while (rs.next()) {                 
                       LigneWishlistById.setId(rs.getInt("id"));
                        LigneWishlistById.setIdWishlist(rs.getInt("idWishlist"));
                        LigneWishlistById.setIdProduit(rs.getInt("idProduit"));
                 }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(FosUserServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return LigneWishlistById;     }
    
    public List<LigneWishlist> findAllLigneByIdWishList(Integer w) {
ArrayList<LigneWishlist> listLigneWishlist = new ArrayList<>();
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from ligne_wishlist where idWishlist =" +w);
            while (rs.next()) {
                LigneWishlist lw = new LigneWishlist();
               // System.out.println(rs.getString(2) + " (" + rs.getString(3) + ")");
               lw.setId(rs.getInt("id"));
               lw.setIdProduit(rs.getInt("idProduit"));
               lw.setIdWishlist(rs.getInt("idWishlist"));
               listLigneWishlist.add(lw);
            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(FosUserServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listLigneWishlist;     }
            }
    
