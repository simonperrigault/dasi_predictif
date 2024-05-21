/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.text.ParseException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import metier.modele.Client;
import metier.modele.Consultation;
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
    public void execute(HttpServletRequest req) {
        HttpSession session = req.getSession(true);
        System.out.println(req.getParameter("mediumId"));
        Medium medium = this.serviceClient.rechercherMediumbyID(Long.parseLong(req.getParameter("mediumId")));
        Client client = (Client) session.getAttribute("client");
        Consultation consultation;
        System.out.println(medium);
        System.out.println(client);
        try {
            consultation = this.serviceClient.demanderConsultation(medium, client);
        } catch (ParseException ex) {
            consultation = null;
        }
        req.setAttribute("consultation", consultation);
    }
    
}
