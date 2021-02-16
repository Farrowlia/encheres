package org.eni.encheres.dal;


public abstract class CodesResultatDAL {
	
	// Code erreur concernant RETRAIT
	public static final int INSERT_RETRAIT_ERREUR = 10000;
	public static final int UPDATE_RETRAIT_ERREUR = 10001;
	public static final int DELETE_RETRAIT_ERREUR = 10002;
	public static final int SELECT_RETRAIT_ERREUR = 10003;
	
	// Code erreur concernant ARTICLE_VENDU
	public static final int INSERT_XXXXXX_ERREUR = 10100;
	
	// Code erreur concernant UTILISATEUR
	public static final int INSERT_XXXXXXX_ERREUR = 10200;
		
	// Code erreur concernant CATEGORIE
	public static final int INSERT_XXXXX_ERREUR = 10300;
	
	// Code erreur concernant ENCHERE
	public static final int INSERT_XXX_ERREUR = 10400;
	
}
