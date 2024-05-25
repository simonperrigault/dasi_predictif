/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import metier.modele.Client;
import metier.modele.Employe;
import metier.service.ServiceClient;
import metier.service.ServiceEmploye;

/**
 *
 * @author gjoussotdu
 */
public class ModifierEmployeAction extends Action {
    public ModifierEmployeAction(ServiceClient client, ServiceEmploye emplo) {
        super(client, emplo);
    }

    //req.getParameter("client")
    @Override
    public void execute(HttpServletRequest req) {
     
        HttpSession session = req.getSession(true);
        Employe employe = (Employe)serviceEmploye.rechercherEmployebyID((Long)session.getAttribute("employeId"));
        System.out.println(employe);
        employe.setNom(req.getParameter("nom"));
        employe.setPrenom(req.getParameter("prenom"));
        employe.setMotDePasse(req.getParameter("mdp"));
        employe.setMail(req.getParameter("email"));
        employe.setTelephone(req.getParameter("tel"));  
        System.out.println(employe);
  
        try{
            
            this.serviceEmploye.modifierEmploye(employe);
          
        }
        catch(IOException ex)
        {
            System.out.println("Erreur du web durant la mise a jour du profil employe");
        }
        
        req.setAttribute("client", employe);
     
        
        
    }
}
