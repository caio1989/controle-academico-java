package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;

import classes.backend.Endereco;
import factory.ConnectionFactory;

public class EnderecoDAO {
	private Connection connection;

	public EnderecoDAO() {
		try {
			connection = ConnectionFactory.getConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArrayList<Endereco> listarBairro() throws SQLException {
		ArrayList<Endereco> lista = new ArrayList<Endereco>();
		String sql = "Select bairro.nome from bairro";
		PreparedStatement stmt = connection.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			Endereco end = new Endereco();
			end.setBairro(rs.getString("nome").toString());
			lista.add(end);
		}
		stmt.close();
		connection.close();
		return lista;

	}
	// esse metodo filtra a lista de cidades do pais apartir de uma selecao do estado
	public ArrayList<Endereco> listarCidade(Endereco endereco)
			throws SQLException {
		ArrayList<Endereco> lista = new ArrayList<Endereco>();
		String sql = "Select cidade.nome, estado.nome from cidade inner join estado on cidade.estado_idestado = estado.idestado and estado.nome = '"
				+ endereco.getEstado() + "'";
		PreparedStatement stmt = connection.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			Endereco end = new Endereco();
			end.setCidade(rs.getString("cidade.nome").toString());
			lista.add(end);
		}
		stmt.close();
		connection.close();
		return lista;

	}
	public ArrayList<Endereco> listarEstado() throws SQLException {
		ArrayList<Endereco> lista = new ArrayList<Endereco>();
		String sql = "Select estado.nome from estado";
		PreparedStatement stmt = connection.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			Endereco end = new Endereco();
			end.setEstado(rs.getString("nome").toString());
			lista.add(end);
		}
		stmt.close();
		connection.close();
		return lista;
	}
	//metodo responsavel por adcionar os enderecos na tabela endereco
		public void adcionarEndereco(Endereco endereco) throws SQLException {
		
			String sql1 = "Select idcidade from cidade where nome = '" + endereco.getCidade()
					+ "'";
			PreparedStatement stmt1 = connection.prepareStatement(sql1);
			ResultSet rs = stmt1.executeQuery();
			while (rs.next()) {
				endereco.setIdCidade(rs.getInt("idcidade"));
			}
			rs.close();
			stmt1.close();

			String sql2 = "Insert into bairro(idbairro, nome, cidade_idcidade) values(?,?,'"
					+ endereco.getIdCidade() + "')";
			PreparedStatement stmt = connection.prepareStatement(sql2,
					Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, endereco.getIdBairro());
			stmt.setString(2, endereco.getBairro());
			stmt.executeUpdate();
			stmt.close();
			String sql3 = "Select bairro.idbairro, cidade.idcidade, estado.idestado from bairro inner join cidade on"
					+ " bairro.cidade_idcidade = cidade.idcidade and bairro.nome = '"
					+ endereco.getBairro()
					+ "' inner join"
					+ " estado on cidade.estado_idestado = estado.idestado and cidade.nome = '"
					+ endereco.getCidade() + "'";
			PreparedStatement stmt2 = connection.prepareStatement(sql3);
			ResultSet rs2 = stmt2.executeQuery();
			while (rs2.next()) {
				endereco.setIdBairro(rs2.getInt("bairro.idbairro"));
				endereco.setIdEstado(rs2.getInt("estado.idestado"));
				endereco.setIdCidade(rs2.getInt("cidade.idcidade"));
			
			}
			rs2.close();
			stmt2.close();

			String sql4 = "Insert into endereco(bairro_idbairro, cidade_idcidade, estado_idestado, rua, telefone, complemento, casa, idendereco) values('"
					+ endereco.getIdBairro()
					+ "','"
					+ endereco.getIdCidade()
					+ "','"
					+ endereco.getIdEstado()
					+ "', ?, ?, ?, ?, ?)";
			PreparedStatement stmt4 = connection.prepareStatement(sql4,
					Statement.RETURN_GENERATED_KEYS);
			stmt4.setString(1, endereco.getRua());
			stmt4.setString(2, endereco.getTelefone());
			stmt4.setString(3, endereco.getComplemento());
			stmt4.setString(4, endereco.getNumeroDaCasa());
			stmt4.setInt(5, endereco.getIdEnd());

			stmt4.executeUpdate();
			stmt4.close();
		
		}

}
