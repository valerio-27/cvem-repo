package it.academy.gaming.milionario.core.application.views;

import java.util.Arrays;

import it.academy.gaming.milionario.core.domain.PercentualeRisposta;
import it.academy.gaming.milionario.core.domain.Votazione;

public class VotazioneView {

	PercentualeRispostaView[] percentualiRispostaView= new PercentualeRispostaView[4];

	public VotazioneView(Votazione votazione) {
		int i = 0;
		for (PercentualeRisposta percentualeRisposta : votazione.getPercentualiRisposta()) {
			percentualiRispostaView[i++] = new PercentualeRispostaView(percentualeRisposta);
		}
	}

	public PercentualeRispostaView[] getPercentualiRispostaView() {
		return percentualiRispostaView;
	}

	@Override
	public String toString() {
		return "VotazioneView [percentualiRispostaView=" + Arrays.toString(percentualiRispostaView) + "]";
	}

}