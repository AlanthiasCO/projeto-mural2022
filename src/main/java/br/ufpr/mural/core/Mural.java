package br.ufpr.mural.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.ufpr.mural.core.usuario.Usuario;

public class Mural {
  
  private String nome;
	private Map<Integer, Post> posts;

	public Mural(String nome) {
		this.nome = nome;
		this.posts = new HashMap<>();
	}

	public String getNome() {
		return nome;
	}

	
	/*METODOS: POST*/
	public void addPost(Post post) {
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
}
