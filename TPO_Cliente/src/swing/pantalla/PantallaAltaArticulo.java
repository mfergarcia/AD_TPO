package swing.pantalla;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JTextField;

import delegados.SistemaBD;
import dto.ArticuloDTO;
import dto.ClienteEmpresaDTO;
import dto.DireccionDTO;
import excepciones.ExcepcionComunicacion;
import excepciones.ExcepcionSistema;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;

public class PantallaAltaArticulo {

	JFrame frmAltaArticulo;
	private JTextField codBarras_textField;
	private JTextField descripcion_textField;
	private JTextField presentacion_textField;
	private JTextField tamaño_textField;
	private JTextField unidad_textField;
	private JTextField precioVenta_textField;
	private JTextField cantFijaCompra_textField;
	private JTextField cantMaxUbi_textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PantallaAltaArticulo window = new PantallaAltaArticulo();
					window.frmAltaArticulo.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PantallaAltaArticulo() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAltaArticulo = new JFrame();
		frmAltaArticulo.setTitle("Alta Articulo");
		frmAltaArticulo.setBounds(100, 100, 450, 300);
		frmAltaArticulo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAltaArticulo.getContentPane().setLayout(null);
		
		codBarras_textField = new JTextField();
		codBarras_textField.setBounds(193, 11, 86, 20);
		frmAltaArticulo.getContentPane().add(codBarras_textField);
		codBarras_textField.setColumns(10);
		
		JLabel lblCodigoDeBarras = new JLabel("Codigo de Barras:");
		lblCodigoDeBarras.setBounds(10, 14, 99, 14);
		frmAltaArticulo.getContentPane().add(lblCodigoDeBarras);
		
		descripcion_textField = new JTextField();
		descripcion_textField.setBounds(193, 42, 86, 20);
		frmAltaArticulo.getContentPane().add(descripcion_textField);
		descripcion_textField.setColumns(10);
		
		presentacion_textField = new JTextField();
		presentacion_textField.setBounds(193, 73, 86, 20);
		frmAltaArticulo.getContentPane().add(presentacion_textField);
		presentacion_textField.setColumns(10);
		
		JLabel lblDescripcion = new JLabel("Descripcion: ");
		lblDescripcion.setBounds(10, 45, 99, 14);
		frmAltaArticulo.getContentPane().add(lblDescripcion);
		
		JLabel lblPresentacion = new JLabel("Presentacion: ");
		lblPresentacion.setBounds(10, 76, 99, 14);
		frmAltaArticulo.getContentPane().add(lblPresentacion);
		
		tamaño_textField = new JTextField();
		tamaño_textField.setBounds(193, 104, 86, 20);
		frmAltaArticulo.getContentPane().add(tamaño_textField);
		tamaño_textField.setColumns(10);
		
		unidad_textField = new JTextField();
		unidad_textField.setBounds(193, 135, 86, 20);
		frmAltaArticulo.getContentPane().add(unidad_textField);
		unidad_textField.setColumns(10);
		
		precioVenta_textField = new JTextField();
		precioVenta_textField.setBounds(193, 166, 86, 20);
		frmAltaArticulo.getContentPane().add(precioVenta_textField);
		precioVenta_textField.setColumns(10);
		
		JLabel lblTamao = new JLabel("Tama\u00F1o: ");
		lblTamao.setBounds(10, 107, 67, 14);
		frmAltaArticulo.getContentPane().add(lblTamao);
		
		JLabel lblUnidad = new JLabel("Unidad:");
		lblUnidad.setBounds(10, 138, 67, 14);
		frmAltaArticulo.getContentPane().add(lblUnidad);
		
		JLabel lblPrecioVta = new JLabel("Precio Venta: ");
		lblPrecioVta.setBounds(10, 169, 77, 14);
		frmAltaArticulo.getContentPane().add(lblPrecioVta);
		
		JLabel lblNewLabel = new JLabel("Cantidad Fija Compra: ");
		lblNewLabel.setBounds(10, 200, 122, 14);
		frmAltaArticulo.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Cantidad m\u00E1xima por Ubicaci\u00F3n:");
		lblNewLabel_1.setBounds(10, 236, 163, 14);
		frmAltaArticulo.getContentPane().add(lblNewLabel_1);
		
		cantFijaCompra_textField = new JTextField();
		cantFijaCompra_textField.setBounds(193, 197, 86, 20);
		frmAltaArticulo.getContentPane().add(cantFijaCompra_textField);
		cantFijaCompra_textField.setColumns(10);
		
		cantMaxUbi_textField = new JTextField();
		cantMaxUbi_textField.setBounds(193, 233, 86, 20);
		frmAltaArticulo.getContentPane().add(cantMaxUbi_textField);
		cantMaxUbi_textField.setColumns(10);
		
		JButton btnCrear = new JButton("Crear");
		btnCrear.setBounds(335, 232, 89, 23);
		btnCrear.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					SistemaBD bd= new SistemaBD();
					ArticuloDTO art= new ArticuloDTO();
					art.setDescripcion(descripcion_textField.getText());
					art.setPresentacion(presentacion_textField.getText());
					art.setCodigoBarras(codBarras_textField.getText());
					art.setCantFijaCompra(Integer.parseInt(cantFijaCompra_textField.getText()));
					art.setCantMaxUbicacion(Integer.parseInt(cantMaxUbi_textField.getText()));
					art.setPrecioVta(Float.parseFloat(precioVenta_textField.getText()));
					art.setTamaño(Integer.parseInt(tamaño_textField.getText()));
					art.setUnidad(unidad_textField.getText());
					bd.altaArticulo(art);
					JOptionPane.showMessageDialog(frmAltaArticulo, "Su Articulo ha sido creado con éxito!");
					Menú m= new Menú();
					m.frmMenu.setVisible(true);
					m.frmMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frmAltaArticulo.dispose();
				} catch (ExcepcionComunicacion ev) {
					System.out.println(ev.getMensaje());
				} catch (ExcepcionSistema ev) {
					System.out.println(ev.getMensaje());
				}
				
			}
		});
		frmAltaArticulo.getContentPane().add(btnCrear);
	}
}
