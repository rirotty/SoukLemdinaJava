/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.souklemdina.services;

import com.souklemdina.entities.Evenement;
import com.souklemdina.entities.FosUser;
import com.souklemdina.entities.ParticipantEvents;
import com.souklemdina.util.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author asus
 */
public class ParticipantsEventsServices {
    Connection conn = DataSource.getInstance().getConnection();
    Statement stmt;
    
    public void participer(Evenement e, FosUser u) {

        String req = "INSERT INTO participant_events (id_user,id_evenement) VALUES (?,?)";
        PreparedStatement st;
        try {
            st = conn.prepareStatement(req);
            st.setInt(1, u.getId());
            st.setInt(2, e.getId());
            
            st.executeUpdate();
            System.out.println("participate");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
     public void pasParticiper(Evenement w, FosUser u) {

        try {
            String req = "DELETE FROM participant_events WHERE id_evenement = ? and  id_user = ?";
            PreparedStatement st = conn.prepareStatement(req);

            st.setInt(1, w.getId());
            st.setInt(2, u.getId());
            System.out.println(st.executeUpdate());;
        } catch (SQLException ex) {
            Logger.getLogger(WorkshopServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<ParticipantEvents> getEvById(Integer p) {
        ArrayList<ParticipantEvents> listPe = new ArrayList<>();
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from participant_events where id_evenement =" + p);

            while (rs.next()) {
                ParticipantEvents pe = new ParticipantEvents();

                pe.setId(rs.getInt("id"));
               pe.setIdUser(rs.getInt("id_user"));
                pe.setIdEvenement(rs.getInt("id_Evenement"));
               
                listPe.add(pe);
            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(FosUserServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listPe;
    }
}
