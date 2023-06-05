package it.academy.gaming.milionario.core.domain;

public interface QuesitoRepository {

	Quesito findByCategoriaAndDifficolta(Categoria random, Difficolta difficolta);

}
