package it.academy.gaming.milionario.manager.core.domain;

import it.academy.gaming.milionario.core.domain.PercentualeFortuna;
import it.academy.gaming.milionario.core.domain.RangeCulturaGenerale;

public interface OpzioniPersonaRepository {

	/**
	 * int=min conoscenza int=max conoscenza int=percentuale fortuna
	 */

	public void setRangeCulturaGenerale(RangeCulturaGenerale range);

	public void setPercentualeFortuna(PercentualeFortuna percentualeFortuna);

}
