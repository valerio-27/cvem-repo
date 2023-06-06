package it.academy.gaming.milionario.core.domain;

public class PartitaGiocata {

	private Giocatore giocatore;
	private Valore valore;

	public PartitaGiocata(Giocatore giocatore, Valore valore) {
		this.giocatore = giocatore;
		this.valore = valore;
	}

	public Giocatore getGiocatore() {
		return giocatore;
	}

	public Valore getValore() {
		return valore;
	}

}