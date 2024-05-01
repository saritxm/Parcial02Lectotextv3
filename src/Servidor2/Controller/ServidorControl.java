package Servidor2.Controller;

import Servidor2.View.FileSelector;
import Servidor2.View.ServidorVista;
import Servidor2.model.ConexionServidor;
import Servidor2.model.LecturaPuertos;

import java.io.IOException;

public class ServidorControl {

   private FileSelector fc; // Selector de archivos
   private LecturaPuertos pt; // Objeto para leer los puertos
   private ServidorVista vista; // Vista del servidor
   private ConexionServidor con;// Conexión del servidor

   // Constructor de la clase
   public ServidorControl() throws IOException{
      pt = new LecturaPuertos(); // Inicialización del objeto para leer los puertos
      fc = new FileSelector(); // Inicialización del selector de archivos
      fc.fileP2(); // Configura el selector de archivos
      fc.fProp2.showOpenDialog(fc.fProp2); // Abre el diálogo para seleccionar el archivo
      pt.setProp(fc.fProp2.getSelectedFile()); // Establece el archivo de propiedades para los puertos
      try {
         pt.cargarPuertos();// Carga los puertos desde el archivo de propiedades
      } catch (Exception e) {
         fc.error();// Muestra un mensaje de error si ocurre un problema al cargar los puertos
      }
      
       // Inicializa la vista del servidor y la conexión del servidor
      vista = new ServidorVista(); 
      con = new ConexionServidor(vista::mostrar, this::hilo, pt.getP1(), pt.getP2());
       // Inicia el servidor llamando al método runServer() de la conexión del servidor
      con.runServer();
   }

   /**
    * Método para manejar un hilo de conexión del servidor
    */
   public void hilo(Object x) {
      // Crea un nuevo hilo de servidor
      threadServidor user = new threadServidor(con.getSock(), con.getSock2(), this);
      // Inicia el hilo de servidor
      user.start();
   }

   /**
    * Método para mostrar mensajes en la vista del servidor
    */
   public void mostrar(String x) {
      // Llama al método mostrar de la vista del servidor
      vista.mostrar(x);
   }
}