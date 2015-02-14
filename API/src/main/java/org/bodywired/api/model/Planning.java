package org.bodywired.api.model;

import java.util.Date;

import org.bodywired.api.model.menu.Recette;

public class Planning extends AbstractBaseModel {

	private Integer	repas;
	private Date	date;
	
	private Utilisateur utilisateur;
	private Recette recette;
	
	
	public Integer getRepas() {
		return repas;
	}
	public void setRepas(Integer repas) {
		this.repas = repas;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	public Recette getRecette() {
		return recette;
	}
	public void setRecette(Recette recette) {
		this.recette = recette;
	}
}
