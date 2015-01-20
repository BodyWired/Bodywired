package org.bodywired.api.service.impl;

import org.bodywired.api.model.Aliment;
import org.bodywired.api.model.menu.CategorieRecette;
import org.bodywired.api.model.menu.Recette;
import org.bodywired.api.service.RecetteService;
import org.springframework.stereotype.Service;

@Service
public class RecetteServiceImpl implements RecetteService {

	@Override
	public Integer getTotalRecettes () {
		return 2;
	}

	@Override
	public Boolean addRecette ( Recette recette ) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Recette rechercheRecetteParNom ( String substring ) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Recette rechercheRecetteIngredient ( String text ) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Aliment rechercheAlimentIngredient ( String text ) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateRecette ( Recette recette ) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addCategorieForRecette ( CategorieRecette catRecette, Recette recette ) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addRecetteIngredientForRecette ( Recette ingredient, int qte, Recette recette ) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addAlimentIngredientForRecette ( Aliment ingredient, int qte, Recette recette ) {
		// TODO Auto-generated method stub

	}

}
