package it.academy.gaming.milionario.test;

import it.academy.gaming.milionario.core.application.MilionarioService;
import it.academy.gaming.milionario.core.domain.OpzioniPersonaRepository;
import it.academy.gaming.milionario.core.domain.PartitaGiocataRepository;
import it.academy.gaming.milionario.core.domain.QuesitoRepository;
import it.academy.gaming.milionario.core.implementation.db.QuesitoRepositoryImpl;

public class Test {

	public static void main(String[] args) {

		String url = "jdbc:mysql://localhost:3306/milionario";
		String user = "root";
		String password = "admin";

		QuesitoRepository quesitoRepository = new QuesitoRepositoryImpl(url, user, password);
		OpzioniPersonaRepository opzioniPersonaRepository;
		PartitaGiocataRepository partitaGiocataRepository;

		MilionarioService service = new MilionarioService(null, null, null);
	}

}
