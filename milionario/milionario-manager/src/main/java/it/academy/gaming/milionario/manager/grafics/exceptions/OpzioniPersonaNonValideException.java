package it.academy.gaming.milionario.manager.grafics.exceptions;

public class OpzioniPersonaNonValideException extends Exception {

	private OpzioniPersonaNonValideException(String message) {
		super(message);
	}

	public static OpzioniPersonaNonValideException opzioniNonValide() {
		return new OpzioniPersonaNonValideException(
				"Le opzioni non possono contenere valori inferiori o superiori ai massimi e ai minimi indicati");
	}

	public static OpzioniPersonaNonValideException conoscenzaMinimaNonValida() {
		return new OpzioniPersonaNonValideException(
				"La cultura generale minima deve essere maggiore del minimo indicato e minore della cultura generale massima");
	}

	public static OpzioniPersonaNonValideException conoscenzaMassimaNonValida() {
		return new OpzioniPersonaNonValideException(
				"La cultura generale massima deve essere minore del massimo indicato e maggiore della cultura generale minima ");
	}

	public static OpzioniPersonaNonValideException percentualeFortunaNonValida() {
		return new OpzioniPersonaNonValideException(
				"La percentuale della fortuna deve essere compresa tra il valore minimo e massimo indicati");
	}

}
