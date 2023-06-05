package it.academy.gaming.milionario.core.domain;

import it.academy.gaming.milionario.core.domain.exceptions.DifficoltaNonInRangeException;

/**
 * viene instanziata sollo allo start dell'applicazione
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
		this.quesitoAttuale = quesitoRepository.findByCategoriaAndDifficolta(Categoria.getRandom(), difficolta);
		

	}
}