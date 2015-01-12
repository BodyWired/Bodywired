package org.bodywired.api.model.nutriment;

import org.bodywired.api.model.ApportNutritionnel;

public class Glucide extends AbstractNutriment {
	
	private double dontFibresAlimentaires;

	public double getDontFibresAlimentaires() {
		return dontFibresAlimentaires;
	}

	public void setDontFibresAlimentaires(double dontFibresAlimentaires) {
		this.dontFibresAlimentaires = dontFibresAlimentaires;
	}

	public Glucide(ApportNutritionnel apportNutritionnel) {
		super(apportNutritionnel);
	}

}
