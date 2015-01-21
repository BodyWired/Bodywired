package org.bodywired.api.model;

import java.util.LinkedList;
import java.util.List;

import org.bodywired.api.model.classement.Etat;

public class Declinaison extends AbstractBaseModel {

	private Aliment aliment;

	private WrapperListNutriment nutriments;

	public void setEtats(List<Etat> etats) {
		this.etats = etats;
	}

	private List<Etat> etats;

	public Declinaison() {
		etats = new LinkedList<Etat>();
		nutriments = new WrapperListNutriment();
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

	public WrapperListNutriment getNutriments() {
		return nutriments;
	}

	public void setNutriments(WrapperListNutriment nutriments) {
		this.nutriments = nutriments;
	}

}
