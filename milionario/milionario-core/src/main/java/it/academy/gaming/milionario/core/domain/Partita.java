package it.academy.gaming.milionario.core.domain;

import it.academy.gaming.milionario.core.domain.exceptions.AiutoNonDisponibileException;
import it.academy.gaming.milionario.core.domain.exceptions.DifficoltaNonInRangeException;
import it.academy.gaming.milionario.core.domain.exceptions.PartitaException;

/**
 * viene instanziata sollo allo start dell'applicazione
 * 
 * mi tengo dei checkpoint
 * 
 * @author Valerio.Crispini
 *
 */
public class Partita {

	private Giocatore giocatore;
	private Classifica classifica;
	private QuesitoRepository quesitoRepository;
	private Quesito quesitoAttuale;
	private Aiuti aiuti;

	private boolean iniziata;
	private boolean terminata;

	private int livelloDifficolta;
	private boolean quesitoIndovinato;

	public Partita(QuesitoRepository quesitoRepository, OpzioniPersonaRepository opzioniPersonaRepository,
			PartitaGiocataRepository partitaGiocataRepository) {
		this.classifica = new Classifica(partitaGiocataRepository);
		this.quesitoRepository = quesitoRepository;
		this.aiuti = new Aiuti(opzioniPersonaRepository.getRangeCulturaGenerale(),
				opzioniPersonaRepository.getPercentualeFortuna());

	}

	public void inizia(Giocatore giocatore) throws PartitaException {
		if(!terminata||iniziata) {
			throw PartitaException.giaInCorso();
		}
		this.livelloDifficolta = 1;
		terminata = false;
		iniziata = true;
		this.giocatore = giocatore;

		aggiornaQuesito();
	}

	public void indovina(LetteraRisposta lettera) throws PartitaException {
		if(terminata||!iniziata) {
			throw PartitaException.nonInCorso();
		}
		if (this.quesitoAttuale.indovina(lettera)) {
			this.quesitoIndovinato = true;
			if (livelloDifficolta == 15) {
				this.terminata = true;
				this.iniziata = false;
				
				registraPartita();
			}
		} else {
			quesitoAttuale.getValore().ricalcolaPerCheckpoints();
			this.terminata = true;
			this.iniziata = false;

			registraPartita();
		}
	}

	public void continua() throws PartitaException {
		if (!quesitoIndovinato || terminata) {
			throw PartitaException.nonContinuabile();
		}
		livelloDifficolta++;
		aggiornaQuesito();
		quesitoIndovinato = false;
	}

	// prendi il valore della domanda appena indovinata
	public PartitaGiocata ritirati() throws PartitaException {
		if (terminata || !quesitoIndovinato) {
			throw PartitaException.ritiroNonConsentito();
		}
		this.terminata = true;
		this.iniziata = false;
		quesitoIndovinato = false;

		return registraPartita();
	}

	public void usaAiutoComputer() throws AiutoNonDisponibileException {
		this.aiuti.usaAiutoComputer(quesitoAttuale);

	}

	public Suggerimento usaAiutoCasa() throws AiutoNonDisponibileException {
		return this.aiuti.usaAiutoCasa(quesitoAttuale, giocatore);
	}

	public Votazione usaAiutoPubblico() throws AiutoNonDisponibileException {
		return this.aiuti.usaAiutoPubblico(quesitoAttuale);

	}

	public Quesito getQuesitoAttuale() {
		return quesitoAttuale;
	}

	public Aiuti getAiuti() {
		return aiuti;
	}

	public boolean isQuesitoIndovinato() {
		return quesitoIndovinato;
	}

	public Giocatore getGiocatore() {
		return giocatore;
	}

	public Classifica getClassifica() {
		return classifica;
	}

	public QuesitoRepository getQuesitoRepository() {
		return quesitoRepository;
	}

	public boolean isIniziata() {
		return iniziata;
	}

	public boolean isTerminata() {
		return terminata;
	}

	public int getLivelloDifficolta() {
		return livelloDifficolta;
	}

	private void aggiornaQuesito() {
		try {
			this.quesitoAttuale = quesitoRepository.findByCategoriaAndDifficolta(Categoria.getRandom(),
					new Difficolta(livelloDifficolta));
		} catch (DifficoltaNonInRangeException ignored) {
		}
	}

	private PartitaGiocata registraPartita() {
		PartitaGiocata partitaGiocata = new PartitaGiocata(giocatore, quesitoAttuale.getValore());
		classifica.registra(partitaGiocata);
		return partitaGiocata;
	}

}