package org.bodywired.api.service;

import java.util.List;

import org.bodywired.api.model.Aliment;
import org.bodywired.api.model.classement.Categorie;

public interface CategorieService {

	Boolean sauvegarderCategorieAliment(Categorie categorie);

	List<Categorie> getCategories();

	Boolean ajouterAlimentDansCategorie(Aliment aliment, Categorie categorie);

}
