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
	
	private static final String ATT_SESSION_USER = "sessionUtilisateur";
	
	
	/**
	 * Récupérer Utilisateur de la session
	 * @param request
	 * @return Utilisateur
	 * @throws BusinessException
	 */
	public Utilisateur getUtilisateurFromSession(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		
		Utilisateur user = (Utilisateur) session.getAttribute("sessionUtilisateur");
		return user;
	}
	
//	/**TRANSPORTE DIRECTEMENT DANS ServletDeconnexion
//	 * demander la fermeture de la session
//	 * @param request
//	 */
//	public void deconnexion(HttpServletRequest request) {
//		HttpSession session = request.getSession();
//		session.invalidate();
//	}

	//SE CONNECTER
	/**
	 * Vérifie l'existance login et pwd dans la BDD et met l'utilisateur en session
	 * @param login
	 * @param pwd
	 * @param request
	 * @throws LoginException 
	 */
	public void login(HttpServletRequest request) throws LoginException {
		LoginException loginException = new LoginException();
		//check si login et pwd existent dans la BDD et check mdp : avec selectByEmailOrPseudo
		Utilisateur user=null;
		try {
			user = userDAO.selectByEmailOrPseudo(MapUtils.getValeurChamp(request, MapUtils.CHAMP_LOGIN));
			if(user == null) {
				loginException.setErreur(MapUtils.CHAMP_LOGIN, "Le pseudo ou email n'existe pas");
			}
			//ok, le login (pseudo ou email) existe
			if(!user.getMotDePasse().equals(MapUtils.getValeurChamp(request, MapUtils.CHAMP_PWD))) {
				loginException.setErreur(MapUtils.CHAMP_PWD, "Le mot de passe est incorrect");
			}
			//ok, le pwd correspond au motDePasse enregistré en BDD
			if (loginException.hasErreurs()) {
				throw loginException;
			}
			
		} catch (SQLException e) {
			System.out.println("erreur BDD");
		}
		HttpSession session = request.getSession();

		session.setAttribute(ATT_SESSION_USER, user);
		

	}
	
}
