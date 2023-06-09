package it.academy.gaming.milionario.core.domain;

import java.util.Collection;

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
	private boolean terminata = true;
	private boolean inAttesa;

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
		if (!terminata || iniziata) {
			throw PartitaException.giaInCorso();
		}
		this.livelloDifficolta = 1;
		terminata = false;
		iniziata = true;
		inAttesa = true;
		this.giocatore = giocatore;
		this.aiuti.ripristinaAiuti();

		aggiornaQuesito();
	}

	public boolean indovina(LetteraRisposta lettera) throws PartitaException {
		if (terminata || !iniziata) {
			throw PartitaException.nonInCorso();
		}
		if (!inAttesa) {
			throw PartitaException.nonInAttesa();
		}

		checkRispostaPresente(lettera);

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
		inAttesa = false;
		
		return this.terminata;
	}

	private void checkRispostaPresente(LetteraRisposta lettera) throws PartitaException {

		boolean letteraPresente = false;
		for (Risposta risposta : quesitoAttuale.getRisposteDisponibili()) {
			if (risposta.getLettera().equals(lettera)) {
				letteraPresente = true;
			}
		}
		if (!letteraPresente) {
			throw PartitaException.rispostaNonPresente();
		}
	}

	public void continua() throws PartitaException {
		if (!quesitoIndovinato || terminata) {
			throw PartitaException.nonContinuabile();
		}
		livelloDifficolta++;
		aggiornaQuesito();
		quesitoIndovinato = false;
		inAttesa = true;
	}

	// prendi il valore della domanda appena indovinata
	public void ritirati() throws PartitaException {
		if (terminata || !quesitoIndovinato) {
			throw PartitaException.ritiroNonConsentito();
		}
		this.terminata = true;
		this.iniziata = false;
		quesitoIndovinato = false;
		inAttesa = false;

		registraPartita();
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
			this.quesitoAttuale = quesitoRepository.findRandomByDifficolta(new Difficolta(livelloDifficolta));
		} catch (DifficoltaNonInRangeException ignored) {
		}
	}

	private void registraPartita() {
		Valore valore = quesitoAttuale.getValore();
		PartitaGiocata partitaGiocata = new PartitaGiocata(giocatore, valore);
		if (valore.getEuro() != 0) {
			classifica.registra(partitaGiocata);
		}
	}

	public Collection<PartitaGiocata> getPartiteGiocate() {

		return this.classifica.getListaPartite();
	}

	public int getEuroRimanenti() {
		Valore valore = quesitoAttuale.getValore();
		valore.ricalcolaPerCheckpoints();
		return valore.getEuro();
	}

	public int getValore() {
		return quesitoAttuale.getValore().getEuro();
	}

	public String getTestoRispostaCorretta() {
		return quesitoAttuale.getTestoRispostaCorretta();
	}

}