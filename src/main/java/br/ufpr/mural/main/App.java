package br.ufpr.mural.main;

import java.io.IOException;

import br.ufpr.mural.server.DatabaseDao;
import br.ufpr.mural.server.InMemoryDatabase;
import br.ufpr.mural.server.JdbcSqlDatabaseDao;
import br.ufpr.mural.server.Servidor;


public class App {
	public String getGreeting() {
		return "Hello World!";
	}

	public static void main(String[] args) throws IOException {
		System.out.println("Iniciando servidor");
		/* ALTERACAO DE ARMAZENAMENTO > INMEMORY OU MYSQL */
		InMemoryDatabase database = new InMemoryDatabase();
		// JdbcSqlDatabaseDao database = new JdbcSqlDatabaseDao(); //< FECHADO, OU SEJA,
		// O SERVIDOR ESTÁ ARMAZENANDO NA CLASSE INMEMORY
		Servidor servidor = new Servidor(database);
		servidor.iniciar();
	}
}
