package gui;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.text.MaskFormatter;

import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import classes.backend.Endereco;
import classes.backend.Disciplinas;
import classes.backend.Professor;
import classes.backend.Turma;
import dao.EnderecoDAO;
import dao.ProfessorDAO;
import dao.TurmaDAO;
import dao.DisciplinasDAO;
import javax.swing.JFormattedTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CadastroProfGUI extends JPanel {
	private JTextField tfNome;
	private JTextField tfRua;
	private JTextField tfCasa;
	private JTextField tfComplemento;
	private JTextField tfBairro;
	private JComboBox cbEstado = new JComboBox();
	private JComboBox cbCidade = new JComboBox();
	private JComboBox cbDisciplina = new JComboBox();
	private JComboBox cbTurma = new JComboBox();
	private String nomeEstado;
	JFormattedTextField tfTelefone = new JFormattedTextField(Mascara("(##) ####-####"));
	/**
	 * Create the panel.
	 * @throws SQLException 
	 * classe responsavel pelo cadastro de professores que serao lancados no banco de dados
	 */
	
	@SuppressWarnings("unchecked")
	public CadastroProfGUI() throws SQLException {
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Nome", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(42, 12, 618, 104);
		add(panel);
		panel.setLayout(null);
		
		tfNome = new JTextField();
		tfNome.setBounds(29, 38, 541, 26);
		panel.add(tfNome);
		tfNome.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Endere\u00E7o", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(12, 126, 858, 172);
		add(panel_1);
		
		tfRua = new JTextField();
		tfRua.setColumns(10);
		tfRua.setBounds(485, 49, 332, 26);
		panel_1.add(tfRua);
		
		
		
		cbCidade.setEditable(true);
		cbCidade.setBounds(281, 49, 128, 24);
		cbCidade.setEditable(true);
		cbCidade.setBounds(309, 50, 128, 25);
		final DefaultComboBoxModel<String> modeloCidade = new DefaultComboBoxModel<String>();
		modeloCidade.addElement("");
		cbCidade.setModel(modeloCidade);
		AutoCompleteDecorator.decorate(cbCidade);
		panel_1.add(cbCidade);
		
		cbEstado.setEditable(true);
		cbEstado.setBounds(12, 50, 120, 25);
		cbEstado.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				
				//filtrando apartir da selecao dos estados as cidades
				
				
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
		
		//lista dos estados
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
		
		
		panel_1.add(cbEstado);
		
		
	
		
		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setBounds(61, 24, 70, 15);
		panel_1.add(lblEstado);
		
		JLabel lblCidade = new JLabel("Cidade");
		lblCidade.setBounds(308, 24, 70, 14);
		panel_1.add(lblCidade);
		
		JLabel lblRua = new JLabel("Rua");
		lblRua.setBounds(485, 22, 70, 15);
		panel_1.add(lblRua);
		
		tfCasa = new JTextField();
		tfCasa.setColumns(10);
		tfCasa.setBounds(16, 126, 61, 26);
		panel_1.add(tfCasa);
		
		JLabel lblCasa = new JLabel("Casa");
		lblCasa.setBounds(12, 99, 70, 15);
		panel_1.add(lblCasa);
		
		tfComplemento = new JTextField();
		tfComplemento.setColumns(10);
		tfComplemento.setBounds(309, 126, 368, 26);
		panel_1.add(tfComplemento);
		
		JLabel lblComplemento = new JLabel("Complemento");
		lblComplemento.setBounds(316, 99, 127, 15);
		panel_1.add(lblComplemento);
		
		tfBairro = new JTextField();
		tfBairro.setColumns(10);
		tfBairro.setBounds(710, 126, 136, 26);
		panel_1.add(tfBairro);
		
		JLabel lblBairro = new JLabel("Bairro");
		lblBairro.setBounds(714, 99, 70, 15);
		panel_1.add(lblBairro);
		
		tfTelefone.setBounds(89, 126, 185, 26);
		panel_1.add(tfTelefone);
		
		JLabel lblTelefone = new JLabel("Telefone");
		lblTelefone.setBounds(94, 99, 90, 15);
		panel_1.add(lblTelefone);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//instanciando os objetos de acordo com as colunas da tabela de dados
				
				Disciplinas materia = new Disciplinas();
				Professor professor = new Professor();
				Endereco endereco = new Endereco();
				Turma turma = new Turma();
		
				//iniciando os objetos do data access object de acordo com os conceitos de designer patterns
				EnderecoDAO enderecoProf = new EnderecoDAO();
				ProfessorDAO professorSalvar = new ProfessorDAO();
			
				try {
					professor.setNomeProf(tfNome.getText());
					endereco.setRua(tfRua.getText());
					endereco.setNumeroDaCasa(tfCasa.getText());
					endereco.setComplemento(tfComplemento.getText());
					endereco.setTelefone(tfTelefone.getText());
					endereco.setBairro(tfBairro.getText());
					endereco.setCidade(cbCidade.getSelectedItem().toString());
					endereco.setEstado(cbEstado.getSelectedItem().toString());
					/*materia e turma*/
					materia.setNomeDisciplina(cbDisciplina.getSelectedItem().toString());
					turma.setNomeTurma(cbTurma.getSelectedItem().toString());
					enderecoProf.adcionarEndereco(endereco);
					professorSalvar.adicionarProfessor(professor,materia,endereco,turma);
				
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					JOptionPane.showMessageDialog(null,"Erro: '"+e+"''");
				}
				JOptionPane.showMessageDialog(null, "Professor '"+professor.getNomeProf()+"' cadastrado com sucesso'");
				bloquearCampos();
			}
		});
		btnSalvar.setBounds(748, 389, 117, 25);
		add(btnSalvar);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Dados complementares", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(50, 310, 512, 104);
		add(panel_2);
		panel_2.setLayout(null);
		
		
		cbDisciplina.setEditable(true);
		cbDisciplina.setBounds(25, 54, 195, 26);
		DefaultComboBoxModel<String> modeloDisciplina = new DefaultComboBoxModel<String>();
		modeloDisciplina.addElement("");
		ArrayList<Disciplinas> listaMateria = new ArrayList<Disciplinas>();
		listaMateria = new DisciplinasDAO().listarNomeDisciplinas();
		for (Disciplinas materia : listaMateria) { // para cada objeto da listaMateria da classe ConnMateria que se repetir, faça...

			modeloDisciplina.addElement(materia.getNomeDisciplina());

		}

		cbDisciplina.setModel(modeloDisciplina);
		AutoCompleteDecorator.decorate(cbDisciplina);
		panel_2.add(cbDisciplina);
		
		JLabel lblDisciplinas = new JLabel("Disciplinas do professor");
		lblDisciplinas.setBounds(25, 24, 200, 18);
		panel_2.add(lblDisciplinas);
		
	
		cbTurma.setEditable(true);
		cbTurma.setBounds(296, 54, 195, 26);
		DefaultComboBoxModel<String> modeloTurma = new DefaultComboBoxModel<String>();
		modeloTurma.addElement("");
		ArrayList<Turma> listaTurma = new ArrayList<>();
		listaTurma = new TurmaDAO().lerNomeTurma();
		for (Turma turma : listaTurma) {
			modeloTurma.addElement(turma.getNomeTurma());
		}

		cbTurma.setModel(modeloTurma);
		AutoCompleteDecorator.decorate(cbTurma);
		panel_2.add(cbTurma);
		
		JLabel lblTurmas = new JLabel("Turmas do professor");
		lblTurmas.setBounds(296, 24, 160, 18);
		panel_2.add(lblTurmas);
		
		JButton btnNovo = new JButton("Novo");
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				desbloqueaCampos();
			}
		});
		btnNovo.setForeground(Color.RED);
		btnNovo.setBounds(594, 389, 117, 25);
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
		JTextField[] campos = { tfNome, tfBairro, tfCasa, tfRua, tfTelefone,
				tfComplemento };
		return campos;
	}

	public JComboBox[] receberCombos() {
		JComboBox[] campos = { cbCidade, cbTurma, cbEstado };
		return campos;
	}

	public void bloquearCampos() {
		JTextField[] campos = receberCampos();
		for (int i = 0; i < campos.length; i++) {
			campos[i].setEnabled(false);
		}
		tfNome.setText("Clique em novo para desbloquear os campos");
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
