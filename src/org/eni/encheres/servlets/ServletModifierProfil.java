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
import org.eni.encheres.erreur.BusinessException;
import org.eni.encheres.utils.MapUtils;

/**
 * Servlet implementation class ServletModifierProfil
 */
@WebServlet("/ModifierProfil")
public class ServletModifierProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
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
		// Supprimer le compte
		if (request.getParameter("action") == "delete") {
			try {
				um.deleteCompte(authentification.getUtilisateurFromSession(request));
				// supprime le compte et les ventes en cours et encheres associées
				this.getServletContext().getRequestDispatcher("/Deconnexion").forward(request, response);
				// déconnecte l'utilisateur et le redirige vers la page d'accueil

			} catch (BusinessException | SQLException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// mise à jour du compte (UPDATE)
		request.setCharacterEncoding("UTF-8");

		Utilisateur utilisateur = new Authentification().getUtilisateurFromSession(request);
		String motDePasseActuel = utilisateur.getMotDePasse();
		utilisateur = MapUtils.mapUtilisateur(request);
		if (MapUtils.getValeurChamp(request, "newMotDePasse") == null) {
			utilisateur.setMotDePasse(motDePasseActuel);
			}
		String newMotDePasse = MapUtils.getValeurChamp(request, "newMotDePasse");
//		if (newMotDePasse != null) {
//			//vérifier qu'il est différent du mdp actuel
//			if ()
			//récupérer mdp et confirmation
			//le charger dans utilisateur
			
		
			try {
				um.saveNewOrExistingCompte(utilisateur);
			} catch (SQLException | InscriptionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			HttpSession session = request.getSession();
			session.setAttribute("sessionUtilisateur", utilisateur);

			request.getRequestDispatcher("index.jsp").forward(request, response);
	}

}
