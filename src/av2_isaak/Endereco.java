package av2_isaak;

public class Endereco {
	
	private String rua;
    private String numero;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;
	
    private String getRua() {
		return this.rua;
	}
	private void setRua(String rua) {
		this.rua = rua;
	}
	private String getNumero() {
		return this.numero;
	}
	private void setNumero(String numero) {
		this.numero = numero;
	}
	private String getBairro() {
		return this.bairro;
	}
	private void setBairro(String bairro) {
		this.bairro = bairro;
	}
	private String getCidade() {
		return this.cidade;
	}
	private void setCidade(String cidade) {
		this.cidade = cidade;
	}
	private String getEstado() {
		return this.estado;
	}
	private void setEstado(String estado) {
		this.estado = estado;
	}
	private String getCep() {
		return this.cep;
	}
	private void setCep(String cep) {
		this.cep = cep;
	}
	
	public String toString() {
		return "Endereco [getRua()=" + getRua() + ", getNumero()=" + getNumero() + ", getBairro()=" + getBairro()
				+ ", getCidade()=" + getCidade() + ", getEstado()=" + getEstado() + ", getCep()=" + getCep() + "]";
	}  

}
