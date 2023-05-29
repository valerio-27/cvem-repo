package it.academy.gaming.milionario.test;

import it.academy.gaming.milionario.manager.core.application.CvemService;
import it.academy.gaming.milionario.manager.core.domain.QuesitoRepository;
import it.academy.gaming.milionario.manager.core_impl.QuesitoRepositoryImplementation;
import it.academy.gaming.milionario.manager.grafics.controller.CvemController;

public class Test {
	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/milionario";
		String user = "root";
		String password = "admin";
		QuesitoRepository repository = new QuesitoRepositoryImplementation(url, user, password);
		CvemService service = new CvemService(repository);
		CvemController controller = new CvemController(service);
		controller.showMenuScreen();

	}

}
