/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.modele;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author slouvetdem
 */
@Entity
public class ProfilAstral implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String couleur;
    private String totem;
    private String signeChinois;
    private String signeZodiaque;

    public ProfilAstral() {}
    
    public ProfilAstral(String couleur, String totem, String signeChinois, String signeZodiaque) {
        this.couleur = couleur;
        this.totem = totem;
        this.signeChinois = signeChinois;
        this.signeZodiaque = signeZodiaque;
    }

    
    
    //gettters
    
    public Long getId() {
        return id;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getCouleur() {
        return couleur;
    }

    public String getTotem() {
        return totem;
    }

    public String getSigneChinois() {
        return signeChinois;
    }

    public String getSigneZodiaque() {
        return signeZodiaque;
    }
    
    //setters


    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public void setTotem(String totem) {
        this.totem = totem;
    }

    public void setSigneChinois(String signeChinois) {
        this.signeChinois = signeChinois;
    }

    public void setSigneZodiaque(String signeZodiaque) {
        this.signeZodiaque = signeZodiaque;
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
        if (!(object instanceof ProfilAstral)) {
            return false;
        }
        ProfilAstral other = (ProfilAstral) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ProfilAstral{" + "couleur=" + couleur + ", totem=" + totem + ", signeChinois=" + signeChinois + ", signeZodiaque=" + signeZodiaque + '}';
    }

    
    
}
