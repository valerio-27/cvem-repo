package it.academy.gaming.milionario.core.domain;

import java.util.Random;

public class ConoscenzaCategoria {

	private static Random random = new Random();

	private Categoria categoria;
	private int conoscenza;

	private ConoscenzaCategoria(Categoria categoria, int conoscenza) {
		this.categoria = categoria;
		this.conoscenza = conoscenza;
	}

	public static ConoscenzaCategoria genera(Categoria categoria) {
		return new ConoscenzaCategoria(categoria, random.nextInt(100) + 1);
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public int getConoscenza() {
		return conoscenza;
	}

}