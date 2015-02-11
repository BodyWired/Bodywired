package org.bodywired.api.service.impl;

import java.util.Set;

import org.bodywired.api.dao.RecetteDao;
import org.bodywired.api.dao.UtilisateurDao;
import org.bodywired.api.model.Utilisateur;
import org.bodywired.api.model.menu.Recette;
import org.bodywired.api.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {

	@Autowired
	private UtilisateurDao utilisateurDao;
	
	@Autowired
	private RecetteDao recetteDao;
	
	@Override
	public Utilisateur getUtilisateurByLogin(String login) {
		return utilisateurDao.getUtilisateurByLogin(login);
	}

	@Override
	public Boolean creerUtilisateur(Utilisateur utilisateur) {
		return utilisateurDao.creerUtilisateur(utilisateur) == 1;
	}

	@Override
	public Boolean ajouterFavori(Integer userId, Integer recId) throws UtilisateurInexistantException, RecetteInexistanteException {
		Utilisateur utilisateur = utilisateurDao.getUtilisateurById(userId);
		if (utilisateur == null)
			throw new UtilisateurInexistantException();
		
		Recette recette = recetteDao.getRecette(recId);
		if (recette == null)
			throw new RecetteInexistanteException();
		
		return (utilisateurDao.ajouterFavori(userId, recId) == 1);
	}

	@Override
	public Boolean supprimerFavori(Integer userId, Integer recId)
			throws UtilisateurInexistantException, RecetteInexistanteException {
		Utilisateur utilisateur = utilisateurDao.getUtilisateurById(userId);
		if (utilisateur == null)
			throw new UtilisateurInexistantException();
		
		Recette recette = recetteDao.getRecette(recId);
		if (recette == null)
			throw new RecetteInexistanteException();
		
		return (utilisateurDao.supprimerFavori(userId, recId) == 1);
	}

	@Override
	public Set<Recette> favoris(Integer userId) throws UtilisateurInexistantException {
		Utilisateur utilisateur = utilisateurDao.getUtilisateurById(userId);
		if (utilisateur == null)
			throw new UtilisateurInexistantException();
		
		return utilisateur.getFavoris();
	}

	
}
