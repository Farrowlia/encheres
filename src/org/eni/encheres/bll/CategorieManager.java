package org.eni.encheres.bll;

import java.util.List;

import org.eni.encheres.bo.Categorie;
import org.eni.encheres.dal.DAOFactory;
import org.eni.encheres.dal.categorie.CategorieDAO;
import org.eni.encheres.erreur.BusinessException;

public class CategorieManager {
	
	private CategorieDAO categorieDAO;
	

	public CategorieManager() {
		categorieDAO = DAOFactory.getCategorieDAO();
	}
	
	
	public void insertCategorie(Categorie categorie) throws BusinessException {
		BusinessException exception = new BusinessException();
		valider(categorie, exception);
		
		if(exception.hasErreurs()) {
			throw exception;
		}
		else {
			categorieDAO.insertCategorie(categorie);
		}
	}
	
	public void updateCategorie(Categorie categorie) throws BusinessException {
		BusinessException exception = new BusinessException();
		valider(categorie, exception);
		
		if(exception.hasErreurs()) {
			throw exception;
		}
		else {
			categorieDAO.updateCategorie(categorie);
		}
	}
	
	public void deleteCategorie(Categorie categorie) throws BusinessException {
		categorieDAO.deleteCategorie(categorie);
	}

	public List<Categorie> selectCategorie() throws BusinessException {
		return categorieDAO.selectCategorie();
	}
	
	private void valider(Categorie categorie, BusinessException exception) {
		categorie.setLibelle(categorie.getLibelle().trim());
		if(categorie.getLibelle() == null || categorie.getLibelle().equals("") || categorie.getLibelle().length() > 30) {
			exception.ajouterErreur(CodesResultatBLL.REGLE_LIBELLE_CATEGORIE_ERREUR);
		}
	}
	
}
