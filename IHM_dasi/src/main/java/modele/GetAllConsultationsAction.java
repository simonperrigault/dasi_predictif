/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import metier.modele.Consultation;
import metier.service.ServiceClient;
import metier.service.ServiceEmploye;

/**
 *
 * @author sperrigaul
 */
public class GetAllConsultationsAction extends Action {
    public GetAllConsultationsAction(ServiceClient client, ServiceEmploye emplo) {
        super(client, emplo);
    }

    @Override
    public void execute(HttpServletRequest req) {
        List<Consultation> liste = this.serviceEmploye.getAllConsultation();
        System.out.println(liste);
        req.setAttribute("listeConsultations", liste);
    }
}
