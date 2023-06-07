package it.academy.gaming.milionario.screen.interfaze;

public class TestoUtil {
	
	public static void main(String[] args) {
		TestoUtil.cadenzaLimitata("Ciao mi chiamo Valerio vengo da Roma ho 21 anni e tifo Roma blablalbalbaqqqqqqqqqqqqqqqqq", 50);
	}

	public static void cadenzaLimitata(String testo, int secondiEsposizione) {
		double contatoreMilliSecondi = 0;
		
		long cadenzaInMillisecondi = secondiEsposizione * 1000 / testo.length() ;
		for (int i = 0; i < testo.length(); i++) {

			if(contatoreMilliSecondi>30000) {
				break;
			}
			String carattere = String.valueOf(testo.charAt(i));
			try {
				Thread.sleep(cadenzaInMillisecondi);
			} catch (InterruptedException e) {
			}

			System.out.print(carattere);
			contatoreMilliSecondi += cadenzaInMillisecondi;
		}

	}

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
