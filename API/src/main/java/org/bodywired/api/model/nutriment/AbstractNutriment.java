package org.bodywired.api.model.nutriment;

import org.bodywired.api.model.AbstractBaseModel;

public abstract class AbstractNutriment extends AbstractBaseModel {

	public enum Ref {
		CAL, CHO, EAU, PRO, LIP, GLU, VIT, MIN, OEL
	}

	public abstract Ref getRef();

	/**
	 * Quantité apports nutritionnel pour 100 grammes de la déclinaison
	 */
	public static final int QUANTITE_REF = 100;

	private double apport;

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

}
