package org.bodywired.api.controller;

import org.bodywired.api.model.WrapperListNutriment;
import org.bodywired.api.service.NutritionService;
import org.bodywired.api.utils.BodywiredURL;
import org.bodywired.api.wrapper.NutrimentReponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

@Controller
@Api(value = BodywiredURL.ROOT_NUTRIMENTS, description = "Gestion des nutriments")
@RequestMapping(BodywiredURL.ROOT_NUTRIMENTS)
public class NutrimentController {

	@Autowired
	private NutritionService nutrimentService;

	/**
	 * Récupérer la liste de toutes les nutriments
	 * 
	 * @return toutes les nutriments
	 */
	@RequestMapping(value = BodywiredURL.LISTER_NUTRIMENTS_DECLINAISON, method = RequestMethod.GET)
	@ApiOperation(value = "Retourne la liste des nutriments d'une declinaison")
	public @ResponseBody NutrimentReponseWrapper recupererNutriments(@PathVariable Integer id) {
		return NutrimentReponseWrapper.from(nutrimentService.getNutriments(id));
	}

	@RequestMapping(value = BodywiredURL.TYPES_NUTRIMENTS, method = RequestMethod.GET)
	public @ResponseBody WrapperListNutriment listerTypeNutriments() {
		return nutrimentService.listerNutriments();
	}

}
