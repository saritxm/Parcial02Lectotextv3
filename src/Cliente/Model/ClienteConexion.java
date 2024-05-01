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
   public DataInputStream entrada = null; // Flujo de entrada para recibir datos
   DataOutputStream salida = null; // Flujo de salida para enviar datos
   public DataInputStream entrada2 = null; // Segundo flujo de entrada para recibir mensajes

   Socket comunication = null;// para la comunicacion
   Socket comunication2 = null;// para recivir msg

   String nomCliente; // Nombre del cliente

   private Consumer<String> avisos; // Mostrar mensajes al usuario
   private int p1;
   private int p2;

   /** Creates a new instance of Cliente */
   // Constructor de la clase
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
         comunication = new Socket(ClienteConexion.IP_SERVER, p1); // Establece la conexión en el puerto 8081
         comunication2 = new Socket(ClienteConexion.IP_SERVER, p2); // Establece la conexión en el puerto 8082
         entrada = new DataInputStream(comunication.getInputStream()); // Obtiene el flujo de entrada del primer socket
         salida = new DataOutputStream(comunication.getOutputStream());// Obtiene el flujo de salida del primer socket
         entrada2 = new DataInputStream(comunication2.getInputStream());
         salida.writeUTF(nomCliente); // Envía el nombre del cliente al servidor
      } catch (IOException e) {
         avisos.accept("\tEl servidor no esta levantado"); // Avisa al usuario si el servidor no está disponible
         avisos.accept("\t=============================");
      }
   }

   public int iniciarS(String user, String password) throws IOException {
      salida.writeInt(1);
      salida.writeUTF(user);
      salida.writeUTF(password);
      return entrada.readInt();
   }
   public boolean crearCuenta(String user, String password) throws IOException {
      salida.writeInt(2);
      salida.writeUTF(user);
      salida.writeUTF(password);
      return entrada.readBoolean();
   }

   public void cerrarSockets(boolean b) throws IOException {
      comunication.close();
      comunication2.close();
      entrada.close();
      entrada2.close();
      salida.close();
   }

   public void enviarCadenas(String mensaje, String idioma) throws IOException {
      salida.writeInt(3);
      salida.writeUTF(mensaje);
      salida.writeUTF(idioma);
   }

}