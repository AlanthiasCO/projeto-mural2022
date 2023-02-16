package br.ufpr.mural.server;

public enum Resposta {

	OK("ok"), COMANDO_INVALIDO("comando-invalido"), USUARIO_JA_EXISTE("usuario-ja-existe"),
	NOME_INVALIDO("nome-invalido"), USUARIO_NAO_ENCONTRADO("usuario-nao-encontrado"),
	USUARIO_NAO_LOGADO("usuario-nao-logado"), MURAL_JA_EXISTE("mural_ja_existe"),
	MURAL_NAO_ENCONTRADO("mural-nao-encontrado"), NAO_ENTROU_EM_MURAL("nao-entrou-em-mural"),
	POST_NAO_ENCONTRADO("post-nao-encontrado"), MENSAGEM_INVALIDA("mensagem-invalida"), MURAL_VAZIO("mural-vazio"),
	NAO_AUTORIZADO("nao-autorizado"), NADA_A_MOSTRAR("nada-a-mostrar"), NAO_EH_UM_EVENTO("nao-eh-um-evento"), 
	EVENTO_NAO_ENCONTRADO("evento-nao-encontrado"), USUARIO_NAO_CONFIRMOU_PRESENCA("nao-tinha-confirmado"), FORMATO_DATA_INVALIDO("formato-data-invalido"), 
	LEMBRETE_NAO_ENCONTRADO("lembrete-nao-existe"), COMENTARIO_NAO_EXISTE("comentario-nao-existe"), COMENTARIO_NAO_CRIADO_PELO_USUARIO_LOGADO("sem-permissao");
	

	String nomeMensagem;

	Resposta(String nomeMensagem) {
		this.nomeMensagem = nomeMensagem;
	}

	@Override
	public String toString() {
		return nomeMensagem;
	}

}
