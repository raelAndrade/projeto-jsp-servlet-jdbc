package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import connection.SingleConnection;

public class LoginDao {
	
	private Connection connection;
	
	public LoginDao() {
		connection = SingleConnection.getConnection();
	}
	
	public boolean validarLogin(String login, String senha) throws Exception {
		
		String sql = "select * from usuario where login = '" + login + "' and senha = '" + senha +  "'";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		
		if(resultSet.next()) {
			return true; // possui login
		} else {
			return false; // não validou usuário
		}
		
	}

}
