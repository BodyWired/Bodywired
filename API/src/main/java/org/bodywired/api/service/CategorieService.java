package org.bodywired.api.service;

import java.util.List;

import org.bodywired.api.model.CategorieAliment;

public interface CategorieService {

	void sauvegarderCategorie ( CategorieAliment categorie );

	List <CategorieAliment> getCategories ();

}
