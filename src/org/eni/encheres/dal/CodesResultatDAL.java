package org.eni.encheres.dal;


public abstract class CodesResultatDAL {
	
	// Code erreur concernant RETRAIT
	public static final int INSERT_RETRAIT_ERREUR = 10000;
	public static final int UPDATE_RETRAIT_ERREUR = 10001;
	public static final int DELETE_RETRAIT_ERREUR = 10002;
	public static final int SELECT_RETRAIT_ERREUR = 10003;

	//Codes erreur concernant UTILISATEUR
	public static final int INSERT_UTILISATEUR_ERREUR = 10020;
	
	// Code erreur concernant ?
	public static final int INSERT_XXXXX_ERREUR = 10100;
	
	//Code erreur insertion d'un objet null
	public static final int INSERT_OBJET_NULL = 10200;
	
}
