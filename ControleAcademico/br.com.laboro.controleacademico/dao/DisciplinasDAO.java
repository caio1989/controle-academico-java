package dao;

import java.sql.Array;
import java.sql.Connection;
import java.sql.SQLException;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import classes.backend.Disciplinas;
import factory.ConnectionFactory;
import java.sql.*;

import javax.swing.JOptionPane;

public class DisciplinasDAO {
	private Connection connection;

	public DisciplinasDAO() {
		try {
			this.connection = ConnectionFactory.getConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void adcionarDisciplina(Disciplinas disciplina){
		String sql = "Insert into disciplinas(idDisciplinas, nome) values(?, ?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1,disciplina.getId());
			stmt.setString(2, disciplina.getNomeDisciplina());
			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	
		
	}
	
	public ArrayList<Disciplinas> listarNomeDisciplinas() throws SQLException {
		ArrayList<Disciplinas>lista = new ArrayList<Disciplinas>();
		String sql = "Select  nome from disciplinas";
		PreparedStatement stmt = connection.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			Disciplinas disciplina = new Disciplinas();
			disciplina.setNomeDisciplina(rs.getString("nome").toString());
			lista.add(disciplina);
		}
		stmt.close();
		rs.close();
		return lista;
	}
}
