package av2_isaak;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class AbaEntregaPanel extends JPanel {
	private JTextField txtData, txtDestino;
	private JComboBox<Status> cbStatus;
	private JComboBox<Entregador> cbEntregador;
	private JComboBox<Veiculo> cbVeiculo;
	private JComboBox<Produto> cbProduto;
	private JButton btSalvar, btAtualizar, btExcluir, btLimpar;
	private JTable tabela;
	private DefaultTableModel modelo;
	private int idEntregaSelecionada = -1;

	public AbaEntregaPanel() {
		setLayout(new BorderLayout());

		JPanel painelCampos = new JPanel(new GridLayout(6, 2, 5, 5));
		txtData = new JTextField();
		txtDestino = new JTextField();
		cbStatus = new JComboBox<>(Status.values());
		cbEntregador = new JComboBox<>();
		cbVeiculo = new JComboBox<>();
		cbProduto = new JComboBox<>();

		painelCampos.add(new JLabel("Data (yyyy-MM-dd):"));
		painelCampos.add(txtData);
		painelCampos.add(new JLabel("Destino:"));
		painelCampos.add(txtDestino);
		painelCampos.add(new JLabel("Status:"));
		painelCampos.add(cbStatus);
		painelCampos.add(new JLabel("Entregador:"));
		painelCampos.add(cbEntregador);
		painelCampos.add(new JLabel("Veículo:"));
		painelCampos.add(cbVeiculo);
		painelCampos.add(new JLabel("Produto:"));
		painelCampos.add(cbProduto);

		JPanel painelBotoes = new JPanel();
		btSalvar = new JButton("Salvar");
		btAtualizar = new JButton("Atualizar");
		btExcluir = new JButton("Excluir");
		btLimpar = new JButton("Limpar");
		painelBotoes.add(btSalvar);
		painelBotoes.add(btAtualizar);
		painelBotoes.add(btExcluir);
		painelBotoes.add(btLimpar);

		JPanel painelTopo = new JPanel(new BorderLayout());
		painelTopo.add(painelCampos, BorderLayout.CENTER);
		painelTopo.add(painelBotoes, BorderLayout.SOUTH);
		add(painelTopo, BorderLayout.NORTH);

		modelo = new DefaultTableModel(
				new Object[] { "ID", "Data", "Destino", "Status", "Entregador", "Veículo", "Produto" }, 0) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tabela = new JTable(modelo);
		add(new JScrollPane(tabela), BorderLayout.CENTER);

		atualizarComboBoxes();
		atualizarTabela();

		btSalvar.addActionListener(e -> salvarEntrega());
		btAtualizar.addActionListener(e -> atualizarEntrega());
		btExcluir.addActionListener(e -> excluirEntrega());
		btLimpar.addActionListener(e -> limparCampos());

		tabela.getSelectionModel().addListSelectionListener(e -> {
			if (!e.getValueIsAdjusting() && tabela.getSelectedRow() != -1) {
				carregarEntregaSelecionada(tabela.getSelectedRow());
			}
		});
	}

	public void atualizarComboBoxes() {
		setListaEntregadores(new EntregadorDAO().listar());
		setListaVeiculos(new VeiculoDAO().listar());
		setListaProdutos(new ProdutoDAO().listar());
	}

	private void salvarEntrega() {
		try {
			Entrega entrega = new Entrega(0, txtData.getText(), txtDestino.getText(),
					(Status) cbStatus.getSelectedItem(), (Entregador) cbEntregador.getSelectedItem(),
					(Veiculo) cbVeiculo.getSelectedItem(), (Produto) cbProduto.getSelectedItem());
			new EntregaDAO().salvar(entrega);
			JOptionPane.showMessageDialog(this, "Entrega salva!");
			limparCampos();
			atualizarTabela();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this, "Erro ao salvar: " + ex.getMessage());
		}
	}

	private void atualizarEntrega() {
		if (idEntregaSelecionada == -1) {
			JOptionPane.showMessageDialog(this, "Selecione uma entrega para atualizar.");
			return;
		}
		try {
			Entrega entrega = new Entrega(idEntregaSelecionada, txtData.getText(), txtDestino.getText(),
					(Status) cbStatus.getSelectedItem(), (Entregador) cbEntregador.getSelectedItem(),
					(Veiculo) cbVeiculo.getSelectedItem(), (Produto) cbProduto.getSelectedItem());
			new EntregaDAO().atualizar(entrega);
			JOptionPane.showMessageDialog(this, "Entrega atualizada!");
			limparCampos();
			atualizarTabela();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this, "Erro ao atualizar: " + ex.getMessage());
		}
	}

	private void excluirEntrega() {
		if (idEntregaSelecionada == -1) {
			JOptionPane.showMessageDialog(this, "Selecione uma entrega para excluir.");
			return;
		}
		new EntregaDAO().excluir(idEntregaSelecionada);
		JOptionPane.showMessageDialog(this, "Entrega excluída!");
		limparCampos();
		atualizarTabela();
	}

	private void limparCampos() {
		txtData.setText("");
		txtDestino.setText("");
		cbStatus.setSelectedIndex(0);
		cbEntregador.setSelectedIndex(0);
		cbVeiculo.setSelectedIndex(0);
		cbProduto.setSelectedIndex(0);
		idEntregaSelecionada = -1;
		tabela.clearSelection();
	}

	private void carregarEntregaSelecionada(int linha) {
		idEntregaSelecionada = (int) modelo.getValueAt(linha, 0);
		txtData.setText((String) modelo.getValueAt(linha, 1));
		txtDestino.setText((String) modelo.getValueAt(linha, 2));
		cbStatus.setSelectedItem(Status.valueOf((String) modelo.getValueAt(linha, 3)));

		cbEntregador.setSelectedItem((Entregador) modelo.getValueAt(linha, 4));
		cbVeiculo.setSelectedItem((Veiculo) modelo.getValueAt(linha, 5));
		cbProduto.setSelectedItem((Produto) modelo.getValueAt(linha, 6));
	}

	private void atualizarTabela() {
		modelo.setRowCount(0);
		List<Entrega> entregas = new EntregaDAO().listar();
		for (Entrega e : entregas) {
			modelo.addRow(new Object[] { e.getId(), e.getData(), e.getDestino(), e.getStatus().name(),
					e.getEntregador(), e.getVeiculo(), e.getProduto() });
		}
	}

	public void atualizarTabela(List<Entrega> entregas) {
		modelo.setRowCount(0);
		for (Entrega e : entregas) {
			modelo.addRow(new Object[] { e.getId(), e.getData(), e.getDestino(), e.getStatus().name(),
					e.getEntregador(), e.getVeiculo(), e.getProduto() });
		}
	}

	public void setListaEntregadores(List<Entregador> entregadores) {
		cbEntregador.removeAllItems();
		for (Entregador e : entregadores) {
			cbEntregador.addItem(e);
		}
	}

	public void setListaVeiculos(List<Veiculo> veiculos) {
		cbVeiculo.removeAllItems();
		for (Veiculo v : veiculos) {
			cbVeiculo.addItem(v);
		}
	}

	public void setListaProdutos(List<Produto> produtos) {
		cbProduto.removeAllItems();
		for (Produto p : produtos) {
			cbProduto.addItem(p);
		}
	}

}
