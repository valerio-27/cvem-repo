package it.academy.gaming.milionario.core.domain;

public class AiutoCasa  {

//	protected AiutoCasa(Quesito quesito) {
//		super(quesito);
//	}
//
//	public SuggerimentoDaCasa usa() {
//		super.disponibile = false;
//
//		PersonaDaCasa persona = PersonaDaCasa.genera();
//		Categoria categoria = getQuesito().getDomanda().getCategoria();
//		int conoscenzaPerCategoria = persona.getCulturaGenerale().getConoscenzaPerCategoria(categoria);
//
//		int conoscenzaFinale = calcolaConoscenzaPerDifficolta(conoscenzaPerCategoria);
//
//		ProbabilitaSuggerimenti probabilitaSuggerimneti = generaProbabilita(conoscenzaFinale);
//
//		int numeroRandom = random.nextInt(100) + 1;
//
//		if (numeroRandom < probabilitaSuggerimneti.getProbabilitaSbagliata()) {
//			return getQuesito().getSuggerimentoDaCasaRandom(Accuratezza.SBAGLIATA);
//		}
//		if (numeroRandom < probabilitaSuggerimneti.getProbabilitaImprecisa()) {
//			return getQuesito().getSuggerimentoDaCasaRandom(Accuratezza.IMPRECISA);
//		}
//		return getQuesito().getSuggerimentoDaCasaRandom(Accuratezza.CORRETTA);
//	}
//
//	private ProbabilitaSuggerimenti generaProbabilita(int conoscenzaFinale) {
//		double probabilitaCorretta = 33;
//		double probabilitaImprecisa = 34;
//		double probabilitaSbagliata = 33;
//
//		int percenutaleDaSottrarre = conoscenzaFinale - 1;
//
//		double valoreDaSottrarreEffettivo = probabilitaSbagliata / 100 * percenutaleDaSottrarre;
//		probabilitaSbagliata -= valoreDaSottrarreEffettivo;
//
//		probabilitaCorretta += valoreDaSottrarreEffettivo / 100 * 75;
//		probabilitaImprecisa += valoreDaSottrarreEffettivo / 100 * 25;
//
//		return new ProbabilitaSuggerimenti(probabilitaCorretta, probabilitaImprecisa, probabilitaSbagliata);
//
//	}
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