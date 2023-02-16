package br.ufpr.mural.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import br.ufpr.mural.core.mural.Anuncio;
import br.ufpr.mural.core.mural.Comentario;
import br.ufpr.mural.core.mural.Evento;
import br.ufpr.mural.core.mural.Lembrete;
import br.ufpr.mural.core.mural.Mural;
import br.ufpr.mural.core.mural.Post;
import br.ufpr.mural.core.mural.Reacao;
import br.ufpr.mural.core.mural.Sugestao;
import br.ufpr.mural.core.usuario.Usuario;

public class JdbcSqlDatabaseDao implements DatabaseDao { // DAO = Data Access Object

	Connection conexao;

	public JdbcSqlDatabaseDao() {
		ConnectionFactory factory = new ConnectionFactory();
		conexao = factory.getConnection();
	}

	/*
	 * Esboço de método que insere usuário no banco. Necessário testar.
	 */
	public void inserirUsuario(Usuario usuario) {
		String sql = "INSERT INTO usuario (id, userName) VALUES (?, ?)";

		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			System.out.println(sql);

			stmt.setInt(1, usuario.getId());
			stmt.setString(2, usuario.getUserName());
			stmt.execute();
			stmt.close();

		} catch (SQLException ex) {
			System.out.println(ex);
		}
	}

	// depois de executado o servidor, sempre lembrar de ir no MYSQL e executar a
	// seguinte query:
	/*
	 * DELETE FROM usuario WHERE id BETWEEN 1 AND 6; /*
	 * 
	 * 
	 * /* Esboço de método que recupera usuário do banco. Necessário testar.
	 */
	public Usuario getUsuario(String userName) {
		String sql = "SELECT * FROM usuario WHERE userName='?'";

		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);

			stmt.setString(1, userName);

			ResultSet rs = stmt.executeQuery();

			stmt.close();

			if (rs.next()) { // só haverá um resultado
				Usuario usuario = new Usuario(rs.getString("username"));
				return usuario;
			}

		} catch (SQLException ex) {
			System.out.println(ex);
		}

		return null;
	}

	@Override
	public void inserirMural(Mural mural) {
		String sql = "INSERT INTO mural (id, nome) VALUES (?, ?)";
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			System.out.println(sql);

			stmt.setInt(1, mural.getId());
			stmt.setString(2, mural.getNome());
			stmt.execute();
			stmt.close();

		} catch (SQLException ex) {
			System.out.println(ex);
		}

	}

	public void inserirPost(Post post, Mural mural) {

	}

	@Override
	public Mural recuperarMural(int idMural) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void recuperarPosts(Mural mural) {
		// TODO Auto-generated method stub

	}

	@Override
	public void inserirPost(Evento post, Mural mural) {
		// TODO Auto-generated method stub

	}

	@Override
	public void inserirPost(Anuncio post, Mural mural) {
		// TODO Auto-generated method stub

	}

	@Override
	public void inserirComentario(Comentario comentario, Post post) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Comentario> recuperarComentarios(Post post) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void inserirPostSalvo(Post post, Usuario usuario) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Post> recuperarPostsSalvos(Usuario usuario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void inserirReacao(Reacao reacao, Post post) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Reacao> recuperarReacoes(Post post) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removerReacao(Reacao reacao, Post post) {
		// TODO Auto-generated method stub

	}

	@Override
	public void inserirConfirmacaoEvento(Evento evento, Usuario usuario) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Evento> recuperarEventosConfirmados(Usuario usuario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removerConfirmacaoEvento(Evento evento, Usuario usuario) {
		// TODO Auto-generated method stub

	}

	@Override
	public void inserirLembrete(Lembrete lembrete, Usuario usuario) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Lembrete> recuperarLembretes(Usuario usuario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void inserirSugestao(Sugestao sugestao) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Sugestao> recuperarSugestoesRecebidas(Usuario usuario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Sugestao> recuperarSugestoesFeitas(Usuario usuario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Sugestao> recuperarSugestoesPost(Post post) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mural getMural(String nomeMural) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void limparBanco() {
		// TODO Auto-generated method stub

	}

	@Override
	public Collection<Mural> listMurais() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Usuario> listUsuarios() {
		// TODO Auto-generated method stub
		return null;
	}

	// ...

}
