package swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import java.awt.Color;
import javax.swing.border.LineBorder;
import java.awt.BorderLayout;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JSplitPane;
import javax.swing.JSeparator;
import java.awt.Label;
import java.awt.Panel;
import java.awt.FlowLayout;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;
import javax.swing.border.MatteBorder;
import java.awt.GridLayout;
import javax.swing.border.CompoundBorder;
import javax.swing.JPasswordField;
import java.awt.Font;
import javax.swing.Box;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JFrame;


import swing.menuPrincipal;
import swing.errorLogin;
import swing.principal;


public class inicio extends JPanel implements ActionListener {
	private JTextField usuario_textField;
	private JPasswordField clave_textPass;
	private Comprueba_pass mievento = new Comprueba_pass();

	/**
	 * Create the panel.
	 */
	public inicio() {
		setBackground(new Color(0, 255, 255));
		setLayout(null);
		
		//Creo el Jlabel USUARIO
		JLabel usuario_jlabel = new JLabel("Usuario");
		usuario_jlabel.setFont(new Font("Script MT Bold", Font.PLAIN, 30));
		usuario_jlabel.setBounds(81, 158, 108, 37);
		add(usuario_jlabel);
		
		//Creo el Jtext para ingresar el nombre de usuario
		usuario_textField = new JTextField();
		usuario_textField.setBounds(222, 164, 125, 28);
		add(usuario_textField);
		usuario_textField.setColumns(10);
		
		//Creo el Jlabel CLAVE
		JLabel clave_jlabel = new JLabel("Clave");
		clave_jlabel.setFont(new Font("Script MT Bold", Font.PLAIN, 30));
		clave_jlabel.setBounds(81, 202, 108, 53);
		add(clave_jlabel);
		
		//Creo el JPassword para ingresar la clave y que nose muestre la misma
		clave_textPass = new JPasswordField();
		clave_textPass.setBackground(Color.WHITE);
		clave_textPass.setToolTipText("");
		clave_textPass.setColumns(2);
		clave_textPass.setBounds(222, 215, 125, 28);
		add(clave_textPass);
		
		//verifico q los valores ingresados en la calve cumplan con los requrimientos minimos
		clave_textPass.getDocument().addDocumentListener(mievento);
		
		
		
		// creo la imagen de logeo, le paso la direccion desde donde tomarla
		//se debe cambiar a una hubicacion accesible
		JLabel lblIcon = new JLabel("");
		lblIcon.setIcon(new ImageIcon(inicio.class.getResource("/swing/icon/netlog3.png")));
		lblIcon.setBounds(145, 8, 152, 144);
		add(lblIcon);
		
		//Aqui se definen, el btn INGRESAR y las acciones al hacer click en el btn INICIAR
		JButton btnIngresar = new JButton("Ingresar");
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//controlo que el usuario ingresado y clave sean correactas,
				//lo deberia vericar contra la BD
				if(usuario_textField.getText().equals("Alex")&& clave_textPass.getText().equals("giusti728")){
					menuPrincipal marco=new menuPrincipal();
					marco.setVisible(true);
					marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					
					
				}else{
					errorLogin error= new errorLogin();
					error.setVisible(true);
					clave_textPass.setText("");
					usuario_textField.setText("");
					
				}
				
			}
		});
		btnIngresar.setBounds(81, 266, 114, 23);
		add(btnIngresar);
		
		
		//Aqui se definen, el btn Registrase y las acciones al hacer click en el btn 
		JButton btnRegistrase = new JButton("Registrase");
		btnRegistrase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setBackground(Color.CYAN);
			}
		});
		btnRegistrase.setBounds(233, 266, 114, 23);
		add(btnRegistrase);

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	//clase para controlar el pass
	private class Comprueba_pass implements DocumentListener{

		@Override
		public void changedUpdate(DocumentEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void insertUpdate(DocumentEvent arg0) {
			// TODO Auto-generated method stub
			char [] contrasena;
			contrasena = clave_textPass.getPassword();
			if (contrasena.length <8 || contrasena.length >12){
				clave_textPass.setBackground(Color.red);
			}else{
				clave_textPass.setBackground(Color.white);
			}
			
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			// TODO Auto-generated method stub
			
		}
	
	}
}
