/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.modele;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author slouvetdem
 */
@Entity
public class Consultation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Temporal(TemporalType.DATE)
    private Date dateConsult; //initialisé à la date de demande de consultation par le client, puis ecrasée par la date de validation par l'employé (utilisée pour calculer la durée)
    private long duree_ms;
    private String duree;
    private String commentaire;
    
    @ManyToOne
    private Client client;
    @ManyToOne
    private Employe employe;
    @ManyToOne
    private Medium medium;

    public Consultation() {
    }

    public Consultation(Client client, Medium medium) {

        this.client = client;
        this.medium = medium;
    }

    public long getDuree_ms() {
        return duree_ms;
    }
    
    public Client getClient() {
        return client;
    }

    public Employe getEmploye() {
        return employe;
    }

    //getters
    public Medium getMedium() {    
        return medium;
    }

    public Long getId() {
        return id;
    }

    public String getDuree() {
        return duree;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Date getDateConsult() {
        return dateConsult;
    }

   

    public String getCommentaire() {
        return commentaire;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    //setters
    public void setMedium(Medium medium) {
        this.medium = medium;
    }

    public void setDateConsult(Date dateConsult) {
        this.dateConsult = dateConsult;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    public void setDuree_ms(long duree_ms) {
        this.duree_ms = duree_ms;
    }

    

    

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
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
        if (!(object instanceof Consultation)) {
            return false;
        }
        Consultation other = (Consultation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Consultation{" + "id=" + id + ", dateConsult=" + dateConsult + ", duree=" + duree + ", commentaire=" + commentaire + ", client=" + client + ", employe=" + employe + ", medium=" + medium + '}';
    }

    
    
}
