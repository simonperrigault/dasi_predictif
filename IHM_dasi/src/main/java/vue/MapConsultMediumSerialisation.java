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
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import metier.modele.Employe;
import metier.modele.Medium;

/**
 *
 * @author sperrigaul
 */
public class MapConsultMediumSerialisation extends Serialisation {

    @Override
    public void appliquer(HttpServletRequest req, HttpServletResponse res) {
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        JsonArray resultat = new JsonArray();

        Map<Medium, Integer> mapRepartition = (Map<Medium, Integer>) req.getAttribute("mapConsultMedium");

        for (Map.Entry<Medium, Integer> entry : mapRepartition.entrySet()) {            
            Medium medium = entry.getKey();
            
            JsonArray intermediaire = new JsonArray();
            
            intermediaire.add(medium.getDenomination());
            intermediaire.add(entry.getValue());
            
            resultat.add(intermediaire);
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
