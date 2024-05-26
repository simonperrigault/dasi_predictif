/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.modele;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author slouvetdem
 */
@Entity
public class Employe implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nom;
    private String prenom;
    @Column(unique = true)
    private String mail;
    private String motDePasse;
    private String telephone;
    private String genre;
    private boolean disponible;
    private int nombreConsult;
    
    @OneToMany
    private List<Consultation> consultations;

    public Employe() {
    }

    public Employe(String nom, String prenom,String mail, String motDePasse,String telephone, String genre) {
        this.nom = nom;
        this.prenom = prenom;
        this.motDePasse = motDePasse;
        this.mail = mail;
        this.telephone = telephone;
        this.genre = genre;
        this.disponible = true;
        this.nombreConsult = 0;
    }
    
    public boolean isDisponible() {    
        return disponible;
    }

    public int getNombreConsult() {
        return nombreConsult;
    }

    //getters
    public List<Consultation> getConsultations() {    
        return consultations;
    }

    public Long getId() {
        return id;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
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

    public String getTelephone() {
        return telephone;
    }

    public String getGenre() {
        return genre;
    }

    public boolean getDisponible() {
        return disponible;
    }
    
    

    //setters

    public void setNombreConsult(int nombreConsult) {
        this.nombreConsult = nombreConsult;
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

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
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
        if (!(object instanceof Employe)) {
            return false;
        }
        Employe other = (Employe) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Employe{" + "nom=" + nom + ", prenom=" + prenom + ", mail=" + mail + ", telephone=" + telephone + '}';
    }

    

    
    
}
