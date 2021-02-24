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
	
	private static final String INSERT_ARTICLE_VENDU = "INSERT INTO ARTICLES_VENDUS (nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, etat_vente, no_utilisateur, no_categorie) VALUES(?,?,?,?,?,?,?,?,?);";
	private static final String UPDATE_ARTICLE_VENDU = "UPDATE ARTICLES_VENDUS SET nom_article = ?, description = ?, date_debut_encheres = ?, date_fin_encheres = ?, prix_initial = ?, prix_vente = ?, etat_vente = ?, no_categorie = ? WHERE no_article = ?;";
	private static final String DELETE_ARTICLE_VENDU = "DELETE FROM ARTICLES_VENDUS WHERE no_article = ?;";
	private static final String SELECT_ARTICLE_VENDU_BY_VENTE_UTILISATEUR = "SELECT * FROM ARTICLES_VENDUS AS art INNER JOIN CATEGORIES AS cat ON art.no_categorie = cat.no_categorie WHERE art.no_utilisateur = ? ORDER BY no_article DESC;";
	private static final String SELECT_ARTICLE_VENDU_BY_ACHAT_UTILISATEUR = "SELECT * FROM ARTICLES_VENDUS AS art INNER JOIN CATEGORIES AS cat ON art.no_categorie = cat.no_categorie INNER JOIN ENCHERES AS ench ON art.no_article = ench.no_article WHERE ench.no_utilisateur = ? ORDER BY montant_enchere DESC;";
	private static final String SELECT_ARTICLE_VENDU_BY_CATandKEY = "SELECT * FROM ARTICLES_VENDUS AS art INNER JOIN CATEGORIES AS cat ON art.no_categorie = cat.no_categorie INNER JOIN UTILISATEURS AS util ON art.no_utilisateur = util.no_utilisateur WHERE cat.no_categorie = ?";
	private static final String SELECT_ARTICLE_VENDU_BY_KEY = "SELECT * FROM ARTICLES_VENDUS AS art INNER JOIN CATEGORIES AS cat ON art.no_categorie = cat.no_categorie INNER JOIN UTILISATEURS AS util ON art.no_utilisateur = util.no_utilisateur";
	private static final String SELECT_ARTICLE_VENDU_BY_ID = "SELECT * FROM ARTICLES_VENDUS AS art INNER JOIN CATEGORIES AS cat ON art.no_categorie = cat.no_categorie INNER JOIN UTILISATEURS AS util ON art.no_utilisateur = util.no_utilisateur WHERE no_article = ?;";

	@Override
	public ArticleVendu insertArticleVendu(ArticleVendu articleVendu) throws BusinessException {
		try (Connection connexion = ConnectionProvider.getConnection();
			PreparedStatement pstmt = connexion.prepareStatement(INSERT_ARTICLE_VENDU, PreparedStatement.RETURN_GENERATED_KEYS);) {
				
			pstmt.setString(1, articleVendu.getNomArticle());
			pstmt.setString(2, articleVendu.getDescription());
			pstmt.setDate(3, java.sql.Date.valueOf(articleVendu.getDateDebutEncheres()));
			pstmt.setDate(4, java.sql.Date.valueOf(articleVendu.getDateFinEncheres()));
			pstmt.setInt(5, articleVendu.getPrixInitial());
			pstmt.setInt(6, articleVendu.getPrixInitial());
			pstmt.setString(7, articleVendu.getEtatVente());
			pstmt.setInt(8, articleVendu.getUtilisateur().getNoUtilisateur());
			pstmt.setInt(9, articleVendu.getCategorie().getNoCategorie());
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
			pstmtArticle.setInt(6, articleVendu.getPrixVente());
			pstmtArticle.setString(7, articleVendu.getEtatVente());
			pstmtArticle.setInt(8, articleVendu.getCategorie().getNoCategorie());
			pstmtArticle.setInt(9, articleVendu.getNoArticle());
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

	@Override
	public List<ArticleVendu> selectArticleVendu(Categorie categorie, String keyword) throws BusinessException {
		List<ArticleVendu> listeArticleVendu = new ArrayList<>();
		
		try (Connection connexion = ConnectionProvider.getConnection();) {
			String finRequeteSQL = "";
			String finRequeteSQL2 = "";
			if (keyword == null || keyword.equals("")) {
				finRequeteSQL = ";";
				finRequeteSQL2 = ";";
			}
			else {
				keyword = "%" + keyword + "%";
				finRequeteSQL = " WHERE nom_article LIKE ?;";
				finRequeteSQL2 = " AND nom_article LIKE ?;";
			}
			
			PreparedStatement pstmt;
			if (categorie.getNoCategorie() == 0) {
				pstmt = connexion.prepareStatement(SELECT_ARTICLE_VENDU_BY_KEY + finRequeteSQL);
					if (!finRequeteSQL.equals(";")) {
						pstmt.setString(1, keyword);
					}
			}
			else {
				pstmt = connexion.prepareStatement(SELECT_ARTICLE_VENDU_BY_CATandKEY + finRequeteSQL2);
					if (!finRequeteSQL2.equals(";")) {
						pstmt.setInt(1, categorie.getNoCategorie());
						pstmt.setString(2, keyword);
					}
			}
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Utilisateur utilisateur1 = new Utilisateur(rs.getInt("no_utilisateur"), rs.getString("pseudo"), rs.getString("nom"),
						rs.getString("prenom"), rs.getString("email"), rs.getString("telephone"), rs.getString("rue"),
						rs.getString("code_postal"), rs.getString("ville"), rs.getString("mot_de_passe"), rs.getInt("credit"),
						rs.getBoolean("administrateur"), rs.getBoolean("compte_actif"));
				Categorie categorie1 = new Categorie(rs.getInt("no_categorie"), rs.getString("libelle"));
				ArticleVendu articleVendu1 = new ArticleVendu(rs.getInt("no_article"), rs.getString("nom_article"), rs.getString("description"),
						rs.getDate("date_debut_encheres").toLocalDate(), rs.getDate("date_fin_encheres").toLocalDate(), rs.getInt("prix_initial"),
						rs.getInt("prix_vente"), rs.getString("etat_vente"), utilisateur1, categorie1);
				
				listeArticleVendu.add(articleVendu1);
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

	@Override
	public ArticleVendu selectArticleVenduById(int noArticle) throws BusinessException {
		ArticleVendu articleVendu = new ArticleVendu();
		
		try (Connection connexion = ConnectionProvider.getConnection();
			PreparedStatement pstmt = connexion.prepareStatement(SELECT_ARTICLE_VENDU_BY_ID);) {
			
			pstmt.setInt(1, noArticle);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				Utilisateur utilisateur = new Utilisateur(rs.getInt("no_utilisateur"), rs.getString("pseudo"), rs.getString("nom"),
														rs.getString("prenom"), rs.getString("email"), rs.getString("telephone"), rs.getString("rue"),
														rs.getString("code_postal"), rs.getString("ville"), rs.getString("mot_de_passe"), rs.getInt("credit"),
														rs.getBoolean("administrateur"), rs.getBoolean("compte_actif"));
				Categorie categorie = new Categorie(rs.getInt("no_categorie"), rs.getString("libelle"));
				articleVendu = new ArticleVendu(rs.getInt("no_article"), rs.getString("nom_article"), rs.getString("description"),
												rs.getDate("date_debut_encheres").toLocalDate(), rs.getDate("date_fin_encheres").toLocalDate(), rs.getInt("prix_initial"),
												rs.getInt("prix_vente"), rs.getString("etat_vente"), utilisateur, categorie);
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException exception = new BusinessException();
			exception.ajouterErreur(CodesResultatArticleVenduDAL.SELECT_ARTICLE_VENDU_ERREUR);
			throw exception;
		}
		return articleVendu;
	}
	
}
