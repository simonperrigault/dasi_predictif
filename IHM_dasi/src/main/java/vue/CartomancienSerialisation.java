/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modele.TestUtilisateur;

/**
 *
 * @author sperrigaul
 */
public class CartomancienSerialisation extends Serialisation {

    @Override
    public void appliquer(HttpServletRequest req, HttpServletResponse res) {
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        JsonObject jsonEleve = new JsonObject();
        JsonObject resultat = new JsonObject();
        
        TestUtilisateur medium = (TestUtilisateur)req.getAttribute("utilisateur");
        if (medium != null) {
//            resultat.addProperty("connexion", true);
//            jsonEleve.addProperty("dénomination", medium.getDenomination());
//            jsonEleve.addProperty("presentation", medium.getPresentation());
//            jsonEleve.addProperty("genre", medium.getGenre());
//            jsonEleve.addProperty("nombreChoisi", medium.getNombreChoisi());
//            jsonEleve.addProperty("cheminPhotoProfil", medium.getCheminPhotoProfil());

            resultat.add("utilisateur", jsonEleve);
        }
        else {
            resultat.addProperty("connexion", false);
        }
        
        res.setContentType("application/json;charset=UTF-8");
        PrintWriter out;
        try {
            out = res.getWriter();
            out.println(gson.toJson(resultat));
            out.close();
        } catch (IOException ex) {
            Logger.getLogger(ProfilUtilisateurSerialisation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
