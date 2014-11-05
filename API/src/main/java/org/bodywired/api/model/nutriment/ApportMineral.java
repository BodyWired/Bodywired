package org.bodywired.api.model.nutriment;

public class ApportMineral extends AbstractApportNutriment {

	private CODE type;

	public ApportMineral(CODE type) {
		super();
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
