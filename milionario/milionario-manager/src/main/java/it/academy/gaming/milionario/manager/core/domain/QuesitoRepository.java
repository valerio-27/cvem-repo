package it.academy.gaming.milionario.manager.core.domain;

import java.util.Optional;

import it.academy.gaming.milionario.core.domain.Categoria;
import it.academy.gaming.milionario.core.domain.CodiceQuesito;
import it.academy.gaming.milionario.core.domain.Difficolta;
import it.academy.gaming.milionario.core.domain.Quesito;

public interface QuesitoRepository {

	void add(Quesito quesito);

	void remove(CodiceQuesito codiceQuesito);

	Optional<Quesito> findByLivelloDifficolta(Difficolta difficolta);

	Optional<Quesito> findByCategoria(Categoria categoriaRicercata);

}
