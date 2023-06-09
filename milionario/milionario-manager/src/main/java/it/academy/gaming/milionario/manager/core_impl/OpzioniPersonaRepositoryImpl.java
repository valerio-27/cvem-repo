package it.academy.gaming.milionario.manager.core_impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import it.academy.gaming.milionario.core.domain.PercentualeFortuna;
import it.academy.gaming.milionario.core.domain.RangeCulturaGenerale;
import it.academy.gaming.milionario.core.domain.exceptions.CulturaGeneraleNonInRangeException;
import it.academy.gaming.milionario.core.domain.exceptions.PercentualeFortunaNonInRangeException;
import it.academy.gaming.milionario.manager.core.domain.OpzioniPersonaRepository;

public class OpzioniPersonaRepositoryImpl implements OpzioniPersonaRepository {
	private Properties properties;
	private String absoluteFilePath;

	public OpzioniPersonaRepositoryImpl(String absoluteFilePath) throws FileNotFoundException {
		super();
		this.properties = new Properties();
		this.absoluteFilePath = absoluteFilePath;
		loadProperties(absoluteFilePath);
	}

	private void loadProperties(String pathDir) {
		properties = new Properties();
		try (FileInputStream input = new FileInputStream(new File(pathDir))) {
			properties.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public RangeCulturaGenerale getRangeCulturaGenerale() {
		int min = Integer.parseInt(properties.getProperty("min_conoscenza"));
		int max = Integer.parseInt(properties.getProperty("max_conoscenza"));

		RangeCulturaGenerale range = null;
		try {
			range = new RangeCulturaGenerale(min, max);
		} catch (CulturaGeneraleNonInRangeException ignored) {

		}

		return range;
	}

	@Override
	public PercentualeFortuna getPercentualeFortuna() {
		int percentuale = Integer.parseInt(properties.getProperty("percentuale_fortuna"));
		PercentualeFortuna percentualeFortuna = null;
		try {
			percentualeFortuna = new PercentualeFortuna(percentuale);
		} catch (PercentualeFortunaNonInRangeException ignored) {
		}

		return percentualeFortuna;
	}

	@Override
	public void setOpzioni(RangeCulturaGenerale range, PercentualeFortuna percentualeFortuna) {

		properties.setProperty("min_conoscenza", String.valueOf(range.getMin()));
		properties.setProperty("max_conoscenza", String.valueOf(range.getMax()));
		properties.setProperty("percentuale_fortuna", String.valueOf(percentualeFortuna.getPercentualeFortuna()));

		try {
			FileOutputStream output = new FileOutputStream(new File(absoluteFilePath), true); // Aggiunge i dati al file
			properties.store(output, "Opzioni Persona");
			output.close(); // Chiude lo stream dopo l'utilizzo
		} catch (IOException e) {
		}

	}

//	public static void main(String[] args) {
//		try {
//			OpzioniPersonaRepository opzioniPersonaRepository = new OpzioniPersonaRepositoryImpl(
//					"C:/Users/aniso/milionario_opzioni_persona.properties");
////			 opzioniPersonaRepository.setOpzioni(new RangeCulturaGenerale(30, 70), new
////			 PercentualeFortuna(25));
//			System.out.println(opzioniPersonaRepository.getRangeCulturaGenerale().toString());
//			System.out.println(opzioniPersonaRepository.getPercentualeFortuna().toString());
//		} catch (FileNotFoundException  e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

}
