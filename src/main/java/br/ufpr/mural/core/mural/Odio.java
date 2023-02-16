package br.ufpr.mural.core.mural;

import br.ufpr.mural.core.usuario.Usuario;

public class Odio extends Reacao {

	public Odio(Usuario usuario) {
		super(usuario);
	}

	@Override
	public String toString() {
		return this.getUsuario() + " odiou";
	}
}