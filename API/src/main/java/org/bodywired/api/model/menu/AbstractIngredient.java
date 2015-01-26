package org.bodywired.api.model.menu;

import org.bodywired.api.model.AbstractBaseModel;

public abstract class AbstractIngredient extends AbstractBaseModel {

	protected Integer quantite;

	public Integer getQuantite() {
		return quantite;
	}

	public void setQuantite(Integer quantite) {
		this.quantite = quantite;
	}
}
