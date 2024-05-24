/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import metier.modele.Consultation;
import metier.service.ServiceClient;
import metier.service.ServiceEmploye;

/**
 *
 * @author gjoussotdu
 */
public class DeconnexionAction extends Action {
    public DeconnexionAction(ServiceClient client, ServiceEmploye emplo) {
        super(client, emplo);
    }
    
    @Override
    public void execute(HttpServletRequest req) {
        HttpSession session = req.getSession();
        session.invalidate();
        req.setAttribute("res", true);
    }
}
