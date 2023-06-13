package it.academy.gaming.milionario.core.domain;

public class ProbabilitaSuggerimenti {
	private double probabilitaCorretta;
	private double probabilitaImprecisa;
	private double probabilitaSbagliata;

	private ProbabilitaSuggerimenti(double probabilitaCorretta, double probabilitaImprecisa,
			double probabilitaSbagliata) {
		super();
		this.probabilitaCorretta = probabilitaCorretta;
		this.probabilitaImprecisa = probabilitaImprecisa;
		this.probabilitaSbagliata = probabilitaSbagliata;
	}

	public static ProbabilitaSuggerimenti generaProbabilita(int conoscenzaFinale) {
		double probabilitaCorretta = 33;
		double probabilitaImprecisa = 34;
		double probabilitaSbagliata = 33;

		int percenutaleDaSottrarre = conoscenzaFinale - 1;

		double valoreDaSottrarreEffettivo = probabilitaSbagliata / 100 * percenutaleDaSottrarre;
		probabilitaSbagliata -= valoreDaSottrarreEffettivo;

		probabilitaCorretta += valoreDaSottrarreEffettivo / 100 * 75;
		probabilitaImprecisa += valoreDaSottrarreEffettivo / 100 * 25;

		return new ProbabilitaSuggerimenti(probabilitaCorretta, probabilitaImprecisa, probabilitaSbagliata);
	}

	public double getProbabilitaCorretta() {
		return probabilitaCorretta;
	}

	public double getProbabilitaImprecisa() {
		return probabilitaImprecisa;
	}

	public double getProbabilitaSbagliata() {
		return probabilitaSbagliata;
	}

	@Override
	public String toString() {
		return "probabilitaCorretta=" + probabilitaCorretta + ", probabilitaImprecisa=" + probabilitaImprecisa
				+ ", probabilitaSbagliata=" + probabilitaSbagliata + "]";
	}

}
