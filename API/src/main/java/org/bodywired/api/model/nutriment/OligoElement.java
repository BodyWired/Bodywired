package org.bodywired.api.model.nutriment;

public class OligoElement extends AbstractNutriment {

	public String code;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public OligoElement() {
	}

	@Override
	public Ref getRef() {
		return Ref.OEL;
	}

	public String getUnite() {
		return "mg";
	}
}
