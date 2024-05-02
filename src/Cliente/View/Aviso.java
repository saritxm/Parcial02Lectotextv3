package Cliente.View;

import java.net.UnknownHostException;

import javax.swing.JOptionPane;

public class Aviso {
    /**
     * Método para mostrar un cuadro de diálogo con un mensaje de error sobre la
     * creación de flujos
     */
    public void verExcepcionFlujos(Exception e) {
        JOptionPane.showMessageDialog(null, "Error: no se pudo crear los flujos" + e.getMessage());
    }

    /**
     * Método para mostrar un cuadro de diálogo con un mensaje de error sobre la
     * conexión al servidor
     */
    public void verExcepcionHost(UnknownHostException e) {
        JOptionPane.showMessageDialog(null, "Error: no se encontro el servidor");
    }

    /**
     * Método para mostrar un cuadro de diálogo con un mensaje de confirmación u
     * otra información
     */
    public void verMensajeOP(String x) {
        JOptionPane.showMessageDialog(null, x);
    }

    /**
     * Método para imprimir un mensaje en la consola
     */
    public void verMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    /**
     * Método para mostrar un cuadro de diálogo con un mensaje de error sobre el
     * cierre de flujos
     */
    public void verExcepcionCerrar(Exception e) {
        JOptionPane.showMessageDialog(null, "Error: no se pudieron cerrar los flujos");
    }

    /**
     * Método para mostrar un cuadro de diálogo con un mensaje de error sobre la
     * carga de propiedades
     */
    public void verExcepcionProperties(Exception e) {
        JOptionPane.showMessageDialog(null, "Error: no se pudieron cargar los properties ");
    }

    /**
     * Método para mostrar un cuadro de diálogo con un mensaje de error sobre la
     * conexión al servidor
     */
    public void verExcepcionConexion(Exception e) {
        JOptionPane.showMessageDialog(null, "Error: no se pudo conectar al servidor");
    }

    /**
     * Método para solicitar al usuario la dirección IP del servidor mediante un
     * cuadro de diálogo
     */
    public String ip() {
        // Muestra un cuadro de diálogo para que el usuario introduzca la dirección IP
        // del servidor
        // El valor por defecto es "localhost"
        return JOptionPane.showInputDialog("Introducir IP_SERVER :", "localhost");
    }

    /**
     * Método para imprimir un mensaje en la consola
     */
    public void consola(String x) {
        System.out.println(x);
    }
}
