package org.bodywired.api.service.impl;

import org.bodywired.api.dao.NutritionDao;
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

}
