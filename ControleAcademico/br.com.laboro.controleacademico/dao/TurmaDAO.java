package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import classes.backend.Aluno;
import classes.backend.Turma;
import factory.ConnectionFactory;

public class TurmaDAO {
	private Connection connection;
	long idTurma;
	String nomeTurma, turno;
	int numeroTurma;
	
	public TurmaDAO(){
		try {
			this.connection = ConnectionFactory.getConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	//Adicionando a turma no Mysql
	public void adicionarTurma(Turma turma){
		
		
		
		String sql = "INSERT INTO turma(idTurma,nomeTurma, ano, turno) VALUES(?,?,?,?)";
	
	try{
		PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		stmt.setLong(1, turma.getIdTurma());
		stmt.setString(2, turma.getNomeTurma());
		stmt.setInt(3, turma.getAno());
		stmt.setString(4, turma.getTurno());
		
		stmt.addBatch();
		stmt.executeBatch();
		
		stmt.close();
	
	}catch(Exception e){
		throw new RuntimeException(e);
		}
	
	}public ArrayList<Turma> lerNomeTurma() throws SQLException{
		
		String sql = "SELECT nomeTurma FROM turma";
		ArrayList<Turma> lista = new ArrayList<Turma>();
		
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				Turma turma = new Turma();
				turma.setNomeTurma(rs.getString("nomeTurma").toString());
				lista.add(turma);
			
			}
			
		stmt.close();
		rs.close();
		connection.close();
		return lista;
		
	}
	
}
	
