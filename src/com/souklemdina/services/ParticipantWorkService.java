/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.souklemdina.services;

import com.souklemdina.entities.FosUser;
import com.souklemdina.entities.ParticipantWork;
import com.souklemdina.entities.Workshop;
import com.souklemdina.util.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;


/**
 *
 * @author lenovo
 */
public class ParticipantWorkService {
    
      Connection conn = DataSource.getInstance().getConnection();
    Statement stmt;
    
    public void participer(Workshop w, FosUser u) {

        String req = "INSERT INTO participant_work (id_user,id_workshop) VALUES (?,?)";
        PreparedStatement st;
        try {
            st = conn.prepareStatement(req);
            st.setInt(1, u.getId());
            st.setInt(2, w.getId());
            
            st.executeUpdate();
            System.out.println("-  added!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
     public void pasParticiper(Workshop w, FosUser u) {

        try {
            String req = "DELETE FROM participant_work WHERE `id_workshop` = ? and  `id_user` = ?";
            PreparedStatement st = conn.prepareStatement(req);

            st.setInt(1, w.getId());
            st.setInt(2, u.getId());
            System.out.println(st.executeUpdate());;
        } catch (SQLException ex) {
            Logger.getLogger(WorkshopServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<ParticipantWork> getWorkshopById(Integer p) {
        ArrayList<ParticipantWork> lstPWo = new ArrayList<>();
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from participant_work where id_Workshop =" + p);

            while (rs.next()) {
                ParticipantWork lc = new ParticipantWork();

                lc.setId(rs.getInt("id"));
                lc.setIdUser(rs.getInt("id_user"));
                lc.setIdWorkshop(rs.getInt("id_workshop"));
               
                lstPWo.add(lc);
            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(FosUserServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lstPWo;
    }
     public ArrayList<ParticipantWork> findAllPar(Integer id) {
        ArrayList<ParticipantWork> lstPar = new ArrayList<>();
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from participant_work where id_user =" + id);

            while (rs.next()) {
                ParticipantWork lc = new ParticipantWork();

                lc.setId(rs.getInt("id"));
                lc.setIdUser(rs.getInt("id_user"));
                lc.setIdWorkshop(rs.getInt("id_workshop"));
               
                lstPar.add(lc);
            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(FosUserServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lstPar;
    }
    
     public void delete(int id) {
        try {
            String req = "DELETE FROM `participant_work` WHERE `id` = ? ";
            PreparedStatement st = conn.prepareStatement(req);
            st.setInt(1, id);
            System.out.println(st.executeUpdate());
        } catch (SQLException ex) {
            Logger.getLogger(WorkshopServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//     public void participTotal(){
//         float l;
//        String req= "SELECT COUNT(*) FROM `participant_work`";
//        PreparedStatement st = conn.prepareStatement(req);
//            st.setInt(1, id);
//          st.execute();
//     
//     }
      public ObservableList<PieChart.Data> participParWork() {
      ArrayList<PieChart.Data> list = new ArrayList<PieChart.Data>();
      try {
      PreparedStatement st = conn.prepareStatement("SELECT w.nomWorkshop,COUNT( * ) from participant_work p ,workshop w WHERE w.id = p.id_workshop GROUP BY nomWorkshop");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new PieChart.Data(rs.getString(1), rs.getInt(2)));
            }
            ObservableList<PieChart.Data> observableList;
            observableList = FXCollections.observableList(list);
//            System.out.println("ici" + observableList.size());
for (PieChart.Data data : observableList) {System.out.println("nadiaaaaa ::"+data);
                
            }
            return observableList;

        } catch (SQLException ex) {
            Logger.getLogger(ParticipantWorkService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
     
//SELECT f.username,COUNT( * ) from fos_user f ,workshop w WHERE f.id = w.id_user GROUP BY username
         public ObservableList<PieChart.Data> UsersActif() {
      ArrayList<PieChart.Data> list = new ArrayList<PieChart.Data>();
      try {
      PreparedStatement st = conn.prepareStatement("SELECT f.username,COUNT( * ) from fos_user f ,workshop w WHERE f.id = w.id_user GROUP BY username");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new PieChart.Data(rs.getString(1), rs.getInt(2)));
            }
            ObservableList<PieChart.Data> observableList;
            observableList = FXCollections.observableList(list);
//            System.out.println("ici" + observableList.size());
for (PieChart.Data data : observableList) {System.out.println("nadiaaaaa ::"+data);
                
            }
            return observableList;

        } catch (SQLException ex) {
            Logger.getLogger(ParticipantWorkService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }}
         
//                 public ArrayList<FosUser> getPartGroupByWOrk(Integer id) {
//        ArrayList<FosUser> lstPar = new ArrayList<>();
//        try {
//            stmt = conn.createStatement();
//            ResultSet rs = stmt.executeQuery("SELECT f.username, f.email, f.phone, p.id_workshop FROM participant_work p, fos_user f WHERE f.getId() = p.getIdUser() GROUP BY id_workshop");
//
//            while (rs.next()) {
//                FosUser lc = new FosUser();
//
//                lc.setId(rs.getInt("id"));
//                lc.sers.getInt("id_user"));
//                lc.setIdWorkshop(rs.getInt("id_workshop"));
//               
//                lstPar.add(lc);
//            }
//            stmt.close();
//        } catch (SQLException ex) {
//            Logger.getLogger(FosUserServices.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return lstPar;
//    }
//}
