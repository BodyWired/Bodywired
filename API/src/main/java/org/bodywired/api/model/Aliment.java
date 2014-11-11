package org.bodywired.api.model;

import java.util.LinkedHashSet;
import java.util.Set;

import org.bodywired.api.model.classement.Categorie;

public class Aliment extends AbstractBaseModel {

	private String nom;

	private Set<Categorie> categories;

	private Set<Declinaison> declinaisons;

	public Aliment() {
		categories = new LinkedHashSet<Categorie>();
	}

	public String getNom() {
		return nom;
	}

	public Set<Declinaison> getDeclinaisons() {
		return declinaisons;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setDeclinaisons(Set<Declinaison> declinaisons) {
		this.declinaisons = declinaisons;
	}

	public void addCategorieAliment(Categorie categorieAliment) {
	}

	public Set<Categorie> getCategories() {
		return categories;
	}

	public void setCategories(Set<Categorie> categories) {
		this.categories = categories;
	}

	public void addCategorie(Categorie categorieAliment) {
		categories.add(categorieAliment);
	}
}