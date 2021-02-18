package org.eni.encheres.controleur;

import javax.websocket.server.PathParam;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;

import org.eni.encheres.bll.UtilisateurManager;
import org.eni.encheres.bo.Utilisateur;
import org.eni.encheres.erreur.BusinessException;

@Path("/utilisateur")
public class UtilisateurControleur {
	
	@GET
	@Path("/{id:\\d+}")
	public Utilisateur voirProfil(@PathParam("id") int id) throws BusinessException {
		UtilisateurManager um = new UtilisateurManager();
		return um.voirProfil(id);
	}
	
	@PUT
	/**
	 * Enregistre un profil, nouveau ou existant
	 * @param utilisateur (formulaire)
	 * @throws BusinessException
	 */
	public void enregistrerProfil(@FormParam("paramProfil") Utilisateur utilisateur) throws BusinessException {
		UtilisateurManager um = new UtilisateurManager();
		um.saveNewOrExistingCompte(utilisateur);
	}
	
	@DELETE
	@Path("/{id:\\d+}")
	public void supprimerProfil(@PathParam("id") int id) throws BusinessException {
		UtilisateurManager um = new UtilisateurManager();
		um.deleteCompte(id);
	}
}
