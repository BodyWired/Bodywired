package org.bodywired.api.service.impl;

import org.bodywired.api.dao.RecetteDao;
import org.bodywired.api.model.Aliment;
import org.bodywired.api.model.menu.CategorieRecette;
import org.bodywired.api.model.menu.Recette;
import org.bodywired.api.service.RecetteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecetteServiceImpl implements RecetteService {

	@Autowired
	private RecetteDao	recetteDao;

	@Override
	public Integer getTotalRecettes () {
		return recetteDao.getTotalRecettes();
	}

	@Override
	public Boolean addRecette ( Recette recette ) {
		return recetteDao.ajouterRecette(recette);
	}

	@Override
	public Recette rechercheRecetteParNom ( String nom ) {
		return recetteDao.rechercherRecetteParNom(nom);
	}

	@Override
	public Recette rechercheRecetteIngredient ( String nom ) {
		return recetteDao.rechercherRecetteIngredient(nom);
	}

	@Override
	public Aliment rechercheAlimentIngredient ( String nom ) {
		return recetteDao.rechercherAlimentIngredient(nom);
	}

	@Override
	public Boolean addCategorieForRecette ( CategorieRecette catRecette, Recette recette ) {
		return recetteDao.ajouterCategorie(catRecette, recette);

	}

	@Override
	public Boolean addRecetteIngredientForRecette ( Recette ingredient, int qte, Recette recette ) {
		return recetteDao.ajouterIngredientRecette(ingredient, qte, recette);
	}

	@Override
	public Boolean addAlimentIngredientForRecette ( Aliment ingredient, int qte, Recette recette ) {
		return recetteDao.ajouterIngredientAliment(ingredient, qte, recette);

	}
}
