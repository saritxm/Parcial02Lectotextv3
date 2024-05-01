package Servidor.Model;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class ServidorConexion {
    private ServerSocket ServerSocketServidor;
    private ServerSocket ServerSocketServidor2;
    private Socket socketClienteIn;
    private Socket socketClienteOut;
    private DataInputStream dataInputStreamCliente;
    private DataOutputStream dataOutputStreamCliente;
    
    private int puerto1;
    private int puerto2;
    private boolean activo = true;
    private String mensaje;

    public ServidorConexion(int puerto1) {
        this.puerto1 = puerto1;
        this.puerto2 = puerto1+1;
    }

    public void conectar()throws IOException{
        ServerSocketServidor = new ServerSocket(puerto1);
        ServerSocketServidor2 = new ServerSocket(puerto2);
        socketClienteIn = ServerSocketServidor.accept();
        socketClienteOut = ServerSocketServidor2.accept();
        dataInputStreamCliente = new DataInputStream(socketClienteIn.getInputStream());
        dataOutputStreamCliente = new DataOutputStream(socketClienteOut.getOutputStream());
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
    public ServerSocket getServerSocket(){
        return this.ServerSocketServidor;
    }
    public ServerSocket getServerSocket2(){
        return this.ServerSocketServidor2;
    }

    public DataOutputStream getDataOutputStreamCliente() {
        return dataOutputStreamCliente;
    }

    public void setDataOutputStreamCliente(DataOutputStream dataOutputStreamCliente) {
        this.dataOutputStreamCliente = dataOutputStreamCliente;
    }
}
