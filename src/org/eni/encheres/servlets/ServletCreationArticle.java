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
import org.eni.encheres.bll.ImageManager;
import org.eni.encheres.bll.RetraitManager;
import org.eni.encheres.bo.ArticleVendu;
import org.eni.encheres.bo.Categorie;
import org.eni.encheres.bo.Image;
import org.eni.encheres.bo.Retrait;
import org.eni.encheres.bo.Utilisateur;
import org.eni.encheres.erreur.BusinessException;

/**
 * Servlet implementation class CreationArticleVendu
 */
@WebServlet("/CreationArticle")
public class ServletCreationArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pageCreationArticle.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			request.setCharacterEncoding("UTF-8");
			HttpSession session = request.getSession();
			
			Utilisateur utilisateur = (Utilisateur)session.getAttribute("sessionUtilisateur");
			ArticleVendu articleVendu = new ArticleVendu();
			Categorie categorie = new Categorie();
			Retrait retrait  = new Retrait();
			Image image = new Image();
			
			ArticleVenduManager articleVenduManager = new ArticleVenduManager();
			RetraitManager retraitManager = new RetraitManager();
			ImageManager imageManager = new ImageManager();
			
			try {
				categorie.setNoCategorie(Integer.parseInt(request.getParameter("noCategorie")));
				
				articleVendu.setNomArticle(request.getParameter("nomArticle"));
				articleVendu.setDescription(request.getParameter("description"));
				articleVendu.setDateDebutEncheres(LocalDate.parse(request.getParameter("dateDebutEncheres")));
				articleVendu.setDateFinEncheres(LocalDate.parse(request.getParameter("dateFinEncheres")));
				articleVendu.setPrixInitial(Integer.parseInt(request.getParameter("prixInitial")));
				articleVendu.setEtatVente("en_attente");
				articleVendu.setUtilisateur(utilisateur);
				articleVendu.setCategorie(categorie);
				articleVendu = articleVenduManager.insertArticleVendu(articleVendu);
				
				retrait.setRue(request.getParameter("rue"));
				retrait.setCodePostal(request.getParameter("codePostal"));
				retrait.setVille(request.getParameter("ville"));
				retrait.setArticleVendu(articleVendu);
				retraitManager.insertRetrait(retrait);
				
				image.setCheminUrl(request.getParameter("cheminUrl"));
				image.setArticleVendu(articleVendu);
				imageManager.insertImage(image);
				
			} catch (BusinessException e) {
				request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
			}
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pageMesArticles.jsp");
			rd.forward(request, response);
		}

}
