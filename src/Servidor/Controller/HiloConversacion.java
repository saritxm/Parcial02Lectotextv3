package Servidor.Controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import Servidor.Model.Database.DatabaseDAO;

public class HiloConversacion extends Thread {

    private Socket socketCliente;
    private Socket socketCliente2;
    private ServidorControl control;
    private DatabaseDAO BD;
    private DataInputStream inputStream = null; 
    private DataOutputStream outputStream = null; 

    public HiloConversacion(Socket socketCliente,Socket socketCliente2, ServidorControl control ){
        
        this.BD = new DatabaseDAO();
        this.socketCliente = socketCliente;
        this.socketCliente2 = socketCliente2;
        this.control = control;
    }

    public void run() {
        try {
            inputStream = new DataInputStream(socketCliente.getInputStream()); 
            outputStream = new DataOutputStream(socketCliente2.getOutputStream());
        } catch (Exception e) {
            
        }
        try {
            while (true) {
                int op = inputStream.readInt();
                System.out.println(op);
                switch (op) {
                    case 1:
                        String mensaje = inputStream.readUTF();
                        if(mensaje != null){
                            control.getAviso().verMensaje(mensaje);
                        }
                        break;
                
                    case 2:
                        String user = inputStream.readUTF();
                        String pass = inputStream.readUTF();
                        try {
                            outputStream.writeInt(1);
                            outputStream.writeInt(BD.inicioSesion(user, pass));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case 3:
                        String userC = inputStream.readUTF();
                        String passC = inputStream.readUTF();
                        try {
                            BD.agregarUsuario(userC, passC);
                        } catch (Exception e) {
                            // TODO: handle exception
                        }
                        break;
                }
                
                
            }
        } catch (IOException e) {
            control.getAviso().verMensaje("Error al leer el mensaje del cliente: " + e.getMessage());
        }
    }

    
}
