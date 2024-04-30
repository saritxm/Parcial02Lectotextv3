package Cliente.Model;

import java.net.*;
import java.io.*;

public class ClienteConexion {

    private ServerSocket fromServer;
    private DataOutputStream dataOutputStreamCliente;
    private Socket socketClienteOut, socketClienteIn;
    private DataInputStream dataInputStreamCliente;
    private int puerto;
    private static String IP_SERVER;

    public ClienteConexion(int puerto1) throws IOException, UnknownHostException {
        this.puerto = puerto1;
        socketClienteOut = new Socket(ClienteConexion.IP_SERVER , puerto);
        dataOutputStreamCliente = new DataOutputStream(socketClienteOut.getOutputStream());
    }
    public void cerrarSockets(boolean activo)throws IOException {
        if(activo){
            dataInputStreamCliente.close();
            socketClienteIn.close();
            socketClienteOut.close();
            dataOutputStreamCliente.close();
            fromServer.close();
        }
    }
    public void enviarCadenas(String mensaje) throws IOException {
        dataOutputStreamCliente.writeUTF(mensaje);   
    }
}