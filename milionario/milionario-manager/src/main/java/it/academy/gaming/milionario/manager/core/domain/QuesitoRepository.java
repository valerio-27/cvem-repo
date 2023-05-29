package it.academy.gaming.milionario.manager.core.domain;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import it.academy.gaming.milionario.core.domain.Categoria;
import it.academy.gaming.milionario.core.domain.CodiceQuesito;
import it.academy.gaming.milionario.core.domain.Difficolta;
import it.academy.gaming.milionario.core.domain.Domanda;
import it.academy.gaming.milionario.core.domain.Quesito;
import it.academy.gaming.milionario.core.domain.Risposta;

public interface QuesitoRepository {

	void add(Quesito quesito);

	void remove(CodiceQuesito codiceQuesito);

	Collection<Quesito> findByLivelloDifficolta(Difficolta difficolta);

	Collection<Quesito> findByCategoria(Categoria categoriaRicercata);

	Optional<Quesito> findByCodice(String codiceQuesitoRicercato);

	void setDifficolta(String codiceQuesito, Difficolta difficolta);

	void setRisposte(String codiceQuesito, List<Risposta> nuoveRisposte);

	void setDomanda(String codiceQuesito, Domanda domanda);
		


}
