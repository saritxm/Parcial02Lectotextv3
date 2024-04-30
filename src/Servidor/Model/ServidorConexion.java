package Servidor.Model;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class ServidorConexion {
    private ServerSocket ServerSocketServidor;
    private Socket socketClienteIn;
    private DataInputStream dataInputStreamCliente;
    private int puerto1;
    private boolean activo = true;
    private String mensaje;

    public ServidorConexion(int puerto1) {
        this.puerto1 = puerto1;
    }

    public void conectar()throws IOException{
        ServerSocketServidor = new ServerSocket(puerto1);
        socketClienteIn = ServerSocketServidor.accept();
        dataInputStreamCliente = new DataInputStream(socketClienteIn.getInputStream());
    }
    public void recibirCadenas()throws IOException{
        mensaje = dataInputStreamCliente.readUTF(); 
    }

    public void cerrarSockets()throws IOException{ 
        dataInputStreamCliente.close();
        socketClienteIn.close();
        ServerSocketServidor.close();
    }
    public void setActivo(boolean activo){
        this.activo = activo;
    }
    public boolean getActivo(){
        return this.activo;
    }
    public String getMensaje(){
        return this.mensaje;
    }
}
