package it.academy.gaming.milionario.core.views;

public class RangeConoscenzaView {
	private int minimoConoscenza;
	private int massimoConoscenza;

	public RangeConoscenzaView(int minimoConoscenza, int massimoConoscenza) {
		super();
		this.minimoConoscenza = minimoConoscenza;
		this.massimoConoscenza = massimoConoscenza;
	}

	public int getMinimoConoscenza() {
		return minimoConoscenza;
	}

	public int getMassimoConoscenza() {
		return massimoConoscenza;
	}

}
