package Cliente.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import Cliente.Model.ClienteConexion;
import Cliente.View.Aviso;
import Cliente.View.VentanaPrincipal;

public class HiloCliente extends Thread implements ActionListener{

    private VentanaPrincipal ventanaPrincipal;
    private String mensaje;
    private ClienteConexion cliente;
    private Aviso aviso;

    public HiloCliente(ClienteConexion con) {
        this.cliente = con;
        this.aviso = new Aviso();
        this.ventanaPrincipal = new VentanaPrincipal();

        ventanaPrincipal.getBtnLeer().addActionListener(this);
        ventanaPrincipal.getBtnSalir().addActionListener(this);
        start();
    }

    public void run(){
        this.ventanaPrincipal.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == ventanaPrincipal.getBtnLeer()) {
            mensaje = ventanaPrincipal.getTextArea().getText();
            try {
                cliente.enviarCadenas(mensaje);
            } catch (Exception ex) {
                aviso.verExcepcionFlujos(ex);
            }
            ventanaPrincipal.getTextArea().setText("");
        }
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
    }

}