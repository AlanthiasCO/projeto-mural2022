package br.ufpr.mural.core.mural;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.ufpr.mural.core.usuario.Usuario;

public class Mural {

	private static int idUltimoMural = 0;
	private Integer idMural;
	private String nome;
	private Map<Integer, Post> posts;

	private static synchronized void incrementarIdUltimo() {
		idUltimoMural++;
	}
	
    public synchronized static void resetaIds() {
    	idUltimoMural = 0;
    }
	
	public Mural(String nome) {
		incrementarIdUltimo();
		this.idMural = idUltimoMural;
		this.nome = nome;
		this.posts = new HashMap<>();
	}

	/* METODOS: MURAL */
	public String getNome() {
		return nome;
	}
	
	public Integer getId() {
		return this.idMural;
	}

	
	/*METODOS: POST*/
	public void inserirPost(Post post) {
		this.posts.put(post.getId(), post);
	}

	public void removerPost(int idPost) {
		this.posts.remove(idPost);
	}

	public Map<Integer, Post> listPosts() {
		return this.posts;
	}

	public Post getPost(Integer idPost) {
		return this.posts.get(idPost);
	}
	

	@Override
	public String toString() {
		return nome;
	}
}
