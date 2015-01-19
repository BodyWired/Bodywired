package org.bodywired.api.controller;

import java.util.List;

import org.bodywired.api.model.Declinaison;
import org.bodywired.api.model.classement.Etat;
import org.bodywired.api.service.DeclinaisonService;
import org.bodywired.api.utils.BodywiredURL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

@Controller
@Api(value = BodywiredURL.ROOT_DECLINAISONS, description = "Gestion des déclinaisons des aliments")
@RequestMapping(BodywiredURL.ROOT_DECLINAISONS)
public class DeclinaisonController {

	@Autowired
	private DeclinaisonService declinaisonService;

	/**
	 * Ajout d'une déclinaison en BDD
	 * 
	 * @param declinaison
	 * @return
	 */
	@ApiOperation(value = BodywiredURL.AJOUTER_DECLINAISON, notes = "ajoute une déclinaison pour un aliment")
	@RequestMapping(value = BodywiredURL.AJOUTER_DECLINAISON, method = RequestMethod.POST)
	public @ResponseBody Declinaison ajouterDeclinaison(
			@RequestBody Declinaison declinaison) {
		declinaisonService.sauvegarderDeclinaison(declinaison);
		return declinaison;
	}

	/**
	 * Récupérer la liste de toutes les déclinaisons d'aliments
	 * 
	 * @return toutes les déclinaison d'aliments
	 */
	// @RequestMapping(value = BodywiredURL.LISTER_DECLINAISON, method =
	// RequestMethod.GET)
	// public @ResponseBody List<Declinaison> recupererDeclinaisons() {
	// return declinaisonService.getDeclinaisons();
	// }

	/**
	 * Ajout d'un état de déclinaison en BDD
	 * 
	 * @param etat
	 * @return
	 */
	@ApiOperation(value = BodywiredURL.AJOUTER_ETAT_DECLINAISON, notes = "ajoute un état de déclinaison")
	@RequestMapping(value = BodywiredURL.AJOUTER_ETAT_DECLINAISON, method = RequestMethod.POST)
	public @ResponseBody Etat ajouterEtat(@RequestBody Etat etat) {
		declinaisonService.sauvegarderEtat(etat);
		return etat;
	}

	/**
	 * Récupérer la liste de tous les états des déclinaisons d'aliments
	 * 
	 * @return toutes les états des déclinaisons d'aliments
	 */
	@ApiOperation(value = BodywiredURL.LISTER_ETAT_DECLINAISON, notes = "liste la liste de tous les états des déclinaisons d'aliments")
	@RequestMapping(value = BodywiredURL.LISTER_ETAT_DECLINAISON, method = RequestMethod.GET)
	public @ResponseBody List<Etat> recupererEtats() {
		return declinaisonService.getEtats();
	}

}
