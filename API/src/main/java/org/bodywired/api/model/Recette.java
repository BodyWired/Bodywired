package org.bodywired.api.model;

import java.util.List;

public class Recette {

	private String nom;
	
	private Integer temps;
	
	private Boolean vegetarien;
	
	private Integer note;
	
	private Integer nbPers;
	
	private List<AlimentRecette> alimentsQuantites;

	public Recette() {}

	public Recette(String nom, Integer temps, Boolean vegetarien, Integer note,
			Integer nbPers, List<AlimentRecette> alimentsQuantites) {
		super();
		this.nom = nom;
		this.temps = temps;
		this.vegetarien = vegetarien;
		this.note = note;
		this.nbPers = nbPers;
		this.alimentsQuantites = alimentsQuantites;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Integer getTemps() {
		return temps;
	}

	public void setTemps(Integer temps) {
		this.temps = temps;
	}

	public Boolean getVegetarien() {
		return vegetarien;
	}

	public void setVegetarien(Boolean vegetarien) {
		this.vegetarien = vegetarien;
	}

	public Integer getNote() {
		return note;
	}

	public void setNote(Integer note) {
		this.note = note;
	}

	public Integer getNbPers() {
		return nbPers;
	}

	public void setNbPers(Integer nbPers) {
		this.nbPers = nbPers;
	}

	public List<AlimentRecette> getAlimentsQuantités() {
		return alimentsQuantites;
	}

	public void setAlimentsQuantités(List<AlimentRecette> alimentsQuantités) {
		this.alimentsQuantites = alimentsQuantités;
	}
	
}
