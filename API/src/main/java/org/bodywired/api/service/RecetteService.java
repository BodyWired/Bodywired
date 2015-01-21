package org.bodywired.api.service;

import org.bodywired.api.model.Aliment;
import org.bodywired.api.model.menu.CategorieRecette;
import org.bodywired.api.model.menu.Recette;

public interface RecetteService {

	public Integer getTotalRecettes ();

	public Boolean addRecette ( Recette recette );

	public Recette rechercheRecetteParNom ( String substring );

	public Recette rechercheRecetteIngredient ( String text );

	public Aliment rechercheAlimentIngredient ( String text );

	public Boolean addCategorieForRecette ( CategorieRecette catRecette, Recette recette );

	public Boolean addRecetteIngredientForRecette ( Recette ingredient, int qte, Recette recette );

	public Boolean addAlimentIngredientForRecette ( Aliment ingredient, int qte, Recette recette );
}
