package gui;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.CloseAction;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Closeable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.UIManager;

public class DeletarAlunoGUI extends JPanel {

	/**
	 * Create the panel.
	 */
	public DeletarAlunoGUI() {
		setBackground(new Color(240, 248, 255));
		setBorder(new TitledBorder(null, "Deletar Aluno", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(240, 248, 255));
		panel.setBorder(new TitledBorder(null, "Digite o nome do Aluno", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(45, 87, 617, 119);
		add(panel);
		panel.setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setEditable(true);
		comboBox.setBounds(33, 53, 541, 25);
		panel.add(comboBox);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setForeground(UIManager.getColor("Button.foreground"));
		btnCancelar.setBackground(UIManager.getColor("Button.background"));
		btnCancelar.setBounds(555, 278, 103, 23);
		add(btnCancelar);
		
		JButton btnDeletar = new JButton("Deletar");
		btnDeletar.setForeground(UIManager.getColor("Button.foreground"));
		btnDeletar.setBackground(UIManager.getColor("Button.background"));
		btnDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnDeletar.setBounds(454, 278, 89, 23);
		add(btnDeletar);

	}
}
