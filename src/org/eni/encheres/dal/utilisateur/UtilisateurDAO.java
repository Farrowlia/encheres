package org.eni.encheres.dal.utilisateur;

import java.util.List;

import org.eni.encheres.bo.Utilisateur;
import org.eni.encheres.erreur.BusinessException;

public interface UtilisateurDAO {
	public void createOrUpdateUtilisateur(Utilisateur utilisateur) throws BusinessException;
	public void deleteUtilisateur(int noUtilisateur) throws BusinessException;
	public Utilisateur selectByPseudo(String pseudo) throws BusinessException;
	public Utilisateur selectByNom(String nom) throws BusinessException;
	public Utilisateur selectById(int noUtilisateur) throws BusinessException;
	public List<Utilisateur> selectAll() throws BusinessException;
	Utilisateur selectByEmail(String email) throws BusinessException;
	public Utilisateur selectByEmailOrPseudo(String emailOuPseudo) throws BusinessException;
	void updatePassword(int noUtilisateur, String pwd) throws BusinessException;
}
