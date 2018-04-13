/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.souklemdina.services;

import com.souklemdina.util.DataSource;
import com.souklemdina.entities.Workshop;
import com.souklemdina.entities.FosUser;
import com.souklemdina.entities.ParticipantWork;
import com.souklemdina.entities.Profile;
import com.souklemdina.interfaces.IWorkshopServices;
import com.souklemdina.util.SessionUser;
import java.sql.Connection;
import java.sql.Timestamp;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

/**
 *
 * @author bhk
 */
public class WorkshopServices implements IWorkshopServices {

    Connection conn = DataSource.getInstance().getConnection();
    Statement stmt;

    @Override
    public void create(Workshop w) {

        String req = "INSERT INTO workshop (nomWorkshop,adresse,type,dateDebut,dateFin,image,updated_At,nbPlace,prix,description,id_user) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement st;
        try {
            st = conn.prepareStatement(req);
            st.setString(1, w.getNomWorkshop());
            st.setString(2, w.getAdresse());
            st.setString(3, w.getType());
            st.setObject(4, w.getDateDebut());
            st.setObject(5, w.getDateFin());
            st.setString(6, w.getImage());
            st.setObject(7, w.getUpdatedAt());
            st.setDouble(8, w.getNbPlace());
            st.setDouble(9, w.getPrix());
            st.setString(10, w.getDescription());
            st.setInt(11, SessionUser.getUser().getId());
            st.executeUpdate();
            System.out.println("- New workshop added!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
//
//    @Override
//    public List<Workshop> findAll() {
//        ArrayList<Workshop> listWorks = new ArrayList<>();
//        try {
//            stmt = conn.createStatement();
//            ResultSet rs = stmt.executeQuery("Select * from workshop");
//            while (rs.next()) {
//                System.out.println( rs.getString(2) + " " 
//                        + rs.getString(3) + " " + rs.getString(4) + " " 
//                        + (Date) rs.getDate(6) + " " + (Date) rs.getDate(7) 
//                        + " " + " " + rs.getString(14) + ""  + (Date) rs.getDate(15) + " "  
//                        +  rs.getInt(8) + " "+rs.getDouble(9) + " " + rs.getString(11) + "");
//                
//                
//                listWorks.add( new Workshop(new Integer(rs.getInt(1)), rs.getString(2), rs.getString(3),
//                                rs.getString(4), (java.sql.Date)rs.getDate(6),(java.sql.Date)rs.getDate(7)
//                        ,rs.getString(14), (java.util.Date)rs.getDate(15), rs.getInt(8), rs.getDouble(9),rs.getString(11) ));     
//            }
//            stmt.close();
//        } catch (SQLException ex) {
//            Logger.getLogger(WorkshopServices.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return listWorks;
//
//    }

    public void addWRarte(Workshop w) {
        try {
            ;
            String req = ("update workshop SET rating =? WHERE id = '" + w.getId() + "' ");

            PreparedStatement st = DataSource.getInstance().getConnection().prepareStatement(req);
            st.setInt(1, w.getRating());

//                      
            st.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(WorkshopServices.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public List<Workshop> aff() {

        List<Workshop> listWorks = new ArrayList<>();

        try {
            String select = "SELECT * from workshop ";
            Statement statement1 = conn.createStatement();
            ResultSet result = statement1.executeQuery(select);

            while (result.next()) {
                Workshop e = new Workshop(result.getInt("id"));
                e.setNomWorkshop(result.getString("nomWorkshop"));
                e.setAdresse(result.getString("adresse"));
                e.setType(result.getString("type"));
                e.setDateDebut(result.getTimestamp("dateDebut"));
                e.setDateFin(result.getTimestamp("dateFin"));
                e.setNbPlace(result.getInt("nbPlace"));
                e.setPrix(result.getDouble("prix"));
                e.setDescription(result.getString("description"));
                e.setImage(result.getString("image"));
                e.setUpdatedAt(result.getTimestamp("updated_at"));
                e.setRating(result.getInt("rating"));
                e.setIdUser(result.getInt("id_user"));
                listWorks.add(e);
            }

        } catch (SQLException ex) {
            Logger.getLogger(WorkshopServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listWorks;
    }

    public int getRateWork(Workshop w) {
        int ra = 0;
        try {
            PreparedStatement st = conn.prepareStatement("SELECT rating FROM `workshop` where id= '" + w.getId() + "' ");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                ra = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(WorkshopServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ra;
    }

    @Override
    public void delete(Workshop w) {

        try {
            String req = "DELETE FROM `workshop` WHERE `workshop`.`id` = ? and  `workshop`.`nomWorkshop` = ?";
            PreparedStatement st = conn.prepareStatement(req);

            st.setInt(1, w.getId());
            st.setString(2, w.getNomWorkshop());
            System.out.println(st.executeUpdate());;
        } catch (SQLException ex) {
            Logger.getLogger(WorkshopServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void delete(int id) {
        try {
            String req = "DELETE FROM `workshop` WHERE `workshop`.`id` = ? ";
            PreparedStatement st = conn.prepareStatement(req);
            st.setInt(1, id);
            System.out.println(st.executeUpdate());;
        } catch (SQLException ex) {
            Logger.getLogger(WorkshopServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Workshop w) {
        String req = "UPDATE `workshop` SET `nomWorkshop` = ?, `adresse`= ?, `type`=? ,`dateDebut`=?, `dateFin`=?, `image`=?, `updated_At`=?, `nbPlace`=?, `prix`=?, `description`=?  WHERE `id` = ?";

        try {
            PreparedStatement st;
            st = conn.prepareStatement(req);

            st.setString(1, w.getNomWorkshop());
            st.setString(2, w.getAdresse());
            st.setString(3, w.getType());
            st.setTimestamp(4, w.getDateDebut());
            st.setTimestamp(5, w.getDateFin());
            st.setString(6, w.getImage());
            st.setTimestamp(7, w.getUpdatedAt());
            st.setDouble(8, w.getNbPlace());
            st.setDouble(9, w.getPrix());
            st.setString(10, w.getDescription());
            st.setInt(11, w.getId());
            int rowUpdated = st.executeUpdate();
            if (rowUpdated > 0) {
                System.out.println("Workshop updated ");
            }

        } catch (SQLException ex) {
            Logger.getLogger(WorkshopServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Workshop findbyid(int id) {
        Workshop wo = new Workshop();

        try {

            String select = "SELECT * FROM workshop WHERE  id_user = '" + id + "' ";
            Statement statement1 = conn.createStatement();
            ResultSet result = statement1.executeQuery(select);

            while (result.next()) {
                wo.setId(result.getInt("id"));
//                wo.setId(result.getInt("id"));
                wo.setNomWorkshop(result.getString("nomWorkshop"));
                wo.setType(result.getString("type"));
                wo.setDateDebut(result.getTimestamp("dateDebut"));
                wo.setDateFin(result.getTimestamp("dateFin"));
                wo.setNbPlace(result.getInt("nbPlace"));
                wo.setPrix(result.getDouble("prix"));
                wo.setDescription(result.getString("description"));
                wo.setImage(result.getString("image"));
                wo.setUpdatedAt(result.getTimestamp("updated_at"));

                WorkshopServices ws = new WorkshopServices();

            }
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
            System.err.println("SQLSTATE: " + e.getSQLState());
            System.err.println("VnedorError: " + e.getErrorCode());
        }
        return wo;
    }
    long nbPar;

    public long nbParticipant(Workshop w) {
        ParticipantWorkService pws = new ParticipantWorkService();

        ParticipantWork p = new ParticipantWork();
        nbPar = pws.getWorkshopById(w.getId()).stream().count();
// "select count(p.id) from participant_work p GROUP BY id_workshop";//nb participant par workshop
        return nbPar;
    }

    public ArrayList<Workshop> getWorkshopByNom(String nom) {
        ArrayList<Workshop> lstnom = new ArrayList<>();
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from workshop where w.getNomWorkshop() =" + nom);

            while (rs.next()) {
                Workshop wo = new Workshop();

                wo.setId(rs.getInt("id"));
                wo.setType(rs.getString("type"));
                wo.setAdresse(rs.getString("adresse"));
                wo.setNomWorkshop("nom");
                wo.setDateDebut(rs.getTimestamp("dateDebut"));
                wo.setDateFin(rs.getTimestamp("dateFin"));
                wo.setNbPlace(rs.getInt("nbPlace"));
                wo.setPrix(rs.getDouble("prix"));
                wo.setIdUser(rs.getInt("id_user"));

                lstnom.add(wo);

            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(FosUserServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lstnom;
    }

    public ArrayList<Workshop> getWorkshopBytype(String test) {
        ArrayList<Workshop> lsttype = new ArrayList<>();
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from workshop where type like '" + test + "' ; ");

            while (rs.next()) {
                Workshop wo = new Workshop();

                wo.setId(rs.getInt("id"));
                wo.setNomWorkshop(rs.getString("nomWorkshop"));
                wo.setType(rs.getString("type"));
                wo.setAdresse(rs.getString("adresse"));
                wo.setDateDebut(rs.getTimestamp("dateDebut"));
                wo.setDateFin(rs.getTimestamp("dateFin"));
                wo.setNbPlace(rs.getInt("nbPlace"));
                wo.setPrix(rs.getDouble("prix"));
                wo.setIdUser(rs.getInt("id_user"));

                lsttype.add(wo);
            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(FosUserServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lsttype;
    }

    public ArrayList<Workshop> getWorkshopByAdresse() {
        Workshop wo = new Workshop();
        ArrayList<Workshop> lstadr = new ArrayList<>();
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from workshop where adresse = '" + wo.getAdresse() + "' ;");

            while (rs.next()) {

                wo.setId(rs.getInt("id"));
                wo.setType(rs.getString("type"));
                wo.setAdresse(rs.getString("adresse"));
                wo.setNomWorkshop("nom");
                wo.setDateDebut(rs.getTimestamp("dateDebut"));
                wo.setDateFin(rs.getTimestamp("dateFin"));
                wo.setNbPlace(rs.getInt("nbPlace"));
                wo.setPrix(rs.getDouble("prix"));
                wo.setIdUser(rs.getInt("id_user"));

                lstadr.add(wo);

            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(FosUserServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lstadr;
    }

    public boolean switchP(Workshop w, int id) {
        boolean x = false;
        try {
            Statement stmt = conn.createStatement();
            String select = "SELECT * FROM participant_work WHERE  id_workshop = '" + w.getId() + "' and id_user = '" + id + "'  ";
            ResultSet result = stmt.executeQuery(select);
            if (result.next()) {
                System.out.println("ne plus participer");
                x = true;
            } else {
                System.out.println("participer");
                x = false;
            }

        } catch (SQLException ex) {
            Logger.getLogger(WorkshopServices.class.getName()).log(Level.SEVERE, null, ex);
        }

        return x;

    }

//    public float moyenneSelonType1(String a) {
//
//        long s, st1;
//        Workshop w = new Workshop();
//        w.setType(a);
//     
//
//        ArrayList<Workshop> lista = new ArrayList<>();
//        s = aff().stream().count() != 0 ? aff().stream().count() : 1;
//      
//        System.out.println(s);
//        st1 = getWorkshopBytype(w.getType()).stream().count();
//        System.out.println(st1);
//        System.out.println(st1/s);
//        return (float) st1/s;
//    }
    public ObservableList<PieChart.Data> moyParType() {
        ArrayList<PieChart.Data> list = new ArrayList<PieChart.Data>();
        try {
            PreparedStatement st = conn.prepareStatement("SELECT type,COUNT(id) FROM `workshop` group by type");

            ResultSet rs = st.executeQuery();
            while (rs.next()) {

                list.add(new PieChart.Data(rs.getString(1), rs.getInt(2)));

            }
            ObservableList<PieChart.Data> observableList;
            observableList = FXCollections.observableList(list);
//            System.out.println("ici" + observableList.size());
            for (PieChart.Data data : observableList) {
                System.out.println("test ::" + data);

            }
            return observableList;

        } catch (SQLException ex) {
            Logger.getLogger(ParticipantWorkService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public double nbWorks() {
        double res = 0;
        try {
            PreparedStatement st = conn.prepareStatement("Select count(*) as vall from workshop ");

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                res = rs.getInt("vall");

            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return res;

    }

    public double nbType1() {
        double result = 0;
        try {
            PreparedStatement st = conn.prepareStatement("Select count(*) as val from workshop WHERE type='patisserie Traditionnelle'");

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                result = rs.getInt("val");

            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return result;

    }

    public double nbType2() {
        double result = 0;
        try {
            PreparedStatement st = conn.prepareStatement("Select count(*) as val from workshop WHERE type='Haute couture'");

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                result = rs.getInt("val");

            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return result;

    }

    public double nbType3() {
        double result = 0;
        try {
            PreparedStatement st = conn.prepareStatement("Select count(*) as val2 from workshop WHERE type='autre'");

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                result = rs.getInt("val2");

            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return result;

    }

    public FosUser getUserById(Integer w) {
        FosUser user = new FosUser();
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from fos_user where id =" + w);

            while (rs.next()) {

                user.setId(rs.getInt("id"));
                user.setPhone(rs.getString("phone"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));

            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(FosUserServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    public Profile getProfilByIdUser(Integer w) {
        Profile p = new Profile();
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from profile where id_user =" + w);

            if (rs.next()) {

                p.setId(w);
                p.setImage(rs.getString("image"));

            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(FosUserServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }

    public ArrayList<Workshop> FindBySignal() {

        ArrayList<Workshop> lstS = new ArrayList<>();
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM `workshop` WHERE nbSignal >= 10 ");

            while (rs.next()) {
                Workshop wo = new Workshop();
                wo.setId(rs.getInt("id"));

                wo.setNomWorkshop(rs.getString("nomWorkshop"));

                wo.setNbSignal(rs.getInt("nbSignal"));
                // wo.setPrix(rs.getDouble("prix"));

                lstS.add(wo);

            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(FosUserServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lstS;
    }

}
