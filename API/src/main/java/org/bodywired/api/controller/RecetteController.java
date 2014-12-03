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
	public @ResponseBody List<Recette> listerRecettes(
			@ApiParam(required = true, value = "wrapper de la recherche") @ModelAttribute RechercheWrapper wrapper) {
		// return menuService.rechercherAliments(wrapper);
		
		RechercheWrapper recherche = new RechercheWrapper();
		List<Aliment> aliments = alimentService.rechercherAliments(recherche);
		
		List<Recette> listeRecettes = new ArrayList<Recette>();
		
		Recette recette1 = new Recette("Recette 1", 30, Boolean.TRUE, 35, 4, null);
		List<AlimentRecette> alimentQuantitéRecette1 = new ArrayList<AlimentRecette>();
		alimentQuantitéRecette1.add(new AlimentRecette(aliments.get(0), recette1, 2));
		alimentQuantitéRecette1.add(new AlimentRecette(aliments.get(1), recette1, 3));
		alimentQuantitéRecette1.add(new AlimentRecette(aliments.get(2), recette1, 1));
		recette1.setAlimentsQuantités(alimentQuantitéRecette1);
		listeRecettes.add(recette1);
		
		Recette recette2 = new Recette("Recette 2",  45, Boolean.FALSE, 45, 6, null);
		List<AlimentRecette> alimentQuantitéRecette2 = new ArrayList<AlimentRecette>();
		alimentQuantitéRecette2.add(new AlimentRecette(aliments.get(3), recette2, 2));
		alimentQuantitéRecette2.add(new AlimentRecette(aliments.get(4), recette2, 6));
		alimentQuantitéRecette2.add(new AlimentRecette(aliments.get(5), recette2, 10));
		recette1.setAlimentsQuantités(alimentQuantitéRecette2);
		listeRecettes.add(recette2);
		
		return listeRecettes;
	}
	
	/**
	 * Retourne le nombre total de recettes contenu en BDD
	 * 
	 * @return le nombre total de recettes contenu en BDD
	 */
	@RequestMapping(value = BodywiredURL.TOTAL_RECETTES, method = RequestMethod.GET)
	public @ResponseBody Integer totalRecettes() {
		return recetteService.getTotalRecettes();
	}
}
