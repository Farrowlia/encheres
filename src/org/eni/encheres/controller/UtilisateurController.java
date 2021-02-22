package org.eni.encheres.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.eni.encheres.authentification.Authentification;
import org.eni.encheres.bll.UtilisateurManager;
import org.eni.encheres.bo.Utilisateur;
import org.eni.encheres.erreur.BusinessException;

@Path("/utilisateur")
public class UtilisateurController {
	
	UtilisateurManager um;
	Authentification auth;
	
	public UtilisateurController() {
		this.um = new UtilisateurManager();
		this.auth = new Authentification();
	}

	/**
	 * @apiNote voir mon profil en mode connecté
	 * @param id
	 * @return l'utilisateur de l'id
	 * @throws BusinessException
	 */
	@GET
	@Path("/monProfil")
	public Utilisateur voirMonProfil(@Context HttpServletRequest request) throws BusinessException {
		
		Utilisateur user = auth.authorize(request);
		return user;
	}
	
	/**
	 * @apiNote Voir le profil en cliquant sur le pseudo
	 * @param pseudo
	 * @param request
	 * @return
	 * @throws BusinessException
	 */
	@GET
	@Path("/{pseudo}")
	public Utilisateur voirProfil(@PathParam("pseudo") String pseudo, @Context HttpServletRequest request) throws BusinessException {
		return um.voirProfil(pseudo);
	}
	
	@POST
	@Path("/connection")
	public void login(@FormParam("login") String login, @FormParam("pwd") String pwd, @Context HttpServletRequest request) {
		Utilisateur user = auth.login(login, pwd);
		/* Création ou récupération de la session */
		HttpSession session = request.getSession();
		/* Mise en session de l'utilisateur */
		session.setAttribute( "user", user);
	}
	
//	/**
//	 * BOUTON Créer ou Enregistrer
//	 * Enregistre un profil, nouveau ou existant
//	 * @param utilisateur (formulaire)
//	 * @throws BusinessException
//	 */
//	@POST
//	@Consumes(MediaType.APPLICATION_JSON)
//	public void enregistrerProfil(@FormParam("paramProfil") Utilisateur utilisateur) throws BusinessException {
//		UtilisateurManager um = new UtilisateurManager();
//		um.saveNewOrExistingCompte(utilisateur);
//	}
	
	/**
	 * Bouton Supprimer mon compte
	 * @param id
	 * @throws BusinessException
	 */
	@DELETE
	public void supprimerMonProfil(@Context HttpServletRequest request) throws BusinessException {
		Utilisateur user = auth.authorize(request);
		um.deleteCompte(user.getNoUtilisateur());
	}
}
