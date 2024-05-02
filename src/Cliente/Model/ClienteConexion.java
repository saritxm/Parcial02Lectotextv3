package Cliente.Model;

import java.io.*;
import java.net.*;
import java.util.function.Consumer;

/**
 *
 * @author Administrador
 */
/**
 * Clase para establecer la conexión y manejar la comunicación del cliente.
 */
public class ClienteConexion {
   public static String IP_SERVER = "localhost"; // Dirección IP del servidor
   // Flujos de entrada y salida para la comunicación
   public DataInputStream entrada = null;
   DataOutputStream salida = null;
   public DataInputStream entrada2 = null;
   // Sockets de comunicación
   Socket comunication = null;
   Socket comunication2 = null;
   // Nombre del cliente
   String nomCliente;

   private Consumer<String> avisos; // Mostrar mensajes al usuario
   // Puertos de conexión
   private int p1;
   private int p2;

   /**
    * Constructor de la clase ClienteConexion.
    * 
    * @param avisos Función para mostrar mensajes al usuario.
    * @param p1     Puerto 1 para la conexión.
    * @param p2     Puerto 2 para la conexión.
    * @throws IOException Excepción de E/S.
    */
   public ClienteConexion(Consumer<String> avisos, int p1, int p2) throws IOException {
      this.p1 = p1;
      this.p2 = p2;
      this.avisos = avisos;
   }

   /**
    * Método para establecer la conexión con el servidor.
    */

   public void conexion() throws IOException {
      try {
         // Establecer conexión a través de los puertos especificados
         comunication = new Socket(ClienteConexion.IP_SERVER, p1);
         comunication2 = new Socket(ClienteConexion.IP_SERVER, p2);
         // Inicializar flujos de entrada y salida
         entrada = new DataInputStream(comunication.getInputStream());
         salida = new DataOutputStream(comunication.getOutputStream());
         entrada2 = new DataInputStream(comunication2.getInputStream());
         salida.writeUTF(nomCliente); // Enviar nombre de cliente al servidor
      } catch (IOException e) {
         // Manejar excepción si el servidor no está levantado
         avisos.accept("\tEl servidor no esta levantado");
         avisos.accept("\t=============================");
      }
   }

   /**
    * Método para iniciar sesión en el servidor.
    * 
    * @param user     Nombre de usuario.
    * @param password Contraseña del usuario.
    * @return Resultado de la autenticación.
    * @throws IOException Excepción de E/S.
    */
   public int iniciarS(String user, String password) throws IOException {
      salida.writeInt(1); // Enviar operación de inicio de sesión al servidor
      salida.writeUTF(user);
      salida.writeUTF(password);
      return entrada.readInt(); // Retornar resultado de la autenticación
   }

   /**
    * Método para crear una cuenta en el servidor.
    * 
    * @param user     Nombre de usuario.
    * @param password Contraseña del usuario.
    * @return Resultado de la operación.
    * @throws IOException Excepción de E/S.
    */
   public boolean crearCuenta(String user, String password) throws IOException {
      salida.writeInt(2);
      salida.writeUTF(user);
      salida.writeUTF(password);
      return entrada.readBoolean();
   }
    /**
     * Método para cerrar los sockets y flujos de comunicación.
     * @param b Booleano que indica si se cierran los sockets.
     * @throws IOException Excepción de E/S.
     */
   public void cerrarSockets(boolean b) throws IOException {
      comunication.close(); // Cerrar socket de comunicación 1
      comunication2.close(); // Cerrar socket de comunicación 2
      entrada.close(); // Cerrar flujo de entrada
      entrada2.close();// Cerrar flujo de entrada 2
      salida.close();// Cerrar flujo de salida
   }
    /**
     * Método para enviar cadenas de texto al servidor.
     * @param mensaje Mensaje a enviar.
     * @param idioma Idioma del mensaje.
     * @throws IOException Excepción de E/S.
     */
   public void enviarCadenas(String mensaje, String idioma) throws IOException {
      salida.writeInt(3); // Enviar operación de envío de mensaje al servidor
      salida.writeUTF(mensaje);
      salida.writeUTF(idioma);
   }

}