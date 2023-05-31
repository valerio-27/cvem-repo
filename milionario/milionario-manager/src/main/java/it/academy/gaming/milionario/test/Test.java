package it.academy.gaming.milionario.test;

import java.io.FileNotFoundException;

import it.academy.gaming.milionario.core.domain.exceptions.CodiceInvalidoException;
import it.academy.gaming.milionario.core.domain.exceptions.CreazioneDomandaException;
import it.academy.gaming.milionario.core.domain.exceptions.CreazioneQuesitoException;
import it.academy.gaming.milionario.core.domain.exceptions.DifficoltaNonInRangeException;
import it.academy.gaming.milionario.core.domain.exceptions.NumeroMassimoRisposteSuperatoException;
import it.academy.gaming.milionario.core.domain.exceptions.TestoRispostaAssenteException;
import it.academy.gaming.milionario.manager.core.application.CvemService;
import it.academy.gaming.milionario.manager.core.domain.OpzioniPersonaRepository;
import it.academy.gaming.milionario.manager.core.domain.QuesitoRepository;
import it.academy.gaming.milionario.manager.core_impl.OpzioniPersonaRepositoryImpl;
import it.academy.gaming.milionario.manager.core_impl.QuesitoRepositoryImplementation;
import it.academy.gaming.milionario.manager.grafics.controller.CvemController;

public class Test {
	public static void main(String[] args)
			throws CreazioneDomandaException, TestoRispostaAssenteException, DifficoltaNonInRangeException,
			NumeroMassimoRisposteSuperatoException, CreazioneQuesitoException, CodiceInvalidoException, FileNotFoundException {
		String url = "jdbc:mysql://localhost:3306/milionario";
		String user = "root";
		String password = "admin";

		QuesitoRepository repository = new QuesitoRepositoryImplementation(url, user, password);
		String pathDir="C:/Users/aniso/milionario_opzioni_persona.properties";
		OpzioniPersonaRepository opzioniPersonaRepository=new OpzioniPersonaRepositoryImpl(pathDir);
		CvemService service = new CvemService(repository,opzioniPersonaRepository);
		CvemController controller = new CvemController(service);
		controller.showMenuScreen();

//		QuesitoBuilder builder = Quesito.builder();
//
//		InformazioniDomanda informazioniDomanda = new InformazioniDomanda(
//				"https://t1.gstatic.com/licensed-image?q=tbn:ANd9GcRBKouHO57uTjWH-ImFXgHxryC617ISal9NYFkomALCV2o0EgG5DqT1myoE1D5Ehi7a",
//				null);
//
//		Domanda domanda = new Domanda("Quanti pianeti ci sono nel sistema solare?", Categoria.SCIENZA,
//				informazioniDomanda);
//		builder.setDomanda(domanda);
//
//		builder.setDifficolta(new Difficolta(4));
//
//		Risposta risposta = Risposta.crea("9", false);
//		builder.aggiungiRisposta(risposta);
//
//		risposta = Risposta.crea("7", false);
//		builder.aggiungiRisposta(risposta);
//
//		risposta = Risposta.crea("10", false);
//		builder.aggiungiRisposta(risposta);
//
//		risposta = Risposta.crea("8", true);
//		builder.aggiungiRisposta(risposta);
//
//		Quesito quesito = builder.build();
//		repository.add(quesito);

//		Collection<Quesito> quesiti = repository.findByLivelloDifficolta(new Difficolta(10));
//		System.out.println();

//		List<Risposta> nuoveRisposte = new ArrayList<>();

//		informazioniDomanda = new InformazioniDomanda(
//				"https://t1.gstatic.com/licensed-image?q=tbn:ANd9GcRBKouHO57uTjWH-ImFXgHxryC617ISal9NYFkomALCV2o0EgG5DqT1myoE1D5Ehi7a",
//				"https://it.wikipedia.org/wiki/Sistema_solare");
//
//		domanda = new Domanda("Ciao?", Categoria.STORIA, informazioniDomanda);
//
//		repository.setDomanda(CodiceQuesito.parse("bWnUG3tl"), domanda);

//		repository.remove(CodiceQuesito.parse("bpwhApo9"));

	}
}
