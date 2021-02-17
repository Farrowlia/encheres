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
	public static final int REGLE_NOM_ARTICLE_ERREUR = 20100;
	public static final int REGLE_DESCRIPTION_ARTICLE_ERREUR = 20101;
	public static final int REGLE_DATE_DEBUT_ENCHERE_ARTICLE_ERREUR = 20102;
	public static final int REGLE_DATE_FIN_ENCHERE_ARTICLE_ERREUR = 20103;
	public static final int REGLE_PRIX_INITIAL_ARTICLE_ERREUR = 20104;
	

	
	// Code erreur concernant CATEGORIE
	public static final int REGLE_LIBELLE_ERREUR = 20100;
	
	// Code erreur concernant ENCHERE
	public static final int REGLE_DATE_ENCHERE_ERREUR = 20200;
	public static final int REGLE_MONTANT_ENCHERE_ERREUR = 20201;



	
}
