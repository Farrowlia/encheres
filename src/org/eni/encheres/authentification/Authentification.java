package org.eni.encheres.authentification;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.eni.encheres.bo.Utilisateur;
import org.eni.encheres.dal.DAOFactory;
import org.eni.encheres.dal.utilisateur.UtilisateurDAO;
import org.eni.encheres.erreur.BusinessException;

public class Authentification {
	
	private UtilisateurDAO userDAO;
	
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
	 */
	public Utilisateur login(String login, String pwd) {
		BusinessException businessException = new BusinessException();
		//check si login et pwd existent dans la BDD et check mdp : avec selectByEmailOrPseudo
		Utilisateur user=null;
		try {
			
			user = userDAO.selectByEmailOrPseudo(login);
			if(user == null) {
				throw businessException;
			}
			//le login (pseudo ou email) existe
			if(!user.getMotDePasse().equals(pwd)) {
				throw businessException;
			}
			//le pwd correspond au motDePasse enregistré en BDD
			
			
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
	
}
