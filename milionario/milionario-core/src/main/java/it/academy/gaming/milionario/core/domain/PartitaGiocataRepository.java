package it.academy.gaming.milionario.core.domain;

import java.util.Collection;

public interface PartitaGiocataRepository {

	void save(PartitaGiocata partitaGiocata);
	
	Collection<PartitaGiocata> getAll();
	
}