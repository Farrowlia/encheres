package org.eni.encheres.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import org.eni.encheres.bll.UtilisateurManager;
import org.eni.encheres.bo.Utilisateur;
import org.eni.encheres.erreur.BusinessException;
import org.glassfish.jersey.message.internal.MediaTypes;

@Path("/utilisateur")
public class UtilisateurController {
	
	/**
	 * Bouton connexion ou clic sur lien du pseudo
	 * @param id
	 * @return l'utilisateur de l'id
	 * @throws BusinessException
	 */
	@GET
	@Path("/{id:\\d+}")
	public Utilisateur voirProfil(@PathParam("id") int id) throws BusinessException {
		UtilisateurManager um = new UtilisateurManager();
		return um.voirProfil(id);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	/**
	 * BOUTON Créer ou Enregistrer
	 * Enregistre un profil, nouveau ou existant
	 * @param utilisateur (formulaire)
	 * @throws BusinessException
	 */
	public void enregistrerProfil(@FormParam("paramProfil") Utilisateur utilisateur) throws BusinessException {
		UtilisateurManager um = new UtilisateurManager();
		um.saveNewOrExistingCompte(utilisateur);
	}
	
//	/**
//	 * Bouton Supprimer mon compte
//	 * @param id
//	 * @throws BusinessException
//	 */
//	@DELETE
//	@Path("/{id:\\d+}")
//	public void supprimerProfil(@PathParam("id") int id) throws BusinessException {
//		UtilisateurManager um = new UtilisateurManager();
//		um.deleteCompte(id);
//	}
}
