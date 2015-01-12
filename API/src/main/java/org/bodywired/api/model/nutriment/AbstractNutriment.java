package org.bodywired.api.model.nutriment;

import org.bodywired.api.model.AbstractBaseModel;
import org.bodywired.api.model.ApportNutritionnel;

public abstract class AbstractNutriment extends AbstractBaseModel {
	private String nom;
	private double quantite;
	private String details;
	
	private ApportNutritionnel apportNutritionnel;
	
	public AbstractNutriment(ApportNutritionnel apportNutritionnel) {
		this.apportNutritionnel = apportNutritionnel;
		quantite = 0.0;
	}
	
	public String getNom() {
		return nom;
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

	public ApportNutritionnel getApportNutritionnel() {
		return apportNutritionnel;
	}

	public void setApportNutritionnel(ApportNutritionnel apportNutritionnel) {
		this.apportNutritionnel = apportNutritionnel;
	}
}
