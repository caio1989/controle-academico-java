package classes.backend;

import java.util.ArrayList;

public class Aluno extends Pessoa {

	int idAluno, turma_idTurma;
	String nomeAluno, resultado;
	ArrayList<Float> notas;
	float media;

	// Aluno
	public String getNomeAluno() {
		return nomeAluno;
	}

	public void setNomeAluno(String nomAluno) {
		this.nomeAluno = nomAluno;
	}
	
	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	public int getId() {
		return idAluno;

	}

	public void setId(int idAluno) {
		this.idAluno = idAluno;
	}

	// disciplinas do aluno
	public ArrayList<Float> getNotas() {
		return notas;
	}

	public void setNotas(ArrayList<Float> notas) {
		this.notas = notas;
	}

	public float getMedia() {
		return media;
	}

	public void setMedia(float media) {
		
		this.media = media;
	}

}
