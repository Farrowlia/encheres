package org.eni.encheres.dal;

import java.util.List;


import org.eni.encheres.bo.Categorie;
import org.eni.encheres.erreur.BusinessException;

/**
 * 
 * @author aleroy2020
 *
 */
public interface CategorieDAO {

	public void insertCategorie(Categorie categorie) throws BusinessException;
	public void updateCategorie(Categorie categorie) throws BusinessException;
	public void deleteCategorie(Categorie categorie) throws BusinessException;
	public List<Categorie> selectCategorie() throws BusinessException;

}
