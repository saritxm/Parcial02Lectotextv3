/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servidor2.model.Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
/**
 *
 * @author Sara
 */
public class DatabaseDAO {
    
    private Connection con;
    private Statement st;
    private ResultSet rs;
    private PreparedStatement ps;
    
    public DatabaseDAO() {
        con = null;
        st = null;
        rs = null;
}
    public void agregarUsuario(String username, String password) throws SQLException{
        String insert = "INSERT INTO users VALUES (?,?)";

        con = (Connection) DatabaseConexion.getConexion();
        ps = con.prepareStatement(insert);

        ps.setString(1, username);
        ps.setString(2, password);

        ps.executeUpdate();

        ps.close();
        DatabaseConexion.desconectar();
    }

    public int inicioSesion(String username, String password) throws SQLException{
        String insert = "SELECT * FROM users WHERE username='"+username+"'";
        int result = 0;

        con = (Connection) DatabaseConexion.getConexion(); 
        st = con.createStatement();
        rs = st.executeQuery(insert);
        if(rs.next()){
            st.close();

            insert = "SELECT * FROM users WHERE username='"+username+"' AND password='"+password+"'";
            st = con.createStatement();
            rs = st.executeQuery(insert);
            //Inicio de sesion existoso
            if(rs.next()){
                result = 1;
            }
            //Constraseña incorrecta
            else{
                result =  2;
            }
            
        }
        //El usuario no existe
        else{
            result =  0;
        }
        rs.close();
        DatabaseConexion.desconectar();
        return result;
    } 
}