package org.bodywired.api.model.menu;

public class IngredientRecette extends AbstractIngredient {
	private Recette recetteAssociee;
	
	public IngredientRecette() {
		super();
	}

	public IngredientRecette(Recette recetteAssociee, Integer quantite) {
		super();
		this.quantite = quantite;
		this.recetteAssociee = recetteAssociee;
	}

	public Recette getRecetteAssociee() {
		return recetteAssociee;
	}

	public void setRecetteAssociee(Recette recetteAssociee) {
		this.recetteAssociee = recetteAssociee;
	}
}
