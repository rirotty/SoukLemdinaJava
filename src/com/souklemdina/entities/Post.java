/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.souklemdina.entities;

import java.io.Serializable;
import java.sql.Timestamp;
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
@Table(name = "post")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Post.findAll", query = "SELECT p FROM Post p")
    , @NamedQuery(name = "Post.findById", query = "SELECT p FROM Post p WHERE p.id = :id")
    , @NamedQuery(name = "Post.findByDate", query = "SELECT p FROM Post p WHERE p.date = :date")
    , @NamedQuery(name = "Post.findByTexte", query = "SELECT p FROM Post p WHERE p.texte = :texte")
    , @NamedQuery(name = "Post.findByTitre", query = "SELECT p FROM Post p WHERE p.titre = :titre")
    , @NamedQuery(name = "Post.findByNbSignal", query = "SELECT p FROM Post p WHERE p.nbSignal = :nbSignal")
    , @NamedQuery(name = "Post.findByImage", query = "SELECT p FROM Post p WHERE p.image = :image")
    , @NamedQuery(name = "Post.findByUpdatedAt", query = "SELECT p FROM Post p WHERE p.updatedAt = :updatedAt")})
public class Post implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp date;
    @Column(name = "texte")
    private String texte;
    @Basic(optional = false)
    @Column(name = "titre")
    private String titre;
    @Column(name = "nb_signal")
    private Integer nbSignal;
    @Column(name = "image")
    private String image;
    @Basic(optional = false)
    @Column(name = "updated_at")
    private Timestamp updatedAt;
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    @ManyToOne
    private Integer idUser;

    public Post() {
    }

    public Post(Integer id) {
        this.id = id;
    }

    public Post(Integer id, Timestamp date, String titre, Timestamp updatedAt) {
        this.id = id;
        this.date = date;
        this.titre = titre;
        this.updatedAt = updatedAt;
    }

    public Post(Integer id, Timestamp date, String texte, String titre, Integer nbSignal, String image, Timestamp updatedAt, Integer idUser) {
        this.id = id;
        this.date = date;
        this.texte = texte;
        this.titre = titre;
        this.nbSignal = nbSignal;
        this.image = image;
        this.updatedAt = updatedAt;
        this.idUser = idUser;
    }
    
    public Post(Timestamp date, String texte, String titre, Integer nbSignal, String image, Timestamp updatedAt, Integer idUser) {
        this.date = date;
        this.texte = texte;
        this.titre = titre;
        this.nbSignal = nbSignal;
        this.image = image;
        this.updatedAt = updatedAt;
        this.idUser = idUser;
    }
    
    public Post(Integer id, Timestamp date, String texte, String titre, String image, Timestamp updatedAt, Integer idUser) {
        this.id = id;
        this.date = date;
        this.texte = texte;
        this.titre = titre;
        this.image = image;
        this.updatedAt = updatedAt;
        this.idUser = idUser;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getTexte() {
        return texte;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Integer getNbSignal() {
        return nbSignal;
    }

    public void setNbSignal(Integer nbSignal) {
        this.nbSignal = nbSignal;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
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
        if (!(object instanceof Post)) {
            return false;
        }
        Post other = (Post) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.souklemdina.entities.Post[ id=" + id + " ]";
    }
    
}
