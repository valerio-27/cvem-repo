package it.academy.gaming.milionario.core.application.views;

import it.academy.gaming.milionario.core.domain.PartitaGiocata;

public class PartitaGiocataView implements Comparable<PartitaGiocataView>{

	private String nome;
	private int euro;

	public PartitaGiocataView(PartitaGiocata partitaGiocata) {
		this.nome = partitaGiocata.getGiocatore().getNome();
		this.euro = partitaGiocata.getValore().getEuro();
	}

	public String getNome() {
		return nome;
	}

	public int getEuro() {
		return euro;
	}

	@Override
	public String toString() {
		return  nome + ", premio: " + euro;
	}

	@Override
	public int compareTo(PartitaGiocataView o) {
	    // Ordinamento decrescente per il valore degli euro
	    return Integer.compare(o.getEuro(), this.euro);
	}
	
	

}
