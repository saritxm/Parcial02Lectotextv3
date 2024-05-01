package Servidor.Controller;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class HiloConversacion extends Thread {

    private Socket socketCliente;
    private ServidorControl control;

    public HiloConversacion(Socket socketCliente, ServidorControl control ){
        this.socketCliente = socketCliente;
        this.control = control;
    }

    public void run() {
        try {
            DataInputStream inputStream = new DataInputStream(socketCliente.getInputStream()); 
            while (control.getConexion().getActivo()) {
                String mensaje = inputStream.readUTF();
                if(mensaje != null){
                    control.getAviso().verMensaje(mensaje);
                }
            }
        } catch (IOException e) {
            control.getAviso().verMensaje("Error al leer el mensaje del cliente: " + e.getMessage());
        }
    }
}
