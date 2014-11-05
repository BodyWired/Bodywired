package org.bodywired.api.model.nutriment;

public class AlimentOligoElement extends AbstractApportNutriment {
	private CODE type;

	public AlimentOligoElement(CODE type) {
		super();
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
