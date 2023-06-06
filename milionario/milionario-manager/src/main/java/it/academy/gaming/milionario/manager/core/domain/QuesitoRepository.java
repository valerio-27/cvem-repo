package it.academy.gaming.milionario.manager.core.domain;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import it.academy.gaming.milionario.core.domain.Categoria;
import it.academy.gaming.milionario.core.domain.Difficolta;
import it.academy.gaming.milionario.core.domain.Domanda;
import it.academy.gaming.milionario.core.domain.Quesito;
import it.academy.gaming.milionario.core.domain.Risposta;
import it.academy.gaming.milionario.core.domain.codici.CodiceQuesito;
import it.academy.gaming.milionario.core.domain.Suggerimento;

public interface QuesitoRepository {

	void add(Quesito quesito);

	void remove(CodiceQuesito codiceQuesito);

	Collection<Quesito> findByLivelloDifficolta(Difficolta difficolta);

	Collection<Quesito> findByCategoria(Categoria categoriaRicercata);

	Optional<Quesito> findByCodice(CodiceQuesito codiceQuesito);

	void setDifficolta(CodiceQuesito codiceQuesito, Difficolta difficolta);

	void setRisposte(CodiceQuesito codiceQuesito, List<Risposta> nuoveRisposte);

	void setDomanda(CodiceQuesito codiceQuesito, Domanda domanda);

	void setSuggerimenti(CodiceQuesito codice, List<Suggerimento> nuoviSuggerimenti);
		


}
