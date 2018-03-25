/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.souklemdina.services;

import com.souklemdina.util.DataSource;
import com.souklemdina.entities.Workshop;
import com.souklemdina.interfaces.IWorkshopServices;
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
 * @author bhk
 */
public class WorkshopServices implements IWorkshopServices {

    Connection conn = DataSource.getInstance().getConnection();
    Statement stmt;

    @Override
    public void create(Workshop w) {
        try {
            String req = "INSERT INTO souklemdina (nomWorkshop,addresse,type,dateDebut,dateFin,nbPlace,prix,description) VALUES (?,?,?,?,?,?,?,?)";

            PreparedStatement st = conn.prepareStatement(req);
            st.setString(1, w.getNomWorkshop());
            st.setString(2, w.getAdresse());
            st.setString(3, w.getType());
            st.setDate(4, (java.sql.Date) w.getDateDebut());//downcast
            st.setDate(5, (java.sql.Date) w.getDateFin());
            st.setDouble(6, w.getPrix());
            st.setString(7, w.getDescription());
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(WorkshopServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Workshop> findAll() {
        ArrayList<Workshop> listWorks = new ArrayList<>();
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from souklemdina");
            while (rs.next()) {
                System.out.println(rs.getString(1) + " (" + rs.getString(2) + ")" + rs.getString(3) + ")" + (Date) rs.getDate(4) + ")" + (Date) rs.getDate(5) + ")" + rs.getDouble(6) + ")" + rs.getString(7) + ")");
                listWorks.add(
                        new Workshop(new Integer(rs.getInt(1)), rs.getString(2), rs.getString(3),
                                rs.getString(4), (java.util.Date)rs.getDate(5),
                                (java.util.Date)rs.getDate(6), rs.getInt(7), rs.getInt(8), rs.getString(9), (java.util.Date)rs.getDate(10)));
            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(WorkshopServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listWorks;

    }

    @Override
    public void update(Workshop w) {
        try {
            String req = "UPDATE `workshop` SET `nomWorkshop` = ?, `adresse`= ?, `dateDebut`= ? `dateFin`= ? `prix`= ? `description`= ? WHERE `id` = ?";
            PreparedStatement st = conn.prepareStatement(req);
            st.setString(1, w.getNomWorkshop());
            st.setString(2, w.getAdresse());
            st.setDate(3, (Date) w.getDateDebut());
            st.setDate(4, (Date) w.getDateFin());
            st.setDouble(5, w.getPrix());
            st.setString(6, w.getDescription());
            st.setInt(7, w.getId());

            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(WorkshopServices.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    @Override
    public void delete(Workshop w) {

        try {
            String req = "DELETE FROM `workshop` WHERE `workshop`.`id` = ? and  `workshop`.`nomWorkshop` = ?";
            PreparedStatement st = conn.prepareStatement(req);

            st.setInt(1, w.getId());
            st.setString(2, w.getNomWorkshop());
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(WorkshopServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
