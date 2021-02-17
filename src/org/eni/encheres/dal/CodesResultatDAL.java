package org.eni.encheres.dal;


public abstract class CodesResultatDAL {
	
	// Code erreur concernant RETRAIT
	public static final int INSERT_RETRAIT_ERREUR = 10000;
	public static final int UPDATE_RETRAIT_ERREUR = 10001;
	public static final int DELETE_RETRAIT_ERREUR = 10002;
	public static final int SELECT_RETRAIT_ERREUR = 10003;
	
	// Code erreur concernant CATEGORIE
	public static final int INSERT_CATEGORIE_ERREUR = 10100;
	public static final int UPDATE_CATEGORIE_ERREUR = 10101;
	public static final int DELETE_CATEGORIE_ERREUR = 10102;
	public static final int SELECT_CATEGORIE_ERREUR = 10103;
	
	// Code erreur concernant ENCHERE
	public static final int INSERT_ENCHERE_ERREUR = 10200;
	public static final int SELECT_ENCHERE_ERREUR = 10201;
	
	// Code erreur concernant ?
	public static final int INSERT_XXXXX_ERREUR = 10300;
	
	
}
