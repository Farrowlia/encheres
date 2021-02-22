package org.eni.encheres.dal.articlevendu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.eni.encheres.bo.ArticleVendu;
import org.eni.encheres.bo.Categorie;
import org.eni.encheres.bo.Utilisateur;
import org.eni.encheres.dal.ConnectionProvider;
import org.eni.encheres.erreur.BusinessException;

public class ArticleVenduDAOJdbcImpl implements ArticleVenduDAO {
	
	private static final String INSERT_ARTICLE_VENDU = "INSERT INTO ARTICLES_VENDUS (nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, etat_vente, no_utilisateur, no_categorie) VALUES(?,?,?,?,?,?,?,?);";
	private static final String UPDATE_ARTICLE_VENDU = "UPDATE ARTICLES_VENDUS SET nom_article = ?, description = ?, date_debut_encheres = ?, date_fin_encheres = ?, prix_initial = ?, etat_vente = ?, no_categorie = ? WHERE no_article = ?;";
	private static final String DELETE_ARTICLE_VENDU = "DELETE FROM ARTICLES_VENDUS WHERE no_article = ?;";
	private static final String SELECT_ARTICLE_VENDU_BY_VENTE_UTILISATEUR = "SELECT * FROM ARTICLES_VENDUS AS art INNER JOIN CATEGORIES AS cat ON art.no_categorie = cat.no_categorie WHERE art.no_utilisateur = ? ORDER BY no_article DESC;";
	private static final String SELECT_ARTICLE_VENDU_BY_ACHAT_UTILISATEUR = "SELECT * FROM ARTICLES_VENDUS AS art INNER JOIN CATEGORIES AS cat ON art.no_categorie = cat.no_categorie INNER JOIN ENCHERES AS ench ON art.no_article = ench.no_article WHERE ench.no_utilisateur = ? ORDER BY montant_enchere DESC;";
	private static final String SELECT_ARTICLE_VENDU_BY_RECHERCHE = "SELECT * FROM ARTICLES_VENDUS AS art INNER JOIN CATEGORIES AS cat ON art.no_categorie = cat.no_categorie INNER JOIN UTILISATEURS AS user ON art.no_utilisateur = user.no_utilisateur";

	@Override
	public ArticleVendu insertArticleVendu(ArticleVendu articleVendu) throws BusinessException {
		try (Connection connexion = ConnectionProvider.getConnection();
			PreparedStatement pstmt = connexion.prepareStatement(INSERT_ARTICLE_VENDU, PreparedStatement.RETURN_GENERATED_KEYS);) {
				
			pstmt.setString(1, articleVendu.getNomArticle());
			pstmt.setString(2, articleVendu.getDescription());
			pstmt.setDate(3, java.sql.Date.valueOf(articleVendu.getDateDebutEncheres()));
			pstmt.setDate(4, java.sql.Date.valueOf(articleVendu.getDateFinEncheres()));
			pstmt.setInt(5, articleVendu.getPrixInitial());
			pstmt.setString(6, articleVendu.getEtatVente());
			pstmt.setInt(7, articleVendu.getUtilisateur().getNoUtilisateur());
			pstmt.setInt(8, articleVendu.getCategorie().getNoCategorie());
			pstmt.executeUpdate();
			
			ResultSet rs = pstmt.getGeneratedKeys();
					if (rs.next()) {
						articleVendu.setNoArticle(rs.getInt(1));
					}
			
			return articleVendu;
			
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException exception = new BusinessException();
			exception.ajouterErreur(CodesResultatArticleVenduDAL.INSERT_ARTICLE_VENDU_ERREUR);
			throw exception;
		}
	}

	@Override
	public void updateArticleVendu(ArticleVendu articleVendu) throws BusinessException {
		try (Connection connexion = ConnectionProvider.getConnection();
			PreparedStatement pstmtArticle = connexion.prepareStatement(UPDATE_ARTICLE_VENDU);) {
				
			pstmtArticle.setString(1, articleVendu.getNomArticle());
			pstmtArticle.setString(2, articleVendu.getDescription());
			pstmtArticle.setDate(3, java.sql.Date.valueOf(articleVendu.getDateDebutEncheres()));
			pstmtArticle.setDate(4, java.sql.Date.valueOf(articleVendu.getDateFinEncheres()));
			pstmtArticle.setInt(5, articleVendu.getPrixInitial());
			pstmtArticle.setString(6, articleVendu.getEtatVente());
			pstmtArticle.setInt(7, articleVendu.getCategorie().getNoCategorie());
			pstmtArticle.setInt(8, articleVendu.getNoArticle());
			pstmtArticle.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException exception = new BusinessException();
			exception.ajouterErreur(CodesResultatArticleVenduDAL.UPDATE_ARTICLE_VENDU_ERREUR);
			throw exception;
		}
	}
	
	@Override
	public void deleteArticleVendu(ArticleVendu articleVendu) throws BusinessException {
		try (Connection connexion = ConnectionProvider.getConnection();
			PreparedStatement pstmt = connexion.prepareStatement(DELETE_ARTICLE_VENDU);) {
				
			pstmt.setInt(1, articleVendu.getNoArticle());
			pstmt.executeUpdate();
				
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException exception = new BusinessException();
			exception.ajouterErreur(CodesResultatArticleVenduDAL.DELETE_ARTICLE_VENDU_ERREUR);
			throw exception;
		}
	}
	
	@Override
	public List<ArticleVendu> selectArticleVenduByVenteUtilisateur(Utilisateur utilisateur) throws BusinessException {
		List<ArticleVendu> listeArticleVendu = new ArrayList<>();
		
		try (Connection connexion = ConnectionProvider.getConnection();
			PreparedStatement pstmt = connexion.prepareStatement(SELECT_ARTICLE_VENDU_BY_VENTE_UTILISATEUR);) {
			
			pstmt.setInt(1, utilisateur.getNoUtilisateur());
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Categorie categorie = new Categorie(rs.getInt("no_categorie"), rs.getString("libelle"));
				ArticleVendu articleVendu = new ArticleVendu(rs.getInt("no_article"), rs.getString("nom_article"), rs.getString("description"),
						rs.getDate("date_debut_encheres").toLocalDate(), rs.getDate("date_fin_encheres").toLocalDate(), rs.getInt("prix_initial"),
						rs.getInt("prix_vente"), rs.getString("etat_vente"), utilisateur, categorie);
				
				listeArticleVendu.add(articleVendu);
			}
			rs.close();
				
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException exception = new BusinessException();
			exception.ajouterErreur(CodesResultatArticleVenduDAL.SELECT_ARTICLE_VENDU_ERREUR);
			throw exception;
		}
		return listeArticleVendu;
	}

	@Override
	public List<ArticleVendu> selectArticleVenduByAchatUtilisateur(Utilisateur utilisateur) throws BusinessException {
		List<ArticleVendu> listeArticleVendu = new ArrayList<>();
		
		try (Connection connexion = ConnectionProvider.getConnection();
			PreparedStatement pstmt = connexion.prepareStatement(SELECT_ARTICLE_VENDU_BY_ACHAT_UTILISATEUR);) {
			
			pstmt.setInt(1, utilisateur.getNoUtilisateur());
			ResultSet rs = pstmt.executeQuery();
			
			ArticleVendu articleVendu = new ArticleVendu();
			while (rs.next()) {
				
				if (rs.getInt("no_article") != articleVendu.getNoArticle()) {
					Categorie categorie = new Categorie(rs.getInt("no_categorie"), rs.getString("libelle"));
					articleVendu = new ArticleVendu(rs.getInt("no_article"), rs.getString("nom_article"), rs.getString("description"),
							rs.getDate("date_debut_encheres").toLocalDate(), rs.getDate("date_fin_encheres").toLocalDate(), rs.getInt("prix_initial"),
							rs.getInt("prix_vente"), rs.getString("etat_vente"), utilisateur, categorie);
					
					listeArticleVendu.add(articleVendu);
				}
				
			}
			rs.close();
				
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException exception = new BusinessException();
			exception.ajouterErreur(CodesResultatArticleVenduDAL.SELECT_ARTICLE_VENDU_ERREUR);
			throw exception;
		}
		return listeArticleVendu;
	}

	// TODO selectArticleVendu par mot-clé
	@Override
	public List<ArticleVendu> selectArticleVendu(Categorie categorie, String keyword) throws BusinessException {
		List<ArticleVendu> listeArticleVendu = new ArrayList<>();
		
		try (Connection connexion = ConnectionProvider.getConnection();) {
			String finRequeteSQL = "";
			if (keyword == null || keyword.equals("")) {
				finRequeteSQL = ";";
			}
			else {
				keyword = "%" + keyword + "%";
				finRequeteSQL = " WHERE nom_article LIKE ?;";
			}
			
			PreparedStatement pstmt = connexion.prepareStatement(SELECT_ARTICLE_VENDU_BY_RECHERCHE + finRequeteSQL);
			pstmt.setString(1, keyword);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Utilisateur utilisateur = new Utilisateur();
				ArticleVendu articleVendu = new ArticleVendu(rs.getInt("no_article"), rs.getString("nom_article"), rs.getString("description"),
						rs.getDate("date_debut_encheres").toLocalDate(), rs.getDate("date_fin_encheres").toLocalDate(), rs.getInt("prix_initial"),
						rs.getInt("prix_vente"), rs.getString("etat_vente"), utilisateur, categorie);
				
				listeArticleVendu.add(articleVendu);
			}
			rs.close();
			pstmt.close();
				
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException exception = new BusinessException();
			exception.ajouterErreur(CodesResultatArticleVenduDAL.SELECT_ARTICLE_VENDU_ERREUR);
			throw exception;
		}
		return listeArticleVendu;
	}
	
}
