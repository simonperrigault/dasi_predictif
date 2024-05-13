/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.modele;

import java.io.Serializable;
import javax.persistence.Entity;



/**
 *
 * @author slouvetdem
 */
@Entity
public class Astrologue extends Medium implements Serializable {
    private static final long serialVersionUID = 1L;  
    
    private String formation;
    private String promotion;

    public Astrologue() {
    }

    public Astrologue(String formation, String promotion, String denomination, String presentation, String genre) {
        super(denomination, presentation, genre);
        this.formation = formation;
        this.promotion = promotion;
    }

    
    @Override
    public String toString() {
        return "Astrologue{" + "formation=" + formation + ", promotion=" + promotion + '}';
    }

    public String getFormation() {
        return formation;
    }

    public void setFormation(String formation) {
        this.formation = formation;
    }

    public String getPromotion() {
        return promotion;
    }

    public void setPromotion(String promotion) {
        this.promotion = promotion;
    }
    
    
    
    
}
