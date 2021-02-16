package org.eni.encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.eni.encheres.bo.Utilisateur;
import org.eni.encheres.erreur.BusinessException;

public class UtilisateurDAOJdbcImpl implements UtilisateurDAO {
	
	
	private static final String INSERT_UTILISATEUR = "INSERT INTO utilisateurs (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String SELECT_BY_CRITERIA = "SELECT * FROM utilisateurs WHERE ? LIKE ?";

	@Override
	public void insertUtilisateur(Utilisateur utilisateur) throws BusinessException {
		try (Connection connexion = ConnectionProvider.getConnection();
				PreparedStatement pstmt = connexion.prepareStatement(INSERT_UTILISATEUR);) {
			
			if (utilisateur == null) {
				BusinessException businessException = new BusinessException();
				businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_NULL);
			}
			
			int index = 1;
			pstmt.setString(index++, utilisateur.getNom());
			pstmt.setString(index++, utilisateur.getPrenom());
			pstmt.setString(index++, utilisateur.getEmail());
			pstmt.setString(index++, utilisateur.getTelephone());
			pstmt.setString(index++, utilisateur.getRue());
			pstmt.setString(index++, utilisateur.getCodePostal());
			pstmt.setString(index++, utilisateur.getVille());
			pstmt.setString(index++, utilisateur.getMotDePasse());
			pstmt.setInt(index++, utilisateur.getCredit());
			pstmt.setBoolean(index++, utilisateur.getAdministrateur());
			
			pstmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
				BusinessException exception = new BusinessException();
				exception.ajouterErreur(CodesResultatDAL.INSERT_UTILISATEUR_ERREUR);
				throw exception;
			}
		}


	@Override
	public void updateUtilisateur(Utilisateur utilisateur) throws BusinessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteUtilisateur(int noUtilisateur) throws BusinessException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public Utilisateur selectById(int noUtilisateur) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Utilisateur> selectAll() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Utilisateur selectByPseudo(String pseudo) throws BusinessException {
		return selectByCriteria(Criteres.PSEUDO, pseudo);
	}

	@Override
	public Utilisateur selectByNom(String nom) throws BusinessException {
		return selectByCriteria(Criteres.NOM, nom);
	}
	@Override
	@Override
	public Utilisateur selectByEmail(String email) throws BusinessException {
		return selectByCriteria(Criteres.EMAIL, email);
	}


	private Utilisateur selectByCriteria(Criteres critere, String value) throws BusinessException {
		Utilisateur utilisateur = null;
		try (Connection connexion = ConnectionProvider.getConnection();
			PreparedStatement pstmt = connexion.prepareStatement(SELECT_BY_CRITERIA);) {
			//definir nom colonne (critere)
			pstmt.setString(1, critere.getNomColonne());
			//definir nom de la valeur à chercher
			pstmt.setString(2, value);
			
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				utilisateur = map(rs);
			}

			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return utilisateur;
		
	}


	private Utilisateur map(ResultSet rs) {
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setNom(rs.getString("noUtilisateur"));
		utilisateur.setPrenom(rs.getString("pseudo"));
		utilisateur.setEmail(rs.getString("prenom"));
		utilisateur.setTelephone(rs.getString("email"));
		utilisateur.setRue(rs.getString("telephone"));
		utilisateur.setCodePostal(rs.getString("rue"));
		utilisateur.setVille(rs.getString("code_postal"));
		utilisateur.setMotDePasse(rs.getString("email"));
		utilisateur.setCredit();
		utilisateur.setAdministrateur();
		return null;
	}
}
