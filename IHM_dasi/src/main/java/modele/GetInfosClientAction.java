/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import metier.modele.Client;
import metier.service.ServiceClient;
import metier.service.ServiceEmploye;

/**
 *
 * @author sperrigaul
 */
public class GetInfosClientAction extends Action {

    public GetInfosClientAction(ServiceClient client, ServiceEmploye emplo) {
        super(client, emplo);
    }

    @Override
    public void execute(HttpServletRequest req) {
        HttpSession session = req.getSession(true);
        
        Client client = (Client)serviceClient.rechercherClientbyID((Long)session.getAttribute("clientId"));
        req.setAttribute("client", client);
    }

}
