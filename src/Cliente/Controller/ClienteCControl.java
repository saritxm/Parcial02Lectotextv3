package Cliente.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Action;

import Cliente.Model.Cliente;
import Cliente.Model.ClienteConexion;
import Cliente.View.Aviso;
import Cliente.View.VentanaPrincipal;

public class ClienteCControl implements ActionListener{

    private VentanaPrincipal ventanaPrincipal;
    private String mensaje;
    private ClienteConexion cliente;
    private Aviso aviso;
    private ClienteControl principal;

    public ClienteCControl(ClienteControl princial){
        this.cliente = princial.getCliente();
        this.principal = princial;
        this.aviso = new Aviso();
        this.ventanaPrincipal = new VentanaPrincipal();
        ventanaPrincipal.getBtnLeer().addActionListener(this);
        ventanaPrincipal.getBtnSalir().addActionListener(this);
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource() == ventanaPrincipal.getBtnLeer()){
            mensaje = ventanaPrincipal.getTextArea().getText();
            try{
            cliente.enviarCadenas(mensaje);
            }catch(Exception ex){
                aviso.verExcepcionFlujos(ex);
            }
            ventanaPrincipal.getTextArea().setText("");
        }
        if(e.getSource() == ventanaPrincipal.getBtnSalir()){
            try{
            cliente.cerrarSockets(true);
            }catch(Exception ex){
                aviso.verExcepcionCerrar(ex);
            }
            ventanaPrincipal.dispose();
            aviso.verMensaje("Desconectado");
        }
    }
}
