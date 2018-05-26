package swing;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;
import javax.swing.border.BevelBorder;

public class Resultados extends JPanel {
	private JTable table;

	/**
	 * Create the panel.
	 */
	public Resultados() {
		
		//LA idea es que los botones dentreo del miCuentaPanel1 guarden los resultados en este panel
		
		table = new JTable();
		table.setColumnSelectionAllowed(true);
		table.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		table.setToolTipText("test");
		table.setSurrendersFocusOnKeystroke(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"Valor", "Artiuclos ID", "Stock Disp"
			}
		));
		add(table);

	}

}
