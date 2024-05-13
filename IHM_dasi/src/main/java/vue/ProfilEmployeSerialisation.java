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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import metier.modele.Employe;

/**
 *
 * @author sperrigaul
 */
public class ProfilEmployeSerialisation extends Serialisation {

    @Override
    public void appliquer(HttpServletRequest req, HttpServletResponse res) {
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        JsonObject resultat = new JsonObject();
        JsonObject jsonEmploye = new JsonObject();
        
        Employe employe = (Employe)req.getAttribute("employe");
        if (employe != null) {
            resultat.addProperty("connexion", true);
            jsonEmploye.addProperty("id", employe.getId());
            jsonEmploye.addProperty("nom", employe.getNom());
            jsonEmploye.addProperty("prenom", employe.getPrenom());
            jsonEmploye.addProperty("mail", employe.getMail());
            jsonEmploye.addProperty("telephone", employe.getTelephone());
            jsonEmploye.addProperty("genre", employe.getGenre());
            jsonEmploye.addProperty("disponible", employe.getDisponible());
            jsonEmploye.addProperty("nombreConsultations", employe.getNombreConsult());

            resultat.add("employe", jsonEmploye);
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
            
        }
    }
    
}
