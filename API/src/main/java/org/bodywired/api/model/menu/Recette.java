package org.bodywired.api.model.menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.bodywired.api.model.AbstractBaseModel;

public class Recette extends AbstractBaseModel implements Ingredient {

	private String							nom;

	private ArrayList <CategorieRecette>	categories;

	// Pour 500g
	private Map <Ingredient, Integer>		ingredients;

	private int								temps;

	private String							preparation;

	// Pour 500g
	private int								calories;

	public Recette () {
		this.categories = new ArrayList <CategorieRecette>();
		this.ingredients = new HashMap <Ingredient, Integer>();
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
	public ArrayList <CategorieRecette> getCategories () {
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
	 * @return the ingredients
	 */
	public Map <Ingredient, Integer> getIngredients () {
		return ingredients;
	}

	/**
	 * @param ingredients
	 *            the ingredients to set
	 */
	public void setIngredients ( Map <Ingredient, Integer> ingredients ) {
		this.ingredients = ingredients;
	}

	public void addIngredient ( Ingredient ingredient, int quantite ) {
		this.ingredients.put(ingredient, quantite);
	}

	/**
	 * @return the temps
	 */
	public int getTemps () {
		return temps;
	}

	/**
	 * @param temps
	 *            the temps to set
	 */
	public void setTemps ( int temps ) {
		this.temps = temps;
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
	public int getCalories () {
		return calories;
	}

	/**
	 * @param calories
	 *            the calories to set
	 */
	public void setCalories ( int calories ) {
		this.calories = calories;
	}

	@Override
	public String getType () {
		return "Recette";
	}

}
