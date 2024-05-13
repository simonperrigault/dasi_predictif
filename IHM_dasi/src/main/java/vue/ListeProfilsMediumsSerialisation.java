/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import metier.modele.Medium;
import metier.modele.Astrologue;
import metier.modele.Spirite;

/**
 *
 * @author sperrigaul
 */
public class ListeProfilsMediumsSerialisation extends Serialisation {

    @Override
    public void appliquer(HttpServletRequest req, HttpServletResponse res) {
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        JsonArray resultat = new JsonArray();
        List<Medium> liste = (List<Medium>) req.getAttribute("liste");

        for (Medium medium : liste) {
            JsonObject jsonMedium = new JsonObject();
            JsonObject inter = new JsonObject();

            if (medium != null) {
                jsonMedium.addProperty("id", medium.getId());
                jsonMedium.addProperty("denomination", medium.getDenomination());
                jsonMedium.addProperty("presentation", medium.getPresentation());
                jsonMedium.addProperty("genre", medium.getGenre());
                jsonMedium.addProperty("nombreChoisi", medium.getNombreChoisi());
                if (medium instanceof Astrologue) {
                    Astrologue astro = (Astrologue) medium;
                    jsonMedium.addProperty("formation", astro.getFormation());
                    jsonMedium.addProperty("promotion", astro.getPromotion());
                    inter.addProperty("type", "astrologue");
                } else if (medium instanceof Spirite) {
                    Spirite spi = (Spirite) medium;
                    jsonMedium.addProperty("support", spi.getSupport());
                    inter.addProperty("type", "spirite");
                } else {
                    inter.addProperty("type", "cartomancien");
                }
                inter.add("medium", jsonMedium);
            }
            resultat.add(inter);
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
