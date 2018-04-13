/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.souklemdina.services;

import com.souklemdina.util.DataSource;
import com.souklemdina.entities.Post;
import com.souklemdina.interfaces.IPostServices;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ramyk
 */
public class PostServices implements IPostServices {

    private final Connection conn = DataSource.getInstance().getConnection();

    @Override
    public void create(Post p) {
        String requete2 = "INSERT INTO post (date,texte,titre,image,updated_at,id_user) VALUES (?,?,?,?,?,?)";
        PreparedStatement pst;
        try {
            pst = conn.prepareStatement(requete2);
            pst.setObject(1, p.getDate());
            pst.setString(2, p.getTexte());
            pst.setString(3, p.getTitre());
            pst.setString(4, p.getImage());
            pst.setObject(5, p.getUpdatedAt());
            pst.setInt(6, p.getIdUser());
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
                l.add(new Post(rs.getInt("id"), (Timestamp) rs.getObject("date"), rs.getString("texte"), rs.getString("titre"), rs.getString("image"), (Timestamp) rs.getObject("updated_at"), rs.getInt("id_user")));
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
            statement.setObject(1, p.getDate());
            statement.setString(2, p.getTexte());
            statement.setString(3, p.getTitre());
            statement.setString(4, p.getImage());
            statement.setObject(5, p.getUpdatedAt());
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

    @Override
    public void deleteById(Integer id) {
        String sql = "DELETE FROM post WHERE id=?";
        PreparedStatement statement;
        int rowsDeleted = 0;
        try {
            statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
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

    @Override
    public List<Post> findByIdUser(Integer idUser) {
        String sql = "SELECT * FROM post WHERE id_user=? ORDER BY date DESC";
        List<Post> l = new ArrayList<>();
        PreparedStatement statement;
        try {
            statement = conn.prepareStatement(sql);
            statement.setInt(1, idUser);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                l.add(new Post(rs.getInt("id"), rs.getTimestamp("date"), rs.getString("texte"), rs.getString("titre"), rs.getString("image"), rs.getTimestamp("updated_at"), rs.getInt("id_user")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return l.isEmpty() ? null : l;
    }

    @Override
    public Post findById(Integer id) {
        String sql = "SELECT * FROM post WHERE id=?";
        Post p = null;
        PreparedStatement statement;
        try {
            statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                p = new Post(rs.getInt("id"), rs.getTimestamp("date"), rs.getString("texte"), rs.getString("titre"), rs.getString("image"), rs.getTimestamp("updated_at"), rs.getInt("id_user"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return p;
    }

    @Override
    public List<Post> findAllFollowed(Integer idProfileFollower, Integer idUserFollower) {
        String sql = "SELECT * FROM post p WHERE p.id_user in ("
                + "SELECT pr.id_user FROM profile pr WHERE pr.id in ("
                + "SELECT f.id_profile_followed FROM follow f WHERE f.id_profile_follower = ?"
                + ")"
                + ") OR p.id_user = ?"
                + " ORDER BY p.date DESC";
        List<Post> l = new ArrayList<>();
        PreparedStatement statement;
        try {
            statement = conn.prepareStatement(sql);
            statement.setInt(1, idProfileFollower);
            statement.setInt(2, idUserFollower);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                l.add(new Post(rs.getInt("id"), rs.getTimestamp("date"), rs.getString("texte"), rs.getString("titre"), rs.getString("image"), rs.getTimestamp("updated_at"), rs.getInt("id_user")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return l.isEmpty() ? null : l;
    }

    @Override
    public void signaler(Post p) {
        String sql1 = "SELECT nb_signal from post where id = ? ";
        String sql2 = "UPDATE post SET nb_signal=? where id = ? ";
        PreparedStatement pst;
        Integer res = 0;
        try {
            pst = conn.prepareStatement(sql1);
            pst.setInt(1, p.getId());
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                res = rs.getInt("nb_signal");
                res++;
                System.out.println(res);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        try {
            pst = conn.prepareStatement(sql2);
            pst.setInt(1, res);
            pst.setInt(2, p.getId());
            pst.executeUpdate();
            System.out.println("- Signal added!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Post> getSignal() {
        String sql = "SELECT * FROM post ORDER BY nb_signal DESC limit 5";
        List<Post> l = new ArrayList<>();
        PreparedStatement statement;
        try {
            statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                l.add(new Post(rs.getInt("id"), rs.getTimestamp("date"), rs.getString("texte"), rs.getString("titre"), rs.getInt("nb_signal"),rs.getString("image"), rs.getTimestamp("updated_at"), rs.getInt("id_user")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return l.isEmpty() ? null : l;
    }

    @Override
    public void unSignaler(Post p) {
        String sql1 = "SELECT nb_signal from post where id = ? ";
        String sql2 = "UPDATE post SET nb_signal=0 where id = ? ";
        PreparedStatement pst;
        Integer res = 0;
        try {
            pst = conn.prepareStatement(sql1);
            pst.setInt(1, p.getId());
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                res = rs.getInt("nb_signal");
                System.out.println(res);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        try {
            pst = conn.prepareStatement(sql2);
            pst.setInt(1, p.getId());
            pst.executeUpdate();
            System.out.println("- Signals Reinitialized!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
