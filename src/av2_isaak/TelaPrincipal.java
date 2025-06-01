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

        abas.addTab("Entregadores", abaEntregador);
        abas.addTab("Veículos", abaVeiculo);
        abas.addTab("Produtos", abaProduto);
        abas.addTab("Entregas", abaEntrega);

        abas.addChangeListener(e -> {
            Component abaSelecionada = abas.getSelectedComponent();

            if (abaSelecionada instanceof AbaEntregaPanel) {
                abaEntrega.setListaEntregadores(new EntregadorDAO().listar());
                abaEntrega.setListaVeiculos(new VeiculoDAO().listar());
                abaEntrega.setListaProdutos(new ProdutoDAO().listar());
                abaEntrega.atualizarTabela(new EntregaDAO().listar());
            }
        });

        add(abas, BorderLayout.CENTER);

        carregarDadosIniciais();
    }

    private void carregarDadosIniciais() {
        abaEntregador.atualizarTabela(EntregadorDAO.listar());
        abaVeiculo.atualizarTabela(VeiculoDAO.listar());
        abaProduto.atualizarTabela(ProdutoDAO.listar());

        abaEntrega.setListaEntregadores(new EntregadorDAO().listar());
        abaEntrega.setListaVeiculos(new VeiculoDAO().listar());
        abaEntrega.setListaProdutos(new ProdutoDAO().listar());
        abaEntrega.atualizarTabela(new EntregaDAO().listar());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TelaPrincipal().setVisible(true);
        });
    }
}
