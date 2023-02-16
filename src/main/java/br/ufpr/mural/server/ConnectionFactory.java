package br.ufpr.mural.server;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {

	public Connection getConnection() {
		try {
			// MUITO IMPORTANJTE ESSA LINHA ABAIXO!!!!
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/rede_social?characterEncoding=utf8", "root",
					"");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
