package org.bodywired.api.utils;

public class BodywiredURL {

	/**
	 * URL pour les aliments
	 */
	public final static String ROOT_ALIMENTS = "aliment";
	public final static String AJOUTER_ALIMENT = "ajouter";
	public final static String LISTER_ALIMENTS = "lister";

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
	public final static String AJOUTER_MENU = "menu/ajouter";
	public final static String LISTER_MENU = "menu/lister";
	public final static String TOTAL_MENU = "menu/total";
	public final static String CATEGORIES_MENU = "menu/categories";
}
