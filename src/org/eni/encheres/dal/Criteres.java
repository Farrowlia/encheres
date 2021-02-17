package org.eni.encheres.dal;

public enum Criteres {

	PSEUDO("pseudo"), NOM("nom"), EMAIL("email"), VILLE("ville");
	
	private final String nomColonne;

	private Criteres(String nomColonne) {
		this.nomColonne = nomColonne;
	}

	public String getNomColonne() {
		return nomColonne;
	}
	
	
}
