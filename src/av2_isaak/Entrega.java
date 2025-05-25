package av2_isaak;

public class Entrega {
	
	private int id;
    private String data;
    private String destino;
    private Status status;
    private Entregador entregador;
    private Veiculo veiculo;
	    
    public Entrega() {}

    public Entrega(int id, String data, String destino, Status status, Entregador entregador, Veiculo veiculo) {
        this.id = id;
        this.data = data;
        this.destino = destino;
        this.status = status;
        this.entregador = entregador;
        this.veiculo = veiculo;
    }
    
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getData() {
		return this.data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getDestino() {
		return this.destino;
	}
	public void setDestino(String destino) {
		this.destino = destino;
	}
	public Status getStatus() {
		return this.status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public Entregador getEntregador() {
		return this.entregador;
	}
	public void setEntregador(Entregador entregador) {
		this.entregador = entregador;
	}
	public Veiculo getVeiculo() {
		return this.veiculo;
	}
	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}
	
	public String toString() {
		return "Entrega [getId()=" + getId() + ", getData()=" + getData() + ", getDestino()=" + getDestino()
				+ ", getStatus()=" + getStatus() + ", getEntregador()=" + getEntregador() + ", getVeiculo()="
				+ getVeiculo() + "]";
	}    

}
