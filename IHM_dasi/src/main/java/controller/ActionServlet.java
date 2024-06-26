package controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modele.AuthentifierClientAction;
import vue.ProfilClientSerialisation;
import dao.JPAutil;
import metier.service.ServiceClient;
import metier.service.ServiceEmploye;
import modele.AuthentifierEmployeAction;
import modele.AvoirAideAction;
import modele.GetConsultationCouranteAction;
import modele.GetInfosClientAction;
import modele.GetInfosEmployeAction;
import modele.GetTopMediums;
import modele.CreerConsultationAction;
import modele.DeconnexionAction;
import modele.GetAllConsultationsAction;
import modele.GetConsultationsEmployeAction;
import modele.GetConsultationsParMediumsAction;
import modele.GetRepartitionAction;
import modele.InscrireClientAction;
import modele.ModifierClientAction;
import modele.ModifierEmployeAction;
import modele.TerminerConsultationAction;
import modele.ValiderConsultationAction;
import vue.BooleanSerialisation;
import vue.ConsultationSerialisation;
import vue.ListeConsultationsSerialisation;
import vue.ListeProfilsMediumsSerialisation;
import vue.MapAideSerialisation;
import vue.MapConsultMediumSerialisation;
import vue.MapRepartitionSerialisation;
import vue.ProfilEmployeSerialisation;

/**
 *
 * @author sperrigaul
 */
@WebServlet(urlPatterns = {"/ActionServlet"})
public class ActionServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
        JPAutil.creerFabriquePersistance();
    }

  
    protected void processRequest(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        ServiceClient servClient = new ServiceClient();
        ServiceEmploye servEmploye = new ServiceEmploye();
        String todo = req.getParameter("todo");
        System.out.println("Trace : todo = " + todo);
        switch (todo) {
            case "connecterClient": {
                new AuthentifierClientAction(servClient, servEmploye).execute(req);
                new ProfilClientSerialisation().appliquer(req, res);
                break;
            }
            case "connecterEmploye": {
                new AuthentifierEmployeAction(servClient, servEmploye).execute(req);
                new ProfilEmployeSerialisation().appliquer(req, res);
                break;
            }
            case "inscrireClient": {
                new InscrireClientAction(servClient, servEmploye).execute(req);
                new ProfilClientSerialisation().appliquer(req, res);
                break;
            }
            case "getInfosClient": {
                new GetInfosClientAction(servClient, servEmploye).execute(req);
                new ProfilClientSerialisation().appliquer(req, res);
                break;
            }
            case "setInfosClient": {
                //System.out.println("update : "+req);
                new ModifierClientAction(servClient, servEmploye).execute(req);
                new ProfilClientSerialisation().appliquer(req, res);
                break;
            }
            case "setInfosEmploye": {
                //System.out.println("update : "+req);
                new ModifierEmployeAction(servClient, servEmploye).execute(req);
                new ProfilEmployeSerialisation().appliquer(req, res);
                break;
            }
            case "getInfosEmploye": {
                new GetInfosEmployeAction(servClient, servEmploye).execute(req);
                new ProfilEmployeSerialisation().appliquer(req, res);
                break;
            }
            case "getTopMediums": {
                new GetTopMediums(servClient, servEmploye).execute(req);
                new ListeProfilsMediumsSerialisation().appliquer(req, res);
                break;
            }
            case "getAllMediums": {
                new GetTopMediums(servClient, servEmploye).execute(req);
                new ListeProfilsMediumsSerialisation().appliquer(req, res);
                break;
            }
            case "creerConsultation": {
                new CreerConsultationAction(servClient, servEmploye).execute(req);
                new ConsultationSerialisation().appliquer(req, res);
                break;
            }
            case "getConsultationCourante": {
                new GetConsultationCouranteAction(servClient, servEmploye).execute(req);
                new ConsultationSerialisation().appliquer(req, res);
                break;
            }
            case "validerConsultation": {
                new ValiderConsultationAction(servClient, servEmploye).execute(req);
                new BooleanSerialisation().appliquer(req, res);
                break;
            }
            case "terminerConsultation": {
                new TerminerConsultationAction(servClient, servEmploye).execute(req);
                new BooleanSerialisation().appliquer(req, res);
                break;
            }
            case "avoirAide": {
                new AvoirAideAction(servClient, servEmploye).execute(req);
                new MapAideSerialisation().appliquer(req, res);
                break;
            }
            case "getRepartitionEmployeClient": {
                new GetRepartitionAction(servClient, servEmploye).execute(req);
                new MapRepartitionSerialisation().appliquer(req, res);
                break;
            }
            case "getConsultMedium": {
                new GetConsultationsParMediumsAction(servClient, servEmploye).execute(req);
                new MapConsultMediumSerialisation().appliquer(req, res);
                break;
            }
            case "getAllConsul" :{
                new GetAllConsultationsAction(servClient,servEmploye).execute(req);
                new ListeConsultationsSerialisation().appliquer(req, res);
                break;            
            }
            case "getConsultEmploye": {
                new GetConsultationsEmployeAction(servClient, servEmploye).execute(req);
                
                new ListeConsultationsSerialisation().appliquer(req, res);
                
                break;
            }
            
            case "deconnexion" : {
                new DeconnexionAction(servClient, servEmploye).execute(req);
                new BooleanSerialisation().appliquer(req, res);
                
            }
            
        }
    }

    @Override
    public void destroy() {
        JPAutil.fermerFabriquePersistance();
        super.destroy();
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
