package it.academy.gaming.milionario.manager.core.commands;

public class InserisciQuesitoCommand {

	private InserisciDomandaCommand domandaRequest;
	private InserisciRispostaCommand[] rispostaRequests;
	private int livelloDifficolta;

	public InserisciQuesitoCommand(InserisciDomandaCommand domandaRequest, InserisciRispostaCommand[] rispostaRequests,
			int livelloDifficolta) {
		super();
		this.domandaRequest = domandaRequest;
		this.rispostaRequests = rispostaRequests;
		this.livelloDifficolta = livelloDifficolta;
	}

	public InserisciDomandaCommand getDomandaCommand() {
		return domandaRequest;
	}

	public InserisciRispostaCommand[] getRispostaCommands() {
		return rispostaRequests;
	}

	public int getLivelloDifficolta() {
		return livelloDifficolta;
	}

}
