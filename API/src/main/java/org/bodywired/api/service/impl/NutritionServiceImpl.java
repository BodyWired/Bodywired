package org.bodywired.api.service.impl;

import java.util.List;

import org.bodywired.api.dao.NutritionDao;
import org.bodywired.api.model.Declinaison;
import org.bodywired.api.model.WrapperListNutriment;
import org.bodywired.api.model.nutriment.AbstractNutriment;
import org.bodywired.api.service.AlimentService;
import org.bodywired.api.service.NutritionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NutritionServiceImpl implements NutritionService {

	@Autowired
	private NutritionDao nutritionDao;

	@Autowired
	private AlimentService alimentService;

	@Override
	public Boolean sauvegarderApportNutritionnel(AbstractNutriment apportNutritionnel, Declinaison declinaison) {
		// /TODO
		return null;
	}

	@Override
	public List<AbstractNutriment> getNutriments(Integer id) {
		return nutritionDao.getNutriments(id);
	}

	@Override
	public WrapperListNutriment listerNutriments() {
		return nutritionDao.listerNutriments();
	}

}
