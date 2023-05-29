package it.academy.gaming.milionario.manager.grafics.screens;

import it.academy.gaming.milionario.core.domain.Categoria;
import it.academy.gaming.milionario.manager.grafics.controller.CvemController;
import it.academy.gaming.milionario.manager.grafics.exceptions.DifficoltaFuoriLimitiException;
import it.academy.gaming.milionario.manager.grafics.requests.RicercaQuesitoPerCategoriaRequest;
import it.academy.gaming.milionario.manager.grafics.requests.RicercaQuesitoPerDifficoltaRequest;

public class RicercaQuesitoScreen extends Screen {
	private static final String OPZIONE_RICERCA_DIFFICOLTA = "D";
	private static final String OPZIONE_RICERCA_CATEGORIA = "C";
	private static final String OPZIONE_MENU = "M";

	public RicercaQuesitoScreen(CvemController controller) {
		super("RICERCA QUESITO", controller);
	}

	public void show() {
		super.showTitle();
		/*
		 * posso fare la ricerca per difficolta o per categoria
		 */
		mostraInfo("Ricerca quesiti per: ");
		mostraInfo("D)ifficolta'");
		mostraInfo("C)ategoria");
		mostraInfo("M)enu");

		String scelta = scanner.next();
		scanner.nextLine();
		try {
			switch (scelta.toUpperCase()) {
			case OPZIONE_RICERCA_CATEGORIA:
				Categoria categoriaScelta = acquisisciCategoria();
				RicercaQuesitoPerCategoriaRequest requestPerCategoria = new RicercaQuesitoPerCategoriaRequest(
						categoriaScelta);
				controller.cercaPerCategoria(requestPerCategoria);

			case OPZIONE_RICERCA_DIFFICOLTA:
				int difficoltaScelta = acquisisciDifficolta(controller.getMinimoDiDifficolta(),
						controller.getMassimoDiDifficolta());
				RicercaQuesitoPerDifficoltaRequest requestPerDifficolta = new RicercaQuesitoPerDifficoltaRequest(
						difficoltaScelta);
				controller.cercaPerDifficolta(requestPerDifficolta);
				break;
			case OPZIONE_MENU:
				controller.showMenuScreen();
			default:
				throw new IllegalArgumentException("Opzione insistete");
			}
		} catch (Exception e) {
			mostraInfo(e.getMessage());
			show();
		}

	}

	private Categoria acquisisciCategoria() {
		mostraInfo("Inserisci la categoria che vuoi cercare");
		String categoriaSceltaString = scanner.next();
		scanner.nextLine();
		Categoria categoriaScelta = null;
		try {
			categoriaScelta = Categoria.valueOf(categoriaSceltaString.toUpperCase());
		} catch (Exception e) {
			mostraInfo("Categoria inesistente");
			return acquisisciCategoria();
		}

		return categoriaScelta;
	}

	private int acquisisciDifficolta(int minimoDiDifficolta, int massimoDiDifficlta)
			throws DifficoltaFuoriLimitiException {
		mostraInfo("Puoi scegliere un livello di difficolta' da " + minimoDiDifficolta + " a " + massimoDiDifficlta);
		int difficoltaScelta = scanner.nextInt();
		scanner.nextLine();

		if (difficoltaScelta < massimoDiDifficlta || difficoltaScelta > massimoDiDifficlta) {
			throw new DifficoltaFuoriLimitiException("La difficolta deve rientrare nei limite comunicati");
		}
		return difficoltaScelta;
	}

}
