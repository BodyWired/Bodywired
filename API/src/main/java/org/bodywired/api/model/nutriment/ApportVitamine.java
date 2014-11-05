package org.bodywired.api.model.nutriment;

public class ApportVitamine extends AbstractApportNutriment {

	private TYPE type;

	public ApportVitamine(TYPE type) {
		super();
		this.type = type;
	}

	public static enum TYPE {
		A, B1, B2, B3, B5, B6, B9, B12, C, D, E, K
	}

	@Override
	public boolean equals(Object arg0) {
		if (arg0 instanceof ApportVitamine) {
			return type.equals(((ApportVitamine) arg0).type);
		} else
			return false;
	}
}
