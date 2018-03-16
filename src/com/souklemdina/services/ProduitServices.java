/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.souklemdina.services;

import com.souklemdina.util.DataSource;
import com.souklemdina.entities.Produit;
import com.souklemdina.interfaces.IProduitService;
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
 * @author asus
 */
public class ProduitServices implements IProduitService {
    
    Connection conn = DataSource.getInstance().getConnection();
    Statement stmt;

    @Override
    public void create(Produit p) {
 try {
            String req = "INSERT INTO produit (id_user,libelle,quqtite,promotion,type,categorie,prix,updated_at,image) VALUES (?,?,?,?,?,?,?,?,?)";

            PreparedStatement st = conn.prepareStatement(req);
            st.setInt(1, p.getIdUser().getId());
            st.setString(2, p.getLibelle());
            st.setInt(3, p.getQuqntite());
            st.setInt(4, p.getPromotion());
            st.setString(5, p.getType());
            st.setString(6, p.getCategorie());
            st.setDouble(7, p.getPrix());
            st.setDate(8,(java.sql.Date)p.getUpdatedAt());
            st.setString(9, p.getImage());
            
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProduitServices.class.getName()).log(Level.SEVERE, null, ex);
        }    }

    @Override
    public List<Produit> findAll() {
        ArrayList<Produit> listProduits = new ArrayList<>();
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from produit");
            while (rs.next()) {
                System.out.println(rs.getString(2) + " " + rs.getString(3) + " " + rs.getInt(4) + " " + rs.getInt(5)+ " " + rs.getString(6) + " "+rs.getString(7)+" "+rs.getDouble(8)+" "+rs.getDate(9));
                listProduits.add(new Produit(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getDouble(8),
                        rs.getDate(9)));
            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProduitServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listProduits;
    }

    @Override
    public void update(Produit p) {
try {
            String req = "UPDATE produit SET libelle=?,quqtite=?,promotion=?,type=?,categorie=?,prix=?,updated_at=?,image=? WHERE id = ?";
            PreparedStatement st = conn.prepareStatement(req);
            st.setString(1, p.getLibelle());
            st.setInt(2, p.getQuqntite());
            st.setInt(3, p.getPromotion());
            st.setString(4, p.getType());
            st.setString(5, p.getCategorie());
            st.setDouble(6, p.getPrix());
            st.setDate(7,(java.sql.Date)p.getUpdatedAt());
            st.setString(8, p.getImage());
            
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProduitServices.class.getName()).log(Level.SEVERE, null, ex);
        }    }

    @Override
    public void delete(Produit p) {
      try {
            String req= "DELETE FROM `produit` WHERE `etudiant`.`id` = ? ";
            PreparedStatement st = conn.prepareStatement(req);
            st.setInt(1, p.getId());
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProduitServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
