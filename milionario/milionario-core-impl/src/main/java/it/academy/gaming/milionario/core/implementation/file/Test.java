package it.academy.gaming.milionario.core.implementation.file;

import java.io.File;
import java.io.IOException;

import it.academy.gaming.milionario.core.domain.Accuratezza;
import it.academy.gaming.milionario.core.domain.Categoria;
import it.academy.gaming.milionario.core.domain.CodiceQuesito;
import it.academy.gaming.milionario.core.domain.Difficolta;
import it.academy.gaming.milionario.core.domain.Domanda;
import it.academy.gaming.milionario.core.domain.InformazioniDomanda;
import it.academy.gaming.milionario.core.domain.Quesito;
import it.academy.gaming.milionario.core.domain.Quesito.QuesitoBuilder;
import it.academy.gaming.milionario.core.domain.Risposta;
import it.academy.gaming.milionario.core.domain.Suggerimento;
import it.academy.gaming.milionario.core.domain.exceptions.CreazioneDomandaException;
import it.academy.gaming.milionario.core.domain.exceptions.CreazioneQuesitoException;
import it.academy.gaming.milionario.core.domain.exceptions.DifficoltaNonInRangeException;
import it.academy.gaming.milionario.core.domain.exceptions.NumeroMassimoRisposteSuperatoException;
import it.academy.gaming.milionario.core.domain.exceptions.SuggerimentiInvalidiException;
import it.academy.gaming.milionario.core.domain.exceptions.SuggerimentoInvalidoException;
import it.academy.gaming.milionario.core.domain.exceptions.TestoRispostaAssenteException;

public class Test {

	public static void main(String[] args) throws NumeroMassimoRisposteSuperatoException, CreazioneDomandaException,
			DifficoltaNonInRangeException, TestoRispostaAssenteException, CreazioneQuesitoException,
			SuggerimentiInvalidiException, SuggerimentoInvalidoException, IOException {

		QuesitoBuilder builder = Quesito.builder();

		CodiceQuesito codiceQuesito = CodiceQuesito.crea();

		InformazioniDomanda informazioniDomanda = new InformazioniDomanda(
				"https://t1.gstatic.com/licensed-image?q=tbn:ANd9GcRBKouHO57uTjWH-ImFXgHxryC617ISal9NYFkomALCV2o0EgG5DqT1myoE1D5Ehi7a",
				null);

		Domanda domanda = new Domanda("Quanti pianeti ci sono nel sistema solare?", Categoria.SCIENZA,
				informazioniDomanda);
		builder.setDomanda(domanda);

		builder.setDifficolta(new Difficolta(4));

		Risposta risposta = Risposta.crea("9", false);
		builder.aggiungiRisposta(risposta);

		risposta = Risposta.crea("7", false);
		builder.aggiungiRisposta(risposta);

		risposta = Risposta.crea("10", false);
		builder.aggiungiRisposta(risposta);

		risposta = Risposta.crea("8", true);
		builder.aggiungiRisposta(risposta);

		Suggerimento suggerimento = Suggerimento.crea("Sono sicuro che sia ${Y}", 3, Accuratezza.CORRETTA);
		builder.aggiungiSuggerimento(suggerimento);

		suggerimento = Suggerimento.crea("Non ne ho idea", 2, Accuratezza.ASTENUTA);
		builder.aggiungiSuggerimento(suggerimento);

		suggerimento = Suggerimento.crea("Penso che la risposta giusta sia ${X}", 5, Accuratezza.SBAGLIATA);
		builder.aggiungiSuggerimento(suggerimento);

		suggerimento = Suggerimento.crea("Sono indeciso tra ${Y} e ${X}", 4, Accuratezza.IMPRECISA);
		builder.aggiungiSuggerimento(suggerimento);

		Quesito quesito = builder.build(codiceQuesito);

		QuesitoWriter writer = new QuesitoWriter();

		File file = new File("C:/ChiVuolEssereMilionarioQuesiti/Quesiti.txt");

		writer.salvaQuesito(quesito, file);

	}

}
