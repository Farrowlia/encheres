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
			// TODO execution méthode de la DAO*************************************
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
			// TODO execution méthode de la DAO*************************************
		}
	}

	public void deleteRetrait(Retrait retrait) throws BusinessException {
		// TODO execution méthode de la DAO*************************************
	}
	
	public Retrait selectRetrait(ArticleVendu articleVendu) throws BusinessException {
		return null; // TODO execution méthode de la DAO*************************************
	}
	
	
	// Les méthodes "valider" vérifie et corrige/transforme les données saisie par l'utilisateur
	public void validerRue(Retrait retrait, BusinessException exeption) {
		retrait.setRue(retrait.getRue().trim());
		if (retrait.getRue() ==  null || retrait.getRue().equals("") || retrait.getRue().length() > 50) {
			exeption.ajouterErreur(CodesResultatBLL.REGLE_XXXXXXXXXXX_ERREUR);
		}
	}
	
	public void validerCodePostal(Retrait retrait, BusinessException exeption) {
		retrait.setCode_postal(retrait.getCode_postal().trim());
		if (retrait.getCode_postal() ==  null || retrait.getCode_postal().equals("") || retrait.getCode_postal().length() > 15) {
			exeption.ajouterErreur(CodesResultatBLL.REGLE_XXXXXXXXXXX_ERREUR);
		}
	}
	
	public void validerVille(Retrait retrait, BusinessException exeption) {
		retrait.setVille(retrait.getVille().trim());
		if (retrait.getVille() ==  null || retrait.getVille().equals("") || retrait.getVille().length() > 15) {
			exeption.ajouterErreur(CodesResultatBLL.REGLE_XXXXXXXXXXX_ERREUR);
		}
	}

}
