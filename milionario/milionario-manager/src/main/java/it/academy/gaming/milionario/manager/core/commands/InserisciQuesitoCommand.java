package it.academy.gaming.milionario.manager.core.commands;

import java.util.Collection;

public class InserisciQuesitoCommand {

	private InserisciDomandaCommand domandaCommand;
	private InserisciRispostaCommand[] rispostaCommands;
	private Collection<InserisciSuggerimentoCommand> suggerimentoCommands;
	private int livelloDifficolta;

	public InserisciQuesitoCommand(InserisciDomandaCommand domandaCommand, InserisciRispostaCommand[] rispostaCommands,
			Collection<InserisciSuggerimentoCommand> suggerimentoCommands, int livelloDifficolta) {
		super();
		this.domandaCommand = domandaCommand;
		this.rispostaCommands = rispostaCommands;
		this.suggerimentoCommands = suggerimentoCommands;
		this.livelloDifficolta = livelloDifficolta;
	}

	public InserisciDomandaCommand getDomandaCommand() {
		return domandaCommand;
	}

	public InserisciRispostaCommand[] getRispostaCommands() {
		return rispostaCommands;
	}

	public Collection<InserisciSuggerimentoCommand> getSuggerimentoCommands() {
		return suggerimentoCommands;
	}

	public int getLivelloDifficolta() {
		return livelloDifficolta;
	}

}
