package it.academy.gaming.milionario.manager.grafics.screens;

import java.util.Scanner;

import it.academy.gaming.milionario.manager.grafics.controller.CvemController;

public abstract class Screen {
	protected Scanner scanner = new Scanner(System.in);
	protected String nome;
	protected CvemController controller;

	public Screen(String nome, CvemController controller) {
		super();
		this.nome = nome;
		this.controller = controller;
	}

	protected void showTitle() {
		mostraInfo("\n");
		mostraInfo(nome);
		mostraInfo("-----------------------------------------------------------");
	}

	protected void mostraInfo(String info) {
		System.out.println(info);
	}

	public Scanner getScanner() {
		return scanner;
	}

	public void setScanner(Scanner scanner) {
		this.scanner = scanner;
	}

	protected CvemController getController() {
		return controller;
	}

	protected void setController(CvemController controller) {
		this.controller = controller;
	}

}
