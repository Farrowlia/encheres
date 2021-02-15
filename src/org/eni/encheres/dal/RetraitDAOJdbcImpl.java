package org.eni.encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.eni.encheres.bo.ArticleVendu;
import org.eni.encheres.bo.Retrait;
import org.eni.encheres.erreur.BusinessException;

public class RetraitDAOJdbcImpl implements RetraitDAO {
	
	
	private static final String INSERT_RETRAIT = "INSERT INTO RETRAITS (noArticle, rue, code_postal, ville) VALUES(?,?,?,?);";
	private static final String UPDATE_RETRAIT = "UPDATE RETRAITS SET rue = ?, code_postal = ?, ville = ? WHERE noArticle = ?;";
	private static final String DELETE_RETRAIT = "DELETE FROM RETRAITS WHERE noArticle = ?;";
	private static final String SELECT_RETRAIT = "SELECT * FROM RETRAITS WHERE noArticle = ?;";

	
	@Override
	public void insertRetrait(Retrait retrait) throws BusinessException {
		try (Connection connexion = ConnectionProvider.getConnection();
			PreparedStatement pstmt = connexion.prepareStatement(INSERT_RETRAIT);) {
			
			pstmt.setInt(1, retrait.getArticleVendu().getNoArticle());
			pstmt.setString(2, retrait.getRue());
			pstmt.setString(3, retrait.getCodePostal());
			pstmt.setString(4, retrait.getVille());
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException exception = new BusinessException();
			exception.ajouterErreur(CodesResultatDAL.INSERT_RETRAIT_ERREUR);
			throw exception;
		}
	}

	@Override
	public void updateRetrait(Retrait retrait) throws BusinessException {
		try (Connection connexion = ConnectionProvider.getConnection();
			PreparedStatement pstmt = connexion.prepareStatement(UPDATE_RETRAIT);) {
				
			pstmt.setString(1, retrait.getRue());
			pstmt.setString(2, retrait.getCodePostal());
			pstmt.setString(3, retrait.getVille());
			pstmt.setInt(4, retrait.getArticleVendu().getNoArticle());
			pstmt.executeUpdate();
				
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException exception = new BusinessException();
			exception.ajouterErreur(CodesResultatDAL.UPDATE_RETRAIT_ERREUR);
			throw exception;
		}
	}

	@Override
	public void deleteRetrait(Retrait retrait) throws BusinessException {
		try (Connection connexion = ConnectionProvider.getConnection();
			PreparedStatement pstmt = connexion.prepareStatement(DELETE_RETRAIT);) {
				
			pstmt.setInt(1, retrait.getArticleVendu().getNoArticle());
			pstmt.executeUpdate();
				
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException exception = new BusinessException();
			exception.ajouterErreur(CodesResultatDAL.DELETE_RETRAIT_ERREUR);
			throw exception;
		}
	}

	@Override
	public Retrait selectRetrait(ArticleVendu articleVendu) throws BusinessException {
		Retrait retrait = new Retrait();
		try (Connection connexion = ConnectionProvider.getConnection();
			PreparedStatement pstmt = connexion.prepareStatement(SELECT_RETRAIT);
			ResultSet rs = pstmt.executeQuery();) {
			
			if (rs.next()) {
				retrait = new Retrait(rs.getString("rue"), rs.getString("code_postal"), rs.getString("ville"), new ArticleVendu(rs.getInt("no_article")));
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException exception = new BusinessException();
			exception.ajouterErreur(CodesResultatDAL.SELECT_RETRAIT_ERREUR);
			throw exception;
		}
		return retrait;
	}

}
