package org.bodywired.api.model.classement;

import org.bodywired.api.model.AbstractBaseModel;

public class FamilleVariete extends AbstractBaseModel {

	private String nom;
	
	public FamilleVariete(String nom) {
		this.nom = nom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
}
