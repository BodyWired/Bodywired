package org.bodywired.api.service;

import java.util.Date;
import java.util.Set;

import org.bodywired.api.model.Planning;
import org.bodywired.api.model.Utilisateur;
import org.bodywired.api.model.menu.Recette;
import org.springframework.http.ResponseEntity;

public interface UtilisateurService {

	public Utilisateur getUtilisateurByLogin(String login);

	public Boolean creerUtilisateur(String login, String pwd)
			throws UtilisateurExistantException;

	public Boolean ajouterFavori(Integer userId, Integer recId)
			throws UtilisateurInexistantException, RecetteInexistanteException;

	public Boolean supprimerFavori(Integer userId, Integer recId)
			throws UtilisateurInexistantException, RecetteInexistanteException;

	public Set<Recette> favoris(Integer userId)
			throws UtilisateurInexistantException;

	public Utilisateur authentifie(String login, String pwd)
			throws UtilisateurNonAuthentifieException;

	public Set<Planning> plannings(Integer userId)
			throws UtilisateurInexistantException;

	public Planning ajouterPlanning(Integer userId, Integer recId, Date dateObject, Integer repas)
			throws UtilisateurInexistantException, RecetteInexistanteException, ServiceUtilisateurException;

	public Boolean modifierPlanning(Integer planId, Integer recId, Date dateObject, Integer repas)
			throws PlanningInexistantException, RecetteInexistanteException;

	public Boolean supprimerPlanning(Integer planId)
			throws PlanningInexistantException;

	
	/* EXCEPTIONS */
	public class ServiceUtilisateurException extends Exception {
		private static final long serialVersionUID = 7041376106709430874L;
		public ServiceUtilisateurException(String msg) { super(msg); }
	}
	
	public class UtilisateurInexistantException extends Exception {
		private static final long serialVersionUID = -4653696859593228713L;
	}

	public class UtilisateurNonAuthentifieException extends Exception {
		private static final long serialVersionUID = 3850541104268215099L;
	}
	
	public class UtilisateurExistantException extends Exception {
		private static final long serialVersionUID = 5638669174371926085L;
	}
	
	public class RecetteInexistanteException extends Exception {
		private static final long serialVersionUID = 3280523211591505053L;
	}
	
	public class PlanningInexistantException extends Exception {
		private static final long serialVersionUID = -6312637016222409683L;
	}
}
