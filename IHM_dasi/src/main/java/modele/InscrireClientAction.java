/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import metier.modele.Client;
import metier.service.ServiceClient;
import metier.service.ServiceEmploye;

/**
 *
 * @author sperrigaul
 */
public class InscrireClientAction extends Action {

    public InscrireClientAction(ServiceClient client, ServiceEmploye emplo) {
        super(client, emplo);
    }

    @Override
    public void execute(HttpServletRequest req) {
        System.out.println(req.getParameter("date"));
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = format.parse((String)req.getParameter("date"));
            Client client = new Client(req.getParameter("nom"), req.getParameter("prenom"), req.getParameter("email"), req.getParameter("mdp"), req.getParameter("adresse"), req.getParameter("tel"), date);
            Boolean reussi = this.serviceClient.inscrireClient(client);
            req.setAttribute("client", client);
        } catch (Exception ex) {
        }
    }

}
