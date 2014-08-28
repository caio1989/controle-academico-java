package gui;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.border.LineBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.UIManager;
import javax.swing.JTextField;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import classes.backend.Aluno;
import classes.backend.Endereco;
import classes.backend.Turma;
import dao.AlunoDAO;
import dao.EnderecoDAO;
import dao.TurmaDAO;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AlterarAlunoGUI extends JPanel {
	private JTextField tfBairro;
	private JTextField tfCasa;
	private JTextField tfTurma;
	private JTextField tfRua;
	private JComboBox cbAluno = new JComboBox ();
	private JComboBox  cbCidade = new JComboBox();
	private JComboBox  cbEstado = new JComboBox ();
	private JComboBox  cbTurma = new JComboBox ();
	private JFormattedTextField tfTelefone = new JFormattedTextField();
	private JTextField tfComplemento;
	private JTextField tfNomeAluno;
	private String nomeEstado;
	Aluno alunoNome = new Aluno();
	/**
	 * iniciando o construtor do painel
	 * 
	 * @throws SQLException
	 */
	public AlterarAlunoGUI() throws SQLException {
		setBackground(new Color(245, 245, 220));
		setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(245, 245, 220));
		panel.setBorder(new TitledBorder(null, "Nome do Aluno",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(26, 12, 570, 78);
		add(panel);
		panel.setLayout(null);
		//combo do aluno, componente de texto da GUI de interface grafica
		cbAluno.setEditable(true);
		cbAluno.setBounds(26, 26, 510, 25);
		DefaultComboBoxModel modeloAluno = new DefaultComboBoxModel();
		ArrayList<Aluno> listaAluno = new ArrayList<Aluno>();
		modeloAluno.addElement("");
		listaAluno = new AlunoDAO().listaNomeAluno();
		for (Aluno aluno : listaAluno) {
			modeloAluno.addElement(aluno.getNomeAluno().toString());
		}
		
		AutoCompleteDecorator.decorate(cbAluno);
		cbAluno.setModel(modeloAluno);
		panel.add(cbAluno);
		//armazenando no objeto a string do combo
		
		
		
		//rua do aluno
		cbAluno.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				try {
					alunoNome.setNomeAluno(cbAluno.getSelectedItem().toString());
					AlunoDAO aluno = new AlunoDAO();
					tfRua.setText(aluno.listarRuaAluno(alunoNome));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		// turma do aluno
		cbAluno.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				
				try {
					alunoNome.setNomeAluno(cbAluno.getSelectedItem().toString());
					AlunoDAO aluno = new AlunoDAO();
					tfTurma.setText(aluno.listarTurmaAluno(alunoNome));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		// estado do aluno
		cbAluno.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				
				try {
					alunoNome.setNomeAluno(cbAluno.getSelectedItem().toString());
					AlunoDAO aluno = new AlunoDAO();
					cbEstado.setSelectedItem(aluno.listarEstadoAluno(alunoNome));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		// cidade do aluno
		cbAluno.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				
				try {
					alunoNome.setNomeAluno(cbAluno.getSelectedItem().toString());
					AlunoDAO aluno = new AlunoDAO();
					cbCidade.setSelectedItem(aluno.listarCidadeAluno(alunoNome));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		// complemento do endereço do aluno
		cbAluno.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				
				try {
					alunoNome.setNomeAluno(cbAluno.getSelectedItem().toString());
					AlunoDAO aluno = new AlunoDAO();
					tfComplemento.setText(aluno.listarComplementoAluno(alunoNome));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		// telefone
		cbAluno.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				
				try {
					alunoNome.setNomeAluno(cbAluno.getSelectedItem().toString());
					AlunoDAO aluno = new AlunoDAO();
					tfTelefone.setText(aluno.listarTelefoneAluno(alunoNome));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		// casa
		cbAluno.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				
				try {
					alunoNome.setNomeAluno(cbAluno.getSelectedItem().toString());
					AlunoDAO aluno = new AlunoDAO();
					tfCasa.setText(aluno.listarCasaAluno(alunoNome));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		// bairro
		cbAluno.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				
				try {
					alunoNome.setNomeAluno(cbAluno.getSelectedItem().toString());
					AlunoDAO aluno = new AlunoDAO();
					tfBairro.setText(aluno.listarBairroAluno(alunoNome));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		// nome do aluno
		cbAluno.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				try {
					tfNomeAluno.setText(cbAluno.getSelectedItem().toString());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(245, 245, 220));
		panel_1.setBorder(new TitledBorder(new LineBorder(new Color(184, 207,
				229)), "Turma Atual", TitledBorder.LEADING, TitledBorder.TOP,
				null, null));
		panel_1.setBounds(665, 12, 189, 78);
		add(panel_1);
		panel_1.setLayout(null);

		tfTurma = new JTextField();
		tfTurma.setEditable(false);
		tfTurma.setColumns(10);
		tfTurma.setBounds(28, 28, 149, 25);
		panel_1.add(tfTurma);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(245, 245, 220));
		panel_2.setLayout(null);
		panel_2.setBorder(new TitledBorder(new LineBorder(new Color(184, 207,
				229)), "Novo Endere\u00E7o", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panel_2.setBounds(36, 166, 818, 177);
		add(panel_2);

		JLabel lblRua = new JLabel("Rua");
		lblRua.setBounds(38, 12, 55, 16);
		panel_2.add(lblRua);

		JLabel lblTelefone = new JLabel("Telefone");
		lblTelefone.setBounds(538, 12, 85, 16);
		panel_2.add(lblTelefone);

		JLabel lblCasa = new JLabel("Casa");
		lblCasa.setBounds(714, 12, 55, 16);
		panel_2.add(lblCasa);

		JLabel lblBairro = new JLabel("Bairro");
		lblBairro.setBounds(58, 118, 55, 16);
		panel_2.add(lblBairro);

		JLabel lblCidade = new JLabel("Cidade");
		lblCidade.setBounds(630, 118, 55, 16);
		panel_2.add(lblCidade);

		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setBounds(424, 120, 55, 16);
		panel_2.add(lblEstado);
		
		
		tfBairro = new JTextField();
		tfBairro.setBounds(52, 135, 284, 25);
		panel_2.add(tfBairro);
		tfBairro.setColumns(10);

		tfCasa = new JTextField();
		tfCasa.setColumns(10);
		tfCasa.setBounds(703, 33, 66, 25);
		panel_2.add(tfCasa);

		tfTelefone.setBounds(519, 33, 152, 25);
		panel_2.add(tfTelefone);

		tfRua = new JTextField();
		tfRua.setBounds(38, 33, 442, 25);
		panel_2.add(tfRua);
		tfRua.setColumns(10);

		cbCidade.setEditable(true);
		cbCidade.setBounds(593, 135, 169, 25);
		 final DefaultComboBoxModel modeloCidade = new DefaultComboBoxModel();
		modeloCidade.addElement("");
		cbCidade.setModel(modeloCidade);
		AutoCompleteDecorator.decorate(cbCidade);
		panel_2.add(cbCidade);
		
		
		cbEstado.setEditable(true);
		//listando os estados no combo
				DefaultComboBoxModel modeloEstado = new DefaultComboBoxModel();
				modeloEstado.addElement("");
				ArrayList<Endereco> listaEstado = new ArrayList<Endereco>();
				listaEstado = new EnderecoDAO().listarEstado();
				for (Endereco aluno : listaEstado) {
					modeloEstado.addElement(aluno.getEstado());
				}
				cbEstado.setModel(modeloEstado);
				AutoCompleteDecorator.decorate(cbEstado);
				cbEstado.setBounds(372, 135, 209, 25);
				panel_2.add(cbEstado);
				
				//evento implementado para filtrar as cidades
		cbEstado.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0){
				
				try {
					ArrayList<Endereco> listaCidade = new ArrayList<Endereco>();
					Endereco endereco = new Endereco();
					nomeEstado = cbEstado.getSelectedItem().toString();
					endereco.setEstado(nomeEstado);
					listaCidade = new EnderecoDAO().listarCidade(endereco);	
					for (Endereco aluno : listaCidade) {
						modeloCidade.addElement(aluno.getCidade());
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		

		tfComplemento = new JTextField();
		tfComplemento.setBounds(34, 84, 600, 25);
		panel_2.add(tfComplemento);
		tfComplemento.setColumns(10);

		JLabel lblComplemento = new JLabel("Complemento");
		lblComplemento.setBounds(34, 70, 120, 15);
		panel_2.add(lblComplemento);

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Aluno aluno = new Aluno();
				Turma turma = new Turma();
				aluno.setNomeAluno(tfNomeAluno.getText());
				String turmaNome = cbTurma.getSelectedItem().toString();
				turma.setNomeTurma(turmaNome);
				Endereco endereco = new Endereco();
				endereco.setBairro(tfBairro.getText());
				endereco.setCidade(cbCidade.getSelectedItem().toString());
				endereco.setComplemento(tfComplemento.getText());
				endereco.setNumeroDaCasa(tfCasa.getText());
				endereco.setTelefone(tfTelefone.getText());
				endereco.setEstado(cbEstado.getSelectedItem().toString());
			
				AlunoDAO salvarAluno = new AlunoDAO();
				try {
					salvarAluno.alterarAluno(aluno,endereco,turma);
					
					bloqueaCampos();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					JOptionPane.showMessageDialog(null,e.getMessage());
				}
				JOptionPane.showMessageDialog(null,"Aluno alterado com sucesso!");
			}
		});
		btnSalvar.setBackground(UIManager.getColor("Button.background"));
		btnSalvar.setForeground(UIManager.getColor("Button.foreground"));
		btnSalvar.setBounds(764, 354, 89, 23);
		add(btnSalvar);

		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBorder(new TitledBorder(new LineBorder(new Color(184, 207,
				229)), "Nova Turma", TitledBorder.LEADING, TitledBorder.TOP,
				null, null));
		panel_3.setBackground(new Color(245, 245, 220));
		panel_3.setBounds(665, 87, 189, 78);
		add(panel_3);

		cbTurma.setEditable(true);
		cbTurma.setBounds(28, 26, 127, 25);
		DefaultComboBoxModel modeloTurma = new DefaultComboBoxModel();
		modeloTurma.addElement("");
		ArrayList<Turma> listaTurma = new ArrayList<Turma>();
		listaTurma = new TurmaDAO().lerNomeTurma();
		for(Turma turma : listaTurma){
			modeloTurma.addElement(turma.getNomeTurma());
			
		}
		cbTurma.setModel(modeloTurma);
		AutoCompleteDecorator.decorate(cbTurma);
		panel_3.add(cbTurma);
		
		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Novo Nome do Aluno", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_4.setBackground(new Color(245, 245, 220));
		panel_4.setBounds(26, 87, 570, 78);
		add(panel_4);
		
		tfNomeAluno = new JTextField();
		tfNomeAluno.setBounds(27, 31, 500, 25);
		panel_4.add(tfNomeAluno);
		tfNomeAluno.setColumns(10);
		
		JButton btnNovo = new JButton("Novo");
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				desbloqueaCampos();
			}
		});
		btnNovo.setForeground(Color.RED);
		btnNovo.setBounds(644, 354, 89, 23);
		add(btnNovo);

	}

	public MaskFormatter Mascara(String Mascara) {
		MaskFormatter F_Mascara = new MaskFormatter();
		try {
			F_Mascara.setMask(Mascara); // Atribui a mascara
			F_Mascara.setPlaceholderCharacter(' '); // Caracter para
													// preencimento
		} catch (Exception excecao) {
			excecao.printStackTrace();
		}
		return F_Mascara;
	}
	public JTextField[] receberCampos() {
		JTextField[] campos = { tfNomeAluno, tfBairro, tfCasa, tfRua, tfTelefone,
				tfComplemento, tfTurma };
		return campos;
	}

	public JComboBox[] receberCombos() {
		JComboBox[] campos = { cbCidade, cbTurma, cbEstado, cbAluno };
		return campos;
	}

	public void bloqueaCampos() {
		JTextField[] campos = receberCampos();
		for (int i = 0; i < campos.length; i++) {
			campos[i].setEnabled(false);
		}
		tfNomeAluno.setText("Clique em novo para desbloquear os campos");
		JComboBox[] comboCampos = receberCombos();
		for (int i = 0; i < comboCampos.length; i++) {
			comboCampos[i].setEnabled(false);
		}
	}

	public void desbloqueaCampos() {
		JTextField[] campos = receberCampos();
		for (int i = 0; i < campos.length; i++) {
			campos[i].setEnabled(true);
			campos[i].setText("");
		}
		JComboBox[] comboCampos = receberCombos();
		for (int i = 0; i < comboCampos.length; i++) {
			comboCampos[i].setEnabled(true);
			comboCampos[i].setSelectedItem("");
		}

	}
}
