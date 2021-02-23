package org.eni.encheres.authentification;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.eni.encheres.bo.Utilisateur;
import org.eni.encheres.dal.utilisateur.UtilisateurDAO;
import org.eni.encheres.dal.utilisateur.UtilisateurDAOJdbcImpl;
import org.eni.encheres.erreur.BusinessException;
import org.eni.encheres.utils.MapUtils;

public class Authentification {
	
	private UtilisateurDAO userDAO = new UtilisateurDAOJdbcImpl();
	
	/**
	 * Vérifier si un utilisateur est connecté
	 * @return true si user connecté
	 * @throws BusinessException
	 */
	public Utilisateur authorize(HttpServletRequest request) throws BusinessException {
	//verifie si user != null dans la session
		HttpSession session = request.getSession();
		/* Récupération de l'objet depuis la session */
		if (session.getAttribute( "user" ) == null) {
			throw new BusinessException();
		}
		Utilisateur user = (Utilisateur) session.getAttribute("user");
		return user;
	}
	
	/**
	 * demander la fermeture de la session
	 * @param request
	 */
	public void deconnection(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
	}

	//SE CONNECTER
	/**
	 * Se connecter
	 * @param login
	 * @param pwd
	 * @param request
	 * @throws LoginException 
	 */
	public Utilisateur login(String login, String pwd) throws LoginException {
		LoginException loginException = new LoginException();
		//check si login et pwd existent dans la BDD et check mdp : avec selectByEmailOrPseudo
		Utilisateur user=null;
		try {
			
			user = userDAO.selectByEmailOrPseudo(login);
			if(user == null) {
				loginException.setErreur(MapUtils.CHAMP_LOGIN, "Le pseudo ou email n'existe pas");
			}
			//ok, le login (pseudo ou email) existe
			if(!user.getMotDePasse().equals(pwd)) {
				loginException.setErreur(MapUtils.CHAMP_PWD, "Le mot de passe est incorrect");
			}
			//ok, le pwd correspond au motDePasse enregistré en BDD
			if (loginException.hasErreurs()) {
				throw loginException;
			}
			
		} catch (SQLException e) {
			System.out.println("erreur BDD");
		}
		return user;
	}
	
}
