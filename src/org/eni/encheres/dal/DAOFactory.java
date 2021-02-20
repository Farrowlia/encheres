package org.eni.encheres.dal;

import org.eni.encheres.dal.categorie.CategorieDAO;
import org.eni.encheres.dal.categorie.CategorieDAOJdbcImpl;
import org.eni.encheres.dal.enchere.EnchereDAO;
import org.eni.encheres.dal.articlevendu.ArticleVenduDAO;
import org.eni.encheres.dal.articlevendu.ArticleVenduDAOJdbcImpl;
import org.eni.encheres.dal.image.ImageDAO;
import org.eni.encheres.dal.image.ImageDAOJdbcImpl;
import org.eni.encheres.dal.retrait.RetraitDAO;
import org.eni.encheres.dal.retrait.RetraitDAOJdbcImpl;
import org.eni.encheres.dal.utilisateur.UtilisateurDAO;
import org.eni.encheres.dal.utilisateur.UtilisateurDAOJdbcImpl;


public class DAOFactory {
	
	private static UtilisateurDAO utilisateurDAO;
	private static ArticleVenduDAO articleVenduDAO;
	private static EnchereDAO enchereDAO;
	private static CategorieDAO categorieDAO;
	private static RetraitDAO retraitDAO;
	private static ImageDAO imageDAO;
	
	
	public static UtilisateurDAO getUtilisateurDAO() {
		if (utilisateurDAO == null) {
			utilisateurDAO = new UtilisateurDAOJdbcImpl();
		}
		return utilisateurDAO;
	}
	
	public static ArticleVenduDAO getArticleVenduDAO() {
		if (articleVenduDAO == null) {
			articleVenduDAO = new ArticleVenduDAOJdbcImpl();
		}
		return articleVenduDAO;
	}
	
	public static EnchereDAO getEnchereDAO() {
		if (enchereDAO == null) {
			enchereDAO = new EnchereDAOJdbcImpl();
		}
		return enchereDAO;
	}
	
	public static CategorieDAO getCategorieDAO() {
		if (categorieDAO == null) {
			categorieDAO = new CategorieDAOJdbcImpl();
		}
		return categorieDAO;
	}
	
	public static RetraitDAO getRetraitDAO() {
		if (retraitDAO == null) {
			retraitDAO = new RetraitDAOJdbcImpl();
		}
		return retraitDAO;
	}
	
	public static ImageDAO getImageDAO() {
		if (imageDAO == null) {
			imageDAO = new ImageDAOJdbcImpl();
		}
		return imageDAO;
	}

}
