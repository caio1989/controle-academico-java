package gui;

import javax.swing.JPanel;

import javax.swing.border.TitledBorder;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;


import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

import javax.swing.JComboBox;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import classes.backend.Disciplinas;
import classes.backend.Endereco;
import classes.backend.Professor;
import classes.backend.Turma;

import componentes.ModeloTabela;
import dao.ProfessorDAO;
import factory.ConnectionFactory;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
/*classe responsavel pela lista de dados do professor*/
public class ListaProfessoresGUI extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Connection connection;
	private JTable tbEndereco;
	private JTable tbDados;
	Professor professor = new Professor();
	private JComboBox<String> cbProfessor = new JComboBox<String>();
	public ListaProfessoresGUI() throws SQLException {
		try {
			connection = ConnectionFactory.getConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setBackground(new Color(245, 255, 250));
		setBounds(50, 30, 966, 443);
		setBorder(new TitledBorder(null, "Lista de Professores",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(null);

		JPanel paneEnd = new JPanel();
		paneEnd.setBackground(new Color(245, 255, 250));
		paneEnd.setBorder(new TitledBorder(null, "Endere\u00E7o do professor",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		paneEnd.setBounds(47, 116, 745, 150);
		add(paneEnd);
		paneEnd.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 24, 695, 103);
		paneEnd.add(scrollPane);

		tbEndereco = new JTable();
		scrollPane.setViewportView(tbEndereco);
		

		JPanel panel = new JPanel();
		panel.setBackground(new Color(245, 255, 250));
		panel.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"),
				"Digite o nome do professor", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panel.setBounds(48, 26, 510, 60);
		add(panel);
		panel.setLayout(null);
		cbProfessor.setBounds(12, 23, 484, 25);
		panel.add(cbProfessor);
		cbProfessor.setEditable(true);
	
		
		cbProfessor.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				String sqlEndereco = "select endereco.rua,endereco.casa," +
						" cidade.nome, bairro.nome, estado.nome, endereco.complemento, endereco.telefone" +
						" from professor inner join endereco on endereco.idendereco = professor.endereco_idendereco" +
						" and professor.nomeProfessor = '"+ cbProfessor.getSelectedItem().toString()+"'" +
						"inner join bairro on endereco.bairro_idbairro = bairro.idbairro " +
						"inner join cidade on endereco.cidade_idcidade = cidade.idcidade " +
						"inner join estado on endereco.estado_idestado = estado.idestado";
				
				String sqlDados = "select turma.nomeTurma, disciplinas.nome from professor" +
						" inner join turmasDoProfessor on turmasDoProfessor.professor_idProfessor = professor.idProfessor" +
						" inner join turma on turmasDoProfessor.turma_idTurma = turma.idTurma " +
						"inner join disciplinas  on disciplinas.idDisciplinas = professor.disciplinas_idDisciplinas"+
						" and professor.nomeProfessor = '"+cbProfessor.getSelectedItem().toString()+"'";
				
				try {
					preencherTabelaEndereco(sqlEndereco);
					preencherTabelaDados(sqlDados);
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		DefaultComboBoxModel<String> modeloProfessor = new DefaultComboBoxModel<String>();
		ArrayList<Professor> listaProfessor = new ArrayList<Professor>();
		modeloProfessor.addElement("");
		listaProfessor = new ProfessorDAO().listarNomeProfessor();
		for (Professor professor : listaProfessor) {
			modeloProfessor.addElement(professor.getNomeProf().toString());
		}
		AutoCompleteDecorator.decorate(cbProfessor);
		cbProfessor.setModel(modeloProfessor);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(240, 255, 250));
		panel_2.setBorder(new TitledBorder(null, "Dados Complementares",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(57, 278, 543, 150);
		add(panel_2);
		panel_2.setLayout(null);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(41, 32, 465, 95);
		panel_2.add(scrollPane_1);

		tbDados = new JTable();
		scrollPane_1.setViewportView(tbDados);
		

	}
	public void preencherTabelaEndereco(String sql)
			throws ClassNotFoundException, SQLException {
		ArrayList<Object[]> dados = new ArrayList<Object[]>();
		String[] colunas = new String[] { " Rua", "Casa", "Bairro",
				"Complemento", "Telefone", "Cidade", "Estado" };
		Endereco professor = new Endereco();

		PreparedStatement stmt = connection.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();

		// para completar todas as linhas da tabela
		while (rs.next()) {
			professor.setRua(rs.getString("endereco.rua")); // rua
			professor.setNumeroDaCasa(rs.getString("endereco.casa")); // casa
			professor.setBairro(rs.getString("bairro.nome")); // bairro
			professor.setComplemento(rs.getString("endereco.complemento")); // complemento
			professor.setTelefone(rs.getString("endereco.telefone"));// telefone
			professor.setCidade(rs.getString("cidade.nome"));// cidade
			professor.setEstado(rs.getString("estado.nome")); // estado

			dados.add(new Object[] { professor.getRua(), professor.getNumeroDaCasa(),
					professor.getBairro(), professor.getComplemento(),
					professor.getTelefone(), professor.getCidade(), professor.getEstado() });
		}
		ModeloTabela modelo = new ModeloTabela(dados, colunas);
		tbEndereco.setModel(modelo);
		tbEndereco.getColumnModel().getColumn(0).setPreferredWidth(228);
		tbEndereco.getColumnModel().getColumn(0).setResizable(true);
		tbEndereco.getColumnModel().getColumn(1).setPreferredWidth(80);
		tbEndereco.getColumnModel().getColumn(1).setResizable(true);
		tbEndereco.getColumnModel().getColumn(2).setPreferredWidth(141);
		tbEndereco.getColumnModel().getColumn(2).setResizable(true);
		tbEndereco.getColumnModel().getColumn(3).setPreferredWidth(285);
		tbEndereco.getColumnModel().getColumn(3).setResizable(true);
		tbEndereco.getColumnModel().getColumn(4).setPreferredWidth(141);
		tbEndereco.getColumnModel().getColumn(4).setResizable(true);
		tbEndereco.getColumnModel().getColumn(5).setPreferredWidth(141);
		tbEndereco.getColumnModel().getColumn(5).setResizable(true);
		tbEndereco.getColumnModel().getColumn(6).setPreferredWidth(141);
		tbEndereco.getColumnModel().getColumn(6).setResizable(true);

		tbEndereco.setAutoResizeMode(tbEndereco.AUTO_RESIZE_OFF);
		tbEndereco.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		stmt.close();
		rs.close();

	}

	public void preencherTabelaDados(String sql) throws SQLException {
		ArrayList<Object[]> dados = new ArrayList<Object[]>();
		String[] colunas = new String[] { "Turma", "Disciplinas" };
		Turma turma = new Turma();

		PreparedStatement stmt = connection.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		
		Disciplinas materia = new Disciplinas();
		// para preencher todas as linhas da tabela

		while (rs.next()) {
			turma.setNomeTurma(rs.getString("turma.nomeTurma"));
			materia.setNomeDisciplina(rs.getString("disciplinas.nome"));
			dados.add(new Object[] { turma.getNomeTurma(),
					materia.getNomeDisciplina() });
		}

		ModeloTabela modelo = new ModeloTabela(dados, colunas);
		tbDados.setModel(modelo);
		tbDados.getColumnModel().getColumn(0).setPreferredWidth(120);
		tbDados.getColumnModel().getColumn(0).setResizable(true);
		tbDados.getColumnModel().getColumn(1).setPreferredWidth(300);
		tbDados.getColumnModel().getColumn(1).setResizable(true);
		tbDados.setAutoResizeMode(tbEndereco.AUTO_RESIZE_OFF);
		tbDados.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		stmt.close();
		rs.close();

	}

}
