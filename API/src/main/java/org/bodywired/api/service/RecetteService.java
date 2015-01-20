package org.bodywired.api.service;

import org.bodywired.api.model.Aliment;
import org.bodywired.api.model.menu.CategorieRecette;
import org.bodywired.api.model.menu.Recette;

public interface RecetteService {

	public Integer getTotalRecettes ();

	public Boolean addRecette ( Recette recette );

	public Recette getRecetteFromName ( String substring );

	public Recette getRecetteIngredient ( String text );

	public Aliment getAlimentIngredient ( String text );

	public void updateRecette ( Recette recette );

	public void addCategorieForRecette ( CategorieRecette catRecette, Recette recette );

	public void addRecetteIngredientForRecette ( Recette ingredient, int qte, Recette recette );

	public void addAlimentIngredientForRecette ( Aliment ingredient, int qte, Recette recette );
}
