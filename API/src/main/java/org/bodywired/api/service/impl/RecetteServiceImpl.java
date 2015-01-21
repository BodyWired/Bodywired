package org.bodywired.api.service.impl;

import org.bodywired.api.dao.RecetteDao;
import org.bodywired.api.model.Aliment;
import org.bodywired.api.model.menu.CategorieRecette;
import org.bodywired.api.model.menu.Recette;
import org.bodywired.api.service.RecetteService;
import org.springframework.stereotype.Service;

@Service
public class RecetteServiceImpl implements RecetteService {

	// @Autowired
	private RecetteDao recetteDao;

	@Override
	public Integer getTotalRecettes() {
		return recetteDao.getTotalRecettes();
	}

	@Override
	public Boolean sauvegarderRecette(Recette recette) {
		return recetteDao.ajouterRecette(recette);
	}

	@Override
	public Recette rechercherRecetteParNom(String nom) {
		return recetteDao.rechercherRecetteParNom(nom);
	}

	@Override
	public Recette rechercherRecetteIngredient(String nom) {
		return recetteDao.rechercherRecetteIngredient(nom);
	}

	@Override
	public Aliment rechercherAlimentIngredient(String nom) {
		return recetteDao.rechercherAlimentIngredient(nom);
	}

	@Override
	public Boolean ajouterCategorieRecette(CategorieRecette catRecette, Recette recette) {
		return recetteDao.ajouterCategorie(catRecette, recette);

	}

	@Override
	public Boolean ajouterRecetteIngredient(Recette ingredient, int qte, Recette recette) {
		return recetteDao.ajouterIngredientRecette(ingredient, qte, recette);
	}

	@Override
	public Boolean ajouterAlimentIngredient(Aliment ingredient, int qte, Recette recette) {
		return recetteDao.ajouterIngredientAliment(ingredient, qte, recette);

	}
}
