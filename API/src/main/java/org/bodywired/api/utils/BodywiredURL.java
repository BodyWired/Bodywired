package org.bodywired.api.utils;

public class BodywiredURL {

	/**
	 * URL pour les aliments
	 */
	public final static String ROOT_ALIMENTS = "aliment";
	public final static String AJOUTER_ALIMENT = "ajouter";
	public final static String LISTER_ALIMENTS = "lister";
	public static final String MODIFIER_ALIMENT = "modifier";
	public static final String SUPPRIMER_ALIMENT = "supprimer/{id}";

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
	public final static String SUPPRIMER_DECLINAISON = "rm/{id}";
	public final static String LISTER_DECLINAISON = "lister";
	public final static String AJOUTER_ETAT_DECLINAISON = "etat/ajouter";
	public final static String MODIFIER_ETAT_DECLINAISON = "etat/modifier";
	public final static String LISTER_ETAT_DECLINAISON = "etat/lister";
	public final static String SUPPRIMER_ETAT_DECLINAISON = "etat/{id}";

	/**
	 * URL pour les nutriments
	 */
	public final static String ROOT_NUTRIMENTS = "nutriments";
	public final static String LISTER_NUTRIMENTS_DECLINAISON = "dec/{id}";
	public final static String TYPES_NUTRIMENTS = "types";

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
	public final static String RECETTE = "recette/{id}";
	public final static String AJOUTER_RECETTE = "ajouter";
	public final static String LISTER_RECETTES = "lister";
	public final static String TOTAL_RECETTES = "total";
	public final static String CATEGORIES_RECETTES = "categories";
	public final static String AJOUTER_CATEGORIE_RECETTE = "categories/ajouter";
	public final static String MODIFIER_RECETTE = "modifier";
	public final static String SUPPRIMER_RECETTE = "supprimer/{id}";

	/**
	 * URL pour les utilisateurs
	 */
	public final static String ROOT_UTILISATEURS = "users";
	public final static String AJOUTER_UTILISATEUR = "ajouter/{login}/{pwd}";
	public final static String CONNECTER_UTILISATEUR = "signin/{login}/{pwd}";
	public final static String AJOUTER_FAVORI = "ajouterFavori/{userid}/{recid}";
	public final static String SUPPRIMER_FAVORI = "supprimerFavori/{userid}/{recid}";
	public final static String FAVORIS = "favoris/{userid}";
	public final static String PLANNINGS = "plannings/{userid}";
	public final static String AJOUTER_PLANNING = "ajouterPlanning/{userid}/{recid}/{date}/{repas}";
	public final static String MODIFIER_PLANNING = "modifierPlanning/{planid}/{recid}/{date}/{repas}";
	public final static String SUPPRIMER_PLANNING = "supprimerPlanning/{planid}";
}
