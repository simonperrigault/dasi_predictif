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

/**
 *
 * @author sperrigaul
 */
public class MapRepartitionSerialisation extends Serialisation {

    @Override
    public void appliquer(HttpServletRequest req, HttpServletResponse res) {
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        JsonArray resultat = new JsonArray();

        Map<Employe, Double> mapRepartition = (Map<Employe, Double>) req.getAttribute("mapRepartition");

        for (Map.Entry<Employe, Double> entry : mapRepartition.entrySet()) {            
            Employe employe = entry.getKey();
            
            JsonArray intermediaire = new JsonArray();
            
            intermediaire.add(employe.getNom());
            intermediaire.add(employe.getPrenom());
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
