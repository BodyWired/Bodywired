package org.bodywired.api.service;

import java.util.Set;

import org.bodywired.api.model.Utilisateur;
import org.bodywired.api.model.menu.Recette;

public interface UtilisateurService {

	public Utilisateur getUtilisateurByLogin(String login);

	public Boolean creerUtilisateur(Utilisateur utilisateur);

	public Boolean ajouterFavori(Integer userId, Integer recId)
			throws UtilisateurInexistantException, RecetteInexistanteException;

	public Boolean supprimerFavori(Integer userId, Integer recId)
			throws UtilisateurInexistantException, RecetteInexistanteException;

	public Set<Recette> favoris(Integer userId)
			throws UtilisateurInexistantException;

	public class UtilisateurInexistantException extends Exception {
		private static final long serialVersionUID = -4653696859593228713L;
	}
	
	public class RecetteInexistanteException extends Exception {
		private static final long serialVersionUID = 3280523211591505053L;
	}
}
