package org.eni.encheres.servlets;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.eni.encheres.bll.ArticleVenduManager;
import org.eni.encheres.bll.EnchereManager;
import org.eni.encheres.bo.ArticleVendu;
import org.eni.encheres.bo.Enchere;
import org.eni.encheres.bo.Utilisateur;
import org.eni.encheres.erreur.BusinessException;

/**
 * Servlet implementation class ServletNouvelleEnchere
 */
@WebServlet("/NouvelleEnchere")
public class ServletNouvelleEnchere extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		try {

		EnchereManager enchereManager = new EnchereManager();
		
		Enchere enchere = new Enchere();
		
		HttpSession session = request.getSession();
        Utilisateur utilisateur = (Utilisateur)session.getAttribute("sessionUtilisateur");
        ArticleVendu articleVendu = (ArticleVendu)session.getAttribute("articleVendu");
        
		enchere.setDateEnchere(LocalDate.now());
		enchere.setMontantEnchere(Integer.parseInt(request.getParameter("montantEnchere")));
		enchere.setUtilisateur(utilisateur);
		enchere.setArticleVendu(articleVendu);
		
		enchereManager.insertEnchere(enchere);
		
		ArticleVenduManager articleVenduManager = new ArticleVenduManager();
		articleVendu.setPrixVente((Integer.parseInt(request.getParameter("montantEnchere"))));
		System.out.println(articleVendu.getPrixVente());
		articleVenduManager.updateArticleVendu(articleVendu);
			
        } catch (BusinessException ex) {
            ex.printStackTrace();
            request.setAttribute("listeCodesErreur", ex.getListeCodesErreur());
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pageMesArticles.jsp");
        rd.forward(request, response);
    }

}
