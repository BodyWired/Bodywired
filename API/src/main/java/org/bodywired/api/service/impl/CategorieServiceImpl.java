package org.bodywired.api.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.bodywired.api.dao.ClassementAlimentDao;
import org.bodywired.api.dao.RecetteDao;
import org.bodywired.api.model.Aliment;
import org.bodywired.api.model.classement.Categorie;
import org.bodywired.api.model.menu.CategorieRecette;
import org.bodywired.api.service.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategorieServiceImpl implements CategorieService {

	@Autowired
	private ClassementAlimentDao classementAlimentDao;

	@Autowired
	private RecetteDao recetteDao;

	@Override
	public Boolean sauvegarderCategorieAliment(Categorie categorie) {
		return (classementAlimentDao.sauvegarderCategorie(categorie) == 1);
	}

	@Override
	public List<Categorie> getCategories() {
		return classementAlimentDao.getCategories();
	}

	@Override
	public Boolean ajouterAlimentDansCategorie(Aliment aliment, Categorie categorie) {
		return (classementAlimentDao.ajouterAlimentDansCategorie(aliment, categorie) == 1);
	}

	@Override
	public Boolean sauvegarderCategorieRecette(CategorieRecette categorie) {
		CategorieRecette existante = rechercherCategorieRecette(categorie.getNom());
		if (existante == null) {
			return recetteDao.sauvegarderCategorie(categorie) == 1;
		} else {
			categorie.setId(existante.getId());
			return true;
		}
	}

	@Override
	public CategorieRecette rechercherCategorieRecette(String text) {
		LOGGER.debug("Recherche CR : ["+text+"]");
		return recetteDao.rechercherCategorie(text);
	}
	
	private static final Logger LOGGER = Logger.getLogger(CategorieServiceImpl.class);

}
