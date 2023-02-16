package br.ufpr.mural.core.mural;

	import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.ufpr.mural.core.usuario.Usuario;

public class Evento extends Post {
	
	private String local;
	private LocalDateTime data;
	private String horario;
	private List<Usuario> usuariosConfirmados;

	public Evento(int idPost, String texto, Usuario usuarioCriador, LocalDateTime data, String local, LocalDateTime dataHora) {
		super(texto, usuarioCriador);
		this.data = data;
		this.local = local;
		this.usuariosConfirmados = new ArrayList<>();
	}

	
	/*METODOS: EVENTO */
	public String getLocal() {
		return local;
	}

	public LocalDateTime getData() {
		return data;
	}

	public String getHorario() {
		return horario;
	}

	
	/* METODOS: CONFIRMAR PRESENCA*/
	public void confirmarPresenca(Usuario usuario) {
		this.usuariosConfirmados.add(usuario);
	}
	
	public void desconfirmarPresenca(Usuario usuario) {
		this.usuariosConfirmados.remove(usuario);
	}
	
	public List<Usuario> getUsuariosConfirmados() {
		return this.usuariosConfirmados;
	}
	

	@Override
	public String toString() {
		return "id_" + this.getId() + " evento " + this.getUsuarioCriador().getUserName() + " " + this.getTexto();
	}
}
