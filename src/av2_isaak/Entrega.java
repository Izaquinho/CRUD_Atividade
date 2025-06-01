package av2_isaak;

public class Entrega {
	
	private int id;
    private String data;
    private String destino;
    private Status status;
    private Entregador entregador;
    private Veiculo veiculo;
    private Produto produto;
	    
    public Entrega() {}

    public Entrega(int id, String data, String destino, Status status, Entregador entregador, Veiculo veiculo, Produto produto) {
        this.id = id;
        this.data = data;
        this.destino = destino;
        this.status = status;
        this.entregador = entregador;
        this.veiculo = veiculo;
        this.produto = produto;
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
	
	public Produto getProduto() {
		return this.produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
}
