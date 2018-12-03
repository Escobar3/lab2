/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO.ProductoDAO;
import DAO.VendedorDAO;
import VO.Producto;
import VO.Vendedor;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jasonjson.core.JsonObject;
import org.jasonjson.core.JsonArray;
/**
 *
 * @author Luis
 */
public class ReportServlet extends HttpServlet {

    ProductoDAO producto;
    VendedorDAO vendedor;
    private List<Producto> listProdutos;
    private List<Vendedor> listVendedor;

    @Override
    public void init() throws ServletException {
        this.producto = new ProductoDAO();
        this.vendedor = new VendedorDAO();
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

        RequestDispatcher rq = request.getRequestDispatcher("Report.jsp");
        if (request.getParameter("Boton1") != null) {
            System.out.println(request.getParameter("Boton1"));
            try {
                listProdutos = producto.ConsulP_item();
            } catch (SQLException ex) {
                Logger.getLogger(Caja_Servlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (URISyntaxException ex) {
                Logger.getLogger(ReportServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println(listProdutos.size());

            request.setAttribute("lista", listProdutos);

        } else if (request.getParameter("Boton2") != null) {
            System.out.println(request.getParameter("Boton1"));
            try {
                listVendedor = vendedor.ConsulVe_vent();
            } catch (SQLException ex) {
                Logger.getLogger(Caja_Servlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (URISyntaxException ex) {
                Logger.getLogger(ReportServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            request.setAttribute("lista2", listVendedor);
        }
        rq.forward(request, response);
        doPost(request, response);
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
        String txtValop = request.getParameter("txtValOpe");
 JsonArray varJsonArrayP = new JsonArray();
 JsonArray varJsonArrayV= new JsonArray();
        PrintWriter escritor = response.getWriter();
        if (txtValop != null) {

            if (txtValop.equalsIgnoreCase("GU")) {
               
                try {
                    listProdutos = producto.ConsulP_item();
                } catch (SQLException ex) {
                    Logger.getLogger(ReportServlet.class.getName()).log(Level.SEVERE, null, ex);
                } catch (URISyntaxException ex) {
                    Logger.getLogger(ReportServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                JsonArray varJObjectLista = metGetListaP(listProdutos, varJsonArrayP);
                escritor.print(varJObjectLista);

            } else if (txtValop.equalsIgnoreCase("VE")) {
                
                try {

                    listVendedor = vendedor.ConsulVe_vent();
                } catch (SQLException ex) {
                    Logger.getLogger(ReportServlet.class.getName()).log(Level.SEVERE, null, ex);
                } catch (URISyntaxException ex) {
                    Logger.getLogger(ReportServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                JsonArray varJObjectLista2 = metGetListaV(listVendedor, varJsonArrayV);
                escritor.print(varJObjectLista2);
            }
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @param in
     * @param varJsonArrayP
     * @return a String containing servlet description
     */
    public JsonArray metGetListaV(List<Vendedor> in, JsonArray varJsonArrayV) {

        JsonObject varJsonObjectResultado = new JsonObject();
        try {
            for (int i = 0; i < in.size(); i++) {
                JsonObject varJsonObjectP = new JsonObject();

                System.out.println("------------------------");
                Vendedor p = in.get(i);
                varJsonObjectP.addProperty("nombre", p.getNombre());
                varJsonObjectP.addProperty("cantidad", p.getProductos_vend());
                varJsonObjectP.addProperty("porcentage", p.getPorcentaje());

                varJsonArrayV.add(varJsonObjectP);
                varJsonObjectP = (JsonObject) varJsonArrayV.get(i);
                System.out.println("-------------------");
                System.out.println(varJsonObjectP.toString());
                System.out.println("-----------------------------");
                System.out.println(varJsonArrayV.get(i));

            }
            varJsonObjectResultado.addProperty("Result", "OK");
            varJsonObjectResultado.add("Records", varJsonArrayV);
        } catch (Exception e) {
            e.printStackTrace();
            varJsonObjectResultado.addProperty("Result", "ERROR");
            varJsonObjectResultado.addProperty("Message", e.getMessage());
        }
        return varJsonArrayV;
    }

    public JsonArray metGetListaP(List<Producto> in, JsonArray varJsonArrayP) {

        JsonObject varJsonObjectResultado = new JsonObject();
        try {
            for (int i = 0; i < in.size(); i++) {
                JsonObject varJsonObjectP = new JsonObject();

                System.out.println("------------------------");
                Producto p = in.get(i);
                varJsonObjectP.addProperty("id", p.getId_producto());
                varJsonObjectP.addProperty("nombre", p.getNombre());
                varJsonObjectP.addProperty("cantidad", p.getCantidad());
                varJsonObjectP.addProperty("porcentage", p.getPorcentaje());
                varJsonArrayP.add(varJsonObjectP);
                varJsonObjectP = (JsonObject) varJsonArrayP.get(i);
                System.out.println("-------------------");
                System.out.println(varJsonObjectP.toString());
                System.out.println("-----------------------------");
                System.out.println(varJsonArrayP.get(i));

            }
            varJsonObjectResultado.addProperty("Result", "OK");
            varJsonObjectResultado.add("Records", varJsonArrayP);
        } catch (Exception e) {
            e.printStackTrace();
            varJsonObjectResultado.addProperty("Result", "ERROR");
            varJsonObjectResultado.addProperty("Message", e.getMessage());
        }
        return varJsonArrayP;
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
