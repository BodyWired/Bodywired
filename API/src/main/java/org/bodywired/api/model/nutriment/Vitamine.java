package org.bodywired.api.model.nutriment;

import org.bodywired.api.model.ApportNutritionnel;

public class Vitamine extends AbstractNutriment {

	private TYPE type;
	
	public Vitamine(ApportNutritionnel apportNutritionnel, TYPE type) {
		super(apportNutritionnel);
		this.type = type;
	}

	public static enum TYPE {
		A, B1, B2, B3, B5, B6, B9, B12, C, D, E, K
	}
	
	@Override
	public boolean equals(Object arg0) {
		if (arg0 instanceof Vitamine) {
			return type.equals(((Vitamine) arg0).type);
		}
		else return false;
	}
}
