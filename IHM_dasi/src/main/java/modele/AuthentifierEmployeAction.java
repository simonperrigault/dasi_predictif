/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import javax.servlet.http.HttpServletRequest;
import metier.modele.Employe;
import metier.service.ServiceClient;
import metier.service.ServiceEmploye;

/**
 *
 * @author sperrigaul
 */
public class AuthentifierEmployeAction extends Action {

    public AuthentifierEmployeAction(ServiceClient client, ServiceEmploye emplo) {
        super(client, emplo);
    }

    @Override
    public void execute(HttpServletRequest req) {
        Employe employe = this.serviceEmploye.authentifierEmploye(req.getParameter("email"), req.getParameter("mdp"));
        req.setAttribute("employe", employe);
    }
    
}
