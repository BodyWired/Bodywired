package org.bodywired.api.controller;

import java.util.List;

import org.bodywired.api.model.Declinaison;
import org.bodywired.api.model.classement.Etat;
import org.bodywired.api.service.DeclinaisonService;
import org.bodywired.api.utils.BodywiredURL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DeclinaisonController {

	@Autowired
	private DeclinaisonService	declinaisonService;

	/**
	 * Ajout d'une déclinaison en BDD
	 * 
	 * @param declinaison
	 * @return
	 */
	@RequestMapping ( value = BodywiredURL.AJOUTER_DECLINAISON, method = RequestMethod.POST )
	public @ResponseBody
	String ajouterDeclinaison ( @RequestParam Declinaison declinaison ) {
		declinaisonService.sauvegarderDeclinaison(declinaison);
		return "OK";
	}

	/**
	 * Récupérer la liste de toutes les déclinaisons d'aliments
	 * 
	 * @return toutes les déclinaison d'aliments
	 */
	@RequestMapping ( value = BodywiredURL.LISTER_DECLINAISON, method = RequestMethod.GET )
	public @ResponseBody
	List <Declinaison> recupererDeclinaisons () {
		return declinaisonService.getDeclinaison();
	}

	/**
	 * Ajout d'un état de déclinaison en BDD
	 * 
	 * @param etat
	 * @return
	 */
	@RequestMapping ( value = BodywiredURL.AJOUTER_ETAT_DECLINAISON, method = RequestMethod.POST )
	public @ResponseBody
	String ajouterEtat ( @RequestParam Etat etat ) {
		declinaisonService.sauvegarderEtat(etat);
		return "OK";
	}

	/**
	 * Récupérer la liste de toutes les états des déclinaisons d'aliments
	 * 
	 * @return toutes les états des déclinaisons d'aliments
	 */
	@RequestMapping ( value = BodywiredURL.LISTER_ETAT_DECLINAISON, method = RequestMethod.GET )
	public @ResponseBody
	List <Etat> recupererEtats () {
		return declinaisonService.getEtats();
	}

}
