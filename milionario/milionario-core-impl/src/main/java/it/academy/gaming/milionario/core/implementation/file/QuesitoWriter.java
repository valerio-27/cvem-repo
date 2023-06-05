package it.academy.gaming.milionario.core.implementation.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import it.academy.gaming.milionario.core.domain.Accuratezza;
import it.academy.gaming.milionario.core.domain.Quesito;
import it.academy.gaming.milionario.core.domain.Risposta;
import it.academy.gaming.milionario.core.domain.Suggerimento;

public class QuesitoWriter {

	private static final String SEPARATORE_CAMPI = "|";

	public void salvaQuesito(Quesito quesito, File fileDestinazione) throws IOException {

		if (!fileDestinazione.exists()) {
			fileDestinazione.createNewFile();

		}

		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(fileDestinazione, true));

			String quesitoString = quesito.toString();

			writer.append("\n" + quesitoString);

		} finally {
			if (writer != null) {
				writer.close();
			}
		}
	}

	private String toStringQuesito(Quesito quesito) {

		StringBuffer buffer = new StringBuffer();

		String risposte = toStringRisposte(quesito.getRisposte());
		String urlImmagine = "UrlImmagine=" + quesito.getDomanda().getInformazioni().getUrlImmagine();
		String urlDocumentazione = "UrlDocumentazione=" + quesito.getDomanda().getInformazioni().getUrlDocumentazione();

		buffer.append(quesito.getCodice().getCodice()).append(SEPARATORE_CAMPI)
				.append("Categoria=" + quesito.getDomanda().getCategoria().toString()).append(SEPARATORE_CAMPI)
				.append(quesito.getDomanda().getTesto()).append(SEPARATORE_CAMPI).append(risposte)
				.append(SEPARATORE_CAMPI);

		if (quesito.getDomanda().hasUrlImmagine() && !quesito.getDomanda().hasUrlDocumentazione()) {
			buffer.append(urlImmagine);
		} else if (!quesito.getDomanda().hasUrlImmagine() && quesito.getDomanda().hasUrlDocumentazione()) {
			buffer.append(urlDocumentazione);
		} else if (quesito.getDomanda().hasUrlImmagine() && quesito.getDomanda().hasUrlDocumentazione()) {
			buffer.append(urlImmagine).append(SEPARATORE_CAMPI).append(urlDocumentazione);
		}

		buffer.append(SEPARATORE_CAMPI).append(quesito.getDifficolta().getDifficolta());

		List<Suggerimento> suggerimenti = null;
		Set<Entry<Accuratezza, List<Suggerimento>>> entrySet = quesito.getSuggerimentiPerAccuratezza().entrySet();
		for (Entry<Accuratezza, List<Suggerimento>> entry : entrySet) {
			suggerimenti = entry.getValue();
		}
		String suggerimentiString = toStringSuggerimentiPerAccuratezza(suggerimenti);

		buffer.append(suggerimentiString);

		String stringRecord = buffer.toString();

		return stringRecord;

	}

	private String toStringRisposte(Risposta[] risposte) {

		StringBuffer buffer = new StringBuffer();
		buffer.append("Risposte=");
		for (Risposta risposta : risposte) {
			if (risposta.isCorretta()) {
				buffer.append(risposta + "&,");
			}
			buffer.append(risposta + ",");
		}
		String stringRecord = buffer.toString();
		return stringRecord;
	}

	private String toStringSuggerimentiPerAccuratezza(List<Suggerimento> suggerimenti) {

		StringBuffer buffer = new StringBuffer();
		buffer.append("Suggerimenti=");

		for (Suggerimento suggerimento : suggerimenti) {
			buffer.append(suggerimento.getCodice().getCodice() + ",").append(suggerimento.getTesto() + ",")
					.append(suggerimento.getTempoMinimo() + "secondi,").append(suggerimento.getAccuratezza().toString())
					.append(";");
		}
		String stringRecord = buffer.toString();
		return stringRecord;
	}

}
