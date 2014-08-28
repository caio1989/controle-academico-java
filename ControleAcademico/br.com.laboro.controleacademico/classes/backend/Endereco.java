package classes.backend;

public class Endereco {
	protected int idEnd, idcidade, idbairro, idestado;
	protected String rua, bairro, complemento, cidade, estado, telefone;
	protected String numero;
	
	
	public int getIdBairro() {
		return idbairro;
	}

	public void setIdBairro(int idbairro) {
		this.idbairro = idbairro;
	}
	
	public int getIdCidade() {
		return idcidade;
	}

	public void setIdCidade(int idcidade) {
		this.idcidade = idcidade;
	}
	
	public int getIdEstado() {
		return idestado;
	}

	public void setIdEstado(int idestado) {
		this.idestado = idestado;
	}

	public int getIdEnd() {
		return idEnd;
	}

	public void setIdEnd(int idEnd) {
		this.idEnd = idEnd;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getNumeroDaCasa() {
		return numero;
	}

	public void setNumeroDaCasa(String numero) {
		this.numero = numero;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
}
