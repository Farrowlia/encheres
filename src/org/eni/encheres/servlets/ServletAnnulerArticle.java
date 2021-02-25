package org.eni.encheres.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.eni.encheres.bll.ArticleVenduManager;
import org.eni.encheres.bo.ArticleVendu;
import org.eni.encheres.erreur.BusinessException;

/**
 * Servlet implementation class ServletAnnulerArticle
 */
@WebServlet("/AnnulerArticle")
public class ServletAnnulerArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try 
		{
			ArticleVenduManager articleVenduManager = new ArticleVenduManager();
			
			HttpSession session = request.getSession();
			ArticleVendu articleVendu = (ArticleVendu) session.getAttribute("articleVendu");
			
			articleVendu.setEtatVente("annule");
			
			articleVenduManager.updateArticleVendu(articleVendu);
				
		} catch (BusinessException ex) {
			ex.printStackTrace();
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pageMesArticles.jsp");
        rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
