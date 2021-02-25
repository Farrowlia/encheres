package org.eni.encheres.bll.validator;

import java.sql.SQLException;
import java.util.regex.Pattern;

import org.eni.encheres.authentification.InscriptionException;
import org.eni.encheres.bll.UtilisateurManager;
import org.eni.encheres.bo.Utilisateur;
import org.eni.encheres.dto.ModifProfilDTO;
import org.eni.encheres.utils.MapUtils;

import com.microsoft.sqlserver.jdbc.StringUtils;

public class UtilisateurValidator {

	private static UtilisateurManager um = new UtilisateurManager();

	
	public static Utilisateur validateUtilisateur(Utilisateur utilisateur, InscriptionException inscriptionException)
			throws SQLException {

		

		// vérif que le mail est unique +
		if (um.isEmailExists(utilisateur.getEmail())) {
			inscriptionException.setErreur(MapUtils.CHAMP_EMAIL, "Cet email est déjà utilisé");
			inscriptionException.setErreur(MapUtils.CHAMP_EMAIL, "xxxx");
		}

		// vérif que le pseudo est unique 
		if (um.isUserExistsByPseudo(utilisateur.getPseudo())) {
			inscriptionException.setErreur(MapUtils.CHAMP_PSEUDO, "Ce pseudo est déjà pris");
		}

		try {
			validationMotsDePasse(utilisateur.getMotDePasse());
		} catch (Exception ex) {
			inscriptionException.setErreur(MapUtils.CHAMP_PWD, ex.getMessage());
		}
		
		try {
			validationEmail(utilisateur.getEmail());
		} catch (Exception ex) {
			inscriptionException.setErreur(MapUtils.CHAMP_EMAIL, ex.getMessage());
		}

		try {
			validationPseudo(utilisateur.getPseudo());
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
	
	public static ModifProfilDTO validateModifProfilDTO(ModifProfilDTO modifProfilDTO,
			InscriptionException inscriptionException) {
		// TODO si l'email est modifié, vérifier l'unicité hors ancien email

		/*Ces méthodes sont à adapter, car elles retourneraients une erreur
		if (um.isEmailExists(modifProfilDTO.getEmail())) {
			inscriptionException.setErreur(MapUtils.CHAMP_EMAIL, "Cet email est déjà utilisé");
			inscriptionException.setErreur(MapUtils.CHAMP_EMAIL, "xxxx");
		}

		// vérif que le pseudo est unique 
		if (um.isUserExistsByPseudo(modifProfilDTO.getPseudo())) {
			inscriptionException.setErreur(MapUtils.CHAMP_PSEUDO, "Ce pseudo est déjà pris");
		}
		*/

		if(modifProfilDTO.isUpdatePwd()) {
			try {
				validationMotsDePasse(modifProfilDTO.getMotDePasse());
			} catch (Exception ex) {
				inscriptionException.setErreur(MapUtils.CHAMP_PWD, ex.getMessage());
			}
		}
		
		try {
			validationEmail(modifProfilDTO.getEmail());
		} catch (Exception ex) {
			inscriptionException.setErreur(MapUtils.CHAMP_EMAIL, ex.getMessage());
		}

		try {
			validationPseudo(modifProfilDTO.getPseudo());
			// TODO vérifier caractères alphanumériques
		} catch (Exception ex) {
			inscriptionException.setErreur(MapUtils.CHAMP_PSEUDO, ex.getMessage());
		}

		try {
			validationTelephone(modifProfilDTO.getTelephone());
		} catch (Exception ex) {
			inscriptionException.setErreur(MapUtils.CHAMP_TELEPHONE, ex.getMessage());
		}

		try {
			validationCodePostal(modifProfilDTO.getCodePostal());
		} catch (Exception ex) {
			inscriptionException.setErreur(MapUtils.CHAMP_CP, ex.getMessage());
		}

		return modifProfilDTO;

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
	 * Valide le mot de passe saisi.
	 * @throws Exception 
	 */
	public static void validationMotsDePasse(String motDePasse) throws Exception {

		
			if (motDePasse.trim().length() < 5) {
				throw new Exception("Les mots de passe doivent contenir au moins 5 caractères.");
			}
			if (motDePasse.length() > 30) {
				throw new Exception("Retapez un mot de passe de moins de 30 caractères sans caractères spéciaux");
			}
	}

	/**
	 * Valide le texte saisi.
	 */
	private static void validationPseudo(String pseudo) throws Exception {
		if (pseudo == null || pseudo.trim().length() < 3) {
			throw new Exception("Le champ " + pseudo + " doit contenir au moins 3 caractères.");
		}
		if (Pattern.matches("[^\\w]", pseudo)) {
			throw new Exception("Ce pseudo ne doit contenir que des caractères alpha-numériques");
		}
	}

	/**
	 * valide le code postal saisi
	 * 
	 * @param codePostal
	 * @throws Exception
	 */
	private static void validationCodePostal(String codePostal) throws Exception {
		if (codePostal == null || codePostal.trim().length() != 5 || !StringUtils.isNumeric(codePostal)) {
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
		if (telephone == null || telephone.trim().length() != 10 || !StringUtils.isNumeric(telephone)) {
			throw new Exception("Merci de saisir un numéro à 10 chiffres");
		}
	}


	
}
