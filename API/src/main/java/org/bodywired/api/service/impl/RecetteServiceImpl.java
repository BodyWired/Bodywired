package org.bodywired.api.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.bodywired.api.dao.AlimentDao;
import org.bodywired.api.dao.RecetteDao;
import org.bodywired.api.model.Aliment;
import org.bodywired.api.model.menu.CategorieRecette;
import org.bodywired.api.model.menu.IngredientAliment;
import org.bodywired.api.model.menu.IngredientRecette;
import org.bodywired.api.model.menu.Recette;
import org.bodywired.api.service.RecetteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecetteServiceImpl implements RecetteService {

	@Autowired
	private RecetteDao recetteDao;

	@Autowired
	private AlimentDao alimentDao;

	@Override
	public List<Recette> getAllRecettes() {
		return recetteDao.getAllRecettes();
	}

	@Override
	public Recette getRecette(Integer id) {
		return recetteDao.getRecette(id);
	}

	@Override
	public Boolean sauvegarderRecette(Recette recette) {
		Recette exitante = rechercherRecetteParNom(recette.getNom());
		if (exitante != null) {
			recette.setId(exitante.getId());
			return true;
		}
		if (recetteDao.sauvegarderRecette(recette) == 0)
			return false;

		for (CategorieRecette categorie : recette.getCategories()) {
			if (recetteDao.sauvegarderCategorieRecette(categorie, recette) == 0)
				return false;
		}

		for (IngredientAliment aliment : recette.getAliments()) {
			if (recetteDao.sauvegarderIngredientAliment(aliment.getAliment(), aliment.getQuantite(), recette) == 0)
				return false;

		}

		for (IngredientRecette ingredientRecette : recette.getRecettes()) {
			if (recetteDao.sauvegarderIngredientRecette(ingredientRecette.getRecetteAssociee(), ingredientRecette.getQuantite(), recette) == 0)
				return false;
		}

		return true;
	}

	@Override
	public Recette rechercherRecetteParNom(String nom) {
		LOGGER.debug("recherche : [" + nom + "]");
		return recetteDao.rechercherRecetteParNom(nom);
	}
	
	private static final Logger LOGGER = Logger.getLogger(AlimentServiceImpl.class);

	@Override
	public List<Recette> rechercherRecetteIngredient(String nom) {
		return recetteDao.rechercherRecettesAssociees(nom);
	}

	@Override
	public List<Aliment> rechercherAlimentIngredient(String nom) {
		return recetteDao.rechercherAlimentsAssocies(nom);
	}

	@Override
	public Boolean ajouterCategorieRecette(CategorieRecette catRecette, Recette recette) {
		if (!recetteDao.recetteEstDansCategorie(recette, catRecette)) {
			return recetteDao.sauvegarderCategorieRecette(catRecette, recette) > 0;
		} else {
			return true;
		}

	}

	@Override
	public Boolean ajouterRecetteIngredient(Recette ingredient, int qte, Recette recette) {
		return recetteDao.sauvegarderIngredientRecette(ingredient, qte, recette) > 0;
	}

	@Override
	public Boolean ajouterAlimentIngredient(Aliment ingredient, int qte, Recette recette) {
		return recetteDao.sauvegarderIngredientAliment(ingredient, qte, recette) > 0;

	}

	@Override
	public Integer getTotalRecettes() {
		return recetteDao.getTotalRecettes();
	}

	@Override
	public Boolean ajouterCategorie(CategorieRecette categorie) {
		return recetteDao.sauvegarderCategorie(categorie) == 1;
	}

	@Override
	public List<CategorieRecette> getAllCategories() {
		return recetteDao.getAllCategories();
	}

	@Override
	public Recette rechercherRecetteParHref(String href) {
		LOGGER.debug("Recherche R : " + href);
		return recetteDao.rechercherRecetteParHref(href);
	}
}
