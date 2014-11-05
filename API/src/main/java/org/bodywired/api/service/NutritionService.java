package org.bodywired.api.service;

import java.util.List;

import org.bodywired.api.model.ApportNutritionnel;
import org.bodywired.api.model.Declinaison;

public interface NutritionService {

	public boolean sauvegarderApportNutritionnel ( ApportNutritionnel apportNutritionnel,
			Declinaison declinaison );

	public List <ApportNutritionnel> getNutriments ();
}
