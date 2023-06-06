package it.academy.gaming.milionario.core.application.views;

import java.util.Collection;

import it.academy.gaming.milionario.core.domain.Aiuti;
import it.academy.gaming.milionario.core.domain.Domanda;
import it.academy.gaming.milionario.core.domain.InformazioniDomanda;
import it.academy.gaming.milionario.core.domain.Partita;
import it.academy.gaming.milionario.core.domain.Quesito;
import it.academy.gaming.milionario.core.domain.Risposta;

public class PartitaView {

	private DomandaView domandaView;
	private RispostaView[] risposteView;
	private boolean aiutoCasaDisponibile;
	private boolean aiutoComputerDisponibile;
	private boolean aiutoPubblicoDisponibile;

	private int euro;
	private boolean quesitoIndovinato;

	public PartitaView(Partita partita) {

		Quesito quesito=partita.getQuesitoAttuale();
		
		Domanda domanda = quesito.getDomanda();
		Aiuti aiuti=partita.getAiuti();
		Collection<Risposta> risposte = quesito.getRisposteDisponibili();

		InformazioniDomanda informazioni = domanda.getInformazioni();
		this.domandaView = new DomandaView(domanda.getTesto(), domanda.getCategoria(),
				new InformazioniView(informazioni.getUrlImmagine(), informazioni.getUrlDocumentazione()));
		this.risposteView = new RispostaView[risposte.size()];

		int i = 0;
		for (Risposta risposta : risposte) {
			risposteView[i++] = new RispostaView(risposta);
		}

		this.aiutoCasaDisponibile = aiuti.isAiutoCasaDisponibile();
		this.aiutoComputerDisponibile = aiuti.isAiutoComputerDisponibile();
		this.aiutoPubblicoDisponibile = aiuti.isAiutoPubblicoDisponibile();

		this.euro = quesito.getValore().getEuro();
		
		this.quesitoIndovinato=  partita.isQuesitoIndovinato();
	}

	public DomandaView getDomandaView() {
		return domandaView;
	}

	public RispostaView[] getRisposteView() {
		return risposteView;
	}

	public boolean isAiutoCasaDisponibile() {
		return aiutoCasaDisponibile;
	}

	public boolean isAiutoComputerDisponibile() {
		return aiutoComputerDisponibile;
	}

	public boolean isAiutoPubblicoDisponibile() {
		return aiutoPubblicoDisponibile;
	}

	public int getEuro() {
		return euro;
	}

	public boolean isQuesitoIndovinato() {
		return quesitoIndovinato;
	}

	
	
}