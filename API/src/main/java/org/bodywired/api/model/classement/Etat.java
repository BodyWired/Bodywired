package org.bodywired.api.model.classement;

import org.bodywired.api.model.AbstractBaseModel;

public class Etat extends AbstractBaseModel {

	private String nom;

	public Etat() {
	}

	public String getNom() {
		return nom;
	}

	public Etat(String nom) {
		super();
		this.nom = nom;
	}

}
