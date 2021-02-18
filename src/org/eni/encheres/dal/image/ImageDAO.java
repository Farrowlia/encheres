package org.eni.encheres.dal.image;

import java.util.List;

import org.eni.encheres.bo.ArticleVendu;
import org.eni.encheres.bo.Image;
import org.eni.encheres.erreur.BusinessException;

public interface ImageDAO {
	
	public void insertImage(Image image) throws BusinessException;
	public void updateImage(Image image) throws BusinessException;
	public void deleteImage(Image image) throws BusinessException;
	public List<Image> selectImage(ArticleVendu articleVendu) throws BusinessException;

}
