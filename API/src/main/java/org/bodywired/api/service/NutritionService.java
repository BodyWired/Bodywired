package org.bodywired.api.service;

import java.util.List;

import org.bodywired.api.model.Declinaison;
import org.bodywired.api.model.nutriment.AbstractApportNutriment;

public interface NutritionService {

	public List<AbstractApportNutriment> getNutriments();

	Boolean sauvegarderApportNutritionnel(
			AbstractApportNutriment apportNutritionnel, Declinaison declinaison);
}
