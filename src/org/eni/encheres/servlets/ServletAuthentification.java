package org.eni.encheres.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eni.encheres.authentification.Authentification;
import org.eni.encheres.authentification.LoginException;
import org.eni.encheres.utils.URL_JSP;

/**
 * Servlet implementation class ServletAuthentification
 */
@WebServlet("/Authentification")
public class ServletAuthentification extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	private static final String ATT_FORM = "form";

	private static final String ATT_ISLOGIN = "isLogin";

 

	/**
	 * Récupérer le pseudo de l'utilisateur dans le formulaire de connexion
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Examine tous les cookie présent sur la machine. Si il trouve le cookie "cookieLogin",
		// il récupère sa valeur pour pré-remplir la champ login
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for(Cookie c: cookies) {
				if (c.getName().equals("cookieLogin")) {
					request.setAttribute("cookieLogin", c.getValue());
					break;
				}
			}
		}
		
		request.setAttribute(ATT_ISLOGIN, true); //pour arriver sur l'onglet "Se connecter"
		request.getAttribute("cookieEmail"); //TODO il se passe quoi si cookie est vide ?
		request.getRequestDispatcher(URL_JSP.URL_JSP_CONNEXION).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			new Authentification().login(request); //vérifie 

		} catch (LoginException loginException) {
			//si la déconnexion ne réussit pas :
			request.setAttribute(ATT_FORM, loginException); //renvoie les messages d'erreur dans le formulaire
//			session.setAttribute(ATT_SESSION_USER, null); => inutile
			request.setAttribute(ATT_ISLOGIN, true); //permet de conditionner le css dans la JSP
			this.getServletContext().getRequestDispatcher(URL_JSP.URL_JSP_CONNEXION).forward(request, response);
		}
		
		// Création d'un cookie qui enregistre le login enregistré lors de la dernière session valide
		Cookie cookieLogin = new Cookie("cookieLogin", request.getParameter("login"));
		cookieLogin.setMaxAge(Integer.MAX_VALUE);
		response.addCookie(cookieLogin);
		
		request.getRequestDispatcher("Accueil").forward(request, response);
	}

}
