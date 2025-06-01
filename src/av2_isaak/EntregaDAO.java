package av2_isaak;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EntregadorDAO {

    public boolean salvar(Entregador e) {
        String sql = "INSERT INTO entregador (nome, cpf, telefone) VALUES (?, ?, ?)";
        try (Connection conn = ConexaoDAO.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, e.getNome());
            stmt.setString(2, e.getCpf());
            stmt.setString(3, e.getTelefone());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro ao salvar entregador: " + ex.getMessage());
            return false;
        }
    }

    public boolean atualizar(Entregador e) {
        String sql = "UPDATE entregador SET nome = ?, cpf = ?, telefone = ? WHERE id = ?";
        try (Connection conn = ConexaoDAO.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, e.getNome());
            stmt.setString(2, e.getCpf());
            stmt.setString(3, e.getTelefone());
            stmt.setInt(4, e.getId());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro ao atualizar entregador: " + ex.getMessage());
            return false;
        }
    }

    public boolean excluir(int id) {
        String sql = "DELETE FROM entregador WHERE id = ?";
        try (Connection conn = ConexaoDAO.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro ao excluir entregador: " + ex.getMessage());
            return false;
        }
    }

    public static List<Entregador> listar() {
        List<Entregador> lista = new ArrayList<>();
        String sql = "SELECT * FROM entregador";
        try (Connection conn = ConexaoDAO.getConexao();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Entregador(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("cpf"),
                    rs.getString("telefone")
                ));
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao listar entregadores: " + ex.getMessage());
        }
        return lista;
    }
    
    public Entregador buscarPorId(int id) {
        String sql = "SELECT * FROM entregador WHERE id = ?";
        try (Connection conn = ConexaoDAO.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Entregador(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("cpf"),
                    rs.getString("telefone")
                );
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao buscar entregador: " + ex.getMessage());
        }
        return null;
    }    
}