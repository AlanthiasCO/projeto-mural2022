package br.ufpr.mural.core.mural;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Lembrete {

	private Post post;
	private Date dataHora;
	private static Integer idUltimoLembrete = 0;
	private Integer idLembrete;

	private static synchronized void incrementarIdUltimo() {
		idUltimoLembrete++;
	}
	
    public synchronized static void resetaIds() {
    	idUltimoLembrete = 0;
    }
	
	public Lembrete(Post post, Date dataHora) {
		incrementarIdUltimo();
		this.idLembrete = idUltimoLembrete;
		this.post = post;
		this.dataHora = dataHora;
	}
	
	/* METODOS: LEMBRETE */
	public Integer getId() {
		return idLembrete;
	}
	
	
	@Override //teoricamente incorreto - nao estou pegando o tipo "evento" ou "anuncio" de nenhum lugar, apenas passei a string "anuncio" direto	
	public String toString() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		return "id_" + this.getId() + " " + dateFormat.format(dataHora) + " id_" + post.getId() + " " + "anuncio "
				+ post.getUsuarioCriador() + " " + post.getTexto();
	}

}