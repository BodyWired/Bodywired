package org.bodywired.api.model.menu;

public class IngredientRecette extends AbstractIngredient {
	private Recette recetteAssociee;

	public Recette getRecetteAssociee() {
		return recetteAssociee;
	}

	public void setRecetteAssociee(Recette recetteAssociee) {
		this.recetteAssociee = recetteAssociee;
	}
}
