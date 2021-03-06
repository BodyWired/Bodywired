package org.bodywired.api.service.impl;

import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.bodywired.api.dao.AlimentDao;
import org.bodywired.api.dao.RecetteDao;
import org.bodywired.api.model.Aliment;
import org.bodywired.api.model.Declinaison;
import org.bodywired.api.model.menu.CategorieRecette;
import org.bodywired.api.model.menu.IngredientAliment;
import org.bodywired.api.model.menu.IngredientRecette;
import org.bodywired.api.model.menu.Recette;
import org.bodywired.api.service.RecetteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecetteServiceImpl implements RecetteService {

	@Autowired
	private RecetteDao recetteDao;

	@Autowired
	private AlimentDao alimentDao;

	@Override
	public List<Recette> getAllRecettes() {
		List<Recette> recettes = recetteDao.getAllRecettes();
		for (Recette recette : recettes) {
			if (recette.getCalories() == null || recette.getCalories().equals(Integer.valueOf(0)))
				calculerCaloriesRecette(recette);
		}
		return recettes;
	}

	@Override
	public Recette getRecette(Integer id) {
		return recetteDao.getRecette(id);
	}

	@Override
	public Boolean sauvegarderRecette(Recette recette) {
		Recette exitante = rechercherRecetteParNom(recette.getNom());
		if (exitante != null) {
			recette.setId(exitante.getId());
			return true;
		}
		if (recetteDao.sauvegarderRecette(recette) == 0)
			return false;

		for (CategorieRecette categorie : recette.getCategories()) {
			if (recetteDao.sauvegarderCategorieRecette(categorie, recette) == 0)
				return false;
		}

		for (IngredientAliment aliment : recette.getAliments()) {
			if (recetteDao.sauvegarderIngredientAliment(aliment.getAliment(), aliment.getQuantite(), recette) == 0)
				return false;

		}

		for (IngredientRecette ingredientRecette : recette.getRecettes()) {
			if (recetteDao.sauvegarderIngredientRecette(ingredientRecette.getRecetteAssociee(), ingredientRecette.getQuantite(), recette) == 0)
				return false;
		}

		return true;
	}

	@Override
	public Boolean supprimerRecette(Integer id) {
		return (recetteDao.supprimerRecette(id)) == 1;
	}

	@Override
	public Boolean modifierRecette(Recette recette) {
		Recette ancienneRecette = recetteDao.getRecette(recette.getId());
		
		if (ancienneRecette == null)
			return false;
		
		for (IngredientRecette ingRec : recette.getRecettes()) {
			if (ancienneRecette.getRecettes().contains(ingRec))
				recetteDao.modifierIngredientRecette(ingRec.getRecetteAssociee(), ingRec.getQuantite(), recette);
			else
				recetteDao.sauvegarderIngredientRecette(ingRec.getRecetteAssociee(), ingRec.getQuantite(), recette);
		}
		
		for (IngredientRecette ingRec : ancienneRecette.getRecettes()) {
			if (!recette.getAliments().contains(ingRec))
				recetteDao.supprimerIngredientRecette(ingRec.getRecetteAssociee(), recette);
		}
		
		for (IngredientAliment ingAli : recette.getAliments()) {
			if (ancienneRecette.getAliments().contains(ingAli))
				recetteDao.modifierIngredientAliment(ingAli.getAliment(), ingAli.getQuantite(), recette);
			else
				recetteDao.sauvegarderIngredientAliment(ingAli.getAliment(), ingAli.getQuantite(), recette);
		}
		
		for (IngredientAliment ingAli : ancienneRecette.getAliments()) {
			if (!recette.getAliments().contains(ingAli))
				recetteDao.supprimerIngredientAliment(ingAli.getAliment(), recette);
		}
		
		for (CategorieRecette catRec : recette.getCategories()) {
			if (!ancienneRecette.getCategories().contains(catRec)) {
				recetteDao.sauvegarderCategorieRecette(catRec, recette);
			}
		}
		
		for (CategorieRecette catRec : ancienneRecette.getCategories()) {
			if (!recette.getCategories().contains(catRec))
				recetteDao.supprimerCategorieRecette(catRec, recette);
		}
		
		if (recetteDao.modifierRecette(recette) == 1) {
			recette = recetteDao.getRecette(recette.getId());
			return true;
		}
		
		return false;
	}

	@Override
	public Recette rechercherRecetteParNom(String nom) {
		LOGGER.debug("recherche : [" + nom + "]");
		return recetteDao.rechercherRecetteParNom(nom);
	}
	
	private static final Logger LOGGER = Logger.getLogger(AlimentServiceImpl.class);

	@Override
	public List<Recette> rechercherRecetteIngredient(String nom) {
		return recetteDao.rechercherRecettesAssociees(nom);
	}

	@Override
	public List<Aliment> rechercherAlimentIngredient(String nom) {
		return recetteDao.rechercherAlimentsAssocies(nom);
	}

	@Override
	public Boolean ajouterCategorieRecette(CategorieRecette catRecette, Recette recette) {
		if (!recetteDao.recetteEstDansCategorie(recette, catRecette)) {
			return recetteDao.sauvegarderCategorieRecette(catRecette, recette) > 0;
		} else {
			return true;
		}

	}

	@Override
	public Boolean ajouterRecetteIngredient(Recette ingredient, int qte, Recette recette) {
		return recetteDao.sauvegarderIngredientRecette(ingredient, qte, recette) > 0;
	}

	@Override
	public Boolean ajouterAlimentIngredient(Aliment ingredient, int qte, Recette recette) {
		return recetteDao.sauvegarderIngredientAliment(ingredient, qte, recette) > 0;

	}

	@Override
	public Integer getTotalRecettes() {
		return recetteDao.getTotalRecettes();
	}

	@Override
	public Boolean ajouterCategorie(CategorieRecette categorie) {
		return recetteDao.sauvegarderCategorie(categorie) == 1;
	}

	@Override
	public List<CategorieRecette> getAllCategories() {
		return recetteDao.getAllCategories();
	}

	private void calculerCaloriesRecette(Recette recette) {
		Double calories = 0.0;
		for(IngredientAliment ingredient : recette.getAliments()){
			Double moyenne = 0.0;
			Set<Declinaison> declinaisons = ingredient.getAliment().getDeclinaisons(); 
			for(Declinaison dec : declinaisons) {
				moyenne = moyenne + dec.getNutriments().getCalorie().getApport();
			}
			moyenne = moyenne / declinaisons.size();
			calories =  (calories + ((moyenne * ingredient.getQuantite()) / 100));
		}
		for(IngredientRecette ingredientRecette: recette.getRecettes()){
			Recette rec = ingredientRecette.getRecetteAssociee();
			if(rec.getCalories() == null || rec.getCalories() == 0) 
				calculerCaloriesRecette(rec);
			calories = calories + rec.getCalories();			
		}
		recette.setCalories(calories.intValue());
		recetteDao.sauvegarderRecette(recette);
	}

	@Override
	public Recette rechercherRecetteParHref(String href) {
		LOGGER.debug("Recherche R : " + href);
		return recetteDao.rechercherRecetteParHref(href);
	}
}
