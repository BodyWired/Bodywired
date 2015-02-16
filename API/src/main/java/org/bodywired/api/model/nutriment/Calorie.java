package org.bodywired.api.model.nutriment;

public class Calorie extends AbstractNutriment {

	@Override
	public Ref getRef() {
		return Ref.CAL;
	}

	public String getUnite() {
		return "kCal";
	}

}
