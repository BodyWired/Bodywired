package org.bodywired.api.model.nutriment;

import org.bodywired.api.model.ApportNutritionnel;

public class Lipide extends AbstractNutriment {

	private double dontGrasSature;
	private double dontGrasMonoInsature;
	private double dontGrasPolyInsature;

	public double getDontGrasSature() {
		return dontGrasSature;
	}

	public void setDontGrasSature(double dontGrasSature) {
		this.dontGrasSature = dontGrasSature;
	}

	public double getDontGrasMonoInsature() {
		return dontGrasMonoInsature;
	}

	public void setDontGrasMonoInsature(double dontGrasMonoInsature) {
		this.dontGrasMonoInsature = dontGrasMonoInsature;
	}

	public double getDontGrasPolyInsature() {
		return dontGrasPolyInsature;
	}

	public void setDontGrasPolyInsature(double dontGrasPolyInsature) {
		this.dontGrasPolyInsature = dontGrasPolyInsature;
	}

	public Lipide(ApportNutritionnel apportNutritionnel) {
		super(apportNutritionnel);
		dontGrasMonoInsature = 0;
		dontGrasPolyInsature = 0;
		dontGrasSature = 0;
	}

}
