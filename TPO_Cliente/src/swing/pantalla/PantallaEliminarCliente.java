package swing.pantalla;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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

public class PantallaEliminarCliente {

	JFrame frmEliminarCliente;
	private JTextField textField;


	public PantallaEliminarCliente() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmEliminarCliente = new JFrame();
		frmEliminarCliente.setTitle("Eliminar Cliente");
		frmEliminarCliente.setBounds(100, 100, 450, 300);
		frmEliminarCliente.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmEliminarCliente.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(163, 109, 86, 20);
		frmEliminarCliente.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblIngreseId = new JLabel("Ingrese ID:");
		lblIngreseId.setBounds(47, 112, 77, 14);
		frmEliminarCliente.getContentPane().add(lblIngreseId);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(335, 227, 89, 23);
		btnEliminar.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(textField.getText()!=null && textField.getText()!=""){
					try {
						SistemaBD bd= new SistemaBD();
						bd.bajaCliente(Integer.parseInt(textField.getText()));
						JOptionPane.showMessageDialog(frmEliminarCliente, "Su Cliente ha sido eliminado!");
						frmEliminarCliente.dispose();
					} catch (ExcepcionComunicacion ev) {
						System.out.println(ev.getMensaje());
					} catch (ExcepcionSistema ev) {
						System.out.println(ev.getMensaje());
					}
				}
			}
		});
		frmEliminarCliente.getContentPane().add(btnEliminar);
	}
}
