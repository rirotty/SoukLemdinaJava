/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.souklemdina.entities;

import java.io.Serializable;
import java.util.List;
import javafx.scene.image.Image;

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
import org.eclipse.persistence.annotations.CascadeOnDelete;

/**
 *
 * @author ramyk
 */
@Entity
@Table(name = "local")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Local.findAll", query = "SELECT l FROM Local l"),
    @NamedQuery(name = "Local.findById", query = "SELECT l FROM Local l WHERE l.id = :id"),
    @NamedQuery(name = "Local.findBySuperficie", query = "SELECT l FROM Local l WHERE l.superficie = :superficie"),
    @NamedQuery(name = "Local.findByPrix", query = "SELECT l FROM Local l WHERE l.prix = :prix"),
    @NamedQuery(name = "Local.findByType", query = "SELECT l FROM Local l WHERE l.type = :type"),
    @NamedQuery(name = "Local.findByDescription", query = "SELECT l FROM Local l WHERE l.description = :description"),
    @NamedQuery(name = "Local.findByAdresse", query = "SELECT l FROM Local l WHERE l.adresse = :adresse"),
    @NamedQuery(name = "Local.findByTelephone", query = "SELECT l FROM Local l WHERE l.telephone = :telephone"),
    @NamedQuery(name = "Local.findByTitre", query = "SELECT l FROM Local l WHERE l.titre = :titre"),
    @NamedQuery(name = "Local.findByLocated", query = "SELECT l FROM Local l WHERE l.located = :located"),
    @NamedQuery(name = "Local.findByNbSignal", query = "SELECT l FROM Local l WHERE l.nbSignal = :nbSignal"),
    @NamedQuery(name = "Local.findByImage", query = "SELECT l FROM Local l WHERE l.image = :image")})
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
 @Column(name = "image")
    private String image;
    @Column(name = "adresse")
    private String adresse;
    @Column(name = "telephone")
    private String telephone;
    @Column(name = "titre")
    private String titre;
 @Column(name = "nbSignal")
    private int nbSignal;
    @Column(name = "located")
    private Image located;
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    @ManyToOne
    private int idUser;
    @OneToMany(mappedBy = "idLocal")
   
    private List<Location> locationList;

    public Local() {
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    
    public Local(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getNbSignal() {
        return nbSignal;
    }

    public void setNbSignal(int nbSignal) {
        this.nbSignal = nbSignal;
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

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.type = titre;
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

    public Image getLocated() {
        return located;
    }

    public void setLocated(Image located) {
        this.located = located;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
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

    public Local(Integer id, Double superficie, Double prix, String type, String description, String adresse, String telephone) {
        this.id = id;
        this.superficie = superficie;
        this.prix = prix;
        this.type = type;
        this.description = description;
        this.adresse = adresse;
        this.telephone = telephone;
    }
    public Local(Integer id, Double superficie, Double prix, String type, String description, String adresse, String telephone, String image) {
        this.id = id;
        this.superficie = superficie;
        this.prix = prix;
        this.type = type;
        this.description = description;
        this.adresse = adresse;
        this.telephone = telephone;
        this.image = image;
    }

    public Local(Integer id, Double superficie, Double prix, String type) {
        this.id = id;
        this.superficie = superficie;
        this.prix = prix;
        this.type = type;
    }
    
    
    public Local(Integer id,Integer nbSignal, String type, String adresse, String telephone) {
        this.id = id;
        this.nbSignal=nbSignal;
        this.type = type;
        this.adresse = adresse;
        this.telephone= telephone;
                
    }
    public Local (Integer id,Integer nbSignal){
        this.id = id;
    this.nbSignal = nbSignal;
    }

}
