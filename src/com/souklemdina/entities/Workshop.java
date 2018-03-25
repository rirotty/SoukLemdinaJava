/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.souklemdina.entities;

import java.io.Serializable;
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
@Table(name = "workshop")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Workshop.findAll", query = "SELECT w FROM Workshop w")
    , @NamedQuery(name = "Workshop.findById", query = "SELECT w FROM Workshop w WHERE w.id = :id")
    , @NamedQuery(name = "Workshop.findByNomWorkshop", query = "SELECT w FROM Workshop w WHERE w.nomWorkshop = :nomWorkshop")
    , @NamedQuery(name = "Workshop.findByAdresse", query = "SELECT w FROM Workshop w WHERE w.adresse = :adresse")
    , @NamedQuery(name = "Workshop.findByType", query = "SELECT w FROM Workshop w WHERE w.type = :type")
    , @NamedQuery(name = "Workshop.findByDateDebut", query = "SELECT w FROM Workshop w WHERE w.dateDebut = :dateDebut")
    , @NamedQuery(name = "Workshop.findByDateFin", query = "SELECT w FROM Workshop w WHERE w.dateFin = :dateFin")
    , @NamedQuery(name = "Workshop.findByNbPlace", query = "SELECT w FROM Workshop w WHERE w.nbPlace = :nbPlace")
    , @NamedQuery(name = "Workshop.findByPrix", query = "SELECT w FROM Workshop w WHERE w.prix = :prix")
    , @NamedQuery(name = "Workshop.findByNbSignal", query = "SELECT w FROM Workshop w WHERE w.nbSignal = :nbSignal")
    , @NamedQuery(name = "Workshop.findByDescription", query = "SELECT w FROM Workshop w WHERE w.description = :description")
    , @NamedQuery(name = "Workshop.findByVideo", query = "SELECT w FROM Workshop w WHERE w.video = :video")
    , @NamedQuery(name = "Workshop.findByPlanning", query = "SELECT w FROM Workshop w WHERE w.planning = :planning")
    , @NamedQuery(name = "Workshop.findByImage", query = "SELECT w FROM Workshop w WHERE w.image = :image")
    , @NamedQuery(name = "Workshop.findByUpdatedAt", query = "SELECT w FROM Workshop w WHERE w.updatedAt = :updatedAt")})
public class Workshop implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nomWorkshop")
    private String nomWorkshop;
    @Basic(optional = false)
    @Column(name = "adresse")
    private String adresse;
    @Basic(optional = false)
    @Column(name = "type")
    private String type;
    @Basic(optional = false)
    @Column(name = "dateDebut")
    @Temporal(TemporalType.DATE)
    private Date dateDebut;
    @Basic(optional = false)
    @Column(name = "dateFin")
    @Temporal(TemporalType.DATE)
    private Date dateFin;
    @Basic(optional = false)
    @Column(name = "nbPlace")
    private int nbPlace;
    @Basic(optional = false)
    @Column(name = "prix")
    private double prix;
    @Column(name = "nbSignal")
    private Integer nbSignal;
    @Basic(optional = false)
    @Column(name = "description")
    private String description;
    @Column(name = "video")
    private String video;
    @Column(name = "planning")
    private String planning;
    @Column(name = "image")
    private String image;
    @Basic(optional = false)
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @OneToMany(mappedBy = "idWorkshop")
    private List<ParticipantWork> participantWorkList;
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    @ManyToOne
    private FosUser idUser;

    public Workshop() {
    }

    public Workshop(Integer id) {
        this.id = id;
    }

    public Workshop(Integer id, String nomWorkshop, String adresse, String type, Date dateDebut, Date dateFin, int nbPlace, double prix, String description, Date updatedAt) {
        this.id = id;
        this.nomWorkshop = nomWorkshop;
        this.adresse = adresse;
        this.type = type;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.nbPlace = nbPlace;
        this.prix = prix;
        this.description = description;
        this.updatedAt = updatedAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomWorkshop() {
        return nomWorkshop;
    }

    public void setNomWorkshop(String nomWorkshop) {
        this.nomWorkshop = nomWorkshop;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public int getNbPlace() {
        return nbPlace;
    }

    public void setNbPlace(int nbPlace) {
        this.nbPlace = nbPlace;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public Integer getNbSignal() {
        return nbSignal;
    }

    public void setNbSignal(Integer nbSignal) {
        this.nbSignal = nbSignal;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getPlanning() {
        return planning;
    }

    public void setPlanning(String planning) {
        this.planning = planning;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @XmlTransient
    public List<ParticipantWork> getParticipantWorkList() {
        return participantWorkList;
    }

    public void setParticipantWorkList(List<ParticipantWork> participantWorkList) {
        this.participantWorkList = participantWorkList;
    }

    public FosUser getIdUser() {
        return idUser;
    }

    public void setIdUser(FosUser idUser) {
        this.idUser = idUser;
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
        if (!(object instanceof Workshop)) {
            return false;
        }
        Workshop other = (Workshop) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.souklemdina.entities.Workshop[ id=" + id + " ]";
    }
    
}
