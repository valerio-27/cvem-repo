package it.academy.gaming.milionario.core.implementation.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import it.academy.gaming.milionario.core.domain.OpzioniPersonaRepository;
import it.academy.gaming.milionario.core.domain.PercentualeFortuna;
import it.academy.gaming.milionario.core.domain.RangeCulturaGenerale;
import it.academy.gaming.milionario.core.domain.exceptions.CulturaGeneraleNonInRangeException;
import it.academy.gaming.milionario.core.domain.exceptions.PercentualeFortunaNonInRangeException;

public class OpzioniPersonaRepositoryImpl implements OpzioniPersonaRepository {
	private Properties properties;

	public OpzioniPersonaRepositoryImpl(String absoluteFilePath) {
		super();
		loadProperties(absoluteFilePath);

	}

	private void loadProperties(String absoluteFilePath) {
		properties = new Properties();
		try (FileInputStream input = new FileInputStream(new File(absoluteFilePath))) {
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

}
