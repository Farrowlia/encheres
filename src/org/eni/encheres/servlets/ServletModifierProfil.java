package org.eni.encheres.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.eni.encheres.authentification.Authentification;
import org.eni.encheres.authentification.InscriptionException;
import org.eni.encheres.bll.UtilisateurManager;
import org.eni.encheres.bo.Utilisateur;
import org.eni.encheres.dto.ModifProfilDTO;
import org.eni.encheres.utils.MapUtils;

/**
 * Servlet implementation class ServletModifierProfil
 */
@WebServlet("/ModifierProfil")
public class ServletModifierProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String ATT_USER = "utilisateur";
	private static final String ATT_FORM = "form";

	UtilisateurManager um = new UtilisateurManager();
	Authentification authentification = new Authentification();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pageMonProfil.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// mise à jour du compte (UPDATE)
		request.setCharacterEncoding("UTF-8");
		
		// récupération des valeurs du formulaire
		ModifProfilDTO modifProfilDTO = MapUtils.mapModifProfilDTO(request);

		try {
			Utilisateur updatedUtilisateur = um.updateUtilisateur(modifProfilDTO);
			
			HttpSession session = request.getSession();
			session.setAttribute("sessionUtilisateur", updatedUtilisateur);
		} catch (SQLException e) {
			System.out.println("erreur BDD");
		} catch (InscriptionException e) {
			// Si échec de la validation de l'update utilisateur
			/* Stockage du formulaire et du bean dans l'objet request */
			request.setAttribute(ATT_FORM, e);
			request.setAttribute(ATT_USER, modifProfilDTO);
		}
		request.getRequestDispatcher("Accueil").forward(request, response);
	}

}
