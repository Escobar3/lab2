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
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

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
            }
            System.out.println(listProdutos.size());

            request.setAttribute("lista", listProdutos);

        } else if (request.getParameter("Boton2") != null) {
            System.out.println(request.getParameter("Boton1"));
            try {
                listVendedor = vendedor.ConsulVe_vent();
            } catch (SQLException ex) {
                Logger.getLogger(Caja_Servlet.class.getName()).log(Level.SEVERE, null, ex);
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
 JSONArray varJsonArrayP = new JSONArray();
 JSONArray varJsonArrayV= new JSONArray();
        PrintWriter escritor = response.getWriter();
        if (txtValop != null) {

            if (txtValop.equalsIgnoreCase("GU")) {
               
                try {
                    listProdutos = producto.ConsulP_item();
                } catch (SQLException ex) {
                    Logger.getLogger(ReportServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                JSONArray varJObjectLista = metGetListaP(listProdutos, varJsonArrayP);
                escritor.print(varJObjectLista);

            } else if (txtValop.equalsIgnoreCase("VE")) {
                
                try {

                    listVendedor = vendedor.ConsulVe_vent();
                } catch (SQLException ex) {
                    Logger.getLogger(ReportServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                JSONArray varJObjectLista2 = metGetListaV(listVendedor, varJsonArrayV);
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
    public JSONArray metGetListaV(List<Vendedor> in, JSONArray varJsonArrayV) {

        JSONObject varJsonObjectResultado = new JSONObject();
        try {
            for (int i = 0; i < in.size(); i++) {
                JSONObject varJsonObjectP = new JSONObject();

                System.out.println("------------------------");
                Vendedor p = in.get(i);
                varJsonObjectP.put("nombre", p.getNombre());
                varJsonObjectP.put("cantidad", p.getProductos_vend());
                varJsonObjectP.put("porcentage", p.getPorcentaje());

                varJsonArrayV.add(varJsonObjectP);
                varJsonObjectP = (JSONObject) varJsonArrayV.get(i);
                System.out.println("-------------------");
                System.out.println(varJsonObjectP.toJSONString());
                System.out.println("-----------------------------");
                System.out.println(varJsonArrayV.get(i));

            }
            varJsonObjectResultado.put("Result", "OK");
            varJsonObjectResultado.put("Records", varJsonArrayV);
        } catch (Exception e) {
            e.printStackTrace();
            varJsonObjectResultado.put("Result", "ERROR");
            varJsonObjectResultado.put("Message", e.getMessage());
        }
        return varJsonArrayV;
    }

    public JSONArray metGetListaP(List<Producto> in, JSONArray varJsonArrayP) {

        JSONObject varJsonObjectResultado = new JSONObject();
        try {
            for (int i = 0; i < in.size(); i++) {
                JSONObject varJsonObjectP = new JSONObject();

                System.out.println("------------------------");
                Producto p = in.get(i);
                varJsonObjectP.put("id", p.getId_producto());
                varJsonObjectP.put("nombre", p.getNombre());
                varJsonObjectP.put("cantidad", p.getCantidad());
                varJsonObjectP.put("porcentage", p.getPorcentaje());
                varJsonArrayP.add(varJsonObjectP);
                varJsonObjectP = (JSONObject) varJsonArrayP.get(i);
                System.out.println("-------------------");
                System.out.println(varJsonObjectP.toJSONString());
                System.out.println("-----------------------------");
                System.out.println(varJsonArrayP.get(i));

            }
            varJsonObjectResultado.put("Result", "OK");
            varJsonObjectResultado.put("Records", varJsonArrayP);
        } catch (Exception e) {
            e.printStackTrace();
            varJsonObjectResultado.put("Result", "ERROR");
            varJsonObjectResultado.put("Message", e.getMessage());
        }
        return varJsonArrayP;
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
