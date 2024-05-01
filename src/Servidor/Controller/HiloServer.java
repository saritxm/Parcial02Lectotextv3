package Servidor.Controller;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class  HiloServer implements Runnable {
    private Socket socketCliente;
    private ServerSocket server;
    private ServidorControl control;

    public HiloServer(ServerSocket serverSocket, ServidorControl control) {
        try {
            this.server = serverSocket;
            this.control = control;
        } catch (Exception e) {
            control.getAviso().verMensaje("Error al ejecutar el servidor"+e.getMessage());
        }
    }

    public void run() {
        try {
            while (control.getConexion().getActivo()) {
                // Leer mensaje del cliente
                this.socketCliente = server.accept();
                HiloConversacion conversacion = new HiloConversacion(socketCliente, control);
                conversacion.start();
            }
        } catch (IOException e) {
            control.getAviso().verMensaje("Error al aceptar la conexion" + e.getMessage());
        }
    }
}
