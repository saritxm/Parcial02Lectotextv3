package Cliente.View;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;

public class Login extends JFrame {
	// Atributos de la interfaz gráfica
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public JTextField password;
	public JTextField user;
	public JButton crearCuentabtn;
	public JButton loginBtn;
	/**
	 * Create the frame.
	 */
	public Login() {
		// Configuración de la ventana
        setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 720, 511);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		// Campo de texto para el nombre de usuario
		user = new JTextField();
		user.setBounds(354, 206, 284, 29);
		contentPane.add(user);
		user.setColumns(10);
		// Campo de texto para la contraseña
		password = new JTextField();
		password.setColumns(10);
		password.setBounds(354, 278, 284, 29);
		contentPane.add(password);
		// Botón para iniciar sesión
		loginBtn = new JButton("Iniciar Sesión");
		loginBtn.setBackground(new Color(255, 54, 28));
		loginBtn.setBounds(434, 327, 124, 36);
		contentPane.add(loginBtn);
		// Botón para crear una cuenta
		crearCuentabtn = new JButton("Crear Cuenta");
		crearCuentabtn.setBackground(new Color(255, 54, 28));
		crearCuentabtn.setBounds(434, 415, 124, 23);
		contentPane.add(crearCuentabtn);
		// Configuración del fondo de la ventana
		ImageIcon imagenFondo = new ImageIcon(Login.class.getResource("iniciosSesion.png"));
		Image img = imagenFondo.getImage();
		Image imgEscalada = img.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
		ImageIcon imagenEscalada = new ImageIcon(imgEscalada);
		JLabel fondo = new JLabel(imagenEscalada);
		fondo.setBounds(0, 0, 704, 472);
		contentPane.add(fondo);
	}
}
