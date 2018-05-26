package swing;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import java.awt.Window.Type;

public class principal extends JFrame {

	private JPanel panelLogin;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					principal frame = new principal();
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
	public principal() {
		setResizable(false);
		setForeground(new Color(230, 230, 250));
		setTitle("Grupo 10 Login");
		setIconImage(Toolkit.getDefaultToolkit().getImage(principal.class.getResource("/swing/icon/login (2).png")));
		setFont(new Font("Arial Unicode MS", Font.PLAIN, 12));
		setBounds(100, 100, 474, 361);
		panelLogin = new JPanel();
		panelLogin.setToolTipText("Gupo 10 Ingreso al sistema");
		panelLogin.setLayout(new BorderLayout(0, 0));
		setContentPane(panelLogin);
		
		inicio inicio_ = new inicio();
		panelLogin.add(inicio_, BorderLayout.CENTER);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{panelLogin, inicio_}));
	}

}
