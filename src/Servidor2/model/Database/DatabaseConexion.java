/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servidor2.model.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Sara
 */
/**
 * Clase encargada de gestionar la conexión a la base de datos.
 */
public class DatabaseConexion {
    private static Connection cn = null;// Objeto para establecer la conexión con la base de datos
    private static String URLBD = "jdbc:mysql://158.247.126.144:3306/users"; // URL de la base de datos
    private static String usuario = "user";// Nombre de usuario de la base de datos
    private static String contrasena = "12345678";// Contraseña de la base de datos

    /**
     * Método estático para obtener una conexión a la base de datos
     */
    public static Connection getConexion() {
        try {
            // Establecer la conexión con la base de datos utilizando los parámetros de
            // conexión
            cn = DriverManager.getConnection(URLBD, usuario, contrasena);
        } catch (SQLException ex) {

        }
        return cn; // Devuelve la conexión establecida o null si ocurrió un error
    }

    public static void desconectar() {
        cn = null; // Establece el objeto Connection como null para cerrar la conexión
    }
}
