package av2_isaak;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EntregaDAO {

	public boolean salvar(Entrega e) {
		String sql = "INSERT INTO entrega (data, destino, status, entregador_id, veiculo_id, produto_id) VALUES (?, ?, ?, ?, ?, ?)";
		try (Connection conn = ConexaoDAO.getConexao(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setDate(1, Date.valueOf(e.getData()));
			stmt.setString(2, e.getDestino());
			stmt.setString(3, e.getStatus().name());
			stmt.setInt(4, e.getEntregador().getId());
			stmt.setInt(5, e.getVeiculo().getId());
			stmt.setInt(6, e.getProduto().getId());
			stmt.executeUpdate();
			return true;
		} catch (SQLException ex) {
			System.err.println("Erro ao salvar entrega: " + ex.getMessage());
			return false;
		}
	}

	public boolean atualizar(Entrega e) {
		String sql = "UPDATE entrega SET data = ?, destino = ?, status = ?, entregador_id = ?, veiculo_id = ?, produto_id = ? WHERE id = ?";
		try (Connection conn = ConexaoDAO.getConexao(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setDate(1, Date.valueOf(e.getData()));
			stmt.setString(2, e.getDestino());
			stmt.setString(3, e.getStatus().name());
			stmt.setInt(4, e.getEntregador().getId());
			stmt.setInt(5, e.getVeiculo().getId());
			stmt.setInt(6, e.getProduto().getId());
			stmt.setInt(7, e.getId());
			stmt.executeUpdate();
			return true;
		} catch (SQLException ex) {
			System.err.println("Erro ao atualizar entrega: " + ex.getMessage());
			return false;
		}
	}

	public boolean excluir(int id) {
		String sql = "DELETE FROM entrega WHERE id = ?";
		try (Connection conn = ConexaoDAO.getConexao(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, id);
			stmt.executeUpdate();
			return true;
		} catch (SQLException ex) {
			System.err.println("Erro ao excluir entrega: " + ex.getMessage());
			return false;
		}
	}

	public static List<Entrega> listar() {
		List<Entrega> lista = new ArrayList<>();
		String sql = "SELECT * FROM entrega";
		try (Connection conn = ConexaoDAO.getConexao();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {
			while (rs.next()) {
				Entregador entregador = new EntregadorDAO().buscarPorId(rs.getInt("entregador_id"));
				Veiculo veiculo = new VeiculoDAO().buscarPorId(rs.getInt("veiculo_id"));
				Produto produto = new ProdutoDAO().buscarPorId(rs.getInt("produto_id"));
				Status status = Status.valueOf(rs.getString("status"));

				lista.add(new Entrega(rs.getInt("id"), rs.getString("data"), rs.getString("destino"), status,
						entregador, veiculo, produto));
			}
		} catch (SQLException ex) {
			System.err.println("Erro ao listar entregas: " + ex.getMessage());
		}
		return lista;
	}

	public Entrega buscarPorId(int id) {
		String sql = "SELECT * FROM entrega WHERE id = ?";
		try (Connection conn = ConexaoDAO.getConexao(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				Entregador entregador = new EntregadorDAO().buscarPorId(rs.getInt("entregador_id"));
				Veiculo veiculo = new VeiculoDAO().buscarPorId(rs.getInt("veiculo_id"));
				Produto produto = new ProdutoDAO().buscarPorId(rs.getInt("produto_id"));
				Status status = Status.valueOf(rs.getString("status"));

				return new Entrega(rs.getInt("id"), rs.getString("data"), rs.getString("destino"), status, entregador,
						veiculo, produto);
			}

		} catch (SQLException e) {
			System.err.println("Erro ao buscar entrega por ID: " + e.getMessage());
		}
		return null;
	}
}