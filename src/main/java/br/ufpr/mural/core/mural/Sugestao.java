package br.ufpr.mural.core.mural;

import br.ufpr.mural.core.usuario.Usuario;

public class Sugestao {
	private Post post;
	private Usuario usuarioSugestor;
	private Usuario usuarioSugerido;
	
    public Sugestao(Post post, Usuario usuarioSugestor, Usuario usuarioSugerido) {
        this.post = post;
        this.usuarioSugestor = usuarioSugestor;
        this.usuarioSugerido = usuarioSugerido;
    }

    /* METODOS: SUGESTAO*/
    public Post getPost() {
        return post;
    }
    
    public Usuario getUsuarioSugestor() {
        return usuarioSugestor;
    }

    public Usuario getUsuarioSugerido() {
        return usuarioSugerido;
    }
    
    
    @Override
    public String toString() {
    	return getUsuarioSugestor() +": " + "id_" + post.getId() + " " + "anuncio"  + " " + post.getUsuarioCriador() + " " + post.getTexto();
    }
}