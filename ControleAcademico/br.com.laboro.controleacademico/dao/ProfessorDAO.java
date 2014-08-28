package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import com.mysql.jdbc.Statement;
import componentes.ModeloTabela;

import classes.backend.Aluno;
import classes.backend.Endereco;
import classes.backend.Disciplinas;
import classes.backend.Professor;
import classes.backend.Turma;
import factory.ConnectionFactory;

public class ProfessorDAO {
	private Connection connection;

	public ProfessorDAO() {
		try {
			this.connection = ConnectionFactory.getConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// esse metodo e constituido de 4 selecoes para estabelecer a conexao entre
	// as tabelas da base de dados
	public void adicionarProfessor(Professor professor, Disciplinas materia,
			Endereco enderecoProf, Turma turma) throws SQLException {
	
		//select do endereco
		String sqlSelect1 = "Select idEndereco from endereco where endereco.rua = '"
				+ enderecoProf.getRua() + "'";

		PreparedStatement stmt1 = connection.prepareStatement(sqlSelect1);
		ResultSet rs1 = stmt1.executeQuery();
		while (rs1.next()) {
			enderecoProf.setIdEnd(rs1.getInt("idEndereco"));
			
		}
		stmt1.close();
		rs1.close();
		//select das disciplinas
		String sqlSelect2 = "Select idDisciplinas from disciplinas where nome = '"
				+ materia.getNomeDisciplina() + "'";

		PreparedStatement stmt2 = connection.prepareStatement(sqlSelect2);
		ResultSet rs2 = stmt2.executeQuery();
		while (rs2.next()) {
			materia.setId(rs2.getInt("idDisciplinas"));
		}
		stmt2.close();
		rs2.close();
		
		String sqlInsert1 = "INSERT INTO professor(idProfessor, nomeProfessor, disciplinas_idDisciplinas, endereco_idEndereco) " +
				"VALUES(?,?,?,?)";
		PreparedStatement stmt3 = connection.prepareStatement(sqlInsert1,
				Statement.RETURN_GENERATED_KEYS);
		stmt3.setInt(1, professor.getId());
		stmt3.setString(2, professor.getNomeProf());
		stmt3.setInt(3, materia.getId());
		stmt3.setInt(4, enderecoProf.getIdEnd());
		
		stmt3.executeUpdate();
		stmt3.close();
		
		//select para resgatar o id e adciona-los a turma do professor
		String sqlSelect3 = "Select idTurma from turma where turma.NomeTurma = '"
				+ turma.getNomeTurma() + "'";
		PreparedStatement stmt4 = connection.prepareStatement(sqlSelect3);
		ResultSet rs3 = stmt4.executeQuery();
		while (rs3.next()) {
			turma.setIdTurma(rs3.getInt("idTurma"));
		}
		stmt4.close();
		rs3.close();
		//select para resgatar a chave primaria e o nome do professor da tabela professor
		String sqlSelect4 = "Select idProfessor, nomeProfessor from professor where nomeProfessor = '"+professor.getNomeProf()+"'";
		PreparedStatement stmt5 = connection.prepareStatement(sqlSelect4);
		ResultSet rs4 = stmt5.executeQuery();
		while(rs4.next()){
			professor.setId(rs4.getInt("idProfessor"));
			professor.setNomeProf(rs4.getString("nomeProfessor"));
			
		}
		stmt5.close();
		rs4.close();
		String sqlInsert2 = "Insert into turmasDoProfessor(professor_idProfessor,professor_nomeProfessor, turma_idTurma)"
				+ " values(?,?,?)";
		PreparedStatement stmt6 = connection.prepareStatement(sqlInsert2);
		stmt6.setInt(1, professor.getId());
		stmt6.setString(2, professor.getNomeProf());
		stmt6.setInt(3, turma.getIdTurma());
	
		stmt6.executeUpdate();
		stmt6.close();
		
	}
	

	public ArrayList<Professor> listarNomeProfessor() throws SQLException {

		String sql = "SELECT  nomeProfessor FROM professor";
		ArrayList<Professor> lista = new ArrayList<Professor>();

		PreparedStatement stmt = connection.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			Professor prof = new Professor();
			prof.setNomeProf(rs.getString("nomeProfessor"));
			lista.add(prof);
		}

		stmt.close();
		return lista;

	}
	/*string para ser usada em tabelas*/
	public String selectSqlDadosProfessor(Professor professor){
		String sql = "Select endereco.rua, endereco.telefone, endereco.casa, bairro.nome, cidade.nome, estado.nome, turma.nomeTurma, disciplinas.nome, professor.nomeProfessor from professor " +
				"inner join turmasDoProfessor on professor.idProfessor = turmasDoProfessor.professor_idprofessor " +
				"inner join endereco on endereco.idendereco = professor.endereco_idendereco " +
				"inner join bairro on bairro.idbairro = endereco.bairro_idbairro " +
				"inner join cidade on cidade.idcidade = endereco.cidade_idcidade " +
				"inner join estado on estado.idestado = endereco.estado_idestado " +
				"inner join turma on turmasDoProfessor.turma_idTurma = turma.idTurma " +
				"inner join disciplinas on disciplinas.idDisciplinas = professor.disciplinas_idDisciplinas  and professor.nomeProfessor = '"+professor.getNomeProf()+"'";
		return sql;
	}
	

}
