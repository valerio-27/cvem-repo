package it.academy.gaming.milionario.core.views;

public class PercentualeFortunaView {
	private int minimoPercentuale;
	private int massimoPercentuale;

	public PercentualeFortunaView(int minimoPercentuale, int massimoPercentuale) {
		super();
		this.minimoPercentuale = minimoPercentuale;
		this.massimoPercentuale = massimoPercentuale;
	}

	public int getMinimoPercentuale() {
		return minimoPercentuale;
	}

	public int getMassimoPercentuale() {
		return massimoPercentuale;
	}

}
