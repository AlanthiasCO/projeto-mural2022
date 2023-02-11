
package br.ufpr.mural.core;

import java.util.ArrayList;
import java.util.List;

import br.ufpr.mural.core.Evento;
import br.ufpr.mural.core.Post;

public class Usuario {

	private static int idUltimoUser = 0;
	private int idUsuario;
	private List<Post> postSalvos;
	private List<Evento> eventosConfirmados;
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
	}

	/*METODOS: USUARIO*/
	public String getUserName() {
		return userName;
	}
	public int getId() {
		return this.idUsuario;
	}
	public boolean isAdmin() {
		return this.userName.equals("admin");
	}
	
	/*METODOS: POSTS SALVOS*/
    public List<Post> getPostsSalvos() {
		return postSalvos;
	}
    
    public void salvarPost(Post idPost) {
    	this.postSalvos.add(idPost);
    }
    
    public void excluirPostSalvo(Post post) {
        this.postSalvos.remove(post);
    }

    
    /*METODOS: EVENTOS CONFIRMADOS*/
    public void addEventoConfirmado(Evento idPost) {
		this.eventosConfirmados.add(idPost);
	}
    
    public List<Evento> getEventosConfirmados(){
    	return eventosConfirmados;
    }

	@Override
	public String toString() {
		return this.userName;
	}
}
