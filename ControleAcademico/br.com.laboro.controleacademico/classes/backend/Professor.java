
package classes.backend;
public class Professor extends Pessoa{
	int idProf;
	String nomeProf;
	
	public String getNomeProf(){
		return nomeProf;
	}
	public void setNomeProf(String nomeProf){
		this.nomeProf = nomeProf;
	}
	public  int getId(){
		return idProf;
	}
	public void setId(int id) {
        this.idProf = id;
    }

}
