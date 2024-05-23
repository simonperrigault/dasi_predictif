/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;
import dao.EmployeDao;
import dao.JPAutil;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import metier.modele.Client;
import metier.modele.Consultation;
import metier.modele.Employe;
import metier.modele.Medium;
import metier.service.ServiceClient;
import metier.service.ServiceEmploye;

/**
 *
 * @author slouvetdem
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException, IOException, InterruptedException {
        
        SimpleDateFormat formatDateNaissance = new SimpleDateFormat("dd/MM/yyyy"); // format des dates de naissance des Clients
        SimpleDateFormat formatDateConsultation = new SimpleDateFormat("dd/MM/yyyy 'at' HH:mm:ss"); // format des dates de consultation des Clients
        
        JPAutil.creerFabriquePersistance(); //création fabrique persistence
        
        // création des service Client et Employe
        ServiceClient serviceClt = new ServiceClient();
        ServiceEmploye serviceEmp = new ServiceEmploye();

        //ajout des Employés et Médiums dans la base de donnée
        serviceEmp.initEmploye();
        serviceEmp.initMedium();
        
        
        // Création de 3 Clients dans la base
        System.out.println("Inscrition des 3 Clients dans la base de donnée\n");
        
        Date dateNaissance = formatDateNaissance.parse("10/12/1995"); 
        Client client1 = new Client("Victor", "HGugo", "vhugo@paris.fr", "tutu", "Lyon", "01 02 03 04 05", dateNaissance);
        
        dateNaissance = formatDateNaissance.parse("30/09/2003"); 
        Client client2 = new Client("Samuel", "Louvet", "slouvet@gmail.fr", "dasi", "Paris", "05 04 03 02 01", dateNaissance);
        
        dateNaissance = formatDateNaissance.parse("24/04/1985"); 
        Client client3 = new Client("Termine", "aTord", "tatord@gmail.fr", "conor", "Roubaix", "02 01 03 04 05", dateNaissance);
        
        
        
        
        //Utilisation du service 'inscrireClient' pour les 3 Clients
        serviceClt.inscrireClient(client1);
        serviceClt.inscrireClient(client2);
        serviceClt.inscrireClient(client3);
        
        //client3.setNom("Louis");
        //serviceClt.modifierClient(client3);
        
        System.out.println("-------------------------------------------------------\n");
        //Thread.sleep(4000);
        
        //Tentative d'authentification de Victor Hugo avec une tentative ratée 
        System.out.println("Tentative d'autentification d'un Client\n");
        
        Client tentativeConnexion = serviceClt.authentifierClient("vhugo@paris.fr", "toto");
        if (tentativeConnexion != null)
        {
            System.out.println("Authentification réussie pour : " + tentativeConnexion + "\n");
        }
        else
        {
            System.out.println("Authentification ratée avec retour : " + tentativeConnexion + "\n");
        }
        
        
        System.out.println("\n");
        
        //Tentative d'authentification de Victor Hugo avec une tentative réussie
        tentativeConnexion = serviceClt.authentifierClient("vhugo@paris.fr", "tutu");
        if (tentativeConnexion != null)
        {
            System.out.println("Authentification réussie pour : " + tentativeConnexion + "\n");
        }
        else
        {
            System.out.println("Authentification ratée avec retour : " + tentativeConnexion + "\n");
        }
        
        System.out.println("-------------------------------------------------------\n");
        //Thread.sleep(4000);
        
        //Consulter la liste de tous les médiums disponibles
        System.out.println("Consulter la liste de tous les Mediums disponible\n");
        
        List<Medium> mediums = serviceClt.consulterListeMedium(); //Récupère la liste des médiums
        
        for(int i = 0; i < mediums.size(); i++){
            System.out.println("Médium n°" + i + " " + mediums.get(i) + "\n");
        }
        
        System.out.println("-------------------------------------------------------\n");
        //Thread.sleep(4000);
        
        //Le Client Victor Hugo fait une demande de consultation avec la médium Mme Irma
        System.out.println("Le Client Victor Hugo va faire une demande de consultation avec le médium Mme Irma\n");
        
        //Mme Irma
        Medium choixClientMedium = serviceClt.rechercherMediumbyID(7L);
        Date dateConsultation = formatDateConsultation.parse("09/04/2024 at 16:50:12");
        
        Consultation nouvelleConsultation = serviceClt.demanderConsultation(choixClientMedium, client1);
        
        if (nouvelleConsultation != null)
        {
            System.out.println("*** Client concerné = " + nouvelleConsultation.getClient().getNom() + " " + nouvelleConsultation.getClient().getPrenom() + "\n");
            System.out.println("*** Medium sélectionné = " + nouvelleConsultation.getMedium().getDenomination() + "\n");
            System.out.println("*** Employé sélectionné = " + nouvelleConsultation.getEmploye().getNom() + " " + nouvelleConsultation.getEmploye().getPrenom() + "\n");
            System.out.println("*** Genre de l'Employé = " + nouvelleConsultation.getEmploye().getGenre() + "/ Genre du Médium = " + nouvelleConsultation.getMedium().getGenre() +"\n");
        }
        
        System.out.println("-------------------------------------------------------\n");
        
        System.out.println("-------------------------------------------------------\n");
        //Thread.sleep(4000);
        // On rajoute 3 consultations pour vérifier les statistiques
        System.out.println("On rajoute 3 consultations pour vérifier les statistiques\n");
        
        //Serena
        choixClientMedium = serviceClt.rechercherMediumbyID(9L);
        Consultation nouvelleConsultation2 = serviceClt.demanderConsultation(choixClientMedium, client2);
        
        if (nouvelleConsultation2 != null)
        {
            System.out.println("*** Client concerné = " + nouvelleConsultation2.getClient().getNom() + " " + nouvelleConsultation2.getClient().getPrenom() + "\n");
            System.out.println("*** Medium sélectionné = " + nouvelleConsultation2.getMedium().getDenomination() + "\n");
            System.out.println("*** Employé sélectionné = " + nouvelleConsultation2.getEmploye().getNom() + " " + nouvelleConsultation2.getEmploye().getPrenom() + "\n");
            System.out.println("*** Genre de l'Employé = " + nouvelleConsultation2.getEmploye().getGenre() + "/ Genre du Médium = " + nouvelleConsultation2.getMedium().getGenre() +"\n");
        }
            
        System.out.println("-------------------------------------------------------\n");
        //Thread.sleep(4000);
        
        //Serena
        choixClientMedium = serviceClt.rechercherMediumbyID(9L);
        Consultation nouvelleConsultation3 = serviceClt.demanderConsultation(choixClientMedium, client2);

        
        if (nouvelleConsultation3 != null)
        {
            System.out.println("*** Client concerné = " + nouvelleConsultation3.getClient().getNom() + " " + nouvelleConsultation3.getClient().getPrenom() + "\n");
            System.out.println("*** Medium sélectionné = " + nouvelleConsultation3.getMedium().getDenomination() + "\n");
            System.out.println("*** Employé sélectionné = " + nouvelleConsultation3.getEmploye().getNom() + " " + nouvelleConsultation3.getEmploye().getPrenom() + "\n");
            System.out.println("*** Genre de l'Employé = " + nouvelleConsultation3.getEmploye().getGenre() + "/ Genre du Médium = " + nouvelleConsultation3.getMedium().getGenre() +"\n");
        }
        
        System.out.println("-------------------------------------------------------\n");
        //Thread.sleep(4000);
        
        //Professeur Tran
        choixClientMedium = serviceClt.rechercherMediumbyID(6L);
        Consultation nouvelleConsultation4 = serviceClt.demanderConsultation(choixClientMedium, client3);
       
        if (nouvelleConsultation4 != null)
        {
            System.out.println("*** Client concerné = " + nouvelleConsultation4.getClient().getNom() + " " + nouvelleConsultation4.getClient().getPrenom() + "\n");
            System.out.println("*** Medium sélectionné = " + nouvelleConsultation4.getMedium().getDenomination() + "\n");
            System.out.println("*** Employé sélectionné = " + nouvelleConsultation4.getEmploye().getNom() + " " + nouvelleConsultation4.getEmploye().getPrenom() + "\n");
            System.out.println("*** Genre de l'Employé = " + nouvelleConsultation4.getEmploye().getGenre() + "/ Genre du Médium = " + nouvelleConsultation4.getMedium().getGenre() +"\n");
        }
        
        System.out.println("-------------------------------------------------------\n");
        //Thread.sleep(4000);
        
        serviceEmp.validerConsultation(nouvelleConsultation2);
        Thread.sleep(2000);
        serviceEmp.terminerConsultation(nouvelleConsultation2, "Merci pour cette consultation !");
        
        serviceEmp.validerConsultation(nouvelleConsultation4);
        Thread.sleep(2000);
        serviceEmp.terminerConsultation(nouvelleConsultation4, "Merci pour cette consultation !");
        
        //On refait une consultation pour le Professeur Tran
        
        //Professeur Tran
        choixClientMedium = serviceClt.rechercherMediumbyID(6L);
        Consultation nouvelleConsultation5 = serviceClt.demanderConsultation(choixClientMedium,client1);
       
        if (nouvelleConsultation5 != null)
        {
            System.out.println("*** Client concerné = " + nouvelleConsultation5.getClient().getNom() + " " + nouvelleConsultation5.getClient().getPrenom() + "\n");
            System.out.println("*** Medium sélectionné = " + nouvelleConsultation5.getMedium().getDenomination() + "\n");
            System.out.println("*** Employé sélectionné = " + nouvelleConsultation5.getEmploye().getNom() + " " + nouvelleConsultation5.getEmploye().getPrenom() + "\n");
            System.out.println("*** Genre de l'Employé = " + nouvelleConsultation5.getEmploye().getGenre() + "/ Genre du Médium = " + nouvelleConsultation5.getMedium().getGenre() +"\n");
        }
        
        serviceEmp.validerConsultation(nouvelleConsultation5);
        Thread.sleep(2000);
        serviceEmp.terminerConsultation(nouvelleConsultation5, "Merci pour cette consultation !");
        
        System.out.println("-------------------------------------------------------\n");
        //Thread.sleep(4000);
        
        //Nous allons authentifier l'Employe choisit pour la consultation
        System.out.println("L'Employe s'authentie à son profil\n");
        
        Employe employe = nouvelleConsultation.getEmploye();
        
        //System.out.println("employe = " + employe + "\n");
        Consultation consult = serviceEmp.obtenirConsultationCourante(employe);
        //System.out.println("employe = " + consult + "\n");
        
        
        
        Employe tentativeConnexionEmp = serviceEmp.authentifierEmploye(employe.getMail(), "error"); //ratée
        if (tentativeConnexionEmp != null)
        {
            System.out.println("Authentification réussie pour : " + tentativeConnexionEmp + "\n");
        }
        else
        {
            System.out.println("Authentification ratée avec retour : " + tentativeConnexionEmp + "\n");
        }
        
        tentativeConnexionEmp = serviceEmp.authentifierEmploye(employe.getMail(), nouvelleConsultation.getEmploye().getMotDePasse()); //réussie
        if (tentativeConnexionEmp != null)
        {
            System.out.println("Authentification réussie pour : " + tentativeConnexionEmp + "\n");
        }
        else
        {
            System.out.println("Authentification ratée avec retour : " + tentativeConnexionEmp + "\n");
        }
        
        System.out.println("-------------------------------------------------------\n");
        //Thread.sleep(4000);
        
        //L'Employe consulte le profil Astral du Client
        System.out.println("L'Employe consulte le profil Astral du Client\n");
        
        System.out.println(serviceEmp.obtenirConsultationCourante(employe).getClient().getProfil());
        
        System.out.println("-------------------------------------------------------\n");
        //Thread.sleep(4000);
        
        //L'Employe cherche de l'aide avec les notes qui lui sont disponible
        System.out.println("L'Employe cherche de l'aide avec les notes qui lui sont disponible\n");
        
        Map<String, String> aidesEmploye = new HashMap<>();
        aidesEmploye = serviceEmp.avoirAide(1, 2, 3, serviceEmp.obtenirConsultationCourante(employe).getClient());
                
        for (Map.Entry<String, String> entry : aidesEmploye.entrySet()) {
            System.out.println("Conseil " + entry.getKey() + ": " + entry.getValue() + "\n");
        }
       
        System.out.println("-------------------------------------------------------\n");
        //Thread.sleep(4000);
        
        //Après l'Employee valide la consultation du Client
        System.out.println("Après l'Employee valide la consultation du Client\n");
        
        serviceEmp.validerConsultation(serviceEmp.obtenirConsultationCourante(employe));
        

        //Après 5 secondes la consultation prend fin
        System.out.println("Après 5 secondes la consultation prend fin\n");
        
        Thread.sleep(2000);
        serviceEmp.terminerConsultation(serviceEmp.obtenirConsultationCourante(employe), "Merci pour cette consultation !");
        
        System.out.println("*** Durée de la consultation = " + consult.getDuree() + "\n");
        System.out.println("*** Commentaire de la consultation = " + consult.getCommentaire() + "\n");

        System.out.println("-------------------------------------------------------\n");
        //Thread.sleep(4000);
        
        //L'employé cherche maintenant à afficher les statistiques
        System.out.println("L'employé cherche maintenant à afficher les statistiques\n");
        
        Long id = new Long(1);
        System.out.println("cbin");
        Employe employeTemp = serviceEmp.rechercherEmployebyID(id);
        
        //Employe fakeEmp = new Employe()

        List<Consultation> cons = serviceEmp.getConsultationByEmploye(employeTemp);
        
       for (Consultation consultation : cons) {
            System.out.println("Consultation : " + consultation.getClient() + ", Date de début : " + consultation.getEmploye());
        }
        
        //L'employé cherche à afficher le Top5 des Médiums
        System.out.println("L'employé cherche à afficher le Top5 des Médiums\n");
        
        List<Medium> top5Medium = serviceEmp.topMediums();
        for(int i = 1; i <= top5Medium.size(); i++){
            System.out.println("Medium n°" + i + " = " + top5Medium.get(i-1).getDenomination() + " nombre de consultation = "+top5Medium.get(i-1).getNombreChoisi()+"\n");
        }
        
        System.out.println("\n");
        
        //L'employé cherche à afficher la répatition des Clients selon les Employe
        System.out.println("L'employé cherche à afficher la répatition des Clients selon les Employe\n");
        
        Map<Employe, Double> repartition = new HashMap<>();
        repartition = serviceEmp.repartitionEmployeClient();
        
        for (Map.Entry<Employe, Double> entry : repartition.entrySet()) {
            System.out.println("Employe = " + entry.getKey().getNom() + " " + entry.getKey().getPrenom() + ", Pourcentage de répartition = " + entry.getValue() + "%");
        }
        
        System.out.println("\n");
        
        //L'employé cherche à afficher le nombre de consultation par médiums
        System.out.println("L'employé cherche à afficher le nombre de consultation par médiums\n");
        
        Map<Medium, Integer> mediumConsults = new HashMap<>();
        mediumConsults = serviceEmp.nombreConsultMediums();
        
        for (Map.Entry<Medium, Integer> entry : mediumConsults.entrySet()) {
            System.out.println("Medium = " + entry.getKey().getDenomination() + ", Nombre de consultation = " + entry.getValue());
        }
        
        System.out.println("-------------------------------------------------------\n");
        
        JPAutil.fermerFabriquePersistance(); // fermeture de la fabrique de persistence
        
    }
    
}
