package it.academy.gaming.milionario.manager.core.application.view;

public class DifficoltaView {

	private int difficolta;

	public DifficoltaView(int difficolta) {
		this.difficolta = difficolta;
	}

	public int getLivelloDifficolta() {
		return difficolta;
	}

	@Override
	public String toString() {
		return difficolta + "";
	}
	

}
