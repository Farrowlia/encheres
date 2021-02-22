package org.eni.encheres.dal.articlevendu;

import java.util.List;

import org.eni.encheres.bo.ArticleVendu;
import org.eni.encheres.bo.Categorie;
import org.eni.encheres.bo.Utilisateur;
import org.eni.encheres.erreur.BusinessException;

// Cette interface permet de faire le lien entre la classe bll et dal sans qu'il est conaissance l'un de l'autre
// Facilite le mantien et les modifications futurs (ex : changement de BDD)
public interface ArticleVenduDAO {
	
	
	public ArticleVendu insertArticleVendu(ArticleVendu articleVendu) throws BusinessException;
	public void updateArticleVendu(ArticleVendu articleVendu) throws BusinessException;
	public void deleteArticleVendu(ArticleVendu articleVendu) throws BusinessException;
	public List<ArticleVendu> selectArticleVenduByVenteUtilisateur(Utilisateur utilisateur) throws BusinessException;
	public List<ArticleVendu> selectArticleVenduByAchatUtilisateur(Utilisateur utilisateur) throws BusinessException;
	public List<ArticleVendu> selectArticleVendu(Categorie categorie, String keyword) throws BusinessException;

}
