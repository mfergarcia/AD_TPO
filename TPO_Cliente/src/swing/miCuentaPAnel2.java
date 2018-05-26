package swing;

import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.Color;

public class miCuentaPAnel2 extends JPanel {

	/**
	 * Create the panel.
	 */
	public miCuentaPAnel2() {
		setBackground(new Color(30, 144, 255));
		setForeground(new Color(30, 144, 255));
		setLayout(null);
		
		JButton btnS = new JButton("s");
		btnS.setForeground(new Color(30, 144, 255));
		btnS.setBackground(new Color(30, 144, 255));
		btnS.setIcon(new ImageIcon(miCuentaPAnel2.class.getResource("/swing/icon/menu.png")));
		btnS.setBounds(0, 0, 100, 67);
		add(btnS);

	}

}
