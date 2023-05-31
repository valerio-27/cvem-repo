package it.academy.gaming.milionario.core.domain;

import it.academy.gaming.milionario.core.domain.exceptions.CulturaGeneraleNonInRangeException;

public interface OpzioniPersonaRepository {

	/**
	 * int=min conoscenza int=max conoscenza int=percentuale fortuna
	 * @throws CulturaGeneraleNonInRangeException 
	 */

	public RangeCulturaGenerale getRangeCulturaGenerale() ;

	public PercentualeFortuna getPercentualeFortuna() ;

}
