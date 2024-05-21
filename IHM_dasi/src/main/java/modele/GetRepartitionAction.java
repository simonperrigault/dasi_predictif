/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.io.IOException;
import java.util.Map;
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
public class GetRepartitionAction extends Action {

    public GetRepartitionAction(ServiceClient client, ServiceEmploye emplo) {
        super(client, emplo);
    }

    @Override
    public void execute(HttpServletRequest req) {
        
        Map<Employe, Double> mapRepartition = serviceEmploye.repartitionEmployeClient();

        req.setAttribute("mapRepartition", mapRepartition);
    }
    
}
