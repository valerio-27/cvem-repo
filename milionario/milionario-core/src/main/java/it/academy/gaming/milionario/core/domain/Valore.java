package it.academy.gaming.milionario.core.domain;

public class Valore {

	private int euro;

	private Valore(int euro) {
		this.euro = euro;
	}

	void ricalcolaPerCheckpoints() {
		if (euro < 3000) {
			euro = 0;
		} else if (euro < 20000) {
			euro = 3000;
		} else if (euro < 1000000) {
			euro = 20000;
		} else {
			euro = 1000000;
		}
	}

	public static Valore parse(int euro) {
		return new Valore(euro);
	}

	public static Valore calcola(Difficolta difficolta) {
		int euro = 0;
		switch (difficolta.getDifficolta()) {
		case 1:
			euro = 500;
			break;
		case 2:
			euro = 1000;
			break;
		case 3:
			euro = 1500;
			break;
		case 4:
			euro = 2000;
			break;
		case 5:
			euro = 3000;
			break;
		case 6:
			euro = 5000;
			break;
		case 7:
			euro = 7000;
			break;
		case 8:
			euro = 10000;
			break;
		case 9:
			euro = 15000;
			break;
		case 10:
			euro = 20000;
			break;
		case 11:
			euro = 30000;
			break;
		case 12:
			euro = 70000;
			break;
		case 13:
			euro = 150000;
			break;
		case 14:
			euro = 300000;
			break;
		case 15:
			euro = 1000000;
			break;
		}
		return new Valore(euro);
	}

	public int getEuro() {
		return euro;
	}
}