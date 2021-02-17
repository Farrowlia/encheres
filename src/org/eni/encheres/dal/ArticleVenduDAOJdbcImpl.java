package org.eni.encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.eni.encheres.bo.ArticleVendu;
import org.eni.encheres.bo.Categorie;
import org.eni.encheres.bo.Utilisateur;
import org.eni.encheres.erreur.BusinessException;

public class ArticleVenduDAOJdbcImpl implements ArticleVenduDAO {
	
	private static final String INSERT_ARTICLE_VENDU = "INSERT INTO ARTICLES_VENDUS (nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, etat_vente, no_utilisateur, no_categorie) VALUES(?,?,?,?,?,?,?,?);";
	private static final String INSERT_IMAGES_ARTICLE_VENDU = "INSERT INTO IMAGES (chemin_url, no_article) VALUES(?,?);";
	private static final String UPDATE_ARTICLE_VENDU = "UPDATE ARTICLES_VENDUS SET nom_article = ?, description = ?, date_debut_encheres = ?, date_fin_encheres = ?, prix_initial = ?, etat_vente = ?, no_categorie = ? WHERE noArticle = ?;";
	private static final String UPDATE_IMAGES_ARTICLE_VENDU = "UPDATE IMAGES SET chemin_url = ? WHERE no_image = ?;";
	private static final String DELETE_ARTICLE_VENDU = "DELETE FROM ARTICLES_VENDUS WHERE noArticle = ?;";
	private static final String SELECT_IMAGES_ARTICLE_VENDU = "SELECT * FROM IMAGES WHERE noArticle = ?;";
	private static final String SELECT_ARTICLE_VENDU_BY_UTILISATEUR = "SELECT * FROM ARTICLES_VENDUS WHERE no_utilisateur = ?;";
	private static final String SELECT_ARTICLE_VENDU_BY_CATEGORIE = "SELECT * FROM ARTICLES_VENDUS WHERE no_categorie = ?;";
	private static final String SELECT_ARTICLE_VENDU_BY_RECHERCHE = "SELECT * FROM ARTICLES_VENDUS WHERE no_categorie = ? AND nom_article LIKE %?%;";

	/**
	 * Cette méthode permet d'insérer un article dans la base de données
	 * @param String nomArticle, String description, LocalDate dateDebutEncheres, LocalDate dateFinEncheres,int prixInitial,
	 * List<String> imageUrl,Utilisateur utilisateur,Categorie categorie
	 * @throws BusinessException
	 * @return ArticleVendu
	 */
	@Override
	public void insertArticleVendu(ArticleVendu articleVendu) throws BusinessException {
		try (Connection connexion = ConnectionProvider.getConnection();
			PreparedStatement pstmtArticle = connexion.prepareStatement(INSERT_ARTICLE_VENDU, PreparedStatement.RETURN_GENERATED_KEYS);) {
				
			pstmtArticle.setString(1, articleVendu.getNomArticle());
			pstmtArticle.setString(2, articleVendu.getDescription());
			pstmtArticle.setDate(3, java.sql.Date.valueOf(articleVendu.getDateDebutEncheres()));
			pstmtArticle.setDate(4, java.sql.Date.valueOf(articleVendu.getDateFinEncheres()));
			pstmtArticle.setInt(5, articleVendu.getPrixInitial());
			pstmtArticle.setString(6, articleVendu.getEtatVente());
			pstmtArticle.setInt(7, articleVendu.getUtilisateur().getNoUtilisateur());
			pstmtArticle.setInt(8, articleVendu.getCategorie().getNoCategorie());
			pstmtArticle.executeUpdate();
			
			// Récupération du noArticle généré par l'insert précédent pour l'insérer avec les ImageUrl dans la table IMAGE
			ResultSet rs = pstmtArticle.getGeneratedKeys();
			if (rs.next()) {
				for (String imageUrl : articleVendu.getImageUrl()) {
					PreparedStatement pstmtImage = connexion.prepareStatement(INSERT_IMAGES_ARTICLE_VENDU);
					pstmtImage.setString(1, imageUrl);
					pstmtImage.setInt(2, rs.getInt(1));
					pstmtImage.executeUpdate();
					pstmtImage.close();
				}
			}
			rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException exception = new BusinessException();
			exception.ajouterErreur(CodesResultatDAL.INSERT_ARTICLE_VENDU_ERREUR);
			throw exception;
		}
	}

	@Override
	public void updateArticleVendu(ArticleVendu articleVendu) throws BusinessException {
		try (Connection connexion = ConnectionProvider.getConnection();
			PreparedStatement pstmtArticle = connexion.prepareStatement(UPDATE_ARTICLE_VENDU);
			PreparedStatement pstmtNumeroImage = connexion.prepareStatement(SELECT_IMAGES_ARTICLE_VENDU);) {
				
			pstmtArticle.setString(1, articleVendu.getNomArticle());
			pstmtArticle.setString(2, articleVendu.getDescription());
			pstmtArticle.setDate(3, java.sql.Date.valueOf(articleVendu.getDateDebutEncheres()));
			pstmtArticle.setDate(4, java.sql.Date.valueOf(articleVendu.getDateFinEncheres()));
			pstmtArticle.setInt(5, articleVendu.getPrixInitial());
			pstmtArticle.setString(6, articleVendu.getEtatVente());
			pstmtArticle.setInt(7, articleVendu.getCategorie().getNoCategorie());
			pstmtArticle.setInt(8, articleVendu.getNoArticle());
			pstmtArticle.executeUpdate();
			
			// Récupération du noImage de chaque ImageUrl déjà enregistré dans la BDD pour pouvoir les modifier
			pstmtNumeroImage.setInt(1, articleVendu.getNoArticle());
			ResultSet rs = pstmtNumeroImage.executeQuery();
			List<Integer> listeNoImage = new ArrayList<>();
			while (rs.next()) {
				listeNoImage.add(rs.getInt("no_image"));
			}
			rs.close();
			for (int i = 0; i < listeNoImage.size(); i++) {
				PreparedStatement pstmtImage = connexion.prepareStatement(UPDATE_IMAGES_ARTICLE_VENDU);
				pstmtImage.setString(1, articleVendu.getImageUrl().get(i));
				pstmtImage.setInt(2, listeNoImage.get(i));
				pstmtImage.executeUpdate();
				pstmtImage.close();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException exception = new BusinessException();
			exception.ajouterErreur(CodesResultatDAL.UPDATE_ARTICLE_VENDU_ERREUR);
			throw exception;
		}
	}
	
	// A ne pas utiliser. Privilégier l'utilisation de l'update avec etat_vente = annule
	@Override
	public void deleteArticleVendu(ArticleVendu articleVendu) throws BusinessException {
		try (Connection connexion = ConnectionProvider.getConnection();
			PreparedStatement pstmt = connexion.prepareStatement(DELETE_ARTICLE_VENDU);) {
				
			pstmt.setInt(1, articleVendu.getNoArticle());
			pstmt.executeUpdate();
				
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException exception = new BusinessException();
			exception.ajouterErreur(CodesResultatDAL.DELETE_ARTICLE_VENDU_ERREUR);
			throw exception;
		}
	}

	@Override
	public List<ArticleVendu> selectArticleVendu(Utilisateur utilisateur) throws BusinessException {
		List<ArticleVendu> listeArticleVendu = new ArrayList<>();
		
		try (Connection connexion = ConnectionProvider.getConnection();
			PreparedStatement pstmt = connexion.prepareStatement(SELECT_ARTICLE_VENDU_BY_UTILISATEUR);) {
			
			pstmt.setInt(1, utilisateur.getNoUtilisateur());
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				ArticleVendu articleVenduTemp = new ArticleVendu();
				Utilisateur utilisateurTemp = new Utilisateur();
				Categorie categorieTemp = new Categorie();
				
				articleVenduTemp.setNoArticle(rs.getInt("no_article"));
				articleVenduTemp.setNomArticle(rs.getString("nom_article"));
				articleVenduTemp.setDescription(rs.getString("description"));
				articleVenduTemp.setDateDebutEncheres(rs.getDate("date_debut_encheres").toLocalDate());
				articleVenduTemp.setDateFinEncheres(rs.getDate("date_fin_encheres").toLocalDate());
				articleVenduTemp.setPrixInitial(rs.getInt("prix_initial"));
				articleVenduTemp.setPrixVente(rs.getInt("prix_vente"));
				articleVenduTemp.setEtatVente(rs.getString("etat_vente"));
				articleVenduTemp.setImageUrl(listeImageBuilder(articleVenduTemp.getNoArticle()));
				
				utilisateurTemp.setNoUtilisateur(rs.getInt("no_utilisateur"));
				articleVenduTemp.setUtilisateur(utilisateurTemp);
				
				categorieTemp.setNoCategorie(rs.getInt("no_categorie"));
				articleVenduTemp.setCategorie(categorieTemp);
				
				listeArticleVendu.add(articleVenduTemp);
			}
			rs.close();
				
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException exception = new BusinessException();
			exception.ajouterErreur(CodesResultatDAL.SELECT_ARTICLE_VENDU_ERREUR);
			throw exception;
		}
		return listeArticleVendu;
	}

	// TODO Prévoir le cas où l'utilisateur entre un keyword et ne choisi pas de catégorie
	@Override
	public List<ArticleVendu> selectArticleVendu(Categorie categorie, String keyword) throws BusinessException {
		List<ArticleVendu> listeArticleVendu = new ArrayList<>();
		
		try (Connection connexion = ConnectionProvider.getConnection();) {
			PreparedStatement pstmt;
			ResultSet rs = null;
			
			// Cas où l'utilisateur n'a pas entré de "keyword" et a choisi "toutes les catégories"
			if (keyword == null || keyword.equals("") && categorie.getNoCategorie() == 0) {
				pstmt = connexion.prepareStatement(SELECT_ARTICLE_VENDU_BY_CATEGORIE);
				
				pstmt.setString(1, "no_categorie");
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					ArticleVendu articleVenduTemp = new ArticleVendu();
					Utilisateur utilisateurTemp = new Utilisateur();
					Categorie categorieTemp = new Categorie();
					
					articleVenduTemp.setNoArticle(rs.getInt("no_article"));
					articleVenduTemp.setNomArticle(rs.getString("nom_article"));
					articleVenduTemp.setDescription(rs.getString("description"));
					articleVenduTemp.setDateDebutEncheres(rs.getDate("date_debut_encheres").toLocalDate());
					articleVenduTemp.setDateFinEncheres(rs.getDate("date_fin_encheres").toLocalDate());
					articleVenduTemp.setPrixInitial(rs.getInt("prix_initial"));
					articleVenduTemp.setPrixVente(rs.getInt("prix_vente"));
					articleVenduTemp.setEtatVente(rs.getString("etat_vente"));
					articleVenduTemp.setImageUrl(listeImageBuilder(articleVenduTemp.getNoArticle()));
					
					utilisateurTemp.setNoUtilisateur(rs.getInt("no_utilisateur"));
					articleVenduTemp.setUtilisateur(utilisateurTemp);
					
					categorieTemp.setNoCategorie(rs.getInt("no_categorie"));
					articleVenduTemp.setCategorie(categorieTemp);
					
					listeArticleVendu.add(articleVenduTemp);
				}
				
			}
			// Cas où l'utilisateur n'a pas entré de "keyword" et a choisi une catégorie spécifique
			else if (keyword == null || keyword.equals("") && categorie.getNoCategorie() != 0) {
				pstmt = connexion.prepareStatement(SELECT_ARTICLE_VENDU_BY_CATEGORIE);
				
				pstmt.setInt(1, categorie.getNoCategorie());
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					ArticleVendu articleVenduTemp = new ArticleVendu();
					Utilisateur utilisateurTemp = new Utilisateur();
					Categorie categorieTemp = new Categorie();
					
					articleVenduTemp.setNoArticle(rs.getInt("no_article"));
					articleVenduTemp.setNomArticle(rs.getString("nom_article"));
					articleVenduTemp.setDescription(rs.getString("description"));
					articleVenduTemp.setDateDebutEncheres(rs.getDate("date_debut_encheres").toLocalDate());
					articleVenduTemp.setDateFinEncheres(rs.getDate("date_fin_encheres").toLocalDate());
					articleVenduTemp.setPrixInitial(rs.getInt("prix_initial"));
					articleVenduTemp.setPrixVente(rs.getInt("prix_vente"));
					articleVenduTemp.setEtatVente(rs.getString("etat_vente"));
					articleVenduTemp.setImageUrl(listeImageBuilder(articleVenduTemp.getNoArticle()));
					
					utilisateurTemp.setNoUtilisateur(rs.getInt("no_utilisateur"));
					articleVenduTemp.setUtilisateur(utilisateurTemp);
					
					categorieTemp.setNoCategorie(rs.getInt("no_categorie"));
					articleVenduTemp.setCategorie(categorieTemp);
					
					listeArticleVendu.add(articleVenduTemp);
				}
				
			}
			else {
				pstmt = connexion.prepareStatement(SELECT_ARTICLE_VENDU_BY_RECHERCHE);
				
				pstmt.setInt(1, categorie.getNoCategorie());
				pstmt.setString(2, keyword);
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					ArticleVendu articleVenduTemp = new ArticleVendu();
					Utilisateur utilisateurTemp = new Utilisateur();
					Categorie categorieTemp = new Categorie();
					
					articleVenduTemp.setNoArticle(rs.getInt("no_article"));
					articleVenduTemp.setNomArticle(rs.getString("nom_article"));
					articleVenduTemp.setDescription(rs.getString("description"));
					articleVenduTemp.setDateDebutEncheres(rs.getDate("date_debut_encheres").toLocalDate());
					articleVenduTemp.setDateFinEncheres(rs.getDate("date_fin_encheres").toLocalDate());
					articleVenduTemp.setPrixInitial(rs.getInt("prix_initial"));
					articleVenduTemp.setPrixVente(rs.getInt("prix_vente"));
					articleVenduTemp.setEtatVente(rs.getString("etat_vente"));
					articleVenduTemp.setImageUrl(listeImageBuilder(articleVenduTemp.getNoArticle()));
					
					utilisateurTemp.setNoUtilisateur(rs.getInt("no_utilisateur"));
					articleVenduTemp.setUtilisateur(utilisateurTemp);
					
					categorieTemp.setNoCategorie(rs.getInt("no_categorie"));
					articleVenduTemp.setCategorie(categorieTemp);
					
					listeArticleVendu.add(articleVenduTemp);
				}
				
			}
			
			rs.close();
			pstmt.close();
				
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException exception = new BusinessException();
			exception.ajouterErreur(CodesResultatDAL.SELECT_ARTICLE_VENDU_ERREUR);
			throw exception;
		}
		return listeArticleVendu;
	}
	
	
	private List<String> listeImageBuilder(int no_article) throws BusinessException {
		List<String> listeImage = new ArrayList<>();
		
		try (Connection connexion = ConnectionProvider.getConnection();
				PreparedStatement pstmt = connexion.prepareStatement("SELECT * FROM IMAGES WHERE noArticle = ?;");) {
					
				pstmt.setInt(1, no_article);
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					listeImage.add(rs.getString("chemin_url"));
				}
				rs.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
				BusinessException exception = new BusinessException();
				exception.ajouterErreur(CodesResultatDAL.SELECT_ARTICLE_VENDU_ERREUR);
				throw exception;
			}
		
		return listeImage;
	}
		
}
