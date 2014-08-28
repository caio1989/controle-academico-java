package classes.backend;

import java.awt.event.InputMethodEvent;

import javax.swing.text.Caret;

public class Turma {
	int idTurma;
	public String nomeTurma, turno ;
	int ano;
	
	//Turma
	
	public int getIdTurma(){
			return idTurma;
	}
	public void setIdTurma(int idTurma) {
	        this.idTurma = idTurma;
	}
	public String getNomeTurma(){
		return nomeTurma;
	}
	public void setNomeTurma(String nomeTurma){
		this.nomeTurma = nomeTurma;
	}
	
	public int getAno(){
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
		
	}
	
	public String getTurno(){
		return turno;
	}
	public void setTurno (String turno){
		this.turno = turno;
	}
	
	
	
}
