package org.bodywired.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.bodywired.api.model.Aliment;
import org.bodywired.api.model.menu.CategorieRecette;
import org.bodywired.api.model.menu.Recette;
import org.bodywired.api.service.AlimentService;
import org.bodywired.api.service.RecetteService;
import org.bodywired.api.utils.BodywiredURL;
import org.bodywired.api.wrapper.RechercheWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiImplicitParam;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

@Controller
@Api(value = BodywiredURL.ROOT_RECETTES, description = "Gestion des recettes")
@RequestMapping(BodywiredURL.ROOT_RECETTES)
public class RecetteController {

	@Autowired
	private RecetteService recetteService;

	@Autowired
	private AlimentService alimentService;

	/**
	 * Récupère la liste des recettes
	 * 
	 * @return la liste des recettes
	 */
	@ApiOperation(value = BodywiredURL.LISTER_RECETTES, notes = "Récupère la liste des recettes")
	@RequestMapping(value = BodywiredURL.LISTER_RECETTES, method = RequestMethod.GET)
	@ApiImplicitParam
	public @ResponseBody List<Recette> listerRecettes(@ApiParam(required = true, value = "wrapper de la recherche") @ModelAttribute RechercheWrapper wrapper) {
		// return menuService.rechercherAliments(wrapper);

		List<Recette> listeRecettes = new ArrayList<Recette>();

		Recette recette1 = new Recette("Recette 1", 30, 20, 15, 0, "Ici voici le déroulement à suivre pour faire une super bonne recette 1", 500);
		CategorieRecette categorie1 = new CategorieRecette("Catégorie 1");
		categorie1.addRecette(recette1);
		CategorieRecette categorie2 = new CategorieRecette("Catégorie 2");
		categorie2.addRecette(recette1);
		recette1.addAliment(alimentService.getAliment("Boeuf musqué"), 200);
		recette1.addAliment(alimentService.getAliment("Champignon"), 150);
		recette1.addAliment(alimentService.getAliment("Cerfeuil"), 50);
		recette1.addAliment(alimentService.getAliment("Béluga"), 200);
		listeRecettes.add(recette1);
		
		Recette recette2 = new Recette("Recette 2", 45, 30, 0, 0, "Ici voici le déroulement à suivre pour faire une super bonne recette 2", 850);
		CategorieRecette categorie3 = new CategorieRecette("Catégorie 3");
		categorie3.addRecette(recette2);
		recette2.addAliment(alimentService.getAliment("Baselle"), 200);
		recette2.addAliment(alimentService.getAliment("Noix de coco"), 50);
		recette2.addAliment(alimentService.getAliment("Oeuf de poule"), 350);
		recette2.addAliment(alimentService.getAliment("Origan"), 125);
		recette2.addAliment(alimentService.getAliment("Poireau"), 75);
		listeRecettes.add(recette2);
		

		return listeRecettes;
	}

	/**
	 * Ajout d'une recette en BDD
	 * 
	 * @param recette
	 * @return
	 */
	@ApiOperation(value = BodywiredURL.AJOUTER_RECETTE, notes = "ajoute une recette")
	@RequestMapping(value = BodywiredURL.AJOUTER_RECETTE, method = RequestMethod.POST)
	public @ResponseBody Recette ajouterRecette(@RequestBody Recette recette) {
		recetteService.sauvegarderRecette(recette);
		return recette;
	}
	
	
	@RequestMapping(value = "tester", method = RequestMethod.GET)
	public @ResponseBody Boolean testerRecettes() {
		// return menuService.rechercherAliments(wrapper);

		Recette recette1 = new Recette("Recette 1", 30, 20, 15, 0, "Ici voici le déroulement à suivre pour faire une super bonne recette 1", 500);
		CategorieRecette categorie1 = new CategorieRecette("Catégorie 1");
		categorie1.addRecette(recette1);
		CategorieRecette categorie2 = new CategorieRecette("Catégorie 2");
		categorie2.addRecette(recette1);
		recette1.addAliment(alimentService.getAliment("Boeuf musqué"), 200);
		recette1.addAliment(alimentService.getAliment("Champignon"), 150);
		recette1.addAliment(alimentService.getAliment("Cerfeuil"), 50);
		recette1.addAliment(alimentService.getAliment("Béluga"), 200);
		Boolean result = recetteService.sauvegarderRecette(recette1);
		
		Recette recette2 = new Recette("Recette 2", 45, 30, 0, 0, "Ici voici le déroulement à suivre pour faire une super bonne recette 2", 850);
		CategorieRecette categorie3 = new CategorieRecette("Catégorie 3");
		categorie3.addRecette(recette2);
		recette2.addAliment(alimentService.getAliment("Baselle"), 200);
		recette2.addAliment(alimentService.getAliment("Noix de coco"), 50);
		recette2.addAliment(alimentService.getAliment("Oeuf de poule"), 350);
		recette2.addAliment(alimentService.getAliment("Origan"), 125);
		recette2.addAliment(alimentService.getAliment("Poireau"), 75);
		result = recetteService.sauvegarderRecette(recette2) && result;
		

		return result;
	}
	
	/**
	 * Retourne le nombre total de recettes contenu en BDD
	 * 
	 * @return le nombre total de recettes contenu en BDD
	 */
	@ApiOperation(value = BodywiredURL.TOTAL_RECETTES, notes = "Renvoie le nombre total de recettes en BDD")
	@RequestMapping(value = BodywiredURL.TOTAL_RECETTES, method = RequestMethod.GET)
	public @ResponseBody Integer totalRecettes() {
		return recetteService.getTotalRecettes();
	}
}
