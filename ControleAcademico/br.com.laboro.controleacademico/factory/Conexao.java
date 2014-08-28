package factory;

import java.sql.Connection;

import java.sql.SQLException;

import javax.swing.JOptionPane;
// classe responsavel por testar a conexao
public class Conexao {
    public void conectar() throws SQLException, ClassNotFoundException {
        Connection connection = new ConnectionFactory().getConnection();
       // JOptionPane.showMessageDialog(null, "Conectado com sucesso ao banco!");
        
    }
    public void desconectar() throws SQLException, ClassNotFoundException{
    	Connection connection = new ConnectionFactory().getConnection();
      //  JOptionPane.showMessageDialog(null, "Desconectado com sucesso ao banco!");
    	connection.close();
    	
    }
   
}

