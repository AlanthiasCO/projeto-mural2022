package br.ufpr.mural.core;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Post {

	private Mural mural;

	private static int ultimoIdPost = 0;
	protected int idPost;
	private String texto;
	private Usuario usuarioCriador;
	private LocalDateTime dataCriacao;
	private List<Reacao> reacoes;

	private static synchronized void incrementarIdUltimo() {
		ultimoIdPost++;
	}

	public static synchronized void resetaIdPost() {
		ultimoIdPost = 0;
	}

	public Post(String texto, Usuario usuarioCriador) {
		incrementarIdUltimo();
		this.idPost = ultimoIdPost;
		this.texto = texto;
		this.usuarioCriador = usuarioCriador;
		this.dataCriacao = LocalDateTime.now();
		this.reacoes = new ArrayList<>();

	}

	public int getId() {
		return idPost;
	}

	public String getTexto() {
		return texto;
	}

	public Usuario getUsuarioCriador() {
		return usuarioCriador;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}



	public Mural getMural() {
		return this.mural;
	}

	public void setMural(Mural mural) {
		this.mural = mural;

	}

	public void curtir(Usuario usuario) {
		reacoes.add(new Curtida(usuario));
	}

	public void odiar(Usuario usuario) {
		reacoes.add(new Odio(usuario));
	}

	public void semNocao(Usuario usuario) {
		reacoes.add(new SemNocao(usuario));

	}

	public List<Reacao> listReacoes() {
		return reacoes;
	}
	
	public Reacao getReacaoDoUsuario(Usuario usuario) {
        for (Reacao reacao : reacoes) {
            if (reacao.getUsuario().equals(usuario)) {
                return reacao;
            }
        }
        return null;
    }
	
	public void removerReacao(Reacao reacao) {
	    this.reacoes.remove(reacao);

	}
	
	@Override
	public String toString() {
		return super.toString();
	}

}

}
