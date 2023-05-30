package it.academy.gaming.milionario.core.domain;

import org.apache.commons.lang3.StringUtils;

public class SuggerimentoDaCasa {

	private String testo;
	private int tempoMinimo;
	private final static int TEMPO_MASSIMO = 30;
	private Accuratezza accuratezza;

	public SuggerimentoDaCasa(String testo, int tempoMinimo, Accuratezza accuratezza)
			throws SuggerimentoInvalidoException {
		if (StringUtils.isBlank(testo) || tempoMinimo < 0 || accuratezza == null) {
			throw new SuggerimentoInvalidoException();
		}
		this.testo = testo;
		this.tempoMinimo = tempoMinimo;
		this.accuratezza = accuratezza;
	}

	public String getTesto() {
		return testo;
	}

	public int getTempoMinimo() {
		return tempoMinimo;
	}

	public Accuratezza getAccuratezza() {
		return accuratezza;
	}

}