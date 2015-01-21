package org.bodywired.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.bodywired.api.model.Aliment;
import org.bodywired.api.model.AlimentRecette;
import org.bodywired.api.model.Recette;
import org.bodywired.api.service.AlimentService;
import org.bodywired.api.service.RecetteService;
import org.bodywired.api.utils.BodywiredURL;
import org.bodywired.api.wrapper.RechercheWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
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

		RechercheWrapper recherche = new RechercheWrapper();
		List<Aliment> aliments = alimentService.rechercherAliments(recherche).getAliments();

		List<Recette> listeRecettes = new ArrayList<Recette>();

		Recette recette1 = new Recette("Recette 1", 30, Boolean.TRUE, 35, 4, null);
		List<AlimentRecette> alimentQuantiteRecette1 = new ArrayList<AlimentRecette>();
		alimentQuantiteRecette1.add(new AlimentRecette(aliments.get(0), recette1, 2));
		alimentQuantiteRecette1.add(new AlimentRecette(aliments.get(1), recette1, 3));
		alimentQuantiteRecette1.add(new AlimentRecette(aliments.get(2), recette1, 1));
		recette1.setAlimentsQuantités(alimentQuantiteRecette1);
		listeRecettes.add(recette1);

		Recette recette2 = new Recette("Recette 2", 45, Boolean.FALSE, 45, 6, null);
		List<AlimentRecette> alimentQuantiteRecette2 = new ArrayList<AlimentRecette>();
		alimentQuantiteRecette2.add(new AlimentRecette(aliments.get(3), recette2, 2));
		alimentQuantiteRecette2.add(new AlimentRecette(aliments.get(4), recette2, 6));
		alimentQuantiteRecette2.add(new AlimentRecette(aliments.get(5), recette2, 10));
		recette1.setAlimentsQuantités(alimentQuantiteRecette2);
		listeRecettes.add(recette2);

		return listeRecettes;
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
