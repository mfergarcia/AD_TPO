package swing.pantalla;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class PantallaAltaClienteEmpresa {

	private JFrame frmAltaClienteEmpresa;
	private JTextField cuit_textField;
	private JTextField razonSocial_textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PantallaAltaClienteEmpresa window = new PantallaAltaClienteEmpresa();
					window.frmAltaClienteEmpresa.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PantallaAltaClienteEmpresa() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAltaClienteEmpresa = new JFrame();
		frmAltaClienteEmpresa.setTitle("Alta Cliente Empresa");
		frmAltaClienteEmpresa.setBounds(100, 100, 450, 300);
		frmAltaClienteEmpresa.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAltaClienteEmpresa.getContentPane().setLayout(null);
		
		JLabel lblCuit = new JLabel("CUIT: ");
		lblCuit.setBounds(10, 63, 46, 14);
		frmAltaClienteEmpresa.getContentPane().add(lblCuit);
		
		JLabel lblRazonSocial = new JLabel("Razon Social:");
		lblRazonSocial.setBounds(10, 173, 84, 14);
		frmAltaClienteEmpresa.getContentPane().add(lblRazonSocial);
		
		cuit_textField = new JTextField();
		cuit_textField.setBounds(172, 60, 86, 20);
		frmAltaClienteEmpresa.getContentPane().add(cuit_textField);
		cuit_textField.setColumns(10);
		
		razonSocial_textField = new JTextField();
		razonSocial_textField.setBounds(172, 170, 86, 20);
		frmAltaClienteEmpresa.getContentPane().add(razonSocial_textField);
		razonSocial_textField.setColumns(10);
		
		JButton btnFinalizar = new JButton("Finalizar");
		btnFinalizar.setBounds(335, 227, 89, 23);
		frmAltaClienteEmpresa.getContentPane().add(btnFinalizar);
	}
}
