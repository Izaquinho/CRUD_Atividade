package av2_isaak;

import javax.swing.*;
import java.awt.*;

public class TelaPrincipal extends JFrame {
	private AbaEntregadorPanel abaEntregador;
	private AbaVeiculoPanel abaVeiculo;
	private AbaProdutoPanel abaProduto;
	private AbaEntregaPanel abaEntrega;

	public TelaPrincipal() {
		setTitle("Sistema de Logística");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		setLocationRelativeTo(null);

		JTabbedPane abas = new JTabbedPane();

		abaEntregador = new AbaEntregadorPanel();
		abaVeiculo = new AbaVeiculoPanel();
		abaProduto = new AbaProdutoPanel();
		abaEntrega = new AbaEntregaPanel();

		abas.addTab("Veículos", abaVeiculo);
		abas.addTab("Produtos", abaProduto);

		add(abas, BorderLayout.CENTER);

		carregarDadosIniciais();
	}

	private void carregarDadosIniciais() {

		abaVeiculo.atualizarTabela(VeiculoDAO.listar());
		abaProduto.atualizarTabela(ProdutoDAO.listar());

	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			new TelaPrincipal().setVisible(true);
		});
	}
}