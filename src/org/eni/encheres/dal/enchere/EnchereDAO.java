package org.eni.encheres.dal.enchere;

import java.util.List;

import org.eni.encheres.bo.ArticleVendu;
import org.eni.encheres.bo.Enchere;
import org.eni.encheres.bo.Utilisateur;
import org.eni.encheres.erreur.BusinessException;

	public interface EnchereDAO {
		
		public void insertEnchere(Enchere enchere) throws BusinessException;
		public List<Enchere> selectEnchere(Utilisateur utilisateur) throws BusinessException;
		public List<Enchere> selectEnchere(ArticleVendu articleVendu) throws BusinessException;
		public List<Enchere> selectEnchereRemporteByUtilisateur(Utilisateur utilisateur) throws BusinessException;
		
	}
