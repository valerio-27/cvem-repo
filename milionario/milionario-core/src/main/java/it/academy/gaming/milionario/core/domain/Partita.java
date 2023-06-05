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

	private Difficolta difficolta;
	private boolean quesitoIndovinato;

	public Partita(QuesitoRepository quesitoRepository, OpzioniPersonaRepository opzioniPersonaRepository,
			PartitaGiocataRepository partitaGiocataRepository) {
		this.classifica = new Classifica(partitaGiocataRepository);
		this.quesitoRepository = quesitoRepository;
		this.aiuti = new Aiuti(opzioniPersonaRepository.getRangeCulturaGenerale(),
				opzioniPersonaRepository.getPercentualeFortuna());

	}

	public void inizia(Giocatore giocatore) {
		try {
			this.difficolta = new Difficolta(1);
		} catch (DifficoltaNonInRangeException ignored) {
		}
		terminata = false;
		iniziata = true;
		this.giocatore = giocatore;

		aggiornaQuesito();
	}

	public void indovina(LetteraRisposta lettera) {
		if (this.quesitoAttuale.indovina(lettera)) {
			this.quesitoIndovinato = true;
			if (difficolta.getDifficolta() == 15) {
				this.terminata = true;
				this.iniziata=false;
			}

		} else {
			this.terminata = true;
			this.iniziata=false;
		}
	}

	public void continua() throws PartitaException {
		if (!quesitoIndovinato || terminata) {
			throw PartitaException.nonContinuabile();
		}
		difficolta.incrementa();
		aggiornaQuesito();
		quesitoIndovinato = false;
	}
	
	public void ritirati() throws PartitaException {
		if(terminata||!quesitoIndovinato) {
			throw PartitaException.ritiroNonConsentito();
		}
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

	private void aggiornaQuesito() {
		this.quesitoAttuale = quesitoRepository.findByCategoriaAndDifficolta(Categoria.getRandom(), difficolta);
	}

}