package org.bodywired.api.dao;

import org.bodywired.api.model.menu.CategorieRecette;

public interface ClassementRecetteDao {

	Boolean sauvegarderCategorieRecette ( CategorieRecette categorie );

	CategorieRecette rechercherCategorieRecette ( String text );

}
