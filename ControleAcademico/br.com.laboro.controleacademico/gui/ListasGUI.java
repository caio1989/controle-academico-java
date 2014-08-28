package gui;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;

import javax.swing.JInternalFrame;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.TitledBorder;
import javax.swing.border.MatteBorder;

import java.awt.Color;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JScrollBar;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.LineBorder;
import javax.swing.border.BevelBorder;
import javax.swing.event.AncestorListener;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.Font;
import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JComboBox;





import componentes.ButtonTabComponent;

import classes.backend.Aluno;
import dao.AlunoDAO;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JFormattedTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentListener;

import javax.swing.JLayeredPane;

import org.omg.CORBA.ARG_IN;

import javax.swing.*;  

import java.awt.*;  
import java.awt.event.ActionListener;  
import java.awt.event.ActionEvent;  
import java.awt.event.InputEvent;  
import java.awt.event.KeyEvent; 

public class ListasGUI extends JInternalFrame {

	public void iniciarJanela() {
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListasGUI frame = new ListasGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public ListasGUI() throws SQLException {
			
		
		final JTabbedPane tbSelect = new JTabbedPane(JTabbedPane.TOP);
		tbSelect.setFocusable(true);
		tbSelect.setBackground(Color.LIGHT_GRAY);
		tbSelect.setBounds(10, 33, 950, 490);
		
		
		setBorder(new LineBorder(new Color(0, 0, 128), 5));

		getContentPane().setBackground(new Color(192, 192, 192));
		setClosable(true);
		setIconifiable(true);
		setTitle("Lista");
		setBounds(50, 50, 1031, 549);
		getContentPane().setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 966, 21);
		setJMenuBar(menuBar);
		
		JMenu mnEscolhaUmaLista = new JMenu("Escolha uma lista");
		menuBar.add(mnEscolhaUmaLista);
		
		JMenuItem mntmProfessor = new JMenuItem("Professor");
		mntmProfessor.setIcon(new ImageIcon("/home/caio/icones/professor.png"));
		mntmProfessor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setContentPane(tbSelect);
				ListaProfessoresGUI prof;
				try {
					prof = new ListaProfessoresGUI();
					tbSelect.addTab("Lista de professores", prof);
					tbSelect.setSelectedComponent(prof);  
					int i = tbSelect.getSelectedIndex();  
					tbSelect.setTabComponentAt(i, new ButtonTabComponent(tbSelect));  
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			
			}
		});
		mnEscolhaUmaLista.add(mntmProfessor);
		
		
		JMenuItem mntmAluno = new JMenuItem("Aluno");
		mntmAluno.setIcon(new ImageIcon("/home/caio/icones/aluno2.png"));
		mntmAluno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setContentPane(tbSelect);
				 ListaAlunoGUI alunos;
				try {
					alunos = new ListaAlunoGUI();
					tbSelect.addTab("Lista de alunos", alunos);
					tbSelect.setSelectedComponent(alunos);
					int i = tbSelect.getSelectedIndex();  
					tbSelect.setTabComponentAt(i, new ButtonTabComponent(tbSelect)); 
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				 
			
				
							
			}
		});
		mnEscolhaUmaLista.add(mntmAluno);
		
		JMenuItem mntmTurmas = new JMenuItem("Turmas");
		mntmTurmas.setIcon(new ImageIcon("/home/caio/icones/turmas.png"));
		mntmTurmas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setContentPane(tbSelect);

				
				ListaTurmasGUI turma = new ListaTurmasGUI();
				tbSelect.addTab("Lista de turmas", turma );
				tbSelect.setSelectedComponent(turma); 
				int i = tbSelect.getSelectedIndex();  
				tbSelect.setTabComponentAt(i, new ButtonTabComponent(tbSelect));  
		
		
			
			}
		});
		mnEscolhaUmaLista.add(mntmTurmas);
		
	}
}
