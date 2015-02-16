package org.bodywired.api.model.nutriment;

public class Vitamine extends AbstractNutriment {

	public Vitamine() {
	}

	@Override
	public Ref getRef() {
		return Ref.VIT;
	}

	public String code;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getUnite() {
		return "mg";
	}
}
