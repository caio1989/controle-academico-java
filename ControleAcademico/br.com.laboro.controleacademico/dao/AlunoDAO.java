package dao;

import java.sql.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;


import classes.backend.Aluno;
import classes.backend.Endereco;
import classes.backend.Disciplinas;
import classes.backend.Turma;
import factory.ConnectionFactory;


/*essa classe segue partes das tecnicas do projeto de designer patterns que facilita a programação de um projeto extenso
 * por favor, se voce nao tiver os devidos conhecimentos, não altere dessa linha para baixo
 * CaioCutrim*/
public class AlunoDAO extends Endereco {
	private Connection connection;
	public AlunoDAO() {
		try {
			this.connection = ConnectionFactory.getConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void adcionarAluno(Aluno aluno, Turma turma, Endereco endereco)
			throws SQLException {
		String sql1 = "Select idTurma from turma  where nomeTurma = '"
				+ turma.getNomeTurma() + "'";

		PreparedStatement stmt1 = connection.prepareStatement(sql1);
		ResultSet rs1 = stmt1.executeQuery();

		while (rs1.next()) {
			turma.setIdTurma(rs1.getInt("idTurma"));
		}
		stmt1.close();
		rs1.close();
		String sql2 = "Select endereco.idendereco from endereco where endereco.rua = '"
				+ endereco.getRua() + "'";
		PreparedStatement stmt2 = connection.prepareStatement(sql2);
		ResultSet rs2 = stmt2.executeQuery();
		while (rs2.next()) {
			endereco.setIdEnd(rs2.getInt("idendereco"));
		}
		stmt2.close();
		rs2.close();
		
		String sql3 = "Insert into aluno(idAluno, nomeAluno, turma_idTurma, endereco_idendereco) value(?, ?, ?, ?)";
		PreparedStatement stmt3 = connection.prepareStatement(sql3,
				Statement.RETURN_GENERATED_KEYS);
		stmt3.setInt(1, aluno.getId());
		stmt3.setString(2, aluno.getNomeAluno());
		stmt3.setInt(3, turma.getIdTurma());
		stmt3.setInt(4, endereco.getIdEnd());
		stmt3.executeUpdate();
		stmt3.close();
	
	}

	public void DisciplinaAluno(Aluno aluno, Disciplinas disciplina)
			throws SQLException {
		Turma turma = new Turma();
		String sql1 = "Select idAluno, turma_idTurma from aluno  where nomeAluno = '"
				+ aluno.getNomeAluno() + "'";
		PreparedStatement stmt1 = connection.prepareStatement(sql1);
		ResultSet rs = stmt1.executeQuery();
			while (rs.next()) {
				
				turma.setIdTurma(rs.getInt("turma_idTurma"));
				aluno.setId(rs.getInt("idAluno"));
				turma.setIdTurma(rs.getInt("turma_idTurma"));
		} 
			stmt1.close();
			rs.close();
		String sql_select2 = "select idDisciplinas from disciplinas where nome = '"
				+ disciplina.getNomeDisciplina() + "'";
		PreparedStatement stmt2 = connection.prepareStatement(sql_select2);
		ResultSet rs2 = stmt2.executeQuery();
		while (rs2.next()) {
			
			disciplina.setId(rs2.getInt("idDisciplinas"));
		}
			stmt2.close();
			rs2.close();
		
		
		String sql_insert = 
				"insert disciplinasDoAluno"
				+ "(aluno_idAluno, aluno_turma_idTurma, disciplinas_idDisciplinas, nota_1, nota_2, nota_3, nota_4, nota_5, nota_6, nota_7, media, resultado)"
				+ " values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement stmt3 = connection.prepareStatement(sql_insert);
		stmt3.setInt(1,aluno.getId());
		stmt3.setInt(2,turma.getIdTurma());
		stmt3.setInt(3, disciplina.getId());
		//laco para pegar os dados das notas do aluno
		ArrayList<Float> nota= aluno.getNotas();
		int j=4;
		for(int i =0;i<nota.size();i++){
			stmt3.setFloat(j,nota.get(i));
			j++;
		}
		stmt3.setFloat(11, aluno.getMedia());
		stmt3.setString(12, aluno.getResultado());
		stmt3.executeUpdate();
		stmt3.close();
	}

	public String listarTurmaAluno(Aluno aluno) throws SQLException { /*ler 
	apenas o nome dos alunos*/

		String sql = "SELECT  turma.nomeTurma FROM turma INNER JOIN aluno on turma_idTurma = idTurma and nomeAluno = '"
				+ aluno.getNomeAluno() + "'";
		String lista = null;
		PreparedStatement stmt = connection.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			Turma turma = new Turma();
			turma.setNomeTurma(rs.getString("turma.nomeTurma").toString());
			lista = turma.getNomeTurma();
		}

		return lista;
	}

	public void deletarAluno(String deletar, Endereco end) { // para deletar o
															// aluno apartir de
															// uma seletivo no
															// combo
		int idResult = 0;
		String sql1 = "Select nomeAluno, idAluno from aluno inner join endereco on endAluno.end_idEnd = idEnd and nomeAluno = '"
				+ deletar + "'";
		String sql2 = "Delete from endAluno where aluno_nomeAluno = '"
				+ deletar + "'";

		try {
			PreparedStatement stmt1 = connection.prepareStatement(sql1);
			PreparedStatement stmt2 = connection.prepareStatement(sql2);

			ResultSet rs = stmt1.executeQuery();
			while (rs.next()) {
				end.setIdEnd(rs.getInt("idEnd"));
				idResult = end.getIdEnd();
			}
			String sql3 = "Delete from endereco where idEnd = '" + idResult
					+ "'";
			PreparedStatement stmt3 = connection.prepareStatement(sql3);

			rs.close();
			stmt1.executeUpdate();
			stmt1.close();
			stmt2.executeUpdate();
			stmt2.close();
			stmt3.executeUpdate();
			stmt3.close();
			JOptionPane.showMessageDialog(null, "Aluno deletado com sucesso!");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"Nenhum aluno encontrado! \nErro: " + e.getMessage());

		}

	}
	public ArrayList<Aluno> listaNomeAluno() throws SQLException {
		ArrayList<Aluno> lista = new ArrayList<Aluno>();
		String sql = "Select aluno.nomeAluno from aluno";
		PreparedStatement stmt = connection.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			Aluno aluno = new Aluno();
			aluno.setNomeAluno(rs.getString("aluno.nomeAluno"));
			aluno.getNomeAluno();
			lista.add(aluno);
		}
		stmt.close();
		return lista;
	}

	/*
	 * 
	 * listas para serem usadas nos campos de texto
	 */

	public String listarEstadoAluno(Aluno aluno) throws SQLException {
		String lista = null;
		String sql = "Select estado.nome from endereco inner join estado on estado.idestado = endereco.estado_idestado"
				+ " inner join aluno on endereco.idendereco = aluno.endereco_idendereco and aluno.nomeAluno = '"
				+ aluno.getNomeAluno() + "'";
		PreparedStatement stmt = connection.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			Endereco end = new Endereco();
			end.setEstado(rs.getString("estado.nome").toString());
			lista = end.getEstado();
		}
		rs.close();
		stmt.close();
	
		return lista;

	}
	public String listarBairroAluno(Aluno aluno) throws SQLException {
		String lista = null;
		String sql = "Select bairro.nome from endereco inner join bairro on bairro.idbairro = endereco.bairro_idbairro"
				+ " inner join aluno on endereco.idendereco = aluno.endereco_idendereco and aluno.nomeAluno = '"
				+ aluno.getNomeAluno() + "'";
		PreparedStatement stmt = connection.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			Endereco end = new Endereco();
			end.setBairro(rs.getString("bairro.nome").toString());
			lista = end.getBairro();
		}
		rs.close();
		stmt.close();
	
		return lista;
	}
	public String listarCidadeAluno(Aluno aluno) throws SQLException {
		String lista = null;
		String sql = "Select cidade.nome from endereco inner join cidade on cidade.idcidade = endereco.cidade_idcidade"
				+ " inner join aluno on endereco.idendereco = aluno.endereco_idendereco and aluno.nomeAluno = '"
				+ aluno.getNomeAluno() + "'";
		PreparedStatement stmt = connection.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			Endereco end = new Endereco();
			end.setCidade(rs.getString("cidade.nome").toString());
			lista = end.getCidade();
		}
		rs.close();
		stmt.close();
	
		return lista;
	}
	public String listarRuaAluno(Aluno aluno) throws SQLException {
		String lista = null;
		String sql = "Select endereco.rua from endereco "
				+ " inner join aluno on endereco.idendereco = aluno.endereco_idendereco and aluno.nomeAluno = '"
				+ aluno.getNomeAluno() + "'";
		PreparedStatement stmt = connection.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			Endereco end = new Endereco();
			end.setRua(rs.getString("endereco.rua").toString());
			lista = end.getRua();
		}
		rs.close();
		stmt.close();
		return lista;
	}
	public String listarTelefoneAluno(Aluno aluno) throws SQLException {
		String lista = null;
		String sql = "Select endereco.telefone from endereco "
				+ " inner join aluno on endereco.idendereco = aluno.endereco_idendereco and aluno.nomeAluno = '"
				+ aluno.getNomeAluno() + "'";
		PreparedStatement stmt = connection.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			Endereco end = new Endereco();
			end.setTelefone(rs.getString("endereco.telefone").toString());
			lista = end.getTelefone();
		}
		rs.close();
		stmt.close();
	
		return lista;
	}
	public String listarCasaAluno(Aluno aluno) throws SQLException {
		String lista = null;
		String sql = "Select endereco.casa from endereco "
				+ " inner join aluno on endereco.idendereco = aluno.endereco_idendereco and aluno.nomeAluno = '"
				+ aluno.getNomeAluno() + "'";
		PreparedStatement stmt = connection.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			Endereco end = new Endereco();
			end.setNumeroDaCasa(rs.getString("endereco.casa").toString());
			lista = end.getNumeroDaCasa();
		}
		rs.close();
		stmt.close();
	
		return lista;
	}
	public String listarComplementoAluno(Aluno aluno) throws SQLException {
		String lista = null;
		String sql = "Select endereco.complemento from endereco "
				+ " inner join aluno on endereco.idendereco = aluno.endereco_idendereco and aluno.nomeAluno = '"
				+ aluno.getNomeAluno() + "'";
		PreparedStatement stmt = connection.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			Endereco end = new Endereco();
			end.setComplemento(rs.getString("endereco.complemento").toString());
			lista = end.getComplemento();
		}
		rs.close();
		stmt.close();

		return lista;
	}
	// alterar dados dos alunos
	public void alterarAluno(Aluno aluno, Endereco endereco,Turma turma)
			throws SQLException {
		String sql1 = "Select aluno.idAluno, aluno.nomeAluno,  endereco.idendereco, endereco.bairro_idbairro, endereco.cidade_idcidade, endereco.estado_idestado, aluno.turma_idTurma "
				+ " from aluno inner join endereco on endereco.idendereco = aluno.endereco_idendereco and aluno.nomeAluno = '"
				+ aluno.getNomeAluno()
				+ "'"
				+ " inner join turma on aluno.turma_idTurma = turma.idTurma";
		PreparedStatement stmt1 = connection.prepareStatement(sql1);
		ResultSet rs = stmt1.executeQuery();
		//instanciandos os objetos para iniciar os metodos implementados de cada classe
	
		
		while (rs.next()) {
			
			endereco.setIdBairro(rs.getInt("endereco.bairro_idbairro"));
			endereco.setIdCidade(rs.getInt("endereco.cidade_idcidade"));
			endereco.setIdEstado(rs.getInt("endereco.estado_idestado"));
			endereco.setIdEnd(rs.getInt("endereco.idendereco"));
			aluno.setId(rs.getInt("aluno.idAluno"));
			aluno.setNomeAluno(rs.getString("aluno.nomeAluno"));
			turma.setIdTurma(rs.getInt("aluno.turma_idTurma"));
			
		}
		String sql2 = "update aluno  inner join endereco on aluno.endereco_idendereco = endereco.idendereco and aluno.nomeAluno = '"
				+ aluno.getNomeAluno()
				+ "'"
				+ " inner join bairro on endereco.bairro_idbairro = '"
				+ endereco.getIdBairro()
				+ "'"
				+ " inner join cidade on endereco.cidade_idcidade = '"
				+ endereco.getIdCidade()
				+ "'"
				+ " inner join estado on endereco.estado_idestado = '"
				+ endereco.getIdEstado()
				+ "'"
				+ " inner join turma on aluno.turma_idTurma =  '"
				+ turma.getIdTurma()
				+ "'"
				+ " set aluno.nomeAluno = '"
				+ aluno.getNomeAluno()
				+ "', "
				+ "bairro.nome = '"
				+ endereco.getBairro()
				+ "', "
				+ "estado.nome = '"
				+ endereco.getEstado()
				+ "', "
				+ "endereco.casa = '"
				+ endereco.getNumeroDaCasa()
				+ "', "
				+ "endereco.telefone = '"
				+ endereco.getTelefone()
				+ "', "
				+ "endereco.complemento = '"
				+ endereco.getComplemento()
				+ "', "
				+ "turma.nomeTurma = '"
				+ turma.getNomeTurma()
				+ "'"
				+ " where idAluno = '"
				+ aluno.getId()
				+ "' and aluno.nomeAluno = '"
				+ aluno.getNomeAluno() + "'";

		PreparedStatement stmt2 = connection.prepareStatement(sql2);
		stmt2.executeUpdate();
		stmt2.close();
		stmt1.close();
	
	}

}