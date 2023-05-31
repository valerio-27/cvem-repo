package it.academy.gaming.milionario.core.domain;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

public class Votazione {

	private PercentualeRisposta[] percentualiRisposta;

	public Votazione(PercentualeRisposta[] percentualiRisposta) throws CreazioneVotazioneException {

		checkNumeronumeroPercentualiInvalido(percentualiRisposta);
		checkPercentualeRispostaAssente(percentualiRisposta);
		checkLettereRisposteIcongruenti(percentualiRisposta);

		this.percentualiRisposta = percentualiRisposta;
	}

	private void checkLettereRisposteIcongruenti(PercentualeRisposta[] percentualiRisposta)
			throws CreazioneVotazioneException {

		List<LetteraRisposta> lettereRisposta = new ArrayList<>(4);

		CollectionUtils.addAll(lettereRisposta, LetteraRisposta.values());

		for (PercentualeRisposta percentualeRisposta : percentualiRisposta) {
			lettereRisposta.remove(percentualeRisposta.getLetteraRisposta());
		}
		if (!lettereRisposta.isEmpty()) {
			throw CreazioneVotazioneException.lettereRisposteIncongruenti();
		}
	}

	private void checkPercentualeRispostaAssente(PercentualeRisposta[] percentualiRisposta)
			throws CreazioneVotazioneException {
		for (PercentualeRisposta percentualeRisposta : percentualiRisposta) {
			if (percentualeRisposta == null) {
				throw CreazioneVotazioneException.percentualeRispostaAssente();
			}
		}
	}

	private void checkNumeronumeroPercentualiInvalido(PercentualeRisposta[] percentualiRisposta)
			throws CreazioneVotazioneException {
		int numeroPercentualiRisposta = percentualiRisposta.length;
		if (numeroPercentualiRisposta != 4) {
			;
			throw CreazioneVotazioneException.numeroPercentualiInvalido(numeroPercentualiRisposta);
		}
	}
}