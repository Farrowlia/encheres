package org.eni.encheres.bll;

public abstract class CodesResultatBLL {
	
	// Code erreur concernant RETRAIT
	public static final int REGLE_RUE_RETRAIT_ERREUR = 20001;
	public static final int REGLE_CODEPOSTAL_RETRAIT_ERREUR = 20002;
	public static final int REGLE_VILLE_RETRAIT_ERREUR = 20003;
	
	// Code erreur concernant CATEGORIE
	public static final int REGLE_LIBELLE_ERREUR = 20100;
	
	// Code erreur concernant ENCHERE
	public static final int REGLE_DATE_ENCHERE_ERREUR = 20200;
	public static final int REGLE_MONTANT_ENCHERE_ERREUR = 20201;
	
	// Code erreur concernant ?
	public static final int REGLE_XXXXXX_ERREUR = 20300;
	
}
