package org.bodywired.api.service.impl;

import java.util.Set;

import org.bodywired.api.dao.RecetteDao;
import org.bodywired.api.dao.UtilisateurDao;
import org.bodywired.api.model.Planning;
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
	public Boolean creerUtilisateur(String login, String pwd)
			throws UtilisateurExistantException {
		
		if (utilisateurDao.getUtilisateurByLogin(login) != null) {
			throw new UtilisateurExistantException();
		}
		Utilisateur utilisateur = new Utilisateur ();
		utilisateur.setLogin(login);
		utilisateur.setPwd(MD5(pwd));
		
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

	@Override
	public Utilisateur authentifie(String login, String pwd)
			throws UtilisateurNonAuthentifieException {
		Utilisateur utilisateur = utilisateurDao.getUtilisateurByLogin(login);

		if (utilisateur == null || !utilisateur.getPwd().equals(MD5(pwd))) {
			throw new UtilisateurNonAuthentifieException();
		}

		return utilisateur;
	}

	@Override
	public Set<Planning> plannings(Integer userId)
			throws UtilisateurInexistantException {
		Utilisateur utilisateur = utilisateurDao.getUtilisateurById(userId);
		if (utilisateur == null)
			throw new UtilisateurInexistantException();
		
		return utilisateur.getPlannings();
	}


	
	
	private String MD5(String md5) {
		try {
			java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
			byte[] array = md.digest(md5.getBytes());
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < array.length; ++i) {
				sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
			}
			return sb.toString();
		} catch (java.security.NoSuchAlgorithmException e) {
		}
		return null;
	}
}
