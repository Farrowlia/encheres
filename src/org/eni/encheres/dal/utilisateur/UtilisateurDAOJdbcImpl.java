package org.eni.encheres.dal.utilisateur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.eni.encheres.bo.Utilisateur;
import org.eni.encheres.dal.CodesResultatDAL;
import org.eni.encheres.dal.ConnectionProvider;
import org.eni.encheres.dal.Criteres;
import org.eni.encheres.erreur.BusinessException;

public class UtilisateurDAOJdbcImpl implements UtilisateurDAO {
	
	
	private static final String INSERT_USER = "INSERT INTO utilisateurs (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur, compte_actif) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String SELECT_ALL= "SELECT * FROM utilisateurs";
	private static final String SELECT_BY_CRITERIA = SELECT_ALL + "WHERE ? = ?";
	private static final String SELECT_BY_NOM_OR_PSEUDO = "SELECT * FROM utilisateurs WHERE pseudo = ? OR email = ?";
	private static final String SELECT_BY_ID = SELECT_ALL + "WHERE no_utilisateur = ?";
	private static final String DELETE_USER = "DELETE FROM utilisateurs WHERE no_utilisateur = ?";
	private static final String UPDATE_USER = "UPDATE utilisateurs SET pseudo = ?, nom = ?, prenom = ?, email = ?, telephone = ?, rue = ?, code_postal = ?, ville = ?, mot_de_passe = ?, credit=?, administrateur=?, compte_actif=? WHERE no_utilisateur = ?";
	private static final String UPDATE_PASSWORD = "UPDATE utilisateurs SET mot_de_passe = ? WHERE no_utilisateur = ?" ;


	/**
	 * @apiNote va créer une nouvelle ligne dans la BDD (avec new noUtilisateur) ou mettre à jour la BDD 
	 * @param Utilisateur (récupère tous les attributs)
	 * @return void
	 * @throws BusinessException
	 */
	@Override
	public void createOrUpdateUtilisateur(Utilisateur utilisateur) throws BusinessException {
		if (utilisateur == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_NULL);
		}
		boolean userExists = isUserExists(utilisateur.getNoUtilisateur());
		
		try (Connection connexion = ConnectionProvider.getConnection()) {
			
			
			String requete = userExists ? UPDATE_USER : INSERT_USER;
			PreparedStatement pstmt = connexion.prepareStatement(requete);
			
			
			int index = 1;
			pstmt.setString(index++, utilisateur.getPseudo());
			pstmt.setString(index++, utilisateur.getNom());
			pstmt.setString(index++, utilisateur.getPrenom());
			pstmt.setString(index++, utilisateur.getEmail());
			pstmt.setString(index++, utilisateur.getTelephone());
			pstmt.setString(index++, utilisateur.getRue());
			pstmt.setString(index++, utilisateur.getCodePostal());
			pstmt.setString(index++, utilisateur.getVille());
			pstmt.setString(index++, utilisateur.getMotDePasse());
			pstmt.setInt(index++, utilisateur.getCredit());
			pstmt.setBoolean(index++, utilisateur.isAdministrateur());
			pstmt.setBoolean(index++, utilisateur.isCompteActif());
			
			// si c'est une INSERT :
			if (!userExists) {
			pstmt.setInt(index++, utilisateur.getNoUtilisateur());
			}
			
			pstmt.executeUpdate();
			
			pstmt.close();
				
		} catch (SQLException ex) {
			ex.printStackTrace();
			BusinessException exception = new BusinessException();
			int codeErreur = userExists ? CodesResultatDAL.UPDATE_UTILISATEUR_ERREUR : CodesResultatDAL.INSERT_UTILISATEUR_ERREUR;
			exception.ajouterErreur(codeErreur);
			throw exception;
		}
	}

	@Override
	public void deleteUtilisateur(int noUtilisateur) throws BusinessException {
		try (Connection connexion = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = connexion.prepareStatement(DELETE_USER);
			
			pstmt.setInt(1, noUtilisateur);
			pstmt.executeUpdate();
			
			pstmt.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
			BusinessException exception = new BusinessException();
			exception.ajouterErreur(CodesResultatDAL.DELETE_UTILISATEUR_ERREUR);
			throw exception;
		}
	}
	
	@Override
	public Utilisateur selectById(int noUtilisateur) throws BusinessException {
		Utilisateur utilisateur = null;
		try (Connection connexion = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = connexion.prepareStatement(SELECT_BY_ID);
			pstmt.setInt(1, noUtilisateur);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				utilisateur = map(rs);
			}
			
			rs.close();
			pstmt.close();
			
		} catch (SQLException ex) {
			ex.printStackTrace();
			BusinessException exception = new BusinessException();
			exception.ajouterErreur(CodesResultatDAL.SELECT_UTILISATEUR_ERREUR);
			throw exception;
		}
		return utilisateur;
	}
	
	
	
	/**
	 * @author marieLaure
	 * SE CONNECTER
	 * récupérer le compte utilisateur par le pseudo ou le nom
	 * @return utilisateur
	 */
	@Override
	public Utilisateur selectByNomOrPseudo(String nomOuPseudo) throws BusinessException {
		Utilisateur utilisateur = null;
		try (Connection connexion = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = connexion.prepareStatement(SELECT_BY_NOM_OR_PSEUDO);
			pstmt.setString(1, nomOuPseudo);
			pstmt.setString(2, nomOuPseudo);
			
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				utilisateur = map(rs);
			}
			
			rs.close();
			pstmt.close();
			
		} catch (SQLException ex) {
			ex.printStackTrace();
			BusinessException exception = new BusinessException();
			exception.ajouterErreur(CodesResultatDAL.SELECT_UTILISATEUR_ERREUR);
			throw exception;
		}
		
		return utilisateur;
	}


	private Utilisateur map(ResultSet rs) throws SQLException {
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setNom(rs.getString("noUtilisateur"));
		utilisateur.setPrenom(rs.getString("pseudo"));
		utilisateur.setEmail(rs.getString("prenom"));
		utilisateur.setTelephone(rs.getString("email"));
		utilisateur.setRue(rs.getString("telephone"));
		utilisateur.setCodePostal(rs.getString("rue"));
		utilisateur.setVille(rs.getString("code_postal"));
		utilisateur.setMotDePasse(rs.getString("email"));
		utilisateur.setCredit(rs.getInt("credit"));
		utilisateur.setAdministrateur(rs.getBoolean("administrateur"));
		utilisateur.setCompteActif(rs.getBoolean("compte_actif"));
		return utilisateur;
	}

	@Override
	public Utilisateur selectByPseudo(String pseudo) throws BusinessException {
		return selectByCriteria(Criteres.PSEUDO, pseudo);
	}
	
	@Override
	public Utilisateur selectByEmail(String email) throws BusinessException {
		return selectByCriteria(Criteres.EMAIL, email);
	}
	
	/**
	 * Methode generale pour rechercher par critère
	 * @param critere (nom de la colonne, indiqué dans l'énumération)
	 * @param value (valeur recherchée)
	 * @return utilisateur
	 * @throws BusinessException
	 */
	private Utilisateur selectByCriteria(Criteres critere, String value) throws BusinessException {
		Utilisateur utilisateur = null;
		try (Connection connexion = ConnectionProvider.getConnection();
				PreparedStatement pstmt = connexion.prepareStatement(SELECT_BY_CRITERIA);) {
			//definir nom colonne (critere)
			pstmt.setString(1, critere.getNomColonne());
			//definir nom de la valeur � chercher
			pstmt.setString(2, value);
			
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				utilisateur = map(rs);
			}
			
			rs.close();
			pstmt.close();
			
		} catch (SQLException ex) {
			ex.printStackTrace();
			BusinessException exception = new BusinessException();
			exception.ajouterErreur(CodesResultatDAL.SELECT_UTILISATEUR_ERREUR);
			throw exception;
		}
		
		
		return utilisateur;
		
	}

	//les méthodes suivantes ne sont pas utilisées pour l'instant
	
	@Override
	public List<Utilisateur> selectAll() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Utilisateur selectByNom(String nom) throws BusinessException {
		return selectByCriteria(Criteres.NOM, nom);
	}



	@Override
	/**
	 * Mise à jour du mot de passe après demande de réinitialisation
	 */
	public void updatePassword(int noUtilisateur, String pwd) throws BusinessException {

		try (Connection connexion = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = connexion.prepareStatement(UPDATE_PASSWORD);
			
			if (pwd == null) {
				BusinessException businessException = new BusinessException();
				businessException.ajouterErreur(CodesResultatDAL.ERREUR_PASSWORD_NULL);
			}
			
			pstmt.setString(1, pwd);
			pstmt.setInt(2, noUtilisateur);
			pstmt.executeUpdate();
			
			pstmt.close();
				
		} catch (SQLException ex) {
			ex.printStackTrace();
			BusinessException exception = new BusinessException();
			exception.ajouterErreur(CodesResultatDAL.UPDATE_PASSWORD_ERREUR);
			throw exception;
		}
	}
	
	/**
	 * @apiNote cette méthode vérifie si l'utilisateur existe déjà
	 * @param noUtilisateur
	 * @return booléen
	 */
	public boolean isUserExists(int noUtilisateur) {
		try {
			selectById(noUtilisateur);
			return true;
		} catch (BusinessException ex) {
			return false;
		}
	}
	
}