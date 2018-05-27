package swing.pantalla;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class PantallaAltaClientePersona {

	private JFrame frmClientepersona;
	private JTextField nombre_textField;
	private JTextField apellido_textField;
	private JTextField dni_textField;
	private JButton btnFinalizar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PantallaAltaClientePersona window = new PantallaAltaClientePersona();
					window.frmClientepersona.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PantallaAltaClientePersona() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmClientepersona = new JFrame();
		frmClientepersona.setTitle("Alta Cliente Persona");
		frmClientepersona.setBounds(100, 100, 450, 300);
		frmClientepersona.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmClientepersona.getContentPane().setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(10, 51, 46, 14);
		frmClientepersona.getContentPane().add(lblNombre);
		
		nombre_textField = new JTextField();
		nombre_textField.setBounds(177, 48, 86, 20);
		frmClientepersona.getContentPane().add(nombre_textField);
		nombre_textField.setColumns(10);
		
		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setBounds(10, 122, 46, 14);
		frmClientepersona.getContentPane().add(lblApellido);
		
		JLabel lblDni = new JLabel("DNI:");
		lblDni.setBounds(10, 196, 46, 14);
		frmClientepersona.getContentPane().add(lblDni);
		
		apellido_textField = new JTextField();
		apellido_textField.setBounds(177, 119, 86, 20);
		frmClientepersona.getContentPane().add(apellido_textField);
		apellido_textField.setColumns(10);
		
		dni_textField = new JTextField();
		dni_textField.setBounds(177, 193, 86, 20);
		frmClientepersona.getContentPane().add(dni_textField);
		dni_textField.setColumns(10);
		
		btnFinalizar = new JButton("Finalizar");
		btnFinalizar.setBounds(335, 227, 89, 23);
		frmClientepersona.getContentPane().add(btnFinalizar);
	}

}
