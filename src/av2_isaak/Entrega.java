package av2_isaak;

public class Entrega {
	
	private int id;
    private String data;
    private String destino;
    private Status status;
    private Entregador entregador;
    private Veiculo veiculo;
	    
    private Entrega(int id, String data, String destino, Status status, Entregador entregador, Veiculo veiculo) {
		super();
		this.id = id;
		this.data = data;
		this.destino = destino;
		this.status = status;
		this.entregador = entregador;
		this.veiculo = veiculo;
	}
    
	private int getId() {
		return this.id;
	}
	private void setId(int id) {
		this.id = id;
	}
	private String getData() {
		return this.data;
	}
	private void setData(String data) {
		this.data = data;
	}
	private String getDestino() {
		return this.destino;
	}
	private void setDestino(String destino) {
		this.destino = destino;
	}
	private Status getStatus() {
		return this.status;
	}
	private void setStatus(Status status) {
		this.status = status;
	}
	private Entregador getEntregador() {
		return this.entregador;
	}
	private void setEntregador(Entregador entregador) {
		this.entregador = entregador;
	}
	private Veiculo getVeiculo() {
		return this.veiculo;
	}
	private void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}
	
	public String toString() {
		return "Entrega [getId()=" + getId() + ", getData()=" + getData() + ", getDestino()=" + getDestino()
				+ ", getStatus()=" + getStatus() + ", getEntregador()=" + getEntregador() + ", getVeiculo()="
				+ getVeiculo() + "]";
	}    

}
