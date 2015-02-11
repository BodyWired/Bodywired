package org.bodywired.api.controller;

import java.util.Set;

import org.bodywired.api.model.Utilisateur;
import org.bodywired.api.model.menu.Recette;
import org.bodywired.api.service.UtilisateurService;
import org.bodywired.api.service.UtilisateurService.RecetteInexistanteException;
import org.bodywired.api.service.UtilisateurService.UtilisateurInexistantException;
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
	public @ResponseBody Utilisateur ajouterUtilisateur(@PathVariable(value="login") String login, @PathVariable(value="pwd") String pwd)
			throws UtilisateurExistantException, ServiceUtilisateurException {
		
		if (utilisateurService.getUtilisateurByLogin(login) != null) {
			throw new UtilisateurExistantException();
		}
		Utilisateur utilisateur = new Utilisateur ();
		utilisateur.setLogin(login);
		utilisateur.setPwd(MD5(pwd));
		
		if (utilisateurService.creerUtilisateur(utilisateur)) {
			return utilisateur;
		}
		
		throw new ServiceUtilisateurException("Création impossible du nouvel utilisateur");
	}
	
	@ApiOperation(value = BodywiredURL.CONNECTER_UTILISATEUR, notes = "ajoute un nouvel utilisateur")
	@RequestMapping(value = BodywiredURL.CONNECTER_UTILISATEUR, method = RequestMethod.GET)
	public ResponseEntity<String> connecterUtilisateur (@PathVariable(value="login") String login, @PathVariable(value="pwd") String pwd) throws ServiceUtilisateurException {
		Utilisateur utilisateur = utilisateurService.getUtilisateurByLogin(login);

		if (utilisateur == null || !utilisateur.getPwd().equals(MD5(pwd))) {
			return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
		}
		
		return new ResponseEntity<String>(HttpStatus.ACCEPTED);
	}
	
	@ApiOperation(value = BodywiredURL.FAVORIS, notes = "retourne les recettes favories de l'utilisateur")
	@RequestMapping(value = BodywiredURL.FAVORIS, method = RequestMethod.GET)
	public @ResponseBody Set<Recette> ajouterFavori(@PathVariable(value="userid") Integer userId)
			throws UtilisateurInexistantException {

		return utilisateurService.favoris(userId);
	}
	
	@ApiOperation(value = BodywiredURL.AJOUTER_FAVORI, notes = "ajoute une recette favorie à l'utilisateur")
	@RequestMapping(value = BodywiredURL.AJOUTER_FAVORI, method = RequestMethod.GET)
	public ResponseEntity<String> ajouterFavori(@PathVariable(value="userid") Integer userId, @PathVariable(value="recid") Integer recId)
			throws UtilisateurInexistantException, RecetteInexistanteException, ServiceUtilisateurException {
		
		if (utilisateurService.ajouterFavori(userId, recId))
			return new ResponseEntity<String>(HttpStatus.OK);
		
		throw new ServiceUtilisateurException("Création impossible du nouveau favori");
	}
	
	@ApiOperation(value = BodywiredURL.SUPPRIMER_FAVORI, notes = "supprime une recette favorie à l'utilisateur")
	@RequestMapping(value = BodywiredURL.SUPPRIMER_FAVORI, method = RequestMethod.GET)
	public ResponseEntity<String> supprimerFavori(@PathVariable(value="userid") Integer userId, @PathVariable(value="recid") Integer recId)
			throws UtilisateurInexistantException, RecetteInexistanteException, ServiceUtilisateurException {
		
		if (utilisateurService.supprimerFavori(userId, recId))
			return new ResponseEntity<String>(HttpStatus.OK);
		
		throw new ServiceUtilisateurException("Suppression impossible du favori");
	}
	
	
	public class UtilisateurExistantException extends Exception {
		private static final long serialVersionUID = 5638669174371926085L;
	}

	@ExceptionHandler(UtilisateurExistantException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ResponseEntity<String> handleException(UtilisateurExistantException e) {
		return new ResponseEntity<String>("Utilisateur existant", HttpStatus.BAD_REQUEST);
	}
	
	public class ServiceUtilisateurException extends Exception {
		private static final long serialVersionUID = 7041376106709430874L;
		ServiceUtilisateurException(String msg) { super(msg); }
	}
	
	@ExceptionHandler(ServiceUtilisateurException.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<String> handleException(ServiceUtilisateurException e) {
		return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
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
	
	
	private String MD5(String md5) {
		try {
			java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
			byte[] array = md.digest(md5.getBytes());
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < array.length; ++i) {
				sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
			}
			return sb.toString();
		} catch (java.security.NoSuchAlgorithmException e) {
		}
		return null;
	}
}
