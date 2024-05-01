package Cliente.Controller;

import java.io.*;
import java.util.function.Consumer;

public class HiloCliente extends Thread {
    Consumer<String> vAvisos;
    DataInputStream entrada; // Flujo de entrada para recibir mensajes del servidor
    ClienteControl principal; // Referencia al controlador principal del cliente

    // Constructor de la clase
    public HiloCliente(DataInputStream entrada,ClienteControl principal, Consumer<String> vAvisos) throws IOException {
        this.entrada = entrada;
        this.principal = principal;
        this.vAvisos = vAvisos;
    }

    /**
     * Método que se ejecuta cuando se inicia el hilo
     */
    public void run() {
        String menser = "", amigo = "";
        int opcion = 0;
        // Bucle para recibir mensajes del servidor
        while (true) {
            try {
                opcion = entrada.readInt(); // Lee la opción del servidor
                switch (opcion) {
                    
                    case 1:
                        
                        
                        break;
                    case 2:// se agrega
                        menser = entrada.readUTF(); // Lee el nombre del usuario del servidor
                        
                        break;
                    case 3:// mensage de amigo
                        menser = entrada.readUTF(); // Lee el nombre del amigo del servidor
                        
                        break;
                    case 4:// Banea al cliente
                        
                        return;
                    case 5://Elimina el usuario baneado
                        
                        break;
                }
            } catch (IOException e) {
                // Muestra un mensaje de error al usuario
                
                break;
            }
        }
         // Muestra un mensaje de que el servidor se desconectó
         
    }

}