package br.ufpr.mural.core;

import br.ufpr.mural.core.Usuario;

public class Odio extends Reacao {

	public Odio(Usuario usuario) {
		super(usuario);
	}
	
	@Override
	public String toString() {
		return this.getUsuario() + " odiou";
	}


}
