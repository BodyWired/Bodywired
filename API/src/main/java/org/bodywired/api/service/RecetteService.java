package org.bodywired.api.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.bodywired.api.model.Aliment;
import org.bodywired.api.model.menu.CategorieRecette;
import org.bodywired.api.model.menu.Recette;

public interface RecetteService {

	public List<Recette> getAllRecettes();
	
	public Recette getRecette(Integer id);

	public Boolean sauvegarderRecette(Recette recette);

	public Recette rechercherRecetteParNom(String recherche);

	public List<Recette> rechercherRecetteIngredient(String recherche);

	public List<Aliment> rechercherAlimentIngredient(String recherche);

	public Boolean ajouterCategorieRecette(CategorieRecette categorieRecette, Recette recette);

	public Boolean ajouterRecetteIngredient(Recette ingredient, int qte, Recette recette);

	public Boolean ajouterAlimentIngredient(Aliment ingredient, int qte, Recette recette);

	public Integer getTotalRecettes();

	public Boolean ajouterCategorie(CategorieRecette categorie);
	
	public List<CategorieRecette> getAllCategories();

	public Recette rechercherRecetteParHref(String href);
}
