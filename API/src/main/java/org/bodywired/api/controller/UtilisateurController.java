package org.bodywired.api.controller;

import java.util.Date;
import java.util.Set;

import org.bodywired.api.model.Planning;
import org.bodywired.api.model.Utilisateur;
import org.bodywired.api.model.menu.Recette;
import org.bodywired.api.service.UtilisateurService;
import org.bodywired.api.service.UtilisateurService.ServiceUtilisateurException;
import org.bodywired.api.service.UtilisateurService.PlanningInexistantException;
import org.bodywired.api.service.UtilisateurService.RecetteInexistanteException;
import org.bodywired.api.service.UtilisateurService.UtilisateurExistantException;
import org.bodywired.api.service.UtilisateurService.UtilisateurInexistantException;
import org.bodywired.api.service.UtilisateurService.UtilisateurNonAuthentifieException;
import org.bodywired.api.utils.BodywiredURL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

@Controller
@Api(value = BodywiredURL.ROOT_UTILISATEURS, description = "Gestion des utilisateurs")
@RequestMapping(BodywiredURL.ROOT_UTILISATEURS)
public class UtilisateurController {
	
	@Autowired
	private UtilisateurService utilisateurService;
		
	@ApiOperation(value = BodywiredURL.AJOUTER_UTILISATEUR, notes = "ajoute un nouvel utilisateur")
	@RequestMapping(value = BodywiredURL.AJOUTER_UTILISATEUR, method = RequestMethod.GET)
	public ResponseEntity<String> ajouterUtilisateur(@PathVariable(value="login") String login, @PathVariable(value="pwd") String pwd)
			throws UtilisateurExistantException, ServiceUtilisateurException {
		
		if (utilisateurService.creerUtilisateur(login, pwd)) {
			return new ResponseEntity<String>(HttpStatus.CREATED);
		}
		
		throw new ServiceUtilisateurException("Création authentification impossible de l'utilisateur");
	}
	
	@ApiOperation(value = BodywiredURL.CONNECTER_UTILISATEUR, notes = "ajoute un nouvel utilisateur")
	@RequestMapping(value = BodywiredURL.CONNECTER_UTILISATEUR, method = RequestMethod.GET)
	public @ResponseBody Utilisateur connecterUtilisateur (@PathVariable(value="login") String login, @PathVariable(value="pwd") String pwd)
			throws UtilisateurNonAuthentifieException {
		return utilisateurService.authentifie(login,pwd);
	}
	
	@ApiOperation(value = BodywiredURL.FAVORIS, notes = "retourne les recettes favories de l'utilisateur")
	@RequestMapping(value = BodywiredURL.FAVORIS, method = RequestMethod.GET)
	public @ResponseBody Set<Recette> favoris(@PathVariable(value="userid") Integer userId)
			throws UtilisateurInexistantException {

		return utilisateurService.favoris(userId);
	}
	
	@ApiOperation(value = BodywiredURL.AJOUTER_FAVORI, notes = "ajoute une recette favorie à l'utilisateur")
	@RequestMapping(value = BodywiredURL.AJOUTER_FAVORI, method = RequestMethod.GET)
	public ResponseEntity<String> ajouterFavori(@PathVariable(value="userid") Integer userId, @PathVariable(value="recid") Integer recId)
			throws UtilisateurInexistantException, RecetteInexistanteException, ServiceUtilisateurException {
		
		if (utilisateurService.ajouterFavori(userId, recId))
			return new ResponseEntity<String>(HttpStatus.CREATED);
		
		throw new ServiceUtilisateurException("Création impossible du nouveau favori");
	}
	
	@ApiOperation(value = BodywiredURL.SUPPRIMER_FAVORI, notes = "supprime une recette favorie à l'utilisateur")
	@RequestMapping(value = BodywiredURL.SUPPRIMER_FAVORI, method = RequestMethod.GET)
	public ResponseEntity<String> supprimerFavori(@PathVariable(value="userid") Integer userId, @PathVariable(value="recid") Integer recId)
			throws UtilisateurInexistantException, RecetteInexistanteException, ServiceUtilisateurException {
		
		if (utilisateurService.supprimerFavori(userId, recId))
			return new ResponseEntity<String>(HttpStatus.ACCEPTED);
		
		throw new ServiceUtilisateurException("Suppression impossible du favori");
	}
	
	@ApiOperation(value = BodywiredURL.PLANNINGS, notes = "retourne les plannings de l'utilisateur")
	@RequestMapping(value = BodywiredURL.PLANNINGS, method = RequestMethod.GET)
	public @ResponseBody Set<Planning> plannings(@PathVariable(value="userid") Integer userId)
			throws UtilisateurInexistantException {

		return utilisateurService.plannings(userId);
	}
	
	@ApiOperation(value = BodywiredURL.AJOUTER_PLANNING, notes = "ajoute une entrée de planning à l'utilisateur")
	@RequestMapping(value = BodywiredURL.AJOUTER_PLANNING, method = RequestMethod.GET)
	public @ResponseBody Planning ajouterPlanning(@PathVariable(value="userid") Integer userId, @PathVariable(value="recid") Integer recId, @PathVariable(value="date") Long date, @PathVariable(value="repas") Integer repas)
			throws UtilisateurInexistantException, RecetteInexistanteException, ServiceUtilisateurException {
		
		Date dateObject = new Date(date);

		return utilisateurService.ajouterPlanning(userId, recId, dateObject, repas);
		
	}
	
	@ApiOperation(value = BodywiredURL.MODIFIER_PLANNING, notes = "modifie une entrée de planning à l'utilisateur")
	@RequestMapping(value = BodywiredURL.MODIFIER_PLANNING, method = RequestMethod.GET)
	public ResponseEntity<String> modifierPlanning(@PathVariable(value="planid") Integer planId, @PathVariable(value="recid") Integer recId, @PathVariable(value="date") Long date, @PathVariable(value="repas") Integer repas)
			throws RecetteInexistanteException, ServiceUtilisateurException, PlanningInexistantException {
		
		Date dateObject = new Date(date);
		
		if (utilisateurService.modifierPlanning(planId, recId, dateObject, repas))
			return new ResponseEntity<String>(HttpStatus.ACCEPTED);
		
		throw new ServiceUtilisateurException("Modification impossible de l'entrée de planning");
	}
	
	@ApiOperation(value = BodywiredURL.SUPPRIMER_PLANNING, notes = "supprime une entrée de planning à l'utilisateur")
	@RequestMapping(value = BodywiredURL.SUPPRIMER_PLANNING, method = RequestMethod.GET)
	public ResponseEntity<String> supprimerPlanning(@PathVariable(value="planid") Integer planId)
			throws ServiceUtilisateurException, PlanningInexistantException {
		
		if (utilisateurService.supprimerPlanning(planId))
			return new ResponseEntity<String>(HttpStatus.ACCEPTED);
		
		throw new ServiceUtilisateurException("Suppression impossible de l'entrée de planning");
	}
	
	/* EXCEPTIONS HANDLERS */
	@ExceptionHandler(ServiceUtilisateurException.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<String> handleException(ServiceUtilisateurException e) {
		return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
	}


	@ExceptionHandler(UtilisateurNonAuthentifieException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ResponseEntity<String> handleException(UtilisateurNonAuthentifieException e) {
		return new ResponseEntity<String>("Utilisateur non authentifié", HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(UtilisateurExistantException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ResponseEntity<String> handleException(UtilisateurExistantException e) {
		return new ResponseEntity<String>("Utilisateur existant", HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(UtilisateurInexistantException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ResponseEntity<String> handleException(UtilisateurInexistantException e) {
		return new ResponseEntity<String>("Utilisateur inexistant", HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(RecetteInexistanteException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ResponseEntity<String> handleException(RecetteInexistanteException e) {
		return new ResponseEntity<String>("Recette inexistante", HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(PlanningInexistantException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ResponseEntity<String> handleException(PlanningInexistantException e) {
		return new ResponseEntity<String>("Planning inexistant", HttpStatus.BAD_REQUEST);
	}
}
