package org.bodywired.api.model.classement;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.bodywired.api.model.AbstractBaseModel;
import org.bodywired.api.model.Aliment;

public class CategorieAliment extends AbstractBaseModel {

	private String nom;
	
	public CategorieAliment() {
	}

	public CategorieAliment(String nom) {
		this.nom = nom;
		aliments = new HashSet<Aliment>();
	}

	String description;
	Set<Aliment> aliments;

	public Set<Aliment> getAliments() {
		return Collections.unmodifiableSet(aliments);
	}

	public String getNom() {
		return nom;
	}

}
