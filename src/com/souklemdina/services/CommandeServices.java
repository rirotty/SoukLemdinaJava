/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.souklemdina.services;

import com.souklemdina.entities.Commande;
import com.souklemdina.entities.FosUser;
import com.souklemdina.interfaces.ICommandeServices;
import com.souklemdina.util.DataSource;
import com.souklemdina.util.LigneDeCommandeProduit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.TableColumn;

/**
 *
 * @author hatem
 */
public class CommandeServices implements ICommandeServices{

    Connection conn = DataSource.getInstance().getConnection();
    Statement stmt;
    
    @Override
    public void create(Commande c) {
try {
            String req = "INSERT INTO commande (id_user,dateCommande,date_max) VALUES (?,?,?)";

            PreparedStatement st = conn.prepareStatement(req);
            st.setInt(1, c.getIdUser());
            
            st.setTimestamp(2, (java.sql.Timestamp)c.getDateCommande());
            st.setTimestamp(3, (java.sql.Timestamp)c.getDateMax());
            
            
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProduitServices.class.getName()).log(Level.SEVERE, null, ex);
        }    }

    
    public ArrayList<Commande> findAlll() {
ArrayList<Commande> listCommande = new ArrayList<>();
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from commande");
            while (rs.next()) {
                //System.out.println(rs.getString(2) + " (" + rs.getString(3) + ")");
                listCommande.add(new Commande(
                        rs.getInt("id"),
                        rs.getInt("id_user"),
                        rs.getDate("dateCommande"),
                        rs.getDate("date_max")
               ));
            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(FosUserServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listCommande;      }
    @Override
    public ArrayList<Commande> findAll(Integer u) {
ArrayList<Commande> listCommande = new ArrayList<>();
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from commande where id_user="+u);
            while (rs.next()) {
                //System.out.println(rs.getString(2) + " (" + rs.getString(3) + ")");
                listCommande.add(new Commande(
                        rs.getInt("id"),
                        rs.getInt("id_user"),
                        rs.getDate("dateCommande"),
                        rs.getDate("date_max")
               ));
            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(FosUserServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listCommande;      }

    @Override
    public void update(Commande c) {
try {
            String req = "UPDATE `commande` SET `id_user`=?,`dateCommande`=?,`date_max`=?";
            PreparedStatement st = conn.prepareStatement(req);
             st.setInt(1, c.getIdUser());
            st.setDate(2,(java.sql.Date) c.getDateCommande());
            st.setDate(3,(java.sql.Date) c.getDateMax());
          
            
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FosUserServices.class.getName()).log(Level.SEVERE, null, ex);
        }     }

    @Override
    public void delete(Commande c) {
try {
            String req= "DELETE FROM `commande` WHERE `id` = ? and `id_user` = ?";
            PreparedStatement st = conn.prepareStatement(req);
            st.setInt(1, c.getId());
            st.setInt(2, c.getIdUser());
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FosUserServices.class.getName()).log(Level.SEVERE, null, ex);
        }    }
    public Integer getLastIdCommande()
    {
                Integer idComm = 0;

         try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select id from commande order by id desc limit 1");
            while (rs.next()) {
              idComm = rs.getInt("id");
               
            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(FosUserServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return idComm;      }
    
    public Commande getCommandeByIdCommande(Integer p) {
Commande c = new Commande();
try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from commande where id =" + p);

            while (rs.next()) {
                

                c.setId(rs.getInt("id"));
                c.setIdUser(rs.getInt("id_user"));
                c.setDateCommande(rs.getTimestamp("dateCommande"));
                c.setDateMax(rs.getTimestamp("date_max"));
               
              
            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(FosUserServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }
    public ArrayList<Commande> getCommandesByIdCommande(Integer p) {
        ArrayList<Commande> lstc = new ArrayList<>();
try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from commande where id =" + p);

            while (rs.next()) {
                
Commande c = new Commande();

                c.setId(rs.getInt("id"));
                c.setIdUser(rs.getInt("id_user"));
                c.setDateCommande(rs.getTimestamp("dateCommande"));
                c.setDateMax(rs.getTimestamp("date_max"));
               lstc.add(c);
              
            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(FosUserServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lstc;
    }
    public ArrayList<Commande> getCommandesByIdUser(Integer p) {
        ArrayList<Commande> lstc = new ArrayList<>();
try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from commande where id_user =" + p);

            while (rs.next()) {
                
Commande c = new Commande();

                c.setId(rs.getInt("id"));
                c.setIdUser(rs.getInt("id_user"));
                c.setDateCommande(rs.getTimestamp("dateCommande"));
                c.setDateMax(rs.getTimestamp("date_max"));
               lstc.add(c);
              
            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(FosUserServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lstc;
    }
    public Commande getCommandeByIdUser(Integer p) {
        Commande c = new Commande();

try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from commande where id_user =" + p);

            while (rs.next()) {
                

                c.setId(rs.getInt("id"));
                c.setIdUser(rs.getInt("id_user"));
                c.setDateCommande(rs.getTimestamp("dateCommande"));
                c.setDateMax(rs.getTimestamp("date_max"));
              
            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(FosUserServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }

    
    }

