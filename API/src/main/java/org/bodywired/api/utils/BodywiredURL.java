package org.bodywired.api.utils;

public class BodywiredURL {

	/**
	 * URL pour les aliments
	 */
	public final static String AJOUTER_ALIMENT = "aliment/ajouter";
	public final static String LISTER_ALIMENTS = "aliment/lister";
	public final static String TOTAL_ALIMENTS = "aliment/total";

	/**
	 * URL pour les categories
	 */
	public final static String LISTER_CATEGORIES = "categories/lister";
	public final static String AJOUTER_CATEGORIE = "categories/ajouter";

	/**
	 * URL pour les menus
	 */
	public final static String AJOUTER_MENU = "menu/ajouter";
	public final static String LISTER_MENU = "menu/lister";
	public final static String TOTAL_MENU = "menu/total";
	public final static String CATEGORIES_MENU = "menu/categories";

	/**
	 * URL pour les déclinaisons
	 */
	public final static String AJOUTER_DECLINAISON = "declinaison/ajouter";
	public final static String LISTER_DECLINAISON = "declinaison/lister";
	public final static String AJOUTER_ETAT_DECLINAISON = "declinaison/etat/ajouter";
	public final static String LISTER_ETAT_DECLINAISON = "declinaison/etat/lister";

	/**
	 * URL pour les nutriments
	 */
	public final static String LISTER_NUTRIMENTS_DECLINAISON = "nutriments/dec/{id}";
	public static final String TYPES_NUTRIMENTS = "nutriments/types";
}
