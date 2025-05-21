package av2_isaak;

public class Entregador {

	private int id;
    private String nome;
    private String cpf;
    private String telefone;
    private String email;
    private Endereco endereco;
	
    private int getId() {
		return this.id;
	}
	private void setId(int id) {
		this.id = id;
	}
	private String getNome() {
		return this.nome;
	}
	private void setNome(String nome) {
		this.nome = nome;
	}
	private String getCpf() {
		return this.cpf;
	}
	private void setCpf(String cpf) {
		this.cpf = cpf;
	}
	private String getTelefone() {
		return this.telefone;
	}
	private void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	private String getEmail() {
		return this.email;
	}
	private void setEmail(String email) {
		this.email = email;
	}
	private Endereco getEndereco() {
		return this.endereco;
	}
	private void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	public String toString() {
		return "Entregador [getId()=" + getId() + ", getNome()=" + getNome() + ", getCpf()=" + getCpf()
				+ ", getTelefone()=" + getTelefone() + ", getEmail()=" + getEmail() + ", getEndereco()=" + getEndereco()
				+ "]";
	}
	
}
