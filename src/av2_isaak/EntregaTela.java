package av2_isaak;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class EntregaTela extends JFrame {

    private JTextField txtData, txtDestino;
    private JComboBox<Status> cbStatus;
    private JComboBox<Entregador> cbEntregador;
    private JComboBox<Veiculo> cbVeiculo;
    private JButton btnSalvar, btnAtualizar, btnExcluir, btnListar;
    private JTextArea txtArea;

    public EntregaTela() {
        setTitle("Cadastro de Entregas");
        setSize(650, 550);
        setLayout(new BorderLayout());

        JPanel panelForm = new JPanel(new GridLayout(6, 2));
        txtData = new JTextField();
        txtDestino = new JTextField();
        cbStatus = new JComboBox<>(Status.values());

        cbEntregador = new JComboBox<>();
        cbVeiculo = new JComboBox<>();

        carregarEntregadores();
        carregarVeiculos();

        panelForm.add(new JLabel("Data (YYYY-MM-DD):"));
        panelForm.add(txtData);
        panelForm.add(new JLabel("Destino:"));
        panelForm.add(txtDestino);
        panelForm.add(new JLabel("Status:"));
        panelForm.add(cbStatus);
        panelForm.add(new JLabel("Entregador:"));
        panelForm.add(cbEntregador);
        panelForm.add(new JLabel("Veículo:"));
        panelForm.add(cbVeiculo);

        add(panelForm, BorderLayout.NORTH);

        JPanel panelBotoes = new JPanel(new FlowLayout());
        btnSalvar = new JButton("Salvar");
        btnAtualizar = new JButton("Atualizar");
        btnExcluir = new JButton("Excluir");
        btnListar = new JButton("Listar Entregas");

        panelBotoes.add(btnSalvar);
        panelBotoes.add(btnAtualizar);
        panelBotoes.add(btnExcluir);
        panelBotoes.add(btnListar);

        add(panelBotoes, BorderLayout.CENTER);

        txtArea = new JTextArea(12, 50);
        JScrollPane scroll = new JScrollPane(txtArea);
        add(scroll, BorderLayout.SOUTH);

        EntregaDAO entregaDAO = new EntregaDAO();

        btnSalvar.addActionListener(e -> {
            Entrega ent = new Entrega();
            ent.setData(txtData.getText());
            ent.setDestino(txtDestino.getText());
            ent.setStatus((Status) cbStatus.getSelectedItem());
            ent.setEntregador((Entregador) cbEntregador.getSelectedItem());
            ent.setVeiculo((Veiculo) cbVeiculo.getSelectedItem());

            entregaDAO.salvar(ent);
            JOptionPane.showMessageDialog(this, "Entrega salva!");
        });

        btnAtualizar.addActionListener(e -> {
            String idStr = JOptionPane.showInputDialog("Informe o ID da entrega a atualizar:");
            int id = Integer.parseInt(idStr);

            Entrega ent = new Entrega();
            ent.setId(id);
            ent.setData(txtData.getText());
            ent.setDestino(txtDestino.getText());
            ent.setStatus((Status) cbStatus.getSelectedItem());
            ent.setEntregador((Entregador) cbEntregador.getSelectedItem());
            ent.setVeiculo((Veiculo) cbVeiculo.getSelectedItem());

            entregaDAO.atualizar(ent);
            JOptionPane.showMessageDialog(this, "Entrega atualizada!");
        });

        btnExcluir.addActionListener(e -> {
            String idStr = JOptionPane.showInputDialog("Informe o ID da entrega a excluir:");
            int id = Integer.parseInt(idStr);
            entregaDAO.excluir(id);
            JOptionPane.showMessageDialog(this, "Entrega excluída!");
        });

        btnListar.addActionListener(e -> {
            List<Entrega> lista = entregaDAO.listar();
            txtArea.setText("");
            for (Entrega ent : lista) {
                txtArea.append("ID: " + ent.getId()
                        + ", Data: " + ent.getData()
                        + ", Destino: " + ent.getDestino()
                        + ", Status: " + ent.getStatus()
                        + ", Entregador ID: " + ent.getEntregador().getId()
                        + ", Veículo ID: " + ent.getVeiculo().getId()
                        + "\n");
            }
        });

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private void carregarEntregadores() {
        EntregadorDAO dao = new EntregadorDAO();
        List<Entregador> lista = dao.listar();
        cbEntregador.removeAllItems();
        for (Entregador e : lista) {
            cbEntregador.addItem(e);
        }
    }

    private void carregarVeiculos() {
        VeiculoDAO dao = new VeiculoDAO();
        List<Veiculo> lista = dao.listar();
        cbVeiculo.removeAllItems();
        for (Veiculo v : lista) {
            cbVeiculo.addItem(v);
        }
    }
}
