package org.bodywired.api.controller;

import java.util.List;

import org.bodywired.api.model.Aliment;
import org.bodywired.api.model.classement.CategorieAliment;
import org.bodywired.api.service.AlimentService;
import org.bodywired.api.wrapper.RechercheWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AlimentController {

	@Autowired
	private AlimentService	alimentService;

	/**
	 * Ajout d'un aliment en BDD
	 * 
	 * @param aliment
	 * @return
	 */
	@RequestMapping ( value = BodywiredURL.ajouterALIMENT, method = RequestMethod.GET )
	public @ResponseBody
	String ajouterAliment ( @ModelAttribute Aliment aliment  ) {
		alimentService.sauvegarderAliment(aliment);
		return "OK";
	}

	/**
	 * Récupérer la liste de toutes les catégories d'aliments
	 * 
	 * @return toutes les catégories d'aliments
	 */
	@RequestMapping ( value = BodywiredURL.categoriesALIMENT, method = RequestMethod.GET )
	public @ResponseBody
	List <CategorieAliment> recupererCategoriesAliments () {
		return alimentService.getCategoriesAliments();
	}

	/**
	 * Récupère la liste des aliments en fonction du filtre. filtre vide ->
	 * récupérer tous les aliments Sinon rechercher les aliments dont le nom
	 * commence par la regex du filtre
	 * 
	 * @param filtre
	 * @return la liste des aliments correspondant à la recherche de
	 *         l'utilisateur
	 */
	@RequestMapping ( value = BodywiredURL.listerALIMENTS, method = RequestMethod.GET )
	public @ResponseBody
	List <Aliment> listerAliments ( @ModelAttribute RechercheWrapper wrapper ) {
		return alimentService.getAliments(wrapper);
	}

	/**
	 * Retourne le nombre total d'aliments contenu en BDD
	 * 
	 * @return le nombre total d'aliments contenu en BDD
	 */
	@RequestMapping ( value = BodywiredURL.totalALIMENTS, method = RequestMethod.GET )
	public @ResponseBody
	int totalAliments () {
		return alimentService.getTotal();
	}

}
