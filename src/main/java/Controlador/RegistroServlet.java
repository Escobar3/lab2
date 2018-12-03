/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import DAO.*;
import VO.*;

/**
 *
 * @author Luis
 */
public class RegistroServlet extends HttpServlet {

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

        if (request.getParameter("guardar") != null) {
            //Estoy editando XD
            String cedula = request.getParameter("id");
            int id = Integer.parseInt(cedula);
            String nombre = request.getParameter("nombre");
            String apellido = request.getParameter("apellido");
            String user = request.getParameter("nombre");
            String clave = request.getParameter("apellido");
            String tipo = request.getParameter("tipo");
            if (nombre != null && apellido != null) {
                if (tipo.equalsIgnoreCase("Vendedor")) {
                    System.out.println("ven");
                    try {
                        vend = new Vendedor(nombre, apellido, id, user, clave, 0);

                        this.vendedor.insert(vend);
                    } catch (SQLException ex) {
                        Logger.getLogger(RegistroServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    Adm = new Administrador(id, user, clave, nombre, apellido);
                    try {
                        this.admon.insert(Adm);
                    } catch (SQLException ex) {
                        Logger.getLogger(RegistroServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

            response.sendRedirect("Registro.jsp");
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
