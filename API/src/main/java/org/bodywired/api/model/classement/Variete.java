package org.bodywired.api.model.classement;

import org.bodywired.api.model.AbstractBaseModel;

public class Variete extends AbstractBaseModel {

	private String nom;

	public Variete(String nom) {
		this.setNom(nom);
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
}
