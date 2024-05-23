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
public class AuthentifierClientAction extends Action {

    public AuthentifierClientAction(ServiceClient client, ServiceEmploye emplo) {
        super(client, emplo);
    }

    @Override
    public void execute(HttpServletRequest req) {
        Client client = this.serviceClient.authentifierClient(req.getParameter("email"), req.getParameter("mdp"));
        req.setAttribute("client", client);
        HttpSession session = req.getSession(true);
        if (client != null) {
            session.setAttribute("clientId", client.getId());
        }
    }

}
