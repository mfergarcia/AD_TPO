package swing.pantalla;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;

import delegados.SistemaBD;
import dto.ClienteEmpresaDTO;
import dto.DireccionDTO;
import excepciones.ExcepcionComunicacion;
import excepciones.ExcepcionSistema;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PantallaModificarClienteEmpresa {

	JFrame frmModificacionClienteempresa;
	private JTextField condicionesEspeciales_textField;
	private JTextField tipoFactura_textField;
	private JTextField localidad_textField;
	private JTextField codigoPostal_textField;
	private JTextField calle_textField;
	private JTextField numero_textField;
	private JTextField cuit_textField;
	private JTextField razonSocial_textField;
	private int id;
	private JLabel lblLmiteCrdito;
	private JTextField limiteCredito_textField;


	public PantallaModificarClienteEmpresa() {
		initialize();
	}
	
	public PantallaModificarClienteEmpresa(int id) {
		initialize();
		this.id=id;
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmModificacionClienteempresa = new JFrame();
		frmModificacionClienteempresa.setTitle("Modificaci\u00F3n Cliente");
		frmModificacionClienteempresa.setBounds(100, 100, 450, 370);
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
				try{
					SistemaBD bd = new SistemaBD();
					ClienteEmpresaDTO cteEmpresa = bd.obtenerCteEmpresa(1);
					DireccionDTO d=new DireccionDTO();
					
					if(!condicionesEspeciales_textField.getText().equals(""))
						cteEmpresa.setCondicionesEspeciales(condicionesEspeciales_textField.getText());
					if(!tipoFactura_textField.getText().equals(""))
						cteEmpresa.setTipoFactura(tipoFactura_textField.getText().charAt(0));
					if(!codigoPostal_textField.getText().equals("")){
						d=cteEmpresa.getDireccionFacturacion();
						d.setCodigoPostal(codigoPostal_textField.getText());
						cteEmpresa.setDireccionFacturacion(d);
					}
					if(!localidad_textField.getText().equals("")){
						d=cteEmpresa.getDireccionFacturacion();
						d.setCodigoPostal(localidad_textField.getText());
						cteEmpresa.setDireccionFacturacion(d);
					}
					if(!calle_textField.getText().equals("")){
						d=cteEmpresa.getDireccionFacturacion();
						d.setCalle(calle_textField.getText());
						cteEmpresa.setDireccionFacturacion(d);
					}
					if(!numero_textField.getText().equals("")){
						d=cteEmpresa.getDireccionFacturacion();
						d.setNumero(Integer.parseInt(numero_textField.getText()));
						cteEmpresa.setDireccionFacturacion(d);
					}
					if(!limiteCredito_textField.getText().equals(""))
						cteEmpresa.setLimiteCredito(Float.parseFloat(limiteCredito_textField.getText()));
					if(!cuit_textField.getText().equals(""))
						cteEmpresa.setCuit(cuit_textField.getText());
					if(!razonSocial_textField.getText().equals(""))
						cteEmpresa.setRazonSocial(razonSocial_textField.getText());
					bd.modificarCteEmpresa(cteEmpresa);
					JOptionPane.showMessageDialog(frmModificacionClienteempresa, "Su cliente ha sido modificado");
					Menú m= new Menú();
					m.frmMenu.setVisible(true);
					m.frmMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frmModificacionClienteempresa.dispose();
				} catch (ExcepcionComunicacion es) {
					System.out.println(es.getMensaje());
				} catch (ExcepcionSistema es) {
					System.out.println(es.getMensaje());
				}
			}
			
		});
		btnFinalizar.setBounds(335, 297, 89, 23);
		frmModificacionClienteempresa.getContentPane().add(btnFinalizar);
		
		lblLmiteCrdito = new JLabel("L\u00EDmite cr\u00E9dito:");
		lblLmiteCrdito.setBounds(10, 258, 86, 20);
		frmModificacionClienteempresa.getContentPane().add(lblLmiteCrdito);
		
		limiteCredito_textField = new JTextField();
		limiteCredito_textField.setBounds(169, 258, 86, 20);
		frmModificacionClienteempresa.getContentPane().add(limiteCredito_textField);
		limiteCredito_textField.setColumns(10);
	}
}
