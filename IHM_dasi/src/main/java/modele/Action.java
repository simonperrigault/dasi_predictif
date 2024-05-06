/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import javax.servlet.http.HttpServletRequest;
import metier.service.ServiceClient;
import metier.service.ServiceEmploye;

/**
 *
 * @author sperrigaul
 */
public abstract class Action {
    protected ServiceClient serviceClient;
    protected ServiceEmploye serviceEmploye;
    
    public Action(ServiceClient client, ServiceEmploye emplo) {
        this.serviceClient = client;
        this.serviceEmploye = emplo;
    }
    public abstract void execute(HttpServletRequest request);
}
