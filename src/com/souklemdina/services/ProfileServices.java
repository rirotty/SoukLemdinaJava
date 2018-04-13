/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.souklemdina.services;

import com.souklemdina.entities.FosUser;
import com.souklemdina.entities.Profile;
import com.souklemdina.util.DataSource;
import com.souklemdina.interfaces.IProfileServices;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ramyk
 */
public class ProfileServices implements IProfileServices {

    private final Connection conn = DataSource.getInstance().getConnection();

    @Override
    public void create(Profile p) {
        String requete2 = "INSERT INTO profile (tagline,image,updated_at,about_me,id_user) VALUES (?,?,?,?,?)";
        PreparedStatement pst;
        try {
            pst = conn.prepareStatement(requete2);
            pst.setString(1, p.getTagline());
            pst.setString(2, p.getImage());
            pst.setTimestamp(3, p.getUpdatedAt());
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
                l.add(new Profile(rs.getInt("id"), rs.getString("tagline"), rs.getString("image"), rs.getTimestamp("updated_at"), rs.getString("about_me"), rs.getInt("id_user")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        l.stream().forEach(System.out::println);

        return l;
    }

    @Override
    public Profile findByIdUser(Integer id) {
        PreparedStatement pst;
        Profile p = null;
        String query = "SELECT * FROM profile WHERE id_user = ?";
        try {
            pst = conn.prepareStatement(query);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                p = new Profile(rs.getInt("id"), rs.getString("tagline"), rs.getString("image"), rs.getTimestamp("updated_at"), rs.getString("about_me"), rs.getInt("id_user"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return p;
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
            pst.setTimestamp(3, p.getUpdatedAt());
            pst.setString(4, p.getAboutMe());
            pst.setInt(5, p.getId());
            rowsUpdated = pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        if (rowsUpdated == 1) {
            System.out.println("- The profile was updated successfully!");
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

    @Override
    public List<FosUser> findByLike(String nameLike) {
        String query = "select * from fos_user where username not like 'admin' and firstname like ? or lastname like ?";
        List<FosUser> l = new ArrayList<>();
        PreparedStatement pst;
        try {
            pst = conn.prepareStatement(query);
            pst.setString(1, "%" + nameLike + "%");
            pst.setString(2, "%" + nameLike + "%");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                l.add(new FosUser(rs.getInt("id"), rs.getString("firstname"), rs.getString("lastname")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        l.stream().forEach(System.out::println);

        return l.isEmpty() ? null : l;
    }

    @Override
    public FosUser findFOSById(Integer id) {
        PreparedStatement pst;
        FosUser u = null;
        String query = "SELECT * FROM fos_user WHERE id = ?";
        try {
            pst = conn.prepareStatement(query);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                u = new FosUser(rs.getInt("id"), rs.getString("firstname"), rs.getString("lastname"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return u;
    }

    @Override
    public void follow(Profile follower, Profile followed) {
        String requete2 = "INSERT INTO follow (id_profile_follower,id_profile_followed) VALUES (?,?)";
        PreparedStatement pst;
        try {
            pst = conn.prepareStatement(requete2);
            pst.setInt(1, follower.getId());
            pst.setInt(2, followed.getId());
            pst.executeUpdate();
            System.out.println("- New Follow added!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void unFollow(Profile follower, Profile followed) {
        String sql = "DELETE FROM follow WHERE id_profile_follower = ? AND id_profile_followed = ?";
        PreparedStatement statement;
        int rowsDeleted = 0;
        try {
            statement = conn.prepareStatement(sql);
            statement.setInt(1, follower.getId());
            statement.setInt(2, followed.getId());
            rowsDeleted = statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        if (rowsDeleted == 1) {
            System.out.println("- The follow was deleted successfully!");
        } else {
            System.err.println("ERROR: rowsDeletedCount isn't normal -- " + rowsDeleted);
        }
    }

    @Override
    public boolean isFollowing(Profile follower, Profile followed) {
        PreparedStatement pst;
        ResultSet rs = null;
        String query = "SELECT * FROM follow WHERE id_profile_follower = ? AND id_profile_followed = ?";
        try {
            pst = conn.prepareStatement(query);
            pst.setInt(1, follower.getId());
            pst.setInt(2, followed.getId());
            rs = pst.executeQuery();
            return rs.next() ? true : false;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return false;
    }

}
