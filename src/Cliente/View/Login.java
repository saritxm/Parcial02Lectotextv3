package Cliente.View;

import java.awt.EventQueue;
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

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public JTextField password;
	public JTextField user;
	public JButton crearCuentabtn;
	public JButton loginBtn;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 720, 511);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		user = new JTextField();
		user.setBounds(354, 206, 284, 29);
		contentPane.add(user);
		user.setColumns(10);

		password = new JTextField();
		password.setColumns(10);
		password.setBounds(354, 278, 284, 29);
		contentPane.add(password);

		loginBtn = new JButton("Iniciar Sesión");
		loginBtn.setBackground(new Color(255, 54, 28));
		loginBtn.setBounds(434, 327, 124, 36);
		contentPane.add(loginBtn);
		
		crearCuentabtn = new JButton("Crear Cuenta");
		crearCuentabtn.setBackground(new Color(255, 54, 28));
		crearCuentabtn.setBounds(434, 415, 124, 23);
		contentPane.add(crearCuentabtn);
		
		
		ImageIcon imagenFondo = new ImageIcon(Login.class.getResource("iniciosSesion.png"));
		Image img = imagenFondo.getImage();
		Image imgEscalada = img.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
		ImageIcon imagenEscalada = new ImageIcon(imgEscalada);
		JLabel fondo = new JLabel(imagenEscalada);
		fondo.setBounds(0, 0, 704, 472);
		contentPane.add(fondo);
	}
}
