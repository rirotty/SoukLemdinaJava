/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.souklemdina.services;

import com.souklemdina.entities.Profile;
import com.souklemdina.util.DataSource;
import com.souklemdina.entities.FosUser;
import com.souklemdina.interfaces.IProfileServices;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ramyk
 */
public class ProfileServices implements IProfileServices{

    private Connection conn = DataSource.getInstance().getConnection();
    
   @Override
    public void create(Profile p) {
        String requete2 = "INSERT INTO profile (tagline,image,updated_at,about_me,id_user) VALUES (?,?,?,?,?)";
        PreparedStatement pst;
        try {
            pst = conn.prepareStatement(requete2);
            pst.setString(1, p.getTagline());
            pst.setString(2, p.getImage());
            pst.setDate(3, new Date(p.getUpdatedAt().getDate()));
            pst.setString(4, p.getAboutMe());
            pst.setInt(5, p.getIdUser());
            pst.executeUpdate();
            System.out.println("- New Profile added!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Profile> findAll() {
        PreparedStatement pst;
        String query = "select * from profile";
        List<Profile> l = new ArrayList<>();

        try {
            pst = conn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                l.add(new Profile(rs.getInt("id"), rs.getString("tagline"), rs.getString("image"), (Date)rs.getDate("updated_at"), rs.getString("about_me"), rs.getInt("id_user")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        l.stream().forEach(System.out::println);

        return l;
    }

    @Override
    public void update(Profile p) {
        String sql = "UPDATE profile SET tagline=?, image=?, updated_at=?, about_me=? WHERE id=?";
        PreparedStatement pst;
        int rowsUpdated = 0;
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, p.getTagline());
            pst.setString(2, p.getImage());
            pst.setDate(3, (Date)p.getUpdatedAt());
            pst.setString(4, p.getAboutMe());
            pst.setInt(5, p.getId());
            pst.executeUpdate();
            System.out.println("- New Profile added!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        if (rowsUpdated == 1) {
            System.out.println("- The post was updated successfully!");
        } else {
            System.err.println("ERROR: rowsUpdatedCount isn't normal -- " + rowsUpdated);
        }
    }

    @Override
    public void delete(Profile p) {
        String sql = "DELETE FROM profile WHERE id=? AND id_user=?";
        PreparedStatement statement;
        int rowsDeleted = 0;
        try {
            statement = conn.prepareStatement(sql);
            statement.setInt(1, p.getId());
            statement.setInt(2, p.getIdUser());
            rowsDeleted = statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        if (rowsDeleted == 1) {
            System.out.println("- The profile was deleted successfully!");
        } else {
            System.err.println("ERROR: rowsDeletedCount isn't normal -- " + rowsDeleted);
        }
    }
    
}
