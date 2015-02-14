package org.bodywired.api.model.classement;

import java.util.List;

import org.bodywired.api.model.AbstractBaseModel;
import org.bodywired.api.model.Aliment;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * http://www.guide-des-aliments.com/dietetique/aliments_par_categorie.html
 * 
 */
public class Categorie extends AbstractBaseModel {

	private String nom;
	private String description;

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setAliments(List<Aliment> aliments) {
		this.aliments = aliments;
	}

	@JsonIgnore
	private List<Aliment> aliments;

	public String getNom() {
		return nom;
	}

	public List<Aliment> getAliments() {
		return aliments;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
