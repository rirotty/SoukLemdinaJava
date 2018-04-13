/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.souklemdina.services;

import com.souklemdina.entities.Local;
import com.souklemdina.interfaces.ILocalServices;
import com.souklemdina.util.DataSource;
import com.souklemdina.util.SessionUser;
import com.souklemdina.util.UploadFile;
import java.io.File;
import java.io.FileInputStream;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 *
 * @author ACER
 */
public class LocalServices implements ILocalServices, Serializable {

    Connection cnx = DataSource.getInstance().getConnection();

    @Override
    public void ajoutLocal(Local l, FileInputStream fis, File file) {
        try {
            //idUser
            String req = "insert into local (type,description,superficie,prix,adresse,telephone,image,id_user) values (?,?,?,?,?,?,?,?)";
            PreparedStatement stmt = cnx.prepareStatement(req);
            stmt.setString(1, l.getType());
            stmt.setString(2, l.getDescription());
            stmt.setDouble(3, l.getSuperficie());
            stmt.setDouble(4, l.getPrix());
            stmt.setString(5, l.getAdresse());
            stmt.setString(6, l.getTelephone());
            String newName = UploadFile.uploadImage(file.getAbsolutePath(), null);
            stmt.setString(7,newName );
            stmt.setInt(8,SessionUser.getUser().getId());
            int rowInserted = stmt.executeUpdate();
            if (rowInserted > 0) {
                    Notifications notif = Notifications.create()
                                        .darkStyle().title("Succés")
                                        .text("Local ajouté avec succés")
                                        .position(Pos.TOP_LEFT)
                                        .hideAfter(Duration.seconds(30))
                                        //.graphic(new ImageView(img))
                                        .onAction(new EventHandler<ActionEvent>() {

                                            @Override
                                            public void handle(ActionEvent event) {
                                                System.out.println("c bn");
                                            }
                                        });
                                notif.showConfirm();
            }
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public ObservableList<Local> afficherLocal() {
        ObservableList<Local> LocalList = FXCollections.observableArrayList();
        try {
            Statement stmt = cnx.createStatement();
            ResultSet rs = stmt.executeQuery("select * from local");
            while (rs.next()) {
                LocalList.add(new Local(rs.getInt("id"), rs.getDouble("superficie"), rs.getDouble("prix"), rs.getString("type"), rs.getString("description"), rs.getString("adresse"), rs.getString("telephone")));
                //LocalList.add(new Local(rs.getInt("id"),rs.getDouble("superficie"),rs.getDouble("prix"),rs.getString("type")));
            }
            stmt.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return LocalList;
    }
    
    
        @Override
    public ObservableList<Local> afficherLocal2() {
        ObservableList<Local> LocalList = FXCollections.observableArrayList();
        try {
            Statement stmt = cnx.createStatement();
            ResultSet rs = stmt.executeQuery("select * from local");
            while (rs.next()) {
                LocalList.add(new Local(rs.getInt("id"),rs.getInt("nbSignal"), rs.getString("type"), rs.getString("adresse"), rs.getString("telephone")));
                //LocalList.add(new Local(rs.getInt("id"),rs.getDouble("superficie"),rs.getDouble("prix"),rs.getString("type")));
            }
            stmt.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return LocalList;
    }

    @Override
    public void modifierLocal(Local l) {

        try {
            String req = "update local set type=? ,description=? ,adresse=? ,telephone=? ,superficie=? ,prix=? where id=?";
            PreparedStatement stmt = cnx.prepareStatement(req);
            stmt.setString(1, l.getType());
            stmt.setString(2, l.getDescription());
            stmt.setString(3, l.getAdresse());
            stmt.setString(4, l.getTelephone());
            stmt.setDouble(5, l.getSuperficie());
            stmt.setDouble(6, l.getPrix());

            stmt.setInt(7, l.getId());

            int rowUpdated = stmt.executeUpdate();

            if (rowUpdated > 0) {
                     Notifications notif = Notifications.create()
                                        .darkStyle().title("Succés")
                                        .text("Mise à jour effectuée avec succés")
                                        .position(Pos.TOP_LEFT)
                                        .hideAfter(Duration.seconds(30))
                                        //.graphic(new ImageView(img))
                                        .onAction(new EventHandler<ActionEvent>() {

                                            @Override
                                            public void handle(ActionEvent event) {
                                                System.out.println("c bn");
                                            }
                                        });
                                notif.showConfirm();
            }

        } catch (SQLException ex) {

            System.err.println(ex.getMessage());
        }

    }

    @Override
    public void supprimerLocal(int id) {
       
        try {
          
            String req = "delete from local where id =?";
            PreparedStatement stmt = cnx.prepareStatement(req);
            stmt.setInt(1, id);
            int rowDeleted = stmt.executeUpdate();
            System.out.println(rowDeleted);
            
            if (rowDeleted > 0) {
                     Notifications notif = Notifications.create()
                                        .darkStyle().title("Succés")
                                        .text("Suppression effectuée avec succés")
                                        .position(Pos.TOP_LEFT)
                                        .hideAfter(Duration.seconds(30))
                                        //.graphic(new ImageView(img))
                                        .onAction(new EventHandler<ActionEvent>() {

                                            @Override
                                            public void handle(ActionEvent event) {
                                                System.out.println("c bn");
                                            }
                                        });
                                notif.showConfirm();
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public ObservableList<Local> rechercheLocal(String s) {
        ObservableList<Local> LocalList = FXCollections.observableArrayList();
        try {
            String req = "select * from local where type LIKE '%" + s + "%' "
                    + " UNION select * from local where superficie LIKE '%" + s + "%'"
                    + "  UNION select * from local where prix LIKE '%" + s + "%'";
            PreparedStatement stmt = cnx.prepareStatement(req);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                LocalList.add(new Local(rs.getInt("id"), rs.getDouble("superficie"), rs.getDouble("prix"), rs.getString("type"), rs.getString("description"), rs.getString("adresse"), rs.getString("telephone")));

            }
        } catch (SQLException ex) {
            Logger.getLogger(LocalServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return LocalList;
    }
    
      public ObservableList<Local> rechercheBack(String s) {
        ObservableList<Local> LocalList = FXCollections.observableArrayList();
        try {
            String req = "select * from local where id LIKE '%" + s + "%' " ;
            PreparedStatement stmt = cnx.prepareStatement(req);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                LocalList.add(new Local(rs.getInt("id"), rs.getInt("nbSignal"), rs.getString("type"), rs.getString("adresse"), rs.getString("telephone")));

            }
        } catch (SQLException ex) {
            Logger.getLogger(LocalServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return LocalList;
    }

    @Override
    public ObservableList<Local> afficherLocalUser(int idUser) {
        ObservableList<Local> LocalList = FXCollections.observableArrayList();
        try {
            String req = "select * from local where id_user=?";
            PreparedStatement stmt = cnx.prepareStatement(req);
            stmt.setInt(1, idUser);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                LocalList.add(new Local(rs.getInt("id"), rs.getDouble("superficie"), rs.getDouble("prix"), rs.getString("type"), rs.getString("description"), rs.getString("adresse"), rs.getString("telephone")));
                //LocalList.add(new Local(rs.getInt("id"),rs.getDouble("superficie"),rs.getDouble("prix"),rs.getString("type")));
            }
            stmt.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return LocalList;
    }
    
    
       @Override
    public Local afficherLocalUn(int id) {
         ObservableList<Local> LocalList = FXCollections.observableArrayList();
        try {
            String req = "select * from local where id=?";
            PreparedStatement stmt = cnx.prepareStatement(req);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                LocalList.add(new Local(rs.getInt("id"), rs.getDouble("superficie"), rs.getDouble("prix"), rs.getString("type"), rs.getString("description"), rs.getString("adresse"), rs.getString("telephone"), rs.getString("image")));
                //LocalList.add(new Local(rs.getInt("id"),rs.getDouble("superficie"),rs.getDouble("prix"),rs.getString("type")));
            }
            stmt.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
         Local l = LocalList.get(0);
        return l;
    }
    
          
    public Local afficherLocalSignaler(int id) {
         ObservableList<Local> LocalList = FXCollections.observableArrayList();
        try {
            String req = "select nbSignl from local where id=?";
            PreparedStatement stmt = cnx.prepareStatement(req);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                LocalList.add(new Local(rs.getInt("id"),rs.getInt("nbSignal")));
                //LocalList.add(new Local(rs.getInt("id"),rs.getDouble("superficie"),rs.getDouble("prix"),rs.getString("type")));
            }
            stmt.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
         Local l = LocalList.get(0);
        return l;
    }
    
    
        public void modifierLocalSignaler(int id, int nb) {

        try {
            String req = "update local set nbSignal = ? where id=?";
            PreparedStatement stmt = cnx.prepareStatement(req);
            stmt.setInt(1, nb);
            stmt.setInt(2, id);
            

            int rowUpdated = stmt.executeUpdate();

            if (rowUpdated > 0) {
                     Notifications notif = Notifications.create()
                                        .darkStyle().title("Succés")
                                        .text("Local signalé avec succés")
                                        .position(Pos.TOP_LEFT)
                                        .hideAfter(Duration.seconds(30))
                                        //.graphic(new ImageView(img))
                                        .onAction(new EventHandler<ActionEvent>() {

                                            @Override
                                            public void handle(ActionEvent event) {
                                                System.out.println("c bn");
                                            }
                                        });
                                notif.showConfirm();
            }

        } catch (SQLException ex) {

            System.err.println(ex.getMessage());
        }

    }

}
