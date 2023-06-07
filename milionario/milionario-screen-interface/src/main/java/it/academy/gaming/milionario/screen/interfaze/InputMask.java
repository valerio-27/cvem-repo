package it.academy.gaming.milionario.screen.interfaze;

import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;

public class InputMask {

	private static Scanner scanner = new Scanner(System.in);

	private InputMask() {

	}

	public static String inserisciNomeGiocatore() {

		while (true) {
			String nome = scanner.next();
			if (StringUtils.isAlpha(nome)) {
				return nome;
			}
			System.err.println("Il nome del giocatore deve essere alfabetico");
		}
	}
}
