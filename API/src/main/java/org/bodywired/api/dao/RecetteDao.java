package org.bodywired.api.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.bodywired.api.model.Aliment;
import org.bodywired.api.model.menu.CategorieRecette;
import org.bodywired.api.model.menu.Recette;

public interface RecetteDao {

	List<Recette> getAllRecettes();

	Recette getRecette(@Param("rec_id") Integer id);

	Integer sauvegarderRecette(@Param("rec") Recette recette);

	Integer modifierRecette(@Param("rec") Recette recette);

	Integer supprimerRecette(@Param("rec_id") Integer id);

	Recette rechercherRecetteParNom(@Param("nom") String nom);

	List<Recette> rechercherRecettesAssociees(@Param("nom") String nom);

	List<Aliment> rechercherAlimentsAssocies(String nom);

	Integer sauvegarderIngredientRecette(@Param("rec") Recette ingredient, @Param("qte") int qte, @Param("rec_concernee") Recette recette);

	Integer supprimerIngredientRecette(@Param("rec") Recette ingredient, @Param("rec_concernee") Recette recette);

	Integer modifierIngredientRecette(@Param("rec") Recette ingredient, @Param("qte") int qte, @Param("rec_concernee") Recette recette);

	Integer sauvegarderIngredientAliment(@Param("ali") Aliment ingredient, @Param("qte") int qte, @Param("rec_concernee") Recette recette);

	Integer supprimerIngredientAliment(@Param("ali") Aliment ingredient, @Param("rec_concernee") Recette recette);

	Integer modifierIngredientAliment(@Param("ali") Aliment ingredient, @Param("qte") int qte, @Param("rec_concernee") Recette recette);

	Integer sauvegarderCategorieRecette(@Param("cat_rec") CategorieRecette catRecette, @Param("rec") Recette recette);

	Integer supprimerCategorieRecette(@Param("cat_rec") CategorieRecette catRecette, @Param("rec") Recette recette);

	Integer getTotalRecettes();

	Integer sauvegarderCategorie(@Param("cat_rec") CategorieRecette catRecette);

	List<CategorieRecette> getAllCategories();

	CategorieRecette rechercherCategorie(@Param("text") String text);

	Boolean recetteEstDansCategorie(@Param("rec") Recette recette, @Param("cat") CategorieRecette catRecette);

	Recette rechercherRecetteParHref(@Param("href") String href);

}
