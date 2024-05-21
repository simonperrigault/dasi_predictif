/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import javax.servlet.http.HttpServletRequest;
import metier.modele.Medium;
import metier.service.ServiceClient;
import metier.service.ServiceEmploye;

/**
 *
 * @author sperrigaul
 */
public class creerConsultationAction extends Action {

    public creerConsultationAction(ServiceClient client, ServiceEmploye emplo) {
        super(client, emplo);
    }

    @Override
    public void execute(HttpServletRequest request) {
        Medium medium = this.serviceClient.rechercherMediumbyID(Long.parseLong(request.getParameter("mediumId")));
        request.setAttribute("medium", medium);
    }
    
}
