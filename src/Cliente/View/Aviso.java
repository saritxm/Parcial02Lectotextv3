package Cliente.View;

import java.net.UnknownHostException;

import javax.swing.JOptionPane;

public class Aviso {
    public void verExcepcionFlujos(Exception e) {
        JOptionPane.showMessageDialog(null, "Error: no se pudo crear los flujos"+e.getMessage());
    }
    public void verExcepcionHost(UnknownHostException e) {
        JOptionPane.showMessageDialog(null, "Error: no se encontro el servidor");
    }
    public void verMensaje(String mensaje) {
        System.out.println(mensaje);
    }
    public void verExcepcionCerrar(Exception e){
        JOptionPane.showMessageDialog(null, "Error: no se pudieron cerrar los flujos");
    }
    public void verExcepcionProperties(Exception e){
        JOptionPane.showMessageDialog(null, "Error: no se pudieron cargar los properties ");
    }
    public void verExcepcionConexion(Exception e) {
        JOptionPane.showMessageDialog(null, "Error: no se pudo conectar al servidor");
    }
}
