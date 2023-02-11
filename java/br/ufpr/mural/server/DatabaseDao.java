package br.ufpr.mural.server;

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

/**
 * Representa um banco de dados e suas operações.
 *
 */


public interface DatabaseDao {

	void inserirUsuario(Usuario usuario);
	
	Usuario getUsuario(String userName);
	
	void inserirMural(Mural mural);
	
	Mural getMural(String nomeMural);
	
	Mural recuperarMural(int idMural);
	
	Map<String, Mural> listMurais();
	
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

	void limparBanco();

	void criarMural(Mural mural);
	
	void removerPost(int idPost);
	
	
}
