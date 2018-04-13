/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.souklemdina.services;

import com.souklemdina.util.DataSource;
import com.souklemdina.entities.Evenement;
import com.souklemdina.entities.ParticipantEvents;
import com.souklemdina.interfaces.IEvenementServices;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

/**
 *
 * @author asus
 */
public class EvenementServices implements IEvenementServices {

    Connection conn = DataSource.getInstance().getConnection();
    Statement stmt;

    @Override
    public void create(Evenement e) {
        try {
            String req = "INSERT INTO evenement (nomEvenement,dateDebut,dateFin,adresse,prix,description,nbPlace,type,heure,photo) VALUES (?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement st = conn.prepareStatement(req);
            st.setString(1, e.getNomEvenement());
            st.setObject(2, e.getDateDebut());
            st.setObject(3, e.getDateFin());

            //  st.setDate(2, (Date)e.getDateDebut());
            //st.setDate(3, (Date)e.getDateFin());
            st.setString(4, e.getAdresse());
            st.setDouble(5, e.getPrix());
            st.setString(6, e.getDescription());
            st.setInt(7, e.getNbPlace());
            st.setString(8, e.getType());
            st.setObject(9, e.getHeure());
            st.setString(10, e.getPhoto());
// st.setDate(9, (java.sql.Date)e.getHeure());

            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(EvenementServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /* @Override
   public List<Evenement> findAll() {
        List<Evenement> listEvenements = new ArrayList<>();
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from evenement");
            while (rs.next()) {
                System.out.println(
                        rs.getString(3) + " (" +(Date) rs.getDate(4) + ")"+(Date)rs.getDate(5)+ ")"+rs.getString(6)+ ")"
                        +rs.getDouble(7)+ ")"+rs.getString(8)+
                        ")"+rs.getInt(9)+ ")"+rs.getString(11)+ ")"+rs.getTime(13)+ ")"+rs.getString(12)   );
                listEvenements.add(new Evenement
                     (
                       new Integer(rs.getInt(1)),
                        rs.getString(3),
                       (java.sql.Date) rs.getDate(4),
                    (java.sql.Date) rs.getDate(5),
                    rs.getString(6),
                     rs.getDouble(7),
                     rs.getString(8),
                    rs.getInt(9),
                    rs.getString(11),
                     rs.getTime(13),
                      rs.getString(12)
                ));

            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(EvenementServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listEvenements;
    }*/
    @Override
    public List<Evenement> afficherev() {

        List<Evenement> le = new ArrayList<>();
        try {
            String select = "SELECT * from evenement ";
            Statement statement1 = conn.createStatement();
            ResultSet result = statement1.executeQuery(select);

            while (result.next()) {
                Evenement e = new Evenement(result.getInt("id"));
                e.setNomEvenement(result.getString("nomEvenement"));
                e.setDateDebut(result.getTimestamp("dateDebut"));
                e.setDateFin(result.getTimestamp("dateFin"));
                e.setAdresse(result.getString("adresse"));
                e.setPrix(result.getInt("prix"));
                e.setDescription(result.getString("description"));
                e.setNbPlace(result.getInt("nbPlace"));
                e.setType(result.getString("type"));
                e.setPhoto(result.getString("photo"));
                e.setHeure(result.getTimestamp("heure"));
                e.setRating(result.getInt("rating"));
                e.setNbSignal(result.getInt("nbSignal"));
                le.add(e);

            }
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
            System.err.println("SQLSTATE: " + ex.getSQLState());
            System.err.println("VnedorError: " + ex.getErrorCode());
        }
        return le;

    }

    public void addRarting(Evenement e) {
        try {
//            String req=("INSERT INTO `evenement`(`id`, `id_user`, `rating`, `nbrrating`, `nbruser`) VALUES (?,?,?,?,?)");
            String req = ("update `evenement` SET `rating` =? WHERE id = '" + e.getId() + "' ");

            PreparedStatement st = DataSource.getInstance().getConnection().prepareStatement(req);
            st.setInt(1, e.getRating());

//                      
            st.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(EvenementServices.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void update(Evenement e) {
        try {
            String req = "UPDATE evenement SET nomEvenement = ?, dateDebut= ?, dateFin=?, adresse=?, prix=?, description=?, nbPlace=?, type=?, `heure`=?,`photo`=? WHERE `id` = ?";
            PreparedStatement st = conn.prepareStatement(req);
            st.setString(1, e.getNomEvenement());
            st.setTimestamp(2, e.getDateDebut());
            st.setTimestamp(3, e.getDateFin());
            st.setString(4, e.getAdresse());
            st.setDouble(5, e.getPrix());
            st.setString(6, e.getDescription());
            st.setInt(7, e.getNbPlace());
            st.setString(8, e.getType());
            st.setObject(9, e.getHeure());
            st.setString(10, e.getPhoto());
            System.out.println(e.getId());
            st.setInt(11, e.getId());

            st.executeUpdate();
            System.out.println("Event updated ");
        } catch (SQLException ex) {
            Logger.getLogger(EvenementServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(int id) {
        try {
            String req = "DELETE FROM `evenement` WHERE `evenement`.`id` = ? ";
            PreparedStatement st = conn.prepareStatement(req);
            st.setInt(1, id);
            //  st.setString(2, e.getNomEvenement());

            st.executeUpdate();
        } catch (SQLException ex) {

            Logger.getLogger(EvenementServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    long participants;

    public long nbParticipant(Evenement e) {
        ParticipantsEventsServices pe = new ParticipantsEventsServices();

        ParticipantEvents p = new ParticipantEvents();
        participants = pe.getEvById(e.getId()).stream().count();
        return participants;
    }

    @Override
    public boolean EventHasNote(Evenement ev) {
        try {
            String req = "SELECT * FROM evenement WHERE id = '" + ev.getId() + "'";
            stmt = conn.prepareStatement(req);
            ResultSet rs = stmt.executeQuery(req);
            while (rs.next()) {
                return true;
            }

        } catch (SQLException e) {
            System.out.println("Echec de recherche de evaluation" + e);
        }
        return false;
    }

    public ObservableList<PieChart.Data> moyEvnType() {
        ArrayList<PieChart.Data> list = new ArrayList<PieChart.Data>();
        try {
            PreparedStatement st = conn.prepareStatement("SELECT type,COUNT(id) FROM `evenement` group by type");

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
            Logger.getLogger(ParticipantsEventsServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ObservableList<PieChart.Data> moyEvnRating() {
        ArrayList<PieChart.Data> list = new ArrayList<PieChart.Data>();
        Evenement e = new Evenement();
        try {
            PreparedStatement st = conn.prepareStatement("SELECT nomEvenement,count(nbrrating) FROM `evenement` GROUP by nomEvenement");

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
            Logger.getLogger(ParticipantsEventsServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public float getRateEvents(Evenement e) {

        float moy = 0.0f;

        try {
            PreparedStatement st = conn.prepareStatement("SELECT AVG(rating) FROM `evenement` where id = '" + e.getId() + "'");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                moy = rs.getFloat(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(EvenementServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return moy;

    }

    public ArrayList<Evenement> getEvenementBytype(String tes) {
        ArrayList<Evenement> lsttype = new ArrayList<>();

        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from evenement where type LIKE '" + tes + "' ;");

            while (rs.next()) {
                Evenement ev = new Evenement();
                
                ev.setId(rs.getInt("id"));
                ev.setPhoto(rs.getString("photo"));
                ev.setType(rs.getString("type"));
//                ev.setType("type");
                ev.setAdresse(rs.getString("adresse"));
                ev.setNomEvenement(rs.getString("nomEvenement"));
                ev.setDateDebut(rs.getTimestamp("dateDebut"));
                ev.setDateFin(rs.getTimestamp("dateFin"));
                ev.setNbPlace(rs.getInt("nbPlace"));
                ev.setPrix(rs.getDouble("prix"));
                ev.setHeure(rs.getTimestamp("heure"));

                lsttype.add(ev);
            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(EvenementServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lsttype;
    }

    public ArrayList<Evenement> getEvenementByaddresse(String adresse) {
        ArrayList<Evenement> lstadd = new ArrayList<>();
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from evenement where e.getAdresse() =" + adresse);

            while (rs.next()) {
                Evenement ev = new Evenement();

                ev.setId(rs.getInt("id"));
                ev.setType("type");
                ev.setAdresse(rs.getString("adresse"));
                ev.setNomEvenement("nom");
                ev.setDateDebut(rs.getTimestamp("dateDebut"));
                ev.setDateFin(rs.getTimestamp("dateFin"));
                ev.setNbPlace(rs.getInt("nbPlace"));
                ev.setPrix(rs.getDouble("prix"));

                lstadd.add(ev);
            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(EvenementServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lstadd;
    }

    public ArrayList<Evenement> getEvenementByName(String nom) {
        ArrayList<Evenement> lstn = new ArrayList<>();
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from evenement where e.getNom_evenement =" + nom);

            while (rs.next()) {
                Evenement ev = new Evenement();

                ev.setId(rs.getInt("id"));
                ev.setType("type");
                ev.setAdresse(rs.getString("adresse"));
                ev.setNomEvenement("nom_evenement");
                ev.setDateDebut(rs.getTimestamp("dateDebut"));
                ev.setDateFin(rs.getTimestamp("dateFin"));
                ev.setNbPlace(rs.getInt("nbPlace"));
                ev.setPrix(rs.getDouble("prix"));

                lstn.add(ev);
            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(EvenementServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lstn;
    }

    @Override
    public Evenement FindEvenement(int id) {
        Evenement ev = null;

        try {
            String select = "SELECT * FROM evenement WHERE  id = '" + id + "' ";
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(select);

            if (result.next()) {
                ev = new Evenement();
                ev.setId(id);
                ev.setNomEvenement(result.getString("nomEvenement"));
                ev.setDateDebut(result.getTimestamp("dateDebut"));
                ev.setDateFin(result.getTimestamp("dateFin"));
                ev.setDescription(result.getString("description"));
                ev.setType(result.getString("type"));
                ev.setPrix(result.getDouble("prix"));
                ev.setNbPlace(result.getInt("nbPlace"));
                ev.setPhoto(result.getNString("photo"));

                EvenementServices es = new EvenementServices();

                //ev.setId(es.));
                //   ev.setId(es.afficherev(result.getInt("id")));
                // ex.setUtilisateur(su.getUserById(result.getInt("id")));     
                //ServiceEtablissement se = new ServiceEtablissement();
                // ex.setEtablissement(se.chercherEtablissement(result.getInt("id_etablissement")));
            }
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
            System.err.println("SQLSTATE: " + e.getSQLState());
            System.err.println("VnedorError: " + e.getErrorCode());
        }
        return ev;
    }

//    @Override
//    public Double MoyRat(int id) {
//        Double moy=0.0;
//        try{
//            String req= "SELECT avg(nbrrating) FROM evenement e WHERE e.id= '+id+' and e.id=e.id_user";
//            PreparedStatement st= conn.prepareStatement(req);
//                        ResultSet rs = st.executeQuery();
//                        while(rs.next()){
//                   moy =rs.getDouble(1);
//                   
//                        }
//                            
//        } 
//        catch (SQLException ex) {
//                      Logger.getLogger(EvenementServices.class.getName()).log(Level.SEVERE, null, ex);
//  
//        }
//        return moy;
    public boolean ChangeOn(Evenement e, int id) {
        boolean x = false;
        try {
            Statement stmt = conn.createStatement();
            String select = "SELECT * FROM participant_events WHERE  id_evenement = '" + e.getId() + "' and id_user = '" + id + "'  ";
            ResultSet result = stmt.executeQuery(select);
            if (result.next()) {
                System.out.println("ne plus participer");
                x = true;
            } else {
                System.out.println("participer");
                x = false;
            }

        } catch (SQLException ex) {
            Logger.getLogger(EvenementServices.class.getName()).log(Level.SEVERE, null, ex);
        }

        return x;

    }
public ArrayList<Evenement> FindBySignal() {
        
        ArrayList<Evenement> sigev = new ArrayList<>();
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM `evenement` WHERE nbSignal >= 7 " );

            while (rs.next()) {
Evenement e = new Evenement();
                e.setId(rs.getInt("id"));
               
                e.setNomEvenement(rs.getString("nomEvenement"));
                
               e.setNbSignal(rs.getInt("nbSignal"));
               // wo.setPrix(rs.getDouble("prix"));

                sigev.add(e);

            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(FosUserServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sigev;
    }
    
    
    
}
