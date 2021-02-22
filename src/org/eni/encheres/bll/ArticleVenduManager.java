package org.eni.encheres.bll;

import java.time.LocalDate;
import java.util.List;

import org.eni.encheres.bo.ArticleVendu;
import org.eni.encheres.bo.Categorie;
import org.eni.encheres.bo.Utilisateur;
import org.eni.encheres.dal.DAOFactory;
import org.eni.encheres.dal.articlevendu.ArticleVenduDAO;
import org.eni.encheres.erreur.BusinessException;

public class ArticleVenduManager { // TODO Prévoir le formattage des String (lower, uppercase,...)
	
private ArticleVenduDAO articleVenduDAO;
	
	
	public ArticleVenduManager() {
		articleVenduDAO = DAOFactory.getArticleVenduDAO();
	}
	
	
	public ArticleVendu insertArticleVendu(ArticleVendu articleVendu) throws BusinessException {
		BusinessException exception = new BusinessException();
		valider(articleVendu, exception);
		articleVendu.setEtatVente("en_attente"); // valeur par defaut obligatoire lors de l'insert
		
		if (exception.hasErreurs()) {
			throw exception;
		}
		return articleVenduDAO.insertArticleVendu(articleVendu);
	}
	
	public void updateArticleVendu(ArticleVendu articleVendu) throws BusinessException {
		BusinessException exception = new BusinessException();
		valider(articleVendu, exception);
		
		if (exception.hasErreurs()) {
			throw exception;
		}
		else {
			articleVenduDAO.updateArticleVendu(articleVendu);
		}
	}
	
	// A éviter. Pour garder une trace privilégier l'utilisation de l'update avec etat_vente = annule
	public void deleteArticleVendu(ArticleVendu articleVendu) throws BusinessException {
		articleVenduDAO.deleteArticleVendu(articleVendu);
	}
	
	public List<ArticleVendu> selectArticleVenduByVenteUtilisateur(Utilisateur utilisateur) throws BusinessException {
		return articleVenduDAO.selectArticleVenduByVenteUtilisateur(utilisateur);
	}
	
	public List<ArticleVendu> selectArticleVenduByAchatUtilisateur(Utilisateur utilisateur) throws BusinessException {
		return articleVenduDAO.selectArticleVenduByAchatUtilisateur(utilisateur);
	}
	
	public List<ArticleVendu> selectArticleVendu(Categorie categorie, String keyword) throws BusinessException {
		keyword = keyword.trim();
		return articleVenduDAO.selectArticleVendu(categorie, keyword);
	}
	
	private void valider(ArticleVendu articleVendu, BusinessException exeption) {
		articleVendu.setNomArticle(articleVendu.getNomArticle().trim());
		if (articleVendu.getNomArticle() == null || articleVendu.getNomArticle().equals("") || articleVendu.getNomArticle().length() > 30) {
			exeption.ajouterErreur(CodesResultatBLL.REGLE_NOM_ARTICLE_ERREUR);
		}
		
		articleVendu.setDescription(articleVendu.getDescription().trim());
		if (articleVendu.getDescription() == null || articleVendu.getDescription().equals("") || articleVendu.getDescription().length() > 300) {
			exeption.ajouterErreur(CodesResultatBLL.REGLE_DESCRIPTION_ARTICLE_ERREUR);
		}
		
		if (articleVendu.getDateDebutEncheres() == null || articleVendu.getDateDebutEncheres().isBefore(LocalDate.now()) || articleVendu.getDateDebutEncheres().isEqual(LocalDate.now())) {
			exeption.ajouterErreur(CodesResultatBLL.REGLE_DATE_DEBUT_ENCHERE_ARTICLE_ERREUR);
		}
		
		if (articleVendu.getDateFinEncheres() == null || articleVendu.getDateFinEncheres().isBefore(articleVendu.getDateDebutEncheres()) || articleVendu.getDateFinEncheres().isEqual(articleVendu.getDateDebutEncheres())) {
			exeption.ajouterErreur(CodesResultatBLL.REGLE_DATE_FIN_ENCHERE_ARTICLE_ERREUR);
		}
		
		if (articleVendu.getPrixInitial() < 0) {
			exeption.ajouterErreur(CodesResultatBLL.REGLE_PRIX_INITIAL_ARTICLE_ERREUR);
		}
	}

}
