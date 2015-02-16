package org.bodywired.api.model.nutriment;

public class Cholesterol extends AbstractNutriment {

	@Override
	public Ref getRef() {
		return Ref.CHO;
	}

	public String getUnite() {
		return "mg";
	}

}
