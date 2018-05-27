package swing.test;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.GridLayout;

public class menuPrincipal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2869186589040288841L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					menuPrincipal frame = new menuPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public menuPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 595, 856);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnArchivo = new JMenu("Archivo");
		menuBar.add(mnArchivo);
		
		JMenuItem mntmAbrir = new JMenuItem("Abrir");
		mnArchivo.add(mntmAbrir);
		
		JMenuItem mntmCerrar = new JMenuItem("Cerrar");
		mnArchivo.add(mntmCerrar);
		
		JMenu mnPedidos = new JMenu("Pedidos");
		menuBar.add(mnPedidos);
		
		JMenuItem mntmCrearPedido = new JMenuItem("Crear Pedido");
		mnPedidos.add(mntmCrearPedido);
		
		JMenuItem mntmBuscarPedidos = new JMenuItem("Editar Pedido");
		mnPedidos.add(mntmBuscarPedidos);
		
		JMenu mnBuscarPedidos = new JMenu("Buscar Pedidos");
		mnPedidos.add(mnBuscarPedidos);
		
		JMenuItem mntmPendientes = new JMenuItem("Pendientes");
		mnBuscarPedidos.add(mntmPendientes);
		
		JMenuItem mntmCerrados = new JMenuItem("Cerrados");
		mnBuscarPedidos.add(mntmCerrados);
		
		JMenu mnCtacte = new JMenu("CtaCte");
		menuBar.add(mnCtacte);
		
		JMenuItem mntmUltimosMov = new JMenuItem("Ultimos Mov.");
		mnCtacte.add(mntmUltimosMov);
		
		JMenuItem mntmVerificarEstado = new JMenuItem("Verificar Estado");
		mnCtacte.add(mntmVerificarEstado);
		getContentPane().setLayout(new GridLayout(0, 2, 0, 0));
		
		miCuentaPanel1 miCuentaPanel1_ = new miCuentaPanel1();
		getContentPane().add(miCuentaPanel1_);
		
		/*Resultados resultados = new Resultados();
		getContentPane().add(resultados);*/
	}
}