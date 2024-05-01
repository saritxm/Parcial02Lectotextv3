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
/**
 * Clase que maneja las operaciones de acceso a la base de datos.
 */
public class DatabaseDAO {

    private Connection con; // Objeto de conexión a la base de datos
    private Statement st;
    private ResultSet rs;
    private PreparedStatement ps;

    // Constructor
    public DatabaseDAO() {
        // Inicialización de la los objetos como nulos
        con = null;
        st = null;
        rs = null;
    }

    /**
     * Método para agregar un nuevo usuario a la base de datos.
     * 
     * @param username Nombre de usuario.
     * @param password Contraseña del usuario.
     */
    public void agregarUsuario(String username, String password) throws SQLException {
        String insert = "INSERT INTO users VALUES (?,?)";
        // Establece la conexión con la base de datos
        con = (Connection) DatabaseConexion.getConexion();
        // Prepara la sentencia SQL
        ps = con.prepareStatement(insert);
        // Establece los parámetros de la sentencia SQL
        ps.setString(1, username);
        ps.setString(2, password);
        // Ejecuta la sentencia SQL para insertar el usuario en la base de datos
        ps.executeUpdate();
        // Cierra la sentencia preparada y desconecta de la base de datos
        ps.close();
        DatabaseConexion.desconectar();
    }

    /**
     * Método para iniciar sesión en la aplicación.
     * 
     * @param username Nombre de usuario.
     * @param password Contraseña del usuario.
     * @return 1 si la sesión se inició correctamente, 2 si la contraseña es
     *         incorrecta, 0 si el usuario no existe.
     */
    public int inicioSesion(String username, String password) throws SQLException {
        String insert = "SELECT * FROM users WHERE username='" + username + "'";
        int result = 0;
        // Establece la conexión con la base de datos
        con = (Connection) DatabaseConexion.getConexion();
        // Crea un objeto de declaración para ejecutar la consulta
        st = con.createStatement();
        // Ejecuta la consulta para verificar si el usuario existe en la base de datos
        rs = st.executeQuery(insert);
        if (rs.next()) { // Si el usuario existe
            st.close();
            // Verifica la contraseña del usuario
            insert = "SELECT * FROM users WHERE username='" + username + "' AND password='" + password + "'";
            st = con.createStatement();
            rs = st.executeQuery(insert);
            // Inicio de sesion existoso
            // Si se encuentra el usuario y la contraseña coincide
            if (rs.next()) {
                result = 1;
            }
            // Constraseña incorrecta
            // Si se encuentra el usuario pero la contraseña no coincide
            else {
                result = 2;
            }

        }
        // El usuario no existe
        else {
            result = 0;
        }
        // Cierra los objetos ResultSet y Statement y desconecta de la base de datos
        rs.close();
        DatabaseConexion.desconectar();
        return result;
    }
}
