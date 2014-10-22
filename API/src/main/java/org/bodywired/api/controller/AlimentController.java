package org.bodywired.api.controller;

import java.util.List;

import org.bodywired.api.model.classement.CategorieAliment;
import org.bodywired.api.service.AlimentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AlimentController {

	@Autowired
	private AlimentService alimentService;
	
//	@RequestMapping(value="/ajouter", method=RequestMethod.GET)
//	public String ajouterAliment(@RequestParam Aliment aliment) {
//		alimentService.sauvegarderAlmient(aliment);
//		return "OK";
//	}
	
	@RequestMapping(value="/aliment/categories", method=RequestMethod.GET)
	public @ResponseBody List<CategorieAliment> ajouterAliment() {
		return alimentService.getCategoriesAliements();
	}
}
