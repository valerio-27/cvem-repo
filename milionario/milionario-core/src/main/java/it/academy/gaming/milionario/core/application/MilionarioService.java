package it.academy.gaming.milionario.core.application;

import it.academy.gaming.milionario.core.application.commands.IniziaPartitaCommand;
import it.academy.gaming.milionario.core.application.views.PartitaView;
import it.academy.gaming.milionario.core.application.views.SuggerimentoView;
import it.academy.gaming.milionario.core.application.views.VotazioneView;
import it.academy.gaming.milionario.core.domain.LetteraRisposta;
import it.academy.gaming.milionario.core.domain.Partita;
import it.academy.gaming.milionario.core.domain.Suggerimento;
import it.academy.gaming.milionario.core.domain.Votazione;
import it.academy.gaming.milionario.core.domain.exceptions.AiutoNonDisponibileException;
import it.academy.gaming.milionario.core.domain.exceptions.PartitaException;

public class MilionarioService {

	private Partita partita;

	public PartitaView iniziaPartita(IniziaPartitaCommand command) {
		partita.inizia(null);
		return new PartitaView(partita);
	}

	public PartitaView usaAiutoComputer() throws AiutoNonDisponibileException {
		partita.usaAiutoComputer();
		return new PartitaView(partita);
	}

	public SuggerimentoView usaAiutoCasa() throws AiutoNonDisponibileException {
		Suggerimento suggerimento = partita.usaAiutoCasa();

		return new SuggerimentoView(suggerimento);
	}

	public VotazioneView usaAiutoPubblico() throws AiutoNonDisponibileException {
		Votazione votazione = this.partita.usaAiutoPubblico();
		return new VotazioneView(votazione);
	}

	public PartitaView indovina(LetteraRisposta lettera) {
		return new PartitaView(partita);
	}
	
	public PartitaView continua() throws PartitaException {
		this.partita.continua();
		return new PartitaView(partita);
	}
}