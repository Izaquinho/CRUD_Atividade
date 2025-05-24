package av2_isaak;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EntregaDAO {

    ConexaoDAO conexaoDAO = new ConexaoDAO();

    public boolean salvar(Entrega e) {
        if (conexaoDAO.getConnection()) {
            String sql = "INSERT INTO entrega (data, destino, status, entregador_id, veiculo_id) VALUES (?, ?, ?, ?, ?)";
            try {
                PreparedStatement stmt = conexaoDAO.connection.prepareStatement(sql);
                stmt.setDate(1, Date.valueOf(e.getData())); // String yyyy-MM-dd
                stmt.setString(2, e.getDestino());
                stmt.setString(3, e.getStatus().name()); // Enum -> String
                stmt.setInt(4, e.getEntregador().getId());
                stmt.setInt(5, e.getVeiculo().getId());
                stmt.executeUpdate();
                return true;
            } catch (SQLException ex) {
                System.out.println("Erro ao salvar entrega: " + ex.getMessage());
            } finally {
                conexaoDAO.close();
            }
        }
        return false;
    }

    public boolean atualizar(Entrega e) {
        if (conexaoDAO.getConnection()) {
            String sql = "UPDATE entrega SET data = ?, destino = ?, status = ?, entregador_id = ?, veiculo_id = ? WHERE id = ?";
            try {
                PreparedStatement stmt = conexaoDAO.connection.prepareStatement(sql);
                stmt.setDate(1, Date.valueOf(e.getData()));
                stmt.setString(2, e.getDestino());
                stmt.setString(3, e.getStatus().name());
                stmt.setInt(4, e.getEntregador().getId());
                stmt.setInt(5, e.getVeiculo().getId());
                stmt.setInt(6, e.getId());
                stmt.executeUpdate();
                return true;
            } catch (SQLException ex) {
                System.out.println("Erro ao atualizar entrega: " + ex.getMessage());
            } finally {
                conexaoDAO.close();
            }
        }
        return false;
    }

    public boolean excluir(int id) {
        if (conexaoDAO.getConnection()) {
            String sql = "DELETE FROM entrega WHERE id = ?";
            try {
                PreparedStatement stmt = conexaoDAO.connection.prepareStatement(sql);
                stmt.setInt(1, id);
                stmt.executeUpdate();
                return true;
            } catch (SQLException ex) {
                System.out.println("Erro ao excluir entrega: " + ex.getMessage());
            } finally {
                conexaoDAO.close();
            }
        }
        return false;
    }


    public List<Entrega> listar() {
        List<Entrega> lista = new ArrayList<>();
        if (conexaoDAO.getConnection()) {
            String sql = "SELECT * FROM entrega";
            try {
                Statement stmt = conexaoDAO.connection.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    Entregador entregador = new Entregador();
                    entregador.setId(rs.getInt("entregador_id"));

                    Veiculo veiculo = new Veiculo();
                    veiculo.setId(rs.getInt("veiculo_id"));

                    Status status;
                    try {
                        status = Status.valueOf(rs.getString("status"));
                    } catch (IllegalArgumentException e) {
                        status = Status.PENDENTE;
                    }

                    Entrega e = new Entrega(
                        rs.getInt("id"),
                        rs.getString("data"),
                        rs.getString("destino"),
                        status,
                        entregador,
                        veiculo
                    );
                    lista.add(e);
                }
            } catch (SQLException ex) {
                System.out.println("Erro ao listar entregas: " + ex.getMessage());
            } finally {
                conexaoDAO.close();
            }
        }
        return lista;
    }
}
