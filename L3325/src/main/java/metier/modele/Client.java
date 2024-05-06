/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.modele;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author slouvetdem
 */
@Entity
public class Client implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nom;
    private String prenom;
    @Column(unique = true)
    private String mail;
    private String motDePasse;
    private String adressePostale;
    private Double latitude;
    private Double longitude;
    private String numTel;
    @Temporal(TemporalType.DATE)
    private Date dateNaissance;
    @OneToOne
    private ProfilAstral profil;
    @OneToMany
    private List<Consultation> consultations;

    
    //constructors

    public Client() {}

    public Client(String nom, String prenom, String mail, String motDePasse, String adressePostale, String numTel, Date dateNaissance) throws IOException {
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.motDePasse = motDePasse;
        this.adressePostale = adressePostale;
        this.numTel = numTel;
        this.dateNaissance = dateNaissance;
        
    }

    

    //getters 
    
    public List<Consultation> getConsultations() {
        return consultations;
    }

    public ProfilAstral getProfil() {
        return profil;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getMail() {
        return mail;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public String getNumTel() {
        return numTel;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public String getAdressePostale() {
        return adressePostale;
    }
 
    
       
    //setters 
    
    public void setProfil(ProfilAstral profil) {
        this.profil = profil;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public void setAdressePostale(String adressePostale) {
        this.adressePostale = adressePostale;
    }
    
    

    @Override
    public String toString() {
        return "Client{" + "nom=" + nom + ", prenom=" + prenom + ", mail=" + mail + ", numTel=" + numTel + ", dateNaissance=" + dateNaissance + '}';
    }
    
    
    
    

    
}
