package classes.backend;
/*Classe responsável por testes e chama as outras classes do BackEnd.
 * Este sistema esta sendo desenvolvido por um amador. Esforçando em aprender e diseminar conhecimento. 
 * Caio Cutrim, nascido em 21/11/1989, Sao Luis, MA.
 * 
 */


import java.sql.SQLException;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import gui.JanelaPrincipalGUI;

public class Main {

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException, UnsupportedLookAndFeelException {
		
		
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		JanelaPrincipalGUI janela = new JanelaPrincipalGUI();	
		janela.iniciarJanela();
			
		}

}
