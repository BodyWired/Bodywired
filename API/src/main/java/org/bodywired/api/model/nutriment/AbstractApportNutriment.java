package org.bodywired.api.model.nutriment;

import org.bodywired.api.model.AbstractBaseModel;
import org.bodywired.api.model.Declinaison;

public abstract class AbstractApportNutriment extends AbstractBaseModel {

	/**
	 * Quantité apports nutritionnel pour 100 grammes de la déclinaison
	 */
	public static final int QUANTITE_REF = 100;

	private double quantite;

	Declinaison declinaison;

	private String details;

	public AbstractApportNutriment() {
		quantite = 0.0;
	}

	public void setQuantite(double quantite) {
		this.quantite = quantite;
	}

	public double getQuantite() {
		return quantite;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getDetails() {
		return details;
	}

}
