package it.academy.gaming.milionario.manager.core.commands;

import java.util.List;

public class ModificaSuggerimentiCommand {
	private String codiceQuesito;
	private List<ModificaSuggerimentoCommand> modificaSuggerimentiCommands;

	public ModificaSuggerimentiCommand(String codiceQuesito,
			List<ModificaSuggerimentoCommand> modificaSuggerimentiCommands) {
		super();
		this.codiceQuesito = codiceQuesito;
		this.modificaSuggerimentiCommands = modificaSuggerimentiCommands;
	}

	public String getCodiceQuesito() {
		return codiceQuesito;
	}

	public List<ModificaSuggerimentoCommand> getModificaSuggerimentiCommands() {
		return modificaSuggerimentiCommands;
	}

}
