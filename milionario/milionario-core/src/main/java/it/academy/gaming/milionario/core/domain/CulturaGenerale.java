package it.academy.gaming.milionario.core.domain;

import java.util.HashMap;
import java.util.Map;

public class CulturaGenerale {

	private Map<Categoria, ConoscenzaCategoria> conoscenze;

	private CulturaGenerale(Map<Categoria, ConoscenzaCategoria> conoscenze) {
		this.conoscenze = conoscenze;
	}

	public static CulturaGenerale genera(RangeCulturaGenerale rangeCulturaGenerale) {
		Map<Categoria, ConoscenzaCategoria> conoscenze = new HashMap<Categoria, ConoscenzaCategoria>();
		for (Categoria categoria : Categoria.values()) {
			conoscenze.put(categoria, ConoscenzaCategoria.genera(categoria,rangeCulturaGenerale));
		}
		return new CulturaGenerale(conoscenze);
	}

	public int getConoscenzaPerCategoria(Categoria categoria) {
		return conoscenze.get(categoria).getConoscenza();
	}
}