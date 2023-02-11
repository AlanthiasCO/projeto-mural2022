package br.ufpr.mural.database;

import java.util.List;
import java.util.Map;

import br.ufpr.mural.core.Anuncio;
import br.ufpr.mural.core.Comentario;
import br.ufpr.mural.core.Evento;
import br.ufpr.mural.core.Lembrete;
import br.ufpr.mural.core.Mural;
import br.ufpr.mural.core.Post;
import br.ufpr.mural.core.Reacao;
import br.ufpr.mural.core.Sugestao;
import br.ufpr.mural.core.Usuario;

/**
 * Representa um banco de dados e suas operações.
 *
 */
public interface DatabaseDao {
	
	void limparBase();

	void inserirUsuario(Usuario usuario);
	
	Usuario getUsuario(String userName);
	
	void inserirMural(Mural mural);
	
	Mural recuperarMural(int idMural);
	
	void recuperarPosts(Mural mural);
	
	void inserirPost(Evento post, Mural mural);
	
	void inserirPost(Anuncio post, Mural mural);
	
	void inserirComentario(Comentario comentario, Post post);
	
	List<Comentario> recuperarComentarios(Post post);
	
	void inserirPostSalvo(Post post, Usuario usuario);
	
	List<Post> recuperarPostsSalvos(Usuario usuario);
	
	void inserirReacao(Reacao reacao, Post post);
	
	List<Reacao> recuperarReacoes(Post post);
	
	void removerReacao(Reacao reacao, Post post);
	
	void inserirConfirmacaoEvento(Evento evento, Usuario usuario);
	
	List<Evento> recuperarEventosConfirmados(Usuario usuario);
	
	void removerConfirmacaoEvento(Evento evento, Usuario usuario);
	
	void inserirLembrete(Lembrete lembrete, Usuario usuario);
	
	List<Lembrete> recuperarLembretes(Usuario usuario);
	
	void inserirSugestao(Sugestao sugestao);
	
	List<Sugestao> recuperarSugestoesRecebidas(Usuario usuario);
	
	List<Sugestao> recuperarSugestoesFeitas(Usuario usuario);
	
	List<Sugestao> recuperarSugestoesPost(Post post);

	Mural getMural(String nomeMural);

	Map<String, Mural> listMurais();

	void limparBanco();

	void removerPost(int idPost);
	
}
