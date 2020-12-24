package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.SingleConnection;
import model.Categoria;
import model.Produto;

public class ProdutoDao {
	
	private Connection connection;
	
	public ProdutoDao() {
		connection = SingleConnection.getConnection();
	}

	public void salvar(Produto produto) {
		try {
			String sql = "insert into produto(descricao, quantidade, valor, categoria_id) values (?, ?, ?, ?)";
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setString(1, produto.getDescricao());
			insert.setDouble(2, produto.getQuantidade());
			insert.setDouble(3, produto.getValor());
			insert.setLong(4, produto.getCategoriaId());
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
			produto.setCategoriaId(resultSet.getLong("categoria_id"));
			
			listar.add(produto);
		}
		return listar;
	}
	
	public List<Categoria> listaCategorias() throws Exception{
		
		List<Categoria> cat = new ArrayList<Categoria>();
		
		String sql = "select * from categoria order by nome desc";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		
		while(resultSet.next()) {
			Categoria categoria = new Categoria();
			categoria.setId(resultSet.getLong("id"));
			categoria.setNome(resultSet.getString("nome"));
			
			cat.add(categoria);
		}
		
		return cat;
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
			produto.setCategoriaId(resultSet.getLong("categoria_id"));
			
			return produto;
		}
		return null;
	}

	public void atualizar(Produto produto) {
		try {
			String sql = "update produto set descricao = ?, quantidade = ?, valor = ?, categoria_id = ? where id = " + produto.getId();
			
			PreparedStatement update = connection.prepareStatement(sql);
			update.setString(1, produto.getDescricao());
			update.setDouble(2, produto.getQuantidade());
			update.setDouble(3, produto.getValor());
			update.setLong(4, produto.getCategoriaId());
			
			update.executeUpdate();
			
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
