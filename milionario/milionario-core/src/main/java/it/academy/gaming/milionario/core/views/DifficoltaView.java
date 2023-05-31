package it.academy.gaming.milionario.core.views;

public class DifficoltaView {
	private int livelloDifficolta;

	public DifficoltaView(int livelloDifficolta) {
		super();
		this.livelloDifficolta = livelloDifficolta;
	}

	public int getLivelloDifficolta() {
		return livelloDifficolta;
	}

	@Override
	public String toString() {
		return "LivelloDifficolta= " + livelloDifficolta ;
	}
	

}
