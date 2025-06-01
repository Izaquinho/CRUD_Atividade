package av2_isaak;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class AbaVeiculoPanel extends JPanel {
	private JTextField txtPlaca, txtModelo, txtTipo;
	private JButton btSalvar, btLimpar, btExcluir, btAtualizar;
	private JTable tabelaVeiculos;
	private DefaultTableModel modeloTabela;

	private int idVeiculoSelecionado = -1;

	public AbaVeiculoPanel() {
		setLayout(new BorderLayout());

		JPanel painelCampos = new JPanel(new GridLayout(4, 2, 5, 5));

		painelCampos.add(new JLabel("Placa:"));
		txtPlaca = new JTextField();
		painelCampos.add(txtPlaca);

		painelCampos.add(new JLabel("Modelo:"));
		txtModelo = new JTextField();
		painelCampos.add(txtModelo);

		painelCampos.add(new JLabel("Tipo:"));
		txtTipo = new JTextField();
		painelCampos.add(txtTipo);

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

		modeloTabela = new DefaultTableModel(new Object[] { "ID", "Placa", "Modelo", "Tipo" }, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tabelaVeiculos = new JTable(modeloTabela);
		JScrollPane scroll = new JScrollPane(tabelaVeiculos);
		add(scroll, BorderLayout.CENTER);

		btLimpar.addActionListener(e -> limparCampos());

		btSalvar.addActionListener(e -> {
			try {
				String placa = txtPlaca.getText().trim();
				String modelo = txtModelo.getText().trim();
				String tipo = txtTipo.getText().trim();

				if (placa.isEmpty() || modelo.isEmpty() || tipo.isEmpty()) {
					JOptionPane.showMessageDialog(this, "Todos os campos devem ser preenchidos.");
					return;
				}

				VeiculoDAO dao = new VeiculoDAO();

				if (idVeiculoSelecionado == -1) {
					dao.salvar(new Veiculo(0, placa, modelo, tipo));
					JOptionPane.showMessageDialog(this, "Veículo salvo!");
				} else {
					dao.atualizar(new Veiculo(idVeiculoSelecionado, placa, modelo, tipo));
					JOptionPane.showMessageDialog(this, "Veículo atualizado!");
				}

				limparCampos();
				atualizarTabela(dao.listar());
				idVeiculoSelecionado = -1;

			} catch (Exception ex) {
				JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
			}
		});

		btExcluir.addActionListener(e -> {
			if (idVeiculoSelecionado != -1) {
				VeiculoDAO dao = new VeiculoDAO();
				dao.excluir(idVeiculoSelecionado);
				JOptionPane.showMessageDialog(this, "Veículo excluído!");
				limparCampos();
				atualizarTabela(dao.listar());
				idVeiculoSelecionado = -1;
			} else {
				JOptionPane.showMessageDialog(this, "Selecione um veículo para excluir.");
			}
		});

		btAtualizar.addActionListener(e -> {
			VeiculoDAO dao = new VeiculoDAO();
			atualizarTabela(dao.listar());
		});

		tabelaVeiculos.getSelectionModel().addListSelectionListener(e -> {
			if (!e.getValueIsAdjusting() && tabelaVeiculos.getSelectedRow() != -1) {
				carregarVeiculoSelecionado(tabelaVeiculos.getSelectedRow());
			}
		});

		atualizarTabela(new VeiculoDAO().listar());
	}

	private void limparCampos() {
		txtPlaca.setText("");
		txtModelo.setText("");
		txtTipo.setText("");
		idVeiculoSelecionado = -1;
		tabelaVeiculos.clearSelection();
	}

	private void carregarVeiculoSelecionado(int linha) {
		idVeiculoSelecionado = (int) modeloTabela.getValueAt(linha, 0);
		txtPlaca.setText((String) modeloTabela.getValueAt(linha, 1));
		txtModelo.setText((String) modeloTabela.getValueAt(linha, 2));
		txtTipo.setText((String) modeloTabela.getValueAt(linha, 3));
	}

	public void atualizarTabela(List<Veiculo> veiculos) {
		modeloTabela.setRowCount(0);
		for (Veiculo v : veiculos) {
			modeloTabela.addRow(new Object[] { v.getId(), v.getPlaca(), v.getModelo(), v.getTipo() });
		}
	}
}