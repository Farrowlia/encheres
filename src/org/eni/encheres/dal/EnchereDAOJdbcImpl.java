package org.eni.encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import org.eni.encheres.bo.ArticleVendu;
import org.eni.encheres.bo.Enchere;
import org.eni.encheres.bo.Utilisateur;
import org.eni.encheres.erreur.BusinessException;

/**
 * 
 * @author aleroy2020
 *
 */

public class EnchereDAOJdbcImpl implements EnchereDAO {
	
	private static final String INSERT_ENCHERE = "INSERT INTO ENCHERES (date_enchere, montant_enchere) values (?, ?);";
	
	private static final String SELECT_ENCHERE_BY_UTILISATEUR =	"SELECT" + 
																" e.date as date_enchere, " +
																" e.montant as montant_enchere,	" +
																" e.utilisateur as no_utilisateur, " +
																" u.pseudo" +
																"FROM" +
																"ENCHERES e" + 
																" INNER JOIN UTILISATEURS as ON e.utilisateur = u.no_utilisateur" + 
																" WHERE e.utilisateur = ?" + 
																" ORDER BY e.utilisateur, u.pseudo, e.date, e.montant; ";
	
	private static final String SELECT_ENCHERE_BY_ARTICLEVENDU =	"SELECT" +
																	" e.date as date_enchere, " +
																	" e.montant as montant_enchere,	" +
																	" e.numarticle as a.no_article, " +
																	" a.nom as a.nom_article" +
																	"FROM" +
																	"ENCHERES e" +
																	" INNER JOIN ARTICLES_VENDUS as ON e.numarticle = a.no_article" + 
																	" WHERE e.numarticle = ?" +
																	" ORDER BY e.numarticle, a.nom, e.date, e.montant; ";
	/**
	 * @param date dateEnchere, int montantEnchere																
	 */
	@Override
	public void insertEnchere(Enchere enchere) throws BusinessException {
		try (Connection connexion = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = connexion.prepareStatement(INSERT_ENCHERE); 			
			pstmt.setDate(1, java.sql.Date.valueOf(enchere.getDateEnchere()));
			pstmt.setInt(2, enchere.getMontanEnchere());
			pstmt.executeUpdate();
			pstmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException exception = new BusinessException();
			exception.ajouterErreur(CodesResultatDAL.INSERT_ENCHERE_ERREUR);
		}
		
	}

	/**
	 * @param int noUtilisateur, String pseudo, date dateEnchere, int montantEnchere
	 */
	@Override
	public List<Enchere> selectEnchere(Utilisateur utilisateur) throws BusinessException {
		List<Enchere> listeEncheres = new ArrayList<Enchere>();
		
		try (Connection connexion = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = connexion.prepareStatement(SELECT_ENCHERE_BY_UTILISATEUR); 
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				//une méthode enchereBuilder séparée serait elle plus appropriée ?
				//je n'utilise pas getUtilisateur et getArticleVendu de la classe Enchere
				listeEncheres.add(new Enchere(rs.getInt("no_utilisateur"), rs.getString("pseudo"), 
						rs.getDate("date_enchere"), rs.getInt("montant_enchere")));
			}
			rs.close();
			pstmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException exception = new BusinessException();
			exception.ajouterErreur(CodesResultatDAL.SELECT_ENCHERE_ERREUR);
		}
		
		
		return listeEncheres;
	}

	/**
	 * @param int noArticle, String nomArticle, date dateEnchere, int montantEnchere
	 * @throws BusinessException
	 * @return listeEncheres
	 */
	@Override
	public List<Enchere> selectEnchere(ArticleVendu articleVendu) throws BusinessException {
		List<Enchere> listeEncheres = new ArrayList<Enchere>();
		
		try (Connection connexion = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = connexion.prepareStatement(SELECT_ENCHERE_BY_ARTICLEVENDU);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				listeEncheres.add(new Enchere(rs.getInt("no_article"), rs.getString("nom_article"), 
						rs.getDate("date_enchere"), rs.getInt("montant_enchere")));
			}
			rs.close();
			pstmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException exception = new BusinessException();
			exception.ajouterErreur(CodesResultatDAL.SELECT_ENCHERE_ERREUR);
		}
		
		
		return listeEncheres;
	}
}
