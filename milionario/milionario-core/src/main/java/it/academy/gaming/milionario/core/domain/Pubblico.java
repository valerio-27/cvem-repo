package it.academy.gaming.milionario.core.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import it.academy.gaming.milionario.core.domain.Votazione.VotazioneBuilder;
import it.academy.gaming.milionario.core.domain.exceptions.CreazioneVotazioneException;
import it.academy.gaming.milionario.core.domain.exceptions.PercentualeRispostaInvalidaExcpetion;

public class Pubblico {

	private static Random random = new Random();
	private Collection<PersonaPubblico> persone;
	private PercentualeFortuna percentualeFortuna;

	private Pubblico(Collection<PersonaPubblico> persone, PercentualeFortuna percentualeFortuna) {
		this.persone = persone;
		this.percentualeFortuna = percentualeFortuna;
	}

	private static Collection<PersonaPubblico> creaPersonePubblico(RangeCulturaGenerale range) {
		Collection<PersonaPubblico> personePubblico = new ArrayList<>();
		for (int i = 0; i < 100; i++) {
			personePubblico.add(PersonaPubblico.genera(range));
		}
		return personePubblico;
	}

	public static Pubblico crea(RangeCulturaGenerale range, PercentualeFortuna percentualeFortuna) {
		return new Pubblico(creaPersonePubblico(range), percentualeFortuna);
	}

	/**
	 * La persona parte con un livello di conoscenza per la categoria del quesito,
	 * ogni quesito in base alla propria difficolta aggiunge/diminuisce il livello
	 * di conoscenza, ora se il quesito ha 2 sole risposte aggiungo un 30%. se la
	 * percentuale risultante è negativa questa viene sostiuita dalla percentuale
	 * fortuna.
	 * 
	 * @param quesito
	 * @param range
	 * @param percentualeFortuna
	 * @return
	 */

	public Votazione vota(Quesito quesito) {
		VotazioneBuilder builder = Votazione.builder();

		Collection<Risposta> risposteDisponibili = quesito.getRisposteDisponibili();

		LetteraRisposta letteraRispostaCorretta = trovaLetteraRispostaCorretta(risposteDisponibili);

		for (PersonaPubblico persona : this.persone) {

			Categoria categoria = quesito.getDomanda().getCategoria();

			int conoscenza = persona.getCulturaGenerale().getConoscenzaPerCategoria(categoria);

			conoscenza += SuccessoDomanda.calcola(quesito);

			if (risposteDisponibili.size() == 2) {
				conoscenza += 30;
			}

			// se la percentuale è negativa questa viene sostiuita dalla percentuale
			// fortuna, sennò rimane quella
			if (conoscenza < 0) {
				conoscenza = percentualeFortuna.getPercentualeFortuna();
			}

			int numero = random.nextInt(100) + 1;

			if (numero <= conoscenza) {
				try {
					builder.vota(letteraRispostaCorretta);
				} catch (PercentualeRispostaInvalidaExcpetion ignored) {
				}
			} else {
				try {
					builder.vota(daiLetteraRispostaSbagliata(risposteDisponibili));
				} catch (PercentualeRispostaInvalidaExcpetion ignored) {
				}
			}
		}
		Votazione votazione = null;
		try {
			votazione = builder.build();
		} catch (CreazioneVotazioneException ignored) {
		}
		return votazione;
	}

	private static LetteraRisposta daiLetteraRispostaSbagliata(Collection<Risposta> risposteDisponibili) {
		List<LetteraRisposta> lettereRispostaErrate = new ArrayList<>();

		for (Risposta risposta : risposteDisponibili) {
			if (!risposta.isCorretta()) {
				lettereRispostaErrate.add(risposta.getLettera());
			}
		}
		return lettereRispostaErrate.get(random.nextInt(lettereRispostaErrate.size()));
	}

	private static LetteraRisposta trovaLetteraRispostaCorretta(Collection<Risposta> risposteDisponibili) {
		LetteraRisposta letteraRisposta = null;
		for (Risposta risposta : risposteDisponibili) {
			if (risposta.isCorretta()) {
				letteraRisposta = risposta.getLettera();
			}
		}
		return letteraRisposta;
	}

}
