package gui;
import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.ImageIcon;

import java.awt.CardLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.LineBorder;

import classes.backend.Professor;
import dao.ProfessorDAO;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.BevelBorder;

import java.awt.SystemColor;
import java.sql.SQLException;

import javax.swing.JFormattedTextField;
import javax.swing.JTabbedPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.event.MenuListener;
import javax.swing.event.MenuEvent;

import componentes.ButtonTabComponent;
import javax.swing.event.MenuKeyListener;
import javax.swing.event.MenuKeyEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ProfessorGUI extends JInternalFrame {

	public void iniciarJanela() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProfessorGUI frame = new ProfessorGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ProfessorGUI() throws ClassNotFoundException, InstantiationException,
			IllegalAccessException {
		setFrameIcon(new ImageIcon("/home/caio/icones/professor.png"));
		

		setBorder(new LineBorder(new Color(128, 0, 0), 5));
		setClosable(true);
		setDefaultLocale(null);
		setIconifiable(true);
		setTitle("Professor");
				
		setBounds(2, 2, 1014, 637);
		getContentPane().setLayout(null);
		
		final JTabbedPane tPane = new JTabbedPane(JTabbedPane.TOP);
		tPane.setBounds(12, 12, 980, 570);
		getContentPane().add(tPane);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnCadastrar = new JMenu("Cadastrar");
		mnCadastrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CadastroProfGUI prof;
				try {
					prof = new CadastroProfGUI();
					tPane.add("Cadastro de Professores", prof);
					tPane.setSelectedComponent(prof);  // setando o painel para adcionalo
					int i = tPane.getSelectedIndex();  // definindo variavel do tipo inteiro como indice do tpane
					tPane.setTabComponentAt(i, new ButtonTabComponent(tPane));   // contruindo a borda
					setContentPane(tPane);//adcionando o painel
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			
			}
		});
		
		menuBar.add(mnCadastrar);
		JMenu mnAlterar = new JMenu("Alterar");
		mnAlterar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AlterarProfessorGUI prof;
				try {
					prof = new AlterarProfessorGUI();
					tPane.add("Alterar dados do professor", prof);
					tPane.setSelectedComponent(prof);  
					int i = tPane.getSelectedIndex();  
					tPane.setTabComponentAt(i, new ButtonTabComponent(tPane));  
					setContentPane(tPane);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		menuBar.add(mnAlterar);
			

	}
}
