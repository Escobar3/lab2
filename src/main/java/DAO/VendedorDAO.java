/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import VO.Vendedor;

/**
 *
 * @author LUIS
 */
public class VendedorDAO implements IBaseDatos<Vendedor> {

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public Vendedor find(String usuario) throws SQLException {
        Vendedor resultado = null;
        String query = "Select * from Vendedor Where usuario ='" + usuario + "'";
        System.out.println(query);
        Connection connection = Conexion.getConnection();
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            int id = 0;
            String nombre = null, apellido = null;
            String user = null, clave = null;
            if (rs.next()) {
                resultado = new Vendedor();
                id = rs.getInt("id_vendedor");
                resultado.setId_vendedor(id);
                nombre = rs.getString("nombre");
                resultado.setNombre(nombre);
                apellido = rs.getString("apellido");
                resultado.setApellido(apellido);
                user = rs.getString("usuario");
                resultado.setUsuario(user);
                clave = rs.getString("clave");
                resultado.setClave(clave);

                resultado = new Vendedor(nombre, apellido, id, user, clave, 1);

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
        List<Vendedor> vendedors = null;
        String query = "SELECT * FROM Vendedor";
        Connection connection = Conexion.getConnection();
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            int id = 0;
            String nombre = null, apellido = null;
            String user = null, clave = null;
            int productos_v = 0;
            while (rs.next()) {
                if (vendedors == null) {
                    vendedors = new ArrayList<Vendedor>();
                }

                Vendedor registro = new Vendedor();
                id = rs.getInt("id_vendedor");
                registro.setId_vendedor(id);
                nombre = rs.getString("nombre");
                registro.setNombre(nombre);
                apellido = rs.getString("apellido");
                registro.setApellido(apellido);
                user = rs.getString("usuario");
                registro.setUsuario(user);
                clave = rs.getString("clave");
                registro.setClave(clave);
                vendedors.add(registro);
            }
            st.close();

        } catch (SQLException e) {
            System.out.println("Problemas al obtener la lista de Departamentos");
            e.printStackTrace();
        }

        return vendedors;
    }

    @Override
    public boolean insert(Vendedor t) throws SQLException {
        boolean result = false;
        Connection connection = Conexion.getConnection();
        String query = " insert into Vendedor (id_vendedor,usuario,clave,nombre,apellido,id_sucursal ) values (?,?,?,?,?,?)";
        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(1, t.getId_vendedor());
            preparedStmt.setString(2, t.getUsuario());
            preparedStmt.setString(3, t.getClave());
            preparedStmt.setString(4, t.getNombre());
            preparedStmt.setString(5, t.getApellido());
            preparedStmt.setInt(6, 1);
            result = preparedStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;

    }

    @Override
    public boolean update(Vendedor t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Vendedor t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List ConsulVe_vent() throws SQLException {
        List<Vendedor> vendedors = null;
        String query = "SELECT ventas.id_vendedor,vendedor.nombre, COUNT(*)as total\n"
                + "from vendedor inner join ventas on(vendedor.id_vendedor=ventas.id_vendedor)\n"
                + "group by vendedor.id_vendedor;";
        Connection connection = Conexion.getConnection();
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            int id = 0;
            int total = 0;
            String nombre = null;
         
            int productos_v = 0;
            while (rs.next()) {
                if (vendedors == null) {
                    vendedors = new ArrayList<Vendedor>();
                }
                Vendedor registro = new Vendedor();
                id = rs.getInt("id_vendedor");
                registro.setId_vendedor(id);
                total = rs.getInt("total");
                registro.setProductos_vend(total);
                nombre=rs.getString("nombre");
                registro.setNombre(nombre);
                System.out.println(registro.getProductos_vend());
                vendedors.add(registro);
            }
            st.close();

        } catch (SQLException e) {
            System.out.println("Problemas al obtener la lista de Departamentos");
            e.printStackTrace();
        }

        return vendedors;
    }

}
