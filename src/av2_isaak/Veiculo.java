package av2_isaak;

public class Veiculo {
	
	private int id;
    private String placa;
    private String modelo;
    private String tipo;
	
    private int getId() {
		return this.id;
	}
	private void setId(int id) {
		this.id = id;
	}
	private String getPlaca() {
		return this.placa;
	}
	private void setPlaca(String placa) {
		this.placa = placa;
	}
	private String getModelo() {
		return this.modelo;
	}
	private void setModelo(String modelo) {
		this.modelo = modelo;
	}
	private String getTipo() {
		return this.tipo;
	}
	private void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public String toString() {
		return "Veiculo [getId()=" + getId() + ", getPlaca()=" + getPlaca() + ", getModelo()=" + getModelo()
				+ ", getTipo()=" + getTipo() + "]";
	}
    
}
