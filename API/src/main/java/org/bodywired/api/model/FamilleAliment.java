package org.bodywired.api.model;

import java.util.List;

import org.bodywired.api.model.nutrition.ValeurNutritive;

/**
 * http://www.guide-des-aliments.com/dietetique/cereales_8.html
 * @author Antoine
 *
 */
public class FamilleAliment extends AbstractBaseModel {

	private String nom;
	private CategorieAliment familleAliment;
	private List<Aliment> declinaisons;
	
}
