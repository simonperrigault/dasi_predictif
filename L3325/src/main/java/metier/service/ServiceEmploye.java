/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.service;

import dao.ClientDao;
import dao.ConsultationDao;
import dao.EmployeDao;
import dao.JPAutil;
import dao.MediumDao;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import metier.modele.Astrologue;
import metier.modele.Cartomancien;
import metier.modele.Client;
import metier.modele.Consultation;
import metier.modele.Employe;
import metier.modele.Medium;
import metier.modele.Spirite;
import util.AstroNetApi;
import util.Message;


/**
 *
 * @author slouvetdem
 */
public class ServiceEmploye {
    

    
    public Consultation obtenirConsultationCourante(Employe employe)
    {
        JPAutil.creerContextePersistance();
        boolean etat = false;
        String msg = null;
        ConsultationDao consultationdao = new ConsultationDao();
        Consultation result;
        
        try {

            result = consultationdao.findCurrentEmployeConsult(employe);
            

        } catch (Exception ex) { // ça n'a pas marché

            ex.printStackTrace();  
            result = null;

        } finally {
            JPAutil.fermerContextePersistance();
        }

        return result;
    }
    
    
    public boolean initMedium() throws IOException {
        MediumDao mediumdao = new MediumDao();
        boolean etat = true;
        String msg = null;
        
        try {
            JPAutil.creerContextePersistance();
            Spirite medium1 = new Spirite("Boule de crital", "Gwenaëlle", "Spécialiste des grandes conversations au-delà de TOUTES les frontières","F");
            Spirite medium2 = new Spirite("Marc de café, boule de cristal, oreille de lapin", "Professeur Tran", "Votre avenir est devant vous: regardons-le ensemble", "H");
            Cartomancien medium3 = new Cartomancien("Mme Irma", "Comprenez votre entourage grâce à mes cartes! Résultats rapides", "F");
            Cartomancien medium4 = new Cartomancien("Endora", "Mes cartes répondrons à toutes vos questions personnelles", "F");
            Astrologue medium5 = new Astrologue("Ecole Normale Supérieure d'Astrologie (ENS-Astro)", "2006", "Serena", "Basée à Champigny sur Marne, Serena vous révelera votre avenir pour éclairer votre passé", "F");
            Astrologue medium6 = new Astrologue("Insitut des Nouveaux Savoirs Astrologiques", "2010", "Mr.M", "Avenir, Avenir, que nous réserves-tu ? N'attendez plus, demandez à me consulter", "M");
            
            JPAutil.ouvrirTransaction();
            mediumdao.create(medium1);
            mediumdao.create(medium2);
            mediumdao.create(medium3);
            mediumdao.create(medium4);
            mediumdao.create(medium5);
            mediumdao.create(medium6);
            

            JPAutil.validerTransaction(); // essayer de valider la transaction
            


        } catch (Exception ex) { // ça n'a pas marché

            ex.printStackTrace();
            JPAutil.annulerTransaction(); // ne pas oublier d'annuler la transaction !
            etat = false;

        } finally { // dans tous les cas, on ferme l'entity manager
            JPAutil.fermerContextePersistance();
        }
        return etat;
    }
    
    /*
    
    public List<Employe> consulterListeEmploye() 
    {
        EmployeDao employedao = new EmployeDao();
        List<Employe> employes;

        JPAutil.creerContextePersistance();
        employes = employedao.findAll();
        
        JPAutil.fermerContextePersistance();

        return employes;
    }
      
    */
    
    public boolean validerConsultation(Consultation consultation) 
    {
        JPAutil.creerContextePersistance(); 
        boolean etat = false;
        String msg = null;
        ConsultationDao consultationdao = new ConsultationDao();
        try {
            
            long currentTime = System.currentTimeMillis();
            consultation.setDuree_ms(currentTime);
            

            try {
 
                JPAutil.ouvrirTransaction();
                consultationdao.update(consultation);
                
                JPAutil.validerTransaction();
                
                
                

            } catch (ParseException e) {
                e.printStackTrace();
            }finally {
            JPAutil.fermerContextePersistance();
            }

            etat = true;
           

        } catch (Exception ex) { // ça n'a pas marché

            ex.printStackTrace();
            msg = "Validation échouée";
            
            Message.envoyerNotification(consultation.getEmploye().getTelephone(), msg);
            etat = false; 

        } 
   

        return etat;
   
    } 
   
        
    public boolean terminerConsultation(Consultation consultation, String commentaire) 
    {
        JPAutil.creerContextePersistance(); 
        ClientDao clientdao = new ClientDao();
        MediumDao mediumdao = new MediumDao();
        ConsultationDao consultationdao = new ConsultationDao();
        EmployeDao employedao = new EmployeDao();
        boolean etat = true;
        String msg = null;
        try {
            consultation.getEmploye().setDisponible(true);
            consultation.getEmploye().setNombreConsult(consultation.getEmploye().getNombreConsult() + 1);
            consultation.getMedium().setNombreChoisi(consultation.getMedium().getNombreChoisi() + 1);
            consultation.setCommentaire(commentaire);
            
            
            long endTime = System.currentTimeMillis();
            long difference = endTime - consultation.getDuree_ms();
            
            long hours = (long) Math.floor((difference)/(60*1000*60)) % 24;
            long seconds = (long) Math.floor((difference)/(1000)) % 60;
            long minutes = (long) Math.floor((difference)/(60*1000)) % 60;
            
            
            String dureeString = String.format("%02d:%02d:%02d", hours, minutes, seconds);
           
            consultation.setDuree(dureeString);
            consultation.setDuree_ms(difference);

            
            JPAutil.ouvrirTransaction();
            employedao.update(consultation.getEmploye());
            consultationdao.update(consultation);
            consultation.getClient().getConsultations().add(consultation);
            clientdao.update(consultation.getClient());
            
            consultation.getEmploye().getConsultations().add(consultation);
            employedao.update(consultation.getEmploye());
            
            consultation.getMedium().getConsultations().add(consultation);
            mediumdao.update(consultation.getMedium());
            JPAutil.validerTransaction();
           

        } catch (Exception ex) { // ça n'a pas marché

            ex.printStackTrace();
            JPAutil.annulerTransaction();
            msg = "Validation échouée";
            
            Message.envoyerNotification(consultation.getEmploye().getTelephone(), msg);
            etat = false; 

        } finally { // dans tous les cas, on ferme l'entity manager
            JPAutil.fermerContextePersistance();
        }
        
   

        return etat;
   
    } 
    
    public Map<String, String> avoirAide(int amour, int sante,int travail,Client client) throws IOException
    {
        AstroNetApi astroApi = new AstroNetApi();
        Map<String, String> aidesEmp = new HashMap<>();
        
        if (amour < 5 && sante < 5 && travail < 5 && amour > 0 && sante > 0 && travail > 0)
        {
            List<String> aidesApi = astroApi.getPredictions(client.getProfil().getCouleur(), client.getProfil().getTotem(), amour, sante, travail);
            List<String> mots = new ArrayList<>();
            mots.add("amour");
            mots.add("santé");
            mots.add("travail");

            for (int i = 0; i < 3; i++) {
                aidesEmp.put(mots.get(i), aidesApi.get(i));
            }
        }
        else
        {
            aidesEmp = null;
        }
            
            
        
        
        
        return aidesEmp;
    }
    
    // STATISTIQUES 
    
    public Map<Employe, Double> repartitionEmployeClient()
    {
        EmployeDao employedao = new EmployeDao();
        
        Map<Employe, Double> dictionnaire = new HashMap<>();
        
        JPAutil.creerContextePersistance();
        List<Employe> listeEmploye = employedao.findAll();
        long nombreConsultationTotal = employedao.nombreTotalConsultation();
        
        for (Employe employe : listeEmploye) {
            double division = (double) employe.getNombreConsult() / nombreConsultationTotal;
            dictionnaire.put(employe, (double)Math.round(division * 100));
        }
        
        return dictionnaire;
       
    }
    
    public Map<Medium, Integer> nombreConsultMediums()
    {
        MediumDao mediumdao = new MediumDao();
        
        Map<Medium, Integer> dictionnaire = new HashMap<>();
        
        JPAutil.creerContextePersistance();
        List<Medium> listeMedium = mediumdao.findAll();
        
        for (Medium medium : listeMedium) {
            dictionnaire.put(medium, medium.getNombreChoisi());
        }
        
        return dictionnaire;
       
    }
        
    public List<Medium> topMediums()
    {
        MediumDao mediumdao = new MediumDao();
        
        List<Medium> listing = null;
        
        JPAutil.creerContextePersistance();
        listing = mediumdao.findBestMediums();
        JPAutil.fermerContextePersistance();

        
        return listing;
            
    }
    
    // FIN STATISTIQUES
    
    
    
    public Employe rechercherEmployebyID(Long id)
    {
        EmployeDao employedao = new EmployeDao();
        
        JPAutil.creerContextePersistance();
        
        Employe result = employedao.findById(id);
        
        JPAutil.fermerContextePersistance();
        
        return result;
        
    }       
    
    
     public Employe authentifierEmploye(String mail, String motDePasse) {
        JPAutil.creerContextePersistance(); 
        EmployeDao employedao = new EmployeDao();
        Employe result = employedao.findByMailID(mail, motDePasse);

        JPAutil.fermerContextePersistance();
        return result;
    }
    
    
     
     public boolean initEmploye() throws IOException {
        EmployeDao employedao = new EmployeDao();
        boolean etat = true;
        String msg = null;
        
        try {
            JPAutil.creerContextePersistance();
            Employe employe1 = new Employe("Allaz", "Adam", "a@a.a", "a", "113", "F");
            Employe employe2 = new Employe("Arnold", "Swarzeneger", "s@s.s", "ss", "333", "H");
            Employe employe3 = new Employe("Macron", "Emmanuel", "m@m.m", "m", "000", "H");
            Employe employe4 = new Employe("Patrick", "Poubelle", "p@p.p", "p", "010", "F");
            
            
            JPAutil.ouvrirTransaction();
            employedao.create(employe1);
            employedao.create(employe2);
            employedao.create(employe3);
            employedao.create(employe4);
            

            JPAutil.validerTransaction(); // essayer de valider la transaction
            


        } catch (Exception ex) { // ça n'a pas marché

            ex.printStackTrace();
            JPAutil.annulerTransaction(); // ne pas oublier d'annuler la transaction !
            etat = false;

        } finally { // dans tous les cas, on ferme l'entity manager
            JPAutil.fermerContextePersistance();
        }
        return etat;
    }
    

}
