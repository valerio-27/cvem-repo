package it.academy.gaming.milionario.manager.core_impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

import it.academy.gaming.milionario.core.domain.PercentualeFortuna;
import it.academy.gaming.milionario.core.domain.RangeCulturaGenerale;
import it.academy.gaming.milionario.manager.core.domain.OpzioniPersonaRepository;

public class OpzioniPersonaRepositoryImpl implements OpzioniPersonaRepository {
	private OutputStream output;
	private Properties properties;

	public OpzioniPersonaRepositoryImpl(String pathDir) throws FileNotFoundException {
		super();
		this.output = new FileOutputStream(new File(pathDir));
		this.properties = new Properties();
	}

	@Override
	public void setRangeCulturaGenerale(RangeCulturaGenerale range) {

		properties.setProperty("min_conoscenza", String.valueOf(range.getMin()));
		properties.setProperty("max_conoscenza", String.valueOf(range.getMax()));
		

		try {
			properties.store(output, "Opzioni Persona");
		} catch (IOException e) {
		}

	}

	@Override
	public void setPercentualeFortuna(PercentualeFortuna percentualeFortuna) {

		properties.setProperty("percentuale_fortuna", String.valueOf(percentualeFortuna.getPercentualeFortuna()));

		try {
			properties.store(output, "Opzioni Persona");
		} catch (IOException e) {
		}

	}

}
