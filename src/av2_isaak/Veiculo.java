package av2_isaak;

public class Veiculo {
	
	private int id;
    private String placa;
    private String modelo;
    private String tipo;
    	
    public Veiculo() {}

    public Veiculo(int id, String placa, String modelo, String tipo) {
        this.id = id;
        this.placa = placa;
        this.modelo = modelo;
        this.tipo = tipo;
    }
    
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPlaca() {
		return this.placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public String getModelo() {
		return this.modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public String getTipo() {
		return this.tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public String toString() {
		return "Veiculo [getId()=" + getId() + ", getPlaca()=" + getPlaca() + ", getModelo()=" + getModelo()
				+ ", getTipo()=" + getTipo() + "]";
	}
    
	public boolean equals(Object o) {
	    if (this == o) return true;
	    if (o == null || getClass() != o.getClass()) return false;
	    Veiculo that = (Veiculo) o;
	    return id == that.id;
	}
}
