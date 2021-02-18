package org.eni.encheres.dal.image;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.eni.encheres.bo.ArticleVendu;
import org.eni.encheres.bo.Image;
import org.eni.encheres.dal.ConnectionProvider;
import org.eni.encheres.erreur.BusinessException;

public class ImageDAOJdbcImpl implements ImageDAO {
	
	private static final String INSERT_IMAGE = "INSERT INTO IMAGES (chemin_url, no_article) VALUES(?,?);";
	private static final String UPDATE_IMAGE = "UPDATE IMAGES SET chemin_url = ? WHERE no_image = ?;";
	private static final String DELETE_IMAGE = "DELETE FROM IMAGES WHERE no_image = ?;";
	private static final String SELECT_IMAGE = "SELECT * FROM IMAGES WHERE no_article = ?;";

	@Override
	public void insertImage(Image image) throws BusinessException {
		try (Connection connexion = ConnectionProvider.getConnection();
			PreparedStatement pstmt = connexion.prepareStatement(INSERT_IMAGE);) {
					
			pstmt.setString(1, image.getCheminUrl());
			pstmt.setInt(2, image.getArticleVendu().getNoArticle());
			pstmt.executeUpdate();
				
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException exception = new BusinessException();
			exception.ajouterErreur(CodesResultatImageDAL.INSERT_IMAGE_ERREUR);
			throw exception;
		}
	}

	@Override
	public void updateImage(Image image) throws BusinessException {
		try (Connection connexion = ConnectionProvider.getConnection();
			PreparedStatement pstmt = connexion.prepareStatement(UPDATE_IMAGE);) {
					
			pstmt.setString(1, image.getCheminUrl());
			pstmt.setInt(2, image.getNoImage());
			pstmt.executeUpdate();
				
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException exception = new BusinessException();
			exception.ajouterErreur(CodesResultatImageDAL.UPDATE_IMAGE_ERREUR);
			throw exception;
		}
	}

	@Override
	public void deleteImage(Image image) throws BusinessException {
		try (Connection connexion = ConnectionProvider.getConnection();
			PreparedStatement pstmt = connexion.prepareStatement(DELETE_IMAGE);) {
					
			pstmt.setInt(1, image.getNoImage());
			pstmt.executeUpdate();
				
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException exception = new BusinessException();
			exception.ajouterErreur(CodesResultatImageDAL.DELETE_IMAGE_ERREUR);
			throw exception;
		}
	}

	@Override
	public List<Image> selectImage(ArticleVendu articleVendu) throws BusinessException {
		List<Image> listeImage = new ArrayList<>();
		
		try (Connection connexion = ConnectionProvider.getConnection();
			PreparedStatement pstmt = connexion.prepareStatement(SELECT_IMAGE);) {
			
			pstmt.setInt(1, articleVendu.getNoArticle());
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Image image = new Image(rs.getInt("no_image"), rs.getString("chemin_url"), articleVendu);
				listeImage.add(image);
			}
			rs.close();
				
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException exception = new BusinessException();
			exception.ajouterErreur(CodesResultatImageDAL.SELECT_IMAGE_ERREUR);
			throw exception;
		}
		return listeImage;
	}

}
