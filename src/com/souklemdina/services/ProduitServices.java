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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author asus
 */
public class ProduitServices implements IProduitService {

    Connection conn = DataSource.getInstance().getConnection();
    Statement stmt;

//    EntityManagerFactory emf = Persistence.createEntityManagerFactory("SoukLemdinaPU");
//    EntityManager em=emf.createEntityManager();
//    em.getTransaction().begin();
    @Override
    public void create(Produit p) {
        try {
            String req = "INSERT INTO produit (id_user,libelle,description,quqntite,promotion,nbsignal,type,categorie,prix,updated_at,image) VALUES (?,?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement st = conn.prepareStatement(req);
            st.setInt(1, p.getIdUser());
            st.setString(2, p.getLibelle());
            st.setString(3, p.getDescription());
            st.setInt(4, p.getQuqntite());
            st.setInt(5, p.getPromotion());
            st.setInt(6, 0);
            st.setString(7, p.getType());
            st.setString(8, p.getCategorie());
            st.setDouble(9, p.getPrix());
            st.setTimestamp(10, p.getUpdatedAt());
            st.setString(11, p.getImage());

            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProduitServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ObservableList<Produit> findAll() {
        ObservableList<Produit> listProduits = FXCollections.observableArrayList();
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from produit");
            while (rs.next()) {
                System.out.println(rs.getInt("id") + " " + rs.getInt("id_user") + " " + rs.getString("libelle") + " " + rs.getString("description") + " " + rs.getInt("quqntite") + " " + rs.getInt("promotion") + " " + rs.getInt("nbsignal") + " " + rs.getString("type") + " " + rs.getString("categorie") + " " + rs.getDouble("prix") + " " + rs.getTimestamp("updated_at") + " " + rs.getString("image"));
                listProduits.add(new Produit(
                        rs.getInt("id"),
                        rs.getString("libelle"),
                        rs.getString("description"),
                        rs.getInt("quqntite"),
                        rs.getInt("promotion"),
                        rs.getInt("nbsignal"),
                        rs.getString("type"),
                        rs.getString("categorie"),
                        rs.getDouble("prix"),
                        rs.getTimestamp("updated_at"),
                        rs.getString("image"),
                        rs.getInt("id_user")
                ));
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
            String req = "UPDATE produit SET libelle=?,description=?,quqntite=?,promotion=?,type=?,categorie=?,prix=?,updated_at=?,image=? WHERE id = ?";
            PreparedStatement st = conn.prepareStatement(req);
            st.setString(1, p.getLibelle());
            st.setString(2, p.getDescription());
            st.setInt(3, p.getQuqntite());
            st.setInt(4, p.getPromotion());
            st.setString(5, p.getType());
            st.setString(6, p.getCategorie());
            st.setDouble(7, p.getPrix());
            st.setTimestamp(8, p.getUpdatedAt());
            st.setString(9, p.getImage());
            st.setInt(10, p.getId());

            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProduitServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Produit p) {
        try {
            String req = "DELETE FROM `produit` WHERE `produit`.`id` = ? ";
            PreparedStatement st = conn.prepareStatement(req);
            st.setInt(1, p.getId());
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProduitServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ObservableList<Produit> Search(String name) {
        ObservableList<Produit> listProduits = FXCollections.observableArrayList();
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from produit where libelle like '%" + name + "%' ");
            while (rs.next()) {
                System.out.println(rs.getInt("id") + " " + rs.getInt("id_user") + " " + rs.getString("libelle") + " " + rs.getString("description") + " " + rs.getInt("quqntite") + " " + rs.getInt("promotion") + " " + rs.getInt("nbsignal") + " " + rs.getString("type") + " " + rs.getString("categorie") + " " + rs.getDouble("prix") + " " + rs.getTimestamp("updated_at") + " " + rs.getString("image"));
                listProduits.add(new Produit(
                        rs.getInt("id"),
                        rs.getString("libelle"),
                        rs.getString("description"),
                        rs.getInt("quqntite"),
                        rs.getInt("promotion"),
                        rs.getInt("nbsignal"),
                        rs.getString("type"),
                        rs.getString("categorie"),
                        rs.getDouble("prix"),
                        rs.getTimestamp("updated_at"),
                        rs.getString("image"),
                        rs.getInt("id_user")
                ));
            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProduitServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listProduits;
    }

    @Override
    public Produit findById(Integer id) {
        Produit p = null;
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from produit where id = " + id);
            if (rs.next()) {
                p = new Produit(
                        rs.getInt("id"),
                        rs.getString("libelle"),
                        rs.getString("description"),
                        rs.getInt("quqntite"),
                        rs.getInt("promotion"),
                        rs.getInt("nbsignal"),
                        rs.getString("type"),
                        rs.getString("categorie"),
                        rs.getDouble("prix"),
                        rs.getTimestamp("updated_at"),
                        rs.getString("image"),
                        rs.getInt("id_user")
                );
            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProduitServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }

    @Override
    public ObservableList<Produit> findByIdUser(Integer idUser) {
        ObservableList<Produit> listProduits = FXCollections.observableArrayList();
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from produit where id_user = " + idUser);
            while (rs.next()) {
                System.out.println(rs.getInt("id") + " " + rs.getInt("id_user") + " " + rs.getString("libelle") + " " + rs.getString("description") + " " + rs.getInt("quqntite") + " " + rs.getInt("promotion") + " " + rs.getInt("nbsignal") + " " + rs.getString("type") + " " + rs.getString("categorie") + " " + rs.getDouble("prix") + " " + rs.getTimestamp("updated_at") + " " + rs.getString("image"));
                listProduits.add(new Produit(
                        rs.getInt("id"),
                        rs.getString("libelle"),
                        rs.getString("description"),
                        rs.getInt("quqntite"),
                        rs.getInt("promotion"),
                        rs.getInt("nbsignal"),
                        rs.getString("type"),
                        rs.getString("categorie"),
                        rs.getDouble("prix"),
                        rs.getTimestamp("updated_at"),
                        rs.getString("image"),
                        rs.getInt("id_user")
                ));
            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProduitServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listProduits;
    }

    public Produit getProduitById(Integer p) {
        Produit prod = new Produit();
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from produit where produit.id =" + p);

            while (rs.next()) {

                prod.setId(rs.getInt("id"));
                //prod.setIdUser(rs.getInt("id_user"));
                prod.setLibelle(rs.getString("libelle"));
                prod.setDescription(rs.getString("description"));
                prod.setQuqntite(rs.getInt("quqntite"));
                prod.setPromotion(rs.getInt("promotion"));
                prod.setNbSignal(rs.getInt("nbSignal"));
                prod.setType(rs.getString("type"));
                prod.setCategorie(rs.getString("categorie"));
                prod.setPrix(rs.getDouble("prix"));
                prod.setUpdatedAt(rs.getTimestamp("updated_at"));
                prod.setImage(rs.getString("image"));

            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(FosUserServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return prod;
    }

    public ArrayList<Produit> getProduitsById(Integer p) {
        ArrayList<Produit> lstP = new ArrayList<>();
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from produit where id =" + p);

            while (rs.next()) {
                Produit prod = new Produit();

                prod.setId(rs.getInt("id"));
                //prod.setIdUser(rs.getInt("id_user"));
                prod.setLibelle(rs.getString("libelle"));
                prod.setDescription(rs.getString("description"));
                prod.setQuqntite(rs.getInt("quqntite"));
                prod.setPromotion(rs.getInt("promotion"));
                prod.setNbSignal(rs.getInt("nbSignal"));
                prod.setType(rs.getString("type"));
                prod.setCategorie(rs.getString("categorie"));
                prod.setPrix(rs.getDouble("prix"));
                prod.setUpdatedAt(rs.getTimestamp("updated_at"));
                prod.setImage(rs.getString("image"));
                lstP.add(prod);
            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(FosUserServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lstP;
    }

}
