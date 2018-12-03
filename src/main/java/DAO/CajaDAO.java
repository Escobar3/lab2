/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import VO.Caja;

/**
 *
 * @author LUIS
 */
public class CajaDAO implements IBaseDatos<Caja> {

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public Caja find(int id) throws SQLException {

        Caja resultado = null;
        String query = "Select * from caja Where id_caja =" + id;
        Connection connection = Conexion.getConnection();
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            int id_v = 0;
            double valor = 0.0;
            int id_sucursal = 0;

            if (rs.next()) {
                resultado = new Caja();
                id_v = rs.getInt("id_caja");
                resultado.setId_caja(id_v);
                valor = rs.getDouble("Dinero");
                resultado.setDinero(valor);
                id_sucursal = rs.getInt("id_sucursal");
                resultado.setId_sucursal(id_sucursal);
                resultado = new Caja(id_v, valor);

            }
            st.close();
        } catch (SQLException e) {
            System.out.println("Problemas al obtener persona");
            e.printStackTrace();
        }
        return resultado;
    }

    @Override
    public List<Caja> findAll() throws SQLException {
        List<Caja> cajas = null;
        String query = "Select * from caja ";

        Connection connection = Conexion.getConnection();
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            int id_v = 0;
            double valor = 0.0;
            int id_sucursal = 0;
            while (rs.next()) {
                if (cajas == null) {
                    cajas = new ArrayList<Caja>();
                }

                Caja resultado = new Caja();
                id_v = rs.getInt("id_caja");
                resultado.setId_caja(id_v);
                valor = rs.getDouble("valor");
                resultado.setDinero(valor);
                id_sucursal = rs.getInt("id_sucursal");
                resultado.setId_sucursal(id_sucursal);
                cajas.add(resultado);
            }
            st.close();

        } catch (SQLException e) {
            System.out.println("Problemas al obtener la lista de Departamentos");
            e.printStackTrace();
        }

        return cajas;
    }

    @Override
    public boolean insert(Caja t) throws SQLException {
        boolean result = false;
        Connection connection = Conexion.getConnection();
        String query = " insert into caja (Dinero,id_sucursal) values (?,?)";
        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setDouble(1, t.getDinero());
            preparedStmt.setInt(2, 1);

            result = preparedStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean update(Caja t) throws SQLException {
        boolean result = false;
        Connection connection = Conexion.getConnection();
        String query = "update Caja set  Dinero = ?,id_sucursal=? where id_caja = ?";
        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setDouble(1, t.getDinero());
            preparedStmt.setInt(2, t.getId_sucursal());
            preparedStmt.setInt(3, t.getId_caja());

            if (preparedStmt.executeUpdate() > 0) {
                result = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean delete(Caja t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
