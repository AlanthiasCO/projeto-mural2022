package br.ufpr.mural.core.usuario;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.ufpr.mural.core.mural.Comentario;
import br.ufpr.mural.core.mural.Evento;
import br.ufpr.mural.core.mural.Lembrete;
import br.ufpr.mural.core.mural.Post;
import br.ufpr.mural.core.mural.Sugestao;

public class Usuario {

	private static int idUltimoUser = 0;
	private int idUsuario;
	private List<Post> postSalvos;
	private List<Evento> eventosConfirmados;
	private List<Lembrete> lembretes;
	private List<Sugestao> sugestoesRecebidas;
	private List<Sugestao> sugestoesFeitas;
	private String userName; // identificador

	private static synchronized void incrementarIdUltimo() {
		idUltimoUser++;
	}

	public synchronized static void resetaIds() {
		idUltimoUser = 0;
	}

	public Usuario(String userName) {
		incrementarIdUltimo();
		this.idUsuario = idUltimoUser;
		this.userName = userName;
		this.postSalvos = new ArrayList<>();
		this.eventosConfirmados = new ArrayList<>();
		this.lembretes = new ArrayList<>();
		this.sugestoesRecebidas = new ArrayList<Sugestao>();
		this.sugestoesFeitas = new ArrayList<Sugestao>();
	}

	/* METODOS: USUARIO */
	public String getUserName() {
		return userName;
	}

	public int getId() {
		return this.idUsuario;
	}

	
	/* METODOS: POSTS SALVOS */
	public List<Post> getPostsSalvos() {
		return postSalvos;
	}

	public void salvarPost(Post idPost) {
		this.postSalvos.add(idPost);
	}

	public void excluirPostSalvo(Post post) {
		this.postSalvos.remove(post);
	}

	
	/* METODOS: EVENTOS CONFIRMADOS */
	public void addEventoConfirmado(Evento idPost) {
		this.eventosConfirmados.add(idPost);
	}

	public List<Evento> getEventosConfirmados() {
		return eventosConfirmados;
	}

	/* METODOS: LEMBRETES */
	public void criarLembrete(Lembrete lembrete) {
		this.lembretes.add(lembrete);
	}

	public List<Lembrete> listLembretes() {
		return lembretes;
	}

	public void removerLembrete(Lembrete lembrete) {
		this.lembretes.remove(lembrete);
	}
	
	public Lembrete getLembrete(Integer idLembrete) {
		for (Lembrete lembrete : this.lembretes) {
			if (lembrete.getId().equals(idLembrete)) {
				return lembrete;
			}
		}
		return null;
	}
	
	
	/* METODOS: SUGESTOES*/
	public List<Sugestao> getSugestoesRecebidas() {
		return sugestoesRecebidas;
	}

	public List<Sugestao> getSugestoesFeitas() {
		return sugestoesFeitas;
	}

	public void addSugestaoRecebida(Sugestao sugestao) {
		sugestoesRecebidas.add(sugestao);
	}

	public void addSugestaoFeita(Sugestao sugestao) {
		sugestoesFeitas.add(sugestao);
	}


	@Override
	public String toString() {
		return this.userName;
	}

}
