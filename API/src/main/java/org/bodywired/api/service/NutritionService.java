package org.bodywired.api.service;

import org.bodywired.api.model.ApportNutritionnel;
import org.bodywired.api.model.Declinaison;

public interface NutritionService {

	public boolean sauvegarderApportNutritionnel(
			ApportNutritionnel apportNutritionnel, Declinaison declinaison);
}
