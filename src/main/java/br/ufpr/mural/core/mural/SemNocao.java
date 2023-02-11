package br.ufpr.mural.core.mural;

import br.ufpr.mural.core.usuario.Usuario;

public class SemNocao extends Reacao{

	public SemNocao(Usuario usuario) {
		super(usuario);
	}
	
	@Override
	public String toString() {
		return this.getUsuario() + " achou sem nocao";
	}
	

}
