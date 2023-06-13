package it.academy.gaming.milionario.core.domain;

import java.util.Collection;
import java.util.Random;

import it.academy.gaming.milionario.core.domain.exceptions.AiutoNonDisponibileException;

public class AiutoCasa {

	private static Random random = new Random();

	private RangeCulturaGenerale range;

	private boolean disponibile = true;
	private int percentualeAggiuntaCasa = 20;

	public AiutoCasa(RangeCulturaGenerale range) {
		this.range = range;
	}

	public Suggerimento vota(Quesito quesito, Giocatore giocatore) throws AiutoNonDisponibileException {
		if (!disponibile) {
			throw AiutoNonDisponibileException.aiutoCasa();
		}

		disponibile = false;

		Persona persona = PersonaDaCasa.genera(range);

		Categoria categoria = quesito.getDomanda().getCategoria();

		int conoscenza = persona.getCulturaGenerale().getConoscenzaPerCategoria(categoria);

		conoscenza += SuccessoDomanda.calcola(quesito);
		if (quesito.getRisposteDisponibili().size() == 2) {
			conoscenza += 30;
		}

		conoscenza += percentualeAggiuntaCasa;

		Suggerimento suggerimento = null;

		// se la conoscenza è negativa ritorno il suggerimento accuratezza astenuta
		if (conoscenza < 0) {
			suggerimento = quesito.getSuggerimentoDaCasaRandom(Accuratezza.ASTENUTA);
		} else {
			ProbabilitaSuggerimenti probabilitaSuggerimenti = ProbabilitaSuggerimenti.generaProbabilita(conoscenza);
			int numeroRandom = random.nextInt(100) + 1;

			if (numeroRandom < probabilitaSuggerimenti.getProbabilitaSbagliata()) {
				suggerimento = quesito.getSuggerimentoDaCasaRandom(Accuratezza.SBAGLIATA);
			} else if (numeroRandom < probabilitaSuggerimenti.getProbabilitaImprecisa()) {
				suggerimento = quesito.getSuggerimentoDaCasaRandom(Accuratezza.IMPRECISA);
			} else {
				suggerimento = quesito.getSuggerimentoDaCasaRandom(Accuratezza.CORRETTA);
			}

		}
		suggerimento.valorizzaBookmarks(giocatore, quesito);
		return suggerimento;
	}

	public void ripristina() {
		this.disponibile = true;
	}

	public boolean isDisponibile() {
		return disponibile;
	}

}