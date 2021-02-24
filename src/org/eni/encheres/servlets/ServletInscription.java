package org.eni.encheres.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.eni.encheres.authentification.InscriptionException;
import org.eni.encheres.bll.UtilisateurManager;
import org.eni.encheres.bll.validator.UtilisateurValidator;
import org.eni.encheres.bo.Utilisateur;
import org.eni.encheres.utils.MapUtils;
import org.eni.encheres.utils.URL_JSP;;

/**
 * Servlet implementation class ServletInscription
 */
@WebServlet("/Inscription")
public class ServletInscription extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String ATT_USER = "utilisateur";
	private static final String ATT_FORM = "form";

	private static final String ATT_SESSION_USER = "sessionUtilisateur";

	private static final String ATT_ISLOGIN = "isLogin";

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		UtilisateurManager um = new UtilisateurManager();
		InscriptionException inscriptionException = new InscriptionException();
		HttpSession session = request.getSession();
		Utilisateur utilisateur = MapUtils.mapUtilisateur(request);

		try {
			// validation mdp
			UtilisateurValidator.validationMotsDePasse(utilisateur.getMotDePasse(),
					MapUtils.getValeurChamp(request, MapUtils.CHAMP_CONF), inscriptionException);
			// validation utilisateur
			UtilisateurValidator.validateUtilisateur(utilisateur, inscriptionException);

			if (inscriptionException.hasErreurs()) {
				// Si échec de la validation de l'utilisateur
				/* Stockage du formulaire et du bean dans l'objet request */
				request.setAttribute(ATT_FORM, inscriptionException);
				request.setAttribute(ATT_USER, utilisateur);
				request.setAttribute(ATT_ISLOGIN, false);
				/* Transmission de la paire d'objets request/response à notre JSP */
				this.getServletContext().getRequestDispatcher(URL_JSP.URL_JSP_INSCRIPTION).forward(request, response);
			} else {
			um.saveNewOrExistingCompte(utilisateur);
			session.setAttribute(ATT_SESSION_USER, utilisateur); //connecte l'utilisateur en le mettant dans la session
			this.getServletContext().getRequestDispatcher(URL_JSP.URL_RECHERCHE).forward(request, response);
			//TODO connecter user
			}
		} catch (SQLException e) {
			System.out.println("erreur BDD");
			// TODO Renvoyer une réponse indiquant un prob avec la base de données
			// (i.e. initialiser variable ici et l'appeler dans setattribute

		}
	}
	

}
