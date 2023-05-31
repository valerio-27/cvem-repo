package it.academy.gaming.milionario.core.domain;

public class ConoscenzaCategoria {

	private Categoria categoria;
	private int conoscenza;

	private ConoscenzaCategoria(Categoria categoria, int conoscenza) {
		this.categoria = categoria;
		this.conoscenza = conoscenza;
	}

	public static ConoscenzaCategoria genera(Categoria categoria, RangeCulturaGenerale rangeCulturaGenerale) {
		return new ConoscenzaCategoria(categoria, rangeCulturaGenerale.getValore());
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public int getConoscenza() {
		return conoscenza;
	}

}