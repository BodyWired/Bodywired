package org.bodywired.api.model.menu;

import java.util.List;

import org.bodywired.api.model.AbstractBaseModel;

public class Recette extends AbstractBaseModel {

	private String nom;
	private Integer tempsDePreparation;
	private Integer tempsDeCuisson;
	private Integer tempsDeRefrigeration;
	private Integer tempsDeMaceration;
	private Integer portion;
	private Integer calories;
	private String instrutions;

	private List<CategorieRecette> categoriesRecette;

	private List<AbstractIngredient> ingredients;

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Integer getTempsDePreparation() {
		return tempsDePreparation;
	}

	public void setTempsDePreparation(Integer tempsDePreparation) {
		this.tempsDePreparation = tempsDePreparation;
	}

	public Integer getTempsDeCuisson() {
		return tempsDeCuisson;
	}

	public void setTempsDeCuisson(Integer tempsDeCuisson) {
		this.tempsDeCuisson = tempsDeCuisson;
	}

	public Integer getTempsDeRefrigeration() {
		return tempsDeRefrigeration;
	}

	public void setTempsDeRefrigeration(Integer tempsDeRefrigeration) {
		this.tempsDeRefrigeration = tempsDeRefrigeration;
	}

	public Integer getTempsDeMaceration() {
		return tempsDeMaceration;
	}

	public void setTempsDeMaceration(Integer tempsDeMaceration) {
		this.tempsDeMaceration = tempsDeMaceration;
	}

	public Integer getPortion() {
		return portion;
	}

	public void setPortion(Integer portion) {
		this.portion = portion;
	}

	public Integer getCalories() {
		return calories;
	}

	public void setCalories(Integer calories) {
		this.calories = calories;
	}

	public String getInstrutions() {
		return instrutions;
	}

	public void setInstrutions(String instrutions) {
		this.instrutions = instrutions;
	}

	public List<AbstractIngredient> getIngredients() {
		return ingredients;
	}

	public List<CategorieRecette> getCategoriesRecette() {
		return categoriesRecette;
	}

}
