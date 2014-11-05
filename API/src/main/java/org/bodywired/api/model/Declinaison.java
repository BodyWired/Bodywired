package org.bodywired.api.model;

import java.util.Collections;
import java.util.List;

import org.bodywired.api.model.classement.Etat;
import org.bodywired.api.model.nutriment.AbstractApportNutriment;

public class Declinaison extends AbstractBaseModel {

	private Aliment aliment;
	private AbstractApportNutriment apportNutriment;
	private List<Etat> etats;

	public Declinaison() {
	}

	public void setAliment(Aliment aliment) {
		this.aliment = aliment;
	}

	public Aliment getAliment() {
		return aliment;
	}

	public List<Etat> getEtats() {
		return Collections.unmodifiableList(etats);
	}

}
