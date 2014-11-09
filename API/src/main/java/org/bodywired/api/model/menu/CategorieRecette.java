package org.bodywired.api.model.menu;

import java.util.List;

import org.bodywired.api.model.AbstractBaseModel;

public class CategorieRecette extends AbstractBaseModel {
	private String nom;

	private List<Recette> recettes;

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<Recette> getRecettes() {
		return recettes;
	}
}
