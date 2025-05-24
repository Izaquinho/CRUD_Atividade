package av2_isaak;

import javax.swing.*;
import java.awt.*;

public class TelaPrincipal extends JFrame {

    public TelaPrincipal() {
        setTitle("Sistema de Logística");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painelLateral = new JPanel();
        painelLateral.setLayout(new GridLayout(3, 1, 10, 10));
        painelLateral.setPreferredSize(new Dimension(200, 0));
        painelLateral.setBackground(new Color(230, 230, 230));

        JButton btnEntregadores = new JButton("Entregadores");
        JButton btnVeiculos = new JButton("Veículos");
        JButton btnEntregas = new JButton("Entregas");

        painelLateral.add(btnEntregadores);
        painelLateral.add(btnVeiculos);
        painelLateral.add(btnEntregas);

        JPanel painelConteudo = new JPanel();
        painelConteudo.setLayout(new BorderLayout());

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(painelLateral, BorderLayout.WEST);
        getContentPane().add(painelConteudo, BorderLayout.CENTER);

        btnEntregadores.addActionListener(e -> {
            painelConteudo.removeAll();
            painelConteudo.add(new EntregadorTela());
            painelConteudo.revalidate();
            painelConteudo.repaint();
        });

        btnVeiculos.addActionListener(e -> {
            painelConteudo.removeAll();
            painelConteudo.add(new VeiculoTela());
            painelConteudo.revalidate();
            painelConteudo.repaint();
        });

        btnEntregas.addActionListener(e -> {
            painelConteudo.removeAll();
            painelConteudo.add(new EntregaTela());
            painelConteudo.revalidate();
            painelConteudo.repaint();
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TelaPrincipal().setVisible(true);
        });
    }
}
