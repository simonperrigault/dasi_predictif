/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.service;

import metier.modele.Client;
import com.google.maps.model.LatLng;
import dao.ClientDao;
import dao.ConsultationDao;
import dao.EmployeDao;
import dao.JPAutil;
import dao.MediumDao;
import dao.ProfilAstralDao;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import metier.modele.Consultation;
import metier.modele.Employe;
import metier.modele.Medium;
import metier.modele.ProfilAstral;
import util.AstroNetApi;
import util.GeoNetApi;
import util.Message;


/**
 *
 * @author slouvetdem
 */
public class ServiceClient {
    
    /*
    
    public List<Client> consulterListeClient() 
    {
        ClientDao clientdao = new ClientDao();
        List<Client> clients;

        JPAutil.creerContextePersistance();
        clients = clientdao.findAll();
        
        JPAutil.fermerContextePersistance();

        return clients;
    }
        
        
       */ 
    
    public Client rechercherClientbyID(Long id)
    {
        ClientDao clientdao = new ClientDao();
        
        JPAutil.creerContextePersistance();
        
        Client result = clientdao.findById(id);
        
        JPAutil.fermerContextePersistance();
        
        return result;
        
        }   

    
    public Medium rechercherMediumbyID(Long id)
    {
        MediumDao mediumdao = new MediumDao();
        
        JPAutil.creerContextePersistance();
        
        Medium result = mediumdao.findById(id);
        
        JPAutil.fermerContextePersistance();
        
        return result;
        
    }  
    
    public List<Medium> consulterListeMedium() 
    {
        MediumDao mediumdao = new MediumDao();
        List<Medium> mediums;

        JPAutil.creerContextePersistance();
        mediums = mediumdao.findAll();
        
        JPAutil.fermerContextePersistance();

        return mediums;
    }
    
    public Client authentifierClient(String mail, String motDePasse) {
        JPAutil.creerContextePersistance(); 
        ClientDao clientdao = new ClientDao();
        Client result = clientdao.findByMailID(mail, motDePasse);

        JPAutil.fermerContextePersistance();
        return result;
    }
    
    /*
    public Medium choisirMedium(String denomination) {
        
        JPAutil.creerContextePersistance(); 
        MediumDao mediumdao = new MediumDao();
        
        Medium result = mediumdao.findByDenomination(denomination);

        JPAutil.fermerContextePersistance();
        return result;
    }
    
    */
    
    public Consultation demanderConsultation(Medium medium, Client client) throws ParseException 
    {
        JPAutil.creerContextePersistance(); 
        EmployeDao employedao = new EmployeDao();
        ConsultationDao consultationdao = new ConsultationDao();

        Date dateConsult = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy 'at' HH:mm:ss");
        String formattedDate = dateFormat.format(dateConsult);
        Date parsedDate = dateFormat.parse(formattedDate);
        
        Consultation newConsultation = null;
        
        
        String msg = null;
        
        try {
            
            
            newConsultation = new Consultation(client, medium);
            newConsultation.setDateConsult(parsedDate);
            
            //sélection de l'employé ayant le même genre que le médium et un statut disponible à true
            Employe employe =  employedao.findByDispoAndGenre(medium.getGenre());

            
            if (employe != null)
            {
                newConsultation.setEmploye(employe);
                JPAutil.ouvrirTransaction();
                newConsultation.getEmploye().setDisponible(false);
                employedao.update(newConsultation.getEmploye());
                consultationdao.create(newConsultation);
                
                JPAutil.validerTransaction();


                msg = "Vous avez une consultation avec " + client.getNom() + " " + client.getPrenom() + " et vous êtes le médium : " + medium.getDenomination();

                Message.envoyerNotification(employe.getTelephone(), msg);
            }
            
            else
            {
                System.out.println("\n");
                System.out.println("Malheureusement votre médium n'est pas disponible. Veuillez sélectionner un autre médium " + "\n");
                System.out.println("\n");
                newConsultation = null;
                
                msg = "Demande de consultation échouée. Malheureusement votre médium n'est pas disponible. Veuillez sélectionner un autre médium.";
                Message.envoyerNotification(client.getNumTel(), msg);
                
            }

        } catch (Exception ex) { // ça n'a pas marché

            ex.printStackTrace();
            JPAutil.annulerTransaction(); // ne pas oublier d'annuler la transaction !
            
            msg = "Demande de consultation échouée";
            
            Message.envoyerNotification(client.getNumTel(), msg);

        } finally {
            JPAutil.fermerContextePersistance();
        }
        
        return newConsultation;
   
    }
     
    public boolean inscrireClient(Client client) throws IOException {
        ClientDao clientdao = new ClientDao();
        ProfilAstralDao profilAstraldao = new ProfilAstralDao();
        boolean etat = true;
        String msg = null;
        
        try {
            JPAutil.creerContextePersistance();
            LatLng coordsclient = GeoNetApi.getLatLng(client.getAdressePostale());
            
            client.setLatitude(coordsclient.lat);
            client.setLongitude(coordsclient.lng);
            
            AstroNetApi astroApi = new AstroNetApi();
            
            List<String> profil = astroApi.getProfil(client.getPrenom(), client.getDateNaissance());
            
            String signeZodiaque = profil.get(0);
            String signeChinois = profil.get(1);
            String couleur = profil.get(2);
            String animal = profil.get(3);

            ProfilAstral profilAstral = new ProfilAstral(couleur, animal, signeChinois, signeZodiaque);
            
            client.setProfil(profilAstral);

            
            JPAutil.ouvrirTransaction();
            clientdao.create(client);
            profilAstraldao.create(profilAstral);

            JPAutil.validerTransaction(); // essayer de valider la transaction
            
            msg = "Inscription réussie pour " + client.getNom() + client.getPrenom() + client.getMail();
            
            Message.envoyerMail("samuel.louvet09@gmail.com", client.getMail(), "TestObjet", msg);


        } catch (Exception ex) { // ça n'a pas marché

            ex.printStackTrace();
            
            msg = "Inscription ratée pour " + client.getNom() + client.getPrenom() + client.getMail();
            
            Message.envoyerMail("samuel.louvet09@gmail.com", client.getMail(), "TestObjet", msg);
            etat = false;

        } finally { // dans tous les cas, on ferme l'entity manager
            JPAutil.fermerContextePersistance();
        }
        return etat;
    }
}
