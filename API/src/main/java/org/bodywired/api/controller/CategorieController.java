package org.bodywired.api.controller;

import java.util.List;

import org.bodywired.api.model.classement.Categorie;
import org.bodywired.api.service.CategorieService;
import org.bodywired.api.utils.BodywiredURL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CategorieController {

	@Autowired
	private CategorieService categorieService;

	/**
	 * Ajout d'une catégorie en BDD
	 * 
	 * @param categorie
	 * @return
	 */
	@RequestMapping(value = BodywiredURL.AJOUTER_CATEGORIE, method = RequestMethod.POST)
	public @ResponseBody Categorie ajouterCategorie(
			@RequestBody Categorie categorie) {
		categorieService.sauvegarderCategorieAliment(categorie);
		return categorie;
	}

	/**
	 * Récupérer la liste de toutes les catégories d'aliments
	 * 
	 * @return toutes les catégories d'aliments
	 */
	@RequestMapping(value = BodywiredURL.LISTER_CATEGORIES, method = RequestMethod.GET)
	public @ResponseBody List<Categorie> recupererCategories() {
		return categorieService.getCategories();
	}

}
