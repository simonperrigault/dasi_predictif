package controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
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
import vue.ProfilEmployeSerialisation;

/**
 *
 * @author sperrigaul
 */
@WebServlet(urlPatterns = {"/ActionServlet"})
public class ActionServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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

