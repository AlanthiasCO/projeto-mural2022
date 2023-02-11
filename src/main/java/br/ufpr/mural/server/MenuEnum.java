package br.ufpr.mural.server;

public enum MenuEnum {
	
	CRIAR_LISTA(1),
	INSERIR_NA_LISTA(2),
	REMOVER_DA_LISTA(3),
	SAIR(4);

	private int value;
	
	MenuEnum(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
	
	

}
