package org.eni.encheres.bll;

import java.time.LocalDate;
import java.util.List;

import org.eni.encheres.bo.ArticleVendu;
import org.eni.encheres.bo.Categorie;
import org.eni.encheres.bo.Utilisateur;
import org.eni.encheres.dal.ArticleVenduDAO;
import org.eni.encheres.dal.DAOFactory;
import org.eni.encheres.erreur.BusinessException;

public class ArticleVenduManager { // TODO Prévoir le formattage des String (lower, uppercase,...)
	
	
private ArticleVenduDAO articleVenduDAO;
private static final int NOMBRE_IMAGE_OBLIGATOIRE = 3;
	
	
	public ArticleVenduManager() {
		articleVenduDAO = DAOFactory.getArticleVenduDAO();
	}
	
	/**
	 * Cette méthode permet d'insérer un article dans la base de données
	 * @param String nomArticle, String description, LocalDate dateDebutEncheres, LocalDate dateFinEncheres, int prixInitial,
	 * List<String> imageUrl, Utilisateur utilisateur, Categorie categorie
	 * @throws BusinessException
	 * @return ArticleVendu
	 */
	public void insertArticleVendu(ArticleVendu articleVendu) throws BusinessException {
		BusinessException exception = new BusinessException();
		validerNom(articleVendu, exception);
		validerDescription(articleVendu, exception);
		validerDateDebutEncheres(articleVendu, exception);
		validerDateFinEncheres(articleVendu, exception);
		validerPrixInitial(articleVendu, exception);
		validerListeImage(articleVendu);
		
		if(exception.hasErreurs()) {
			throw exception;
		}
		else {
			articleVenduDAO.insertArticleVendu(articleVendu);
		}
	}
	
	public void updateArticleVendu(ArticleVendu articleVendu) throws BusinessException {
		BusinessException exception = new BusinessException();
		validerNom(articleVendu, exception);
		validerDescription(articleVendu, exception);
		validerDateDebutEncheres(articleVendu, exception);
		validerDateFinEncheres(articleVendu, exception);
		validerPrixInitial(articleVendu, exception);
		validerListeImage(articleVendu);
		
		if(exception.hasErreurs()) {
			throw exception;
		}
		else {
			articleVenduDAO.updateArticleVendu(articleVendu);
		}
	}
	
	// A ne pas utiliser. Privilégier l'utilisation de l'update avec etat_vente = annule
	public void deleteArticleVendu(ArticleVendu articleVendu) throws BusinessException {
		articleVenduDAO.deleteArticleVendu(articleVendu);
	}
	
	public List<ArticleVendu> selectArticleVendu(Utilisateur utilisateur) throws BusinessException {
		return articleVenduDAO.selectArticleVendu(utilisateur);
	}
	
	public List<ArticleVendu> selectArticleVendu(Categorie categorie, String keyword) throws BusinessException {
		keyword = keyword.trim();
		return articleVenduDAO.selectArticleVendu(categorie, keyword);
	}
	
	// Les méthodes "valider" vérifie et corrige/transforme les données saisie par l'utilisateur
	private void validerNom(ArticleVendu articleVendu, BusinessException exeption) {
		articleVendu.setNomArticle(articleVendu.getNomArticle().trim());
		if (articleVendu.getNomArticle() ==  null || articleVendu.getNomArticle().equals("") || articleVendu.getNomArticle().length() > 30) {
			exeption.ajouterErreur(CodesResultatBLL.REGLE_NOM_ARTICLE_ERREUR);
		}
	}
	
	private void validerDescription(ArticleVendu articleVendu, BusinessException exeption) {
		articleVendu.setDescription(articleVendu.getDescription().trim());
		if (articleVendu.getDescription() ==  null || articleVendu.getDescription().equals("") || articleVendu.getDescription().length() > 300) {
			exeption.ajouterErreur(CodesResultatBLL.REGLE_DESCRIPTION_ARTICLE_ERREUR);
		}
	}
	
	private void validerDateDebutEncheres(ArticleVendu articleVendu, BusinessException exeption) {
		if (articleVendu.getDateDebutEncheres() ==  null || articleVendu.getDateDebutEncheres().isBefore(LocalDate.now()) || articleVendu.getDateDebutEncheres().isEqual(LocalDate.now())) {
			exeption.ajouterErreur(CodesResultatBLL.REGLE_DATE_DEBUT_ENCHERE_ARTICLE_ERREUR);
		}
	}
	
	private void validerDateFinEncheres(ArticleVendu articleVendu, BusinessException exeption) {
		if (articleVendu.getDateFinEncheres() ==  null || articleVendu.getDateFinEncheres().isBefore(articleVendu.getDateDebutEncheres()) || articleVendu.getDateFinEncheres().isEqual(articleVendu.getDateDebutEncheres())) {
			exeption.ajouterErreur(CodesResultatBLL.REGLE_DATE_FIN_ENCHERE_ARTICLE_ERREUR);
		}
	}
	
	private void validerPrixInitial(ArticleVendu articleVendu, BusinessException exeption) {
		if (articleVendu.getPrixInitial() < 0) {
			exeption.ajouterErreur(CodesResultatBLL.REGLE_PRIX_INITIAL_ARTICLE_ERREUR);
		}
	}
	
	// Caractéristique obligatoire pour le bon fonctionnement des méthodes suivantes
	// La liste d'image doit contenir obligatoirement un certain nombre d'image
	private void validerListeImage(ArticleVendu articleVendu) {
		while (articleVendu.getImageUrl().size() < NOMBRE_IMAGE_OBLIGATOIRE) {
			articleVendu.getImageUrl().add("");
		}
	}

}
