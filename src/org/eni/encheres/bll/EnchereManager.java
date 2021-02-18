package org.eni.encheres.bll;

import java.time.LocalDate;
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
		validerMontantEnchere(enchere, exception);

		if (exception.hasErreurs()) {
			throw exception;
		} else {
			enchereDAO.insertEnchere(enchere);
		}
	}

	
	
	// le montant de l'enchere doit être superieur au montant de l'enchere
	// precedente
	private void validerMontantEnchere(ArticleVendu articleVendu, BusinessException exeption) {
		List<Enchere> listeEncheres = enchereDAO.selectEncheres(articleVendu);
		
		int monEnchereMax = listeEncheres.get(0);
			for(Enchere enchere : listeEncheres) {
				if(enchere.getMontanEnchere() > monEnchereMax) {
					monEnchereMax=enchere.getMontanEnchere();
				}
			}
			if(articleVendu.getEnchere().getMontantEnchere() > monEnchereMax) {
				EnchereDAO.insertEnchere(articleVendu);
			}
			else {
				exeption.ajouterErreur(CodesResultatBLL.REGLE_MONTANT_ENCHERE_ERREUR);
			}
		
	}
}
