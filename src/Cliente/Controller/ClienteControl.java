package Cliente.Controller;

import java.util.Properties;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.UnknownHostException;

import Cliente.Model.ClienteConexion;
import Cliente.View.Aviso;
import Cliente.View.FileChooser;
import Cliente.View.VentanaPrincipal;

public class ClienteControl implements ActionListener{
    private ClienteConexion cliente;
    private VentanaPrincipal ventanaPrincipal;
    private Properties properties;
    private int p1;
    private String mensaje;
    private FileChooser fileChooser;
    private Aviso aviso;
    
    public ClienteControl(){
        this.properties = new Properties();
        this.fileChooser = new FileChooser();
        this.aviso = new Aviso();
        cargarProperties();
        try {
            this.cliente = new ClienteConexion(p1);
        } catch (UnknownHostException e) {
            aviso.verExcepcionHost(e);
        } catch (Exception e) {
            aviso.verExcepcionConexion(e);
        }
        this.ventanaPrincipal = new VentanaPrincipal();
        ventanaPrincipal.getBtnLeer().addActionListener(this);
        ventanaPrincipal.getBtnSalir().addActionListener(this);
    }
    public void cargarProperties(){
        try{
            this.properties.load(this.fileChooser.getProperties());
            this.p1 = Integer.parseInt(this.properties.getProperty("port.1"));
        }catch(Exception e){
            aviso.verExcepcionProperties(e);
        }
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
