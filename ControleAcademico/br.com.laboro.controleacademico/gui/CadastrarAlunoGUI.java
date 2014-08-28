package gui;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.border.EtchedBorder;
import javax.swing.text.MaskFormatter;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import org.jdesktop.swingx.autocomplete.AutoCompleteStyledDocument;


import classes.backend.Aluno;
import classes.backend.Endereco;
import classes.backend.Turma;
import dao.AlunoDAO;
import dao.EnderecoDAO;
import dao.TurmaDAO;

import java.awt.Color;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.UIManager;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class CadastrarAlunoGUI extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField tfAluno;
	private JTextField tfComplemento;
	private JTextField tfCasa;
	private JTextField tfRua;
	private JFormattedTextField tfTelefone = new JFormattedTextField(
			Mascara(" (##) ####-#### "));
	private JTextField tfBairro;
	 private JComboBox<String> cbTurma = new JComboBox<String>();
	 private JComboBox<String> cbCidade = new JComboBox<String>();
	 private JComboBox<String> cbEstado = new JComboBox<String>();
	private String nomeEstado;

	/**
	 * Iniciando o construtor
	 * 
	 */
	public CadastrarAlunoGUI() throws SQLException {
		setBackground(new Color(220, 220, 220));
		setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.RAISED, null,
				null), "Cadastro de Alunos", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		setLayout(null);

		JPanel panelNome = new JPanel();
		panelNome.setBackground(new Color(220, 220, 220));
		panelNome.setBorder(new TitledBorder(null, "Nome do aluno",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelNome.setBounds(37, 40, 521, 103);
		add(panelNome);
		panelNome.setLayout(null);

		tfAluno = new JTextField();
		tfAluno.setBounds(29, 43, 460, 25);
		panelNome.add(tfAluno);
		tfAluno.setColumns(10);

		JPanel panelEnd = new JPanel();
		panelEnd.setBackground(new Color(220, 220, 220));
		panelEnd.setBorder(new TitledBorder(null, "Endere\u00E7o",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelEnd.setBounds(37, 155, 879, 249);
		add(panelEnd);
		panelEnd.setLayout(null);

		JLabel lblRua = new JLabel("Rua");
		lblRua.setBounds(50, 88, 46, 14);
		panelEnd.add(lblRua);

		JLabel lblBairro = new JLabel("Bairro");
		lblBairro.setBounds(639, 29, 86, 14);
		panelEnd.add(lblBairro);

		JLabel lblCidade = new JLabel("Cidade");
		lblCidade.setBounds(313, 29, 86, 14);
		panelEnd.add(lblCidade);

		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setBounds(42, 29, 74, 14);
		panelEnd.add(lblEstado);

		JLabel lblNCasa = new JLabel("N\u00B0 Casa");
		lblNCasa.setBounds(387, 87, 76, 14);
		panelEnd.add(lblNCasa);

		JLabel lblTelefone = new JLabel("Telefone");
		lblTelefone.setBounds(497, 88, 96, 14);
		panelEnd.add(lblTelefone);
		// cidade
		
		cbCidade.setEditable(true);
		cbCidade.setBounds(309, 50, 277, 25);
		final DefaultComboBoxModel<String> modeloCidade = new DefaultComboBoxModel<String>();
		modeloCidade.addElement("");
		cbCidade.setModel(modeloCidade);
		AutoCompleteDecorator.decorate(cbCidade);
		
		panelEnd.add(cbCidade);
		// evento do campo de cb estado para filtrar as cidades do país
		cbEstado.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				try {
					ArrayList<Endereco> listaCidade = new ArrayList<Endereco>();
					nomeEstado = cbEstado.getSelectedItem().toString();
					Endereco endereco = new Endereco();
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
		
		// estado

		cbEstado.setEditable(true);
		DefaultComboBoxModel<String> modeloEstado = new DefaultComboBoxModel<String>();
		modeloEstado.addElement("");
		ArrayList<Endereco> listaEstado = new ArrayList<Endereco>();
		listaEstado = new EnderecoDAO().listarEstado();
		for (Endereco aluno : listaEstado) {
			modeloEstado.addElement(aluno.getEstado());
		}
		cbEstado.setBounds(44, 51, 209, 25);
		cbEstado.setModel(modeloEstado);
		AutoCompleteDecorator.decorate(cbEstado);
		panelEnd.add(cbEstado);
	

		tfComplemento = new JTextField();
		tfComplemento.setBounds(50, 166, 600, 25);
		panelEnd.add(tfComplemento);
		tfComplemento.setColumns(10);

		JLabel lblComplemento = new JLabel("Complemento");
		lblComplemento.setBounds(42, 151, 149, 16);
		panelEnd.add(lblComplemento);

		tfCasa = new JTextField();
		tfCasa.setBounds(397, 114, 56, 25);
		panelEnd.add(tfCasa);
		tfCasa.setColumns(10);

		tfRua = new JTextField();
		tfRua.setBounds(42, 114, 313, 25);
		panelEnd.add(tfRua);
		tfRua.setColumns(10);

		tfTelefone.setBounds(507, 114, 157, 25);
		panelEnd.add(tfTelefone);

		tfBairro = new JTextField();
		tfBairro.setBounds(639, 50, 193, 25);
		panelEnd.add(tfBairro);
		tfBairro.setColumns(10);
		JButton btnNovo = new JButton("Novo");
		btnNovo.setBounds(667, 200, 89, 23);
		panelEnd.add(btnNovo);
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				desbloqueaCampos();
			}
		});
		btnNovo.setToolTipText("Clique aqui para desbloquear os campos");
		btnNovo.setForeground(UIManager.getColor("Button.foreground"));
		btnNovo.setBackground(UIManager.getColor("Button.background"));
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setBounds(778, 200, 89, 23);
		panelEnd.add(btnSalvar);
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Endereco endAluno = new Endereco();
				Aluno aluno = new Aluno();
				Turma turma = new Turma();
				// pegando os valores do cb's
				endAluno.setBairro(tfBairro.getText());
				String cidade = cbCidade.getSelectedItem().toString();
				endAluno.setCidade(cidade);
				String estado = cbEstado.getSelectedItem().toString();
				endAluno.setEstado(estado);
				endAluno.setRua(tfRua.getText());
				endAluno.setNumeroDaCasa(tfCasa.getText());
				endAluno.setTelefone(tfTelefone.getText());
				endAluno.setComplemento(tfComplemento.getText());
				// pegando o valor do textField e cbTurma
				aluno.setNomeAluno(tfAluno.getText());
				turma.setNomeTurma(cbTurma.getSelectedItem().toString());
				
				//instanciando os objetos de acordo com o padrao de OOP
				AlunoDAO alunoSalvar = new AlunoDAO();
				EnderecoDAO endereco = new EnderecoDAO();
				
				try {
				endereco.adcionarEndereco(endAluno);
				alunoSalvar.adcionarAluno(aluno, turma, endAluno);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					JOptionPane.showMessageDialog(null,
							e.getMessage());
				}
				JOptionPane.showMessageDialog(null,
						"O aluno " + tfAluno.getText()
								+ " foi cadastrado com sucesso");
				bloqueaCampos();
			}
		});
		
				btnSalvar.setToolTipText("Salvar dados");

		// novo painel
		JPanel panel = new JPanel();
		panel.setBackground(new Color(220, 220, 220));
		panel.setBorder(new TitledBorder(null, "Turma", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panel.setBounds(595, 43, 258, 89);
		add(panel);
		panel.setLayout(null);

		/*
		 * Carregando dados do banco para o combobox auto completar facilitando
		 * o usuário a busca
		 */

		cbTurma.setEditable(true);
		cbTurma.setBounds(47, 36, 182, 25);
		DefaultComboBoxModel<String> modeloTurma = new DefaultComboBoxModel<String>();
		modeloTurma.addElement("");
		ArrayList<Turma> listaTurma = new ArrayList<>();
		listaTurma = new TurmaDAO().lerNomeTurma();
		for (Turma turma : listaTurma) {

			modeloTurma.addElement(turma.getNomeTurma());

		}

		cbTurma.setModel(modeloTurma);
		AutoCompleteDecorator.decorate(cbTurma);
		panel.add(cbTurma);

		// Botão salvar
		/*
		 * iniciando os eventos...
		 */
		bloqueaCampos();

		/*
		 * Botão novo iniciando os eventos
		 */

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
		JTextField[] campos = { tfAluno, tfBairro, tfCasa, tfRua, tfTelefone,
				tfComplemento };
		return campos;
	}

	public JComboBox[] receberCombos() {
		JComboBox[] campos = { cbCidade, cbTurma, cbEstado };
		return campos;
	}

	public void bloqueaCampos() {
		JTextField[] campos = receberCampos();
		for (int i = 0; i < campos.length; i++) {
			campos[i].setEnabled(false);
		}
		tfAluno.setText("Clique em novo para desbloquear os campos");
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
