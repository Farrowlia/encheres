package org.eni.encheres.dal.enchere;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.eni.encheres.bo.ArticleVendu;
import org.eni.encheres.bo.Categorie;
import org.eni.encheres.bo.Enchere;
import org.eni.encheres.bo.Utilisateur;
import org.eni.encheres.dal.CodesResultatDAL;
import org.eni.encheres.dal.ConnectionProvider;
import org.eni.encheres.dal.enchere.EnchereDAO;
import org.eni.encheres.erreur.BusinessException;

/**
 * 
 * @author aleroy2020
 *
 */

public class EnchereDAOJdbcImpl implements EnchereDAO {
	
	private static final String INSERT_ENCHERE = "INSERT INTO ENCHERES (no_utilisateur, no_article, date_enchere, montant_enchere) values (?, ?, ?, ?);";
	private static final String SELECT_ENCHERE_BY_UTILISATEUR_OR_ARTICLEVENDU =	"SELECT * FROM ENCHERES AS e " +
																				"INNER JOIN ARTICLES_VENDUS AS a ON a.no_article = e.no_article " +
																				"INNER JOIN CATEGORIES AS c ON a.no_categorie = c.no_categorie " +
																				"INNER JOIN UTILISATEUR AS u ON u.no_utilisateur = a.no_utilisateur " +
																				"WHERE no_utilisateur = ? OR no_article = ?;"; 
	
	/**
	 * @param int noUtilisateur, int noArticle, date dateEnchere, int montantEnchere																
	 */
	@Override
	public void insertEnchere(Enchere enchere) throws BusinessException {
		try (Connection connexion = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = connexion.prepareStatement(INSERT_ENCHERE); 
			
			int index = 1;
			pstmt.setInt(index++, enchere.getUtilisateur().getNoUtilisateur());
			pstmt.setInt(index++, enchere.getArticleVendu().getNoArticle());
			pstmt.setDate(index++, java.sql.Date.valueOf(enchere.getDateEnchere()));
			pstmt.setInt(index++, enchere.getMontanEnchere());
			pstmt.executeUpdate();
			pstmt.close();
			
		} catch (SQLException ex) {
			ex.printStackTrace();
			BusinessException exception = new BusinessException();
			exception.ajouterErreur(CodesResultatDAL.INSERT_ENCHERE_ERREUR);
		}
	}

	/**
	 * @param int noUtilisateur, date dateEnchere, int montantEnchere
	 * @throws BusinessException
	 * @return listeEncheres
	 */
	@Override
	public List<Enchere> selectEncheres(Utilisateur utilisateur) throws BusinessException {
		List<Enchere> listeEncheres = new ArrayList<Enchere>();
		
		try (Connection connexion = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = connexion.prepareStatement(SELECT_ENCHERE_BY_UTILISATEUR_OR_ARTICLEVENDU); 
			pstmt.setInt(1, utilisateur.getNoUtilisateur());
			ResultSet rs = pstmt.executeQuery();
			
			// TODO la classe utilisateur doit implementer un constructeur avec 100% des variables
			while(rs.next())
			{
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
															
				listeEncheres.add(new Enchere(rs.getDate("date_enchere").toLocalDate(), 
												rs.getInt("montant_enchere"), utilisateur, articleVendu));
			}
			rs.close();
			pstmt.close();
			
		} catch (SQLException ex) {
			ex.printStackTrace();
			BusinessException exception = new BusinessException();
			exception.ajouterErreur(CodesResultatDAL.SELECT_ENCHERE_ERREUR);
		}
		return listeEncheres;
	}

	/**
	 * @param int noArticle, date dateEnchere, int montantEnchere
	 * @throws BusinessException
	 * @return listeEncheres
	 */
	@Override
	public List<Enchere> selectEncheres(ArticleVendu articleVendu) throws BusinessException {
		List<Enchere> listeEncheres = new ArrayList<Enchere>();
		
		try (Connection connexion = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = connexion.prepareStatement(SELECT_ENCHERE_BY_UTILISATEUR_OR_ARTICLEVENDU);
			pstmt.setInt(1, articleVendu.getNoArticle());
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next())
			{
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
				
															
				listeEncheres.add(new Enchere(rs.getDate("date_enchere").toLocalDate(), 
												rs.getInt("montant_enchere"), utilisateur, articleVendu));
			}
			rs.close();
			pstmt.close();
			
		} catch (SQLException ex) {
			ex.printStackTrace();
			BusinessException exception = new BusinessException();
			exception.ajouterErreur(CodesResultatDAL.SELECT_ENCHERE_ERREUR);
		}
		
		
		return listeEncheres;
	}
	
	public Enchere derniereEnchere(ArticleVendu articleVendu) {
		
	}

	
	
	
}
