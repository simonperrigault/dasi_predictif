/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author sperrigaul
 */
public abstract class Action {
    public Action() {
        
    }
    public abstract void execute(HttpServletRequest request);
}
