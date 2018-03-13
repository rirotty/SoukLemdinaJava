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
@Table(name = "location")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Location.findAll", query = "SELECT l FROM Location l")
    , @NamedQuery(name = "Location.findById", query = "SELECT l FROM Location l WHERE l.id = :id")
    , @NamedQuery(name = "Location.findByDateDebutLocation", query = "SELECT l FROM Location l WHERE l.dateDebutLocation = :dateDebutLocation")
    , @NamedQuery(name = "Location.findByDateFinLocatiom", query = "SELECT l FROM Location l WHERE l.dateFinLocatiom = :dateFinLocatiom")})
public class Location implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "dateDebutLocation")
    @Temporal(TemporalType.DATE)
    private Date dateDebutLocation;
    @Basic(optional = false)
    @Column(name = "dateFinLocatiom")
    @Temporal(TemporalType.DATE)
    private Date dateFinLocatiom;
    @JoinColumn(name = "id_local", referencedColumnName = "id")
    @ManyToOne
    private Local idLocal;
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    @ManyToOne
    private FosUser idUser;

    public Location() {
    }

    public Location(Integer id) {
        this.id = id;
    }

    public Location(Integer id, Date dateDebutLocation, Date dateFinLocatiom) {
        this.id = id;
        this.dateDebutLocation = dateDebutLocation;
        this.dateFinLocatiom = dateFinLocatiom;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDateDebutLocation() {
        return dateDebutLocation;
    }

    public void setDateDebutLocation(Date dateDebutLocation) {
        this.dateDebutLocation = dateDebutLocation;
    }

    public Date getDateFinLocatiom() {
        return dateFinLocatiom;
    }

    public void setDateFinLocatiom(Date dateFinLocatiom) {
        this.dateFinLocatiom = dateFinLocatiom;
    }

    public Local getIdLocal() {
        return idLocal;
    }

    public void setIdLocal(Local idLocal) {
        this.idLocal = idLocal;
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
        if (!(object instanceof Location)) {
            return false;
        }
        Location other = (Location) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.souklemdina.entities.Location[ id=" + id + " ]";
    }
    
}
