package org.bodywired.api.service.impl;

import java.util.List;

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
		if ( recetteDao.sauvegarderRecette(recette) == 0)
			return false;
		
		for (CategorieRecette categorie : recette.getCategories()) {
			if ( recetteDao.sauvegarderCategorieRecette(categorie, recette) == 0 )
				return false;
		}
		
		for (IngredientAliment aliment : recette.getAliments()) {
			if ( recetteDao.sauvegarderIngredientAliment(aliment.getAliment(), aliment.getQuantite(), recette) == 0)
				return false;
			
		}
		
		for (IngredientRecette ingredientRecette : recette.getRecettes()) {
			if ( recetteDao.sauvegarderIngredientRecette(ingredientRecette.getRecetteAssociee(), ingredientRecette.getQuantite(), recette) == 0)
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
		return recetteDao.rechercherRecetteParNom(nom);
	}

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
		return recetteDao.sauvegarderCategorieRecette(catRecette, recette) > 0;

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
		// init accumulateur calories
		recette.getAliments();		
		// calculer moyenne calories aliment & ajouter à accu calories
		recette.getRecettes();
		// test caloriesRec = 0 -> calculerCaloriesRecette(Recette recette)
		// ajouter caloriesRec a accu calories
	}
}
