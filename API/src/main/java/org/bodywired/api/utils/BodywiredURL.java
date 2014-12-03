package org.bodywired.api.utils;

public class BodywiredURL {

	/**
	 * URL pour les aliments
	 */
	public final static String ROOT_ALIMENTS = "aliment";
	public final static String AJOUTER_ALIMENT = "ajouter";
	public final static String LISTER_ALIMENTS = "lister";
	public final static String TOTAL_ALIMENTS = "total";

	/**
	 * URL pour les categories
	 */
	public final static String ROOT_CATEGORIES = "categories";
	public final static String LISTER_CATEGORIES = "lister";
	public final static String AJOUTER_CATEGORIE = "ajouter";

	/**
	 * URL pour les déclinaisons
	 */
	public final static String ROOT_DECLINAISONS = "declinaison";
	public final static String AJOUTER_DECLINAISON = "ajouter";
	public final static String LISTER_DECLINAISON = "lister";
	public final static String AJOUTER_ETAT_DECLINAISON = "etat/ajouter";
	public final static String LISTER_ETAT_DECLINAISON = "etat/lister";

	/**
	 * URL pour les nutriments
	 */
	public final static String ROOT_NUTRIMENTS = "nutriments";
	public final static String LISTER_NUTRIMENTS_DECLINAISON = "dec/{id}";
	public static final String TYPES_NUTRIMENTS = "types";

	/**
	 * URL pour les menus
	 */
	public final static String ROOT_MENUS = "menu";
	public final static String AJOUTER_MENU = "ajouter";
	public final static String LISTER_MENUS = "lister";
	public final static String TOTAL_MENUS = "total";
	public final static String CATEGORIES_MENUS = "categories";
	
	/**
	 * URL pour les recettes
	 */
	public final static String ROOT_RECETTES = "recettes";
	public final static String AJOUTER_RECETTE = "ajouter";
	public final static String LISTER_RECETTES = "lister";
	public final static String TOTAL_RECETTES = "total";
	public final static String CATEGORIES_RECETTES = "categories";
}
