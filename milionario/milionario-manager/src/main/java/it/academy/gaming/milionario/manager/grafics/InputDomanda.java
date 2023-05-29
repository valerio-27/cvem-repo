package it.academy.gaming.milionario.manager.grafics;

import org.apache.commons.lang3.StringUtils;

import it.academy.gaming.milionario.core.domain.Categoria;
import it.academy.gaming.milionario.manager.grafics.exceptions.FormatoFraseNonCorrettoException;

public class InputDomanda {
	/*
	 * .String testo .Enum Categoria categoria .InformazioniDomanda informazioni NON
	 * obbligatorio
	 */
	private String testo;
	private Categoria categoria;
	private InputInformazioniDomanda informazioni;

	private InputDomanda(String testo, Categoria categoria, InputInformazioniDomanda informazioni) {
		super();
		this.testo = testo;
		this.categoria = categoria;
		this.informazioni = informazioni;
	}

	public String getTesto() {
		return testo;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public InputInformazioniDomanda getInformazioni() {
		return informazioni;
	}

	public static InputDomanda crea(String frase, String categoria, String urlImmagine, String urlDocumentazione)
			throws FormatoFraseNonCorrettoException {

		controllaFrase(frase);

		InputInformazioniDomanda informazioni = new InputInformazioniDomanda(urlImmagine, urlDocumentazione);

		return new InputDomanda(frase, Categoria.valueOf(categoria.toUpperCase()), informazioni);
	}

	private static void controllaFrase(String frase) throws FormatoFraseNonCorrettoException {
		/*
		 * verifico che sia un alfanumerico
		 */
		boolean fraseAccettabile = StringUtils.isNotBlank(frase);
		if (!fraseAccettabile) {
			throw new FormatoFraseNonCorrettoException("La domanda deve essere scritta in formato alfanumerico");
		}

	}

}
