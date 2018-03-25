/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.souklemdina.entities;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ramyk
 */
@Entity
@Table(name = "local")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Local.findAll", query = "SELECT l FROM Local l")
    , @NamedQuery(name = "Local.findById", query = "SELECT l FROM Local l WHERE l.id = :id")
    , @NamedQuery(name = "Local.findBySuperficie", query = "SELECT l FROM Local l WHERE l.superficie = :superficie")
    , @NamedQuery(name = "Local.findByPrix", query = "SELECT l FROM Local l WHERE l.prix = :prix")
    , @NamedQuery(name = "Local.findByType", query = "SELECT l FROM Local l WHERE l.type = :type")
    , @NamedQuery(name = "Local.findByDescription", query = "SELECT l FROM Local l WHERE l.description = :description")
    , @NamedQuery(name = "Local.findByNbSignal", query = "SELECT l FROM Local l WHERE l.nbSignal = :nbSignal")
    , @NamedQuery(name = "Local.findByAdresse", query = "SELECT l FROM Local l WHERE l.adresse = :adresse")
    , @NamedQuery(name = "Local.findByTelephone", query = "SELECT l FROM Local l WHERE l.telephone = :telephone")
    , @NamedQuery(name = "Local.findByChoixPrix", query = "SELECT l FROM Local l WHERE l.choixPrix = :choixPrix")
    , @NamedQuery(name = "Local.findByHeureDeb", query = "SELECT l FROM Local l WHERE l.heureDeb = :heureDeb")
    , @NamedQuery(name = "Local.findByHeureFin", query = "SELECT l FROM Local l WHERE l.heureFin = :heureFin")
    , @NamedQuery(name = "Local.findByTitre", query = "SELECT l FROM Local l WHERE l.titre = :titre")
    , @NamedQuery(name = "Local.findByImage", query = "SELECT l FROM Local l WHERE l.image = :image")})
public class Local implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "superficie")
    private Double superficie;
    @Column(name = "prix")
    private Double prix;
    @Column(name = "type")
    private String type;
    @Column(name = "description")
    private String description;
    @Column(name = "nbSignal")
    private Integer nbSignal;
    @Column(name = "adresse")
    private String adresse;
    @Column(name = "telephone")
    private String telephone;
    @Column(name = "choixPrix")
    private String choixPrix;
    @Column(name = "heureDeb")
    private Integer heureDeb;
    @Column(name = "heureFin")
    private Integer heureFin;
    @Column(name = "titre")
    private String titre;
    @Basic(optional = false)
    @Column(name = "image")
    private String image;
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    @ManyToOne
    private FosUser idUser;
    @OneToMany(mappedBy = "idLocal")
    private List<Location> locationList;

    public Local() {
    }

    public Local(Integer id) {
        this.id = id;
    }

    public Local(Integer id, String image) {
        this.id = id;
        this.image = image;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getSuperficie() {
        return superficie;
    }

    public void setSuperficie(Double superficie) {
        this.superficie = superficie;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getNbSignal() {
        return nbSignal;
    }

    public void setNbSignal(Integer nbSignal) {
        this.nbSignal = nbSignal;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getChoixPrix() {
        return choixPrix;
    }

    public void setChoixPrix(String choixPrix) {
        this.choixPrix = choixPrix;
    }

    public Integer getHeureDeb() {
        return heureDeb;
    }

    public void setHeureDeb(Integer heureDeb) {
        this.heureDeb = heureDeb;
    }

    public Integer getHeureFin() {
        return heureFin;
    }

    public void setHeureFin(Integer heureFin) {
        this.heureFin = heureFin;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public FosUser getIdUser() {
        return idUser;
    }

    public void setIdUser(FosUser idUser) {
        this.idUser = idUser;
    }

    @XmlTransient
    public List<Location> getLocationList() {
        return locationList;
    }

    public void setLocationList(List<Location> locationList) {
        this.locationList = locationList;
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
        if (!(object instanceof Local)) {
            return false;
        }
        Local other = (Local) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.souklemdina.entities.Local[ id=" + id + " ]";
    }
    
}
