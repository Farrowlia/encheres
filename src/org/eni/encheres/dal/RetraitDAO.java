package org.eni.encheres.dal;

import org.eni.encheres.bo.ArticleVendu;
import org.eni.encheres.bo.Retrait;
import org.eni.encheres.erreur.BusinessException;

// Cette interface permet de faire le lien entre la classe bll et dal sans qu'il est conaissance l'un de l'autre
// Facilite le mantien et les modifications futurs (ex : changement de BDD)
public interface RetraitDAO {
	
	
	public void insertRetrait(Retrait retrait) throws BusinessException;
	public void updateRetrait(Retrait retrait) throws BusinessException;
	public void deleteRetrait(Retrait retrait) throws BusinessException;
	public Retrait selectRetrait(ArticleVendu articleVendu) throws BusinessException;

}
