/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servidor.Model.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Sara
 */
public class DatabaseConexion {


    private static Connection cn = null; 
    private static String URLBD = "jdbc:mysql://localhost:3306/users";
    private static String usuario = "root"; 
    private static String contrasena = "";
    
    public static Connection getConexion() {
        try {
            cn = DriverManager.getConnection(URLBD, usuario, contrasena);
        } catch (SQLException ex) {
            
        }
        return cn;
    }

    public static void desconectar() {
        cn = null;
    }
}

   
