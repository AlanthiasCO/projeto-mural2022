package br.ufpr.mural.core.mural;

import br.ufpr.mural.core.usuario.Usuario;

public abstract class Reacao {
	
	private Usuario usuario;

	
	public Reacao(Usuario usuario) {
		super();
		this.usuario = usuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}

