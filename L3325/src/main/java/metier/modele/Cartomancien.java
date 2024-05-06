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
public class Cartomancien extends Medium implements Serializable {
    private static final long serialVersionUID = 1L;  

    public Cartomancien() {
    }

    
    
    public Cartomancien(String denomination, String presentation, String genre) {
        super(denomination, presentation, genre);
    }

    @Override
    public String toString() {
        return "Cartomancien [denomination=" + denomination + ", presentation ="+ presentation + ", genre=" + genre+ "]";
    }
    
    
    
    
}
