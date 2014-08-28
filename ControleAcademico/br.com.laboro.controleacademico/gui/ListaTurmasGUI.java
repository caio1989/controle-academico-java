package gui;

import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;

import java.awt.SystemColor;
import java.awt.Color;
import javax.swing.JComboBox;

public class ListaTurmasGUI extends JPanel {

	
	private JTable table;
	
	
	
	public ListaTurmasGUI(){
		setBackground(new Color(240, 248, 255));
		setBorder(new TitledBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null), "Lista de turmas", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(240, 248, 255)));
	
		
		setBounds(50 ,30 ,852, 454);
		
		setLayout(null);
		
		JPanel paneTurma = new JPanel();
		paneTurma.setBackground(new Color(240, 248, 255));
		paneTurma.setBorder(new TitledBorder(null, "Dados da turma", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 128)));
		paneTurma.setBounds(37, 225, 775, 196);
		add(paneTurma);
		paneTurma.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 24, 719, 146);
		paneTurma.add(scrollPane);
		
		JTable table_1 = new JTable();
		scrollPane.setViewportView(table_1);
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"Nome da turma", "Sala", "Turno", "Ano letivo"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, Integer.class, String.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table_1.getColumnModel().getColumn(0).setPreferredWidth(228);
		table_1.getColumnModel().getColumn(1).setPreferredWidth(45);
		table_1.getColumnModel().getColumn(2).setPreferredWidth(181);
		table_1.getColumnModel().getColumn(3).setPreferredWidth(141);
		
		JPanel nomeTurma = new JPanel();
		nomeTurma.setBackground(new Color(240, 248, 255));
		nomeTurma.setBorder(new TitledBorder(null, "Digite o nome da turma cadastrada", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		nomeTurma.setBounds(533, 91, 238, 76);
		add(nomeTurma);
		nomeTurma.setLayout(null);
		
		JComboBox cbTurma = new JComboBox();
		cbTurma.setEditable(true);
		cbTurma.setBounds(64, 27, 103, 25);
		nomeTurma.add(cbTurma);

	}

}
