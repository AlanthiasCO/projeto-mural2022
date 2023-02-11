package br.ufpr.mural.server;

import java.util.Scanner;

public class TestesEnum {
	
	public static void main(String[] args) {
		
		Comando comandoEntrada = Comando.valueOf("criar-mural");
		System.out.println(comandoEntrada);
		
		
		if (comandoEntrada.equals(Comando.CRIAR_MURAL)) {
			
		} else if (comandoEntrada.equals(Comando.CRIAR_USUARIO)) {
			
		}
		
		
		switch (comandoEntrada) {
			case CRIAR_MURAL:
				System.out.println("criando mural");
				break;
			case CRIAR_USUARIO:
				System.out.println("criando usuario");
	
		}
		
		
		Scanner scanner = new Scanner(System.in);
		int opcao = scanner.nextInt();
		
		if (opcao == MenuEnum.CRIAR_LISTA.getValue()) {
			System.out.println("criando lista");
		} else if (opcao == MenuEnum.INSERIR_NA_LISTA.getValue()) {
			System.out.println("inserir na lista");
		}
	}
	

}
