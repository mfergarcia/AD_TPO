package swing.pantalla;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PantallaModificarClienteEmpresa {

	private JFrame frmModificacionClienteempresa;
	private JTextField condicionesEspeciales_textField;
	private JTextField tipoFactura_textField;
	private JTextField localidad_textField;
	private JTextField codigoPostal_textField;
	private JTextField calle_textField;
	private JTextField numero_textField;
	private JTextField cuit_textField;
	private JTextField razonSocial_textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PantallaModificarClienteEmpresa window = new PantallaModificarClienteEmpresa();
					window.frmModificacionClienteempresa.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PantallaModificarClienteEmpresa() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmModificacionClienteempresa = new JFrame();
		frmModificacionClienteempresa.setTitle("Modificaci\u00F3n Cliente");
		frmModificacionClienteempresa.setBounds(100, 100, 450, 322);
		frmModificacionClienteempresa.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmModificacionClienteempresa.getContentPane().setLayout(null);
		
		condicionesEspeciales_textField = new JTextField();
		condicionesEspeciales_textField.setBounds(169, 11, 86, 20);
		frmModificacionClienteempresa.getContentPane().add(condicionesEspeciales_textField);
		condicionesEspeciales_textField.setColumns(10);
		
		JLabel lblCondicionesEspeciales = new JLabel("Condiciones especiales: ");
		lblCondicionesEspeciales.setBounds(10, 14, 116, 14);
		frmModificacionClienteempresa.getContentPane().add(lblCondicionesEspeciales);
		
		tipoFactura_textField = new JTextField();
		tipoFactura_textField.setBounds(169, 42, 86, 20);
		frmModificacionClienteempresa.getContentPane().add(tipoFactura_textField);
		tipoFactura_textField.setColumns(10);
		
		JLabel lblTipoFactura = new JLabel("Tipo factura:");
		lblTipoFactura.setBounds(10, 45, 86, 14);
		frmModificacionClienteempresa.getContentPane().add(lblTipoFactura);
		
		localidad_textField = new JTextField();
		localidad_textField.setBounds(169, 73, 86, 20);
		frmModificacionClienteempresa.getContentPane().add(localidad_textField);
		localidad_textField.setColumns(10);
		
		JLabel lblLocalidad = new JLabel("Localidad: ");
		lblLocalidad.setBounds(10, 76, 116, 14);
		frmModificacionClienteempresa.getContentPane().add(lblLocalidad);
		
		codigoPostal_textField = new JTextField();
		codigoPostal_textField.setBounds(169, 104, 86, 20);
		frmModificacionClienteempresa.getContentPane().add(codigoPostal_textField);
		codigoPostal_textField.setColumns(10);
		
		JLabel lblcodigoPostal = new JLabel("Codigo postal:");
		lblcodigoPostal.setBounds(10, 107, 86, 14);
		frmModificacionClienteempresa.getContentPane().add(lblcodigoPostal);
		
		JLabel lblCalle = new JLabel("Calle:");
		lblCalle.setBounds(10, 138, 46, 14);
		frmModificacionClienteempresa.getContentPane().add(lblCalle);
		
		JLabel lblNumero = new JLabel("N\u00FAmero: ");
		lblNumero.setBounds(10, 169, 46, 14);
		frmModificacionClienteempresa.getContentPane().add(lblNumero);
		
		JLabel lblCuit = new JLabel("CUIT: ");
		lblCuit.setBounds(10, 200, 46, 14);
		frmModificacionClienteempresa.getContentPane().add(lblCuit);
		
		JLabel lblRaznSocial = new JLabel("Raz\u00F3n social: ");
		lblRaznSocial.setBounds(10, 230, 86, 14);
		frmModificacionClienteempresa.getContentPane().add(lblRaznSocial);
		
		calle_textField = new JTextField();
		calle_textField.setBounds(169, 135, 86, 20);
		frmModificacionClienteempresa.getContentPane().add(calle_textField);
		calle_textField.setColumns(10);
		
		numero_textField = new JTextField();
		numero_textField.setBounds(169, 166, 86, 20);
		frmModificacionClienteempresa.getContentPane().add(numero_textField);
		numero_textField.setColumns(10);
		
		cuit_textField = new JTextField();
		cuit_textField.setBounds(169, 197, 86, 20);
		frmModificacionClienteempresa.getContentPane().add(cuit_textField);
		cuit_textField.setColumns(10);
		
		razonSocial_textField = new JTextField();
		razonSocial_textField.setBounds(169, 227, 86, 20);
		frmModificacionClienteempresa.getContentPane().add(razonSocial_textField);
		razonSocial_textField.setColumns(10);
		
		JButton btnFinalizar = new JButton("Finalizar");
		btnFinalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnFinalizar.setBounds(335, 249, 89, 23);
		frmModificacionClienteempresa.getContentPane().add(btnFinalizar);
	}
}
