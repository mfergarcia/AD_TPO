package swing;

import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class miCuentaPanel1 extends JPanel {

	/**
	 * Create the panel.
	 */
	public miCuentaPanel1() {
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		
		JLabel lblMiCuenta = new JLabel("Mi Cuenta");
		lblMiCuenta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.out.println("al hacer click en este boton deberia devolver todos los datos del cliente en la pantalla resultados");
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, lblMiCuenta, 21, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblMiCuenta, 35, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, lblMiCuenta, -601, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.EAST, lblMiCuenta, 223, SpringLayout.WEST, this);
		lblMiCuenta.setIcon(new ImageIcon(miCuentaPanel1.class.getResource("/swing/icon/home.png")));
		lblMiCuenta.setFont(new Font("Tahoma", Font.PLAIN, 17));
		add(lblMiCuenta);
		
		JLabel lblPedidos = new JLabel("Pedidos");
		lblPedidos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("al hacer click en este boton deberia devolver los pedidos en la pantalla resultados");
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, lblPedidos, 0, SpringLayout.SOUTH, lblMiCuenta);
		springLayout.putConstraint(SpringLayout.WEST, lblPedidos, 35, SpringLayout.WEST, this);
		lblPedidos.setIcon(new ImageIcon(miCuentaPanel1.class.getResource("/swing/icon/comprar.png")));
		lblPedidos.setFont(new Font("Tahoma", Font.PLAIN, 17));
		add(lblPedidos);
		
		JLabel lblCtacte = new JLabel("CtaCte");
		lblCtacte.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("al hacer click en este boton deberia devolver todos los datos del cliente en la pantalla resultados");
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, lblCtacte, 25, SpringLayout.SOUTH, lblPedidos);
		springLayout.putConstraint(SpringLayout.WEST, lblCtacte, 0, SpringLayout.WEST, lblMiCuenta);
		springLayout.putConstraint(SpringLayout.EAST, lblCtacte, 0, SpringLayout.EAST, lblMiCuenta);
		lblCtacte.setIcon(new ImageIcon(miCuentaPanel1.class.getResource("/swing/icon/ctacte.png")));
		lblCtacte.setFont(new Font("Tahoma", Font.PLAIN, 17));
		add(lblCtacte);
		
		JLabel lblSoporte = new JLabel("Soporte");
		lblSoporte.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("al hacer click en este boton deberia devolver todos los datos del cliente en la pantalla resultados");
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, lblSoporte, 16, SpringLayout.SOUTH, lblCtacte);
		springLayout.putConstraint(SpringLayout.WEST, lblSoporte, 0, SpringLayout.WEST, lblMiCuenta);
		springLayout.putConstraint(SpringLayout.EAST, lblSoporte, 0, SpringLayout.EAST, lblMiCuenta);
		lblSoporte.setIcon(new ImageIcon(miCuentaPanel1.class.getResource("/swing/icon/settings.png")));
		lblSoporte.setFont(new Font("Tahoma", Font.PLAIN, 17));
		add(lblSoporte);
		
		JLabel lblBuscar = new JLabel("Buscar");
		lblBuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("al hacer click en este boton deberia devolver todos los datos del cliente en la pantalla resultados");
			}
			
		});
		springLayout.putConstraint(SpringLayout.NORTH, lblBuscar, 19, SpringLayout.SOUTH, lblSoporte);
		springLayout.putConstraint(SpringLayout.WEST, lblBuscar, 0, SpringLayout.WEST, lblMiCuenta);
		lblBuscar.setIcon(new ImageIcon(miCuentaPanel1.class.getResource("/swing/icon/buscar.png")));
		lblBuscar.setFont(new Font("Tahoma", Font.PLAIN, 17));
		add(lblBuscar);
		
		JLabel lblNewLabel = new JLabel("Contacto");
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("al hacer click en este boton deberia devolver todos los datos del cliente en la pantalla resultados");
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel, 15, SpringLayout.SOUTH, lblBuscar);
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel, 37, SpringLayout.WEST, this);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel.setIcon(new ImageIcon(miCuentaPanel1.class.getResource("/swing/icon/contacto.png")));
		add(lblNewLabel);

	}

}
