/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import DAO.*;
import VO.*;
import java.net.URISyntaxException;
import javax.servlet.annotation.WebServlet;
import java.sql.SQLException;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.time.LocalDate;
import org.jasonjson.core.JsonObject;
import org.jasonjson.core.JsonArray;
/**
 *
 * @author LUIS
 */
@WebServlet(name = "Caja_Servlet", urlPatterns = {"/Caja_Servlet"})
public class Caja_Servlet extends HttpServlet {

    ProductoDAO producto;
    VendedorDAO vendedor;
    Item_ventDAO item;
    CajaDAO caja;
    VentaDAO venta;
    private Venta ven;
    private List<Producto> listProdutos;
    List<Producto> inven = new ArrayList<Producto>();
    List<Item_vent> items = new ArrayList<Item_vent>();

    @Override
    public void init() throws ServletException {
        this.producto = new ProductoDAO();
        this.venta = new VentaDAO();
        this.vendedor = new VendedorDAO();
        this.caja = new CajaDAO();
        this.item = new Item_ventDAO();
        this.ven = new Venta();
        ven = new Venta();
        try {
            venta.insert(new Venta());
        } catch (SQLException ex) {
            Logger.getLogger(Caja_Servlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*try {
            venta.insert(new Venta());
        } catch (SQLException ex) {
            Logger.getLogger(Caja_Servlet.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.sql.SQLException
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
             RequestDispatcher rq = request.getRequestDispatcher("Caja.jsp");
        try {
            listProdutos = producto.findAll();
        } catch (SQLException ex) {
            Logger.getLogger(Caja_Servlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(listProdutos.size());
        System.out.println("dsfsdgssdgsgsdgsdgsdgsgs");
        request.setAttribute("p2", listProdutos);

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
        response.setContentType("text/html");
        PrintWriter escritor = response.getWriter();
        String idP = request.getParameter("productos");
        String idC = request.getParameter("idCaja");
        String UserV = request.getParameter("UserV");
        String p = request.getParameter("p");
        String f = request.getParameter("fecha");
        String can = request.getParameter("unds");
        System.out.println(can);
        System.out.println(f);

        if (txtValop != null) {
            try {

                if (txtValop.equalsIgnoreCase("GU")) {
                    int unds = Integer.parseInt(can);

                    Item_vent itemDeVenda = new Item_vent();
                    System.out.println(idP);
                    System.out.println(idC);
                    System.out.println(Integer.parseInt(idC));
                    Caja aux = caja.find(Integer.parseInt(idC));

                    Vendedor vend = vendedor.find(UserV);
                    if (aux != null && vend != null) {
                        ven = venta.buscarUltimaVenda();
                        Producto p1 = producto.find(Integer.parseInt(idP));
                        double pre = aux.calP(p1.getPrecio(), unds);
                        p1.setCantidad(unds);
                        p1.setPrecio(pre);
                        itemDeVenda.setVenta(ven);
                        itemDeVenda.setProducto(p1);
                        itemDeVenda.setCantidad(p1.getCantidad());
                        itemDeVenda.setValor(pre);
                        System.out.println(vend.getApellido());
                        ven.setData(f);
                        ven.setVendedor(vend);
                        ven.setCaja(aux);
                        ven.setValor(ven.getValor() + itemDeVenda.getValor());
                        items.add(itemDeVenda);
                        ven.setItem_vents(items);
                        inven.add(p1);
                        System.out.println(ven.getItem_vents().size());
                        System.out.println("enviando");

                        JsonArray varJObjectLista = metGetLista(inven, varJsonArrayP);
                        escritor.print(varJObjectLista);

                    }
                } else if (txtValop.equalsIgnoreCase("RE")) {
                  
                    ven.getCaja().setDinero(ven.getValor());
                    System.out.println(ven.getCaja().getDinero());
                    for (int x = 0; x < ven.getItem_vents().size(); x++) {
                        System.out.println("sadasggggggggggggg");
                        System.out.println(ven.getId_venta());
                        System.out.println(ven.getItem_vents().get(x).getProducto().getId_producto());
                        System.out.println("------------------------------------");
                        System.out.println();
                        item.insert(ven.getItem_vents().get(x));

                    }
                    System.out.println(ven.getData());
                    System.out.println("+++++++++++++++++");
                    venta.update(ven);

                }
            } catch (SQLException ex) {
                Logger.getLogger(Caja_Servlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (URISyntaxException ex) {
                Logger.getLogger(Caja_Servlet.class.getName()).log(Level.SEVERE, null, ex);
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


    ;
    
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
                System.out.println(varJsonObjectP.toString().toString());
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
