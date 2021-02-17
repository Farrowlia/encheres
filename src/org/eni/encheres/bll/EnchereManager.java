package org.eni.encheres.bll;

import java.time.LocalDate;
import java.util.List;

import org.eni.encheres.bo.ArticleVendu;
import org.eni.encheres.bo.Enchere;
import org.eni.encheres.bo.Utilisateur;
import org.eni.encheres.dal.DAOFactory;
import org.eni.encheres.dal.EnchereDAO;
import org.eni.encheres.erreur.BusinessException;

public class EnchereManager {
	
	private EnchereDAO enchereDAO;
	
	public EnchereManager() {
		enchereDAO = DAOFactory.getEnchereDAO();
	}

	public void insertEnchere (Enchere enchere) throws BusinessException {
		BusinessException exception = new BusinessException();
		validerDateEnchere(enchere, exception);
		validerMontantEnchere(enchere, exception);
		
		if(exception.hasErreurs()) {
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

	// Les méthodes "valider" vérifient et corrigent/transforme les données saisies par l'utilisateur
	private void validerDateEnchere(Enchere enchere, BusinessException exeption) {
		enchere.setDateEnchere(enchere.getDateEnchere());
		if(enchere.getDateEnchere() == null || enchere.getDateEnchere().isBefore(LocalDate.now())) {
			exeption.ajouterErreur(CodesResultatBLL.REGLE_DATE_ENCHERE_ERREUR);
		}
	}
	
	private void validerMontantEnchere(Enchere enchere, BusinessException exeption) {
		enchere.setMontantEnchere(enchere.getMontanEnchere());
		if(enchere.getMontanEnchere() <= 0) {
			exeption.ajouterErreur(CodesResultatBLL.REGLE_MONTANT_ENCHERE_ERREUR);
		}
	}
}
