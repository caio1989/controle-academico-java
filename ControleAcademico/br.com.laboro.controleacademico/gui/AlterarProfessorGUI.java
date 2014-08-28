package gui;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import classes.backend.*;


import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import componentes.ModeloTabela;

import dao.ProfessorDAO;
import factory.ConnectionFactory;








import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;


public class AlterarProfessorGUI extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Connection connection;
	private JTextField tfBairro;
	private JTextField tfRua;
	private JTextField textField;
	private JComboBox <String>cbDisciplina = new JComboBox<String>();
	private JComboBox <String>cbTurma = new JComboBox<String>();
	private JComboBox <String>cbNome = new JComboBox<String>();
	private JComboBox <String>cbEstado = new JComboBox<String>();
	private JComboBox <String>cbCidade = new JComboBox<String>();
	private JFormattedTextField tfTelefone = new JFormattedTextField();
	 Professor professor = new Professor();
	private JTable tDadosAtuais = tDadosAtuais = new JTable();;
	/**
	 * Criando o painel.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public AlterarProfessorGUI() throws SQLException  {
		
		try {
			connection = ConnectionFactory.getConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setLocation(-35, -245);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Nome", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(35, 12, 790, 77);
		add(panel);
		panel.setLayout(null);
		cbNome.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				
				Professor professor = new Professor();
				ProfessorDAO dao = new ProfessorDAO();
				try {
					preencherTabelaDadosAtuais(dao.selectSqlDadosProfessor(professor));
				} catch (SQLException e) {
					e.printStackTrace();
				}			
			}
		});
		
		
		cbNome.setEditable(true);
		cbNome.setBounds(43, 27, 667, 26);
		DefaultComboBoxModel<String>modeloProf = new DefaultComboBoxModel<>();
		modeloProf.addElement("");
		ArrayList<Professor>lista = new ArrayList<Professor>();
		lista = new ProfessorDAO().listarNomeProfessor();
		for(classes.backend.Professor professor:lista){
			modeloProf.addElement(professor.getNomeProf());
		}
		cbNome.setModel(modeloProf);
		AutoCompleteDecorator.decorate(cbNome);
		panel.add(cbNome);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Endere\u00E7o", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(35, 300, 746, 137);
		add(panel_1);
		panel_1.setLayout(null);
		cbEstado.setEditable(true);
		cbEstado.setBounds(38, 39, 186, 26);
		panel_1.add(cbEstado);
		
		
		cbCidade.setEditable(true);
		cbCidade.setBounds(259, 39, 187, 26);
		panel_1.add(cbCidade);
		
		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setBounds(38, 22, 70, 15);
		panel_1.add(lblEstado);
		
		JLabel lblCidade = new JLabel("Cidade");
		lblCidade.setBounds(259, 22, 70, 15);
		panel_1.add(lblCidade);
		
		tfBairro = new JTextField();
		tfBairro.setBounds(499, 39, 228, 26);
		panel_1.add(tfBairro);
		tfBairro.setColumns(10);
		
		JLabel lblBairro = new JLabel("Bairro");
		lblBairro.setBounds(502, 22, 70, 15);
		panel_1.add(lblBairro);
		
		tfRua = new JTextField();
		tfRua.setColumns(10);
		tfRua.setBounds(48, 98, 330, 26);
		panel_1.add(tfRua);
		
		JLabel lblRua = new JLabel("Rua");
		lblRua.setBounds(48, 77, 70, 15);
		panel_1.add(lblRua);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(393, 98, 50, 26);
		panel_1.add(textField);
		
		JLabel lblCasa = new JLabel("Casa");
		lblCasa.setBounds(394, 77, 70, 15);
		panel_1.add(lblCasa);
		
		
		tfTelefone.setBounds(476, 98, 198, 26);
		panel_1.add(tfTelefone);
		
		JLabel lblTelefone = new JLabel("Telefone");
		lblTelefone.setBounds(476, 77, 70, 15);
		panel_1.add(lblTelefone);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Dados complementares", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(45, 434, 542, 93);
		add(panel_2);
		panel_2.setLayout(null);
		
		
		cbDisciplina.setEditable(true);
		cbDisciplina.setBounds(26, 42, 186, 26);
		panel_2.add(cbDisciplina);
		
		
		cbTurma.setEditable(true);
		cbTurma.setBounds(273, 42, 186, 26);
		panel_2.add(cbTurma);
		
		JLabel lblDisciplinaDoProfessor = new JLabel("Disciplina do professor");
		lblDisciplinaDoProfessor.setBounds(33, 28, 200, 15);
		panel_2.add(lblDisciplinaDoProfessor);
		
		JLabel lblTurmaDoProfessor = new JLabel("Turma do professor");
		lblTurmaDoProfessor.setBounds(273, 28, 200, 15);
		panel_2.add(lblTurmaDoProfessor);
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.setBounds(718, 502, 117, 25);
		add(btnAlterar);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "Dados Atuais", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(45, 89, 803, 199);
		add(panel_3);
		panel_3.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 26, 755, 149);
		panel_3.add(scrollPane);
		
		
		scrollPane.setViewportView(tDadosAtuais);
		

	}
	public void preencherTabelaDadosAtuais(String sql) throws SQLException {
		ArrayList<Object[]> dados = new ArrayList<Object[]>();
		String[] colunas = new String[] { "Nome do Professor","Turma", "Disciplinas","Rua","Casa","Telefone","Bairro","Cidade","Estado" };
		Turma turma = new Turma();
		Endereco endereco = new Endereco();
		classes.backend.Professor professor = new classes.backend.Professor();
		PreparedStatement stmt = connection.prepareStatement(sql);
		Disciplinas materia = new Disciplinas();
		
		
		
		
		
		ModeloTabela modelo = new ModeloTabela(dados, colunas);
		tDadosAtuais.setModel(modelo);
		tDadosAtuais.getColumnModel().getColumn(0).setPreferredWidth(300);
		tDadosAtuais.getColumnModel().getColumn(0).setResizable(true);
		tDadosAtuais.getColumnModel().getColumn(1).setPreferredWidth(120);
		tDadosAtuais.getColumnModel().getColumn(1).setResizable(true);
		tDadosAtuais.getColumnModel().getColumn(2).setPreferredWidth(300);
		tDadosAtuais.getColumnModel().getColumn(2).setResizable(true);
		tDadosAtuais.getColumnModel().getColumn(3).setPreferredWidth(400);
		tDadosAtuais.getColumnModel().getColumn(3).setResizable(true);
		tDadosAtuais.getColumnModel().getColumn(4).setPreferredWidth(170);
		tDadosAtuais.getColumnModel().getColumn(4).setResizable(true);
		tDadosAtuais.getColumnModel().getColumn(5).setPreferredWidth(120);
		tDadosAtuais.getColumnModel().getColumn(5).setResizable(true);
		tDadosAtuais.getColumnModel().getColumn(6).setPreferredWidth(120);
		tDadosAtuais.getColumnModel().getColumn(6).setResizable(true);
		tDadosAtuais.getColumnModel().getColumn(7).setPreferredWidth(120);
		tDadosAtuais.getColumnModel().getColumn(7).setResizable(true);
		tDadosAtuais.getColumnModel().getColumn(8).setPreferredWidth(120);
		tDadosAtuais.getColumnModel().getColumn(8).setResizable(true);
		tDadosAtuais.setAutoResizeMode(tDadosAtuais.AUTO_RESIZE_OFF);
		tDadosAtuais.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		
		
		
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			professor.setNomeProf(rs.getString("professor.nomeProfessor"));
			turma.setNomeTurma(rs.getString("turma.nomeTurma"));
			materia.setNomeDisciplina(rs.getString("disciplinas.nome"));
			endereco.setRua(rs.getString("endereco.rua"));
			endereco.setNumeroDaCasa(rs.getString("endereco.casa"));
			endereco.setTelefone(rs.getString("endereco.telefone"));
			endereco.setBairro(rs.getString("bairro.nome"));
			endereco.setCidade(rs.getString("cidade.nome"));
			endereco.setEstado(rs.getString("estado.nome"));
			dados.add(new Object[] { professor.getNomeProf(),turma.getNomeTurma(),
					materia.getNomeDisciplina(),endereco.getRua(),endereco.getNumeroDaCasa(),
					endereco.getTelefone(),
					endereco.getBairro(),
					endereco.getCidade(),
					endereco.getEstado()});
		}
	
		
		stmt.close();
		rs.close();
		

	}
	
}
