package org.eni.encheres.dal.utilisateur;

import java.sql.SQLException;
import java.util.List;

import org.eni.encheres.bo.Utilisateur;

public interface UtilisateurDAO {
	public void createOrUpdateUtilisateur(Utilisateur utilisateur) throws SQLException;
	public void deleteUtilisateur(Utilisateur utilisateur) throws SQLException;
	public Utilisateur selectByPseudo(String pseudo) throws SQLException;
	public Utilisateur selectByNom(String nom) throws SQLException;
	public Utilisateur selectById(int noUtilisateur) throws SQLException;
	public List<Utilisateur> selectAll() throws SQLException;
	Utilisateur selectByEmail(String email) throws SQLException;
	public Utilisateur selectByEmailOrPseudo(String emailOuPseudo) throws SQLException;
	void updatePassword(int noUtilisateur, String pwd) throws SQLException;
}
