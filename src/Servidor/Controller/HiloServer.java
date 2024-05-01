package Servidor.Controller;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.mysql.fabric.Server;

public class  HiloServer implements Runnable {
    private Socket socketCliente;
    private Socket socketCliente2;
    private ServerSocket server;
    private ServerSocket server2;
    private ServidorControl control;

    public HiloServer(ServerSocket serverSocket,ServerSocket serverSocket2, ServidorControl control) {
        try {
            this.server = serverSocket;
            this.server2 = serverSocket2;
            this.control = control;
        } catch (Exception e) {
            control.getAviso().verMensaje("Error al ejecutar el servidor"+e.getMessage());
        }
    }

    public void run() {
        try {
            while (true) {
                // Leer mensaje del cliente
                this.socketCliente = server.accept();
                this.socketCliente2 = server2.accept();
                HiloConversacion conversacion = new HiloConversacion(socketCliente,socketCliente2, control);
                conversacion.start();
            }
        } catch (IOException e) {
            control.getAviso().verMensaje("Error al aceptar la conexion" + e.getMessage());
        }
    }
}
