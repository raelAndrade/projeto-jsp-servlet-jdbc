package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Produto;
import connection.SingleConnection;

public class ProdutoDao {
	
	private Connection connection;
	
	public ProdutoDao() {
		connection = SingleConnection.getConnection();
	}

	public void salvar(Produto produto) {
		try {
			String sql = "insert into produto(descricao, quantidade, valor) values (?, ?, ?)";
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setString(1, produto.getDescricao());
			insert.setDouble(2, produto.getQuantidade());
			insert.setDouble(3, produto.getValor());
			insert.execute();			
		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	public List<Produto> listar() throws Exception {
		List<Produto> listar = new ArrayList<Produto>();
		
		String sql = "select * from produto";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		
		while(resultSet.next()) {
			Produto produto = new Produto();
			produto.setId(resultSet.getLong("id"));
			produto.setDescricao(resultSet.getString("descricao"));
			produto.setQuantidade(resultSet.getDouble("quantidade"));
			produto.setValor(resultSet.getDouble("valor"));
			
			listar.add(produto);
		}
		return listar;
	}
	
	public void delete(String id) {
		try {
			String sql = "delete from produto where id = '" + id + "'";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.execute();
			
			connection.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	public Produto consulta(String id) throws Exception {
		
		String sql = "select * from produto where id = '" + id + "'";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		
		if(resultSet.next()) {
			Produto produto = new Produto();
			produto.setId(resultSet.getLong("id"));
			produto.setDescricao(resultSet.getString("descricao"));
			produto.setQuantidade(resultSet.getDouble("quantidade"));
			produto.setValor(resultSet.getDouble("valor"));
			
			return produto;
		}
		return null;
	}

	public void atualizar(Produto produto) {
		try {
			String sql = "update produto set descricao = ?, quantidade = ?, valor = ? where id = " + produto.getId();
			
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, produto.getDescricao());
			statement.setDouble(2, produto.getQuantidade());
			statement.setDouble(3, produto.getValor());
			statement.executeUpdate();
			
			connection.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	public boolean validarDescricao(String descricao) throws Exception {
		
		String sql = "select count(1) as qtd from produto where descricao = '" + descricao + "'";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		
		if(resultSet.next()) {
			
			return resultSet.getInt("qtd") <= 0; /* Return true */
		}
		return false;
	}

}
