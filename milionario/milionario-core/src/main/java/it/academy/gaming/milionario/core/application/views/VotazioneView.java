package it.academy.gaming.milionario.core.application.views;

import it.academy.gaming.milionario.core.domain.PercentualeRisposta;
import it.academy.gaming.milionario.core.domain.Votazione;

public class VotazioneView {

	PercentualeRispostaView[] percentualiRispostaView;

	public VotazioneView(Votazione votazione) {
		int i = 0;
		for (PercentualeRisposta percentualeRisposta : votazione.getPercentualiRisposta()) {
			percentualiRispostaView[i++] = new PercentualeRispostaView(percentualeRisposta);
		}
	}

	public PercentualeRispostaView[] getPercentualiRispostaView() {
		return percentualiRispostaView;
	}

}
