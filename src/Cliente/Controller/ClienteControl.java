package Cliente.Controller;

import java.util.Properties;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.UnknownHostException;

import Cliente.Model.ClienteConexion;
import Cliente.View.Aviso;
import Cliente.View.CrearCuenta;
import Cliente.View.FileChooser;
import Cliente.View.Login;
import Cliente.View.VentanaPrincipal;

public class ClienteControl implements ActionListener{
    private ClienteConexion cliente;
    private VentanaPrincipal ventanaPrincipal;
    private Properties properties;
    private int p1;
    private String mensaje;
    private FileChooser fileChooser;
    private Aviso aviso;
    private Login inicioSesion;
    private CrearCuenta cr;

    
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
        
        inicio();
    }

    private void inicio(){
        inicioSesion = new Login();
        inicioSesion.loginBtn.addActionListener(this);
        inicioSesion.crearCuentabtn.addActionListener(this);
        inicioSesion.setVisible(true);
    }

    public void cargarProperties(){
        try{
            this.properties.load(this.fileChooser.getProperties());
            this.p1 = Integer.parseInt(this.properties.getProperty("port.1"));
        }catch(Exception e){
            aviso.verExcepcionProperties(e);
        }
    }

    /**
     * Verificar cliente y crear hilo
     */
    private void iniciarCliente(){
        String user = inicioSesion.user.getText();
        String password = inicioSesion.password.getText();
        inicioSesion.dispose();
        new ClienteCControl(this);
    }

    private void crearCuenta(){
        String user = cr.user.getText();
        String p1  = cr.password.getText();
        String p2  = cr.password.getText();
    }

    public void actionPerformed(ActionEvent e){
        //Inicio de seson
        if(e.getSource()==inicioSesion.loginBtn){
            iniciarCliente();
            //System.out.println("xd");
        }
        //Crear cuenta
        else if(e.getSource()==inicioSesion.crearCuentabtn){
          inicioSesion.setVisible(false);
          cr = new CrearCuenta();
          cr.crearCuentabtn.addActionListener(this);
          cr.setVisible(true);
        }
        else if(e.getSource()==cr.crearCuentabtn){
            crearCuenta();
        }
    }
    public ClienteConexion getCliente(){
        return this.cliente;
    }
}
