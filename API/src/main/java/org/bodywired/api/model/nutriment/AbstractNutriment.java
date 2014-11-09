package org.bodywired.api.model.nutriment;

import org.bodywired.api.model.AbstractBaseModel;
import org.bodywired.api.model.Declinaison;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public abstract class AbstractNutriment extends AbstractBaseModel {

	public enum CodeBDD {
		CAL, CHO, EAU, PRO, LIP, GLU, VIT, MIN, OEL
	}

	public static enum VIT_TYPE {
		A, B1, B2, B3, B5, B6, B9, B12, C, D, E, K;

		@JsonValue
		public String value() {
			return "vitamine " + this;
		}

		@JsonCreator
		public static VIT_TYPE forValue(String v) {
			for (VIT_TYPE type : values()) {
				if (type.value().equals(v)) {
					return type;
				}
			}
			return null;
		}
	}

	public static enum OEL_TYPE {
		Fe("fer"), Zn("zinc"), Cu("cuivre"), Se("selenium");

		private String nom;

		@JsonCreator
		public static OEL_TYPE getType(String text) {
			for (OEL_TYPE c : values()) {
				if (c.getNom().equalsIgnoreCase(text)) {
					return c;
				}
			}
			return null;
		}

		private OEL_TYPE(String nom) {
			this.nom = nom;
		}

		@JsonValue
		public String getNom() {
			return nom;
		}

	}

	public static enum MIN_TYPE {
		Ca("calcium"), Mg("magnesium"), P("phosphore"), K("potassium"), Na(
				"sodium");

		private String nom;

		@JsonCreator
		public static MIN_TYPE getType(String text) {
			for (MIN_TYPE c : values()) {
				if (c.getNom().equalsIgnoreCase(text)) {
					return c;
				}
			}
			return null;
		}

		private MIN_TYPE(String nom) {
			this.nom = nom;
		}

		@JsonValue
		public String getNom() {
			return nom;
		}

	}

	public abstract CodeBDD getCodeBDD();

	/**
	 * Quantité apports nutritionnel pour 100 grammes de la déclinaison
	 */
	public static final int QUANTITE_REF = 100;

	Declinaison declinaison;

	private double apport;

	private VIT_TYPE typeVitamine;
	private OEL_TYPE typeOligoElement;
	private MIN_TYPE typeMineral;

	private String details;

	public AbstractNutriment() {
		apport = 0.0;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getDetails() {
		return details;
	}

	public double getApport() {
		return apport;
	}

	public void setApport(double apport) {
		this.apport = apport;
	}

	public VIT_TYPE getTypeVitamine() {
		return typeVitamine;
	}

	public void setTypeVitamine(VIT_TYPE typeVitamine) {
		this.typeVitamine = typeVitamine;
	}

	public OEL_TYPE getTypeOligoElement() {
		return typeOligoElement;
	}

	public void setTypeOligoElement(OEL_TYPE typeOligoElement) {
		this.typeOligoElement = typeOligoElement;
	}

	public MIN_TYPE getTypeMineral() {
		return typeMineral;
	}

	public void setTypeMineral(MIN_TYPE typeMineral) {
		this.typeMineral = typeMineral;
	}

}
