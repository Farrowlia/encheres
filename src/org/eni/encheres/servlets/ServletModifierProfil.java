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
import org.eni.encheres.bll.UtilisateurManager;
import org.eni.encheres.bo.Utilisateur;
import org.eni.encheres.erreur.BusinessException;

/**
 * Servlet implementation class ServletModifierProfil
 */
@WebServlet("/ModifierProfil")
public class ServletModifierProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pageMonProfil.jsp");
			rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Supprimer le compte
		if (request.getParameter("action").equals("delete")) {
		Authentification authentification = new Authentification();
			if (authentification.authorize(request)) {
				UtilisateurManager um = new UtilisateurManager();
				
				try {
					um.deleteCompte(authentification.getUtilisateurFromSession(request)); //supprime le compte et les ventes en cours et encheres associées
					this.getServletContext().getRequestDispatcher("/Deconnexion").forward(request, response); //déconnecte l'utilisateur et le redirige vers la page d'accueil
					
				} catch (BusinessException | SQLException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		if (request.getParameter("action").equals("update")) {
			request.setCharacterEncoding("UTF-8");
			HttpSession session = request.getSession();
			
			Utilisateur utilisateur = (Utilisateur)session.getAttribute("sessionUtilisateur");
			UtilisateurManager utilisateurManager = new UtilisateurManager();
			
			if (request.getParameter("motDePasse").equals(request.getParameter("confirmationMotDePasse"))) {
					utilisateur.setPseudo(request.getParameter("pseudo"));
					utilisateur.setNom(request.getParameter("nom"));
					utilisateur.setPrenom(request.getParameter("prenom"));
					utilisateur.setEmail(request.getParameter("email"));
					utilisateur.setTelephone(request.getParameter("telephone"));
					utilisateur.setRue(request.getParameter("rue"));
					utilisateur.setCodePostal(request.getParameter("codePostal"));
					utilisateur.setVille(request.getParameter("ville"));
					utilisateur.setMotDePasse(request.getParameter("motDePasse"));
					try {
						utilisateurManager.saveNewOrExistingCompte(utilisateur);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
				session.setAttribute("sessionUtilisateur", utilisateur);
				
				RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
				rd.forward(request, response);
			}
		}
	}

}
