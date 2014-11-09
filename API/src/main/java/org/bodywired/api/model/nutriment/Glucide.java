package org.bodywired.api.model.nutriment;

public class Glucide extends AbstractNutriment {

	private double fibreAlimentaire;

	public double getFibreAlimentaire() {
		return fibreAlimentaire;
	}

	public void setFibreAlimentaire(double fibreAlimentaire) {
		this.fibreAlimentaire = fibreAlimentaire;
	}

	@Override
	public CodeBDD getCodeBDD() {
		return CodeBDD.GLU;
	}

}
