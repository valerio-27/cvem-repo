package it.academy.gaming.milionario.screen.interfaze;

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

	public static String coloraRosso(String testo) {
		return "\u001B[31m" + testo + "\u001B[0m";
	}

	public static void cadenzaPerCarattere(String testo) {
		int numeroCaratteri = testo.length();
		int cadenzaInMIlliecondi = 60 * numeroCaratteri;
		cadenzaFrase(testo, cadenzaInMIlliecondi);
	}

	public static void cadenzaFrase(String testo, int cadenzaFrase) {

		int caratteri = testo.length();

		int cadenzaCarattere = cadenzaFrase / caratteri;

		cadenza(testo, cadenzaCarattere);

	}

}
