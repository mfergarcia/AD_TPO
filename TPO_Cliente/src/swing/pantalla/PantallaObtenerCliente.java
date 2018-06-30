package swing.pantalla;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;

import delegados.SistemaBD;
import excepciones.ExcepcionComunicacion;
import excepciones.ExcepcionSistema;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class PantallaObtenerCliente {

	JFrame BuscarCliente;
	private JTextField textField_id;

	public PantallaObtenerCliente() {
		initialize();
	}

	private void initialize() {
		BuscarCliente = new JFrame();
		BuscarCliente.setTitle("Buscar Cliente");
		BuscarCliente.setBounds(100, 100, 450, 300);
		BuscarCliente.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		BuscarCliente.getContentPane().setLayout(null);
		
		JButton btnSiguiente = new JButton("Siguiente");
		btnSiguiente.setBounds(335, 227, 89, 23);
		btnSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					SistemaBD bd= new SistemaBD();
					char tipo=bd.obtenerTipoCliente(Integer.parseInt(textField_id.getText()));
					if(tipo == 'E')
					{
						PantallaModificarClienteEmpresa pm= new PantallaModificarClienteEmpresa(Integer.parseInt(textField_id.getText()));
						pm.frmModificacionClienteempresa.setVisible(true);
						pm.frmModificacionClienteempresa.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					}else if(tipo== 'P') {
						PantallaModificarClientePersona pp= new PantallaModificarClientePersona(Integer.parseInt(textField_id.getText()));
						pp.frmModificarClientepersona.setVisible(true);
						pp.frmModificarClientepersona.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					}
					BuscarCliente.dispose();
				} catch (ExcepcionComunicacion ev) {
					System.out.println(ev.getMensaje());
				} catch (ExcepcionSistema ev) {
					System.out.println(ev.getMensaje());
				}
			}
		});
		BuscarCliente.getContentPane().add(btnSiguiente);
		
		textField_id = new JTextField();
		textField_id.setBounds(165, 121, 86, 20);
		BuscarCliente.getContentPane().add(textField_id);
		textField_id.setColumns(10);
		
		JLabel lblIngreseIdDel = new JLabel("Ingrese Id del Cliente");
		lblIngreseIdDel.setBounds(157, 65, 186, 14);
		BuscarCliente.getContentPane().add(lblIngreseIdDel);
	}

}
