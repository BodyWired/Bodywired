package org.bodywired.api.controller;

public class BodywiredURL {

	/**
	 * URL pour les aliments
	 */
	public final static String	ajouterALIMENT		= "aliment/ajouter";
	public final static String	listerALIMENTS		= "aliment/lister/{offset}/{limit}";
	public final static String	totalALIMENTS		= "aliment/total";
	public final static String	categoriesALIMENT	= "aliment/categories";

	/**
	 * URL pour les menus
	 */
	public final static String	ajouterMENU			= "menu/ajouter";
	public final static String	listerMENU			= "menu/lister";
	public final static String	totalMENU			= "menu/total";
	public final static String	categoriesMENU		= "menu/categories";

}
