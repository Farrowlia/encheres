package org.eni.encheres.authentification;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.eni.encheres.bo.Utilisateur;

/**
 * Controles de saisie
 * @author marieLaureV
 *
 */
public final class InscriptionForm {



	
    private String resultat;
	
	
	public String getResultat() {
		return resultat;
	}
	
		
		public Utilisateur inscrireUtilisateur(HttpServletRequest request) {
			 /* Récupération des champs du formulaire. */
			String pseudo = getValeurChamp(request, CHAMP_PSEUDO );
			String nom = getValeurChamp(request, CHAMP_NOM );
			String prenom = getValeurChamp(request, CHAMP_PRENOM );
			String email = getValeurChamp(request, CHAMP_EMAIL );
			String telephone = getValeurChamp(request, CHAMP_TELEPHONE );
			String rue = getValeurChamp(request, CHAMP_RUE );
			String codePostal = getValeurChamp(request, CHAMP_CP );
			String ville = getValeurChamp(request, CHAMP_VILLE );
			String motDePasse = getValeurChamp(request, CHAMP_PWD );
	        String confirmation = getValeurChamp(request, CHAMP_CONF ); 
	        Utilisateur user = new Utilisateur();

	        try {
	            validationEmail(email);
	            } catch (Exception ex) {
	                setErreur(CHAMP_EMAIL, ex.getMessage());
	            }
	        user.setEmail(email);
	        
	        try {
	        	validationMotsDePasse( motDePasse, confirmation );
			} catch (Exception ex) {
	            setErreur(CHAMP_PWD, ex.getMessage());
	            setErreur(CHAMP_CONF, null);
			}
	        user.setMotDePasse(motDePasse);
	        
	        try {
	        	validationTexte(pseudo);
	        	//TODO vérifier caractères alphanumériques et vérifier unicité
			} catch (Exception ex) {
	            setErreur(CHAMP_PSEUDO, ex.getMessage());
			}
	        user.setPseudo(pseudo);
	        
	        try {
	        	validationTelephone(telephone);
			} catch (Exception ex) {
	            setErreur(CHAMP_TELEPHONE, ex.getMessage());
			}
	        user.setTelephone(telephone);
	        
	        try {
	            validationCodePostal(codePostal);
	        } catch (Exception ex) {
	            setErreur(CHAMP_CP, ex.getMessage());
	        }
	        user.setCodePostal(codePostal);
	        
	        user.setNom(nom);
	        user.setPrenom(prenom);
	        user.setRue(rue);
	        user.setVille(ville);
	        
	        
	        //initialisation du résultat global de la validation
	        if (erreurs.isEmpty()) {
	        	resultat = "Inscription réussie";
	        	
	        } else {
	        	resultat = "Echec de l'inscription";
	        	
	        }
	        return user;
		}
	
	
		
		/**
		 * Valide les mots de passe saisis.
		 */
		private void validationMotsDePasse( String motDePasse, String confirmation ) throws Exception{
		    if (motDePasse != null && confirmation != null ) {
		        if (!motDePasse.equals(confirmation)) {
		            throw new Exception("Les mots de passe entrés sont différents, merci de les saisir à nouveau.");
		        } else if (motDePasse.trim().length() < 5) {
		            throw new Exception("Les mots de passe doivent contenir au moins 5 caractères.");
		        }
		    } else {
		        throw new Exception("Merci de saisir et confirmer votre mot de passe.");
		    }
		}

		/**
		 * Valide le texte saisi.
		 */
		private void validationTexte(String nomChamp) throws Exception {
		    if ( nomChamp != null && nomChamp.trim().length() < 3 ) {
		        throw new Exception( "Le champ " + nomChamp + " doit contenir au moins 3 caractères." );
		    }
		}
		
		/**
		 * valide le code postal saisi
		 * @param codePostal
		 * @throws Exception
		 */
		private void validationCodePostal(String codePostal) throws Exception {
			if (codePostal!=null && codePostal.trim().length()!=5 && !codePostal.matches("[0-9]")) {
				throw new Exception("Le format du code postal n'est pas correct");
			}
		}
		
		/**
		 * Valide le telephone saisi
		 * @param telephone
		 * @throws Exception
		 */
		private void validationTelephone(String telephone) throws Exception {
			if (telephone!=null && telephone.trim().length()!=10 && !telephone.matches("[0-9]")) {
				throw new Exception("Merci de saisir un numéro à 10 chiffres");
			}
		}
		

		
		private static String getValeurChamp(HttpServletRequest request, String champ) {
			String valeur = request.getParameter(champ);
			if(valeur == null || valeur.trim().length() == 0) {
				return null;
			}
			return valeur.trim();
		}
	
}
