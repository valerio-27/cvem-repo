package it.academy.gaming.milionario.test;

import java.io.FileNotFoundException;

import it.academy.gaming.milionario.core.domain.Accuratezza;
import it.academy.gaming.milionario.core.domain.Categoria;
import it.academy.gaming.milionario.core.domain.Difficolta;
import it.academy.gaming.milionario.core.domain.Domanda;
import it.academy.gaming.milionario.core.domain.InformazioniDomanda;
import it.academy.gaming.milionario.core.domain.Quesito;
import it.academy.gaming.milionario.core.domain.Quesito.QuesitoBuilder;
import it.academy.gaming.milionario.core.domain.Risposta;
import it.academy.gaming.milionario.core.domain.Suggerimento;
import it.academy.gaming.milionario.core.domain.exceptions.CodiceInvalidoException;
import it.academy.gaming.milionario.core.domain.exceptions.CreazioneDomandaException;
import it.academy.gaming.milionario.core.domain.exceptions.CreazioneQuesitoException;
import it.academy.gaming.milionario.core.domain.exceptions.DifficoltaNonInRangeException;
import it.academy.gaming.milionario.core.domain.exceptions.NumeroMassimoRisposteSuperatoException;
import it.academy.gaming.milionario.core.domain.exceptions.SuggerimentiInvalidiException;
import it.academy.gaming.milionario.core.domain.exceptions.SuggerimentoInvalidoException;
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
			NumeroMassimoRisposteSuperatoException, CreazioneQuesitoException, CodiceInvalidoException,
			FileNotFoundException, SuggerimentiInvalidiException, SuggerimentoInvalidoException {
		String url = "jdbc:mysql://localhost:3306/milionario";
		String user = "root";
		String password = "admin";

		QuesitoRepository repository = new QuesitoRepositoryImplementation(url, user, password);
		String absoluteFilePath = "C:/Users/aniso/milionario_opzioni_persona.properties";
		OpzioniPersonaRepository opzioniRepository = new OpzioniPersonaRepositoryImpl(absoluteFilePath);

		CvemService service = new CvemService(repository, opzioniRepository);
		CvemController controller = new CvemController(service);
		controller.showMenuScreen();

//		QuesitoBuilder builder = Quesito.builder();
//
//		InformazioniDomanda informazioniDomanda = new InformazioniDomanda(null, null);
//
//		Domanda domanda = new Domanda("Aereo civile che vola a due volte la velocità del suono", Categoria.SCIENZA,
//				informazioniDomanda);
//		builder.setDomanda(domanda);
//
//		builder.setDifficolta(new Difficolta(12));
//
//		/*
//		 * aggiu ngo le risposte
//		 */
//
//		Risposta risposta = Risposta.crea("Bell X2 Starbuster", false);
//		builder.aggiungiRisposta(risposta);
//
//		risposta = Risposta.crea("AIRBUS A 380", false);
//		builder.aggiungiRisposta(risposta);
//
//		risposta = Risposta.crea("Concorde", true);
//		builder.aggiungiRisposta(risposta);
//
//		risposta = Risposta.crea("Space Shuttle", false);
//		builder.aggiungiRisposta(risposta);
//
//		/*
//		 * aggiungo i suggerimenti
//		 */
//		Suggerimento suggerimento = Suggerimento.crea("Non è il piu' veloce ma sono sicuro sia il  ${Y}", 10,
//				Accuratezza.CORRETTA);
//		builder.aggiungiSuggerimento(suggerimento);
//
//		suggerimento = Suggerimento.crea("Sono molto indeciso, credo sia ${Y} oppure ${X}", 10, Accuratezza.IMPRECISA);
//		builder.aggiungiSuggerimento(suggerimento);
//
//		suggerimento = Suggerimento.crea("${N} penso sia ${X}", 7, Accuratezza.SBAGLIATA);
//		builder.aggiungiSuggerimento(suggerimento);
//
//		suggerimento = Suggerimento.crea("${N} mi dispiace non vorrei suggerirti male non la so...", 8,
//				Accuratezza.ASTENUTA);
//		builder.aggiungiSuggerimento(suggerimento);
//
//		Quesito quesito = builder.build();
//		repository.add(quesito);
	}
}
