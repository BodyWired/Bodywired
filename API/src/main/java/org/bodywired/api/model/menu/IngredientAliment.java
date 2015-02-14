package org.bodywired.api.model.menu;

import org.bodywired.api.model.Aliment;

public class IngredientAliment extends AbstractIngredient {

	private Aliment aliment;

	public IngredientAliment() {
		super();
	}

	public IngredientAliment(Aliment aliment, Integer quantite) {
		super();
		this.quantite = quantite;
		this.aliment = aliment;
	}

	public Aliment getAliment() {
		return aliment;
	}

	public void setAliment(Aliment aliment) {
		this.aliment = aliment;
	}
	
	
}
