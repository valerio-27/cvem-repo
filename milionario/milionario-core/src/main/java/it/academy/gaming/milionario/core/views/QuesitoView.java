package it.academy.gaming.milionario.core.views;

import java.util.List;

public class QuesitoView {
	private DomandaView domandaView;
	private List<RispostaView> risposteView;
	private DifficoltaView difficoltaView;
	private String codice;

	public QuesitoView(DomandaView domandaView, List<RispostaView> risposteView, DifficoltaView difficoltaView,
			String codice) {
		super();
		this.domandaView = domandaView;
		this.risposteView = risposteView;
		this.difficoltaView = difficoltaView;
		this.codice = codice;
	}

	public DomandaView getDomandaView() {
		return this.domandaView;
	}

	public List<RispostaView> getRisposteView() {
		return this.risposteView;
	}

	public DifficoltaView getDifficoltaView() {
		return this.difficoltaView;
	}

	public String getCodice() {
		return this.codice;
	}

}
