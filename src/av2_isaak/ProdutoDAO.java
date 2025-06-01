package av2_isaak;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

	public boolean salvar(Produto v) {
		String sql = "INSERT INTO produto (nome, peso, valor) VALUES (?, ?, ?)";
		try (Connection conn = ConexaoDAO.getConexao(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, v.getNome());
			stmt.setDouble(2, v.getPeso());
			stmt.setFloat(3, v.getValor());
			stmt.executeUpdate();
			return true;
		} catch (SQLException ex) {
			System.err.println("Erro ao salvar produto: " + ex.getMessage());
			return false;
		}
	}

	public boolean atualizar(Produto v) {
		String sql = "UPDATE produto SET nome = ?, peso = ?, valor = ? WHERE id = ?";
		try (Connection conn = ConexaoDAO.getConexao(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, v.getNome());
			stmt.setDouble(2, v.getPeso());
			stmt.setFloat(3, v.getValor());
			stmt.setInt(4, v.getId());
			stmt.executeUpdate();
			return true;
		} catch (SQLException ex) {
			System.err.println("Erro ao atualizar produto: " + ex.getMessage());
			return false;
		}
	}

	public boolean excluir(int id) {
		String sql = "DELETE FROM produto WHERE id = ?";
		try (Connection conn = ConexaoDAO.getConexao(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, id);
			stmt.executeUpdate();
			return true;
		} catch (SQLException ex) {
			System.err.println("Erro ao excluir produto: " + ex.getMessage());
			return false;
		}
	}

	public static List<Produto> listar() {
		List<Produto> lista = new ArrayList<>();
		String sql = "SELECT * FROM produto";
		try (Connection conn = ConexaoDAO.getConexao();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {
			while (rs.next()) {
				lista.add(
						new Produto(rs.getInt("id"), rs.getString("nome"), rs.getDouble("peso"), rs.getFloat("valor")));
			}
		} catch (SQLException ex) {
			System.err.println("Erro ao listar produtos: " + ex.getMessage());
		}
		return lista;
	}

	public Produto buscarPorId(int id) {
		String sql = "SELECT * FROM produto WHERE id = ?";
		try (Connection conn = ConexaoDAO.getConexao(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return new Produto(rs.getInt("id"), rs.getString("nome"), rs.getDouble("peso"), rs.getFloat("valor"));
			}
		} catch (SQLException ex) {
			System.err.println("Erro ao buscar produto: " + ex.getMessage());
		}
		return null;
	}

}