/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import metier.modele.Client;
import metier.modele.Consultation;
import metier.modele.Employe;
import metier.modele.Medium;
import metier.service.ServiceClient;
import metier.service.ServiceEmploye;

/**
 *
 * @author sperrigaul
 */
public class GetConsultationsEmployeAction extends Action {

    public GetConsultationsEmployeAction(ServiceClient client, ServiceEmploye emplo) {
        super(client, emplo);
    }

    @Override
    public void execute(HttpServletRequest req) {
        
        HttpSession session = req.getSession(true);
        
        Employe employe = serviceEmploye.rechercherEmployebyID((Long)session.getAttribute("employeId"));
        List<Consultation> listeConsultations = employe.getConsultations();
        System.out.println(employe);
        System.out.println(serviceEmploye.rechercherEmployebyID(employe.getId()));
        System.out.println(listeConsultations);
            
          //List<Consultation> listeConsultations = serviceEmploye.getAllConsultation();

        req.setAttribute("listeConsultations", listeConsultations);
    }
    
}
