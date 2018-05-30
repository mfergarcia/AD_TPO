package swing.pantalla;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;

public class PantallaModificarClientePersona {

	private JFrame frmModificarClientepersona;
	private JTextField condicionesEspeciales_textField;
	private JTextField tipoFactura_textField;
	private JTextField localidad_textField;
	private JTextField codigoPostal_textField;
	private JTextField calle_textField;
	private JTextField numero_textField;
	private JTextField dni_textField;
	private JTextField nombre_textField;
	private JTextField apellido_textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PantallaModificarClientePersona window = new PantallaModificarClientePersona();
					window.frmModificarClientepersona.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PantallaModificarClientePersona() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmModificarClientepersona = new JFrame();
		frmModificarClientepersona.setTitle("Modificaci\u00F3n Cliente");
		frmModificarClientepersona.setBounds(100, 100, 450, 352);
		frmModificarClientepersona.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmModificarClientepersona.getContentPane().setLayout(null);
		
		condicionesEspeciales_textField = new JTextField();
		condicionesEspeciales_textField.setColumns(10);
		condicionesEspeciales_textField.setBounds(169, 11, 86, 20);
		frmModificarClientepersona.getContentPane().add(condicionesEspeciales_textField);
		
		JLabel condicionesEspeciales_label = new JLabel("Condiciones especiales: ");
		condicionesEspeciales_label.setBounds(10, 14, 116, 14);
		frmModificarClientepersona.getContentPane().add(condicionesEspeciales_label);
		
		tipoFactura_textField = new JTextField();
		tipoFactura_textField.setColumns(10);
		tipoFactura_textField.setBounds(169, 42, 86, 20);
		frmModificarClientepersona.getContentPane().add(tipoFactura_textField);
		
		JLabel tipoFactura_label = new JLabel("Tipo factura:");
		tipoFactura_label.setBounds(10, 45, 86, 14);
		frmModificarClientepersona.getContentPane().add(tipoFactura_label);
		
		localidad_textField = new JTextField();
		localidad_textField.setColumns(10);
		localidad_textField.setBounds(169, 73, 86, 20);
		frmModificarClientepersona.getContentPane().add(localidad_textField);
		
		JLabel localidad_label = new JLabel("Localidad: ");
		localidad_label.setBounds(10, 76, 116, 14);
		frmModificarClientepersona.getContentPane().add(localidad_label);
		
		codigoPostal_textField = new JTextField();
		codigoPostal_textField.setColumns(10);
		codigoPostal_textField.setBounds(169, 104, 86, 20);
		frmModificarClientepersona.getContentPane().add(codigoPostal_textField);
		
		JLabel codigoPostal_label = new JLabel("Codigo postal:");
		codigoPostal_label.setBounds(10, 107, 86, 14);
		frmModificarClientepersona.getContentPane().add(codigoPostal_label);
		
		JLabel calle_label = new JLabel("Calle:");
		calle_label.setBounds(10, 138, 46, 14);
		frmModificarClientepersona.getContentPane().add(calle_label);
		
		JLabel lblNmero = new JLabel("N\u00FAmero: ");
		lblNmero.setBounds(10, 169, 46, 14);
		frmModificarClientepersona.getContentPane().add(lblNmero);
		
		calle_textField = new JTextField();
		calle_textField.setColumns(10);
		calle_textField.setBounds(169, 135, 86, 20);
		frmModificarClientepersona.getContentPane().add(calle_textField);
		
		numero_textField = new JTextField();
		numero_textField.setColumns(10);
		numero_textField.setBounds(169, 166, 86, 20);
		frmModificarClientepersona.getContentPane().add(numero_textField);
		
		dni_textField = new JTextField();
		dni_textField.setColumns(10);
		dni_textField.setBounds(169, 197, 86, 20);
		frmModificarClientepersona.getContentPane().add(dni_textField);
		
		nombre_textField = new JTextField();
		nombre_textField.setColumns(10);
		nombre_textField.setBounds(169, 227, 86, 20);
		frmModificarClientepersona.getContentPane().add(nombre_textField);
		
		JButton button = new JButton("Finalizar");
		button.setBounds(335, 279, 89, 23);
		frmModificarClientepersona.getContentPane().add(button);
		
		apellido_textField = new JTextField();
		apellido_textField.setBounds(169, 258, 86, 20);
		frmModificarClientepersona.getContentPane().add(apellido_textField);
		apellido_textField.setColumns(10);
		
		JLabel lblDni = new JLabel("DNI:");
		lblDni.setBounds(10, 200, 46, 14);
		frmModificarClientepersona.getContentPane().add(lblDni);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(10, 230, 46, 14);
		frmModificarClientepersona.getContentPane().add(lblNombre);
		
		JLabel lblApellido = new JLabel("Apellido: ");
		lblApellido.setBounds(10, 261, 46, 14);
		frmModificarClientepersona.getContentPane().add(lblApellido);
	}

}
