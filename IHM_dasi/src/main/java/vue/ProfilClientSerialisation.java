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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import metier.modele.Client;
import metier.modele.ProfilAstral;

/**
 *
 * @author sperrigaul
 */
public class ProfilClientSerialisation extends Serialisation {

    @Override
    public void appliquer(HttpServletRequest req, HttpServletResponse res) {
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        JsonObject resultat = new JsonObject();
        JsonObject jsonClient = new JsonObject();
        JsonObject jsonProfil = new JsonObject();
        
        Client client = (Client)req.getAttribute("client");
        if (client != null) {
            resultat.addProperty("connexion", true);
            jsonClient.addProperty("id", client.getId());
            jsonClient.addProperty("nom", client.getNom());
            jsonClient.addProperty("prenom", client.getPrenom());
            jsonClient.addProperty("mail", client.getMail());
            jsonClient.addProperty("mdp", client.getMotDePasse());
            jsonClient.addProperty("telephone", client.getNumTel());
            jsonClient.addProperty("adresse", client.getAdressePostale());
            jsonClient.addProperty("latitude", client.getLatitude());
            jsonClient.addProperty("longitude", client.getLongitude());
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            jsonClient.addProperty("dateNaissance", format.format(client.getDateNaissance()));
            
            ProfilAstral profil = client.getProfil();
            jsonProfil.addProperty("couleur", profil.getCouleur());
            jsonProfil.addProperty("totem", profil.getTotem());
            jsonProfil.addProperty("signeChinois", profil.getSigneChinois());
            jsonProfil.addProperty("signeZodiaque", profil.getSigneZodiaque());
            jsonClient.add("profil", jsonProfil);

            resultat.add("client", jsonClient);
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
