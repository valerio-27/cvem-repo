package it.academy.gaming.milionario.core.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import org.apache.commons.collections.CollectionUtils;

import it.academy.gaming.milionario.core.domain.Votazione.VotazioneBuilder;

public class Pubblico {

	private static Random random = new Random();
	private Collection<PersonaPubblico> persone;
	private PercentualeFortuna percentualeFortuna;

	private Pubblico(Collection<PersonaPubblico> persone) {
		this.persone = persone;
	}

	private static Collection<PersonaPubblico> creaPersonePubblico(RangeCulturaGenerale range) {
		Collection<PersonaPubblico> personePubblico = new ArrayList<>();
		for (int i = 0; i < 100; i++) {
			personePubblico.add(PersonaPubblico.genera(range));
		}
		return personePubblico;
	}

	public Votazione vota(Quesito quesito) {

		return null;
	}

	/**
	 * La persona parte con un livello di conoscenza per la categoria del quesito,
	 * ogni quesito in base alla propria difficolta aggiunge/diminuisce il livello
	 * di conoscenza, ora solo se il quesito ha 2 sole risposte aggiungo un 30%. Ora
	 * se la percentuale è negativa questa viene sostiuita dalla percentuale
	 * fortuna, sennò rimane quella
	 * 
	 * @param quesito
	 * @param range
	 * @param percentualeFortuna
	 * @return
	 */
	public static Votazione vota(Quesito quesito, RangeCulturaGenerale range, PercentualeFortuna percentualeFortuna) {

		VotazioneBuilder builder = Votazione.builder();

		Collection<Risposta> risposteDisponibili = quesito.getRisposteDisponibili();

		LetteraRisposta letteraRispostaCorretta = trovaLetteraRispostaCorretta(risposteDisponibili);

		for (int i = 0; i < 100; i++) {
			PersonaPubblico persona = PersonaPubblico.genera(range);

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

//va da 1 a 100
			int numero = random.nextInt(100) + 1;

			if (numero <= conoscenza) {
				for (Risposta risposta : risposteDisponibili) {
					if (risposta.isCorretta()) {
						try {
							builder.vota(risposta.getLettera());
						} catch (PercentualeRispostaInvalidaExcpetion ignored) {
						}
					}
				}
			} else {
				Collection<Risposta> risposteErrate = new ArrayList<>();
				for (Risposta risposta : risposteErrate) {
					if (!risposta.isCorretta()) {
						risposteErrate.add(risposta);
					}
				}

			}
		}
		return null;
	}

	private static LetteraRisposta daiLetteraRispostaSbagliata(Collection<Risposta> risposteDisponibili) {
		List<LetteraRisposta> lettereRisposta = new ArrayList<>();
		CollectionUtils.addAll(lettereRisposta, LetteraRisposta.values());

		lettereRisposta.remove(trovaLetteraRispostaCorretta(risposteDisponibili));

		return lettereRisposta.get(random.nextInt(lettereRisposta.size()));
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
