/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.souklemdina.entities;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "fos_user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FosUser.findAll", query = "SELECT f FROM FosUser f")
    , @NamedQuery(name = "FosUser.findById", query = "SELECT f FROM FosUser f WHERE f.id = :id")
    , @NamedQuery(name = "FosUser.findByUsername", query = "SELECT f FROM FosUser f WHERE f.username = :username")
    , @NamedQuery(name = "FosUser.findByUsernameCanonical", query = "SELECT f FROM FosUser f WHERE f.usernameCanonical = :usernameCanonical")
    , @NamedQuery(name = "FosUser.findByEmail", query = "SELECT f FROM FosUser f WHERE f.email = :email")
    , @NamedQuery(name = "FosUser.findByEmailCanonical", query = "SELECT f FROM FosUser f WHERE f.emailCanonical = :emailCanonical")
    , @NamedQuery(name = "FosUser.findByEnabled", query = "SELECT f FROM FosUser f WHERE f.enabled = :enabled")
    , @NamedQuery(name = "FosUser.findBySalt", query = "SELECT f FROM FosUser f WHERE f.salt = :salt")
    , @NamedQuery(name = "FosUser.findByPassword", query = "SELECT f FROM FosUser f WHERE f.password = :password")
    , @NamedQuery(name = "FosUser.findByLastLogin", query = "SELECT f FROM FosUser f WHERE f.lastLogin = :lastLogin")
    , @NamedQuery(name = "FosUser.findByConfirmationToken", query = "SELECT f FROM FosUser f WHERE f.confirmationToken = :confirmationToken")
    , @NamedQuery(name = "FosUser.findByPasswordRequestedAt", query = "SELECT f FROM FosUser f WHERE f.passwordRequestedAt = :passwordRequestedAt")
    , @NamedQuery(name = "FosUser.findByGender", query = "SELECT f FROM FosUser f WHERE f.gender = :gender")
    , @NamedQuery(name = "FosUser.findByFirstname", query = "SELECT f FROM FosUser f WHERE f.firstname = :firstname")
    , @NamedQuery(name = "FosUser.findByLastname", query = "SELECT f FROM FosUser f WHERE f.lastname = :lastname")
    , @NamedQuery(name = "FosUser.findByZipCode", query = "SELECT f FROM FosUser f WHERE f.zipCode = :zipCode")
    , @NamedQuery(name = "FosUser.findByVille", query = "SELECT f FROM FosUser f WHERE f.ville = :ville")
    , @NamedQuery(name = "FosUser.findByPays", query = "SELECT f FROM FosUser f WHERE f.pays = :pays")
    , @NamedQuery(name = "FosUser.findByPhone", query = "SELECT f FROM FosUser f WHERE f.phone = :phone")
    , @NamedQuery(name = "FosUser.findByNbsignal", query = "SELECT f FROM FosUser f WHERE f.nbsignal = :nbsignal")
    , @NamedQuery(name = "FosUser.findByDatenaiss", query = "SELECT f FROM FosUser f WHERE f.datenaiss = :datenaiss")})
public class FosUser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @Column(name = "username_canonical")
    private String usernameCanonical;
    @Basic(optional = false)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @Column(name = "email_canonical")
    private String emailCanonical;
    @Basic(optional = false)
    @Column(name = "enabled")
    private boolean enabled;
    @Column(name = "salt")
    private String salt;
    @Basic(optional = false)
    @Column(name = "password")
    private String password;
    @Column(name = "last_login")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLogin;
    @Column(name = "confirmation_token")
    private String confirmationToken;
    @Column(name = "password_requested_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date passwordRequestedAt;
    @Basic(optional = false)
    @Lob
    @Column(name = "roles")
    private String roles;
    @Column(name = "gender")
    private String gender;
    @Column(name = "firstname")
    private String firstname;
    @Column(name = "lastname")
    private String lastname;
    @Lob
    @Column(name = "rue")
    private String rue;
    @Column(name = "zip_code")
    private Integer zipCode;
    @Column(name = "ville")
    private String ville;
    @Column(name = "pays")
    private String pays;
    @Column(name = "phone")
    private String phone;
    @Column(name = "nbsignal")
    private Integer nbsignal;
    @Basic(optional = false)
    @Column(name = "datenaiss")
    @Temporal(TemporalType.DATE)
    private Date datenaiss;
    @OneToMany(mappedBy = "idUser")
    private List<ParticipantWork> participantWorkList;
    @OneToMany(mappedBy = "idUser")
    private List<Produit> produitList;
    @OneToOne(mappedBy = "idUser")
    private Wishlist wishlist;
    @OneToMany(mappedBy = "idUser")
    private List<Workshop> workshopList;
    @OneToOne(mappedBy = "idUser")
    private Profile profile;
    @OneToMany(mappedBy = "idUser")
    private List<Commande> commandeList;
    @OneToMany(mappedBy = "idUser")
    private List<Local> localList;
    @OneToMany(mappedBy = "idUser")
    private List<Post> postList;
    @OneToMany(mappedBy = "idUser")
    private List<Location> locationList;
    @OneToMany(mappedBy = "idUser")
    private List<Evenement> evenementList;
    @OneToMany(mappedBy = "idUser")
    private List<ParticipantEvents> participantEventsList;

    public FosUser() {
    }

    public FosUser(Integer id) {
        this.id = id;
    }

    public FosUser(Integer id, String username, String usernameCanonical, String email, String emailCanonical, boolean enabled, String password, String roles, Date datenaiss) {
        this.id = id;
        this.username = username;
        this.usernameCanonical = usernameCanonical;
        this.email = email;
        this.emailCanonical = emailCanonical;
        this.enabled = enabled;
        this.password = password;
        this.roles = roles;
        this.datenaiss = datenaiss;
    }

    public FosUser(Integer id, String username, String usernameCanonical, String email, String emailCanonical, boolean enabled, String salt, String password, Date lastLogin, String confirmationToken, Date passwordRequestedAt, String roles, String gender, String firstname, String lastname, String rue, Integer zipCode, String ville, String pays, String phone, Integer nbsignal, Date datenaiss) {
        this.id = id;
        this.username = username;
        this.usernameCanonical = usernameCanonical;
        this.email = email;
        this.emailCanonical = emailCanonical;
        this.enabled = enabled;
        this.salt = salt;
        this.password = password;
        this.lastLogin = lastLogin;
        this.confirmationToken = confirmationToken;
        this.passwordRequestedAt = passwordRequestedAt;
        this.roles = roles;
        this.gender = gender;
        this.firstname = firstname;
        this.lastname = lastname;
        this.rue = rue;
        this.zipCode = zipCode;
        this.ville = ville;
        this.pays = pays;
        this.phone = phone;
        this.nbsignal = nbsignal;
        this.datenaiss = datenaiss;
        
    }

    
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsernameCanonical() {
        return usernameCanonical;
    }

    public void setUsernameCanonical(String usernameCanonical) {
        this.usernameCanonical = usernameCanonical;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmailCanonical() {
        return emailCanonical;
    }

    public void setEmailCanonical(String emailCanonical) {
        this.emailCanonical = emailCanonical;
    }

    public boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getConfirmationToken() {
        return confirmationToken;
    }

    public void setConfirmationToken(String confirmationToken) {
        this.confirmationToken = confirmationToken;
    }

    public Date getPasswordRequestedAt() {
        return passwordRequestedAt;
    }

    public void setPasswordRequestedAt(Date passwordRequestedAt) {
        this.passwordRequestedAt = passwordRequestedAt;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public Integer getZipCode() {
        return zipCode;
    }

    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getNbsignal() {
        return nbsignal;
    }

    public void setNbsignal(Integer nbsignal) {
        this.nbsignal = nbsignal;
    }

    public Date getDatenaiss() {
        return datenaiss;
    }

    public void setDatenaiss(Date datenaiss) {
        this.datenaiss = datenaiss;
    }

    @XmlTransient
    public List<ParticipantWork> getParticipantWorkList() {
        return participantWorkList;
    }

    public void setParticipantWorkList(List<ParticipantWork> participantWorkList) {
        this.participantWorkList = participantWorkList;
    }

    @XmlTransient
    public List<Produit> getProduitList() {
        return produitList;
    }

    public void setProduitList(List<Produit> produitList) {
        this.produitList = produitList;
    }

    public Wishlist getWishlist() {
        return wishlist;
    }

    public void setWishlist(Wishlist wishlist) {
        this.wishlist = wishlist;
    }

    @XmlTransient
    public List<Workshop> getWorkshopList() {
        return workshopList;
    }

    public void setWorkshopList(List<Workshop> workshopList) {
        this.workshopList = workshopList;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    @XmlTransient
    public List<Commande> getCommandeList() {
        return commandeList;
    }

    public void setCommandeList(List<Commande> commandeList) {
        this.commandeList = commandeList;
    }

    @XmlTransient
    public List<Local> getLocalList() {
        return localList;
    }

    public void setLocalList(List<Local> localList) {
        this.localList = localList;
    }

    @XmlTransient
    public List<Post> getPostList() {
        return postList;
    }

    public void setPostList(List<Post> postList) {
        this.postList = postList;
    }

    @XmlTransient
    public List<Location> getLocationList() {
        return locationList;
    }

    public void setLocationList(List<Location> locationList) {
        this.locationList = locationList;
    }

    @XmlTransient
    public List<Evenement> getEvenementList() {
        return evenementList;
    }

    public void setEvenementList(List<Evenement> evenementList) {
        this.evenementList = evenementList;
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
        if (!(object instanceof FosUser)) {
            return false;
        }
        FosUser other = (FosUser) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.souklemdina.entities.FosUser[ id=" + id + " ]";
    }
    
}
