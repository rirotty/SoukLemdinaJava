/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.souklemdina.entities;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ramyk
 */
@Entity
@Table(name = "ligne_de_commande")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LigneDeCommande.findAll", query = "SELECT l FROM LigneDeCommande l")
    , @NamedQuery(name = "LigneDeCommande.findById", query = "SELECT l FROM LigneDeCommande l WHERE l.id = :id")
    , @NamedQuery(name = "LigneDeCommande.findByPrixTotal", query = "SELECT l FROM LigneDeCommande l WHERE l.prixTotal = :prixTotal")
    , @NamedQuery(name = "LigneDeCommande.findByQuntite", query = "SELECT l FROM LigneDeCommande l WHERE l.quntite = :quntite")
    , @NamedQuery(name = "LigneDeCommande.findByDateLivraison", query = "SELECT l FROM LigneDeCommande l WHERE l.dateLivraison = :dateLivraison")
    , @NamedQuery(name = "LigneDeCommande.findByAdresse", query = "SELECT l FROM LigneDeCommande l WHERE l.adresse = :adresse")
    , @NamedQuery(name = "LigneDeCommande.findByAdresse2", query = "SELECT l FROM LigneDeCommande l WHERE l.adresse2 = :adresse2")
    , @NamedQuery(name = "LigneDeCommande.findByVille", query = "SELECT l FROM LigneDeCommande l WHERE l.ville = :ville")
    , @NamedQuery(name = "LigneDeCommande.findByCodePostal", query = "SELECT l FROM LigneDeCommande l WHERE l.codePostal = :codePostal")
    , @NamedQuery(name = "LigneDeCommande.findByNumTel", query = "SELECT l FROM LigneDeCommande l WHERE l.numTel = :numTel")})
public class LigneDeCommande implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "prix_total")
    private double prixTotal;
    @Column(name = "quntite")
    private Integer quntite;
    @Basic(optional = false)
    @Column(name = "dateLivraison")
    @Temporal(TemporalType.DATE)
    private Date dateLivraison;
    @Basic(optional = false)
    @Column(name = "adresse")
    private String adresse;
    @Column(name = "adresse2")
    private String adresse2;
    @Basic(optional = false)
    @Column(name = "ville")
    private String ville;
    @Basic(optional = false)
    @Column(name = "code_postal")
    private int codePostal;
    @Basic(optional = false)
    @Column(name = "num_tel")
    private String numTel;
    @JoinColumn(name = "id_commande", referencedColumnName = "id")
    @ManyToOne
    private Integer idCommande;
    @JoinColumn(name = "id_produit", referencedColumnName = "id")
    @ManyToOne
    private Integer idProduit;

    public LigneDeCommande() {
    }

    public LigneDeCommande(Integer id) {
        this.id = id;
    }

    public LigneDeCommande(Integer id, double prixTotal, Date dateLivraison, String adresse, String ville, int codePostal, String numTel) {
        this.id = id;
        this.prixTotal = prixTotal;
        this.dateLivraison = dateLivraison;
        this.adresse = adresse;
        this.ville = ville;
        this.codePostal = codePostal;
        this.numTel = numTel;
    }

    public LigneDeCommande(Integer id,  Integer idProduit,Integer idCommande,double prixTotal, Integer quntite, Date dateLivraison, String adresse, String adresse2, String ville, int codePostal, String numTel) {
        this.id = id;
                this.idProduit = idProduit;
        this.idCommande = idCommande;

        this.quntite = quntite;
        this.dateLivraison = dateLivraison;
                this.prixTotal = prixTotal;

        this.adresse = adresse;
        this.adresse2 = adresse2;
        this.ville = ville;
        this.codePostal = codePostal;
        this.numTel = numTel;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(double prixTotal) {
        this.prixTotal = prixTotal;
    }

    public Integer getQuntite() {
        return quntite;
    }

    public void setQuntite(Integer quntite) {
        this.quntite = quntite;
    }

    public Date getDateLivraison() {
        return dateLivraison;
    }

    public void setDateLivraison(Date dateLivraison) {
        this.dateLivraison = dateLivraison;
    }

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

    public Integer getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(Integer idCommande) {
        this.idCommande = idCommande;
    }

    public Integer getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(Integer idProduit) {
        this.idProduit = idProduit;
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
        if (!(object instanceof LigneDeCommande)) {
            return false;
        }
        LigneDeCommande other = (LigneDeCommande) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.souklemdina.entities.LigneDeCommande[ id=" + id + " ]";
    }
    
}
