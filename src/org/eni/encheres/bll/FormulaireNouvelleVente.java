package org.eni.encheres.bll;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.eni.encheres.bo.ArticleVendu;
import org.eni.encheres.bo.Categorie;
import org.eni.encheres.bo.Image;
import org.eni.encheres.bo.Retrait;
import org.eni.encheres.bo.Utilisateur;

public class FormulaireNouvelleVente {


	private static final String CHAMP_ARTICLE			= "nomArticle";
	private static final String	CHAMP_DESCRIPTION		= "description";
	private static final String CHAMP_CATEGORIE			= "categorie";
	private static final String CHAMP_PHOTO				= "cheminUrl";
	private static final String CHAMP_PRIX				= "prixInitial";
	private static final String CHAMP_DEBUT_ENCHERE		= "dateDebutEncheres";
	private static final String CHAMP_FIN_ENCHERE		= "dateFinEncheres";
	private static final String CHAMP_RUE				= "rue";
	private static final String CHAMP_CODE_POSTAL		= "codePostal";
	private static final String CHAMP_VILLE				= "ville";
	
	
	
	public ArticleVendu creerArticle(HttpServletRequest request) {
		
		Categorie categorie = new Categorie();
		
		categorie.setNoCategorie(Integer.parseInt(getValeurChamp(request, CHAMP_CATEGORIE)));
		
		String debutEnchere = getValeurChamp(request, CHAMP_DEBUT_ENCHERE);
		String finEnchere = getValeurChamp(request, CHAMP_FIN_ENCHERE);
        
        HttpSession session = request.getSession();
        Utilisateur utilisateur = (Utilisateur)session.getAttribute("sessionUtilisateur");
		
        ArticleVendu articleVendu = new ArticleVendu();
        
        articleVendu.setNomArticle(getValeurChamp( request, CHAMP_ARTICLE));
        articleVendu.setDescription(getValeurChamp(request, CHAMP_DESCRIPTION));
        articleVendu.setDateDebutEncheres(LocalDate.parse(debutEnchere));
        articleVendu.setDateFinEncheres(LocalDate.parse(finEnchere));
        articleVendu.setPrixInitial(Integer.parseInt(getValeurChamp(request, CHAMP_PRIX)));
        articleVendu.setCategorie(categorie);
        articleVendu.setUtilisateur(utilisateur);
        
		return articleVendu;
	}

	 public Retrait creerRetrait(HttpServletRequest request) {
		 
		 Retrait retrait = new Retrait();
		 
		 retrait.setRue(getValeurChamp(request, CHAMP_RUE));
		 retrait.setCodePostal(getValeurChamp(request, CHAMP_CODE_POSTAL));
		 retrait.setVille(getValeurChamp(request, CHAMP_VILLE));
		 
		 return retrait;
	 }
	
	 
	 public Image loadImage(HttpServletRequest request) {
		 
		 Image image = new Image();
		 
		 image.setCheminUrl(getValeurChamp(request, CHAMP_PHOTO));
		 
		 return image;
	 }
	 
	private  static String getValeurChamp(HttpServletRequest request, String nomChamp) {
		
		String valeur = request.getParameter(nomChamp);
		if(valeur == null || valeur.trim().length()==0) {
			return null;
		} else {
			return valeur;
		}
	}
}
