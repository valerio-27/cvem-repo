package it.academy.gaming.milionario.core.implementation.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import it.academy.gaming.milionario.core.domain.Quesito;

public class QuesitoWriter {

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
}
