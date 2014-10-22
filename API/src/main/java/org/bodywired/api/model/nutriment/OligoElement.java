package org.bodywired.api.model.nutriment;

import org.bodywired.api.model.ApportNutritionnel;



public class OligoElement extends AbstractNutriment {
	private CODE type;

	public OligoElement(ApportNutritionnel apportNutritionnel, CODE type) {
		super(apportNutritionnel);
		this.type = type;
	}

	public static enum CODE {
		Fe("fer"), Zn("zinc"), Cu("cuivre"), Se("selenium");

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
