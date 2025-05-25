package av2_isaak;

public class Entregador {

	private int id;
    private String nome;
    private String cpf;
    private String telefone;
    
    public Entregador() {}

    public Entregador(int id, String nome, String cpf, String telefone) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
    }
    public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return this.nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return this.cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getTelefone() {
		return this.telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public String toString() {
		return "Entregador [getId()=" + getId() + ", getNome()=" + getNome() + ", getCpf()=" + getCpf()
				+ ", getTelefone()=" + getTelefone() ;
	}
	
	public boolean equals(Object o) {
	    if (this == o) return true;
	    if (o == null || getClass() != o.getClass()) return false;
	    Entregador that = (Entregador) o;
	    return id == that.id;
	}
	
}
