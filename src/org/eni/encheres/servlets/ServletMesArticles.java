package org.eni.encheres.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.eni.encheres.bll.ArticleVenduManager;
import org.eni.encheres.bo.ArticleVendu;
import org.eni.encheres.bo.Utilisateur;
import org.eni.encheres.erreur.BusinessException;

/**
 * Servlet implementation class MesArticles
 */
@WebServlet("/MesArticles")
public class ServletMesArticles extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pageMesArticles.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		
		Utilisateur utilisateur = (Utilisateur)session.getAttribute("sessionUtilisateur");
		List<ArticleVendu> listeArticleVendu = new ArrayList<>();
		
		ArticleVenduManager articleVenduManager = new ArticleVenduManager();
		
		if (request.getParameter("radio").equals("radioAchat")) {
			try {
				listeArticleVendu = articleVenduManager.selectArticleVenduByAchatUtilisateur(utilisateur);
				
			} catch (BusinessException e) {
				request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
			}
			
			request.setAttribute("listeArticleVendu", listeArticleVendu);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pageMesArticles.jsp");
			rd.forward(request, response);
		}
		
		if (request.getParameter("radio").equals("radioVente")) {
			try {
				listeArticleVendu = articleVenduManager.selectArticleVenduByVenteUtilisateur(utilisateur);
				
			} catch (BusinessException e) {
				request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
			}
			
			request.setAttribute("listeArticleVendu", listeArticleVendu);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pageMesArticles.jsp");
			rd.forward(request, response);
		}
	}

}
