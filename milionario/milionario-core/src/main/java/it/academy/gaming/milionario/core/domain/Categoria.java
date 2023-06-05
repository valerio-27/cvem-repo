package it.academy.gaming.milionario.core.domain;

import java.util.Random;

public enum Categoria {
	SPETTACOLO, ARTE, SPORT, GEOGRAFIA, SCIENZA, STORIA;

	private static Random random = new Random();
	
     public static Categoria getRandom() {
    	 Categoria[] categorie = Categoria.values();
    	 return categorie[random.nextInt(categorie.length)];
     }
}