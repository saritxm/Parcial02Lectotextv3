package Cliente.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Cliente.Model.ClienteConexion;
import Cliente.Model.LecturaPuertos;
import Cliente.View.Aviso;
import Cliente.View.CrearCuenta;
import Cliente.View.FileChooser;
import Cliente.View.Login;

/**
 * Controlador que gestiona las interacciones entre la vista y el modelo del
 * cliente.
 */
public class ClienteControl implements ActionListener {
    // Componentes de la vista y el modelo
    private Aviso aviso;
    private Login inicioSesion;
    private CrearCuenta cr;

    private FileChooser fc;
    private LecturaPuertos pt;
    public ClienteConexion conexion;

    /**
     * Constructor de la clase ClienteControl.
     * Inicializa los componentes necesarios y establece la conexión con el
     * servidor.
     */
    public ClienteControl() {
        // Configuración de los componentes de lectura de archivos y puertos
        fc = new FileChooser();
        pt = new LecturaPuertos();
        this.aviso = new Aviso();
        // Selección del archivo de propiedades y carga de los puertos
        fc.fileP();
        fc.fProp.showOpenDialog(fc.fProp);
        pt.setProp(fc.fProp.getSelectedFile());
        try {
            pt.cargarPuertos();
        } catch (Exception e) {
            fc.error();
        }
        aviso = new Aviso(); // Crea una instancia del componente de avisos
        ClienteConexion.IP_SERVER = aviso.ip(); // Obtiene la dirección IP del servidor desde el usuario
        // Inicio de la conexión con el servidor
        try {
            conexion = new ClienteConexion(aviso::consola, pt.getP1(), pt.getP2()); // Inicia la conexión con el
                                                                                    // servidor
        } catch (Exception e) {
            // TODO: handle exception
        }
        // Inicio de la sesión del cliente
        inicio();
    }
    /**
     * Método para configurar la interfaz de inicio de sesión del cliente.
     */
    private void inicio() {
        try {
            conexion.conexion();
        } catch (Exception e) {
            // TODO: handle exception
        }
        // Configuración de la interfaz de inicio de sesión
        inicioSesion = new Login();
        inicioSesion.loginBtn.addActionListener(this);
        inicioSesion.crearCuentabtn.addActionListener(this);
        inicioSesion.setVisible(true);
    }

    /**
     * Verificar cliente y crear hilo
     */
    private void iniciarCliente() {
        String user = inicioSesion.user.getText();
        String password = inicioSesion.password.getText();
        if (user.isBlank() || password.isBlank()) {
            aviso.verMensajeOP("Llene todos los campos por favor");
        } else {
            try {
                int op = conexion.iniciarS(user, password);
                switch (op) {
                    case 0:
                        aviso.verMensajeOP(
                                "El usuario registrado no existe, verifique los datos, en caso de no tener cuenta, lo invitamos a crear una cuenta");
                        break;
                    case 1:
                    // Iniciar sesión correctamente, abrir la interfaz principal del cliente
                        inicioSesion.dispose();
                        new HiloCliente(this.conexion);

                        break;
                    case 2:
                        aviso.verMensajeOP("Contraseña invalida, verifique sus datos");
                        break;
                    default:
                        break;
                }
            } catch (Exception e) {
                aviso.consola(e.getMessage());

            }
        }

    }

    /**
     * Metodo encargado de ordenar la creacion de una nueva cuenta
     */
    private void crearCuenta() {
        // Obtener los datos ingresados por el usuario
        String user = cr.user.getText();
        String p1 = cr.password.getText();
        String p2 = cr.password2.getText();
        // Verificar si los campos están vacíos
        if (user.isBlank() || p1.isBlank() || p2.isBlank()) {
            aviso.verMensajeOP("Llene todos los campos por favor");
            // Verificar si las contraseñas coinciden
        } else if (p1.equals(p2)) {
            try {
                boolean aux = conexion.crearCuenta(user, p1);
                if (aux) {
                    aviso.verMensajeOP("Cuenta creada con exito");
                    cr.dispose();
                    inicioSesion.setVisible(true);
                } else {
                    aviso.verMensajeOP("Una cuenta con este nombre de usuario ya existe, elija otro nombre de usuario");
                }

            } catch (Exception e) {
                aviso.consola(e.getMessage());
            }
        } else if (!p1.equals(p2)) {
            aviso.verMensajeOP("Las contraseñas no coinciden, verifique");
        }

    }
    /**
     * Método para gestionar las acciones de los botones en la interfaz de usuario.
     */
    public void actionPerformed(ActionEvent e) {
        // Inicio de seson
        if (e.getSource() == inicioSesion.loginBtn) {
            iniciarCliente();
        }
        // Crear cuenta
        else if (e.getSource() == inicioSesion.crearCuentabtn) {
            inicioSesion.setVisible(false);
            cr = new CrearCuenta();
            cr.crearCuentabtn.addActionListener(this);
            cr.setVisible(true);
        } else if (e.getSource() == cr.crearCuentabtn) {
            crearCuenta();
        }
    }
}
