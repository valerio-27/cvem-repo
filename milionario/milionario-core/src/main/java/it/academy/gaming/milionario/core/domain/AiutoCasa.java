package it.academy.gaming.milionario.core.domain;

import java.util.Collection;

public class AiutoCasa {

	private boolean disponibile = true;
	private int percentualeAggiuntaCasa = 20;

	public void vota(RangeCulturaGenerale range, Quesito quesito, Giocatore giocatore) {
		Persona persona = PersonaDaCasa.genera(range);
		Collection<Risposta> risposteDisponibili = quesito.getRisposteDisponibili();

		Categoria categoria = quesito.getDomanda().getCategoria();

		int conoscenza = persona.getCulturaGenerale().getConoscenzaPerCategoria(categoria);

		conoscenza += SuccessoDomanda.calcola(quesito);
		if (risposteDisponibili.size() == 2) {
			conoscenza += 30;
		}

		conoscenza += percentualeAggiuntaCasa;
		// se la conoscenza è negativa ritorno il suggerimento accuratezza astenuta
		SuggerimentoDaCasa suggerimento = null;

		if (conoscenza < 0) {
			suggerimento = quesito.getSuggerimentoDaCasaRandom(Accuratezza.ASTENUTA);
			suggerimento.valorizzaBookmarks(giocatore, risposteDisponibili);
		}

		// altrimenti eseguo il calcolo delle probabilita

		// stabiliamo la probabilità di scegliere un determinato tipo di suggerimento

		// se la percentuale è negativa questa viene sostiuita dalla percentuale
		// fortuna, sennò rimane quella
		if (conoscenza < 0) {
			conoscenza = percentualeFortuna.getPercentualeFortuna();
		}

		int numero = random.nextInt(100) + 1;

		if (numero <= conoscenza) {
			try {
				builder.vota(letteraRispostaCorretta);
			} catch (PercentualeRispostaInvalidaExcpetion ignored) {
			}
		} else {
			try {
				builder.vota(daiLetteraRispostaSbagliata(risposteDisponibili));
			} catch (PercentualeRispostaInvalidaExcpetion ignored) {
			}
		}
	}

	}

//	protected AiutoCasa(Quesito quesito) {
//		super(quesito);
//	}
//
	public SuggerimentoDaCasa usa() {
		super.disponibile = false;

		PersonaDaCasa persona = PersonaDaCasa.genera();
		Categoria categoria = getQuesito().getDomanda().getCategoria();
		int conoscenzaPerCategoria = persona.getCulturaGenerale().getConoscenzaPerCategoria(categoria);

		int conoscenzaFinale = calcolaConoscenzaPerDifficolta(conoscenzaPerCategoria);

		ProbabilitaSuggerimenti probabilitaSuggerimneti = generaProbabilita(conoscenzaFinale);

		int numeroRandom = random.nextInt(100) + 1;

		if (numeroRandom < probabilitaSuggerimneti.getProbabilitaSbagliata()) {
			return getQuesito().getSuggerimentoDaCasaRandom(Accuratezza.SBAGLIATA);
		}
		if (numeroRandom < probabilitaSuggerimneti.getProbabilitaImprecisa()) {
			return getQuesito().getSuggerimentoDaCasaRandom(Accuratezza.IMPRECISA);
		}
		return getQuesito().getSuggerimentoDaCasaRandom(Accuratezza.CORRETTA);
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
//
//	private int calcolaConoscenzaPerDifficolta(int conoscenzaPerCategoria) {
//		int difficolta = getQuesito().getDifficolta().getDifficolta();
//
//		return conoscenzaPerCategoria - difficolta * 3;
//	}
//
//	public static class ProbabilitaSuggerimenti {
//		private double probabilitaCorretta;
//		private double probabilitaImprecisa;
//		private double probabilitaSbagliata;
//
//		private ProbabilitaSuggerimenti(double probabilitaCorretta, double probabilitaImprecisa,
//				double probabilitaSbagliata) {
//			super();
//			this.probabilitaCorretta = probabilitaCorretta;
//			this.probabilitaImprecisa = probabilitaImprecisa;
//			this.probabilitaSbagliata = probabilitaSbagliata;
//		}
//
//		public double getProbabilitaCorretta() {
//			return probabilitaCorretta;
//		}
//
//		public double getProbabilitaImprecisa() {
//			return probabilitaImprecisa;
//		}
//
//		public double getProbabilitaSbagliata() {
//			return probabilitaSbagliata;
//		}
//
//		@Override
//		public String toString() {
//			return "probabilitaCorretta=" + probabilitaCorretta + ", probabilitaImprecisa=" + probabilitaImprecisa
//					+ ", probabilitaSbagliata=" + probabilitaSbagliata + "]";
//		}
//
//	}
}