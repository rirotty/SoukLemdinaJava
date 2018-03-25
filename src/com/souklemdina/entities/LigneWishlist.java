/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.souklemdina.entities;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ramyk
 */
@Entity
@Table(name = "ligne_wishlist")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LigneWishlist.findAll", query = "SELECT l FROM LigneWishlist l")
    , @NamedQuery(name = "LigneWishlist.findById", query = "SELECT l FROM LigneWishlist l WHERE l.id = :id")})
public class LigneWishlist implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "idProduit", referencedColumnName = "id")
    @ManyToOne
    private Produit idProduit;
    @JoinColumn(name = "idWishlist", referencedColumnName = "id")
    @ManyToOne
    private Wishlist idWishlist;

    public LigneWishlist() {
    }

    public LigneWishlist(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Produit getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(Produit idProduit) {
        this.idProduit = idProduit;
    }

    public Wishlist getIdWishlist() {
        return idWishlist;
    }

    public void setIdWishlist(Wishlist idWishlist) {
        this.idWishlist = idWishlist;
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
        if (!(object instanceof LigneWishlist)) {
            return false;
        }
        LigneWishlist other = (LigneWishlist) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.souklemdina.entities.LigneWishlist[ id=" + id + " ]";
    }
    
}
