/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.souklemdina.services;

import com.souklemdina.util.DataSource;
import com.souklemdina.entities.FosUser;
import com.souklemdina.interfaces.IFosUserServices;
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
public class FosUserServices implements IFosUserServices {

    Connection conn = DataSource.getInstance().getConnection();
    Statement stmt;

    @Override
    public void create(FosUser u) {
 try {
            String req = "INSERT INTO `fos_user`(`username`, `username_canonical`, `email`, `email_canonical`, `enabled`, `salt`, `password`, `last_login`, `confirmation_token`, `password_requested_at`, `roles`, `gender`, `firstname`, `lastname`, `rue`, `zip_code`, `ville`, `pays`, `phone`, `nbsignal`, `datenaiss`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement st = conn.prepareStatement(req);
            st.setString(1, u.getUsername());
            st.setString(2, u.getUsernameCanonical());
            st.setString(3, u.getEmail());
            st.setString(4, u.getEmailCanonical());
            st.setInt(5,1);
            st.setString(6, u.getSalt());
            st.setString(7, u.getPassword());
            st.setDate(8, (java.sql.Date) u.getLastLogin());
            st.setString(9, u.getConfirmationToken());
            st.setDate(10, (java.sql.Date) u.getPasswordRequestedAt());
            st.setString(11, "a:1:{i:0;s:9:\"ROLE_User\";}");
            st.setString(12, u.getGender());
            st.setString(13, u.getFirstname());
            st.setString(14, u.getLastname());
            st.setString(15, u.getRue());
            st.setInt(16, u.getZipCode());
            st.setString(17, u.getVille());
            st.setString(18, u.getPays());
            st.setString(19, u.getPhone());
            st.setInt(20, u.getNbsignal());
            st.setDate(21, (java.sql.Date) u.getDatenaiss());

            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FosUserServices.class.getName()).log(Level.SEVERE, null, ex);
        }    }

    @Override
    public List<FosUser> findAll() {
ArrayList<FosUser> listUsers = new ArrayList<>();
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from fos_user");
            while (rs.next()) {
                System.out.println(rs.getString(2) + " (" + rs.getString(3) + ")");
                listUsers.add(new FosUser(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                rs.getString(4),
                rs.getString(5),
                rs.getBoolean(6),
                rs.getString(7),
                rs.getString(8),
                rs.getDate(9),
                rs.getString(10),
                rs.getDate(11),
                rs.getString(12),
                rs.getString(13),
                rs.getString(14),
                rs.getString(15),
                rs.getString(16),
                rs.getInt(17),
                rs.getString(18),
                rs.getString(19),
                rs.getString(20),
                rs.getInt(21),
                rs.getDate(22)));
            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(FosUserServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listUsers;    }

    @Override
    public void update(FosUser u) {
try {
            String req = "UPDATE `fos_user` SET `username`=?,`username_canonical`=?,`email`=?,`email_canonical`=?,`enabled`=?,`salt`=?,`password`=?,`last_login`=?,`confirmation_token`=?,`password_requested_at`=?,`roles`=?,`gender`=?,`firstname`=?,`lastname`=?,`rue`=?,`zip_code`=?,`ville`=?,`pays`=?,`phone`=?,`nbsignal`=?,`datenaiss`=? WHERE 1 WHERE `id` = ?";
            PreparedStatement st = conn.prepareStatement(req);
             st.setString(1, u.getUsername());
            st.setString(2, u.getUsernameCanonical());
            st.setString(3, u.getEmail());
            st.setString(4, u.getEmailCanonical());
            st.setInt(5,1);
            st.setString(6, u.getSalt());
            st.setString(7, u.getPassword());
            st.setDate(8, (java.sql.Date) u.getLastLogin());
            st.setString(9, u.getConfirmationToken());
            st.setDate(10, (java.sql.Date) u.getPasswordRequestedAt());
            st.setString(11, "a:1:{i:0;s:9:\"ROLE_User\";}");
            st.setString(12, u.getGender());
            st.setString(13, u.getFirstname());
            st.setString(14, u.getLastname());
            st.setString(15, u.getRue());
            st.setInt(16, u.getZipCode());
            st.setString(17, u.getVille());
            st.setString(18, u.getPays());
            st.setString(19, u.getPhone());
            st.setInt(20, u.getNbsignal());
            st.setDate(21, (java.sql.Date) u.getDatenaiss());
            
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FosUserServices.class.getName()).log(Level.SEVERE, null, ex);
        }    }

    @Override
    public void delete(FosUser u) {
 try {
            String req= "DELETE FROM `etudiant` WHERE `etudiant`.`id` = ? and `etudiant`.`email` = ?";
            PreparedStatement st = conn.prepareStatement(req);
            st.setInt(1, u.getId());
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FosUserServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
