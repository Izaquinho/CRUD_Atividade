package av2_isaak;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TelaPrincipal extends JFrame {

    private JTextArea txtAreaEntregador, txtAreaVeiculo, txtAreaEntrega;
    private JComboBox<Entregador> cbEntregador;
    private JComboBox<Veiculo> cbVeiculo;
    private Integer idEntregadorEditando = null;
    private Integer idVeiculoEditando = null;

    public TelaPrincipal() {
        setTitle("Sistema de Logística");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();

        // ---------- Aba Entregadores ----------
        JPanel panelEntregador = new JPanel(new BorderLayout());
        JPanel formEntregador = new JPanel(new GridLayout(4, 2, 5, 5));

        JTextField txtNome = new JTextField();
        JTextField txtCpf = new JTextField();
        JTextField txtTelefone = new JTextField();

        formEntregador.add(new JLabel("Nome:"));
        formEntregador.add(txtNome);
        formEntregador.add(new JLabel("CPF:"));
        formEntregador.add(txtCpf);
        formEntregador.add(new JLabel("Telefone:"));
        formEntregador.add(txtTelefone);

        JPanel botoesEntregador = new JPanel(new FlowLayout());
        JButton btSalvarEnt = new JButton("Salvar");
        JButton btEditarEnt = new JButton("Editar");
        JButton btExcluirEnt = new JButton("Excluir");
        JButton btListarEnt = new JButton("Listar");
        JButton btSairEnt = new JButton("Sair");
        botoesEntregador.add(btSalvarEnt);
        botoesEntregador.add(btEditarEnt);
        botoesEntregador.add(btExcluirEnt);
        botoesEntregador.add(btListarEnt);
        botoesEntregador.add(btSairEnt);

        txtAreaEntregador = new JTextArea();
        txtAreaEntregador.setEditable(false);
        JScrollPane scrollEnt = new JScrollPane(txtAreaEntregador);

        JSplitPane splitEntregador = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
                new JPanel(new BorderLayout()) {{
                    add(formEntregador, BorderLayout.NORTH);
                    add(botoesEntregador, BorderLayout.SOUTH);
                }},
                scrollEnt);
        splitEntregador.setResizeWeight(0.4);

        panelEntregador.add(splitEntregador, BorderLayout.CENTER);

        btSalvarEnt.addActionListener(e -> {
            if (idEntregadorEditando == null) {
                Entregador ent = new Entregador(0, txtNome.getText(), txtCpf.getText(), txtTelefone.getText());
                new EntregadorDAO().salvar(ent);
                JOptionPane.showMessageDialog(this, "Entregador salvo!");
            } else {
                Entregador ent = new Entregador(idEntregadorEditando, txtNome.getText(), txtCpf.getText(), txtTelefone.getText());
                new EntregadorDAO().atualizar(ent);
                JOptionPane.showMessageDialog(this, "Entregador atualizado!");
                idEntregadorEditando = null; 
            }

            limparCampos(txtNome, txtCpf, txtTelefone);
        });


        btEditarEnt.addActionListener(e -> {
            String idStr = JOptionPane.showInputDialog("ID do entregador para editar:");
            if (idStr != null && !idStr.isEmpty()) {
                try {
                    int id = Integer.parseInt(idStr);
                    EntregadorDAO dao = new EntregadorDAO();
                    Entregador ent = dao.buscarPorId(id);

                    if (ent != null) {
                        txtNome.setText(ent.getNome());
                        txtCpf.setText(ent.getCpf());
                        txtTelefone.setText(ent.getTelefone());
                        idEntregadorEditando = id;
                        JOptionPane.showMessageDialog(this, "Edite os dados e clique em Salvar.");
                    } else {
                        JOptionPane.showMessageDialog(this, "Entregador não encontrado.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "ID inválido.");
                }
            }
        });

        btExcluirEnt.addActionListener(e -> {
            String idStr = JOptionPane.showInputDialog("ID do entregador para excluir:");
            if (idStr != null) {
                int id = Integer.parseInt(idStr);
                new EntregadorDAO().excluir(id);
                JOptionPane.showMessageDialog(this, "Entregador excluído!");
            }
        });

        btListarEnt.addActionListener(e -> {
            List<Entregador> lista = new EntregadorDAO().listar();
            txtAreaEntregador.setText("ID | Nome | CPF | Telefone\n");
            txtAreaEntregador.append("----------------------------------------\n");
            for (Entregador ent : lista) {
                txtAreaEntregador.append(ent.getId() + " | " + ent.getNome() + " | " + ent.getCpf() + " | " + ent.getTelefone() + "\n");
            }
        });

        btSairEnt.addActionListener(e -> System.exit(0));

        tabbedPane.addTab("Entregadores", panelEntregador);

        // ---------- Aba Veículos ----------
        JPanel panelVeiculo = new JPanel(new BorderLayout());
        JPanel formVeiculo = new JPanel(new GridLayout(4, 2, 5, 5));

        JTextField txtModelo = new JTextField();
        JTextField txtPlaca = new JTextField();
        JTextField txtTipo = new JTextField();

        formVeiculo.add(new JLabel("Modelo:"));
        formVeiculo.add(txtModelo);
        formVeiculo.add(new JLabel("Placa:"));
        formVeiculo.add(txtPlaca);
        formVeiculo.add(new JLabel("Tipo:"));
        formVeiculo.add(txtTipo);
        
        JPanel botoesVeiculo = new JPanel(new FlowLayout());
        JButton btSalvarVei = new JButton("Salvar");
        JButton btEditarVei = new JButton("Editar");
        JButton btExcluirVei = new JButton("Excluir");
        JButton btListarVei = new JButton("Listar");
        JButton btSairVei = new JButton("Sair");
        botoesVeiculo.add(btSalvarVei);
        botoesVeiculo.add(btEditarVei);
        botoesVeiculo.add(btExcluirVei);
        botoesVeiculo.add(btListarVei);
        botoesVeiculo.add(btSairVei);

        txtAreaVeiculo = new JTextArea();
        txtAreaVeiculo.setEditable(false);
        JScrollPane scrollVei = new JScrollPane(txtAreaVeiculo);

        JSplitPane splitVeiculo = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
                new JPanel(new BorderLayout()) {{
                    add(formVeiculo, BorderLayout.NORTH);
                    add(botoesVeiculo, BorderLayout.SOUTH);
                }},
                scrollVei);
        splitVeiculo.setResizeWeight(0.4);

        panelVeiculo.add(splitVeiculo, BorderLayout.CENTER);

        btSalvarVei.addActionListener(e -> {
            VeiculoDAO dao = new VeiculoDAO();
            if (idVeiculoEditando == -1) {
                Veiculo v = new Veiculo(0, txtPlaca.getText(), txtModelo.getText(), txtTipo.getText());
                dao.salvar(v);
                JOptionPane.showMessageDialog(this, "Veículo salvo!");
            } else {
                Veiculo v = new Veiculo(idVeiculoEditando, txtPlaca.getText(), txtModelo.getText(), txtTipo.getText());
                dao.atualizar(v);
                JOptionPane.showMessageDialog(this, "Veículo atualizado!");
                idVeiculoEditando = -1; // Reseta o modo de edição
            }
            limparCampos(txtPlaca, txtModelo, txtTipo);
        });

        btEditarVei.addActionListener(e -> {
        	String idStr = JOptionPane.showInputDialog("ID do veículo para editar:");
            if (idStr != null) {
                int id = Integer.parseInt(idStr);
                Veiculo v = new VeiculoDAO().buscarPorId(id);
                if (v != null) {
                    txtPlaca.setText(v.getPlaca());
                    txtModelo.setText(v.getModelo());
                    txtTipo.setText(v.getTipo());
                    idVeiculoEditando = v.getId();
                } else {
                    JOptionPane.showMessageDialog(this, "Veículo não encontrado!");
                }
            }
        });

        btExcluirVei.addActionListener(e -> {
            String idStr = JOptionPane.showInputDialog("ID do veículo para excluir:");
            if (idStr != null) {
                int id = Integer.parseInt(idStr);
                new VeiculoDAO().excluir(id);
                JOptionPane.showMessageDialog(this, "Veículo excluído!");
            }
        });

        btListarVei.addActionListener(e -> {
        	List<Veiculo> lista = new VeiculoDAO().listar();
        	txtAreaVeiculo.setText("ID | Modelo | Placa | Tipo\n");
        	txtAreaVeiculo.append("----------------------------------------\n");
        	for (Veiculo vei : lista) {
        	    txtAreaVeiculo.append(vei.getId() + " | " + vei.getModelo() + " | " + vei.getPlaca() + " | " + vei.getTipo() + "\n");
        	}
        });

        btSairVei.addActionListener(e -> System.exit(0));

        tabbedPane.addTab("Veículos", panelVeiculo);

        // ---------- Aba Entregas ----------
        JPanel panelEntrega = new JPanel(new BorderLayout());
        JPanel formEntrega = new JPanel(new GridLayout(5, 2, 5, 5));

        JTextField txtData = new JTextField();
        JTextField txtDestino = new JTextField();
        JComboBox<Status> cbStatus = new JComboBox<>(Status.values());
        cbEntregador = new JComboBox<>();
        cbVeiculo = new JComboBox<>();

        formEntrega.add(new JLabel("Data:"));
        formEntrega.add(txtData);
        formEntrega.add(new JLabel("Destino:"));
        formEntrega.add(txtDestino);
        formEntrega.add(new JLabel("Status:"));
        formEntrega.add(cbStatus);
        formEntrega.add(new JLabel("Entregador:"));
        formEntrega.add(cbEntregador);
        formEntrega.add(new JLabel("Veículo:"));
        formEntrega.add(cbVeiculo);

        JPanel botoesEntrega = new JPanel(new FlowLayout());
        JButton btSalvarEntregas = new JButton("Salvar");
        JButton btEditarEntregas = new JButton("Editar");
        JButton btExcluirEntregas = new JButton("Excluir");
        JButton btListarEntregas = new JButton("Listar");
        JButton btSairEntregas = new JButton("Sair");
        botoesEntrega.add(btSalvarEntregas);
        botoesEntrega.add(btEditarEntregas);
        botoesEntrega.add(btExcluirEntregas);
        botoesEntrega.add(btListarEntregas);
        botoesEntrega.add(btSairEntregas);

        txtAreaEntrega = new JTextArea();
        txtAreaEntrega.setEditable(false);
        JScrollPane scrollEntrega = new JScrollPane(txtAreaEntrega);

        JSplitPane splitEntrega = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
                new JPanel(new BorderLayout()) {{
                    add(formEntrega, BorderLayout.NORTH);
                    add(botoesEntrega, BorderLayout.SOUTH);
                }},
                scrollEntrega);
        splitEntrega.setResizeWeight(0.4);

        panelEntrega.add(splitEntrega, BorderLayout.CENTER);

        // Preenche os JComboBox com dados sempre que a aba for selecionada
        tabbedPane.addChangeListener(e -> {
            if (tabbedPane.getSelectedComponent() == panelEntrega) {
                atualizarComboEntregadores();
                atualizarComboVeiculos();
            }
        });

        btSalvarEntregas.addActionListener(e -> {
            Entregador ent = (Entregador) cbEntregador.getSelectedItem();
            Veiculo vei = (Veiculo) cbVeiculo.getSelectedItem();
            if (ent == null || vei == null) {
                JOptionPane.showMessageDialog(this, "Selecione entregador e veículo.");
                return;
            }
            Entrega entrega = new Entrega(0, txtData.getText(), txtDestino.getText(), (Status) cbStatus.getSelectedItem(), ent, vei);
            new EntregaDAO().salvar(entrega);
            JOptionPane.showMessageDialog(this, "Entrega salva!");
            limparCampos(txtData, txtDestino);
        });

        btEditarEntregas.addActionListener(e -> {
            String idStr = JOptionPane.showInputDialog("ID da entrega para editar:");
            if (idStr != null) {
                int id = Integer.parseInt(idStr);
                Entregador ent = (Entregador) cbEntregador.getSelectedItem();
                Veiculo vei = (Veiculo) cbVeiculo.getSelectedItem();
                if (ent == null || vei == null) {
                    JOptionPane.showMessageDialog(this, "Selecione entregador e veículo.");
                    return;
                }
                Entrega entrega = new Entrega(id, txtData.getText(), txtDestino.getText(), (Status) cbStatus.getSelectedItem(), ent, vei);
                new EntregaDAO().atualizar(entrega);
                JOptionPane.showMessageDialog(this, "Entrega atualizada!");
                limparCampos(txtData, txtDestino);
            }
        });

        btExcluirEntregas.addActionListener(e -> {
            String idStr = JOptionPane.showInputDialog("ID da entrega para excluir:");
            if (idStr != null) {
                int id = Integer.parseInt(idStr);
                new EntregaDAO().excluir(id);
                JOptionPane.showMessageDialog(this, "Entrega excluída!");
            }
        });

        btListarEntregas.addActionListener(e -> {
            List<Entrega> lista = new EntregaDAO().listar();
            txtAreaEntrega.setText("ID | Data | Destino | Status | Entregador | Veículo\n");
            txtAreaEntrega.append("--------------------------------------------------------------\n");
            for (Entrega ent : lista) {
                txtAreaEntrega.append(
                        ent.getId() + " | " +
                        ent.getData() + " | " +
                        ent.getDestino() + " | " +
                        ent.getStatus() + " | " +
                        ent.getEntregador().getNome() + " | " +
                        ent.getVeiculo().getModelo() + "\n"
                );
            }
        });

        btSairEntregas.addActionListener(e -> System.exit(0));

        tabbedPane.addTab("Entregas", panelEntrega);

        add(tabbedPane);
        setVisible(true);
    }

    private void limparCampos(JTextField... campos) {
        for (JTextField campo : campos) {
            campo.setText("");
        }
    }

    private void atualizarComboEntregadores() {
        cbEntregador.removeAllItems();
        List<Entregador> lista = new EntregadorDAO().listar();
        for (Entregador ent : lista) {
            cbEntregador.addItem(ent);
        }
    }

    private void atualizarComboVeiculos() {
        cbVeiculo.removeAllItems();
        List<Veiculo> lista = new VeiculoDAO().listar();
        for (Veiculo vei : lista) {
            cbVeiculo.addItem(vei);
        }
    }
    
    public static void main(String[] args) {
        new TelaPrincipal();
    }
}
