package org.eni.encheres.servlets;

import static org.eni.encheres.utils.MapUtils.getValeurChamp;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eni.encheres.authentification.Authentification;
import org.eni.encheres.authentification.InscriptionException;
import org.eni.encheres.authentification.LoginException;
import org.eni.encheres.bll.UtilisateurManager;
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

		InscriptionException inscriptionException = new InscriptionException();
		Utilisateur utilisateur = MapUtils.mapUtilisateur(request);
		String motDePasse = getValeurChamp(request, MapUtils.CHAMP_PWD);

		try {
			// validation mdp
			if (!motDePasse.equals(getValeurChamp(request, ATT_FORM))) {
				inscriptionException.setErreur(MapUtils.CHAMP_CONF, "Les mots de passe entrés sont différents, merci de les saisir à nouveau.");
			} else {
			utilisateur.setMotDePasse(motDePasse);
			}
			// validation utilisateur et insertion en BDD, puis login
			new UtilisateurManager().saveNewOrExistingCompte(utilisateur);
			new Authentification().login(request);
			//redirection vers la page accueil en mode connecté
			this.getServletContext().getRequestDispatcher(URL_JSP.URL_RECHERCHE).forward(request, response);
		
		} catch (InscriptionException e) {
			// Si échec de la validation de l'utilisateur
			/* Stockage du formulaire et du bean dans l'objet request */
			request.setAttribute(ATT_FORM, inscriptionException);
			request.setAttribute(ATT_USER, utilisateur);
			request.setAttribute(ATT_ISLOGIN, false);
			/* Transmission de la paire d'objets request/response à notre JSP initiale */
			this.getServletContext().getRequestDispatcher(URL_JSP.URL_JSP_INSCRIPTION).forward(request, response);

		} catch (LoginException e) {
			System.out.println("erreur login");

		} catch (SQLException e) {
				System.out.println("erreur BDD");
				// TODO Renvoyer une réponse indiquant un prob avec la base de données
				// (i.e. initialiser variable ici et l'appeler dans setattribute
		}
	}

}
