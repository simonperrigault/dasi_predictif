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
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sperrigaul
 */
public class MapAideSerialisation extends Serialisation {

    @Override
    public void appliquer(HttpServletRequest req, HttpServletResponse res) {
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        JsonObject resultat = new JsonObject();

        Map<String, String> mapAide = (Map<String, String>) req.getAttribute("mapAide");

        if (mapAide == null) {
            resultat.addProperty("res", false);
        }
        else {
            resultat.addProperty("res", true);
            for (Map.Entry<String, String> entry : mapAide.entrySet()) {
                resultat.addProperty(entry.getKey(), entry.getValue());
            }
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
