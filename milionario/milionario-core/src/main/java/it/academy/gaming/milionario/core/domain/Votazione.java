package it.academy.gaming.milionario.core.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

public class Votazione {

	private PercentualeRisposta[] percentualiRisposta;

	private Votazione(PercentualeRisposta[] percentualiRisposta) {
		this.percentualiRisposta = percentualiRisposta;
	}

	public static VotazioneBuilder builder() {
		return new VotazioneBuilder();
	}

	public PercentualeRisposta[] getPercentualiRisposta() {
		return percentualiRisposta;
	}

	public static class VotazioneBuilder {
		private PercentualeRisposta[] percentualiRisposta = new PercentualeRisposta[4];

		private VotazioneBuilder() {
			try {
				int i = 0;
				for (LetteraRisposta letteraRisposta : LetteraRisposta.values()) {
					percentualiRisposta[i++] = new PercentualeRisposta(letteraRisposta);
				}
			} catch (PercentualeRispostaInvalidaExcpetion ignored) {
			}
		}

		public VotazioneBuilder vota(LetteraRisposta letteraRisposta) throws PercentualeRispostaInvalidaExcpetion {
			if (letteraRisposta == null) {
				throw new IllegalArgumentException();
			}
			for (PercentualeRisposta percentualeRisposta : percentualiRisposta) {
				if (percentualeRisposta.getLetteraRisposta().equals(letteraRisposta)) {
					percentualeRisposta.incrementaPercentuale();
					break;
				}
			}
			return this;
		}

		public Votazione build() throws CreazioneVotazioneException {
			checkNumeronumeroPercentualiInvalido(percentualiRisposta);
			checkPercentualeRispostaAssente(percentualiRisposta);
			checkLettereRisposteIcongruenti(percentualiRisposta);
			checkSommaPercentualiRisposte(percentualiRisposta);

			return new Votazione(percentualiRisposta);
		}

		private void checkSommaPercentualiRisposte(PercentualeRisposta[] percentualiRisposta2)
				throws CreazioneVotazioneException {
			int sommaPercentuali = 0;
			for (PercentualeRisposta percentualeRisposta : percentualiRisposta2) {
				sommaPercentuali += percentualeRisposta.getPercentuale();
			}
			if (sommaPercentuali != 100) {
				throw CreazioneVotazioneException.sommaPercentualiRisposteIncongruenti();
			}
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

		public PercentualeRisposta[] getPercentualiRisposta() {
			return percentualiRisposta;
		}

		public void setPercentualiRisposta(PercentualeRisposta[] percentualiRisposta) {
			this.percentualiRisposta = percentualiRisposta;
		}

	}

	@Override
	public String toString() {
		return Arrays.toString(percentualiRisposta);
	}

}