package gui;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.ImageIcon;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import componentes.ButtonTabComponent;

import javax.swing.JTabbedPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;



import javax.swing.border.LineBorder;
import java.awt.Color;
import java.sql.SQLException;



public class DisciplinasGUI extends JInternalFrame {
	
	
	private static final long serialVersionUID = 1L;




		public void iniciarJanela() throws ClassNotFoundException,
			InstantiationException {// tratando o inicio da JInternalFrame

			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						DisciplinasGUI frame = new DisciplinasGUI();
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					} catch (Throwable e) {

						e.printStackTrace();
					}
				}
			});
		}
	
	
	
	
	public DisciplinasGUI() {
		setClosable(true);
		setResizable(true);
		setMaximizable(true);
		setIconifiable(true);
		setTitle("Disciplinas ");
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBorder(new LineBorder(new Color(102, 205, 170), 5));
		setFrameIcon(new ImageIcon("/home/caio/icones/materias.png"));
		getContentPane().setLayout(null);
		setBounds(100, 100, 895, 543);
		final JTabbedPane tbDisciplina = new JTabbedPane(JTabbedPane.TOP);
		tbDisciplina.setBounds(12, 12, 854, 466);
		getContentPane().add(tbDisciplina);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnCadastrarAlunoPor = new JMenu("Cadastrar aluno por Materia");
		mnCadastrarAlunoPor.addMouseListener(new MouseAdapter() {
	
			public void mouseClicked(MouseEvent e) {
				NotasAlunosGUI aluno;
				try {
					aluno = new NotasAlunosGUI();
					tbDisciplina.add("Cadastro de Notas", aluno);
					tbDisciplina.setSelectedComponent(aluno);  
					int i = tbDisciplina.getSelectedIndex();  
					tbDisciplina.setTabComponentAt(i, new ButtonTabComponent(tbDisciplina));  
					setContentPane(tbDisciplina);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		menuBar.add(mnCadastrarAlunoPor);
		
		JMenu mnAdcionarNovaMateria = new JMenu("Adcionar nova Materia");
		mnAdcionarNovaMateria.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AdcionarDisciplinaGUI aluno;
				aluno = new AdcionarDisciplinaGUI();
				tbDisciplina.add("Adcionar nova disciplina", aluno);
				tbDisciplina.setSelectedComponent(aluno);  
				int i = tbDisciplina.getSelectedIndex();  
				tbDisciplina.setTabComponentAt(i, new ButtonTabComponent(tbDisciplina));  
				setContentPane(tbDisciplina);
			}
		});
		
		menuBar.add(mnAdcionarNovaMateria);
		
	}
	
	
}
