package org.bodywired.api.model.nutriment;

public class Proteine extends AbstractNutriment {

	@Override
	public Ref getRef() {
		return Ref.PRO;
	}

	public String getUnite() {
		return "g";
	}

}
