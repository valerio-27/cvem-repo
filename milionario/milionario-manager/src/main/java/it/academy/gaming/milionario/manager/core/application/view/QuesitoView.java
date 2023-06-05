package it.academy.gaming.milionario.manager.core.application.view;

import java.util.List;

import it.academy.gaming.milionario.core.application.views.DomandaView;

public class QuesitoView {
	private DomandaView domandaView;
	private List<RispostaView> risposteView;
	private List<SuggerimentoView> suggerimentiView;
	private DifficoltaView difficoltaView;
	private String codice;

	public QuesitoView(DomandaView domandaView, List<RispostaView> risposteView,
			List<SuggerimentoView> suggerimentiView, DifficoltaView difficoltaView, String codice) {
		super();
		this.domandaView = domandaView;
		this.risposteView = risposteView;
		this.suggerimentiView = suggerimentiView;
		this.difficoltaView = difficoltaView;
		this.codice = codice;
	}

	public DomandaView getDomandaView() {
		return domandaView;
	}

	public List<RispostaView> getRisposteView() {
		return risposteView;
	}

	public List<SuggerimentoView> getSuggerimentiView() {
		return suggerimentiView;
	}

	public DifficoltaView getDifficoltaView() {
		return difficoltaView;
	}

	public String getCodice() {
		return codice;
	}

//TODO aggiungi la view dei suggerimenti
	@Override
	public String toString() {
		return "Quesito: " + domandaView.toString() + ";\n" + "Risposte: " + "\n" + risposteView.get(0).toString()
				+ "\n" + risposteView.get(1).toString() + "\n" + risposteView.get(2).toString() + "\n"
				+ risposteView.get(3).toString() + "\n" + "Difficolta= " + difficoltaView.toString() + ";\n" + "Codice="
				+ codice + ".";
	}

}
