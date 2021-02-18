package org.eni.encheres.bll;

import java.util.List;

import org.eni.encheres.bo.ArticleVendu;
import org.eni.encheres.bo.Image;
import org.eni.encheres.dal.DAOFactory;
import org.eni.encheres.dal.image.ImageDAO;
import org.eni.encheres.erreur.BusinessException;

public class ImageManager {
	
	private ImageDAO imageDAO;
		
		
		public ImageManager() {
			imageDAO = DAOFactory.getImageDAO();
		}
		
		
		public void insertImage(Image image) throws BusinessException {
			BusinessException exception = new BusinessException();
			valider(image, exception);
			
			if (exception.hasErreurs()) {
				throw exception;
			}
			else {
				imageDAO.insertImage(image);
			}
		}
		
		public void updateImage(Image image) throws BusinessException {
			BusinessException exception = new BusinessException();
			valider(image, exception);
			
			if (exception.hasErreurs()) {
				throw exception;
			}
			else {
				imageDAO.updateImage(image);
			}
		}

		public void deleteImage(Image image) throws BusinessException {
			imageDAO.deleteImage(image);
		}

		public List<Image> selectImage(ArticleVendu articleVendu) throws BusinessException {
			return imageDAO.selectImage(articleVendu);
		}
		
		private void valider(Image image, BusinessException exeption) {
			if (image.getCheminUrl() == null || image.getCheminUrl().equals("") || image.getCheminUrl().length() > 200) {
				exeption.ajouterErreur(CodesResultatBLL.REGLE_NOM_ARTICLE_ERREUR); // TODO REGLE ERREUR
			}
		}

}
