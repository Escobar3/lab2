/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO.AdministradorDAO;
import DAO.VendedorDAO;

import VO.Vendedor;
import VO.Administrador;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Luis
 */
public class LoginServlet extends HttpServlet {

    VendedorDAO vendedor;
    AdministradorDAO admon;

    Administrador Adm;
    Vendedor vend;

    @Override
    public void init() throws ServletException {
        this.vendedor = new VendedorDAO();
        this.admon = new AdministradorDAO();
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
        
        
        
        RequestDispatcher rq = request.getRequestDispatcher("login.jsp");
        rq.forward(request, response);
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
        System.out.println("asdasfasffasf");
        System.out.println("yolooooooooooooooooooooooooa");
        String Usuario = request.getParameter("Usuario");
        String Clave = request.getParameter("Clave");

        if (request.getParameter("Boton1") != null) {
            System.out.println("---------------------");
            System.out.println(Usuario);

            if (Usuario != null && Clave != null) {
                try {
                    System.out.println("entro");
                    Adm = admon.find(Usuario);
                    vend = vendedor.find(Usuario);
                } catch (SQLException ex) {
                    Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
                } catch (URISyntaxException ex) {
                    Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
                }

                if (Adm != null) {
                    System.out.println("entro adm");
                    if (Clave.equalsIgnoreCase(Adm.getClave())) {
                        System.out.println("yolodrfd");
                        response.sendRedirect("vAdministrador.jsp");
                    }
                } else if (vend != null) {
                    System.out.println("entro vend");
                    if (Clave.equalsIgnoreCase(vend.getClave())) {
                        System.out.println("yoloVEND");
                        response.sendRedirect("Caja.jsp");
                    }
                }

            }
        }
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
