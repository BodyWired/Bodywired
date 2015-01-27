package org.bodywired.api.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.bodywired.api.model.Aliment;
import org.bodywired.api.model.menu.CategorieRecette;
import org.bodywired.api.model.menu.Recette;

public interface RecetteDao {

	List<Recette> getAllRecettes();

	Integer sauvegarderRecette(@Param("rec") Recette recette);

	Recette rechercherRecetteParNom( @Param("nom") String nom);

	List<Recette> rechercherRecettesAssociees( @Param("nom") String nom);

	List<Aliment> rechercherAlimentsAssocies(String nom);

	Integer sauvegarderCategorie(CategorieRecette catRecette, Recette recette);

	Integer sauvegarderIngredientRecette(Recette ingredient, int qte,
			Recette recette);

	Integer sauvegarderIngredientAliment(Aliment ingredient, int qte,
			Recette recette);

	Integer getTotalRecettes();

}
