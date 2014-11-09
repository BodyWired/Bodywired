package org.bodywired.api.model.classement;

import java.util.List;

import org.bodywired.api.model.AbstractBaseModel;
import org.bodywired.api.model.Aliment;

/**
 * http://www.guide-des-aliments.com/dietetique/aliments_par_categorie.html
 * 
 */
public class Categorie extends AbstractBaseModel {

	private String nom;

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setAliments(List<Aliment> aliments) {
		this.aliments = aliments;
	}

	private List<Aliment> aliments;

	public String getNom() {
		return nom;
	}

	public List<Aliment> getAliments() {
		return aliments;
	}
}
