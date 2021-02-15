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
		
	}
	
	public void updateRetrait(Retrait retrait) throws BusinessException {
		
	}

	public void deleteRetrait(Retrait retrait) throws BusinessException {
		
	}
	
	public Retrait selectRetrait(ArticleVendu articleVendu) throws BusinessException {
		return null;
	}

}
