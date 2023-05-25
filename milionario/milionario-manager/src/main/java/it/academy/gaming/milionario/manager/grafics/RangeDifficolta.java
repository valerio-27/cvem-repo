package it.academy.gaming.milionario.manager.grafics;

import it.academy.gaming.milionario.manager.grafics.controller.CvemController;
import it.academy.gaming.milionario.manager.grafics.exceptions.DifficoltaFuoriLimitiException;

public class RangeDifficolta {

	public static void verificaLimitiDifficolta(CvemController controller, int numeroInserito)
			throws DifficoltaFuoriLimitiException {
		int minimo = controller.getMinimoDiDifficolta();
		int massimo = controller.getMinimoDiDifficolta();
		if (numeroInserito < minimo || numeroInserito > massimo) {
			throw new DifficoltaFuoriLimitiException("La difficolta deve rientrare nei limite comunicati");
		}
	}

}
