package Cliente.Model;

import java.net.*;

import javax.imageio.IIOException;

import java.io.*;

public class ClienteConexion {

    private ServerSocket fromServer;
    private DataOutputStream dataOutputStreamCliente;
    private DataInputStream dataInputStreamCliente;
    private Socket socketClienteOut;
    private Socket socketClienteIn;
    private int puerto;
    private static String IP_SERVER= "localhost";


    public ClienteConexion(int puerto1)  {
        try {
            this.puerto = puerto1;
            socketClienteOut = new Socket(ClienteConexion.IP_SERVER , puerto);
            socketClienteIn = new Socket(ClienteConexion.IP_SERVER , puerto+1);
            dataOutputStreamCliente = new DataOutputStream(socketClienteOut.getOutputStream());
            dataInputStreamCliente = new DataInputStream(socketClienteIn.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    public void cerrarSockets(boolean activo)throws IOException {
        if(activo){
            socketClienteOut.close();
            dataOutputStreamCliente.close();
            fromServer.close();
        }
    }
    public void enviarCadenas(String mensaje) throws IOException {
        dataOutputStreamCliente.writeInt(1);
        dataOutputStreamCliente.writeUTF(mensaje);   
    }
    public int iniciarS(String user, String pass) throws IOException{
        dataOutputStreamCliente.writeInt(2);
        dataOutputStreamCliente.writeUTF(user);
        dataOutputStreamCliente.writeUTF(pass);
        return  dataInputStreamCliente.readInt();
    }

}
