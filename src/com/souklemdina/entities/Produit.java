/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.souklemdina.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ramyk
 */
@Entity
@Table(name = "produit")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Produit.findAll", query = "SELECT p FROM Produit p")
    , @NamedQuery(name = "Produit.findById", query = "SELECT p FROM Produit p WHERE p.id = :id")
    , @NamedQuery(name = "Produit.findByLibelle", query = "SELECT p FROM Produit p WHERE p.libelle = :libelle")
    , @NamedQuery(name = "Produit.findByDescription", query = "SELECT p FROM Produit p WHERE p.description = :description")
    , @NamedQuery(name = "Produit.findByQuqntite", query = "SELECT p FROM Produit p WHERE p.quqntite = :quqntite")
    , @NamedQuery(name = "Produit.findByPromotion", query = "SELECT p FROM Produit p WHERE p.promotion = :promotion")
    , @NamedQuery(name = "Produit.findByNbSignal", query = "SELECT p FROM Produit p WHERE p.nbSignal = :nbSignal")
    , @NamedQuery(name = "Produit.findByType", query = "SELECT p FROM Produit p WHERE p.type = :type")
    , @NamedQuery(name = "Produit.findByCategorie", query = "SELECT p FROM Produit p WHERE p.categorie = :categorie")
    , @NamedQuery(name = "Produit.findByPrix", query = "SELECT p FROM Produit p WHERE p.prix = :prix")
    , @NamedQuery(name = "Produit.findByUpdatedAt", query = "SELECT p FROM Produit p WHERE p.updatedAt = :updatedAt")
    , @NamedQuery(name = "Produit.findByImage", query = "SELECT p FROM Produit p WHERE p.image = :image")})
public class Produit implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "libelle")
    private String libelle;
    @Basic(optional = false)
    @Column(name = "description")
    private String description;
    @Column(name = "quqntite")
    private Integer quqntite;
    @Basic(optional = false)
    @Column(name = "promotion")
    private int promotion;
    @Basic(optional = false)
    @Column(name = "nbSignal")
    private int nbSignal;
    @Basic(optional = false)
    @Column(name = "type")
    private String type;
    @Basic(optional = false)
    @Column(name = "categorie")
    private String categorie;
    @Basic(optional = false)
    @Column(name = "prix")
    private double prix;
    @Basic(optional = false)
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp updatedAt;
    @Basic(optional = false)
    @Column(name = "image")
    private String image;
    @OneToMany(mappedBy = "idProduit")
    private List<LigneDeCommande> ligneDeCommandeList;
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    @ManyToOne
    private Integer idUser;
    @OneToMany(mappedBy = "idProduit")
    private List<LigneWishlist> ligneWishlistList;

    public Produit() {
    }

    public Produit(Integer id) {
        this.id = id;
    }

    public Produit(Integer id, String libelle, String description, int promotion, int nbSignal, String type, String categorie, double prix, Timestamp updatedAt, String image) {
        this.id = id;
        this.libelle = libelle;
        this.description = description;
        this.promotion = promotion;
        this.nbSignal = nbSignal;
        this.type = type;
        this.categorie = categorie;
        this.prix = prix;
        this.updatedAt = updatedAt;
        this.image = image;
    }

    public Produit(Integer id, String libelle, String description, Integer quqntite, int promotion, String type, String categorie, double prix, Timestamp updatedAt) {
        this.id = id;
        this.libelle = libelle;
        this.description = description;
        this.quqntite = quqntite;
        this.promotion = promotion;
        this.type = type;
        this.categorie = categorie;
        this.prix = prix;
        this.updatedAt = updatedAt;
    }

    public Produit(Integer id, String libelle, String description, Integer quqntite, int promotion, int nbSignal, String type, String categorie, double prix, Timestamp updatedAt, String image, Integer idUser) {
        this.id = id;
        this.libelle = libelle;
        this.description = description;
        this.quqntite = quqntite;
        this.promotion = promotion;
        this.nbSignal = nbSignal;
        this.type = type;
        this.categorie = categorie;
        this.prix = prix;
        this.updatedAt = updatedAt;
        this.image = image;
        this.idUser = idUser;
    }
    
    

    
    
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @XmlTransient
    public List<LigneDeCommande> getLigneDeCommandeList() {
        return ligneDeCommandeList;
    }

    public void setLigneDeCommandeList(List<LigneDeCommande> ligneDeCommandeList) {
        this.ligneDeCommandeList = ligneDeCommandeList;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @XmlTransient
    public List<LigneWishlist> getLigneWishlistList() {
        return ligneWishlistList;
    }

    public void setLigneWishlistList(List<LigneWishlist> ligneWishlistList) {
        this.ligneWishlistList = ligneWishlistList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Produit)) {
            return false;
        }
        Produit other = (Produit) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.souklemdina.entities.Produit[ id=" + id + " ]";
    }
    
}
