/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import VO.*;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LUIS
 */
public class VentaDAO implements IBaseDatos<Venta> {

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public Venta find(int id) throws SQLException {
        Vendedor vendedor = new Vendedor();
        Caja caja = new Caja();
        Venta resultado = new Venta();
        String query = "Select * from ventas Where usuario =" + id;
        System.out.println(query);
        Connection connection = Conexion.getConnection();
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            int id_v = 0;
            double valor = 0.0;
            int id_vend = 0;
            int id_c = 0;
            String dato = null;

            if (rs.next()) {
                resultado = new Venta();
                id_v = rs.getInt("id_ventas");
                resultado.setId_venta(id);
                valor = rs.getDouble("valor");
                resultado.setValor(valor);
                id_vend = rs.getInt("id_vendedor");
                id_c = rs.getInt("id_caja");
                dato = rs.getString("dato");
                vendedor.setId_vendedor(id_vend);
                caja.setId_caja(id_c);
                resultado.setData(dato);
                resultado.setCaja(caja);
                resultado.setVendedor(vendedor);

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
        List<Venta> ventas = null;
        Vendedor vendedor = new Vendedor();
        Caja caja = new Caja();
        String query = "SELECT * FROM ventas";
        Connection connection = Conexion.getConnection();
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            int id_v = 0;
            double valor = 0.0;
            int id_vend = 0;
            int id_c = 0;
            String dato = null;
            while (rs.next()) {
                if (ventas == null) {
                    ventas = new ArrayList<Venta>();
                }

                Venta registro = new Venta();

                id_v = rs.getInt("id_ventas");
                registro.setId_venta(id_v);
                valor = rs.getDouble("valor");
                registro.setValor(valor);
                id_vend = rs.getInt("id_vendedor");
                id_c = rs.getInt("id_caja");
                dato = rs.getString("dato");
                vendedor.setId_vendedor(id_vend);
                caja.setId_caja(id_c);
                registro.setData(dato);
                registro.setCaja(caja);
                registro.setVendedor(vendedor);
                ventas.add(registro);
            }
            st.close();

        } catch (SQLException e) {
            System.out.println("Problemas al obtener la lista de Departamentos");
            e.printStackTrace();
        }

        return ventas;
    }

    @Override
    public boolean delete(Venta t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean insert(Venta t) throws SQLException {
        boolean result = false;
        Connection connection = Conexion.getConnection();

        PreparedStatement preparedStmt = null;
        try {
            String query = " insert into ventas (valor,id_vendedor,id_caja,dato) values (?,?,?,?)";
            preparedStmt = connection.prepareStatement(query);
            System.out.println(t.getId_venta());
            if (t.getId_venta() != 0) {

                preparedStmt.setDouble(1, t.getValor());
                preparedStmt.setInt(2, t.getVendedor().getId_vendedor());
                preparedStmt.setInt(3, t.getCaja().getId_caja());
                preparedStmt.setString(4,t.getData());
            } else {

                query = " insert into ventas (dato) values (?)";
                preparedStmt = connection.prepareStatement(query);

                preparedStmt.setDate(1, null);
            }
            result = preparedStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean insert1(Venta t) throws SQLException {
        boolean result = false;
        Connection connection = Conexion.getConnection();
        String query = " insert into ventas (valor,id_vendedor,id_caja,dato) values (?,?,?,?)";
        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setDouble(1, t.getValor());
            preparedStmt.setInt(2, t.getVendedor().getId_vendedor());
            preparedStmt.setInt(3, t.getCaja().getId_caja());
            preparedStmt.setDate(4, Date.valueOf(t.getData()));

            result = preparedStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean update(Venta t) throws SQLException {
        boolean result = false;
        Connection connection = Conexion.getConnection();
        String query
                = "update ventas set  valor = ?,id_vendedor=?,id_caja=?,dato= ? where id_ventas = ?";
        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setDouble(1, t.getValor());
            preparedStmt.setInt(2, t.getVendedor().getId_vendedor());
            preparedStmt.setInt(3, t.getCaja().getId_caja());
            preparedStmt.setString(4,t.getData());
            preparedStmt.setInt(5, t.getId_venta());

            if (preparedStmt.executeUpdate() > 0) {
                result = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public Venta buscarUltimaVenda() throws SQLException {
        String query = "SELECT max(id_ventas) FROM ventas";
        System.out.println(query);
        Venta retorno = new Venta();
        Connection connection = Conexion.getConnection();

        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            ResultSetMetaData rsmd = rs.getMetaData();
            if (rs.next()) {

                System.out.println(rsmd.getColumnName(1).toString());
                System.out.println(rs.getInt(rsmd.getColumnName(1)));
                int a = rs.getInt(rsmd.getColumnName(1));
                retorno.setId_venta(a);
                return retorno;
            }
        } catch (SQLException ex) {
            Logger.getLogger(VentaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;

    }
}
