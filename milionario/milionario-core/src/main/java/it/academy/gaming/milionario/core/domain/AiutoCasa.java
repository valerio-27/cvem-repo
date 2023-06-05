package it.academy.gaming.milionario.core.domain;

import java.util.Collection;
import java.util.Random;

import it.academy.gaming.milionario.core.domain.exceptions.AiutoNonDisponibileException;

public class AiutoCasa {

	private static Random random = new Random();

	private RangeCulturaGenerale range;

	private boolean disponibile = true;
	private int percentualeAggiuntaCasa = 20;

	public AiutoCasa(RangeCulturaGenerale range) {
		this.range = range;
	}

	public Suggerimento vota(Quesito quesito, Giocatore giocatore) throws AiutoNonDisponibileException {

		if (!disponibile) {
			throw AiutoNonDisponibileException.aiutoCasa();
		}

		disponibile = false;

		Persona persona = PersonaDaCasa.genera(range);
		Collection<Risposta> risposteDisponibili = quesito.getRisposteDisponibili();

		Categoria categoria = quesito.getDomanda().getCategoria();

		int conoscenza = persona.getCulturaGenerale().getConoscenzaPerCategoria(categoria);

		conoscenza += SuccessoDomanda.calcola(quesito);
		if (risposteDisponibili.size() == 2) {
			conoscenza += 30;
		}

		conoscenza += percentualeAggiuntaCasa;

		Suggerimento suggerimento = null;

		// se la conoscenza è negativa ritorno il suggerimento accuratezza astenuta
		if (conoscenza < 0) {
			suggerimento = quesito.getSuggerimentoDaCasaRandom(Accuratezza.ASTENUTA);
			suggerimento.valorizzaBookmarks(giocatore, risposteDisponibili);
			return suggerimento;
		}

		ProbabilitaSuggerimenti probabilitaSuggerimenti = generaProbabilita(conoscenza);

		int numeroRandom = random.nextInt(100) + 1;

		if (numeroRandom < probabilitaSuggerimenti.getProbabilitaSbagliata()) {
			return quesito.getSuggerimentoDaCasaRandom(Accuratezza.SBAGLIATA);
		}
		if (numeroRandom < probabilitaSuggerimenti.getProbabilitaImprecisa()) {
			return quesito.getSuggerimentoDaCasaRandom(Accuratezza.IMPRECISA);
		}
		return quesito.getSuggerimentoDaCasaRandom(Accuratezza.CORRETTA);

	}

	private ProbabilitaSuggerimenti generaProbabilita(int conoscenzaFinale) {
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

	public static class ProbabilitaSuggerimenti {
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

	public void ripristina() {
		this.disponibile = true;
	}
}