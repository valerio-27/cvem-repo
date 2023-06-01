package it.academy.gaming.milionario.core.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import it.academy.gaming.milionario.core.domain.exceptions.CreazioneQuesitoException;
import it.academy.gaming.milionario.core.domain.exceptions.NumeroMassimoRisposteSuperatoException;
import it.academy.gaming.milionario.core.domain.exceptions.RisposteInvalideException;
import it.academy.gaming.milionario.core.domain.exceptions.SuggerimentiInvalidiException;

public class Quesito {

	private static Random random = new Random();

	private CodiceQuesito codice;
	private Domanda domanda;
	private Risposta[] risposte;
	private Difficolta difficolta;
	private Valore valore;
	private Map<Accuratezza, List<SuggerimentoDaCasa>> suggerimentiPerAccuratezza;

	private Quesito(Domanda domanda, Risposta[] risposte, Difficolta difficolta,
			Map<Accuratezza, List<SuggerimentoDaCasa>> suggerimentiPerAccuratezza) {
		this(CodiceQuesito.crea(), domanda, risposte, difficolta, suggerimentiPerAccuratezza);
	}

	private Quesito(CodiceQuesito codiceQuesito, Domanda domanda, Risposta[] risposte, Difficolta difficolta,
			Map<Accuratezza, List<SuggerimentoDaCasa>> suggerimentiPerAccuratezza) {
		this.codice = codiceQuesito;
		this.domanda = domanda;
		this.risposte = risposte;
		this.difficolta = difficolta;
		this.suggerimentiPerAccuratezza = suggerimentiPerAccuratezza;
		this.valore = Valore.calcola(difficolta);
	}

	public static Quesito parse(CodiceQuesito codiceQuesito, Domanda domanda, Risposta[] risposte,
			Difficolta difficolta, Map<Accuratezza, List<SuggerimentoDaCasa>> suggerimentiPerAccuratezza)
			throws CreazioneQuesitoException {
		return new Quesito(codiceQuesito, domanda, risposte, difficolta, suggerimentiPerAccuratezza);
	}

	public SuggerimentoDaCasa getSuggerimentoDaCasaRandom(Accuratezza accuratezza) {
		List<SuggerimentoDaCasa> suggerimentiDaCasa = suggerimentiPerAccuratezza.get(accuratezza);
		return suggerimentiDaCasa.get(random.nextInt(suggerimentiDaCasa.size()));
	}

	public static QuesitoBuilder builder() {
		return new QuesitoBuilder();
	}

	public static class QuesitoBuilder {
		private static final int NUMERO_RISPOSTE = 4;

		private Domanda domanda;
		private Risposta[] risposte = new Risposta[NUMERO_RISPOSTE];
		private Difficolta difficolta;
		private Map<Accuratezza, List<SuggerimentoDaCasa>> suggerimentiPerAccuratezza = new HashMap<>();

		private int indiceRisposte = 0;

		private QuesitoBuilder() {
		}

		public void setDomanda(Domanda domanda) {
			this.domanda = domanda;
		}

		public void setDifficolta(Difficolta difficolta) {
			this.difficolta = difficolta;
		}

		/**
		 * Permette di agggiungere una risposta fino a che non viene superato il numero
		 * massimo consentito
		 * 
		 * @param risposta
		 * @throws NumeroMassimoRisposteSuperatoException
		 */
		public void aggiungiRisposta(Risposta risposta) throws NumeroMassimoRisposteSuperatoException {
			if (indiceRisposte == NUMERO_RISPOSTE) {
				throw new NumeroMassimoRisposteSuperatoException();
			}
			LetteraRisposta letteraRisposta = LetteraRisposta.values()[indiceRisposte];
			risposta.setLettera(letteraRisposta);

			risposte[indiceRisposte++] = risposta;
		}

		public void aggiungiSuggerimento(SuggerimentoDaCasa suggerimentoDaCasa) {

			Accuratezza accuratezza = suggerimentoDaCasa.getAccuratezza();
			List<SuggerimentoDaCasa> suggerimentiPerAccuratezza = this.suggerimentiPerAccuratezza.get(accuratezza);
			if (suggerimentiPerAccuratezza == null) {
				suggerimentiPerAccuratezza = new ArrayList<>();
				this.suggerimentiPerAccuratezza.put(accuratezza, suggerimentiPerAccuratezza);
			}
			suggerimentiPerAccuratezza.add(suggerimentoDaCasa);
		}

		/**
		 * Crea un Quesito, la domanda e la difficolta devono essere entrambe
		 * valorizzate, devono essere presenti tutte le risposte previste di cui una
		 * sola corretta
		 * 
		 * @return
		 * @throws CreazioneQuesitoException
		 * @throws SuggerimentiInvalidiException
		 */
		public Quesito build() throws CreazioneQuesitoException, SuggerimentiInvalidiException {
			if (domanda == null) {
				throw CreazioneQuesitoException.testoAssente();
			}
			if (difficolta == null) {
				throw CreazioneQuesitoException.difficolaAssente();
			}

			checkRisposteValide(Arrays.asList(this.risposte));
			checkSuggerimentiValidi();

			return new Quesito(domanda, risposte, difficolta, suggerimentiPerAccuratezza);
		}

		private void checkSuggerimentiValidi() throws SuggerimentiInvalidiException {
			Set<Accuratezza> keys = this.suggerimentiPerAccuratezza.keySet();
			if (keys.size() != Accuratezza.values().length) {
				throw new SuggerimentiInvalidiException();
			}

		}

		public static int getNumeroMassimoRisposte() {
			return NUMERO_RISPOSTE;
		}

	}

	public Domanda getDomanda() {
		return domanda;
	}

	public Risposta[] getRisposte() {
		return risposte;
	}

	public Difficolta getDifficolta() {
		return difficolta;
	}

	public CodiceQuesito getCodice() {
		return codice;
	}

	public Valore getValore() {
		return valore;
	}

	public static void checkRisposteValide(Collection<Risposta> risposte) throws RisposteInvalideException {
		int risposteCorrettePresenti = 0;

		for (Risposta risposta : risposte) {
			if (risposta == null) {
				throw RisposteInvalideException.presenteRispostaNulla();
			}
			if (risposta.isCorretta()) {
				risposteCorrettePresenti++;
			}
		}
		if (risposteCorrettePresenti != 1) {
			throw RisposteInvalideException.rispostaEsattaUnivocaAssente();
		}
	}

	public Collection<Risposta> getRisposteDisponibili() {
		Collection<Risposta> risposteDisponibili = new ArrayList<Risposta>();

		for (Risposta risposta : this.risposte) {
			if (risposta != null) {
				risposteDisponibili.add(risposta);
			}
		}
		return risposteDisponibili;
	}
}