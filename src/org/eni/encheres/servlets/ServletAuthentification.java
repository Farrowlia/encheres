package org.eni.encheres.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
//		//TODO if (case se souvenir est cochée) {
//		Cookie[] cookies = request.getCookies();
//		Cookie cookieEmail=null;
//		Utilisateur utilisateur = new Authentification().getUtilisateurFromSession(request);
//		if(cookies!=null)
//		{
//			for(Cookie c:cookies)
//			{
//				if(c.getName().equals("seSouvenir"))
//				{
//					cookieEmail=c;
//					cookieEmail.setValue(utilisateur.getEmail());
//					break;
//				}
//			}
//		}
//		
//		if(cookieEmail==null)
//		{
//			cookieEmail=new Cookie("email", utilisateur.getEmail());
//			
//			cookieEmail.setValue(utilisateur.getEmail());
//		}
//		
//		cookieEmail.setMaxAge(Integer.MAX_VALUE);
//		
//		response.addCookie(cookieEmail);
//		
//		request.setAttribute("cookieEmail", cookieEmail);}
		
		request.getRequestDispatcher("Accueil").forward(request, response);
		
	}

}
