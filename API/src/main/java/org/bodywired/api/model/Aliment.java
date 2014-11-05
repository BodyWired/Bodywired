package org.bodywired.api.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.bodywired.api.model.classement.CategorieAliment;

public class Aliment extends AbstractBaseModel {
	private String nom;

	public Aliment() {
		declinaisons = new HashSet<Declinaison>();
	}

	public Aliment(String nom) {
		this();
		this.nom = nom;
	}

	/*Variete variete;
	FamilleVariete familleVariete;*/
	
	private CategorieAliment categorieAliment;
	Set<Declinaison> declinaisons;
	
	public void setCategorieAliment(CategorieAliment categorieAliment) {
		this.categorieAliment = categorieAliment;
	}

	public String getNom() {
		return nom;
	}

	public Set<Declinaison> getDeclinaisons() {
		return Collections.unmodifiableSet(declinaisons);
	}
	
	public CategorieAliment getCategorieAliment() {
		return categorieAliment;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}

}
