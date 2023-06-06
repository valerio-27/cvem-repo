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
import it.academy.gaming.milionario.manager.core.domain.QuesitoRepository;
import it.academy.gaming.milionario.manager.core_impl.QuesitoRepositoryImplementation;

public class Test {
//	public static void main(String[] args)
//			throws CreazioneDomandaException, TestoRispostaAssenteException, DifficoltaNonInRangeException,
//			NumeroMassimoRisposteSuperatoException, CreazioneQuesitoException, CodiceInvalidoException,
//			FileNotFoundException, SuggerimentiInvalidiException, SuggerimentoInvalidoException {
//		String url = "jdbc:mysql://localhost:3306/milionario";
//		String user = "root";
//		String password = "admin";
//
//		QuesitoRepository repository = new QuesitoRepositoryImplementation(url, user, password);
//
//		QuesitoBuilder builder = Quesito.builder();
//
//		InformazioniDomanda informazioniDomanda = new InformazioniDomanda(null, null);
//
//		Domanda domanda = new Domanda(
//				"In quale di queste isole si trovano sia la villa dell'imperatore Tiberio che la celebre Grotta Azzurra?",
//				Categoria.GEOGRAFIA, informazioniDomanda);
//		builder.setDomanda(domanda);
//
//		builder.setDifficolta(new Difficolta(3));
//
//		/*
//		 * aggiu ngo le risposte
//		 */
//
//		Risposta risposta = Risposta.crea("Capri", true);
//		builder.aggiungiRisposta(risposta);
//
//		risposta = Risposta.crea("Ponza", false);
//		builder.aggiungiRisposta(risposta);
//
//		risposta = Risposta.crea("Ischia", false);
//		builder.aggiungiRisposta(risposta);
//
//		risposta = Risposta.crea("Palmaria", false);
//		builder.aggiungiRisposta(risposta);
//
//		/*
//		 * aggiungo i suggerimenti
//		 */
//		Suggerimento suggerimento = Suggerimento.crea("${N}sicurissimo che sia ${Y}, non ho dubbi", 10, Accuratezza.CORRETTA);
//		builder.aggiungiSuggerimento(suggerimento);
//
//		suggerimento = Suggerimento.crea("non ricordo bene ,credo ${X} o ${Y}", 10, Accuratezza.IMPRECISA);
//		builder.aggiungiSuggerimento(suggerimento);
//
//		suggerimento = Suggerimento.crea("${N} ci andai tempo fa, sta a ${X} ", 13, Accuratezza.SBAGLIATA);
//		builder.aggiungiSuggerimento(suggerimento);
//
//		suggerimento = Suggerimento.crea("${N} mi dispiace non so proprio cosa dirti...", 8,
//				Accuratezza.ASTENUTA);
//		builder.aggiungiSuggerimento(suggerimento);
//
//		Quesito quesito = builder.build();
//		repository.add(quesito);
//
//	}
}
