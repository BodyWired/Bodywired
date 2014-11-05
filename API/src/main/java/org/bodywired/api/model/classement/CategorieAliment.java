package org.bodywired.api.model.classement;

import java.util.Collections;
import java.util.List;

import org.bodywired.api.model.AbstractBaseModel;
import org.bodywired.api.model.Aliment;

/**
 * http://www.guide-des-aliments.com/dietetique/aliments_par_categorie.html
 * 
 */
public class CategorieAliment extends AbstractBaseModel {

	private String nom;

	private List<Aliment> aliments;

	public CategorieAliment(String nom) {
		super();
		this.nom = nom;
	}

	public CategorieAliment() {
	}

	public String getNom() {
		return nom;
	}

	public List<Aliment> getAliments() {
		return Collections.unmodifiableList(aliments);
	}
}
