/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.souklemdina.util;

import java.util.Date;

/**
 *
 * @author hatem
 */
public class LigneDeCommandeProduit {

    private Integer idLigneDeCommande;
    private Integer idCommande;

    
    private double prixTotal;
    private Integer quantiteCommander;
    private Date dateLivraison;
    private int idProduit;
    private int id_user;
    private String libelle;
    private String description;
    private Integer quantiteEnStock;
    private int promotion;
    private int nbSignal;
    private String type;
    private String categorie;
    private double prix;
    private double prixU;
    private Date updatedAt;
    private String image;
    private String adresse;
    private String adresse2;
    private String ville;
    private int codePostal;
    private String numTel;

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getAdresse2() {
        return adresse2;
    }

    public void setAdresse2(String adresse2) {
        this.adresse2 = adresse2;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public int getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(int codePostal) {
        this.codePostal = codePostal;
    }

    public String getNumTel() {
        return numTel;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

    
    public LigneDeCommandeProduit(Integer idLigneDeCommande, double prixTotal, Integer quantiteCommander, Date dateLivraison, int idProduit, int id_user, String libelle, String description, Integer quantiteEnStock, int promotion, int nbSignal, String type, String categorie, double prix, Date updatedAt, String image) {
        this.idLigneDeCommande = idLigneDeCommande;
        this.prixTotal = prixTotal;
        this.quantiteCommander = quantiteCommander;
        this.dateLivraison = dateLivraison;
        this.idProduit = idProduit;
        this.id_user = id_user;
        this.libelle = libelle;
        this.description = description;
        this.quantiteEnStock = quantiteEnStock;
        this.promotion = promotion;
        this.nbSignal = nbSignal;
        this.type = type;
        this.categorie = categorie;
        this.prix = prix;
        this.updatedAt = updatedAt;
        this.image = image;
    }

    public LigneDeCommandeProduit() {
    }

    
    public Integer getIdLigneDeCommande() {
        return idLigneDeCommande;
    }

    public double getPrixU() {
        return prixU;
    }

    public void setPrixU(double prixU) {
        this.prixU = prixU;
    }
    
public Integer getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(Integer idCommande) {
        this.idCommande = idCommande;
    }
    
    public void setIdLigneDeCommande(Integer idLigneDeCommande) {
        this.idLigneDeCommande = idLigneDeCommande;
    }

    public double getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(double prixTotal) {
        this.prixTotal = prixTotal;
    }

    public Integer getQuantiteCommander() {
        return quantiteCommander;
    }

    public void setQuantiteCommander(Integer quantiteCommander) {
        this.quantiteCommander = quantiteCommander;
    }

    public Date getDateLivraison() {
        return dateLivraison;
    }

    public void setDateLivraison(Date dateLivraison) {
        this.dateLivraison = dateLivraison;
    }

    public int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getQuantiteEnStock() {
        return quantiteEnStock;
    }

    public void setQuantiteEnStock(Integer quantiteEnStock) {
        this.quantiteEnStock = quantiteEnStock;
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
    
    
}
