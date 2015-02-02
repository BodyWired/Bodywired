package org.bodywired.api.service.impl;

import java.util.List;

import org.bodywired.api.dao.AlimentDao;
import org.bodywired.api.dao.RecetteDao;
import org.bodywired.api.model.Aliment;
import org.bodywired.api.model.menu.CategorieRecette;
import org.bodywired.api.model.menu.IngredientAliment;
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
		return recetteDao.getAllRecettes();
	}

	@Override
	public Integer getTotalRecettes() {
		return recetteDao.getTotalRecettes();
	}

	@Override
	public Boolean sauvegarderRecette(Recette recette) {
		for (IngredientAliment aliment : recette.getAliments()) {
			
		}
		
		return recetteDao.sauvegarderRecette(recette) > 0;
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
}
