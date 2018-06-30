package swing.pantalla;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

public class PantallaEliminarArticulo {

	JFrame frmEliminarArticulo;
	private JTextField textField_CodBarras;

	public PantallaEliminarArticulo() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmEliminarArticulo = new JFrame();
		frmEliminarArticulo.setTitle("Elimina Articulo");
		frmEliminarArticulo.setBounds(100, 100, 450, 300);
		frmEliminarArticulo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmEliminarArticulo.getContentPane().setLayout(null);
		
		textField_CodBarras = new JTextField();
		textField_CodBarras.setBounds(160, 102, 86, 20);
		frmEliminarArticulo.getContentPane().add(textField_CodBarras);
		textField_CodBarras.setColumns(10);
		
		JLabel lblIngreseCodigoDe = new JLabel("Ingrese Codigo de Barras:");
		lblIngreseCodigoDe.setBounds(23, 105, 127, 14);
		frmEliminarArticulo.getContentPane().add(lblIngreseCodigoDe);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					SistemaBD bd= new SistemaBD();
					bd.bajaArticulo(textField_CodBarras.getText());
					Menú m= new Menú();
					m.frmMenu.setVisible(true);
					m.frmMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frmEliminarArticulo.dispose();
				} catch (ExcepcionComunicacion ev) {
					System.out.println(ev.getMensaje());
				} catch (ExcepcionSistema ev) {
					System.out.println(ev.getMensaje());
				}
			}
		});
		btnEliminar.setBounds(335, 227, 89, 23);
		frmEliminarArticulo.getContentPane().add(btnEliminar);
	}

}
