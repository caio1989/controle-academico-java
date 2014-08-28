package factory;


import java.sql.Connection; // conexao SQL para Java 
import java.sql.DriverManager; // gerenciador do drive de  conexão SQL para Java 
import java.sql.SQLException;



//essa versao do jdbc nao precisa de registro, ele era ativados atraves do metodo classForName que carregava esse drive

public class ConnectionFactory

{  
public static Connection getConnection() throws ClassNotFoundException  { 	 	
	
     try  
     	{  
    	 
     		return  DriverManager.getConnection("jdbc:mysql://localhost:3306/controleacademico", "root", "153ca");
    
     	}  
     
     catch(SQLException e )  
     {         
    	 	throw new RuntimeException(e);
    	 
          
     }  

	}  
}  


