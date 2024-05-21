/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import metier.modele.Client;
import metier.modele.Consultation;
import metier.modele.Employe;
import metier.service.ServiceClient;
import metier.service.ServiceEmploye;

/**
 *
 * @author sperrigaul
 */
public class AvoirAideAction extends Action {

    public AvoirAideAction(ServiceClient client, ServiceEmploye emplo) {
        super(client, emplo);
    }

    @Override
    public void execute(HttpServletRequest req) {
        HttpSession session = req.getSession(true);
        
        Employe employe = (Employe)session.getAttribute("employe");
        Consultation consultation = serviceEmploye.obtenirConsultationCourante(employe);
        Client client = consultation.getClient();
        int amour = Integer.parseInt(req.getParameter("amour"));
        int sante = Integer.parseInt(req.getParameter("sante"));
        int travail = Integer.parseInt(req.getParameter("travail"));
        Map<String, String> mapAide = null;
        try {
            mapAide = serviceEmploye.avoirAide(amour, sante, travail, client);
        } catch (IOException ex) {
            
        }
        req.setAttribute("mapAide", mapAide);
    }
    
}
