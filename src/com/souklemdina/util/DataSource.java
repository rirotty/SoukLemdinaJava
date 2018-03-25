/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.souklemdina.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ramyk
 */
// La classe est finale, car un singleton n'est pas censé avoir d'héritier.
public class DataSource {

    // L'utilisation du mot clé volatile, en Java version 5 et supérieure,
    // permet d'éviter le cas où "Singleton.instance" est non nul,
    // mais pas encore "réellement" instancié.
    // De Java version 1.2 à 1.4, il est possible d'utiliser la classe ThreadLocal.
    private static DataSource instance = null;

    public final static String DB_URL = "jdbc:mysql://localhost:3306/souklemdina";
    public final static String DB_USERNAME = "root";
    public final static String DB_PASSWORD = "";

    private Connection conn;

    /**
     * Constructor
     */
    private DataSource() {
        // La présence d'un constructeur privé supprime le constructeur public par défaut.
        // De plus, seul le singleton peut s'instancier lui-même.
        super();
        try {
            conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            System.out.println(DB_URL + ": DB Connection created successfully.");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    //
    // Méthode permettant de renvoyer une instance de la classe Singleton
    // @return Retourne l'instance du singleton.
    //
    public final static DataSource getInstance() {
        //Le "Double-Checked Singleton"/"Singleton doublement vérifié" permet 
        //d'éviter un appel coûteux à synchronized, 
        //une fois que l'instanciation est faite.
        if (DataSource.instance == null) {
            // Le mot-clé synchronized sur ce bloc empêche toute instanciation
            // multiple même par différents "threads".
            // Il est TRES important.
            //synchronized (DataSource.class) {
            //    if (DataSource.instance == null) {
            DataSource.instance = new DataSource();
            //    }
            //}
        }
        return DataSource.instance;
    }

    public final Connection getConnection() {
        return conn;
    }

}
