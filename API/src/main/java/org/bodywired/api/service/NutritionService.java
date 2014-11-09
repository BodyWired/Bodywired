package org.bodywired.api.service;

import java.util.List;

import org.bodywired.api.model.Declinaison;
import org.bodywired.api.model.nutriment.AbstractNutriment;

public interface NutritionService {

	public List<AbstractNutriment> getNutriments(Integer id);

	Boolean sauvegarderApportNutritionnel(AbstractNutriment apportNutritionnel,
			Declinaison declinaison);
}
