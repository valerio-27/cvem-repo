package it.academy.gaming.milionario.manager.core.application.view;

public class RangeCulturaGeneraleView {
	private int limiteMinimoConoscenza;
	private int limiteMassimoConoscenza;

	public RangeCulturaGeneraleView(int minimoConoscenza, int massimoConoscenza) {
		super();
		this.limiteMinimoConoscenza = minimoConoscenza;
		this.limiteMassimoConoscenza = massimoConoscenza;
	}

	public int getMinimoConoscenza() {
		return limiteMinimoConoscenza;
	}

	public int getMassimoConoscenza() {
		return limiteMassimoConoscenza;
	}

}
