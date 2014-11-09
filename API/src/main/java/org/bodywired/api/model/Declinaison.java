package org.bodywired.api.model;

import java.util.LinkedList;
import java.util.List;

import org.bodywired.api.model.classement.Etat;
import org.bodywired.api.model.nutriment.AbstractNutriment;

public class Declinaison extends AbstractBaseModel {

	private Aliment aliment;

	public void setEtats(List<Etat> etats) {
		this.etats = etats;
	}

	private List<AbstractNutriment> apportsNutriment;
	private List<Etat> etats;

	public Declinaison() {
		etats = new LinkedList<Etat>();
		apportsNutriment = new LinkedList<AbstractNutriment>();
	}

	public void setAliment(Aliment aliment) {
		this.aliment = aliment;
	}

	public Aliment getAliment() {
		return aliment;
	}

	public List<Etat> getEtats() {
		return etats;
	}

	public void addEtat(Etat etat) {
		etats.add(etat);
	}

	public List<AbstractNutriment> getApportsNutriment() {
		return apportsNutriment;
	}

	public void setApportsNutriment(List<AbstractNutriment> apportsNutriment) {
		this.apportsNutriment = apportsNutriment;
	}

	public void addApportNutriment(AbstractNutriment nutriment) {
		apportsNutriment.add(nutriment);
	}

}
