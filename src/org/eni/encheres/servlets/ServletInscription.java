package org.eni.encheres.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eni.encheres.authentification.InscriptionException;
import org.eni.encheres.authentification.InscriptionForm;
import org.eni.encheres.bll.UtilisateurManager;
import org.eni.encheres.bll.validator.UtilisateurValidator;
import org.eni.encheres.bo.Utilisateur;
import org.eni.encheres.erreur.BusinessException;
import org.eni.encheres.utils.MapUtils;
import static org.eni.encheres.utils.MapUtils.getValeurChamp;;

/**
 * Servlet implementation class ServletInscription
 */
@WebServlet("/ServletInscription")
public class ServletInscription extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static final String URL_JSP_INSCRIPTION = "/WEB-INF/jsp/formInscription.jsp";

	private static final String ATT_USER = "utilisateur";
	private static final String ATT_FORM = "form";

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher(URL_JSP_INSCRIPTION).forward(request, response);

		if (request.getParameter("cancel") != null) {
			request.getRequestDispatcher("/accueil.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		UtilisateurManager um = new UtilisateurManager();

		try {
			Utilisateur utilisateur = MapUtils.mapUtilisateur(request);;

				// Si échec de la validation de l'utilisateur
				/* Stockage du formulaire et du bean dans l'objet request */
				request.setAttribute(ATT_FORM, inscriptionException);
				request.setAttribute(ATT_USER, utilisateur);

				/* Transmission de la paire d'objets request/response à notre JSP */
				this.getServletContext().getRequestDispatcher(URL_JSP_INSCRIPTION).forward(request, response);
				
			// TODO Redirection vers page voulue

			um.saveNewOrExistingCompte(utilisateur);
		} catch (SQLException e) {
			// TODO Renvoyer une réponse indiquant un prob avec la base de données
			// (i.e. initialiser variable ici et l'appeler dans setattribute
		}

	}

}
