package org.bodywired.api.controller;

import org.bodywired.api.model.Aliment;
import org.bodywired.api.service.AlimentService;
import org.bodywired.api.utils.BodywiredURL;
import org.bodywired.api.wrapper.RechercheWrapper;
import org.bodywired.api.wrapper.ResultatRechercheWrapper;
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
@Api(value = BodywiredURL.ROOT_ALIMENTS, description = "Gestion des aliments")
@RequestMapping(BodywiredURL.ROOT_ALIMENTS)
public class AlimentController {

	@Autowired
	private AlimentService alimentService;

	/**
	 * Ajout d'un aliment en BDD
	 * 
	 * @param aliment
	 * @return
	 */
	@ApiOperation(value = BodywiredURL.AJOUTER_ALIMENT, notes = "ajoute un aliment sans leur déclinaison")
	@RequestMapping(value = BodywiredURL.AJOUTER_ALIMENT, method = RequestMethod.POST)
	public @ResponseBody Aliment ajouterAliment(@RequestBody Aliment aliment) {
		alimentService.sauvegarderAliment(aliment);
		return aliment;
	}

	/**
	 * Récupère la liste des aliments en fonction du filtre. filtre vide ->
	 * récupérer tous les aliments Sinon rechercher les aliments dont le nom
	 * contient la regex du filtre
	 * 
	 * @param filtre
	 * @return aliments : la liste des aliments correspondant à la recherche de
	 *         l'utilisateur total : le nombre total d'aliment sans limit ni
	 *         offset
	 */
	@ApiOperation(value = BodywiredURL.LISTER_ALIMENTS, notes = "Récupère la liste des aliments avec leur déclinaison en fonction de la recherche")
	@RequestMapping(value = BodywiredURL.LISTER_ALIMENTS, method = RequestMethod.GET)
	@ApiImplicitParam
	public @ResponseBody ResultatRechercheWrapper listerAliments(@ApiParam(required = true, value = "wrapper de la recherche") @ModelAttribute RechercheWrapper wrapper) {
		return alimentService.rechercherAliments(wrapper);
	}

}
