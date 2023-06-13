package it.academy.gaming.milionario.test;

import it.academy.gaming.milionario.core.application.MilionarioService;
import it.academy.gaming.milionario.core.domain.OpzioniPersonaRepository;
import it.academy.gaming.milionario.core.domain.PartitaGiocataRepository;
import it.academy.gaming.milionario.core.domain.QuesitoRepository;
import it.academy.gaming.milionario.core.implementation.db.PartitaGiocataRepositoryDb;
import it.academy.gaming.milionario.core.implementation.db.QuesitoRepositoryImpl;
import it.academy.gaming.milionario.core.implementation.file.OpzioniPersonaRepositoryImpl;
import it.academy.gaming.milionario.screen.interfaze.controller.MilionarioController;

public class Test {

	public static void main(String[] args) throws Exception {

		String url = "jdbc:mysql://localhost:3306/milionario";
		String user = "root";
		String password = "admin";

		QuesitoRepository quesitoRepository = new QuesitoRepositoryImpl(url, user, password);
//		OpzioniPersonaRepository opzioniPersonaRepository = new OpzioniPersonaRepositoryImpl(
//				"C:/Users/aniso/milionario_opzioni_persona.properties");
		OpzioniPersonaRepository opzioniPersonaRepository = new OpzioniPersonaRepositoryImpl(
				"C:/Lavoro/Milionario/milionario_opzioni_persona.properties");
		PartitaGiocataRepository partitaGiocataRepository = new PartitaGiocataRepositoryDb(url, user, password);

		MilionarioService service = new MilionarioService(quesitoRepository, opzioniPersonaRepository,
				partitaGiocataRepository);

		MilionarioController controller = new MilionarioController(service);
		controller.showMenu();
	}
}