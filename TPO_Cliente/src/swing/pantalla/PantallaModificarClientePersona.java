package swing.pantalla;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JTextField;

import delegados.SistemaBD;
import dto.ClienteEmpresaDTO;
import dto.ClientePersonaDTO;
import dto.DireccionDTO;
import excepciones.ExcepcionComunicacion;
import excepciones.ExcepcionSistema;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;

public class PantallaModificarClientePersona {

	JFrame frmModificarClientepersona;
	private JTextField condicionesEspeciales_textField;
	private JTextField tipoFactura_textField;
	private JTextField localidad_textField;
	private JTextField codigoPostal_textField;
	private JTextField calle_textField;
	private JTextField numero_textField;
	private JTextField dni_textField;
	private JTextField nombre_textField;
	private JTextField apellido_textField;
	private JLabel lblLmitecrdito;
	private JTextField limiteCredito_textField;
	private int id;

	public PantallaModificarClientePersona() {
		initialize();
	}
	
	public PantallaModificarClientePersona(int id) {
		initialize();
		this.id=id;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmModificarClientepersona = new JFrame();
		frmModificarClientepersona.setTitle("Modificaci\u00F3n Cliente");
		frmModificarClientepersona.setBounds(100, 100, 450, 384);
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
		
		JButton btnFinalizar = new JButton("Finalizar");
		btnFinalizar.setBounds(335, 311, 89, 23);
		frmModificarClientepersona.getContentPane().add(btnFinalizar);
		btnFinalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					SistemaBD bd = new SistemaBD();
					ClientePersonaDTO ctePersona = bd.obtenerCtePersona(id);
					DireccionDTO d=new DireccionDTO();
					
					if(!condicionesEspeciales_textField.getText().equals(""))
						ctePersona.setCondicionesEspeciales(condicionesEspeciales_textField.getText());
					
					if(!tipoFactura_textField.getText().equals(""))
						ctePersona.setTipoFactura(tipoFactura_textField.getText().charAt(0));
					
					if(!codigoPostal_textField.getText().equals("")){
						d=ctePersona.getDireccionFacturacion();
						d.setCodigoPostal(codigoPostal_textField.getText());
						ctePersona.setDireccionFacturacion(d);
					}
					
					if(!localidad_textField.getText().equals("")){
						d=ctePersona.getDireccionFacturacion();
						d.setCodigoPostal(localidad_textField.getText());
						ctePersona.setDireccionFacturacion(d);
					}
					
					if(!calle_textField.getText().equals("")){
						d=ctePersona.getDireccionFacturacion();
						d.setCalle(calle_textField.getText());
						ctePersona.setDireccionFacturacion(d);
					}
				
					if(!numero_textField.getText().equals("")){
						d=ctePersona.getDireccionFacturacion();
						d.setNumero(Integer.parseInt(numero_textField.getText()));
						ctePersona.setDireccionFacturacion(d);
					}
					
					if(!limiteCredito_textField.getText().equals(""))
						ctePersona.setLimiteCredito(Float.parseFloat(limiteCredito_textField.getText()));
					
					if(!dni_textField.getText().equals(""))
						ctePersona.setDni(dni_textField.getText());
					
					if(!nombre_textField.getText().equals(""))
						ctePersona.setNombre(nombre_textField.getText());
					
					if(!apellido_textField.getText().equals(""))
						ctePersona.setApellido(apellido_textField.getText());
				bd.modificarCtePersona(ctePersona);
				JOptionPane.showMessageDialog(frmModificarClientepersona, "Su cliente ha sido modificado");
				Menú m= new Menú();
				m.frmMenu.setVisible(true);
				m.frmMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frmModificarClientepersona.dispose();
				} catch (ExcepcionComunicacion es) {
					System.out.println(es.getMensaje());
				} catch (ExcepcionSistema es) {
					System.out.println(es.getMensaje());
				}
				
			}
		});
		
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
		
		lblLmitecrdito = new JLabel("L\u00EDmite cr\u00E9dito:");
		lblLmitecrdito.setBounds(10, 289, 86, 20);
		frmModificarClientepersona.getContentPane().add(lblLmitecrdito);
		
		limiteCredito_textField = new JTextField();
		limiteCredito_textField.setColumns(10);
		limiteCredito_textField.setBounds(169, 289, 86, 20);
		frmModificarClientepersona.getContentPane().add(limiteCredito_textField);
	}

}
