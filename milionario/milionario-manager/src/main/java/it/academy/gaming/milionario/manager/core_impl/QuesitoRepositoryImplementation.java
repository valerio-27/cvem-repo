package it.academy.gaming.milionario.manager.core_impl;

import java.util.Optional;

import it.academy.gaming.milionario.core.domain.Categoria;
import it.academy.gaming.milionario.core.domain.CodiceQuesito;
import it.academy.gaming.milionario.core.domain.Difficolta;
import it.academy.gaming.milionario.core.domain.Quesito;
import it.academy.gaming.milionario.manager.core.domain.QuesitoRepository;

public class QuesitoRepositoryImplementation implements QuesitoRepository {

	@Override
	public void add(Quesito quesito) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(CodiceQuesito codiceQuesito) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Optional<Quesito> findByLivelloDifficolta(Difficolta difficolta) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Optional<Quesito> findByCategoria(Categoria categoriaRicercata) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

}
