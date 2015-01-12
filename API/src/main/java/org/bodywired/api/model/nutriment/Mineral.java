package org.bodywired.api.model.nutriment;

import org.bodywired.api.model.ApportNutritionnel;


public class Mineral extends AbstractNutriment {

	private CODE type;

	public Mineral(ApportNutritionnel apportNutritionnel, CODE type) {
		super(apportNutritionnel);
		this.type = type;
	}

	public static enum CODE {
		Ca("calcium"), Mg("magnesium"), P("phosphore"), K("potassium"), Na(
				"sodium");

		private String nom;
		
		public static CODE getType(String text) {
			for (CODE c : values()) {
				if (c.getNom().equalsIgnoreCase(text)) {
					return c;
				}
			}
			return null;
		}

		private CODE(String nom) {
			this.nom = nom;
		}

		public String getNom() {
			return nom;
		}
		
	}

}
