package org.eni.encheres.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletAuthentification
 */
@WebServlet("/ServletAuthentification")
public class ServletAuthentification extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

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
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/formConnexion.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
