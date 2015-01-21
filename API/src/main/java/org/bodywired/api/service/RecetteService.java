package org.bodywired.api.service;

import org.bodywired.api.model.Aliment;
import org.bodywired.api.model.menu.CategorieRecette;
import org.bodywired.api.model.menu.Recette;

public interface RecetteService {

	public Integer getTotalRecettes();

	public Boolean sauvegarderRecette(Recette recette);

	public Recette rechercherRecetteParNom(String recherche);

	public Recette rechercherRecetteIngredient(String recherche);

	public Aliment rechercherAlimentIngredient(String recherche);

	public Boolean ajouterCategorieRecette(CategorieRecette categorieRecette, Recette recette);

	public Boolean ajouterRecetteIngredient(Recette ingredient, int qte, Recette recette);

	public Boolean ajouterAlimentIngredient(Aliment ingredient, int qte, Recette recette);
}
