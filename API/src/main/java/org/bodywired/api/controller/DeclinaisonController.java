package org.bodywired.api.controller;

import java.util.List;

import org.bodywired.api.model.Aliment;
import org.bodywired.api.model.Declinaison;
import org.bodywired.api.model.classement.Etat;
import org.bodywired.api.model.nutriment.AbstractNutriment.Ref;
import org.bodywired.api.service.DeclinaisonService;
import org.bodywired.api.utils.BodywiredURL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
	public @ResponseBody Declinaison ajouterDeclinaison(@RequestBody Declinaison declinaison) {
		Ref t = declinaison.getNutriments().getVitamines().get(0).getRef();
		declinaisonService.sauvegarderDeclinaison(declinaison);

		return declinaison;
	}

	/**
	 * Suppression d'une déclinaison en BDD
	 * 
	 * @param declinaison
	 * @return
	 */
	@ApiOperation(value = BodywiredURL.SUPPRIMER_DECLINAISON, notes = "ajoute une déclinaison pour un aliment")
	@RequestMapping(value = BodywiredURL.SUPPRIMER_DECLINAISON, method = RequestMethod.DELETE)
	public ResponseEntity<String> supprimerDeclinaison(@PathVariable(value="id") Integer id) {
		if (!declinaisonService.supprimerDeclinaison(id)) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<String>(HttpStatus.OK);
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
	 * Modification d'un état de déclinaison en BDD
	 * 
	 * @param etat
	 * @return
	 */
	@ApiOperation(value = BodywiredURL.MODIFIER_ETAT_DECLINAISON, notes = "modifie un état de déclinaison")
	@RequestMapping(value = BodywiredURL.MODIFIER_ETAT_DECLINAISON, method = RequestMethod.PUT)
	public ResponseEntity<String> modifierEtat(@RequestBody Etat etat) {
		if (!declinaisonService.modifierEtat(etat)) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	/**
	 * Suppression d'un état de déclinaison en BDD
	 * 
	 * @param id
	 * @return
	 */
	@ApiOperation(value = BodywiredURL.SUPPRIMER_ETAT_DECLINAISON, notes = "supprime un état de déclinaison")
	@RequestMapping(value = BodywiredURL.SUPPRIMER_ETAT_DECLINAISON, method = RequestMethod.DELETE)
	public ResponseEntity<String> supprimerEtat(@PathVariable(value="id") Integer id) {
		if (!declinaisonService.supprimerEtat(id)) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<String>(HttpStatus.OK);
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
