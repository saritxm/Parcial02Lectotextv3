package Cliente.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Cliente.Model.ClienteConexion;
import Cliente.View.Aviso;
import Cliente.View.VentanaPrincipal;

public class HiloCliente extends Thread implements ActionListener {
    // Componentes de la interfaz y conexión del cliente
    private VentanaPrincipal ventanaPrincipal;
    private String mensaje;
    private ClienteConexion cliente;
    private Aviso aviso;
    private String idioma = "es";

    /**
     * Constructor de la clase HiloCliente.
     * 
     * @param con La conexión del cliente.
     */
    public HiloCliente(ClienteConexion con) {
        // Inicialización de componentes
        this.cliente = con;
        this.aviso = new Aviso();
        this.ventanaPrincipal = new VentanaPrincipal();
        // Configuración de acciones para los botones de la interfaz
        ventanaPrincipal.getBtnLeer().addActionListener(this);
        ventanaPrincipal.getBtnSalir().addActionListener(this);
        ventanaPrincipal.btnNuevoCliente.addActionListener(this);
        ventanaPrincipal.bEspañol.addActionListener(this);
        ventanaPrincipal.bEspañol.setSelected(true);
        ventanaPrincipal.bIngles.addActionListener(this);
        // Inicio del hilo
        start();
    }
    /**
     * Método principal del hilo.
     */
    public void run() {
        this.ventanaPrincipal.setVisible(true);
    }
    /**
     * Maneja las acciones de los botones de la interfaz.
     */
    public void actionPerformed(ActionEvent e) {
        // Cambio de idioma
        if (e.getSource() == ventanaPrincipal.bEspañol || e.getSource() == ventanaPrincipal.bIngles) {
            if (ventanaPrincipal.bEspañol.isSelected()) {
                idioma = "Es";
            } else if (ventanaPrincipal.bIngles.isSelected()) {
                idioma = "En";
            }
        }
        // Enviar mensaje al servidor
        if (e.getSource() == ventanaPrincipal.getBtnLeer()) {
            mensaje = ventanaPrincipal.getTextArea().getText();
            try {
                cliente.enviarCadenas(mensaje, idioma);
            } catch (Exception ex) {
                aviso.verExcepcionFlujos(ex);
            }
            ventanaPrincipal.getTextArea().setText("");
        }
        // Salir de la aplicación
        if (e.getSource() == ventanaPrincipal.getBtnSalir()) {
            try {
                cliente.cerrarSockets(true);
                ventanaPrincipal.dispose();
                interrupt();
            } catch (Exception ex) {
                aviso.verExcepcionCerrar(ex);
            }
            ventanaPrincipal.dispose();
            aviso.verMensaje("Desconectado");
        }
        // Abrir una nueva ventana de inicio de sesión
        if (e.getSource() == ventanaPrincipal.btnNuevoCliente) {
            new ClienteControl();
        }

    }

}