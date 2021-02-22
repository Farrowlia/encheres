package org.eni.encheres.dal.enchere;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.eni.encheres.bo.ArticleVendu;
import org.eni.encheres.bo.Categorie;
import org.eni.encheres.bo.Enchere;
import org.eni.encheres.bo.Utilisateur;
import org.eni.encheres.dal.ConnectionProvider;
import org.eni.encheres.erreur.BusinessException;


public class EnchereDAOJdbcImpl implements EnchereDAO {
	
	private static final String INSERT_ENCHERE = "INSERT INTO ENCHERES (date_enchere, montant_enchere, no_utilisateur, no_article) values (?, ?, ?, ?);";
	private static final String SELECT_ENCHERE_BY_ARTICLE_VENDU = "SELECT * FROM ENCHERES AS ench INNER JOIN ARTICLES_VENDUS AS art ON ench.no_article = art.no_article INNER JOIN UTILISATEURS AS user ON ench.no_utilisateur = user.no_utilisateur WHERE art.no_article = ? ORDER BY montant_enchere DESC;";
	private static final String SELECT_ENCHERE_BY_UTILISATEUR = "SELECT * FROM ENCHERES AS ench INNER JOIN ARTICLES_VENDUS AS art ON ench.no_article = art.no_article INNER JOIN CATEGORIES AS car ON art.no_categorie = cat.no_categorie INNER JOIN UTILISATEURS AS user ON art.no_utilisateur = user.no_utilisateur WHERE user.no_utilisateur = ? ORDER BY date_enchere DESC;";
//	private static final String SELECT_ENCHERE_BY_UTILISATEUR_OR_ARTICLEVENDU =	"SELECT * FROM ENCHERES AS e " +
//																				"INNER JOIN ARTICLES_VENDUS AS a ON a.no_article = e.no_article " +
//																				"INNER JOIN CATEGORIES AS c ON a.no_categorie = c.no_categorie " +
//																				"INNER JOIN UTILISATEUR AS u ON u.no_utilisateur = a.no_utilisateur " +
//																				"WHERE no_utilisateur = ? OR no_article = ?;"; 
	
	@Override
	public void insertEnchere(Enchere enchere) throws BusinessException {
		try (Connection connexion = ConnectionProvider.getConnection();
			PreparedStatement pstmt = connexion.prepareStatement(INSERT_ENCHERE);) {
			
			
			int index = 1;
			pstmt.setDate(index++, java.sql.Date.valueOf(enchere.getDateEnchere()));
			pstmt.setInt(index++, enchere.getMontanEnchere());
			pstmt.setInt(index++, enchere.getUtilisateur().getNoUtilisateur());
			pstmt.setInt(index++, enchere.getArticleVendu().getNoArticle());
			pstmt.executeUpdate();
			
		} catch (SQLException ex) {
			ex.printStackTrace();
			BusinessException exception = new BusinessException();
			exception.ajouterErreur(CodesResultatEnchereDAL.INSERT_ENCHERE_ERREUR);
		}
	}

	@Override
	public List<Enchere> selectEnchere(ArticleVendu articleVendu) throws BusinessException {
		List<Enchere> listeEncheres = new ArrayList<Enchere>();
		
		try (Connection connexion = ConnectionProvider.getConnection();
			PreparedStatement pstmt = connexion.prepareStatement(SELECT_ENCHERE_BY_ARTICLE_VENDU);) {
			
			pstmt.setInt(1, articleVendu.getNoArticle());
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Utilisateur utilisateur = new Utilisateur(rs.getInt("no_utilisateur"),
															rs.getString("pseudo"),
															rs.getString("nom"),
															rs.getString("prenom"),
															rs.getString("email"),
															rs.getString("telephone"),
															rs.getString("rue"),
															rs.getString("code_postal"),
															rs.getString("ville"),
															rs.getString("mot_de_passe"),
															rs.getInt("credit"),
															rs.getBoolean("administrateur"),
															rs.getBoolean("compte_actif"));
															
				listeEncheres.add(new Enchere(rs.getDate("date_enchere").toLocalDate(), rs.getInt("montant_enchere"), utilisateur, articleVendu));
			}
			
			rs.close();
			
		} catch (SQLException ex) {
			ex.printStackTrace();
			BusinessException exception = new BusinessException();
			exception.ajouterErreur(CodesResultatEnchereDAL.SELECT_ENCHERE_ERREUR);
		}
		return listeEncheres;
	}
	
	@Override
	public List<Enchere> selectEnchere(Utilisateur utilisateur) throws BusinessException {
		List<Enchere> listeEncheres = new ArrayList<Enchere>();
		
		try (Connection connexion = ConnectionProvider.getConnection();
			PreparedStatement pstmt = connexion.prepareStatement(SELECT_ENCHERE_BY_UTILISATEUR);) {
			 
			pstmt.setInt(1, utilisateur.getNoUtilisateur());
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Categorie categorie = new Categorie(rs.getInt("no_categorie"), rs.getString("libelle"));
				
				Utilisateur vendeur = new Utilisateur(rs.getInt("no_utilisateur"),
													rs.getString("pseudo"),
													rs.getString("nom"),
													rs.getString("prenom"),
													rs.getString("email"),
													rs.getString("telephone"),
													rs.getString("rue"),
													rs.getString("code_postal"),
													rs.getString("ville"),
													rs.getString("mot_de_passe"),
													rs.getInt("credit"),
													rs.getBoolean("administrateur"),
													rs.getBoolean("compte_actif"));
				
				ArticleVendu articleVendu = new ArticleVendu(rs.getInt("no_article"),
															rs.getString("nom_article"),
															rs.getString("description"),
															rs.getDate("date_debut_encheres").toLocalDate(),
															rs.getDate("date_fin_encheres").toLocalDate(),
															rs.getInt("prix_initial"),
															rs.getInt("prix_vente"),
															rs.getString("etatVente"),
															vendeur, categorie);
															
				listeEncheres.add(new Enchere(rs.getDate("date_enchere").toLocalDate(), rs.getInt("montant_enchere"), utilisateur, articleVendu));
			}
			rs.close();
			
		} catch (SQLException ex) {
			ex.printStackTrace();
			BusinessException exception = new BusinessException();
			exception.ajouterErreur(CodesResultatEnchereDAL.SELECT_ENCHERE_ERREUR);
		}
		return listeEncheres;
	}
	
}
