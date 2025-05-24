package av2_isaak;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VeiculoDAO {

    ConexaoDAO conexaoDAO = new ConexaoDAO();

    public boolean salvar(Veiculo v) {
        if (conexaoDAO.getConnection()) {
            String sql = "INSERT INTO veiculo (placa, modelo, tipo) VALUES (?, ?, ?)";
            try {
                PreparedStatement stmt = conexaoDAO.connection.prepareStatement(sql);
                stmt.setString(1, v.getPlaca());
                stmt.setString(2, v.getModelo());
                stmt.setString(3, v.getTipo());
                stmt.executeUpdate();
                return true;
            } catch (SQLException e) {
                System.out.println("Erro ao salvar veículo: " + e.getMessage());
            } finally {
                conexaoDAO.close();
            }
        }
        return false;
    }

    public boolean atualizar(Veiculo v) {
        if (conexaoDAO.getConnection()) {
            String sql = "UPDATE veiculo SET placa = ?, modelo = ?, tipo = ? WHERE id = ?";
            try {
                PreparedStatement stmt = conexaoDAO.connection.prepareStatement(sql);
                stmt.setString(1, v.getPlaca());
                stmt.setString(2, v.getModelo());
                stmt.setString(3, v.getTipo());
                stmt.setInt(4, v.getId());
                stmt.executeUpdate();
                return true;
            } catch (SQLException e) {
                System.out.println("Erro ao atualizar veículo: " + e.getMessage());
            } finally {
                conexaoDAO.close();
            }
        }
        return false;
    }

    public boolean excluir(int id) {
        if (conexaoDAO.getConnection()) {
            String sql = "DELETE FROM veiculo WHERE id = ?";
            try {
                PreparedStatement stmt = conexaoDAO.connection.prepareStatement(sql);
                stmt.setInt(1, id);
                stmt.executeUpdate();
                return true;
            } catch (SQLException e) {
                System.out.println("Erro ao excluir veículo: " + e.getMessage());
            } finally {
                conexaoDAO.close();
            }
        }
        return false;
    }

    public List<Veiculo> listar() {
        List<Veiculo> lista = new ArrayList<>();
        if (conexaoDAO.getConnection()) {
            String sql = "SELECT * FROM veiculo";
            try {
                Statement stmt = conexaoDAO.connection.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    Veiculo v = new Veiculo(
                        rs.getInt("id"),
                        rs.getString("placa"),
                        rs.getString("modelo"),
                        rs.getString("tipo")
                    );
                    lista.add(v);
                }
            } catch (SQLException e) {
                System.out.println("Erro ao listar veículos: " + e.getMessage());
            } finally {
                conexaoDAO.close();
            }
        }
        return lista;
    }
}
