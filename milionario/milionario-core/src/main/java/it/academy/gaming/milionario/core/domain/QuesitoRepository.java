package it.academy.gaming.milionario.core.domain;

public interface QuesitoRepository {

	Quesito findRandomByDifficolta(Difficolta difficolta);

}