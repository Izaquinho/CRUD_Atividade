package av2_isaak;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class AbaEntregadorPanel extends JPanel {
	private JTextField txtNome, txtCpf, txtTelefone;
	private JButton btSalvar, btLimpar, btExcluir, btAtualizar;
	private JTable tabelaEntregadores;
	private DefaultTableModel modeloTabela;

	private int idEntregadorSelecionado = -1;

	public AbaEntregadorPanel() {
		setLayout(new BorderLayout());

		JPanel painelCampos = new JPanel(new GridLayout(4, 2, 5, 5));
		painelCampos.add(new JLabel("Nome:"));
		txtNome = new JTextField();
		painelCampos.add(txtNome);

		painelCampos.add(new JLabel("CPF:"));
		txtCpf = new JTextField();
		painelCampos.add(txtCpf);

		painelCampos.add(new JLabel("Telefone:"));
		txtTelefone = new JTextField();
		painelCampos.add(txtTelefone);

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

		modeloTabela = new DefaultTableModel(new Object[] { "ID", "Nome", "CPF", "Telefone" }, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tabelaEntregadores = new JTable(modeloTabela);
		JScrollPane scroll = new JScrollPane(tabelaEntregadores);
		add(scroll, BorderLayout.CENTER);

		btLimpar.addActionListener(e -> limparCampos());

		btSalvar.addActionListener(e -> {
			try {
				String nome = txtNome.getText().trim();
				String cpf = txtCpf.getText().trim();
				String telefone = txtTelefone.getText().trim();

				if (nome.isEmpty() || cpf.isEmpty() || telefone.isEmpty()) {
					JOptionPane.showMessageDialog(this, "Preencha todos os campos.");
					return;
				}

				EntregadorDAO dao = new EntregadorDAO();

				if (idEntregadorSelecionado == -1) {
					dao.salvar(new Entregador(0, nome, cpf, telefone));
					JOptionPane.showMessageDialog(this, "Entregador salvo!");
				} else {
					dao.atualizar(new Entregador(idEntregadorSelecionado, nome, cpf, telefone));
					JOptionPane.showMessageDialog(this, "Entregador atualizado!");
				}

				limparCampos();
				atualizarTabela(dao.listar());
				idEntregadorSelecionado = -1;

			} catch (Exception ex) {
				JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
			}
		});

		btExcluir.addActionListener(e -> {
			if (idEntregadorSelecionado != -1) {
				EntregadorDAO dao = new EntregadorDAO();
				dao.excluir(idEntregadorSelecionado);
				JOptionPane.showMessageDialog(this, "Entregador excluído!");
				limparCampos();
				atualizarTabela(dao.listar());
				idEntregadorSelecionado = -1;
			} else {
				JOptionPane.showMessageDialog(this, "Selecione um entregador para excluir.");
			}
		});

		btAtualizar.addActionListener(e -> {
			EntregadorDAO dao = new EntregadorDAO();
			atualizarTabela(dao.listar());
		});

		tabelaEntregadores.getSelectionModel().addListSelectionListener(e -> {
			if (!e.getValueIsAdjusting() && tabelaEntregadores.getSelectedRow() != -1) {
				carregarEntregadorSelecionado(tabelaEntregadores.getSelectedRow());
			}
		});

		atualizarTabela(new EntregadorDAO().listar());
	}

	private void limparCampos() {
		txtNome.setText("");
		txtCpf.setText("");
		txtTelefone.setText("");
		idEntregadorSelecionado = -1;
		tabelaEntregadores.clearSelection();
	}

	private void carregarEntregadorSelecionado(int linha) {
		idEntregadorSelecionado = (int) modeloTabela.getValueAt(linha, 0);
		txtNome.setText((String) modeloTabela.getValueAt(linha, 1));
		txtCpf.setText((String) modeloTabela.getValueAt(linha, 2));
		txtTelefone.setText((String) modeloTabela.getValueAt(linha, 3));
	}

	public void atualizarTabela(List<Entregador> entregadores) {
		modeloTabela.setRowCount(0);
		for (Entregador e : entregadores) {
			modeloTabela.addRow(new Object[] { e.getId(), e.getNome(), e.getCpf(), e.getTelefone() });
		}
	}
}
