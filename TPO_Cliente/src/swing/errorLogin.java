package swing;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Toolkit;

public class errorLogin extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					errorLogin frame = new errorLogin();
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
	public errorLogin() {
		setTitle("ERROR");
		setIconImage(Toolkit.getDefaultToolkit().getImage(errorLogin.class.getResource("/javax/swing/plaf/metal/icons/Error.gif")));
		setBounds(100, 100, 468, 190);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLabel errorInicio = new JLabel("El Usuario o Contrase\u00F1a Ingresada no son correstas");
		errorInicio.setFont(new Font("Cambria Math", Font.PLAIN, 17));
		errorInicio.setIcon(new ImageIcon(errorLogin.class.getResource("/javax/swing/plaf/metal/icons/Error.gif")));
		contentPane.add(errorInicio, BorderLayout.CENTER);
	}

}
