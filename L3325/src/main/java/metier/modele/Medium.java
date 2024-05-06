/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.modele;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

/**
 *
 * @author slouvetdem
 */
@Entity
@Inheritance (strategy = InheritanceType.JOINED)
public class Medium implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    protected String denomination;
    protected String presentation;
    protected String genre;
    private int nombreChoisi;
    private String cheminPhotoProfil;
    @OneToMany
    private List<Consultation> consultations;

    public Medium() {
    }

    public Medium(String denomination, String presentation, String genre) {
        this.denomination = denomination;
        this.presentation = presentation;
        this.genre = genre;
    }
    
    
    
    //getters
    
    public int getNombreChoisi() {
        return nombreChoisi;
    }

    public List<Consultation> getConsultations() {
        return consultations;
    }

    public Long getId() {
        return id;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getDenomination() {
        return denomination;
    }

    public String getPresentation() {
        return presentation;
    }

    public String getGenre() {
        return genre;
    }
    
    //setters

    public void setDenomination(String denomination) {
        this.denomination = denomination;
    }

    public void setPresentation(String presentation) {
        this.presentation = presentation;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setCheminPhotoProfil(String cheminPhotoProfil) {
        this.cheminPhotoProfil = cheminPhotoProfil;
    }

    public void setNombreChoisi(int nombreChoisi) {
        this.nombreChoisi = nombreChoisi;
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
        if (!(object instanceof Medium)) {
            return false;
        }
        Medium other = (Medium) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "metier.modele.Medium[ id=" + id + " ]";
    }
    
}
