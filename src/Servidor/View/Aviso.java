package Servidor.View;

import javax.swing.JOptionPane;

public class Aviso {
    public void verExcepcionConexion(Exception e) {
        JOptionPane.showMessageDialog(null, "Error: no se pudo conectar al servidor");
    }
    public void verExcepcionEnvio(Exception e) {
        JOptionPane.showMessageDialog(null, "Error: no se pudo recibir cadena");
    }
    public void verMensaje(String mensaje) {
        System.out.println(mensaje);
    }
    public void verExcepcion(Exception e){
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
    }
}
