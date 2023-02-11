package br.ufpr.mural.core;

import br.ufpr.mural.core.Usuario;

public class SemNocao extends Reacao{

	public SemNocao(Usuario usuario) {
		super(usuario);
	}
	
	@Override
	public String toString() {
		return this.getUsuario() + " achou sem nocao";
	}
	

}
