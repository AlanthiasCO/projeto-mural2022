package br.ufpr.mural.core;

import br.ufpr.mural.core.Usuario;

public class Curtida extends Reacao {

	public Curtida(Usuario usuario) {
		super(usuario);
	}

	@Override
	public String toString() {
		return this.getUsuario() + " curtiu";
	}

	
}
