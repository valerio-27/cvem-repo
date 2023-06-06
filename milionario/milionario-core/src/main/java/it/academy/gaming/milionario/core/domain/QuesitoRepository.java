package it.academy.gaming.milionario.core.domain;

public interface QuesitoRepository {

	Quesito findRandomByCategoriaAndDifficolta(Categoria random, Difficolta difficolta);

}