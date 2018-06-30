package swing.pantalla;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import delegados.SistemaBD;
import dto.ArticuloDTO;
import dto.ClienteEmpresaDTO;
import dto.DireccionDTO;
import excepciones.ExcepcionComunicacion;
import excepciones.ExcepcionSistema;

import javax.swing.JButton;

public class PantallaModificarArticulo {

	JFrame frmModificarArticulo;
	private JTextField textField_Descripcion;
	private JTextField textField_Presentacion;
	private JTextField textField_Tamaño;
	private JTextField textField_Unidad;
	private JTextField textField_PrecioVenta;
	private JTextField textField_CantFijCompra;
	private JTextField textField_CantMaxUbi;
	private JTextField textField_estado;
	private JTextField textField_CodBarras;

	
	public PantallaModificarArticulo() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmModificarArticulo = new JFrame();
		frmModificarArticulo.setTitle("Modificar Articulo");
		frmModificarArticulo.setBounds(100, 100, 450, 300);
		frmModificarArticulo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmModificarArticulo.getContentPane().setLayout(null);
		
		JLabel lblDescripcin = new JLabel("Descripci\u00F3n:");
		lblDescripcin.setBounds(10, 11, 66, 14);
		frmModificarArticulo.getContentPane().add(lblDescripcin);
		
		JLabel lblPresentacin = new JLabel("Presentaci\u00F3n:");
		lblPresentacin.setBounds(10, 36, 66, 14);
		frmModificarArticulo.getContentPane().add(lblPresentacin);
		
		JLabel lblTamao = new JLabel("Tama\u00F1o:");
		lblTamao.setBounds(10, 61, 66, 14);
		frmModificarArticulo.getContentPane().add(lblTamao);
		
		JLabel lblUnidad = new JLabel("Unidad:");
		lblUnidad.setBounds(10, 86, 46, 14);
		frmModificarArticulo.getContentPane().add(lblUnidad);
		
		JLabel lblPrecioVenta = new JLabel("Precio Venta:");
		lblPrecioVenta.setBounds(10, 111, 66, 14);
		frmModificarArticulo.getContentPane().add(lblPrecioVenta);
		
		textField_Descripcion = new JTextField();
		textField_Descripcion.setBounds(176, 8, 86, 20);
		frmModificarArticulo.getContentPane().add(textField_Descripcion);
		textField_Descripcion.setColumns(10);
		
		textField_Presentacion = new JTextField();
		textField_Presentacion.setBounds(176, 33, 86, 20);
		frmModificarArticulo.getContentPane().add(textField_Presentacion);
		textField_Presentacion.setColumns(10);
		
		textField_Tamaño = new JTextField();
		textField_Tamaño.setBounds(176, 58, 86, 20);
		frmModificarArticulo.getContentPane().add(textField_Tamaño);
		textField_Tamaño.setColumns(10);
		
		textField_Unidad = new JTextField();
		textField_Unidad.setBounds(176, 83, 86, 20);
		frmModificarArticulo.getContentPane().add(textField_Unidad);
		textField_Unidad.setColumns(10);
		
		textField_PrecioVenta = new JTextField();
		textField_PrecioVenta.setBounds(176, 108, 86, 20);
		frmModificarArticulo.getContentPane().add(textField_PrecioVenta);
		textField_PrecioVenta.setColumns(10);
		
		JLabel lblCantidaFijaCompra = new JLabel("Cantidad fija compra:");
		lblCantidaFijaCompra.setBounds(10, 136, 107, 14);
		frmModificarArticulo.getContentPane().add(lblCantidaFijaCompra);
		
		textField_CantFijCompra = new JTextField();
		textField_CantFijCompra.setBounds(176, 133, 86, 20);
		frmModificarArticulo.getContentPane().add(textField_CantFijCompra);
		textField_CantFijCompra.setColumns(10);
		
		JLabel lblCantidadMaximaUbicacion = new JLabel("Cantidad maxima ubicacion:");
		lblCantidadMaximaUbicacion.setBounds(10, 161, 133, 14);
		frmModificarArticulo.getContentPane().add(lblCantidadMaximaUbicacion);
		
		JLabel lblEstado = new JLabel("Estado:");
		lblEstado.setBounds(10, 186, 46, 14);
		frmModificarArticulo.getContentPane().add(lblEstado);
		
		textField_CantMaxUbi = new JTextField();
		textField_CantMaxUbi.setBounds(176, 158, 86, 20);
		frmModificarArticulo.getContentPane().add(textField_CantMaxUbi);
		textField_CantMaxUbi.setColumns(10);
		
		textField_estado = new JTextField();
		textField_estado.setBounds(176, 183, 86, 20);
		frmModificarArticulo.getContentPane().add(textField_estado);
		textField_estado.setColumns(10);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			if(!textField_CodBarras.getText().equals("")) {
				try{
					SistemaBD bd = new SistemaBD();
					ArticuloDTO art= bd.obtenerArticulo(textField_CodBarras.getText());
					if(!textField_Descripcion.getText().equals(""))
						art.setDescripcion(textField_Descripcion.getText());
					if(!textField_Presentacion.getText().equals(""))
						art.setPresentacion(textField_Presentacion.getText());
					if(!textField_Tamaño.getText().equals("")){
						art.setTamaño(Integer.parseInt(textField_Tamaño.getText()));
					}
					if(!textField_Unidad.getText().equals("")) {
						art.setUnidad(textField_Unidad.getText());
					}
					if(!textField_PrecioVenta.getText().equals("")){
						art.setPrecioVta(Float.parseFloat(textField_PrecioVenta.getText()));
					}
					if(!textField_CantFijCompra.getText().equals("")){
						art.setCantFijaCompra(Integer.parseInt(textField_CantFijCompra.getText()));
					}
					if(!textField_CantMaxUbi.getText().equals(""))
						art.setCantMaxUbicacion(Integer.parseInt(textField_CantMaxUbi.getText()));
					if(!textField_estado.getText().equals(""))
						art.setEstado(textField_estado.getText().charAt(0));
					bd.modificarArticulo(art);
					JOptionPane.showMessageDialog(frmModificarArticulo, "Su Articulo ha sido modificado");
					Menú m= new Menú();
					m.frmMenu.setVisible(true);
					m.frmMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frmModificarArticulo.dispose();
				} catch (ExcepcionComunicacion es) {
					System.out.println(es.getMensaje());
				} catch (ExcepcionSistema es) {
					System.out.println(es.getMensaje());
				}
			}
		}
		});
		btnModificar.setBounds(335, 227, 89, 23);
		frmModificarArticulo.getContentPane().add(btnModificar);
		
		JLabel lblCodbarras = new JLabel("CodBarras: *");
		lblCodbarras.setBounds(10, 211, 86, 14);
		frmModificarArticulo.getContentPane().add(lblCodbarras);
		
		textField_CodBarras = new JTextField();
		textField_CodBarras.setBounds(176, 208, 86, 20);
		frmModificarArticulo.getContentPane().add(textField_CodBarras);
		textField_CodBarras.setColumns(10);
		
		JLabel lblCampoobligatorio = new JLabel("* CampoObligatorio");
		lblCampoobligatorio.setBounds(290, 11, 134, 14);
		frmModificarArticulo.getContentPane().add(lblCampoobligatorio);
	}
}
