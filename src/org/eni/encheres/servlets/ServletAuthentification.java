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
		Cookie[] cookies = request.getCookies();
		Cookie cookieSeSouvenir=null;
		if(cookies!=null)
		{
			for(Cookie c:cookies)
			{
				if(c.getName().equals("seSouvenir"))
				{
					cookieSeSouvenir=c;
					int valeur = Integer.parseInt(cookieSeSouvenir.getValue())+1;
					cookieSeSouvenir.setValue(String.valueOf(valeur));
					break;
				}
			}
		}
		
		if(cookieSeSouvenir==null)
		{
			cookieSeSouvenir=new Cookie("seSouvenir", "1");
		}
		
		cookieSeSouvenir.setMaxAge(Integer.MAX_VALUE);
		
		response.addCookie(cookieSeSouvenir);
		
		request.setAttribute("cookieSeSouvenir", cookieSeSouvenir);
		
		request.getRequestDispatcher(URL_JSP.URL_JSP_CONNEXION).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Authentification auth = new Authentification();
		try {
			auth.login(request); //vérifie 
//			je factorise ces 3 lignes
//			Utilisateur user = auth.login(MapUtils.getValeurChamp(request, MapUtils.CHAMP_LOGIN), MapUtils.getValeurChamp(request, MapUtils.CHAMP_PWD));
//			request.setAttribute(ATT_USER, user);
//			session.setAttribute(ATT_SESSION_USER, user);
		} catch (LoginException loginException) {
			request.setAttribute(ATT_FORM, loginException); //renvoie les messages d'erreur dans le formulaire
//			session.setAttribute(ATT_SESSION_USER, null); => inutile
			request.setAttribute(ATT_ISLOGIN, true); //permet de conditionner le css dans la JSP
			this.getServletContext().getRequestDispatcher(URL_JSP.URL_JSP_CONNEXION).forward(request, response);
		}
		this.getServletContext().getRequestDispatcher(URL_JSP.URL_RECHERCHE).forward(request, response);
		
	}

}
