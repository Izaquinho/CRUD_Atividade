package av2_isaak;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class VeiculoTela extends JFrame {

    private JTextField txtPlaca, txtModelo, txtTipo;
    private JButton btnSalvar, btnAtualizar, btnExcluir, btnListar;
    private JTextArea txtArea;

    public VeiculoTela() {
        setTitle("Cadastro de Veículos");
        setSize(500, 400);
        setLayout(new BorderLayout());

        JPanel panelForm = new JPanel(new GridLayout(3, 2));
        txtPlaca = new JTextField();
        txtModelo = new JTextField();
        txtTipo = new JTextField();

        panelForm.add(new JLabel("Placa:"));
        panelForm.add(txtPlaca);
        panelForm.add(new JLabel("Modelo:"));
        panelForm.add(txtModelo);
        panelForm.add(new JLabel("Tipo:"));
        panelForm.add(txtTipo);

        add(panelForm, BorderLayout.NORTH);

        JPanel panelBotoes = new JPanel(new FlowLayout());
        btnSalvar = new JButton("Salvar");
        btnAtualizar = new JButton("Atualizar");
        btnExcluir = new JButton("Excluir");
        btnListar = new JButton("Listar");

        panelBotoes.add(btnSalvar);
        panelBotoes.add(btnAtualizar);
        panelBotoes.add(btnExcluir);
        panelBotoes.add(btnListar);

        add(panelBotoes, BorderLayout.CENTER);

        txtArea = new JTextArea(10, 40);
        JScrollPane scroll = new JScrollPane(txtArea);
        add(scroll, BorderLayout.SOUTH);

        VeiculoDAO dao = new VeiculoDAO();

        btnSalvar.addActionListener(e -> {
            Veiculo v = new Veiculo(0, txtPlaca.getText(), txtModelo.getText(), txtTipo.getText());
            dao.salvar(v);
            JOptionPane.showMessageDialog(this, "Veículo salvo!");
        });

        btnAtualizar.addActionListener(e -> {
            String idStr = JOptionPane.showInputDialog("Informe o ID para atualizar:");
            int id = Integer.parseInt(idStr);
            Veiculo v = new Veiculo(id, txtPlaca.getText(), txtModelo.getText(), txtTipo.getText());
            dao.atualizar(v);
            JOptionPane.showMessageDialog(this, "Veículo atualizado!");
        });

        btnExcluir.addActionListener(e -> {
            String idStr = JOptionPane.showInputDialog("Informe o ID para excluir:");
            int id = Integer.parseInt(idStr);
            dao.excluir(id);
            JOptionPane.showMessageDialog(this, "Veículo excluído!");
        });

        btnListar.addActionListener(e -> {
            List<Veiculo> lista = dao.listar();
            txtArea.setText("");
            for (Veiculo v : lista) {
                txtArea.append(v.toString() + "\n");
            }
        });

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}
