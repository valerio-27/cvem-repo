package it.academy.gaming.milionario.manager.grafics.exceptions;

public class OpzioniNonValideException extends Exception {

	private OpzioniNonValideException(String message) {
		super(message);
	}

	public static OpzioniNonValideException opzioniNonValide() {
		return new OpzioniNonValideException(
				"Le opzioni non possono contenere valori inferiori o superiori ai massimi e ai minimi indicati");
	}

	public static OpzioniNonValideException conoscenzaMinimaNonValida() {
		return new OpzioniNonValideException(
				"La conoscenza minima deve essere maggiore del minimo indicato e minore della conoscenza massima");
	}

	public static OpzioniNonValideException conoscenzaMassimaNonValida() {
		return new OpzioniNonValideException(
				"La conoscenza massima deve essere minore del massimo indicato e maggiore della conoscenza minima ");
	}

	public static OpzioniNonValideException percentualeFortunaNonValida() {
		return new OpzioniNonValideException(
				"La percentuale della fortuna deve essere compresa tra il valore minimo e massimo indicati");
	}

}
