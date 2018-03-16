/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.souklemdina.services;

import com.souklemdina.util.DataSource;
import com.souklemdina.entities.FosUser;
import com.souklemdina.entities.Post;
import com.souklemdina.interfaces.IPostServices;
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
public class PostServices implements IPostServices {

    private Connection conn = DataSource.getInstance().getConnection();

    @Override
    public void create(Post p) {
        String requete2 = "INSERT INTO post (date,texte,titre,image,update_at,id_user) VALUES (?,?,?,?,?,?)";
        PreparedStatement pst;
        try {
            pst = conn.prepareStatement(requete2);
            pst.setDate(1, (Date) p.getDate());
            pst.setString(2, p.getTexte());
            pst.setString(3, p.getTitre());
            pst.setString(4, p.getImage());
            pst.setDate(5, (Date) p.getUpdatedAt());
            pst.setInt(6, p.getIdUser().getId());
            pst.executeUpdate();
            System.out.println("- New Post added!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Post> findAll() {
        PreparedStatement pst;
        String query = "select * from post";
        List<Post> l = new ArrayList<>();

        try {
            pst = conn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                l.add(new Post(rs.getInt("id"), (Date)rs.getDate("date"), rs.getString("texte"), rs.getString("titre"), rs.getString("image"), (Date)rs.getDate("updated_at"), (FosUser)rs.getObject("id_user")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        l.stream().forEach(System.out::println);

        return l;
    }

    @Override
    public void update(Post p) {
        String sql = "UPDATE post SET date=?, texte=?, titre=?, image=?, updated_at=? WHERE id=?";
        PreparedStatement statement;
        int rowsUpdated = 0;
        try {
            statement = conn.prepareStatement(sql);
            statement.setDate(1, (Date) p.getDate());
            statement.setString(2, p.getTexte());
            statement.setString(3, p.getTitre());
            statement.setString(4, p.getImage());
            statement.setDate(5, (Date) p.getUpdatedAt());
            statement.setInt(6, p.getId());
            rowsUpdated = statement.executeUpdate();
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
    public void delete(Post p) {
        String sql = "DELETE FROM post WHERE id=? AND date=?";
        PreparedStatement statement;
        int rowsDeleted = 0;
        try {
            statement = conn.prepareStatement(sql);
            statement.setInt(1, p.getId());
            statement.setString(2, p.getTitre());
            rowsDeleted = statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        if (rowsDeleted == 1) {
            System.out.println("- The post was deleted successfully!");
        } else {
            System.err.println("ERROR: rowsDeletedCount isn't normal -- " + rowsDeleted);
        }
    }

}
