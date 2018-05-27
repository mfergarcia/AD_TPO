package swing.pantalla;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

public class PantallaAltaCliente {

	public JFrame frmAltaCliente;
	private JTextField condicionesEsp_textfield;
	private JTextField tipoFacutra_textField;
	private JTextField localidad_textField;
	private JTextField codigoPostal_textField;
	private JTextField calle_textField;
	private JTextField numero_textField;
	private JTextField limiteCredito_textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PantallaAltaCliente window = new PantallaAltaCliente();
					window.frmAltaCliente.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PantallaAltaCliente() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initialize() {
		frmAltaCliente = new JFrame();
		frmAltaCliente.setTitle("Alta Cliente");
		frmAltaCliente.setBounds(100, 100, 450, 300);
		frmAltaCliente.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAltaCliente.getContentPane().setLayout(null);
		
		condicionesEsp_textfield = new JTextField();
		condicionesEsp_textfield.setBounds(172, 11, 86, 20);
		frmAltaCliente.getContentPane().add(condicionesEsp_textfield);
		condicionesEsp_textfield.setColumns(10);
		
		tipoFacutra_textField = new JTextField();
		tipoFacutra_textField.setBounds(172, 42, 86, 20);
		frmAltaCliente.getContentPane().add(tipoFacutra_textField);
		tipoFacutra_textField.setColumns(10);
		
		@SuppressWarnings("rawtypes")
		JComboBox tipo_comboBox = new JComboBox();
		tipo_comboBox.setModel(new DefaultComboBoxModel(new String[] {"Empresa", "Persona"}));
		tipo_comboBox.setMaximumRowCount(2);
		tipo_comboBox.setBounds(172, 73, 86, 20);
		frmAltaCliente.getContentPane().add(tipo_comboBox);
		
		localidad_textField = new JTextField();
		localidad_textField.setBounds(172, 104, 86, 20);
		frmAltaCliente.getContentPane().add(localidad_textField);
		localidad_textField.setColumns(10);
		
		codigoPostal_textField = new JTextField();
		codigoPostal_textField.setBounds(172, 135, 86, 20);
		frmAltaCliente.getContentPane().add(codigoPostal_textField);
		codigoPostal_textField.setColumns(10);
		
		calle_textField = new JTextField();
		calle_textField.setBounds(172, 166, 86, 20);
		frmAltaCliente.getContentPane().add(calle_textField);
		calle_textField.setColumns(10);
		
		numero_textField = new JTextField();
		numero_textField.setBounds(172, 197, 86, 20);
		frmAltaCliente.getContentPane().add(numero_textField);
		numero_textField.setColumns(10);
		
		JLabel lblCondicionesEsp = new JLabel("Condiciones Especiales:");
		lblCondicionesEsp.setBounds(10, 14, 157, 14);
		frmAltaCliente.getContentPane().add(lblCondicionesEsp);
		
		JLabel lblTipoFactura = new JLabel("Tipo Factura:");
		lblTipoFactura.setBounds(10, 45, 119, 14);
		frmAltaCliente.getContentPane().add(lblTipoFactura);
		
		limiteCredito_textField = new JTextField();
		limiteCredito_textField.setBounds(172, 228, 86, 20);
		frmAltaCliente.getContentPane().add(limiteCredito_textField);
		limiteCredito_textField.setColumns(10);
		
		JLabel lblTipoCliente = new JLabel("Tipo Cliente");
		lblTipoCliente.setBounds(10, 76, 86, 14);
		frmAltaCliente.getContentPane().add(lblTipoCliente);
		
		JLabel lblLocalidad = new JLabel("Localidad:");
		lblLocalidad.setBounds(10, 107, 86, 14);
		frmAltaCliente.getContentPane().add(lblLocalidad);
		
		JLabel lblCodigoPostal = new JLabel("Codigo Postal:");
		lblCodigoPostal.setBounds(10, 138, 86, 14);
		frmAltaCliente.getContentPane().add(lblCodigoPostal);
		
		JLabel lblCalle = new JLabel("Calle: ");
		lblCalle.setBounds(10, 169, 46, 14);
		frmAltaCliente.getContentPane().add(lblCalle);
		
		JLabel lblNumero = new JLabel("Numero:");
		lblNumero.setBounds(10, 200, 46, 14);
		frmAltaCliente.getContentPane().add(lblNumero);
		
		JLabel lblLimiteDeCredito = new JLabel("Limite de Credito:");
		lblLimiteDeCredito.setBounds(10, 231, 129, 14);
		frmAltaCliente.getContentPane().add(lblLimiteDeCredito);
		
		JButton btnSiguiente = new JButton("Siguiente ->");
		btnSiguiente.setBounds(325, 227, 99, 23);
		btnSiguiente.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(tipo_comboBox.equals("Empresa")){
					PantallaAltaClienteEmpresa p= new PantallaAltaClienteEmpresa(condicionesEsp_textfield.getText(), 
												  tipoFacutra_textField.getText(), localidad_textField.getText(), codigoPostal_textField.getText()
												  , calle_textField.getText(), numero_textField.getText(), limiteCredito_textField.getText());
				
					p.frmAltaClienteEmpresa.setVisible(true);
					p.frmAltaClienteEmpresa.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frmAltaCliente.dispose();
				}else if(tipo_comboBox.equals("Persona")){
					PantallaAltaClientePersona p= new PantallaAltaClientePersona(condicionesEsp_textfield.getText(), 
							  tipoFacutra_textField.getText(), localidad_textField.getText(), codigoPostal_textField.getText()
							  , calle_textField.getText(), numero_textField.getText(), limiteCredito_textField.getText());

					p.frmClientepersona.setVisible(true);
					p.frmClientepersona.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frmAltaCliente.dispose();
					
				}
			}
		});
		frmAltaCliente.getContentPane().add(btnSiguiente);
	}
}
