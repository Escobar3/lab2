/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import VO.Producto;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LUIS
 */
public class ProductoDAO implements IBaseDatos<Producto> {

    public Producto find(int id) throws SQLException, URISyntaxException {
        Producto resultado = null;
        String query = "Select * from Producto Where id_producto =" + id;
        Connection connection = Conexion.getConnection();
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            int id_P = 0;
            String nombre = null;
            double precio = 0.0;
            int cantidad = 0;

            if (rs.next()) {

                id_P = rs.getInt("id_producto");

                nombre = rs.getString("nombre");

                precio = rs.getDouble("precio");

                cantidad = rs.getInt("cantidad");
                resultado = new Producto(id_P, nombre, precio, cantidad);

            }
            st.close();
        } catch (SQLException e) {
            System.out.println("Problemas al obtener persona");
            e.printStackTrace();
        }
        return resultado;
    }

    @Override
    public List findAll() throws SQLException {
        List<Producto> productos = null;
        String query = "Select * from Producto ";
        Connection connection = null;
        try {
            connection = Conexion.getConnection();
        } catch (URISyntaxException ex) {
            Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("asdfsdgsgsdgsgdcdgdgv");
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            int id_P = 0;
            String nombre = null;
            double precio = 0.0;
            int cantidad = 0;
            while (rs.next()) {
                if (productos == null) {
                    productos = new ArrayList<Producto>();
                }

                Producto registro = new Producto();
                registro = new Producto();
                id_P = rs.getInt("id_producto");
                registro.setId_producto(id_P);
                nombre = rs.getString("nombre");
                registro.setNombre(nombre);
                precio = rs.getDouble("precio");
                registro.setPrecio(precio);
                cantidad = rs.getInt("cantidad");
                registro.setCantidad(cantidad);

                registro = new Producto(id_P, nombre, precio, cantidad);
                productos.add(registro);
            }
            
            
            System.out.println("daoooooooooo");
            st.close();

        } catch (SQLException e) {
            System.out.println("Problemas al obtener la lista de Departamentos");
            e.printStackTrace();
        }

        return productos;

    }

    @Override
    public boolean insert(Producto t) throws SQLException {
        boolean result = false;
        Connection connection = null;
        try {
            connection = Conexion.getConnection();
        } catch (URISyntaxException ex) {
            Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        String query = " insert into Producto(id_producto,nombre,precio,cantidad,id_sucursal ) values (?,?,?,?,?)";
        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(1, t.getId_producto());
            preparedStmt.setString(2, t.getNombre());
            preparedStmt.setDouble(3, t.getPrecio());
            preparedStmt.setInt(4, t.getCantidad());
            preparedStmt.setInt(5, 1);
            result = preparedStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean update(Producto t) throws SQLException {
        boolean result = false;
        Connection connection = null;
        try {
            connection = Conexion.getConnection();
        } catch (URISyntaxException ex) {
            Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        String query
                = "update Producto set cantidad = ? where id_producto = ?";
        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(1, t.getCantidad());
            preparedStmt.setInt(2, t.getId_producto());       
  

            if (preparedStmt.executeUpdate() > 0) {
                result = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;

    }

    public List ConsulP_item() throws SQLException, URISyntaxException {
        System.out.println("asdasd");
        List<Producto> productos = null;
        System.out.println("sadsada");
        String query = "SELECT item_vent.id_producto,producto.nombre, sum(item_vent.cantidad) as cantidad ,\n"
                + "(select sum(item_vent.cantidad)*100/(select sum(item_vent.cantidad)from item_vent\n"
                + "inner join producto on(item_vent.id_producto = producto.id_producto) ) )as porcentaje\n"
                + "FROM item_vent inner join producto on(item_vent.id_producto = producto.id_producto)\n"
                + "group by item_vent.id_producto ";
        System.out.println(query);
        Connection connection = Conexion.getConnection();
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            int id_P = 0;
            String nombre = null;
            int cantidad = 0;
            double porcen = 0.0;
            while (rs.next()) {
                if (productos == null) {
                    productos = new ArrayList<Producto>();
                }

                Producto registro = new Producto();
                registro = new Producto();
                id_P = rs.getInt("id_producto");
                registro.setId_producto(id_P);
                nombre = rs.getString("nombre");
                registro.setNombre(nombre);
                cantidad = rs.getInt("cantidad");
                porcen=rs.getDouble("porcentaje");
                registro.setCantidad(cantidad);
                registro.setPorcentaje(porcen);
                productos.add(registro);
            }
            st.close();

        } catch (SQLException e) {
            System.out.println("Problemas al obtener la lista de Departamentos");
            e.printStackTrace();
        }

        return productos;

    }

    @Override
    public boolean delete(Producto t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
