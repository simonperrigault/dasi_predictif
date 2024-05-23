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
import metier.service.ServiceClient;
import metier.service.ServiceEmploye;

/**
 *
 * @author gjoussotdu
 */
public class ModifierClientAction extends Action {
    public ModifierClientAction(ServiceClient client, ServiceEmploye emplo) {
        super(client, emplo);
    }

    //req.getParameter("client")
    @Override
    public void execute(HttpServletRequest req) {
     
        HttpSession session = req.getSession(true);
        Client client = (Client)serviceClient.rechercherClientbyID((Long)session.getAttribute("clientId"));
        System.out.println(client);
        client.setNom(req.getParameter("nom"));
        client.setPrenom(req.getParameter("prenom"));
        client.setMotDePasse(req.getParameter("mdp"));
        client.setAdressePostale(req.getParameter("adresse"));
        client.setMail(req.getParameter("email"));
        client.setNumTel(req.getParameter("tel"));  
        System.out.println(client);
  
        try{
            
            this.serviceClient.modifierClient(client);
          
        }
        catch(IOException ex)
        {
            System.out.println("Erreur du web durant la mise a jour du profil client");
        }
        
        req.setAttribute("client", client);
     
        
        
    }
}
