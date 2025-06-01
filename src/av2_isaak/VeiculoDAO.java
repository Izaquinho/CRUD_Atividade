package av2_isaak;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VeiculoDAO {

    public boolean salvar(Veiculo v) {
        String sql = "INSERT INTO veiculo (placa, modelo, tipo) VALUES (?, ?, ?)";
        try (Connection conn = ConexaoDAO.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, v.getPlaca());
            stmt.setString(2, v.getModelo());
            stmt.setString(3, v.getTipo());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro ao salvar veículo: " + ex.getMessage());
            return false;
        }
    }

    public boolean atualizar(Veiculo v) {
        String sql = "UPDATE veiculo SET placa = ?, modelo = ?, tipo = ? WHERE id = ?";
        try (Connection conn = ConexaoDAO.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, v.getPlaca());
            stmt.setString(2, v.getModelo());
            stmt.setString(3, v.getTipo());
            stmt.setInt(4, v.getId());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro ao atualizar veículo: " + ex.getMessage());
            return false;
        }
    }

    public boolean excluir(int id) {
        String sql = "DELETE FROM veiculo WHERE id = ?";
        try (Connection conn = ConexaoDAO.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro ao excluir veículo: " + ex.getMessage());
            return false;
        }
    }

    public static List<Veiculo> listar() {
        List<Veiculo> lista = new ArrayList<>();
        String sql = "SELECT * FROM veiculo";
        try (Connection conn = ConexaoDAO.getConexao();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Veiculo(
                    rs.getInt("id"),
                    rs.getString("placa"),
                    rs.getString("modelo"),
                    rs.getString("tipo")
                ));
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao listar veículos: " + ex.getMessage());
        }
        return lista;
    }
    
    public Veiculo buscarPorId(int id) {
        String sql = "SELECT * FROM veiculo WHERE id = ?";
        try (Connection conn = ConexaoDAO.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Veiculo(
                		rs.getInt("id"),
                        rs.getString("placa"),
                        rs.getString("modelo"),
                        rs.getString("tipo")
                );
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao buscar veículo: " + ex.getMessage());
        }
        return null;
    }  
    
}
