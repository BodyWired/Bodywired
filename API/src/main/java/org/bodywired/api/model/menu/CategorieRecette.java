package org.bodywired.api.model.menu;

import java.util.ArrayList;
import java.util.List;

import org.bodywired.api.model.AbstractBaseModel;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class CategorieRecette extends AbstractBaseModel {

	public CategorieRecette() {}
	
	public CategorieRecette(String nom) {
		this.nom = nom;
		this.recettes = new ArrayList<Recette>();
	}

	private String			nom;

	private List <Recette>	recettes;

	public String getNom () {
		return nom;
	}

	public void setNom ( String nom ) {
		this.nom = nom;
	}

	@JsonIgnore
	public List <Recette> getRecettes () {
		return recettes;
	}

	public void addRecette ( Recette recette ) {
		recette.getCategories().add(this);
	}
}
