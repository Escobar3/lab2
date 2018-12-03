/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import org.jasonjson.core.JsonObject;
import org.jasonjson.core.JsonArray;
import DAO.*;
import VO.*;
import java.net.URISyntaxException;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Luis
 */
public class InventarioServlet2 extends HttpServlet {

    ProductoDAO producto;
    private List<Producto> listProdutos;
    List <Producto> inven = new ArrayList<Producto>();

    @Override
    public void init() throws ServletException {
        this.producto = new ProductoDAO();

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
        System.out.println("yololooooo");
        request.getRequestDispatcher("Inventario.jsp").forward(request, response);
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
        response.setContentType("text/html");
        PrintWriter escritor = response.getWriter();
        String nombre = request.getParameter("producto");
        String idp = request.getParameter("idp");
        String precio = request.getParameter("precio");
        String cantidad = request.getParameter("cantidad");

        System.out.println(txtValop);
        if (txtValop != null) {
            System.out.println("  botn");
            if (txtValop.equalsIgnoreCase("GU")) {
                int idP = Integer.parseInt(idp);

                int c = Integer.parseInt(cantidad);
                double pre = Double.parseDouble(precio);
                Producto p = new Producto(idP, nombre, pre, c);
                try {
                    Producto aux = producto.find(idP);
                    System.out.println(idp);
                    if (aux == null) {
                        try {
                            producto.insert(p);
                        } catch (SQLException ex) {
                            Logger.getLogger(InventarioServlet2.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        int res=p.getCantidad()+aux.getCantidad();
                        p.setCantidad(res);
                        producto.update(p);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(InventarioServlet2.class.getName()).log(Level.SEVERE, null, ex);
                } catch (URISyntaxException ex) {
                    Logger.getLogger(InventarioServlet2.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else if (txtValop.equalsIgnoreCase("MU")) {
                try {
                    System.out.println(txtValop);
                    inven = producto.findAll();
                } catch (SQLException ex) {
                    Logger.getLogger(InventarioServlet2.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (inven != null) {
                    JsonArray varJObjectLista = metGetLista(inven, varJsonArrayP);
                    escritor.print(varJObjectLista);
                } else {
                    JsonArray varJObjectLista = new JsonArray();
                    escritor.print("null");
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

    public JsonArray metGetLista(List<Producto> in, JsonArray varJsonArrayP) {

        JsonObject varJsonObjectResultado = new JsonObject();
        try {
            for (int i = 0; i < in.size(); i++) {
                JsonObject varJsonObjectP = new JsonObject();

                System.out.println("------------------------");
                Producto p = in.get(i);
                varJsonObjectP.addProperty("id", p.getId_producto());
                varJsonObjectP.addProperty("nombre", p.getNombre());
                varJsonObjectP.addProperty("cantidad", p.getCantidad());
                varJsonObjectP.addProperty("precio", p.getPrecio());
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

}
