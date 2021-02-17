package org.eni.encheres.bo;

import java.sql.Date;
import java.time.LocalDate;

/**
 * 
 * @author aleroy2020
 *
 */
public class Enchere {
	
	private LocalDate dateEnchere;
	private int montantEnchere;
	private Utilisateur utilisateur;
	private ArticleVendu articleVendu;
	
	
	public Enchere() {
	}
	
	//TODO vérifier la nécessiré de ce constructeur pour EnchereDAOJdbcImpl et les méthodes 
	//SELECT BY UTILISATEUR et SELECT BY ARTICLEVENDU
	public Enchere(int numero, String nom, Date date, int montantEnchere) {
	}

	public LocalDate getDateEnchere() {
		return dateEnchere;
	}
	public void setDateEnchere(LocalDate dateEnchere) {
		this.dateEnchere = dateEnchere;
	}


	public int getMontanEnchere() {
		return montantEnchere;
	}
	public void setMontantEnchere(int montantEnchere) {
		this.montantEnchere = montantEnchere;
	}


	public Utilisateur getUtilisateur() {
		return utilisateur;
	}
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}


	public ArticleVendu getArticleVendu() {
		return articleVendu;
	}
	public void setArticleVendu(ArticleVendu articleVendu) {
		this.articleVendu = articleVendu;
	}

}
