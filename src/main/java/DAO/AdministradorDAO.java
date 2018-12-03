/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import VO.Administrador;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LUIS
 */
public class AdministradorDAO implements IBaseDatos<Administrador> {

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public Administrador find(String usuario) throws SQLException, URISyntaxException {
        Administrador resultado = null;
        System.out.println(usuario);
       
        String query = "Select * from Administrador Where usuario = '"+usuario+"'";
        
        System.out.println(query);
        System.out.println("----");
        Connection connection = Conexion.getConnection();
        try {
            System.out.println("entro");
            Statement st = connection.createStatement();

            ResultSet rs = st.executeQuery(query);           
            int id = 0;
            String nombre = null, apellido = null;
            String user = null, clave = null;
            if (rs.next()) {
               
                
                id = rs.getInt("id_Adm");
                
                nombre = rs.getString("nombre");
                apellido = rs.getString("apellido");
                user = rs.getString("usuario");
                
                clave = rs.getString("clave");
         
                resultado = new Administrador(id,user,clave,apellido,nombre);
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
        List<Administrador> administradors = null;
        String query = "SELECT * FROM Administrador";
        Connection connection = null;
        try {
            connection = Conexion.getConnection();
        } catch (URISyntaxException ex) {
            Logger.getLogger(AdministradorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            int id = 0;
            String nombre = null, apellido = null;
            String user = null, clave = null;
            while (rs.next()) {
                if (administradors == null) {
                    administradors = new ArrayList<Administrador>();
                }

                Administrador registro = new Administrador();
                id = rs.getInt("id_Adm");
                registro.setId_adm(id);
                nombre = rs.getString("nombre");
                registro.setNombre(nombre);
                apellido = rs.getString("apellido");
                registro.setApellido(apellido);
                user = rs.getString("usuario");
                registro.setApellido(user);
                clave = rs.getString("clave");
                registro.setApellido(clave);
                administradors.add(registro);
            }
            st.close();

        } catch (SQLException e) {
            System.out.println("Problemas al obtener la lista de Departamentos");
            e.printStackTrace();
        }

        return administradors;
    }

    @Override
    public boolean insert(Administrador t) throws SQLException {
        boolean result = false;
        Connection connection = null;
        try {
            connection = Conexion.getConnection();
        } catch (URISyntaxException ex) {
            Logger.getLogger(AdministradorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        String query = " insert into administrador (id_Adm,usuario,clave,nombre,apellido) values (?,?,?,?,?)";
        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(1, t.getId_adm());
            preparedStmt.setString(2, t.getUsuario());
            preparedStmt.setString(3, t.getClave());
            preparedStmt.setString(4, t.getApellido());
            preparedStmt.setString(5, t.getNombre());
            result = preparedStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean update(Administrador t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Administrador t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
