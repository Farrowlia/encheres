package org.eni.encheres.dal.enchere;

import java.util.List;

import org.eni.encheres.bo.ArticleVendu;
import org.eni.encheres.bo.Enchere;
import org.eni.encheres.bo.Utilisateur;
import org.eni.encheres.erreur.BusinessException;

/**
 * 
 * @author aleroy2020
 *
 */
public interface EnchereDAO {

	
	public void insertEnchere(Enchere enchere) throws BusinessException;
	public List<Enchere> selectEncheres(Utilisateur utilisateur) throws BusinessException;
	public List<Enchere> selectEncheres(ArticleVendu articleVendu) throws BusinessException;
	
}
	
