package gui;

import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import java.sql.Connection;

import classes.backend.Aluno;
import classes.backend.Endereco;
import classes.backend.Disciplinas;
import classes.backend.Turma;

import componentes.ModeloTabela;

import dao.AlunoDAO;
import factory.ConnectionFactory;

import javax.swing.ScrollPaneConstants;
/*
 * essa clase eh responsavel por mostrar os dados do banco para o usuário, nao pude seguir o designer patterns aqui, por falta de pespectiva e tempo
 * se voce nao tiver o conhecimento necessario, por favor nao mude as linhas desse codigo
 * Desenvolvido por Caio Cutrim
 */
public class ListaAlunoGUI extends JPanel {

	/*
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable tbDados;
	private ArrayList<Float> resultado = new ArrayList<Float>();
	private JComboBox<String> cbAluno = new JComboBox<String>();
	private JTable tbEndereco = new JTable();
	Connection connection;
	public ListaAlunoGUI() throws SQLException, ClassNotFoundException {
		 
		try {
			connection = ConnectionFactory.getConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setBackground(new Color(245, 245, 245));

		setBounds(50, 30, 852, 376);

		setLayout(null);

		JPanel paneEnd = new JPanel();
		paneEnd.setBackground(new Color(245, 245, 245));
		paneEnd.setBorder(new TitledBorder(null, "Endere\u00E7o",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		paneEnd.setBounds(34, 88, 775, 122);
		add(paneEnd);
		paneEnd.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(28, 23, 709, 81);
		paneEnd.add(scrollPane);

		scrollPane.setViewportView(tbEndereco);

		JPanel nomeAluno = new JPanel();
		nomeAluno.setBackground(new Color(245, 245, 245));
		nomeAluno.setBorder(new TitledBorder(null,
				"Digite o nome do aluno cadastrado", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		nomeAluno.setBounds(34, 12, 600, 76);
		add(nomeAluno);
		nomeAluno.setLayout(null);
		cbAluno.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				try {
					//usando o script sql para fazer a seleção e preencher a tabela corretamente
					//nao mude daqui para baixo
					String sqlEndereco = "select endereco.rua,endereco.casa," +
							" cidade.nome, bairro.nome, estado.nome, endereco.complemento, endereco.telefone" +
							" from aluno inner join endereco on endereco.idendereco = aluno.endereco_idendereco" +
							" and aluno.nomeAluno = '"+ cbAluno.getSelectedItem().toString()+"'" +
							"inner join bairro on endereco.bairro_idbairro = bairro.idbairro " +
							"inner join cidade on endereco.cidade_idcidade = cidade.idcidade " +
							"inner join estado on endereco.estado_idestado = estado.idestado";
					
					String sqlDados = "select turma.nomeTurma, disciplinas.nome, disciplinasDoAluno.media,disciplinasDoAluno.resultado from disciplinasDoAluno" +
							" inner join disciplinas on disciplinasDoAluno.disciplinas_idDisciplinas = disciplinas.idDisciplinas" +
							" and disciplinasDoAluno.aluno_nomeAluno = '"+cbAluno.getSelectedItem().toString()+"'" +
							" inner join aluno on disciplinasDoAluno.aluno_turma_idTurma = aluno.turma_idTurma " +
							" inner join turma on aluno.turma_idTurma = turma.idTurma 	" ;
						
					preencherTabelaEndereco(sqlEndereco);
					preencherTabelaDados(sqlDados);
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		

		cbAluno.setEditable(true);
		DefaultComboBoxModel<String> modeloAluno = new DefaultComboBoxModel<String>();
		ArrayList<Aluno> listaAluno = new ArrayList<Aluno>();
		modeloAluno.addElement("");
		listaAluno = new AlunoDAO().listaNomeAluno();
		for (Aluno aluno : listaAluno) {
			modeloAluno.addElement(aluno.getNomeAluno().toString());
		}
		AutoCompleteDecorator.decorate(cbAluno);
		cbAluno.setModel(modeloAluno);
		cbAluno.setBounds(28, 26, 506, 25);
		nomeAluno.add(cbAluno);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Dados Complementares",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(44, 222, 726, 133);
		add(panel_1);
		panel_1.setLayout(null);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(25, 23, 637, 96);
		panel_1.add(scrollPane_1);

		tbDados = new JTable();
		scrollPane_1.setViewportView(tbDados);

		
	}

	public void preencherTabelaEndereco(String sql) throws ClassNotFoundException,
			SQLException {
		ArrayList<Object[]> dados = new ArrayList<Object[]>();
		String[] colunas = new String[] {" Rua", "Casa", "Bairro","Complemento","Telefone","Cidade", "Estado"};
		Endereco aluno = new Endereco();
		
		PreparedStatement stmt = connection.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		
		// para completar todas as linhas da tabela
		while (rs.next()){
			
			aluno.setRua(rs.getString("endereco.rua")); //rua
			aluno.setNumeroDaCasa(rs.getString("endereco.casa")); //casa
			aluno.setBairro(rs.getString("bairro.nome")); //bairro
			aluno.setComplemento(rs.getString("endereco.complemento")); //complemento
			aluno.setTelefone(rs.getString("endereco.telefone"));//telefone
			aluno.setCidade(rs.getString("cidade.nome"));//cidade
			aluno.setEstado(rs.getString("estado.nome")); //estado
			
		dados.add(new Object[] { 
				aluno.getRua(),
				aluno.getNumeroDaCasa(),
				aluno.getBairro(),
				aluno.getComplemento(),
				aluno.getTelefone(),
				aluno.getCidade(),
				aluno.getEstado()
				 });
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
		String[] colunas = new String[] { "Turma", "Disciplinas", "Médias","Resultado" };
		Turma turma = new Turma();
		
		PreparedStatement stmt = connection.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		Aluno aluno = new Aluno();
		Disciplinas materia = new Disciplinas();
		//para preencher todas as linhas da tabela
		
		while(rs.next()){
			turma.setNomeTurma(rs.getString("turma.nomeTurma"));
			materia.setNomeDisciplina(rs.getString("disciplinas.nome"));
			aluno.setMedia(rs.getFloat("disciplinasDoAluno.media"));
			aluno.setResultado(rs.getString("disciplinasDoAluno.resultado"));
		dados.add(new Object[] {
				turma.getNomeTurma(),
				materia.getNomeDisciplina(),
				aluno.getMedia(),
				aluno.getResultado()
				
		});
		}
	
		ModeloTabela modelo = new ModeloTabela(dados, colunas);
		tbDados.setModel(modelo);
		tbDados.getColumnModel().getColumn(0).setPreferredWidth(120);
		tbDados.getColumnModel().getColumn(0).setResizable(true);
		tbDados.getColumnModel().getColumn(1).setPreferredWidth(400);
		tbDados.getColumnModel().getColumn(1).setResizable(true);
		tbDados.getColumnModel().getColumn(2).setPreferredWidth(120);
		tbDados.getColumnModel().getColumn(2).setResizable(true);
		tbDados.getColumnModel().getColumn(3).setPreferredWidth(120);
		tbDados.getColumnModel().getColumn(3).setResizable(true);
	
		tbDados.setAutoResizeMode(tbEndereco.AUTO_RESIZE_OFF);
		tbDados.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		stmt.close();
		rs.close();
	
	}

}
