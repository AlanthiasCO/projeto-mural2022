package br.ufpr.mural.core.mural;

import java.util.Date;

import br.ufpr.mural.core.usuario.Usuario;

public class Anuncio extends Post {
	public Anuncio(int idPost, String texto, Usuario usuarioCriador, Date date) {
		super(texto, usuarioCriador);
	}

	
	
	public String toString() {
		return "id_" + this.getId() + " anuncio " + this.getUsuarioCriador().getUserName() + " " + this.getTexto();
	}

}
