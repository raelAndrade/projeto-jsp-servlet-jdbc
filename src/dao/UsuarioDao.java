package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Usuario;
import connection.SingleConnection;

public class UsuarioDao {
	
	private Connection connection;

	public UsuarioDao() {
		connection = SingleConnection.getConnection();
	}
	
	public void salvar(Usuario usuario) {
		try {
			String sql = "insert into usuario("
					+ "login, "
					+ "senha, "
					+ "nome, "
					+ "cep, "
					+ "rua, "
					+ "bairro, "
					+ "cidade, "
					+ "estado, "
					+ "ibge, "
					+ "fotobase64, "
					+ "contenttype, "
					+ "curriculobase64, "
					+ "contenttypecurriculo, "
					+ "fotobase64miniatura, "
					+ "ativo, "
					+ "sexo, "
					+ "perfil) "
					+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setString(1,  usuario.getLogin());
			insert.setString(2, usuario.getSenha());
			insert.setString(3, usuario.getNome());
			insert.setString(4, usuario.getCep());
			insert.setString(5, usuario.getRua());
			insert.setString(6, usuario.getBairro());
			insert.setString(7, usuario.getCidade());
			insert.setString(8, usuario.getUf());
			insert.setString(9, usuario.getIbge());
			insert.setString(10, usuario.getFotoBase64());
			insert.setString(11, usuario.getContentType());
			insert.setString(12, usuario.getCurriculoBase64());
			insert.setString(13, usuario.getContentTypeCurriculo());
			insert.setString(14, usuario.getFotoBase64Miniatura());
			insert.setBoolean(15, usuario.isAtivo());
			insert.setString(16, usuario.getSexo());
			insert.setString(17, usuario.getPerfil());
			
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
	
	public List<Usuario> listar() throws Exception{
		String sql = "select * from usuario where login <> 'admin'";
		return consultarUsuarios(sql);
	}

	private List<Usuario> consultarUsuarios(String sql) throws SQLException {
		List<Usuario> listar = new ArrayList<Usuario>();
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		
		while(resultSet.next()) {
			
			Usuario usuario = new Usuario();
			usuario.setId(resultSet.getLong("id"));
			usuario.setLogin(resultSet.getString("login"));
			usuario.setSenha(resultSet.getString("senha"));
			usuario.setNome(resultSet.getString("nome"));
			usuario.setCep(resultSet.getString("cep"));
			usuario.setRua(resultSet.getString("rua"));
			usuario.setBairro(resultSet.getString("bairro"));
			usuario.setCidade(resultSet.getString("cidade"));
			usuario.setUf(resultSet.getString("estado"));
			usuario.setIbge(resultSet.getString("ibge"));
			usuario.setFotoBase64(resultSet.getString("fotobase64"));
			usuario.setContentType(resultSet.getString("contenttype"));
			usuario.setCurriculoBase64(resultSet.getString("curriculobase64"));
			usuario.setContentTypeCurriculo(resultSet.getString("contenttypecurriculo"));
			usuario.setFotoBase64Miniatura(resultSet.getString("fotobase64miniatura"));
			usuario.setAtivo(resultSet.getBoolean("ativo"));
			usuario.setSexo(resultSet.getString("sexo"));
			usuario.setPerfil(resultSet.getString("perfil"));
			
			listar.add(usuario);
		}
		
		return listar;
	}
	
	public List<Usuario> listar(String descricaoconsulta) throws SQLException{
		String sql = "select * from usuario where login <> 'admin' and nome like '%" + descricaoconsulta + "%'";
		return consultarUsuarios(sql);
	}
	
	public void delete(String id) {
		try {
			String sql = "delete from usuario where id = '" + id + "' and login <> 'admin'";
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
	
	public Usuario consulta(String id) throws Exception {
		
		String sql = "select * from usuario where id = '" + id + "' and login <> 'admin'";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		
		if(resultSet.next()) {
			Usuario usuario = new Usuario();
			usuario.setId(resultSet.getLong("id"));
			usuario.setLogin(resultSet.getString("login"));
			usuario.setSenha(resultSet.getString("senha"));
			usuario.setNome(resultSet.getString("nome"));
			usuario.setCep(resultSet.getString("cep"));
			usuario.setRua(resultSet.getString("rua"));
			usuario.setBairro(resultSet.getString("bairro"));
			usuario.setCidade(resultSet.getString("cidade"));
			usuario.setUf(resultSet.getString("estado"));
			usuario.setIbge(resultSet.getString("ibge"));
			usuario.setFotoBase64(resultSet.getString("fotobase64"));
			usuario.setContentType(resultSet.getString("contenttype"));
			usuario.setCurriculoBase64(resultSet.getString("curriculobase64"));
			usuario.setContentTypeCurriculo(resultSet.getString("contenttypecurriculo"));
			usuario.setFotoBase64Miniatura(resultSet.getString("fotobase64miniatura"));
			usuario.setAtivo(resultSet.getBoolean("ativo"));
			usuario.setSexo(resultSet.getString("sexo"));
			usuario.setPerfil(resultSet.getString("perfil"));
			
			return usuario;
		}
		
		return null;
	}
	
	public boolean validarLogin(String login) throws Exception {
		
		String sql = "select count(1) as qtd from usuario where login = '" + login + "'";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		
		if(resultSet.next()) {
			
			return resultSet.getInt("qtd") <= 0; /* Return true */
		}
		
		return false;
	}
	
	public boolean validarSenha(String senha) throws Exception {
		
		String sql = "select count(1) as qtd from usuario where senha = '" + senha + "'";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		
		if(resultSet.next()) {
			
			return resultSet.getInt("qtd") <= 0; /* Return true */
		}
		
		return false;
	}
	
	public boolean validarLoginUpdate(String login, String id) throws Exception {
		
		String sql = "select count(1) as qtd from usuario where login = '" + login + "' and id <> '" + id + "'";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		
		if(resultSet.next()) {
			
			return resultSet.getInt("qtd") <= 0; /* Return true */
		}
		
		return false;
	}
	
	public void atualizar(Usuario usuario) {
		
		try {
			
			StringBuilder sql = new StringBuilder(); 
			
			sql.append(" update usuario set ");
			sql.append(" login = ?, ");
			sql.append(" senha = ?, ");
			sql.append(" nome = ?, ");
			sql.append(" cep = ?, ");
			sql.append(" rua = ?, ");
			sql.append(" bairro = ?, ");
			sql.append(" cidade = ?, ");
			sql.append(" estado = ?, ");
			sql.append(" ibge = ?, ");
			sql.append(" ativo = ?, ");
			sql.append(" sexo = ?, ");
			sql.append(" perfil = ?, ");
			
			if(usuario.isAtualizarImage()) {
				sql.append(" fotobase64 = ?, ");
				sql.append(" contenttype = ?, ");				
			} else {
			}
			
			if(usuario.isAtualizarPdf()) {
				sql.append(" curriculobase64 = ?, ");
				sql.append(" contenttypecurriculo = ?, ");
			}
			
			if(usuario.isAtualizarImage()) {
				sql.append(" fotobase64miniatura = ?, ");
			}

			sql.append(" where id = " + usuario.getId());
			
			PreparedStatement update = connection.prepareStatement(sql.toString().replaceAll("\\,  where", " where"));
			update.setString(1, usuario.getLogin());
			update.setString(2, usuario.getSenha());
			update.setString(3, usuario.getNome());
			update.setString(4, usuario.getCep());
			update.setString(5, usuario.getRua());
			update.setString(6, usuario.getBairro());
			update.setString(7, usuario.getCidade());
			update.setString(8, usuario.getUf());
			update.setString(9, usuario.getIbge());
			update.setBoolean(10, usuario.isAtivo());
			update.setString(11, usuario.getSexo());
			update.setString(12, usuario.getPerfil());
			
			if(usuario.isAtualizarImage()) {
				update.setString(13, usuario.getFotoBase64());
				update.setString(14, usuario.getContentType());
			}
			
			if(usuario.isAtualizarPdf()) {
				
				if(usuario.isAtualizarPdf() && !usuario.isAtualizarImage()) {
					update.setString(13, usuario.getCurriculoBase64());
					update.setString(14, usuario.getContentTypeCurriculo());					
				} else {
					update.setString(15, usuario.getCurriculoBase64());
					update.setString(16, usuario.getContentTypeCurriculo());
				}
				
			} else {
				
				if(usuario.isAtualizarImage()) {
					update.setString(15, usuario.getFotoBase64Miniatura());
				}
			}
			
			if(usuario.isAtualizarImage() && usuario.isAtualizarPdf()) {
				update.setString(17, usuario.getFotoBase64Miniatura());
			}
			
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
}