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
import metier.modele.Client;
import metier.modele.Medium;
import metier.modele.ProfilAstral;

/**
 *
 * @author sperrigaul
 */
public class ConsultationSerialisation extends Serialisation {

    @Override
    public void appliquer(HttpServletRequest req, HttpServletResponse res) {
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        JsonObject resultat = new JsonObject();
        JsonObject jsonClient = new JsonObject();
        JsonObject jsonProfil = new JsonObject();
        
        Medium medium = (Medium)req.getAttribute("medium");
        
        resultat.addProperty("mediumId", medium.getId());
        
        
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
