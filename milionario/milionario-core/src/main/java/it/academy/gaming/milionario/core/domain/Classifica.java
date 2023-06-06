package it.academy.gaming.milionario.core.domain;

import java.util.Collection;

public class Classifica {

	private PartitaGiocataRepository partitaGiocataRepository;

	public Classifica(PartitaGiocataRepository partitaGiocataRepository) {
		this.partitaGiocataRepository = partitaGiocataRepository;
	}

	public void registra(PartitaGiocata partitaGiocata) {
		partitaGiocataRepository.save(partitaGiocata);
	}
	
	public Collection<PartitaGiocata> listaPartite() {
		return partitaGiocataRepository.getAll();
	}
}