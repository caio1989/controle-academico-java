package gui;
import java.awt.Color;
import java.awt.EventQueue;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;

import javax.swing.UnsupportedLookAndFeelException;

import javax.swing.border.LineBorder;




import javax.swing.JMenuBar;
import javax.swing.JMenu;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTabbedPane;

import componentes.ButtonTabComponent;

public class AlunoGUI extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	public void iniciarJanela() throws ClassNotFoundException,
			InstantiationException {// tratando o inicio da JInternalFrame

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AlunoGUI frame = new AlunoGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				} catch (Throwable e) {

					e.printStackTrace();
				}
			}
		});
	}

	public AlunoGUI() throws ClassNotFoundException, Throwable,
			IllegalAccessException, UnsupportedLookAndFeelException {
		setFrameIcon(new ImageIcon("/home/caio/icones/aluno2.png"));
		setMaximizable(true);
		setLocation(10, 10);
		setIconifiable(true);
		setForeground(Color.WHITE);
		setBorder(new LineBorder(new Color(102, 205, 170), 5));
		setClosable(true);
		
		setTitle("Aluno");
		setBounds(100, 100, 998, 518);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		final JTabbedPane tbAluno = new JTabbedPane(JTabbedPane.TOP);
		tbAluno.setBounds(10, 11, 298, 325);
		
		
		JMenu mnNovoAluno = new JMenu("Novo Aluno");
		mnNovoAluno.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				
				try {
					CadastrarAlunoGUI aluno;
					aluno = new CadastrarAlunoGUI();
					tbAluno.add("Cadastro de Alunos", aluno);
					tbAluno.setSelectedComponent(aluno);  
					int i = tbAluno.getSelectedIndex();  
					tbAluno.setTabComponentAt(i, new ButtonTabComponent(tbAluno));  
					setContentPane(tbAluno);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		
		menuBar.add(mnNovoAluno);
		
		JMenu mnDeletar = new JMenu("Deletar");
		mnDeletar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DeletarAlunoGUI aluno = new DeletarAlunoGUI();
				tbAluno.add("Deletar Alunos", aluno);
				tbAluno.setSelectedComponent(aluno);  
				int i = tbAluno.getSelectedIndex();  
				tbAluno.setTabComponentAt(i, new ButtonTabComponent(tbAluno));  
				setContentPane(tbAluno);
				
			}
		});
		menuBar.add(mnDeletar);
		
		JMenu mnAtualizar = new JMenu("Alterar");
		mnAtualizar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AlterarAlunoGUI aluno;
				try {
					aluno = new AlterarAlunoGUI();
					tbAluno.add("Alterar dados do  aluno", aluno);
					tbAluno.setSelectedComponent(aluno);  
					int i = tbAluno.getSelectedIndex();  
					tbAluno.setTabComponentAt(i, new ButtonTabComponent(tbAluno));  
					setContentPane(tbAluno);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
	
		
		menuBar.add(mnAtualizar);
		
		
		
	}
}
