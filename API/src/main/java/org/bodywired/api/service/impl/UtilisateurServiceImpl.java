package org.bodywired.api.service.impl;

import org.bodywired.api.dao.UtilisateurDao;
import org.bodywired.api.model.Utilisateur;
import org.bodywired.api.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {

	@Autowired
	private UtilisateurDao utilisateurDao;
	
	@Override
	public Utilisateur getUtilisateurByLogin(String login) {
		return utilisateurDao.getUtilisateurByLogin(login);
	}

	@Override
	public Boolean creerUtilisateur(Utilisateur utilisateur) {
		return utilisateurDao.creerUtilisateur(utilisateur) == 1;
	}

}
