/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author sperrigaul
 */
public class AuthentifierUtilisateurAction extends Action {

    @Override
    public void execute(HttpServletRequest request) {
        TestUtilisateur eleve = new TestUtilisateur(new Long(12), "Gabin", "Perrigault", "nicolas.karabatich38@insa-lyon.fr");
        if (!request.getParameter("login").equals(eleve.getMail())) {
            eleve = null;
        }
        request.setAttribute("utilisateur", eleve);
    }
    
}
