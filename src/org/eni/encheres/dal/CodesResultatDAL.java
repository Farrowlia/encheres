package org.eni.encheres.dal;


public abstract class CodesResultatDAL {
	
	// Code erreur concernant RETRAIT
	public static final int INSERT_RETRAIT_ERREUR = 10000;
	public static final int UPDATE_RETRAIT_ERREUR = 10001;
	public static final int DELETE_RETRAIT_ERREUR = 10002;
	public static final int SELECT_RETRAIT_ERREUR = 10003;

	//Codes erreur concernant UTILISATEUR
	public static final int INSERT_UTILISATEUR_ERREUR = 10020;
	public static final int SELECT_UTILISATEUR_ERREUR = 10021;
	public static final int DELETE_UTILISATEUR_ERREUR = 10022;
	public static final int UPDATE_UTILISATEUR_ERREUR = 10023;
	public static final int UPDATE_PASSWORD_ERREUR = 10024;
	

	// Code erreur concernant ARTICLE_VENDU
	public static final int INSERT_ARTICLE_VENDU_ERREUR = 90100;
	public static final int UPDATE_ARTICLE_VENDU_ERREUR = 90101;
	public static final int DELETE_ARTICLE_VENDU_ERREUR = 90102;
	public static final int SELECT_ARTICLE_VENDU_ERREUR = 90103;
	
	// Code erreur concernant CATEGORIE
	public static final int INSERT_CATEGORIE_ERREUR = 10100;
	public static final int UPDATE_CATEGORIE_ERREUR = 10101;
	public static final int DELETE_CATEGORIE_ERREUR = 10102;
	public static final int SELECT_CATEGORIE_ERREUR = 10103;
	
	// Code erreur concernant ENCHERE
	public static final int INSERT_ENCHERE_ERREUR = 10200;
	public static final int SELECT_ENCHERE_ERREUR = 10201;
	

	

	
	//Code erreur insertion d'un objet null
	public static final int INSERT_OBJET_NULL = 10200;
	public static final int ERREUR_PASSWORD_NULL = 10201;
	
}
