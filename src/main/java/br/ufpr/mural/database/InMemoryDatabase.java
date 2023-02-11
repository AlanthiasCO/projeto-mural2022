
package br.ufpr.mural.database;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import br.ufpr.mural.core.Anuncio;
import br.ufpr.mural.core.Comentario;
import br.ufpr.mural.core.Evento;
import br.ufpr.mural.core.Lembrete;
import br.ufpr.mural.core.Mural;
import br.ufpr.mural.core.Post;
import br.ufpr.mural.core.Reacao;
import br.ufpr.mural.core.Sugestao;
import br.ufpr.mural.core.Usuario;


public class InMemoryDatabase implements DatabaseDao{ // DAO = Data Access Object

	private Map<String, Usuario> usuarios; // agregação 1 para muitos
	private Map<String, Mural> murais;
	

	public InMemoryDatabase() {
		this.usuarios = new HashMap<>();
		this.murais = new HashMap<>();

	}
	
	@Override
	public void limparBanco() {
		this.usuarios = new HashMap<>();
		this.murais = new HashMap<>();
		Usuario.resetaIds();
		Post.resetaIdPost();
	}

	// USUARIOS
	public void inserirUsuario(Usuario usuario) {
		this.usuarios.put(usuario.getUserName(), usuario);
	}

	public Usuario getUsuario(String userNameNovo) {
		return this.usuarios.get(userNameNovo);

	}

	// MURAIS
	public void criarMural(Mural mural) {
		this.murais.put(mural.getNome(), mural);
	}

	public Mural getMural(String nomeMural) {
		return this.murais.get(nomeMural);
	
	}

	@Override
	public Map<String, Mural> listMurais() {
		return this.murais;
	}

	
	
	//OBRIGATORIOS DEVIDO O IMPLEMENTS
	
	@Override
	public void inserirMural(Mural mural) {
		// TODO Auto-generated method stub
		
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
	public void removerPost(int idPost) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void limparBase() {
		// TODO Auto-generated method stub
		
	}
}
