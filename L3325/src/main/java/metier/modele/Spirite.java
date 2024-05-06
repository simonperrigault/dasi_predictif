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
public class Spirite extends Medium implements Serializable {

    private static final long serialVersionUID = 1L;    

    private String support;

    public Spirite() {
    }

    public Spirite(String support, String denomination, String presentation, String genre) {
        super(denomination, presentation, genre);
        this.support = support;
    }

    @Override
    public String toString() {
        return "Spirite{" + "support=" + support + '}';
    }
    
    

 

    
}
