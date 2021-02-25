package org.eni.encheres.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.eni.encheres.bll.CategorieManager;
import org.eni.encheres.bo.Categorie;
import org.eni.encheres.erreur.BusinessException;
import org.eni.encheres.utils.TimerMiseAJourBDD;

/**
 * Servlet implementation class Accueil
 */
@WebServlet("/Accueil")
public class ServletAccueil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		List<Categorie> listeCategorie = new ArrayList<>();
		CategorieManager categorieManager = new CategorieManager();
		
		try {
			listeCategorie = categorieManager.selectCategorie();
			
		} catch (BusinessException e) {
			request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
		}
		
		session.setAttribute("listeCategorie", listeCategorie);
		
		
		// Appelle du Run contenu dans utils.TimerMiseAJourBDD
		TimerTask timerMiseAJourBDD = new TimerMiseAJourBDD();
		Timer timer = new Timer();
		timer.schedule(timerMiseAJourBDD, 1000, 1000);
		
		
		RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
