package br.ufpr.mural.server;

public enum Comando {

	CRIAR_USUARIO("criar-usuario"), LIMPAR_BANCO("limpar-banco"), CRIAR_MURAL("criar-mural"),
	POSTAR_ANUNCIO("postar-anuncio"), POSTAR_EVENTO("postar-evento"), LISTAR_MURAIS("listar-murais"), LOGIN("login"),
	LOGOUT("logout"), USAR_MURAL("usar-mural"), POSTAR("postar"), LISTAR_POSTS("listar-posts"),
	EXCLUIR_POST("excluir-post"), SALVAR_POST("salvar-post"), LISTAR_SALVOS("listar-salvos"),
	EXCLUIR_SALVO("excluir-salvo"), CURTIR_POST("curtir-post"), ODIAR_POST("odiar-post"),
	COLOCAR_SEM_NOCAO_POST("colocar-sem-nocao-post"), LISTAR_REACOES("listar-reacoes"),
	REMOVER_REACAO_POST("remover-reacao-post"), LISTAR_CURTIDAS("listar-curtidas"),
	CONFIRMAR_PRESENCA("confirmar-presenca"), LISTAR_PARTICIPANTES("listar-participantes-evento"),
	LISTAR_EVENTOS_CONFIRMADOS("listar-eventos-confirmados"), DESCONFIRMAR_PRESENCA("desconfirmar-presenca"),
	CRIAR_LEMBRETE("criar-lembrete"), LISTAR_LEMBRETES("listar-lembretes"), REMOVER_LEMBRETE("remover-lembrete"),
	COMENTAR_POST("comentar"), LISTAR_COMENTARIOS("listar-comentarios"), EXCLUIR_COMENTARIO("excluir-comentario"),
	SUGERIR_POST("sugerir"), LISTAR_SUGESTOES("listar-sugestoes"), LISTAR_SUGERIDOS("listar-sugeridos"),
	LISTAR_SUGESTOES_POST("listar-sugestoes-post");

	private String nomeComando;

	Comando(String nomeComando) {
		this.nomeComando = nomeComando;
	}

	@Override
	public String toString() {
		return this.nomeComando;
	}
}