package br.ufpr.mural.server;

import br.ufpr.mural.core.usuario.Usuario;

public class TestesBanco {

	public static void main(String[] args) {
		
		
		JdbcSqlDatabaseDao dao = new JdbcSqlDatabaseDao();

		Usuario usuario = new Usuario("ciclano");
		dao.inserirUsuario(usuario);
		System.out.println(usuario.getId());
		//dao.getUsuario("fulano");
	}
	
}
