package org.bodywired.api.model.menu;

import org.bodywired.api.model.AbstractBaseModel;

public abstract class AbstractIngredient extends AbstractBaseModel {

	private Integer quantite;
	private String note;
	private Recette recette;

	public Integer getQuantite() {
		return quantite;
	}

	public void setQuantite(Integer quantite) {
		this.quantite = quantite;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Recette getRecette() {
		return recette;
	}

	public void setRecette(Recette recette) {
		this.recette = recette;
	}
}
