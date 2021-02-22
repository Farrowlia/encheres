package org.eni.encheres.bll;

import java.sql.SQLException;

import org.eni.encheres.authentification.InscriptionException;
import org.eni.encheres.bll.validator.UtilisateurValidator;
import org.eni.encheres.bo.Utilisateur;
import org.eni.encheres.dal.DAOFactory;
import org.eni.encheres.dal.utilisateur.UtilisateurDAO;
import org.eni.encheres.erreur.BusinessException;

public class UtilisateurManager {
	


	private UtilisateurDAO userDAO;
	
	public UtilisateurManager() {
		userDAO = DAOFactory.getUtilisateurDAO();
		
	}

	
	public void saveNewOrExistingCompte(Utilisateur utilisateur) throws SQLException, InscriptionException {
		InscriptionException inscriptionException = new InscriptionException();
		UtilisateurValidator.validateUtilisateur(utilisateur, inscriptionException);
		if (inscriptionException.hasErreurs()) {
			throw inscriptionException;
		} 
		if (utilisateur.getNoUtilisateur() != 0) {
			//si le compte est modifiable et modifié, enregistrer les modifs
			userDAO.createOrUpdateUtilisateur(utilisateur);
		} else {
			//création d'un compte
			//mettre le crédit à '100' et administrateur à 'false'
			Utilisateur newUser = new Utilisateur(	utilisateur.getPseudo(), 
					utilisateur.getNom(), 
					utilisateur.getPrenom(), 
					utilisateur.getEmail(),
					utilisateur.getTelephone(),
					utilisateur.getRue(),
					utilisateur.getCodePostal(),
					utilisateur.getVille(),
					utilisateur.getMotDePasse(),
					100, 
					false,
					true);
			
			userDAO.createOrUpdateUtilisateur(newUser);
		}
		
	}
	

	
	/**
	 * Mise à jour du mot de passe après demande de réinitialisation
	 * vérifie que le mdp ne dépasse pas 30 caractères
	 * @throws SQLException 
	 */
	public void newPassword(int id, String pwd) throws BusinessException, SQLException {
		BusinessException businessException = new BusinessException();
		if (pwd.length()>30) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_PASSWORD_ERREUR);
			throw businessException;
		}
		userDAO.updatePassword(id, pwd);
	}
	
	/**
	 * AFFICHER SON PROFIL via noUtilisateur
	 * @param id
	 * @return Utilisateur
	 * @throws BusinessException
	 * @throws SQLException 
	 */
	public Utilisateur voirProfil(int id) throws BusinessException, SQLException {
		return userDAO.selectById(id);
	}
	
	/**
	 * voir le profil d'un autre utilisateur (avec pseudo en paramètre)
	 * @param pseudo
	 * @return
	 * @throws BusinessException
	 * @throws SQLException 
	 */
	public Utilisateur voirProfil(String pseudo) throws BusinessException, SQLException {
		return userDAO.selectByPseudo(pseudo);
	}
	

	
	/**
	 * supprimer le compte
	 * @param id
	 * @throws BusinessException
	 * @throws SQLException 
	 */
	public void deleteCompte(int id) throws BusinessException, SQLException {
		userDAO.deleteUtilisateur(id);
	}

	public boolean isUserExistsByPseudo(String pseudo) throws SQLException {
		
		return userDAO.selectByPseudo(pseudo) != null;
	}
	
	public boolean isEmailExists(String email) throws SQLException {
		
		return userDAO.selectByEmail(email) != null;
	}
	
}
