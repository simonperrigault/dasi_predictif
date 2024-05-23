/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import metier.modele.Consultation;
import metier.modele.Employe;
import metier.service.ServiceClient;
import metier.service.ServiceEmploye;

/**
 *
 * @author sperrigaul
 */
public class ValiderConsultationAction extends Action {

    public ValiderConsultationAction(ServiceClient client, ServiceEmploye emplo) {
        super(client, emplo);
    }

    @Override
    public void execute(HttpServletRequest req) {
        HttpSession session = req.getSession(true);
        
        Employe employe = serviceEmploye.rechercherEmployebyID((Long)session.getAttribute("employeId"));
        Consultation consultation = serviceEmploye.obtenirConsultationCourante(employe);
        Boolean res = serviceEmploye.validerConsultation(consultation);
        req.setAttribute("res", res);
    }
    
}
