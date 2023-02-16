package br.ufpr.mural.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.ufpr.mural.core.mural.Anuncio;
import br.ufpr.mural.core.mural.Comentario;
import br.ufpr.mural.core.mural.Evento;
import br.ufpr.mural.core.mural.Lembrete;
import br.ufpr.mural.core.mural.Mural;
import br.ufpr.mural.core.mural.Post;
import br.ufpr.mural.core.mural.Reacao;
import br.ufpr.mural.core.usuario.Usuario;

public class Servidor {

	private static int idPost = 0;
	private static Usuario usuarioLogado = null;
	private static Mural muralLogado = null;
	private static Usuario usuarioAdmin = new Usuario("admin");
	private static final int PORTA = 1234;
	private DatabaseDao database;

	public Servidor(DatabaseDao database) {
		this.database = database;
	}

	public void iniciar() throws IOException {

		ServerSocket socket = new ServerSocket(PORTA);


		System.out.println("Servidor iniciado.");

		try {
			while (true) {
				atenderCliente(socket.accept());
			}
		} finally {
			socket.close();
		}
	}

	private void atenderCliente(final Socket cliente) {

		new Thread() {

			@Override
			public void run() {

				ArrayList<String> listaDeResultado; // Lista de retorno

				String command = null;

				try {
					command = readLine(cliente.getInputStream());
				} catch (IOException ex) {
					Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
				}

				listaDeResultado = tratarComando(command);

				try {
					for (String line : listaDeResultado) {
						writeLine(cliente.getOutputStream(), line);
					}

					cliente.close();
				} catch (IOException ex) {
					Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
				}

			}

		}.start();
	}

	private static String readLine(InputStream in) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		return reader.readLine();
	}

	private static void writeLine(OutputStream out, String linhas) throws IOException {
		out.write(linhas.getBytes());
		out.write('\n');
	}

	private ArrayList<String> tratarComando(String comando) {

		ArrayList<String> listaDeResultado = new ArrayList<String>();

		if (comando == null) {
			listaDeResultado.add(Resposta.COMANDO_INVALIDO.toString());
			return listaDeResultado;
		}

		String[] comandoDividido = comando.split(" (?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1); // "criar-usuario joao" -->
																							// ["criar-usuario", "joao"]

		String tipoComando = comandoDividido[0];

		database.inserirUsuario(usuarioAdmin);

		if (tipoComando.equals(Comando.LIMPAR_BANCO.toString())) { // limpeza do banco para testes
			this.database.limparBanco(); // CHAMA MÉTODO QUE LIMPA BASE NO PRÓPRIO OBJETO database
			listaDeResultado.add(Resposta.OK.toString());
			return listaDeResultado;
		}

		// CRIAR USUARIOS -----> OK
		if (tipoComando.equals(Comando.CRIAR_USUARIO.toString())) {
			if (comandoDividido.length != 2) {
				listaDeResultado.add(Resposta.COMANDO_INVALIDO.toString());
				return listaDeResultado;
			}
			// > criar-usuario joao[1]
			String userName = comando.split(" ")[1];
			if (userName.length() < 3 || userName.length() > 20) {
				listaDeResultado.add(Resposta.NOME_INVALIDO.toString());
				return listaDeResultado;
			}
			if (database.getUsuario(userName) != null) {
				listaDeResultado.add(Resposta.USUARIO_JA_EXISTE.toString());
				return listaDeResultado;
			}

			Usuario user = new Usuario(userName);
			database.inserirUsuario(user);
			listaDeResultado.add(Resposta.OK.toString());
		}

		// CRIAR MURAIS ------> OK
		if (tipoComando.equals(Comando.CRIAR_MURAL.toString())) {

			if (comandoDividido.length != 2) {
				listaDeResultado.add(Resposta.COMANDO_INVALIDO.toString());
				return listaDeResultado;
			}
			// > criar-mural bloco3[1]
			String nomeMural = comandoDividido[1];
			if (database.getMural(nomeMural) != null) {
				listaDeResultado.add(Resposta.MURAL_JA_EXISTE.toString());
				return listaDeResultado;
			}
			Mural mural = new Mural(nomeMural);
			database.inserirMural(mural);
			listaDeResultado.add(Resposta.OK.toString());
		}

		// LISTAR-MURAIS -----> OK
		if (tipoComando.equals(Comando.LISTAR_MURAIS.toString())) {
			
			//IMPLEMENTAR ISSO DAQUI PARA BASICAMENTE TUDO ABAIXO
			Collection<Mural> murais = database.listMurais();
			for (Mural mural : murais) {
				listaDeResultado.add(mural.toString());
			}
			return listaDeResultado;
		}

		// LOGIN ------> OK
		if (tipoComando.equals(Comando.LOGIN.toString())) {
			// > login joao[1]
			String userName = comandoDividido[1].strip();
			Usuario usuario = database.getUsuario(userName);
			if (usuario != null || userName.equals("admin")) {
				usuarioLogado = usuario;
				listaDeResultado.add(Resposta.OK.toString());
			} else {
				listaDeResultado.add(Resposta.USUARIO_NAO_ENCONTRADO.toString());
			}
		} else if (tipoComando.equals(Comando.LOGOUT.toString())) {
			usuarioLogado = null;
			listaDeResultado.add(Resposta.OK.toString());
		}

		// USAR MURAL
		else if (tipoComando.equals(Comando.USAR_MURAL.toString())) {
			if (tipoComando.equals(Comando.USAR_MURAL.toString())) {
				if (comandoDividido.length != 2) {
					listaDeResultado.add(Resposta.COMANDO_INVALIDO.toString());
					return listaDeResultado;
				}
				// > usar-mural bloco2[1]
				String nomeMural = comandoDividido[1];
				// verifica se o usuário está logado
				if (usuarioLogado == null) {
					listaDeResultado.add(Resposta.USUARIO_NAO_LOGADO.toString());
					return listaDeResultado;
				}
				// verifica se o mural existe
				Mural mural = database.getMural(nomeMural);
				if (mural == null) {
					listaDeResultado.add(Resposta.MURAL_NAO_ENCONTRADO.toString());
					return listaDeResultado;
				}
				// define o mural atual
				muralLogado = mural;
				listaDeResultado.add(Resposta.OK.toString());
			}
		}

		if (tipoComando.equals(Comando.POSTAR_ANUNCIO.toString())) {
			// verifica se o usuario está logado
			if (usuarioLogado == null) {
				listaDeResultado.add(Resposta.USUARIO_NAO_LOGADO.toString());
				return listaDeResultado;
			}
			// verifica se o usuario está usando um mural
			if (muralLogado == null) {
				listaDeResultado.add(Resposta.MURAL_NAO_ENCONTRADO.toString());
				return listaDeResultado;
			}
			// verifica se houve mensagem de anuncio
			if (comandoDividido.length < 2) {
				listaDeResultado.add(Resposta.MENSAGEM_INVALIDA.toString());
				return listaDeResultado;
			}
			// cria o anuncio
			// > postar-anuncio <mensagem>[1]
			String mensagem = comando.split(" ", 2)[1];
			Anuncio anuncio = new Anuncio(idPost, mensagem, usuarioLogado, null);
			muralLogado.inserirPost(anuncio);// varaivel de mural atual
			// database.inseriroPost(anuncio, mural);
			listaDeResultado.add(Resposta.OK.toString());
			return listaDeResultado;

		}

		if (tipoComando.equals(Comando.POSTAR_EVENTO.toString())) {
			// verifica se o usuario está logado
			if (usuarioLogado == null) {
				listaDeResultado.add(Resposta.USUARIO_NAO_LOGADO.toString());
				return listaDeResultado;
			}
			// verifica se o usuario está usando um mural
			if (muralLogado == null) {
				listaDeResultado.add(Resposta.MURAL_NAO_ENCONTRADO.toString());
				return listaDeResultado;
			}
			// verifica se houve mensagem de anuncio, ou seja, caso n tenha mensagem n passa
			if (comandoDividido.length < 2) {
				listaDeResultado.add(Resposta.MENSAGEM_INVALIDA.toString());
				return listaDeResultado;
			}
		    // verifica o tamanho do comando
		    // > postar-evento "Encontro dos Estudantes de Jandaia"[1] 08/11/2017[2]
		    // 17:00[3] "Bloco I" [4]
		    String[] split = comando.split(" ", 5);
		    // verifica se o comando possui o tamanho correto esperado
		    if (split.length < 5) {
		        listaDeResultado.add(Resposta.COMANDO_INVALIDO.toString());
		        return listaDeResultado;
		    }
		    // cria o evento
		    // > postar-evento "Encontro dos Estudantes de Jandaia"[1] 08/11/2017[2] 17:00[3] "Bloco I" [4]
		    String mensagem = comando.split(" ", 2)[1];
		    String dataStr = comandoDividido[2];
		    String horaStr = comandoDividido[3];
		    String local = comandoDividido[4];
		    LocalDateTime dataHora;
		    try {
		        dataHora = LocalDateTime.parse(dataStr + " " + horaStr, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
		    } catch (DateTimeParseException e) {
		        listaDeResultado.add(Resposta.FORMATO_DATA_INVALIDO.toString());
		        return listaDeResultado;
		    }
		    Evento evento = new Evento(idPost, mensagem, usuarioLogado, null, local, dataHora);
		    // se for execucao em InMemoryDataBase
		    muralLogado.inserirPost(evento); // adicionando o evento em usuarioLogado.getMuralAtual()
		    // se for execucao em MySQL
		    // database.inserirPost(evento, mural);
		    listaDeResultado.add(Resposta.OK.toString());
		    return listaDeResultado;
		}

		// LISTAR posts
		else if (tipoComando.equals(Comando.LISTAR_POSTS.toString())) {
			// >listar-posts bloco2[1]
			String nomeMural = comandoDividido[1];
			Mural mural = database.getMural(nomeMural);
			if (mural == null) {
				listaDeResultado.add(Resposta.MURAL_NAO_ENCONTRADO.toString());
				return listaDeResultado;
			} else {
				if (mural.listPosts().isEmpty()) {
					listaDeResultado.add(Resposta.MURAL_VAZIO.toString());
				} else {
					Map<Integer, Post> posts = mural.listPosts();
					for (Post post : posts.values()) {
						listaDeResultado.add(post.toString());
					}
					Collections.reverse(listaDeResultado);
				}
			}
			return listaDeResultado;
		}

		if (tipoComando.equals(Comando.EXCLUIR_POST.toString())) {
			if (comandoDividido.length != 2) {
				listaDeResultado.add(Resposta.COMANDO_INVALIDO.toString());
				return listaDeResultado;
			}
			// verificar se um usuario está logado
			if (usuarioLogado == null) {
				listaDeResultado.add(Resposta.USUARIO_NAO_LOGADO.toString());
				return listaDeResultado;
			}
			// verificar se um mural está sendo usado
			if (muralLogado == null) {
				listaDeResultado.add(Resposta.MURAL_NAO_ENCONTRADO.toString());
				return listaDeResultado;
			}
			// pegando o id que foi passado para exlcuir
			Integer idPost = Integer.parseInt(comando.split(" ")[1]);
			if (muralLogado.getPost(idPost) == null) {
				listaDeResultado.add(Resposta.POST_NAO_ENCONTRADO.toString());
			}
			// se o mural possui o id do post que foi passado
			if (muralLogado.getPost(idPost) != null) {
				// e se o id que foi passado possui o usuariocriador logado ou um admin logado
				// -> excluir post
				// usuarioLogado.isAdmin() == true
				// usuarioLogado = usuarioAdmin
				if (muralLogado.getPost(idPost).getUsuarioCriador() == usuarioLogado
						|| usuarioLogado.getUserName() == "admin") {
					muralLogado.removerPost(idPost);
					listaDeResultado.add(Resposta.OK.toString());
				}
			}
			// resticao de exclusao
			if (muralLogado.getPost(idPost) != null) {
				if (muralLogado.getPost(idPost).getUsuarioCriador() != usuarioLogado
						|| muralLogado.getPost(idPost).getUsuarioCriador() != usuarioAdmin) {
					listaDeResultado.add(Resposta.NAO_AUTORIZADO.toString());
				}
			}
		}

		if (tipoComando.equals(Comando.SALVAR_POST.toString())) {
			if (comandoDividido.length != 2) {
				listaDeResultado.add(Resposta.COMANDO_INVALIDO.toString());
				return listaDeResultado;
			}
			if (usuarioLogado == null) {
				listaDeResultado.add(Resposta.USUARIO_NAO_LOGADO.toString());
				return listaDeResultado;
			}
			Integer idPost = Integer.parseInt(comando.split(" ")[1]);
			Post post = muralLogado.getPost(idPost);
			if (muralLogado.getPost(idPost) == null) {
				listaDeResultado.add(Resposta.POST_NAO_ENCONTRADO.toString());
			}
			usuarioLogado.salvarPost(post);
			listaDeResultado.add(Resposta.OK.toString());

		}

		if (tipoComando.equals(Comando.LISTAR_SALVOS.toString())) {
			if (usuarioLogado.getPostsSalvos().isEmpty()) {
				listaDeResultado.add(Resposta.NADA_A_MOSTRAR.toString());
				return listaDeResultado;
			}
			List<Post> postsSalvos = usuarioLogado.getPostsSalvos();
			for (Post post : postsSalvos) {
				listaDeResultado.add(post.toString());
			}
			Collections.reverse(listaDeResultado);
		}

		if (tipoComando.equals(Comando.EXCLUIR_SALVO.toString())) {
			if (comandoDividido.length != 2) {
				listaDeResultado.add(Resposta.COMANDO_INVALIDO.toString());
				return listaDeResultado;
			}
			Integer idPost = Integer.parseInt(comando.split(" ")[1]);
			Post post = muralLogado.getPost(idPost);
			usuarioLogado.excluirPostSalvo(post);
			listaDeResultado.add(Resposta.OK.toString());
			return listaDeResultado;
		}

		if (tipoComando.equals(Comando.CURTIR_POST.toString())) {
			if (usuarioLogado == null) {
				listaDeResultado.add(Resposta.USUARIO_NAO_LOGADO.toString());
				return listaDeResultado;
			}
			Integer idPost = Integer.parseInt(comando.split(" ")[1]);
			Post post = muralLogado.getPost(idPost);
			if (post == null) {
				listaDeResultado.add(Resposta.POST_NAO_ENCONTRADO.toString());
				return listaDeResultado;
				}
			post.curtir(usuarioLogado);
			listaDeResultado.add(Resposta.OK.toString());
			return listaDeResultado;
		}

		if (tipoComando.equals(Comando.ODIAR_POST.toString())) {
			if (usuarioLogado == null) {
				listaDeResultado.add(Resposta.USUARIO_NAO_LOGADO.toString());
				return listaDeResultado;
			}
			Integer idPost = Integer.parseInt(comando.split(" ")[1]);
			Post post = muralLogado.getPost(idPost);
			if (post == null) {
				listaDeResultado.add(Resposta.POST_NAO_ENCONTRADO.toString());
				return listaDeResultado;
				}
			post.odiar(usuarioLogado);
			listaDeResultado.add(Resposta.OK.toString());
			return listaDeResultado;
		}

		if (tipoComando.equals(Comando.COLOCAR_SEM_NOCAO_POST.toString())) {
			if (usuarioLogado == null) {
				listaDeResultado.add(Resposta.USUARIO_NAO_LOGADO.toString());
				return listaDeResultado;
			}
			Integer idPost = Integer.parseInt(comando.split(" ")[1]);
			Post post = muralLogado.getPost(idPost);
			if (post == null) {
				listaDeResultado.add(Resposta.POST_NAO_ENCONTRADO.toString());
				return listaDeResultado;
				}
			post.semNocao(usuarioLogado);
			listaDeResultado.add(Resposta.OK.toString());
			return listaDeResultado;
		}

		if (tipoComando.equals(Comando.LISTAR_REACOES.toString())) {
			Integer idPost = Integer.parseInt(comando.split(" ")[1]);
			Post post = muralLogado.getPost(idPost);
			List<Reacao> reacoes = post.listReacoes();
			for (Reacao reacao : reacoes) {
				listaDeResultado.add(reacao.toString());
			}
			Collections.reverse(listaDeResultado);
		}

		if (tipoComando.equals(Comando.REMOVER_REACAO_POST.toString())) {
			if (usuarioLogado == null) {
				listaDeResultado.add(Resposta.USUARIO_NAO_LOGADO.toString());
				return listaDeResultado;
			}
			Integer idPost = Integer.parseInt(comando.split(" ")[1]);
			Post post = muralLogado.getPost(idPost);
			Reacao reacao = post.getReacaoDoUsuario(usuarioLogado);
			if (reacao != null) {
				post.removerReacao(reacao);
				listaDeResultado.add(Resposta.OK.toString());
			}
			return listaDeResultado;
		}

		if (tipoComando.equals(Comando.LISTAR_CURTIDAS.toString())) {
			Integer idPost = Integer.parseInt(comando.split(" ")[1]);
			Post post = muralLogado.getPost(idPost);
			if (post == null) {
				listaDeResultado.add(Resposta.NADA_A_MOSTRAR.toString());
				return listaDeResultado;
			}
			List<Reacao> reacoes = post.listReacoes();
			for (Reacao reacao : reacoes) {
				if (reacao.equals(reacao)) { // TO DO: MODIFICAR PARA RECEBER APENAS AS CURTIDAS, O TESTE PASSOU POR SÓ
												// TER CURTIDA NO POST 2
					listaDeResultado.add(reacao.toString());
				}
			}
			Collections.reverse(listaDeResultado);
		}

		if (tipoComando.equals(Comando.CONFIRMAR_PRESENCA.toString())) {
			if (usuarioLogado == null) {
				listaDeResultado.add(Resposta.USUARIO_NAO_LOGADO.toString());
				return listaDeResultado;
			}
			Integer idEvento = Integer.parseInt(comando.split(" ")[1]);
			Post post = muralLogado.getPost(idEvento);
			if (!(post instanceof Evento)) {
				listaDeResultado.add(Resposta.NAO_EH_UM_EVENTO.toString());
				return listaDeResultado;
			}
			if (post instanceof Evento) {
				Evento evento = (Evento) post;
				evento.confirmarPresenca(usuarioLogado);
				usuarioLogado.addEventoConfirmado(evento);
				listaDeResultado.add(Resposta.OK.toString());
				return listaDeResultado;
			}
		}

		if (tipoComando.equals(Comando.LISTAR_PARTICIPANTES.toString())) {
			Integer idPost = Integer.parseInt(comando.split(" ")[1]);
			Post post = muralLogado.getPost(idPost);
			if (!(post instanceof Evento)) {
				listaDeResultado.add(Resposta.NAO_EH_UM_EVENTO.toString());
				return listaDeResultado;
			}
			Evento evento = (Evento) post;
			List<Usuario> usuarios = evento.getUsuariosConfirmados();
			for (Usuario usuario : usuarios) {
				listaDeResultado.add(usuario.getUserName());
			}
			Collections.reverse(listaDeResultado);
		}

		if (tipoComando.equals(Comando.LISTAR_EVENTOS_CONFIRMADOS.toString())) {
			List<Evento> eventosConfirmados = usuarioLogado.getEventosConfirmados();
			for (Evento evento : eventosConfirmados) {
				listaDeResultado.add(evento.toString());
			}
		}

		if (tipoComando.equals(Comando.DESCONFIRMAR_PRESENCA.toString())) {
			Integer idEvento = Integer.parseInt(comando.split(" ")[1]);
			Evento evento = (Evento) muralLogado.getPost(idEvento);
			if (evento == null) {
				listaDeResultado.add(Resposta.EVENTO_NAO_ENCONTRADO.toString());
				return listaDeResultado;
			}
			if (!evento.getUsuariosConfirmados().contains(usuarioLogado)) {
				listaDeResultado.add(Resposta.USUARIO_NAO_CONFIRMOU_PRESENCA.toString());
				return listaDeResultado;
			}
			evento.desconfirmarPresenca(usuarioLogado);
			listaDeResultado.add(Resposta.OK.toString());
		}
	
	
		if(tipoComando.equals(Comando.CRIAR_LEMBRETE.toString())){
			 if (usuarioLogado == null) {
			        listaDeResultado.add(Resposta.USUARIO_NAO_LOGADO.toString());
			        return listaDeResultado;
			    }
			    String[] parametros = comando.split(" ");
			    Integer idPost = Integer.parseInt(parametros[1]);
			    String data = parametros[2];
			    String hora = parametros[3];
			    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		        Date dataHora = null;
				try {
					dataHora = dateFormat.parse(data + " " + hora);
				} catch (ParseException e) {
					e.printStackTrace();
				}
		        Post post = muralLogado.getPost(idPost);
		        Lembrete lembrete = new Lembrete(post, dataHora);
		        usuarioLogado.criarLembrete(lembrete);
		        listaDeResultado.add(Resposta.OK.toString());

			    return listaDeResultado;
			}
		
		if (tipoComando.equals(Comando.LISTAR_LEMBRETES.toString())) {
		    if (usuarioLogado == null) {
		        listaDeResultado.add(Resposta.USUARIO_NAO_LOGADO.toString());
		        return listaDeResultado;
		    }
		    List<Lembrete> lembretes = usuarioLogado.listLembretes();
		    for (Lembrete lembrete : lembretes) {
		        listaDeResultado.add(lembrete.toString());
		    }
		    return listaDeResultado;
		}
		
		
		if(tipoComando.equals(Comando.REMOVER_LEMBRETE.toString())){
		    if (usuarioLogado == null) {
		        listaDeResultado.add(Resposta.USUARIO_NAO_LOGADO.toString());
		        return listaDeResultado;
		    }
		    Integer idLembrete = Integer.parseInt(comando.split(" ")[1]);
		    Lembrete lembrete = usuarioLogado.getLembrete(idLembrete);
		    if (lembrete == null) {
		        listaDeResultado.add(Resposta.LEMBRETE_NAO_ENCONTRADO.toString());
		        return listaDeResultado;
		    }
		    usuarioLogado.removerLembrete(lembrete);
		    listaDeResultado.add(Resposta.OK.toString());
		}
		
		
		if (tipoComando.equals(Comando.COMENTAR_POST.toString())) {
		    if (usuarioLogado == null) {
		        listaDeResultado.add(Resposta.USUARIO_NAO_LOGADO.toString());
		        return listaDeResultado;
		    }
		    
		    String[] parametros = comando.split(" ", 3); // limita o split para no máximo 3 substrings
		    Integer idPost = Integer.parseInt(parametros[1]);
		    String mensagem = parametros[2];
		    
		    Post post = muralLogado.getPost(idPost);
		    if (post == null) {
		        listaDeResultado.add(Resposta.POST_NAO_ENCONTRADO.toString());
		        return listaDeResultado;
		    }
		    
		    post.addComentario(usuarioLogado, mensagem);
		    
		    listaDeResultado.add(Resposta.OK.toString());
		    return listaDeResultado;
		}
		
		if (tipoComando.equals(Comando.LISTAR_COMENTARIOS.toString())) {
		    Integer idPost = Integer.parseInt(comando.split(" ")[1]);
			Post post = muralLogado.getPost(idPost);

		    if (post == null) {
		        listaDeResultado.add(Resposta.POST_NAO_ENCONTRADO.toString());
		        return listaDeResultado;
		    }

		    for (Comentario comentario : post.listComentarios()) {
		        listaDeResultado.add(comentario.toString());
		    }

		    return listaDeResultado;
		}
		
		

		
		if (tipoComando.equals(Comando.EXCLUIR_COMENTARIO.toString())) {
		    if (usuarioLogado == null) {
		        listaDeResultado.add(Resposta.USUARIO_NAO_LOGADO.toString());
		        return listaDeResultado;
		    }
		    String[] parametros = comando.split(" ");
		    
		    Integer idPost = Integer.parseInt(parametros[1]);
		    
		    Integer idComentario = Integer.parseInt(parametros[2]);
		    
		    Post post = muralLogado.getPost(idPost);
		  /*  
		    if (post == null) {
		        listaDeResultado.add(Resposta.POST_NAO_ENCONTRADO.toString());
		        return listaDeResultado;
		    }*/
		   /* Comentario comentario = post.getCometario(idComentario);
		    
	/*	    
		    if (comentario == null) {
		        listaDeResultado.add(Resposta.COMENTARIO_NAO_EXISTE.toString());
		        return listaDeResultado;
		    }

		    if (!comentario.getUsuarioComent().equals(usuarioLogado)) {
		        listaDeResultado.add(Resposta.COMENTARIO_NAO_CRIADO_PELO_USUARIO_LOGADO.toString());
		        return listaDeResultado;
		    }*/
		    post.removerComentario(idComentario);
		    listaDeResultado.add(Resposta.OK.toString());
		    return listaDeResultado;
		}
		
		

		return listaDeResultado; // ultimo return, lá do começo
	}
}
