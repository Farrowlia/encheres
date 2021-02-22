package org.eni.encheres.bll.validator;

import java.sql.SQLException;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.eni.encheres.authentification.InscriptionException;
import org.eni.encheres.bll.UtilisateurManager;
import org.eni.encheres.bo.Utilisateur;
import org.eni.encheres.utils.MapUtils;

public class UtilisateurValidator {

	private static UtilisateurManager um = new UtilisateurManager();

	public static Utilisateur validateUtilisateur(Utilisateur utilisateur, InscriptionException inscriptionException)
			throws SQLException {

		

		// vérif que le mail est unique +

		if (um.isEmailExists(utilisateur.getEmail())) {
			inscriptionException.setErreur(MapUtils.CHAMP_EMAIL, "Cet email est déjà utilisé");
			// TODO vérif format de l'adresse mail
			inscriptionException.setErreur(MapUtils.CHAMP_EMAIL, "xxxx");
		}

		// vérif que le pseudo est unique OU qu'il ne contient pas de caractère spécial
		if (um.isUserExistsByPseudo(utilisateur.getPseudo()) || Pattern.matches("[^\\w]", utilisateur.getPseudo())) {
			inscriptionException.setErreur(MapUtils.CHAMP_PSEUDO, "Ce pseudo est déjà pris");
		}

		// vérif que le pseudo est unique OU qu'il ne contient pas de caractère spécial
		if (Pattern.matches("[^\\w]", utilisateur.getPseudo())) {
			inscriptionException.setErreur(MapUtils.CHAMP_PSEUDO,
					"Ce pseudo ne doit contenir que des caractères alpha-numériques");
		}

		if (utilisateur.getCodePostal() != null && utilisateur.getCodePostal().trim().length() != 5
				&& !utilisateur.getCodePostal().matches("[0-9]")) {
			inscriptionException.setErreur(MapUtils.CHAMP_CP, "Le format du code postal n'est pas correct");
		}

		try {
			validationEmail(utilisateur.getEmail());
		} catch (Exception ex) {
			inscriptionException.setErreur(MapUtils.CHAMP_EMAIL, ex.getMessage());
		}

		try {
			validationMotsDePasse(utilisateur.getMotDePasse(), MapUtils.getValeurChamp(request, MapUtils.CHAMP_CONF));
		//TODO DTO
		} catch (Exception ex) {
			inscriptionException.setErreur(MapUtils.CHAMP_PWD, ex.getMessage());
			inscriptionException.setErreur(MapUtils.CHAMP_CONF, null);
		}

		try {
			validationTexte(utilisateur.getPseudo());
			// TODO vérifier caractères alphanumériques et vérifier unicité
		} catch (Exception ex) {
			inscriptionException.setErreur(MapUtils.CHAMP_PSEUDO, ex.getMessage());
		}

		try {
			validationTelephone(utilisateur.getTelephone());
		} catch (Exception ex) {
			inscriptionException.setErreur(MapUtils.CHAMP_TELEPHONE, ex.getMessage());
		}

		try {
			validationCodePostal(utilisateur.getCodePostal());
		} catch (Exception ex) {
			inscriptionException.setErreur(MapUtils.CHAMP_CP, ex.getMessage());
		}

		return utilisateur;

	}

	// Méthodes de validation des champs
	/**
	 * Valide l'adresse mail saisie.
	 */
	private static void validationEmail(String email) throws Exception {
		if (email != null) {
			if (!email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
				throw new Exception("Merci de saisir une adresse mail valide.");
			}
		} else {
			throw new Exception("Merci de saisir une adresse mail.");
		}
	}

	/**
	 * Valide les mots de passe saisis.
	 */
	private static void validationMotsDePasse(String motDePasse, String confirmation) throws Exception {

		if (motDePasse != null && confirmation != null) {
			if (!motDePasse.equals(confirmation)) {
				throw new Exception("Les mots de passe entrés sont différents, merci de les saisir à nouveau.");
			} else if (motDePasse.trim().length() < 5) {
				throw new Exception("Les mots de passe doivent contenir au moins 5 caractères.");
			} else if (motDePasse.length() > 30) {
				throw new Exception("Retapez un mot de passe de moins de 30 caractères sans caractères spéciaux");
			}
		} else {
			throw new Exception("Merci de saisir et confirmer votre mot de passe.");
		}
	}

	/**
	 * Valide le texte saisi.
	 */
	private static void validationTexte(String nomChamp) throws Exception {
		if (nomChamp != null && nomChamp.trim().length() < 3) {
			throw new Exception("Le champ " + nomChamp + " doit contenir au moins 3 caractères.");
		}
	}

	/**
	 * valide le code postal saisi
	 * 
	 * @param codePostal
	 * @throws Exception
	 */
	private static void validationCodePostal(String codePostal) throws Exception {
		if (codePostal != null && codePostal.trim().length() != 5 && !codePostal.matches("[0-9]")) {
			throw new Exception("Le format du code postal n'est pas correct");
		}
	}

	/**
	 * Valide le telephone saisi
	 * 
	 * @param telephone
	 * @throws Exception
	 */
	private static void validationTelephone(String telephone) throws Exception {
		if (telephone != null && telephone.trim().length() != 10 && !telephone.matches("[0-9]")) {
			throw new Exception("Merci de saisir un numéro à 10 chiffres");
		}
	}
}
