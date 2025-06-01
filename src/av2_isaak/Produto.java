package av2_isaak;

public class Produto {

	private int id;
	private String nome;
	private double peso;
	private float valor;

	public Produto() {
	};

	public Produto(int id, String nome, double peso, float valor) {
		this.id = id;
		this.nome = nome;
		this.peso = peso;
		this.valor = valor;
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

	public double getPeso() {
		return this.peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public float getValor() {
		return this.valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}
}