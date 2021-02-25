package org.eni.encheres.bll;

public abstract class CodesResultatBLL {
	
	// Code erreur concernant RETRAIT
	public static final int REGLE_RUE_RETRAIT_ERREUR = 20000;
	public static final int REGLE_CODEPOSTAL_RETRAIT_ERREUR = 20001;
	public static final int REGLE_VILLE_RETRAIT_ERREUR = 20002;
	

	// Code erreur concernant UTILISATEUR
	public static final int REGLE_EMAIL_ERREUR = 20100;
	public static final int REGLE_PSEUDO_ERREUR = 20101;
	public static final int REGLE_PASSWORD_ERREUR = 20102;


	// Code erreur concernant ARTICLE_VENDU
	public static final int REGLE_NOM_ARTICLE_ERREUR = 20200;
	public static final int REGLE_DESCRIPTION_ARTICLE_ERREUR = 20201;
	public static final int REGLE_DATE_DEBUT_ENCHERE_ARTICLE_ERREUR = 20202;
	public static final int REGLE_DATE_FIN_ENCHERE_ARTICLE_ERREUR = 20203;
	public static final int REGLE_PRIX_INITIAL_ARTICLE_ERREUR = 20204;
	public static final int REGLE_PRIX_VENTE_ARTICLE_ERREUR = 20205;

	
	// Code erreur concernant CATEGORIE
	public static final int REGLE_LIBELLE_CATEGORIE_ERREUR = 20300;
	
	
	// Code erreur concernant ENCHERE
	public static final int REGLE_MONTANT_ENCHERE_ERREUR = 20400;
	
	
	// Code erreur concernant IMAGE
	public static final int REGLE_URL_IMAGE_ERREUR = 20500;
	
}
