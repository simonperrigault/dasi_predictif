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
import metier.modele.Medium;
import metier.modele.Astrologue;
import metier.modele.Spirite;

/**
 *
 * @author sperrigaul
 */
public class ProfilMediumSerialisation extends Serialisation {

    @Override
    public void appliquer(HttpServletRequest req, HttpServletResponse res) {
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        JsonObject jsonMedium = new JsonObject();
        JsonObject resultat = new JsonObject();
        
        Medium medium = (Medium)req.getAttribute("medium");
        if (medium != null) {
            jsonMedium.addProperty("id", medium.getId());
            jsonMedium.addProperty("denomination", medium.getDenomination());
            jsonMedium.addProperty("presentation", medium.getPresentation());
            jsonMedium.addProperty("genre", medium.getGenre());
            jsonMedium.addProperty("nombreChoisi", medium.getNombreChoisi());
            jsonMedium.addProperty("image", medium.getCheminPhotoProfil());
            if (medium instanceof Astrologue) {
                Astrologue astro = (Astrologue)medium;
                jsonMedium.addProperty("formation", astro.getFormation());
                jsonMedium.addProperty("promotion", astro.getPromotion());
                resultat.addProperty("type", "astrologue");
            }
            else if (medium instanceof Spirite) {
                Spirite spi = (Spirite)medium;
                jsonMedium.addProperty("support", spi.getSupport());
                resultat.addProperty("type", "spirite");
            }
            else {
                resultat.addProperty("type", "cartomancien");
            }
            resultat.add("medium", jsonMedium);
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
