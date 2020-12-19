package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Telefone;
import connection.SingleConnection;

public class TelefoneDao {
	
	private Connection connection;
	
	public TelefoneDao() {
		connection = SingleConnection.getConnection();
	}

	public void salvar(Telefone telefone) {
		try {
			String sql = "insert into telefone(numero, tipo, usuario_id) values (?, ?, ?)";
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setString(1,  telefone.getNumero());
			insert.setString(2, telefone.getTipo());
			insert.setLong(3, telefone.getUsuarioId());
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

	public List<Telefone> listar(Long user) throws Exception {
		List<Telefone> listar = new ArrayList<Telefone>();
		
		String sql = "select * from telefone where usuario_id = " + user;
		
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		
		while(resultSet.next()) {
			Telefone telefone = new Telefone();
			telefone.setId(resultSet.getLong("id"));
			telefone.setNumero(resultSet.getString("numero"));
			telefone.setTipo(resultSet.getString("tipo"));
			telefone.setUsuarioId(resultSet.getLong("usuario_id"));
			
			listar.add(telefone);
		}
		return listar;
	}
	
	public void delete(String id) {
		try {
			String sql = "delete from telefone where id = '" + id + "'";
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

}
