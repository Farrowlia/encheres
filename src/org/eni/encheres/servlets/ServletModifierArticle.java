package org.eni.encheres.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.eni.encheres.bll.ArticleVenduManager;
import org.eni.encheres.bll.ImageManager;
import org.eni.encheres.bll.RetraitManager;
import org.eni.encheres.bo.ArticleVendu;
import org.eni.encheres.bo.Categorie;
import org.eni.encheres.bo.Image;
import org.eni.encheres.bo.Retrait;
import org.eni.encheres.erreur.BusinessException;

/**
 * Servlet implementation class ServletModifierArticle
 */
@WebServlet("/ModifierArticle")
public class ServletModifierArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		try
		{
			ArticleVenduManager articleVenduManager = new ArticleVenduManager();
			RetraitManager retraitManager = new RetraitManager();
			ImageManager imageManager = new ImageManager();
			
			
			Categorie categorie = new Categorie();
			categorie.setNoCategorie(Integer.parseInt(request.getParameter("categorie")));
			
			HttpSession session = request.getSession();
			ArticleVendu articleVendu = (ArticleVendu) session.getAttribute("articleVendu");
			
			articleVendu.setNomArticle(request.getParameter("nomArticle"));
			articleVendu.setDescription(request.getParameter("description"));
			articleVendu.setPrixInitial(Integer.parseInt(request.getParameter("prixInitial")));
			articleVendu.setDateDebutEncheres(LocalDate.parse(request.getParameter("dateDebutEncheres")));
			articleVendu.setDateFinEncheres(LocalDate.parse(request.getParameter("dateFinEncheres")));
			articleVendu.setCategorie(categorie);
			
			Retrait retrait = new Retrait();
			
			retrait.setRue(request.getParameter("rue"));
			retrait.setCodePostal(request.getParameter("codePostal"));
			retrait.setVille(request.getParameter("ville"));
			
			Image image = new Image();
			@SuppressWarnings("unchecked")
			List<Image> listeImage = (List<Image>) session.getAttribute("listeImage");
			
			image.setNoImage(listeImage.get(0).getNoImage());
			image.setCheminUrl(request.getParameter("cheminUrl"));
			
			
			articleVenduManager.updateArticleVendu(articleVendu);
			
			retrait.setArticleVendu(articleVendu);
			retraitManager.updateRetrait(retrait);
			
			image.setArticleVendu(articleVendu);
			imageManager.updateImage(image);
			
		
		} catch (BusinessException ex) {
			ex.printStackTrace();
			request.setAttribute("listeCodesErreur", ex.getListeCodesErreur());
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pageMesArticles.jsp");
        rd.forward(request, response);
		
		
	}

}
