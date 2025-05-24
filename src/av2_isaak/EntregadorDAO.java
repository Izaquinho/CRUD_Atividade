package av2_isaak;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EntregadorDAO {

    ConexaoDAO conexaoDAO = new ConexaoDAO();

    public boolean salvar(Entregador entregador) {
        if (conexaoDAO.getConnection()) {
            String sql = "INSERT INTO entregador (nome, cpf, telefone) VALUES (?, ?, ?)";
            try {
                PreparedStatement stmt = conexaoDAO.connection.prepareStatement(sql);
                stmt.setString(1, entregador.getNome());
                stmt.setString(2, entregador.getCpf());
                stmt.setString(3, entregador.getTelefone());
                stmt.executeUpdate();
                return true;
            } catch (SQLException e) {
                System.out.println("Erro ao salvar entregador: " + e.getMessage());
            } finally {
                conexaoDAO.close();
            }
        }
        return false;
    }

    public boolean atualizar(Entregador entregador) {
        if (conexaoDAO.getConnection()) {
            String sql = "UPDATE entregador SET nome = ?, cpf = ?, telefone = ? WHERE id = ?";
            try {
                PreparedStatement stmt = conexaoDAO.connection.prepareStatement(sql);
                stmt.setString(1, entregador.getNome());
                stmt.setString(2, entregador.getCpf());
                stmt.setString(3, entregador.getTelefone());
                stmt.setInt(4, entregador.getId());
                stmt.executeUpdate();
                return true;
            } catch (SQLException e) {
                System.out.println("Erro ao atualizar entregador: " + e.getMessage());
            } finally {
                conexaoDAO.close();
            }
        }
        return false;
    }

    public boolean excluir(int id) {
        if (conexaoDAO.getConnection()) {
            String sql = "DELETE FROM entregador WHERE id = ?";
            try {
                PreparedStatement stmt = conexaoDAO.connection.prepareStatement(sql);
                stmt.setInt(1, id);
                stmt.executeUpdate();
                return true;
            } catch (SQLException e) {
                System.out.println("Erro ao excluir entregador: " + e.getMessage());
            } finally {
                conexaoDAO.close();
            }
        }
        return false;
    }

    public List<Entregador> listar() {
        List<Entregador> lista = new ArrayList<>();
        if (conexaoDAO.getConnection()) {
            String sql = "SELECT * FROM entregador";
            try {
                Statement stmt = conexaoDAO.connection.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    Entregador e = new Entregador(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getString("telefone")
                    );
                    lista.add(e);
                }
            } catch (SQLException e) {
                System.out.println("Erro ao listar entregadores: " + e.getMessage());
            } finally {
                conexaoDAO.close();
            }
        }
        return lista;
    }

}
