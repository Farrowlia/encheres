package org.eni.encheres.bll;

import org.eni.encheres.bo.ArticleVendu;
import org.eni.encheres.bo.Retrait;
import org.eni.encheres.dal.DAOFactory;
import org.eni.encheres.dal.RetraitDAO;
import org.eni.encheres.erreur.BusinessException;

public class RetraitManager {
	
	
	private RetraitDAO retraitDAO;
	
	
	public RetraitManager() {
		retraitDAO = DAOFactory.getRetraitDAO();
	}
	
	
	public void insertRetrait(Retrait retrait) throws BusinessException {
		BusinessException exception = new BusinessException();
		validerRue(retrait, exception);
		validerCodePostal(retrait, exception);
		validerVille(retrait, exception);
		
		if(exception.hasErreurs()) {
			throw exception;
		}
		else {
			retraitDAO.insertRetrait(retrait);
		}
	}
	
	public void updateRetrait(Retrait retrait) throws BusinessException {
		BusinessException exception = new BusinessException();
		validerRue(retrait, exception);
		validerCodePostal(retrait, exception);
		validerVille(retrait, exception);
		
		if(exception.hasErreurs()) {
			throw exception;
		}
		else {
			retraitDAO.updateRetrait(retrait);
		}
	}

	public void deleteRetrait(Retrait retrait) throws BusinessException {
		retraitDAO.deleteRetrait(retrait);
	}
	
	public Retrait selectRetrait(ArticleVendu articleVendu) throws BusinessException {
		return retraitDAO.selectRetrait(articleVendu);
	}
	
	
	// Les méthodes "valider" vérifie et corrige/transforme les données saisie par l'utilisateur
	public void validerRue(Retrait retrait, BusinessException exeption) {
		retrait.setRue(retrait.getRue().trim());
		if (retrait.getRue() ==  null || retrait.getRue().equals("") || retrait.getRue().length() > 50) {
			exeption.ajouterErreur(CodesResultatBLL.REGLE_RUE_RETRAIT_ERREUR);
		}
	}
	
	public void validerCodePostal(Retrait retrait, BusinessException exeption) {
		retrait.setCodePostal(retrait.getCodePostal().trim());
		if (retrait.getCodePostal() ==  null || retrait.getCodePostal().equals("") || retrait.getCodePostal().length() > 15) {
			exeption.ajouterErreur(CodesResultatBLL.REGLE_CODEPOSTAL_RETRAIT_ERREUR);
		}
	}
	
	public void validerVille(Retrait retrait, BusinessException exeption) {
		retrait.setVille(retrait.getVille().trim());
		if (retrait.getVille() ==  null || retrait.getVille().equals("") || retrait.getVille().length() > 30) {
			exeption.ajouterErreur(CodesResultatBLL.REGLE_VILLE_RETRAIT_ERREUR);
		}
	}

}
