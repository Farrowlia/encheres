package org.eni.encheres.bo;

/**
 * 
 * @author aleroy2020
 *
 */
public class Categorie {
	
	private int noCategorie;
	private String libelle;
	
	
	public Categorie() {
	}
	
	public Categorie(int noCategorie) {
		this.noCategorie = noCategorie;
	}

	public Categorie(int noCategorie, String libelle) {
		this.noCategorie = noCategorie;
		this.libelle = libelle;
	}
	
	public int getNoCategorie() {
		return noCategorie;
	}
	public void setNoCategorie(int noCategorie) {
		this.noCategorie = noCategorie;
	}


	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

}
