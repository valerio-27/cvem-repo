package it.academy.gaming.milionario.core.implementation.file;

public class TestoUtil {

	public static void cadenza(String testo, int cadenzaInMillisecondi) {

		for (int i = 0; i < testo.length(); i++) {

			String carattere = String.valueOf(testo.charAt(i));

			try {
				Thread.sleep(cadenzaInMillisecondi);
			} catch (InterruptedException e) {
			}

			System.out.print(carattere);

		}

	}

	public static void cadenzaFrase(String testo, int cadenzaFrase) {

		int caratteri = testo.length();

		int cadenzaCarattere = cadenzaFrase / caratteri;

		cadenza(testo, cadenzaCarattere);

	}

	public static void main(String[] args) {

		String testo = "Andiamo tutti a Budapest";

		// TestoUtil.cadenza(testo, 1000);

		String testo2 = "Non vedo l'ora che arrivi la finale di settimana prossima";

		TestoUtil.cadenzaFrase(testo2, 10000);

	}
}
