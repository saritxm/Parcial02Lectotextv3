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

public class CrearCuenta extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public JTextField password;
	public JTextField user;
	public JButton crearCuentabtn;
	private JTextField password2;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CrearCuenta frame = new CrearCuenta();
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
	public CrearCuenta() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 720, 511);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		user = new JTextField();
		user.setBounds(354, 180, 284, 29);
		contentPane.add(user);
		user.setColumns(10);

		password = new JTextField();
		password.setColumns(10);
		password.setBounds(354, 261, 284, 29);
		contentPane.add(password);
		
		crearCuentabtn = new JButton("Crear Cuenta");
		crearCuentabtn.setBackground(new Color(255, 54, 28));
		crearCuentabtn.setBounds(429, 402, 129, 36);
		contentPane.add(crearCuentabtn);

		password2 = new JTextField();
		password2.setColumns(10);
		password2.setBounds(354, 347, 284, 29);
		contentPane.add(password2);
		
		ImageIcon imagenFondo = new ImageIcon(CrearCuenta.class.getResource("CrearCuenta.png"));
		Image img = imagenFondo.getImage();
		Image imgEscalada = img.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
		ImageIcon imagenEscalada = new ImageIcon(imgEscalada);
		JLabel fondo = new JLabel(imagenEscalada);
		fondo.setBounds(0, 0, 704, 472);
		contentPane.add(fondo);
		
		
	}
}
