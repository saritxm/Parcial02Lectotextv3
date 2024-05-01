package Servidor2.Controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Vector;
import java.util.Locale;
import javax.speech.Central;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;

import Servidor2.model.Database.DatabaseDAO;

public class threadServidor extends Thread {
   Socket scli = null; // Socket para la comunicación con el cliente
   Socket scli2 = null; // Socket para enviar mensajes al cliente
   DataInputStream entrada = null; // Flujo de entrada de datos
   DataOutputStream salida = null; // Flujo de salida de datos
   DataOutputStream salida2 = null; // Flujo de salida de datos para el cliente
   // Vector que almacena hilos activos del servidor
   public static Vector<threadServidor> clientesActivos = new Vector<>();
   String nameUser; // Nombre del usuario
   ServidorControl serv; // Referencia al controlador del servidor
<<<<<<< HEAD
   private DatabaseDAO BD; // Objeto para acceder a la base de datos
=======
   private DatabaseDAO BD;
   private String userP = "";
>>>>>>> 3b16bd5ab978801cc298b1e5abaccf2184c27611

   public threadServidor(Socket scliente, Socket scliente2, ServidorControl serv) {
      // Inicialización del objeto de acceso a la base de datos
      this.BD = new DatabaseDAO();
<<<<<<< HEAD
      scli = scliente; // Asignación del socket de comunicación
      scli2 = scliente2; // Asignación del socket para enviar mensajes al cliente
      this.serv = serv; // Asignación del controlador del servidor
      nameUser = "";// Inicialización del nombre del usuario como cadena vacía
      clientesActivos.add(this); // Agrega este hilo a la lista de hilos activos
      // Muestra un mensaje en el servidor indicando que se agregó un cliente
      serv.mostrar("cliente agregado: " + this);
=======
      scli = scliente; // Asigna el socket para mensajes generales
      scli2 = scliente2; // Asigna el socket para mensajes privados
      this.serv = serv;// Asigna la referencia al controlador del servidor
      nameUser = ""; // Inicializa el nombre del usuario
      clientesActivos.add(this);// Agrega este hilo a la lista de clientes activos
      // variable de tipo servidor con mensaje extraido de la vista
      serv.mostrar("Cliente agregado: " + this);
>>>>>>> 3b16bd5ab978801cc298b1e5abaccf2184c27611
   }

   // Métodos getter y setter para el nombre del usuario
   public String getNameUser() {
      return nameUser;
   }

   public void setNameUser(String name) {
      nameUser = name;
   }

   /**
    * Método que se ejecuta cuando el hilo inicia
    */
   public void run() {
      // Muestra un mensaje en la vista del servidor indicando que está esperando
      // mensajes
<<<<<<< HEAD
      serv.mostrar(".::Esperando Mensajes :");
=======
      serv.mostrar(".::Esperando ordenes :");

>>>>>>> 3b16bd5ab978801cc298b1e5abaccf2184c27611
      try {
         // Inicializa los flujos de entrada y salida de datos
         entrada = new DataInputStream(scli.getInputStream());
         salida = new DataOutputStream(scli.getOutputStream());
         salida2 = new DataOutputStream(scli2.getOutputStream());
      } catch (IOException e) {
         // Muestra un mensaje en el servidor si hay un error al establecer los flujos de
         // entrada y salida
         serv.mostrar(e.getMessage());
      }

      int opcion = 0;

      while (true) {
         try {
            // Lee la opción enviada por el cliente
            opcion = entrada.readInt();
            switch (opcion) {
               case 1:
                  // Inicio de sesión
                  String user = entrada.readUTF();
                  String pass = entrada.readUTF();
                  try {
                     // Verifica el inicio de sesión en la base de datos y envía el resultado al
                     // cliente
                     salida.writeInt(BD.inicioSesion(user, pass));
                     if(BD.inicioSesion( user, pass)==1){
                        setUserP(user);
                        serv.mostrar("El usuario "+userP+"tuvo un logeo exitosos");
                     }
                  } catch (Exception e) {
                     // Muestra un mensaje en el servidor si hay un error al iniciar sesión
                     serv.mostrar(e.getMessage());
                  }

                  break;
               case 2:
                  // Registro de usuario
                  String user2 = entrada.readUTF();
                  String pass2 = entrada.readUTF();
                  try {
                     // Agrega un nuevo usuario a la base de datos y envía un mensaje de confirmación
                     // al cliente
                     BD.agregarUsuario(user2, pass2);
                     salida.writeBoolean(true);
                  } catch (Exception e) {
                     // Envía un mensaje de error al cliente si hay un error al agregar usuario
                     salida.writeBoolean(false);
                  }
                  break;
               case 3:
                  // Lectura de texto para reproducción de voz
                  String msg = entrada.readUTF();
                  String lng = entrada.readUTF();
                  // Llama al método para leer y reproducir el texto
                  leerTexto(msg, lng);
                  serv.mostrar("Reproduciendo texto de: " + userP +"\nMensaje: "+msg);

                  break;
            }
         } catch (IOException e) {
<<<<<<< HEAD
            // Muestra un mensaje en el servidor si el cliente termina la conexión
            serv.mostrar("El cliente termino la conexion");
=======
            // Muestra un mensaje en la vista del servidor si el cliente termina la conexión
            serv.mostrar("Nos vemos "+userP);
            leerTexto("Nos vemos "+userP, "Es");
>>>>>>> 3b16bd5ab978801cc298b1e5abaccf2184c27611
            break;
         }
      }
      try {
         // Muestra un mensaje en el servidor cuando se desconecta un usuario
         serv.mostrar("Se desconecto un usuario");
         scli.close(); // Cierra el socket del cliente
      } catch (Exception et) {
         // Muestra un mensaje en el servidor si no se puede cerrar el socket
         serv.mostrar("no se puede cerrar el socket");
      }
   }

<<<<<<< HEAD
   /**
    * Método para leer y reproducir texto usando sintetizador de voz
    */
=======
   public String getUserP() {
      return userP;
   }

   public void setUserP(String userP) {
      this.userP = userP;
   }
>>>>>>> 3b16bd5ab978801cc298b1e5abaccf2184c27611

   public void leerTexto(String texto, String idioma) {
      try {
         // Configura la configuración del sintetizador de voz
         SynthesizerModeDesc desc = new SynthesizerModeDesc();
         // Configurar el idioma
         Locale locale = new Locale(idioma); //Se crea un objeto Locale
         desc.setLocale(locale); //Se establece ese objeto en el modo del sintetizador

         // Obtiene un sintetizador de voz compatible con la configuración
         Synthesizer synthesizer = Central.createSynthesizer(desc);

         // Verifica si se pudo crear el sintetizador de voz
         if (synthesizer == null) {
            serv.mostrar("No se pudo crear el sintetizador de voz");
            return;
         }
         // Asignación y activación del sintetizador de voz
         synthesizer.allocate();
         synthesizer.resume();
         // Se sintetiza el texto utilizando el sintetizador de voz
         synthesizer.speakPlainText(texto, null);
         // Pausa la ejecucion del programa hasta que la cola de reproducción este vacía
         synthesizer.waitEngineState(Synthesizer.QUEUE_EMPTY);
         //Liberación de recursos del sintetizador de voz
         synthesizer.deallocate();

      } catch (Exception e) {
         serv.mostrar(e.getMessage());
      }
   }

}