package swing.pantalla;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import delegados.SistemaBD;
import dto.ClienteEmpresaDTO;
import dto.DireccionDTO;
import excepciones.ExcepcionComunicacion;
import excepciones.ExcepcionSistema;

import javax.swing.JButton;

public class PantallaAltaClienteEmpresa {

	public JFrame frmAltaClienteEmpresa;
	private JTextField cuit_textField;
	private JTextField razonSocial_textField;
	private String condicionesEspeciales;
	private String tipoFactura;
	private String localidad;
	private String codigoPostal;
	private String calle;
	private String numero;
	private String limiteCredito;

	/**
	 * Create the application.
	 */
	public PantallaAltaClienteEmpresa() {
		initialize();
	}

	public PantallaAltaClienteEmpresa(String condEsp, String tipoFac, String localidad, String codPostal, String calle, String numero,
			String limCredito) {
		this.calle= calle;
		this.codigoPostal= codPostal;
		this.condicionesEspeciales= condEsp;
		this.limiteCredito= limCredito;
		this.tipoFactura= tipoFac;
		this.localidad= localidad;
		this.numero= numero;
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
		btnFinalizar.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				try {
					SistemaBD bd= new SistemaBD();
					ClienteEmpresaDTO cteEmpresa= new ClienteEmpresaDTO();
					cteEmpresa.setCuit(cuit_textField.getText());
					cteEmpresa.setRazonSocial(razonSocial_textField.getText());
					DireccionDTO dirFacturacion = new DireccionDTO();
					dirFacturacion.setCalle(calle);
					dirFacturacion.setNumero(Integer.parseInt(numero));
					dirFacturacion.setCodigoPostal(codigoPostal);
					dirFacturacion.setLocalidad(localidad);
					cteEmpresa.setDireccionFacturacion(dirFacturacion);
					cteEmpresa.setTipoFactura(tipoFactura.charAt(0));
					cteEmpresa.setCondicionesEspeciales(condicionesEspeciales);
					cteEmpresa.setLimiteCredito(Float.parseFloat(limiteCredito));
					cteEmpresa = bd.altaClienteEmpresa(cteEmpresa);
					
				} catch (ExcepcionComunicacion ev) {
					System.out.println(ev.getMensaje());
				} catch (ExcepcionSistema ev) {
					System.out.println(ev.getMensaje());
				}
			}
		});
		frmAltaClienteEmpresa.getContentPane().add(btnFinalizar);
	}
}
