/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.souklemdina.services;

import com.souklemdina.entities.Commande;
import com.souklemdina.entities.LigneDeCommande;
import com.souklemdina.interfaces.ILigneDeCommandeServices;
import com.souklemdina.util.DataSource;
import java.sql.Connection;
import java.sql.Date;
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
public class LigneDeCommandeServices implements ILigneDeCommandeServices {

    Connection conn = DataSource.getInstance().getConnection();
    Statement stmt;

    @Override
    public void create(LigneDeCommande lc) {
        try {
            String req = "INSERT INTO ligne_de_commande (id_produit,id_commande,prix_total,quntite,dateLivraison,adresse,adresse2,ville,code_postal,num_tel) VALUES (?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement st = conn.prepareStatement(req);
            st.setInt(1, lc.getIdProduit());
            st.setInt(2, lc.getIdCommande());
            st.setDouble(3, lc.getPrixTotal());
            st.setInt(4, lc.getQuntite());
            st.setDate(5, new Date(lc.getDateLivraison().getDate()));
            st.setString(6, lc.getAdresse());
            st.setString(7, lc.getAdresse2());
            st.setString(8, lc.getVille());
            st.setInt(9, lc.getCodePostal());
            st.setString(10, lc.getNumTel());

            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProduitServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ArrayList<LigneDeCommande> findAll() {
        ArrayList<LigneDeCommande> listLigneCommande = new ArrayList<>();
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from ligne_de_commande");
            while (rs.next()) {
                System.out.println(rs.getString(2) + " (" + rs.getString(3) + ")");
                listLigneCommande.add(new LigneDeCommande(
                        rs.getInt("id"),
                        rs.getInt("id_produit"),
                        rs.getInt("id_commande"),
                        rs.getDouble("prix_total"),
                        rs.getInt("quntite"),
                        rs.getDate("dateLivraison"),
                        rs.getString("adresse"),
                        rs.getString("adresse2"),
                        rs.getString("ville"),
                        rs.getInt("code_postal"),
                        rs.getString("num_tel")
                ));
            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(FosUserServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listLigneCommande;
    }

    @Override
    public void update(LigneDeCommande lc) {
        try {
            String req = "UPDATE `ligne_de_commande` SET `adresse`=?,`adresse2`=?,`ville`=?,`code_postal`=?,`num_tel`=? where id="+lc.getId();
            PreparedStatement st = conn.prepareStatement(req);
            st.setString(1, lc.getAdresse());
            st.setString(2, lc.getAdresse2());
            st.setString(3, lc.getVille());
            st.setInt(4, lc.getCodePostal());
            st.setString(5, lc.getNumTel());

            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FosUserServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(LigneDeCommande lc) {
        try {
            String req = "DELETE FROM `ligne_de_commande` WHERE `id` = ? and `id_commande` = ?";
            PreparedStatement st = conn.prepareStatement(req);
            st.setInt(1, lc.getId());
            st.setInt(2, lc.getIdCommande());
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FosUserServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<LigneDeCommande> getLignesDeCommandeByIdCommande(Integer p) {
        ArrayList<LigneDeCommande> lstLigneCommande = new ArrayList<>();
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from ligne_de_commande where id_commande =" + p);

            while (rs.next()) {
                LigneDeCommande lc = new LigneDeCommande();

                lc.setId(rs.getInt("id"));
                lc.setAdresse(rs.getString("adresse"));
                lc.setAdresse2(rs.getString("adresse2"));
                lc.setCodePostal(rs.getInt("code_postal"));
                lc.setDateLivraison(rs.getDate("dateLivraison"));
                lc.setIdCommande(rs.getInt("id_commande"));
                lc.setIdProduit(rs.getInt("id_produit"));
                lc.setNumTel(rs.getString("num_tel"));
                lc.setPrixTotal(rs.getDouble("prix_total"));
                lc.setQuntite(rs.getInt("quntite"));
                lc.setVille(rs.getString("ville"));
                lstLigneCommande.add(lc);
            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(FosUserServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lstLigneCommande;
    }
    public LigneDeCommande getLigneDeCommandeByIdCommande(Integer p) {
                        LigneDeCommande lc = new LigneDeCommande();

        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from ligne_de_commande where id_commande =" + p);

            while (rs.next()) {

                lc.setId(rs.getInt("id"));
                lc.setAdresse(rs.getString("adresse"));
                lc.setAdresse2(rs.getString("adresse2"));
                lc.setCodePostal(rs.getInt("code_postal"));
                lc.setDateLivraison(rs.getDate("dateLivraison"));
                lc.setIdCommande(rs.getInt("id_commande"));
                lc.setIdProduit(rs.getInt("id_produit"));
                lc.setNumTel(rs.getString("num_tel"));
                lc.setPrixTotal(rs.getDouble("prix_total"));
                lc.setQuntite(rs.getInt("quntite"));
                lc.setVille(rs.getString("ville"));
            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(FosUserServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lc;
    }
    public LigneDeCommande getLigneDeCommandeById(Integer p) {
                        LigneDeCommande lc = new LigneDeCommande();

        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from ligne_de_commande where id =" + p);

            while (rs.next()) {

                lc.setId(rs.getInt("id"));
                lc.setAdresse(rs.getString("adresse"));
                lc.setAdresse2(rs.getString("adresse2"));
                lc.setCodePostal(rs.getInt("code_postal"));
                lc.setDateLivraison(rs.getDate("dateLivraison"));
                lc.setIdCommande(rs.getInt("id_commande"));
                lc.setIdProduit(rs.getInt("id_produit"));
                lc.setNumTel(rs.getString("num_tel"));
                lc.setPrixTotal(rs.getDouble("prix_total"));
                lc.setQuntite(rs.getInt("quntite"));
                lc.setVille(rs.getString("ville"));
            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(FosUserServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lc;
    }
    public ArrayList<LigneDeCommande> getLigneDeCommandeByIdProduit(Integer p) {
ArrayList<LigneDeCommande> lstlc = new ArrayList<>();
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from ligne_de_commande where id_produit =" + p);

            while (rs.next()) {
                        LigneDeCommande lc = new LigneDeCommande();

                lc.setId(rs.getInt("id"));
                lc.setAdresse(rs.getString("adresse"));
                lc.setAdresse2(rs.getString("adresse2"));
                lc.setCodePostal(rs.getInt("code_postal"));
                lc.setDateLivraison(rs.getDate("dateLivraison"));
                lc.setIdCommande(rs.getInt("id_commande"));
                lc.setIdProduit(rs.getInt("id_produit"));
                lc.setNumTel(rs.getString("num_tel"));
                lc.setPrixTotal(rs.getDouble("prix_total"));
                lc.setQuntite(rs.getInt("quntite"));
                lc.setVille(rs.getString("ville"));
                
                lstlc.add(lc);
            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(FosUserServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lstlc;
    }
    
    
   
     public ArrayList<LigneDeCommande> getTop5IdProduit() {
        ArrayList<LigneDeCommande> listLigneCommande = new ArrayList<>();
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id,id_produit,COUNT(*) as p FROM ligne_de_commande GROUP by id_produit ORDER BY p DESC LIMIT 5");
            while (rs.next()) {
LigneDeCommande lc = new LigneDeCommande();

lc.setId(rs.getInt("id"));
lc.setIdProduit(rs.getInt("id_produit"));
                        listLigneCommande.add(lc);
                
            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(FosUserServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listLigneCommande;
    }

}
