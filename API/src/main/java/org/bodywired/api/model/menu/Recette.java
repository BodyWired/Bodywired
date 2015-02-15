package org.bodywired.api.model.menu;

import java.util.ArrayList;
import java.util.List;

import org.bodywired.api.model.AbstractBaseModel;
import org.bodywired.api.model.Aliment;
import org.bodywired.api.utils.TypeIngredient;

public class Recette extends AbstractBaseModel implements Ingredient {

	private String							nom;

	private List <CategorieRecette>			categories;
	private Integer							note;

	// Pour 500g
	private List <IngredientRecette>		recettes;
	private List <IngredientAliment>		aliments;

	private Integer							tmpPreparation;
	private Integer							tmpCuisson;
	private Integer							tmpRefrigeration;
	private Integer							tmpMaceration;

	private String							preparation;

	// Pour 500g
	private Integer							calories;

	public Recette () {
		this.categories = new ArrayList <CategorieRecette>();
		this.recettes = new ArrayList <IngredientRecette>();
		this.aliments = new ArrayList <IngredientAliment>();
	}
	
	

	public Recette(String nom,
			Integer tmpPreparation,
			Integer tmpCuisson,
			Integer tmpRefrigeration,
			Integer tmpMaceration,
			String preparation,
			Integer calories) {
		super();
		this.nom = nom;
		this.tmpPreparation = tmpPreparation;
		this.tmpCuisson = tmpCuisson;
		this.tmpRefrigeration = tmpRefrigeration;
		this.tmpMaceration = tmpMaceration;
		this.preparation = preparation;
		this.calories = calories;
		this.categories = new ArrayList <CategorieRecette>();
		this.recettes = new ArrayList <IngredientRecette>();
		this.aliments = new ArrayList <IngredientAliment>();
	}



	/**
	 * @return the nom
	 */
	public String getNom () {
		return nom;
	}

	/**
	 * @param nom
	 *            the nom to set
	 */
	public void setNom ( String nom ) {
		this.nom = nom;
	}

	/**
	 * @return the categories
	 */
	public List <CategorieRecette> getCategories () {
		return categories;
	}

	/**
	 * @param categories
	 *            the categories to set
	 */
	public void setCategories ( ArrayList <CategorieRecette> categories ) {
		this.categories = categories;
	}

	/**
	 * @param categorie
	 *            the categorie to add
	 */
	public void addCategories ( CategorieRecette categorie ) {
		this.categories.add(categorie);
	}

	/**
	 * @return the aliments
	 */
	public List <IngredientAliment> getAliments () {
		return aliments;
	}

	/**
	 * @param ingredients
	 *            the ingredients to set
	 */
	public void setAliments ( List <IngredientAliment> aliments ) {
		this.aliments = aliments;
	}

	public void addAliment ( Aliment aliment, int quantite ) {
		this.aliments.add(new IngredientAliment(aliment, quantite));
	}


	/**
	 * @return the recettes
	 */
	public List <IngredientRecette> getRecettes () {
		return recettes;
	}

	/**
	 * @param recettes
	 *            the recettes to set
	 */
	public void setRecettes ( List <IngredientRecette> recettes ) {
		this.recettes = recettes;
	}

	public void addRecette ( Recette recette, int quantite ) {
		this.recettes.add(new IngredientRecette(recette, quantite));
	}

	/**
	 * @return the preparation
	 */
	public String getPreparation () {
		return preparation;
	}

	/**
	 * @param preparation
	 *            the preparation to set
	 */
	public void setPreparation ( String preparation ) {
		this.preparation = preparation;
	}

	/**
	 * @return the calories
	 */
	public Integer getCalories () {
		return calories;
	}

	/**
	 * @param calories
	 *            the calories to set
	 */
	public void setCalories ( Integer calories ) {
		this.calories = calories;
	}

	@Override
	public TypeIngredient getType () {
		return TypeIngredient.REC;
	}

	public Integer getTmpPreparation () {
		return tmpPreparation;
	}

	public void setTmpPreparation ( Integer tmpPreparation ) {
		this.tmpPreparation = tmpPreparation;
	}

	public Integer getTmpCuisson () {
		return tmpCuisson;
	}

	public void setTmpCuisson ( Integer tmpCuisson ) {
		this.tmpCuisson = tmpCuisson;
	}

	public Integer getTmpRefrigeration () {
		return tmpRefrigeration;
	}

	public void setTmpRefrigeration ( Integer tmpRefrigeration ) {
		this.tmpRefrigeration = tmpRefrigeration;
	}

	public Integer getTmpMaceration () {
		return tmpMaceration;
	}

	public void setTmpMaceration ( Integer tmpMaceration ) {
		this.tmpMaceration = tmpMaceration;
	}

	public void setCategories(List<CategorieRecette> categories) {
		this.categories = categories;
	}

	public Integer getNote() {
		return note;
	}

	public void setNote(Integer note) {
		this.note = note;
	}

}
