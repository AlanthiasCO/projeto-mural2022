package br.ufpr.mural.core.mural;

import br.ufpr.mural.core.usuario.Usuario;

public class Comentario {

	private static Integer idUltimoComent = 0;
	private Integer idComentario;
	private Usuario usuarioComent;
	private String comentario;
	

	private static synchronized void incrementarIdUltimoComent() {
		idUltimoComent++;
	}
	
    public synchronized static void resetaIds() {
    	idUltimoComent = 0;
    }
	
	public Comentario(Usuario usuario, String comentario) {
		incrementarIdUltimoComent();
		this.usuarioComent = usuario;
		this.idComentario = idUltimoComent;
		this.comentario = comentario;

	}
	
	/* METODOS: COMETARIO */
	public Integer getIdComent() {
		return idComentario;
	}
	
	public String getComentario() {
		return comentario;
	}
	
	public Usuario getUsuarioComent() {
		return usuarioComent;
	}
	
	
	@Override
	public String toString() {
		return "id_" + getIdComent() + " " + getUsuarioComent()+ ": " + getComentario() ;
	}
}
