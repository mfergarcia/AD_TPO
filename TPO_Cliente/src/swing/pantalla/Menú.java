package swing.pantalla;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JButton;

public class Menú {

	JFrame frmMenu;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menú window = new Menú();
					window.frmMenu.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Menú() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMenu = new JFrame();
		frmMenu.setTitle("Men\u00FA");
		frmMenu.setBounds(100, 100, 450, 300);
		frmMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMenu.getContentPane().setLayout(null);
		
		JButton btnCrearCliente = new JButton("Crear Cliente");
		btnCrearCliente.setBounds(10, 11, 110, 23);
		btnCrearCliente.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				PantallaAltaCliente pa= new PantallaAltaCliente();
				pa.frmAltaCliente.setVisible(true);
				pa.frmAltaCliente.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frmMenu.dispose();
			}
		});
		frmMenu.getContentPane().add(btnCrearCliente);
		
		JButton btnModificarCliente = new JButton("Modificar Cliente");
		btnModificarCliente.setBounds(160, 11, 111, 23);
		btnModificarCliente.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				PantallaObtenerCliente poe= new PantallaObtenerCliente();
				poe.BuscarCliente.setVisible(true);
				poe.BuscarCliente.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frmMenu.dispose();
			}
		});
		frmMenu.getContentPane().add(btnModificarCliente);
		
		JButton btnEliminarCliente = new JButton("Eliminar Cliente");
		btnEliminarCliente.setBounds(301, 11, 105, 23);
		btnEliminarCliente.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				PantallaEliminarCliente pec= new PantallaEliminarCliente();
				pec.frmEliminarCliente.setVisible(true);
				pec.frmEliminarCliente.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frmMenu.dispose();
			}
		});
		frmMenu.getContentPane().add(btnEliminarCliente);
		
		JButton btnAltaArticulo = new JButton("Alta Articulo");
		btnAltaArticulo.setBounds(10, 189, 110, 23);
		btnAltaArticulo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				PantallaAltaArticulo paa= new PantallaAltaArticulo();
				paa.frmAltaArticulo.setVisible(true);
				paa.frmAltaArticulo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frmMenu.dispose();
			}
		});
		frmMenu.getContentPane().add(btnAltaArticulo);
		
		JButton btnModificarArticulo = new JButton("Modificar Articulo");
		btnModificarArticulo.setBounds(148, 189, 123, 23);
		btnModificarArticulo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				PantallaModificarArticulo pme= new PantallaModificarArticulo();
				pme.frmModificarArticulo.setVisible(true);
				pme.frmModificarArticulo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frmMenu.dispose();
			}
		});
		frmMenu.getContentPane().add(btnModificarArticulo);
		
		JButton btnEliminarArticulo = new JButton("Eliminar Articulo");
		btnEliminarArticulo.setBounds(301, 189, 125, 23);
		btnEliminarArticulo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				PantallaEliminarArticulo pe=new PantallaEliminarArticulo();
				pe.frmEliminarArticulo.setVisible(true);
				pe.frmEliminarArticulo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frmMenu.dispose();
			}
		});
		frmMenu.getContentPane().add(btnEliminarArticulo);
	}

}
