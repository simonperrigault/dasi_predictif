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
import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import metier.modele.Astrologue;
import metier.modele.Client;
import metier.modele.Consultation;
import metier.modele.Employe;
import metier.modele.Medium;
import metier.modele.ProfilAstral;
import metier.modele.Spirite;

/**
 *
 * @author sperrigaul
 */
public class ListeConsultationsSerialisation extends Serialisation {

    @Override
    public void appliquer(HttpServletRequest req, HttpServletResponse res) {
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();

        List<Consultation> consultations = (List<Consultation>) req.getAttribute("listeConsultations");

        JsonArray jsonConsultations = new JsonArray();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        for (Consultation consultation : consultations) {
            JsonObject resultat = new JsonObject();
            JsonObject jsonConsultation = new JsonObject();
            jsonConsultation.addProperty("date", format.format(consultation.getDateConsult()));
            jsonConsultation.addProperty("duree", consultation.getDuree());
            jsonConsultation.addProperty("commentaire", consultation.getCommentaire());
            resultat.add("consultation", jsonConsultation);

            JsonObject jsonMedium = new JsonObject();

            Medium medium = consultation.getMedium();
            if (medium != null) {
                jsonMedium.addProperty("id", medium.getId());
                jsonMedium.addProperty("denomination", medium.getDenomination());
                jsonMedium.addProperty("presentation", medium.getPresentation());
                jsonMedium.addProperty("genre", medium.getGenre());
                jsonMedium.addProperty("nombreChoisi", medium.getNombreChoisi());
                jsonMedium.addProperty("image", medium.getCheminPhotoProfil());
                if (medium instanceof Astrologue) {
                    Astrologue astro = (Astrologue) medium;
                    jsonMedium.addProperty("formation", astro.getFormation());
                    jsonMedium.addProperty("promotion", astro.getPromotion());
                    resultat.addProperty("type", "astrologue");
                } else if (medium instanceof Spirite) {
                    Spirite spi = (Spirite) medium;
                    jsonMedium.addProperty("support", spi.getSupport());
                    resultat.addProperty("type", "spirite");
                } else {
                    resultat.addProperty("type", "cartomancien");
                }
                resultat.add("medium", jsonMedium);
            }

            JsonObject jsonClient = new JsonObject();
            JsonObject jsonProfil = new JsonObject();
            Client client = consultation.getClient();
            if (client != null) {
                jsonClient.addProperty("id", client.getId());
                jsonClient.addProperty("nom", client.getNom());
                jsonClient.addProperty("prenom", client.getPrenom());
                jsonClient.addProperty("mail", client.getMail());
                jsonClient.addProperty("mdp", client.getMotDePasse());
                jsonClient.addProperty("telephone", client.getNumTel());
                jsonClient.addProperty("adresse", client.getAdressePostale());
                jsonClient.addProperty("latitude", client.getLatitude());
                jsonClient.addProperty("longitude", client.getLongitude());
                jsonClient.addProperty("dateNaissance", format.format(client.getDateNaissance()));

                ProfilAstral profil = client.getProfil();
                jsonProfil.addProperty("couleur", profil.getCouleur());
                jsonProfil.addProperty("totem", profil.getTotem());
                jsonProfil.addProperty("signeChinois", profil.getSigneChinois());
                jsonProfil.addProperty("signeZodiaque", profil.getSigneZodiaque());
                jsonClient.add("profil", jsonProfil);

                resultat.add("client", jsonClient);
            }

            JsonObject jsonEmploye = new JsonObject();
            Employe employe = consultation.getEmploye();
            if (employe != null) {
                resultat.addProperty("connexion", true);
                jsonEmploye.addProperty("id", employe.getId());
                jsonEmploye.addProperty("nom", employe.getNom());
                jsonEmploye.addProperty("prenom", employe.getPrenom());
                jsonEmploye.addProperty("mail", employe.getMail());
                jsonEmploye.addProperty("mdp", employe.getMotDePasse());
                jsonEmploye.addProperty("telephone", employe.getTelephone());
                jsonEmploye.addProperty("genre", employe.getGenre());
                jsonEmploye.addProperty("disponible", employe.getDisponible());
                jsonEmploye.addProperty("nombreConsultations", employe.getNombreConsult());

                resultat.add("employe", jsonEmploye);
            }
            jsonConsultations.add(resultat);
        }

        res.setContentType("application/json;charset=UTF-8");
        PrintWriter out;

        try {
            out = res.getWriter();
            out.println(gson.toJson(jsonConsultations));
            out.close();
        } catch (IOException ex) {

        }
    }

}
