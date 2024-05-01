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
   Socket scli = null; // Socket para la comunicación con el cliente para mensajes generales
   Socket scli2 = null;// Socket para la comunicación con el cliente para enviar mensajes privados
   DataInputStream entrada = null; // Stream de entrada de datos desde el cliente
   DataOutputStream salida = null;// Stream de salida de datos hacia el cliente para mensajes generales
   DataOutputStream salida2 = null; // Stream de salida de datos hacia el cliente para enviar mensajes privados
   // Vector que almacena los hilos activos de los clientes
   public static Vector<threadServidor> clientesActivos = new Vector<>();
   String nameUser;// Nombre del usuario
   ServidorControl serv; // Referencia al controlador del servidor
   private DatabaseDAO BD;
   private String userP = "";

   // Constructor de la clase
   public threadServidor(Socket scliente, Socket scliente2, ServidorControl serv) {
      this.BD = new DatabaseDAO();
      scli = scliente; // Asigna el socket para mensajes generales
      scli2 = scliente2; // Asigna el socket para mensajes privados
      this.serv = serv;// Asigna la referencia al controlador del servidor
      nameUser = ""; // Inicializa el nombre del usuario
      clientesActivos.add(this);// Agrega este hilo a la lista de clientes activos
      // variable de tipo servidor con mensaje extraido de la vista
      serv.mostrar("Cliente agregado: " + this);
   }

   // Getter para obtener el nombre del usuario
   public String getNameUser() {
      return nameUser;
   }

   // Setter para establecer el nombre del usuario
   public void setNameUser(String name) {
      nameUser = name;
   }

   /**
    * Método que se ejecuta cuando el hilo inicia
    */
   public void run() {
      // Muestra un mensaje en la vista del servidor indicando que está esperando
      // mensajes
      serv.mostrar(".::Esperando ordenes :");

      try {
         // Establece los flujos de entrada y salida de datos para mensajes generales
         entrada = new DataInputStream(scli.getInputStream());
         salida = new DataOutputStream(scli.getOutputStream());
         salida2 = new DataOutputStream(scli2.getOutputStream());
      } catch (IOException e) {
         serv.mostrar(e.getMessage());
      }

      int opcion = 0;

      while (true) {
         try {
            // Lee la opción enviada por el cliente
            opcion = entrada.readInt();
            switch (opcion) {
               case 1:// Inicio de sesion
                  String user = entrada.readUTF();
                  String pass = entrada.readUTF();
                  try {
                     salida.writeInt(BD.inicioSesion(user, pass));
                     if(BD.inicioSesion( user, pass)==1){
                        setUserP(user);
                        serv.mostrar("El usuario "+userP+"tuvo un logeo exitosos");
                     }
                  } catch (Exception e) {
                     serv.mostrar(e.getMessage());
                  }

                  break;
               case 2:// Crear cuenta
                  String user2 = entrada.readUTF();
                  String pass2 = entrada.readUTF();
                  try {
                     BD.agregarUsuario(user2, pass2);
                     salida.writeBoolean(true);
                  } catch (Exception e) {
                     salida.writeBoolean(false);
                  }
                  break;
               case 3:// Leer mensaje
                  String msg = entrada.readUTF();
                  String lng = entrada.readUTF();
                  leerTexto(msg, lng);
                  serv.mostrar("Reproduciendo texto de: " + userP +"\nMensaje: "+msg);

                  break;
            }
         } catch (IOException e) {
            // Muestra un mensaje en la vista del servidor si el cliente termina la conexión
            serv.mostrar("Nos vemos "+userP);
            leerTexto("Nos vemos "+userP, "Es");
            break;
         }
      }
      try {
         serv.mostrar("Se desconecto un usuario");
         // Cierra el socket de comunicación con el cliente
         scli.close();
      } catch (Exception et) {
         // Muestra un mensaje en la vista del servidor si no se pudo cerrar el socket
         serv.mostrar("no se puede cerrar el socket");
      }
   }

   public String getUserP() {
      return userP;
   }

   public void setUserP(String userP) {
      this.userP = userP;
   }

   public void leerTexto(String texto, String idioma) {
      try {
         // Configura la configuración del sintetizador de voz
         SynthesizerModeDesc desc = new SynthesizerModeDesc();
         // Configurar el idioma
         Locale locale = new Locale(idioma);
         desc.setLocale(locale);

         // Obtiene un sintetizador de voz compatible con la configuración
         Synthesizer synthesizer = Central.createSynthesizer(desc);

         // Verifica si se pudo crear el sintetizador de voz
         if (synthesizer == null) {
            serv.mostrar("No se pudo crear el sintetizador de voz");
            return;
         }

         synthesizer.allocate();
         synthesizer.resume();

         synthesizer.speakPlainText(texto, null);

         synthesizer.waitEngineState(Synthesizer.QUEUE_EMPTY);
         synthesizer.deallocate();

      } catch (Exception e) {
         serv.mostrar(e.getMessage());
      }
   }

}