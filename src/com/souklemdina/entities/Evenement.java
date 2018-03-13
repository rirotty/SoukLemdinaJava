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
@Table(name = "evenement")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Evenement.findAll", query = "SELECT e FROM Evenement e")
    , @NamedQuery(name = "Evenement.findById", query = "SELECT e FROM Evenement e WHERE e.id = :id")
    , @NamedQuery(name = "Evenement.findByNomEvenement", query = "SELECT e FROM Evenement e WHERE e.nomEvenement = :nomEvenement")
    , @NamedQuery(name = "Evenement.findByDateDebut", query = "SELECT e FROM Evenement e WHERE e.dateDebut = :dateDebut")
    , @NamedQuery(name = "Evenement.findByDateFin", query = "SELECT e FROM Evenement e WHERE e.dateFin = :dateFin")
    , @NamedQuery(name = "Evenement.findByAdresse", query = "SELECT e FROM Evenement e WHERE e.adresse = :adresse")
    , @NamedQuery(name = "Evenement.findByPrix", query = "SELECT e FROM Evenement e WHERE e.prix = :prix")
    , @NamedQuery(name = "Evenement.findByDescription", query = "SELECT e FROM Evenement e WHERE e.description = :description")
    , @NamedQuery(name = "Evenement.findByNbPlace", query = "SELECT e FROM Evenement e WHERE e.nbPlace = :nbPlace")
    , @NamedQuery(name = "Evenement.findByNbSignal", query = "SELECT e FROM Evenement e WHERE e.nbSignal = :nbSignal")
    , @NamedQuery(name = "Evenement.findByType", query = "SELECT e FROM Evenement e WHERE e.type = :type")
    , @NamedQuery(name = "Evenement.findByHeure", query = "SELECT e FROM Evenement e WHERE e.heure = :heure")
    , @NamedQuery(name = "Evenement.findByPhoto", query = "SELECT e FROM Evenement e WHERE e.photo = :photo")
    , @NamedQuery(name = "Evenement.findByRating", query = "SELECT e FROM Evenement e WHERE e.rating = :rating")
    , @NamedQuery(name = "Evenement.findByNbrrating", query = "SELECT e FROM Evenement e WHERE e.nbrrating = :nbrrating")
    , @NamedQuery(name = "Evenement.findByNbruser", query = "SELECT e FROM Evenement e WHERE e.nbruser = :nbruser")})
public class Evenement implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nomEvenement")
    private String nomEvenement;
    @Basic(optional = false)
    @Column(name = "dateDebut")
    @Temporal(TemporalType.DATE)
    private Date dateDebut;
    @Basic(optional = false)
    @Column(name = "dateFin")
    @Temporal(TemporalType.DATE)
    private Date dateFin;
    @Basic(optional = false)
    @Column(name = "adresse")
    private String adresse;
    @Basic(optional = false)
    @Column(name = "prix")
    private double prix;
    @Basic(optional = false)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @Column(name = "nbPlace")
    private int nbPlace;
    @Column(name = "nbSignal")
    private Integer nbSignal;
    @Basic(optional = false)
    @Column(name = "type")
    private String type;
    @Basic(optional = false)
    @Column(name = "heure")
    @Temporal(TemporalType.TIME)
    private Date heure;
    @Column(name = "photo")
    private String photo;
    @Column(name = "rating")
    private Integer rating;
    @Column(name = "nbrrating")
    private Integer nbrrating;
    @Column(name = "nbruser")
    private Integer nbruser;
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    @ManyToOne
    private FosUser idUser;
    @OneToMany(mappedBy = "idEvenement")
    private List<ParticipantEvents> participantEventsList;

    public Evenement() {
    }

    public Evenement(Integer id) {
        this.id = id;
    }

    public Evenement(Integer id, String nomEvenement, Date dateDebut, Date dateFin, String adresse, double prix, String description, int nbPlace, String type, Date heure) {
        this.id = id;
        this.nomEvenement = nomEvenement;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.adresse = adresse;
        this.prix = prix;
        this.description = description;
        this.nbPlace = nbPlace;
        this.type = type;
        this.heure = heure;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomEvenement() {
        return nomEvenement;
    }

    public void setNomEvenement(String nomEvenement) {
        this.nomEvenement = nomEvenement;
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

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNbPlace() {
        return nbPlace;
    }

    public void setNbPlace(int nbPlace) {
        this.nbPlace = nbPlace;
    }

    public Integer getNbSignal() {
        return nbSignal;
    }

    public void setNbSignal(Integer nbSignal) {
        this.nbSignal = nbSignal;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getHeure() {
        return heure;
    }

    public void setHeure(Date heure) {
        this.heure = heure;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Integer getNbrrating() {
        return nbrrating;
    }

    public void setNbrrating(Integer nbrrating) {
        this.nbrrating = nbrrating;
    }

    public Integer getNbruser() {
        return nbruser;
    }

    public void setNbruser(Integer nbruser) {
        this.nbruser = nbruser;
    }

    public FosUser getIdUser() {
        return idUser;
    }

    public void setIdUser(FosUser idUser) {
        this.idUser = idUser;
    }

    @XmlTransient
    public List<ParticipantEvents> getParticipantEventsList() {
        return participantEventsList;
    }

    public void setParticipantEventsList(List<ParticipantEvents> participantEventsList) {
        this.participantEventsList = participantEventsList;
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
        if (!(object instanceof Evenement)) {
            return false;
        }
        Evenement other = (Evenement) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.souklemdina.entities.Evenement[ id=" + id + " ]";
    }
    
}
