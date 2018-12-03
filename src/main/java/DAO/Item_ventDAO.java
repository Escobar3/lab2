/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import VO.Item_vent;
import VO.Producto;
import VO.Venta;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LUIS
 */
public class Item_ventDAO implements IBaseDatos<Item_vent> {

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public Item_vent find(int id) throws SQLException, URISyntaxException {
        Item_vent resultado = null;
        String query = "Select * from item_vent Where id_producto =" + id;
        Connection connection = Conexion.getConnection();
        Producto producto = new Producto();
        Venta venta = new Venta();
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            int id_P = 0;

            double precio = 0.0;
            int cantidad = 0;
            int id_ve = 0;
            int id_p = 0;
            if (rs.next()) {
                resultado = new Item_vent();
                id_P = rs.getInt("id_item");
                resultado.setId_item(id_P);
                precio = rs.getDouble("valor");
                resultado.setValor(precio);
                cantidad = rs.getInt("cantidad");
                resultado.setCantidad(cantidad);
                id_p = rs.getInt("id_producto");
                id_ve = rs.getInt("id_ventas");
                ProductoDAO p = new ProductoDAO();
                VentaDAO v = new VentaDAO();
                producto = p.find(id_p);
                venta = v.find(id_ve);
                resultado.setProducto(producto);
                resultado.setVenta(venta);

            }
            st.close();
        } catch (SQLException e) {
            System.out.println("Problemas al obtener persona");
            e.printStackTrace();
        }
        return resultado;
    }

    @Override
    public List<Item_vent> findAll() throws SQLException {
        List<Item_vent> items = null;
        Venta venta = new Venta();
        Producto producto = new Producto();
        String query = "SELECT * FROM item_vent";
        Connection connection = null;
        try {
            connection = Conexion.getConnection();
        } catch (URISyntaxException ex) {
            Logger.getLogger(Item_ventDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            int id_P = 0;
            double precio = 0.0;
            int cantidad = 0;
            int id_ve = 0;
            int id_p = 0;
            while (rs.next()) {
                if (items == null) {
                    items = new ArrayList<Item_vent>();
                }

                Item_vent registro = new Item_vent();
                id_P = rs.getInt("id_prodcuto");
                registro.setId_item(id_P);
                precio = rs.getDouble("precio");
                registro.setValor(precio);
                cantidad = rs.getInt("cantidad");
                registro.setCantidad(cantidad);
                id_p = rs.getInt("id_producto");
                id_ve = rs.getInt("id_ventas");
                ProductoDAO p = new ProductoDAO();
                VentaDAO v = new VentaDAO();
                producto = p.find(id_p);
                venta = v.find(id_ve);
                registro.setProducto(producto);
                registro.setVenta(venta);
                items.add(registro);
            }
            st.close();

        } catch (SQLException e) {
            System.out.println("Problemas al obtener la lista de Departamentos");
            e.printStackTrace();
        } catch (URISyntaxException ex) {
            Logger.getLogger(Item_ventDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return items;
    }

    @Override
    public boolean insert(Item_vent t) throws SQLException {
        boolean result = false;
        Connection connection = null;
        try {
            connection = Conexion.getConnection();
        } catch (URISyntaxException ex) {
            Logger.getLogger(Item_ventDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        String query = " insert into item_vent (valor,cantidad,id_ventas,id_producto) values (?,?,?,?)";
        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = connection.prepareStatement(query);

            preparedStmt.setDouble(1, t.getValor());
            preparedStmt.setInt(2, t.getCantidad());
            System.out.println(t.getVenta().getId_venta());
            preparedStmt.setInt(3, t.getVenta().getId_venta());
            preparedStmt.setInt(4, t.getProducto().getId_producto());

            result = preparedStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean update(Item_vent t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Item_vent t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
   
}
