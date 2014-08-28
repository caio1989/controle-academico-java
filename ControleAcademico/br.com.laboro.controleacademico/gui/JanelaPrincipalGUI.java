package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.UIManager;
import javax.swing.event.InternalFrameEvent;

import java.awt.event.MouseMotionAdapter;

/*Sistema idealizado e desenvolvido por Caio Cutrim, estudante.*/
public class JanelaPrincipalGUI extends JFrame {

	JMenuBar menuBar = new JMenuBar();
	TurmaGUI turma = new TurmaGUI();
	JMenu mnResultados = new JMenu("Resultados");
	JMenu mnLista = new JMenu("Listas");
	final JMenu mnCadastrar = new JMenu("Cadastrar");

	private static final long serialVersionUID = 1L;

	/*
	 * classe responsavel por gerenciar e manipular todas as janelas internas do
	 * sistema aqui criei um sistema com uma janela principal que cria outras
	 * janelas apartir dos eventos do menu, por favor, se voce nao tiver o
	 * minimo de conhecimento em java nao altere dessa linha para baixo
	 */
	public void iniciarJanela() throws SQLException {

		// tratando a excessoes do codigo ao iniciar

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					JanelaPrincipalGUI janela = new JanelaPrincipalGUI();
					janela.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public JanelaPrincipalGUI() throws SQLException, ClassNotFoundException,
			InstantiationException, IllegalAccessException {
		setUndecorated(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				"/home/caio/SISCAIcones/shortcut.png"));

		setResizable(false);
		getContentPane().setForeground(
				UIManager.getColor("InternalFrame.activeTitleForeground"));

		setBackground(new Color(224, 255, 255));
		getContentPane().setBackground(new Color(224, 255, 255));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setTitle("Controle Acad\u00EAmico");
		setSize(1366, 729);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		final JDesktopPane dPane = new JDesktopPane();
		dPane.setLocation(0, 0);
		dPane.setSize(1340, 709);
		dPane.setBorder(null);
		dPane.setBackground(Color.WHITE);
		setContentPane(dPane);

		JLabel lblNewLabel = new JLabel("");

		lblNewLabel.setIcon(new ImageIcon("/home/caio/icones/painel.png"));
		lblNewLabel.setBounds(12, 10, 1342, 661);
		dPane.add(lblNewLabel);

		// criando e adcionando a barra de menus

		menuBar.setBackground(UIManager.getColor("Menu.background"));
		menuBar.setVisible(true);
		setJMenuBar(menuBar);// para redimensionar automaticamente

		mnCadastrar.setIcon(new ImageIcon("/home/caio/icones/cadastrar.png"));
		mnCadastrar.setToolTipText("Cadastrar alunos, professores e etc...");
		mnCadastrar.setForeground(new Color(169, 169, 169));
		menuBar.add(mnCadastrar);

		final JMenuItem mntmTurmas = new JMenuItem("Turmas");
		mntmTurmas.setIcon(new ImageIcon("/home/caio/icones/turmas.png"));
		mntmTurmas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mnCadastrar.setEnabled(false);
				mnLista.setEnabled(false);
				mnResultados.setEnabled(false);
				dPane.add(turma);
				turma.show();
				turma.addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
					public void internalFrameClosed(
							javax.swing.event.InternalFrameEvent evt) {
						mnCadastrar.setEnabled(true);
						mnLista.setEnabled(true);
						mnResultados.setEnabled(true);
					}

					public void internalFrameActivated(InternalFrameEvent e) {
					}

					public void internalFrameClosing(InternalFrameEvent e) {
					}

					public void internalFrameDeactivated(InternalFrameEvent e) {
					}

					public void internalFrameDeiconified(InternalFrameEvent e) {
					}

					public void internalFrameIconified(InternalFrameEvent e) {
					}

					public void internalFrameOpened(InternalFrameEvent e) {
					}
				});

			}

		});

		mnCadastrar.add(mntmTurmas);

		JMenuItem mntmAlunos_1 = new JMenuItem("Alunos");
		mntmAlunos_1.setIcon(new ImageIcon("/home/caio/icones/aluno2.png"));
		mntmAlunos_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mnCadastrar.setEnabled(false);
				mnLista.setEnabled(false);
				mnResultados.setEnabled(false);
				try {
					AlunoGUI aluno = new AlunoGUI();
					aluno.iniciarJanela();
					aluno.addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
						public void internalFrameClosed(
								javax.swing.event.InternalFrameEvent evt) {
							mnCadastrar.setEnabled(true);
							mnLista.setEnabled(true);
							mnResultados.setEnabled(true);
						}
						public void internalFrameActivated(InternalFrameEvent e) {
						}
						public void internalFrameClosing(InternalFrameEvent e) {
						}
						public void internalFrameDeactivated(
								InternalFrameEvent e) {
						}
						public void internalFrameDeiconified(
								InternalFrameEvent e) {
						}
						public void internalFrameIconified(InternalFrameEvent e) {
						}
						public void internalFrameOpened(InternalFrameEvent e) {
						}
					});
					dPane.add(aluno);
					aluno.show();
				} catch (Throwable e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		mnCadastrar.add(mntmAlunos_1);

		JMenuItem mntmProfessores = new JMenuItem("Professores");
		mntmProfessores
				.setIcon(new ImageIcon("/home/caio/icones/professor.png"));
		mntmProfessores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					ProfessorGUI prof;
					prof = new ProfessorGUI();
					prof.iniciarJanela();
					mnCadastrar.setEnabled(false);
					mnResultados.setEnabled(false);
					mnLista.setEnabled(false);
					prof.addInternalFrameListener(new javax.swing.event.InternalFrameListener(){
						public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt)   {  
				               mnCadastrar.setEnabled(true);
				               mnLista.setEnabled(true);
								mnResultados.setEnabled(true);
				            }
							public void internalFrameActivated(InternalFrameEvent e) {
							}
							public void internalFrameClosing(InternalFrameEvent e) {
							}
							public void internalFrameDeactivated(InternalFrameEvent e) {	
							}
							public void internalFrameDeiconified(InternalFrameEvent e) {	
							}
							public void internalFrameIconified(InternalFrameEvent e) {
							}
							public void internalFrameOpened(InternalFrameEvent e) {	
							}
						});
					dPane.add(prof);
					prof.show();
				} catch (ClassNotFoundException | InstantiationException
						| IllegalAccessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		mnCadastrar.add(mntmProfessores);

		JMenuItem mntmMaterias = new JMenuItem("Disciplinas");
		mntmMaterias.setIcon(new ImageIcon("/home/caio/icones/materias.png"));
		mntmMaterias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					DisciplinasGUI disciplina;
					disciplina = new DisciplinasGUI();
					disciplina.iniciarJanela();
					mnCadastrar.setEnabled(false);
					mnResultados.setEnabled(false);
					mnLista.setEnabled(false);
					disciplina.addInternalFrameListener(new javax.swing.event.InternalFrameListener(){
					public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt)   {  
		               mnCadastrar.setEnabled(true);
		               mnLista.setEnabled(true);
						mnResultados.setEnabled(true);
		            }
					public void internalFrameActivated(InternalFrameEvent e) {
					}
					public void internalFrameClosing(InternalFrameEvent e) {
					}
					public void internalFrameDeactivated(InternalFrameEvent e) {	
					}
					public void internalFrameDeiconified(InternalFrameEvent e) {	
					}
					public void internalFrameIconified(InternalFrameEvent e) {
					}
					public void internalFrameOpened(InternalFrameEvent e) {	
					}
				});
					dPane.add(disciplina);
					disciplina.show();
				} catch (ClassNotFoundException | InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		mnCadastrar.add(mntmMaterias);
		mnResultados.setIcon(new ImageIcon("/home/caio/icones/resultado.png"));
		mnResultados.setToolTipText("Confira a situação dos alunos");
		mnResultados.setForeground(new Color(169, 169, 169));
		menuBar.add(mnResultados);

		JMenuItem mntmAlunos = new JMenuItem("Alunos");
		mntmAlunos.setIcon(new ImageIcon("/home/caio/icones/aluno2.png"));
		mntmAlunos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ResultAlunoGUI janela_result;
				
				try {
					janela_result = new ResultAlunoGUI();
					janela_result.iniciarJanela();
					mnCadastrar.setEnabled(false);
					mnResultados.setEnabled(false);
					mnLista.setEnabled(false);
					janela_result.addInternalFrameListener(new javax.swing.event.InternalFrameListener(){
					public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt)   {  
		               mnCadastrar.setEnabled(true);
		               mnLista.setEnabled(true);
						mnResultados.setEnabled(true);
		            }
					public void internalFrameActivated(InternalFrameEvent e) {
					}
					public void internalFrameClosing(InternalFrameEvent e) {
					}
					public void internalFrameDeactivated(InternalFrameEvent e) {	
					}
					public void internalFrameDeiconified(InternalFrameEvent e) {	
					}
					public void internalFrameIconified(InternalFrameEvent e) {
					}
					public void internalFrameOpened(InternalFrameEvent e) {	
					}
				});
					dPane.add(janela_result);
					janela_result.show();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

		mnResultados.add(mntmAlunos);

		mnLista.setIcon(new ImageIcon("/home/caio/icones/lista.png"));
		mnLista.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				
			
				try {
					ListasGUI listas = new ListasGUI();
					listas.iniciarJanela();
					mnCadastrar.setEnabled(false);
					mnResultados.setEnabled(false);
					mnLista.setEnabled(false);
					listas.addInternalFrameListener(new javax.swing.event.InternalFrameListener(){
						public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt)   {  
				               mnCadastrar.setEnabled(true);
				               mnLista.setEnabled(true);
								mnResultados.setEnabled(true);
				            }
							public void internalFrameActivated(InternalFrameEvent e) {
							}
							public void internalFrameClosing(InternalFrameEvent e) {
							}
							public void internalFrameDeactivated(InternalFrameEvent e) {	
							}
							public void internalFrameDeiconified(InternalFrameEvent e) {	
							}
							public void internalFrameIconified(InternalFrameEvent e) {
							}
							public void internalFrameOpened(InternalFrameEvent e) {	
							}
						});
					dPane.add(listas);
					listas.show();
				} catch (SQLException e) {

					e.printStackTrace();
				}

			}
		});
		mnLista.setToolTipText("Lista de Alunos e Professores Cadastrados");
		mnLista.setForeground(new Color(169, 169, 169));
		menuBar.add(mnLista);

		JMenu sair = new JMenu("Sair");
		sair.setIcon(new ImageIcon("/home/caio/icones/sair.png"));
		menuBar.add(sair);
		sair.setToolTipText("Sair do Sistema");
		sair.setFont(new Font("Segoe UI", Font.BOLD, 12));
		sair.setForeground(Color.RED);
		sair.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				System.exit(0);
			}
		});

	}
}
