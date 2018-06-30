package swing.pantalla;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.nio.channels.ShutdownChannelGroupException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import delegados.SistemaBD;
import dto.ClienteEmpresaDTO;
import dto.ClientePersonaDTO;
import dto.DireccionDTO;
import excepciones.ExcepcionComunicacion;
import excepciones.ExcepcionSistema;

import javax.swing.JButton;

public class PantallaAltaClientePersona {

	public JFrame frmClientepersona;
	private JTextField nombre_textField;
	private JTextField apellido_textField;
	private JTextField dni_textField;
	private JButton btnFinalizar;
	private String condicionesEspeciales;
	private String tipoFactura;
	private String localidad;
	private String codigoPostal;
	private String calle;
	private String numero;
	private String limiteCredito;
	
	
	public PantallaAltaClientePersona() {
		initialize();
	}

	public PantallaAltaClientePersona(String condEsp, String tipoFac, String localidad, String codPostal, String calle, String numero,
			String limCredito) {
		this.calle= calle;
		this.codigoPostal= codPostal;
		this.condicionesEspeciales= condEsp;
		this.limiteCredito= limCredito;
		this.tipoFactura= tipoFac;
		this.localidad= localidad;
		this.numero= numero;
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
		btnFinalizar.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				try {
					SistemaBD bd= new SistemaBD();
					ClientePersonaDTO ctePersona = new ClientePersonaDTO();
					ctePersona.setDni(dni_textField.getText());
					ctePersona.setApellido(apellido_textField.getText());
					ctePersona.setNombre(nombre_textField.getText());
					DireccionDTO dirFacturacion = new DireccionDTO();
					dirFacturacion.setCalle(calle);
					dirFacturacion.setNumero(Integer.parseInt(numero));
					dirFacturacion.setCodigoPostal(codigoPostal);
					dirFacturacion.setLocalidad(localidad);
					ctePersona.setDireccionFacturacion(dirFacturacion);
					ctePersona.setTipoFactura(tipoFactura.charAt(0));
					ctePersona.setCondicionesEspeciales(condicionesEspeciales);
					ctePersona.setLimiteCredito(Float.parseFloat(limiteCredito));
					ctePersona = bd.altaClientePersona(ctePersona);
					JOptionPane.showMessageDialog(frmClientepersona, "Su Cliente ha sido creado con éxito!");
					Menú m= new Menú();
					m.frmMenu.setVisible(true);
					m.frmMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frmClientepersona.dispose();
				} catch (ExcepcionComunicacion ev) {
					System.out.println(ev.getMensaje());
				} catch (ExcepcionSistema ev) {
					System.out.println(ev.getMensaje());
				}
			}
		});
		frmClientepersona.getContentPane().add(btnFinalizar);
	}

}
