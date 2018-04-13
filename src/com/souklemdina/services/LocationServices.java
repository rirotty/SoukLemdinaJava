/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.souklemdina.services;

import com.souklemdina.entities.Location;

import com.souklemdina.interfaces.ILocationServices;
import com.souklemdina.util.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
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
public class LocationServices implements ILocationServices{

    Connection cnx  = DataSource.getInstance().getConnection();
    
    @Override
    public void ajoutLocation(Location l) {
             try {
            //idUser
            String req = "insert into location (id_local,dateDebutLocation,dateFinLocation,id_user) values (?,?,?,?)";
            PreparedStatement stmt = cnx.prepareStatement(req);
            stmt.setInt(1, l.getIdLocal());
            stmt.setDate(2, (java.sql.Date) l.getDateDebut());
            stmt.setDate(3, (java.sql.Date) l.getDateFin());
            stmt.setInt(4, l.getIdUser());
         stmt.executeUpdate();
   
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public ObservableList<Location> afficherLocation(int idLocal) { // + idUser
             ObservableList<Location> LocationList = FXCollections.observableArrayList();
        try{
                 String req="select * from location where id_local=?";
            PreparedStatement stmt = cnx.prepareStatement(req);
            stmt.setInt(1,idLocal);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next())
            {
              LocationList.add(new Location(rs.getDate("dateDebutLocation"),rs.getDate("dateFinLocation")));
            }
            stmt.close();
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
        }
        
        return LocationList; 
    }

    @Override
    public void supprimerLocation(int id) {
              try{
       String req = "delete from location where id_local =?  AND id_user = ?"; // +idUser
       PreparedStatement stmt = cnx.prepareStatement(req);
       stmt.setInt(1, id);
       stmt.setInt(2, 2);
       int rowDeleted = stmt.executeUpdate();
         if (rowDeleted>0)
     {
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
       }catch(SQLException ex){
           System.err.println(ex.getMessage());
       } 
    }
    
     public static Date parse(String value, DateFormat... formatters) {
    Date date = null;
    for (DateFormat formatter : formatters) {
      try {
        date = formatter.parse(value);
        break;
      } catch (ParseException e) {
      }
    }
    return date;
  }
    


    @Override
    public ObservableList<Location> afficherLocationUser(int idUser) {
        
                     ObservableList<Location> LocationList = FXCollections.observableArrayList();
        try{
                 String req="select * from location where id_user=?";
            PreparedStatement stmt = cnx.prepareStatement(req);
            stmt.setInt(1,idUser);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next())
            {
              LocationList.add(new Location(rs.getInt("id"),rs.getDate("dateDebutLocation"),rs.getDate("dateFinLocation"),rs.getInt("id_local")));
            }
            stmt.close();
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
        }
        
        return LocationList;
    }
       @Override
    public ObservableList<Location> afficherTout(int idLocal) {
        
                     ObservableList<Location> LocationList = FXCollections.observableArrayList();
        try{
                    String req = "select * from  location where id_user=? ";

            PreparedStatement stmt = cnx.prepareStatement(req);
            stmt.setInt(1,idLocal);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next())
            {
              LocationList.add(new Location(rs.getInt("id"),rs.getDate("dateDebutLocation"),rs.getDate("dateFinLocation")));
            }
            stmt.close();
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
        }
        
        return LocationList;
    }
    
}
