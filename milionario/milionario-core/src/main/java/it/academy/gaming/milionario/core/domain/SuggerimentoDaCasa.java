package it.academy.gaming.milionario.core.domain;

import java.util.Random;

import org.apache.commons.lang3.StringUtils;

public class SuggerimentoDaCasa {

	private static Random random = new Random();
	private String testo;
	private int tempoMinimo;
	private int tempoEsposizione;
	private final static int TEMPO_MASSIMO = 30;
	private Accuratezza accuratezza;

	public SuggerimentoDaCasa(String testo, int tempoMinimo, Accuratezza accuratezza)
			throws SuggerimentoInvalidoException {
		if (StringUtils.isBlank(testo) || tempoMinimo < 1 || tempoMinimo > 30 || accuratezza == null) {
			throw new SuggerimentoInvalidoException();
		}
		this.testo = testo;
		this.tempoMinimo = tempoMinimo;
		this.accuratezza = accuratezza;
	}

	void generaTempoEsposizione() {
		tempoEsposizione = tempoMinimo + random.nextInt(TEMPO_MASSIMO) + 1;
	}

	public String getTesto() {
		return testo;
	}

	public int getTempoMinimo() {
		return tempoMinimo;
	}

	public int getTempoEsposizione() {
		return tempoEsposizione;
	}

	public Accuratezza getAccuratezza() {
		return accuratezza;
	}

}