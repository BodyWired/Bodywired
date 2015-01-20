package org.bodywired.api.dao;

import org.bodywired.api.model.Aliment;
import org.bodywired.api.model.menu.CategorieRecette;
import org.bodywired.api.model.menu.Recette;

public interface RecetteDao {

	Boolean ajouterRecette ( Recette recette );

	Recette rechercherRecetteParNom ( String nom );

	Recette rechercherRecetteIngredient ( String nom );

	Aliment rechercherAlimentIngredient ( String nom );

	Boolean ajouterCategorie ( CategorieRecette catRecette, Recette recette );

	Boolean ajouterIngredientRecette ( Recette ingredient, int qte, Recette recette );

	Boolean ajouterIngredientAliment ( Aliment ingredient, int qte, Recette recette );

	Integer getTotalRecettes ();

}
