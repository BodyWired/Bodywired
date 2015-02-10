package org.bodywired.api.service;

import org.bodywired.api.model.Utilisateur;

public interface UtilisateurService {

	public Utilisateur getUtilisateurByLogin(String login);

	public Boolean creerUtilisateur(Utilisateur utilisateur);

}
