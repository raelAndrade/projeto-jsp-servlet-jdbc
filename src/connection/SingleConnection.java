package connection;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Classe responsável por fazer a conexão com o banco de dados
 * 
 * @author israel
 *
 */
public class SingleConnection {

	private static String url = "jdbc:postgresql://localhost:5432/curso-jsp?autoReconnect=true";
	
	private static String user = "postgres";
	
	private static String password = "";
	
	private static Connection connection = null;
	
	static {
		conectar();
	}

	private static void conectar() {
		
		try {
			
			if(connection == null) {
				Class.forName("org.postgresql.Driver");
				connection = DriverManager.getConnection(url, user, password);
				System.out.println("Conectado !!!");
				connection.setAutoCommit(false);
			}
			
		}catch (Exception e) {
			throw new RuntimeException("Erro ao conectar com o banco de dados ");
		}
		
	}
	
	public static Connection getConnection() {
		return connection;
	}
	
	
	
}
