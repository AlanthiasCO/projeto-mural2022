package br.ufpr.mural.core.mural;

import br.ufpr.mural.core.usuario.Usuario;

public class Curtida extends Reacao {

	public Curtida(Usuario usuario) {
		super(usuario);
	}

	@Override
	public String toString() {
		return this.getUsuario() + " curtiu";
	}

	
}
