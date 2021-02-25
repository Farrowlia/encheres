package org.eni.encheres.utils;

import javax.servlet.http.HttpServletRequest;

import org.eni.encheres.bo.Utilisateur;

public class MapUtils {

	public static final String CHAMP_PSEUDO = "pseudo";
	public static final String CHAMP_NOM = "nom";
	public static final String CHAMP_PRENOM = "prenom";
	public static final String CHAMP_EMAIL = "email";
	public static final String CHAMP_TELEPHONE = "telephone";
	public static final String CHAMP_RUE = "rue";
	public static final String CHAMP_CP = "codePostal";
	public static final String CHAMP_VILLE = "ville";
	public static final String CHAMP_PWD = "motdepasse";
	public static final String CHAMP_CONF = "confirmation";
	/* pour la connexion par email ou pseudo : */
	public static final String CHAMP_LOGIN= "login";

	public static Utilisateur mapUtilisateur(HttpServletRequest request) {
		/* Récupération des champs du formulaire. */
		Utilisateur user = new Utilisateur();

		user.setEmail(getValeurChamp(request, CHAMP_EMAIL));
		user.setMotDePasse(getValeurChamp(request, CHAMP_PWD));
		user.setPseudo(getValeurChamp(request, CHAMP_PSEUDO));
		user.setCodePostal(getValeurChamp(request, CHAMP_CP));
		user.setNom(getValeurChamp(request, CHAMP_NOM));
		user.setPrenom(getValeurChamp(request, CHAMP_PRENOM));
		user.setRue(getValeurChamp(request, CHAMP_RUE));
		user.setVille(getValeurChamp(request, CHAMP_VILLE));
		user.setTelephone(getValeurChamp(request, CHAMP_TELEPHONE));
		
		return user;
	}

	public static String getValeurChamp(HttpServletRequest request, String champ) {
		String valeur = request.getParameter(champ);
		if (valeur == null || valeur.trim().length() == 0) {
			return null;
		}
		return valeur.trim();
	}

}
