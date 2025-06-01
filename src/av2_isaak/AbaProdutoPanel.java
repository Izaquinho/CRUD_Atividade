package av2_isaak;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class AbaProdutoPanel extends JPanel {
	private JTextField txtNome, txtPeso, txtValor;
	private JButton btSalvar, btLimpar, btExcluir, btAtualizar;
	private JTable tabelaProdutos;
	private DefaultTableModel modeloTabela;

	private int idProdutoSelecionado = -1;

	public AbaProdutoPanel() {
		setLayout(new BorderLayout());

		JPanel painelCampos = new JPanel(new GridLayout(4, 2, 5, 5));
		painelCampos.add(new JLabel("Nome:"));
		txtNome = new JTextField();
		painelCampos.add(txtNome);

		painelCampos.add(new JLabel("Peso (kg):"));
		txtPeso = new JTextField();
		painelCampos.add(txtPeso);

		painelCampos.add(new JLabel("Valor (R$):"));
		txtValor = new JTextField();
		painelCampos.add(txtValor);

		btSalvar = new JButton("Salvar");
		btLimpar = new JButton("Limpar");
		btExcluir = new JButton("Excluir");
		btAtualizar = new JButton("Atualizar");

		JPanel painelBotoes = new JPanel();
		painelBotoes.add(btSalvar);
		painelBotoes.add(btLimpar);
		painelBotoes.add(btExcluir);
		painelBotoes.add(btAtualizar);

		JPanel topo = new JPanel(new BorderLayout());
		topo.add(painelCampos, BorderLayout.CENTER);
		topo.add(painelBotoes, BorderLayout.SOUTH);

		add(topo, BorderLayout.NORTH);

		modeloTabela = new DefaultTableModel(new Object[] { "ID", "Nome", "Peso", "Valor" }, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tabelaProdutos = new JTable(modeloTabela);
		JScrollPane scroll = new JScrollPane(tabelaProdutos);
		add(scroll, BorderLayout.CENTER);

		btLimpar.addActionListener(e -> limparCampos());

		btSalvar.addActionListener(e -> {
			try {
				String nome = txtNome.getText().trim();
				float peso = Float.parseFloat(txtPeso.getText().trim());
				float valor = Float.parseFloat(txtValor.getText().trim());

				if (nome.isEmpty()) {
					JOptionPane.showMessageDialog(this, "Nome não pode ser vazio.");
					return;
				}

				ProdutoDAO dao = new ProdutoDAO();

				if (idProdutoSelecionado == -1) {
					dao.salvar(new Produto(0, nome, peso, valor));
					JOptionPane.showMessageDialog(this, "Produto salvo!");
				} else {
					dao.atualizar(new Produto(idProdutoSelecionado, nome, peso, valor));
					JOptionPane.showMessageDialog(this, "Produto atualizado!");
				}

				limparCampos();
				atualizarTabela(dao.listar());
				idProdutoSelecionado = -1;

			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(this, "Peso e Valor devem ser números válidos.");
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
			}
		});

		btExcluir.addActionListener(e -> {
			if (idProdutoSelecionado != -1) {
				ProdutoDAO dao = new ProdutoDAO();
				dao.excluir(idProdutoSelecionado);
				JOptionPane.showMessageDialog(this, "Produto excluído!");
				limparCampos();
				atualizarTabela(dao.listar());
				idProdutoSelecionado = -1;
			} else {
				JOptionPane.showMessageDialog(this, "Selecione um produto para excluir.");
			}
		});

		btAtualizar.addActionListener(e -> {
			ProdutoDAO dao = new ProdutoDAO();
			atualizarTabela(dao.listar());
		});

		tabelaProdutos.getSelectionModel().addListSelectionListener(e -> {
			if (!e.getValueIsAdjusting() && tabelaProdutos.getSelectedRow() != -1) {
				carregarProdutoSelecionado(tabelaProdutos.getSelectedRow());
			}
		});

		atualizarTabela(new ProdutoDAO().listar());
	}

	private void limparCampos() {
		txtNome.setText("");
		txtPeso.setText("");
		txtValor.setText("");
		idProdutoSelecionado = -1;
		tabelaProdutos.clearSelection();
	}

	private void carregarProdutoSelecionado(int linha) {
		idProdutoSelecionado = (int) modeloTabela.getValueAt(linha, 0);
		txtNome.setText((String) modeloTabela.getValueAt(linha, 1));
		txtPeso.setText(modeloTabela.getValueAt(linha, 2).toString());
		txtValor.setText(modeloTabela.getValueAt(linha, 3).toString());
	}

	public void atualizarTabela(List<Produto> produtos) {
		modeloTabela.setRowCount(0);
		for (Produto p : produtos) {
			modeloTabela.addRow(new Object[] { p.getId(), p.getNome(), p.getPeso(), p.getValor() });
		}
	}
}