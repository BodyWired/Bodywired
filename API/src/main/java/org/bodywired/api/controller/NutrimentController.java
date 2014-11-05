package org.bodywired.api.controller;

import java.util.List;

import org.bodywired.api.model.nutriment.AbstractApportNutriment;
import org.bodywired.api.service.NutritionService;
import org.bodywired.api.utils.BodywiredURL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class NutrimentController {

	@Autowired
	private NutritionService nutrimentService;

	/**
	 * Récupérer la liste de toutes les nutriments
	 * 
	 * @return toutes les nutriments
	 */
	@RequestMapping(value = BodywiredURL.LISTER_NUTRIMENTS, method = RequestMethod.GET)
	public @ResponseBody List<AbstractApportNutriment> recupererNutriments() {
		return nutrimentService.getNutriments();
	}

}
