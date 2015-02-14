package org.bodywired.api.model.classement;

import org.bodywired.api.model.AbstractBaseModel;

public class Etat extends AbstractBaseModel {

	private String nom;

	public Etat() {
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

}
