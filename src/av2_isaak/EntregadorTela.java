package av2_isaak;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.*;

public class EntregadorTela extends JFrame {
	
    private JTextField txtNome, txtCpf, txtTelefone;
    private JButton btnSalvar, btnAtualizar, btnExcluir, btnListar;
    private JTextArea txtArea;

    public EntregadorTela() {
        setTitle("Cadastro de Entregadores");
        setSize(500, 400);
        setLayout(new BorderLayout());

        JPanel panelForm = new JPanel(new GridLayout(4, 2));
        txtNome = new JTextField();
        txtCpf = new JTextField();
        txtTelefone = new JTextField();

        panelForm.add(new JLabel("Nome:"));
        panelForm.add(txtNome);
        panelForm.add(new JLabel("CPF:"));
        panelForm.add(txtCpf);
        panelForm.add(new JLabel("Telefone:"));
        panelForm.add(txtTelefone);

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
        
        EntregadorDAO dao = new EntregadorDAO();

        btnSalvar.addActionListener(e -> {
            Entregador ent = new Entregador(0, txtNome.getText(), txtCpf.getText(), txtTelefone.getText());
            dao.salvar(ent);
            JOptionPane.showMessageDialog(this, "Entregador salvo!");
        });

        btnAtualizar.addActionListener(e -> {
            String idStr = JOptionPane.showInputDialog("Informe o ID para atualizar:");
            int id = Integer.parseInt(idStr);
            Entregador ent = new Entregador(id, txtNome.getText(), txtCpf.getText(), txtTelefone.getText());
            dao.atualizar(ent);
            JOptionPane.showMessageDialog(this, "Entregador atualizado!");
        });

        btnExcluir.addActionListener(e -> {
            String idStr = JOptionPane.showInputDialog("Informe o ID para excluir:");
            int id = Integer.parseInt(idStr);
            dao.excluir(id);
            JOptionPane.showMessageDialog(this, "Entregador excluído!");
        });

        btnListar.addActionListener(e -> {
            List<Entregador> lista = dao.listar();
            txtArea.setText("");
            for (Entregador ent : lista) {
                txtArea.append(ent.toString() + "\n");
            }
        });

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }


}
