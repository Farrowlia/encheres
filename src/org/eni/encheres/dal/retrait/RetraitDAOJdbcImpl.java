package org.eni.encheres.dal.retrait;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.eni.encheres.bo.ArticleVendu;
import org.eni.encheres.bo.Retrait;
import org.eni.encheres.dal.ConnectionProvider;
import org.eni.encheres.erreur.BusinessException;

public class RetraitDAOJdbcImpl implements RetraitDAO {
	
	private static final String INSERT_RETRAIT = "INSERT INTO RETRAITS (no_article, rue, code_postal, ville) VALUES(?,?,?,?);";
	private static final String UPDATE_RETRAIT = "UPDATE RETRAITS SET rue = ?, code_postal = ?, ville = ? WHERE no_article = ?;";
	private static final String DELETE_RETRAIT = "DELETE FROM RETRAITS WHERE no_article = ?;";
	private static final String SELECT_RETRAIT = "SELECT * FROM RETRAITS WHERE no_article = ?;";
	
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
			exception.ajouterErreur(CodesResultatRetraitDAL.INSERT_RETRAIT_ERREUR);
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
			exception.ajouterErreur(CodesResultatRetraitDAL.UPDATE_RETRAIT_ERREUR);
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
			exception.ajouterErreur(CodesResultatRetraitDAL.DELETE_RETRAIT_ERREUR);
			throw exception;
		}
	}

	@Override
	public Retrait selectRetrait(ArticleVendu articleVendu) throws BusinessException {
		Retrait retrait = new Retrait();
		
		try (Connection connexion = ConnectionProvider.getConnection();
			PreparedStatement pstmt = connexion.prepareStatement(SELECT_RETRAIT);) {
			
			pstmt.setInt(1, articleVendu.getNoArticle());
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				retrait = new Retrait(rs.getString("rue"), rs.getString("code_postal"), rs.getString("ville"), articleVendu);
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException exception = new BusinessException();
			exception.ajouterErreur(CodesResultatRetraitDAL.SELECT_RETRAIT_ERREUR);
			throw exception;
		}
		return retrait;
	}

}
