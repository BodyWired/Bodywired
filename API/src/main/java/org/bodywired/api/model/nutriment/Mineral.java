package org.bodywired.api.model.nutriment;

public class Mineral extends AbstractNutriment {

	public String code;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Mineral() {
	}

	@Override
	public Ref getRef() {
		return Ref.MIN;
	}

	public String getUnite() {
		return "mg";
	}

}
