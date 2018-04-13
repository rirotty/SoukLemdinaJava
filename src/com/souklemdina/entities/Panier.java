/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.souklemdina.entities;

import java.util.Date;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author hatem
 */
public class Panier {
       private int id;
       private int id_user;
       private String libelle;
       private SimpleStringProperty description;
           private Integer quqntite;

       private int promotion;
       private int nbSignal;
       private String type;
       private String categorie;
       private double prix;
       
       private Date updatedAt;
       private String image;
       private Integer qauntiteCommander;

    public Panier(int id,int id_user, String libelle, String description, Integer quqntite, int promotion, int nbSignal, String type, String categorie, double prix, Date updatedAt, String image, Integer qauntiteCommander) {
        this.id = id;
        this.id_user=id_user;
        this.libelle = libelle;
        this.description = new SimpleStringProperty(description);
        this.quqntite = quqntite;
        this.promotion = promotion;
        this.nbSignal = nbSignal;
        this.type = type;
        this.categorie = categorie;
        this.prix = prix;
        
        this.updatedAt = updatedAt;
        this.image = image;
        this.qauntiteCommander = qauntiteCommander;
    }

    public Panier() {
    }

    public Panier(int id, Integer qauntiteCommander) {
        this.id = id;
        this.qauntiteCommander = qauntiteCommander;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

       
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDescription() {
        return description.get();
    }

    public void setDescription(String description) {
        this.description = new SimpleStringProperty (description);
    }

    public Integer getQuqntite() {
        return quqntite;
    }

    public void setQuqntite(Integer quqntite) {
        this.quqntite = quqntite;
    }

    public int getPromotion() {
        return promotion;
    }

    public void setPromotion(int promotion) {
        this.promotion = promotion;
    }

    public int getNbSignal() {
        return nbSignal;
    }

    public void setNbSignal(int nbSignal) {
        this.nbSignal = nbSignal;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getQauntiteCommander() {
        return qauntiteCommander;
    }

    public void setQauntiteCommander(Integer qauntiteCommander) {
        this.qauntiteCommander = qauntiteCommander;
    }

       
}
