package br.ufpr.mural.core;

import br.ufpr.mural.core.Usuario;

public abstract class Reacao {
	
	private Usuario usuario;

	
	public Reacao(Usuario usuario) {
		super();
		this.usuario = usuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}
}

