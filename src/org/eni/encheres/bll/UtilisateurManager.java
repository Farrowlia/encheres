package org.eni.encheres.bll;

import java.sql.SQLException;

import org.eni.encheres.authentification.InscriptionException;
import org.eni.encheres.bll.validator.UtilisateurValidator;
import org.eni.encheres.bo.Utilisateur;
import org.eni.encheres.dal.DAOFactory;
import org.eni.encheres.dal.utilisateur.UtilisateurDAO;
import org.eni.encheres.dto.ModifProfilDTO;
import org.eni.encheres.erreur.BusinessException;

public class UtilisateurManager {
	


	private UtilisateurDAO userDAO;
	
	public UtilisateurManager() {
		userDAO = DAOFactory.getUtilisateurDAO();
		
	}

	public Utilisateur createUtilisateur(Utilisateur utilisateur) throws SQLException {
	

		//création d'un compte
		//mettre le crédit à '100', compte actif et administrateur à 'false'
		utilisateur.setCredit(100);
		utilisateur.setCompteActif(true);
		utilisateur.setAdministrateur(false);
		
		return userDAO.insertUtilisateur(utilisateur);
	} 
	
	public Utilisateur updateUtilisateur(ModifProfilDTO modifProfilDTO) throws SQLException, InscriptionException {
		InscriptionException inscriptionException = new InscriptionException();
		UtilisateurValidator.validateModifProfilDTO(modifProfilDTO, inscriptionException);
		
		if (inscriptionException.hasErreurs()) {
			throw inscriptionException;
		}
		Utilisateur userToUpdate = new Utilisateur(
				modifProfilDTO.getNoUtilisateur(),
				modifProfilDTO.getPseudo(), 
				modifProfilDTO.getNom(), 
				modifProfilDTO.getPrenom(), 
				modifProfilDTO.getEmail(),
				modifProfilDTO.getTelephone(),
				modifProfilDTO.getRue(),
				modifProfilDTO.getCodePostal(),
				modifProfilDTO.getVille(),
				modifProfilDTO.getMotDePasse());
		
		return userDAO.updateUtilisateur(userToUpdate, modifProfilDTO.isUpdatePwd());
	} 
	
	/**
	 * @apiNote cette méthode vérifie si l'utilisateur existe déjà
	 * @param noUtilisateur
	 * @return booléen
	 * @throws SQLException 
	 */
	public boolean isUserExists(int noUtilisateur) throws SQLException {
			return userDAO.selectById(noUtilisateur) != null;	
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
	 * @param utilisateur
	 * @throws BusinessException
	 * @throws SQLException 
	 */
	public void deleteCompte(Utilisateur utilisateur) throws BusinessException, SQLException {
		userDAO.deleteUtilisateur(utilisateur);
	}

	public boolean isUserExistsByPseudo(String pseudo) throws SQLException {
		
		return userDAO.selectByPseudo(pseudo.trim()) != null;
	}
	
	public boolean isEmailExists(String email) throws SQLException {
		
		return userDAO.selectByEmail(email.trim()) != null;
	}
	
}
