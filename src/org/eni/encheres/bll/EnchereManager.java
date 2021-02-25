package org.eni.encheres.bll;

import java.util.List;

import org.eni.encheres.bo.ArticleVendu;
import org.eni.encheres.bo.Enchere;
import org.eni.encheres.bo.Utilisateur;
import org.eni.encheres.dal.DAOFactory;
import org.eni.encheres.dal.enchere.EnchereDAO;
import org.eni.encheres.erreur.BusinessException;

public class EnchereManager {
	
	private EnchereDAO enchereDAO;
	
	
	public EnchereManager() {
		enchereDAO = DAOFactory.getEnchereDAO();
	}

	
	public void insertEnchere(Enchere enchere) throws BusinessException {
		BusinessException exception = new BusinessException();
		valider(enchere, exception);

		if (exception.hasErreurs()) {
			throw exception;
		}
		else {
			enchereDAO.insertEnchere(enchere);
		}
	}

	public List<Enchere> selectEnchere(Utilisateur utilisateur) throws BusinessException {
		return enchereDAO.selectEnchere(utilisateur);
	}
	
	public List<Enchere> selectEnchere(ArticleVendu articleVendu) throws BusinessException {
		return enchereDAO.selectEnchere(articleVendu);
	}
	
	public List<Enchere> selectEnchereRemporteByUtilisateur(Utilisateur utilisateur) throws BusinessException {
		return enchereDAO.selectEnchereRemporteByUtilisateur(utilisateur);
	}

	private void valider(Enchere enchere, BusinessException exeption) throws BusinessException {
		List<Enchere> listeEncheres = enchereDAO.selectEnchere(enchere.getArticleVendu());
		
		if (listeEncheres.size() > 0) {
			int montantEnchereMax = listeEncheres.get(0).getMontanEnchere(); // la 1ère enchère est la plus haute car ORDER BY dans la dal
			if (enchere.getMontanEnchere() <= montantEnchereMax) {
				exeption.ajouterErreur(CodesResultatBLL.REGLE_MONTANT_ENCHERE_ERREUR);
			}
		}
	}
	
}
