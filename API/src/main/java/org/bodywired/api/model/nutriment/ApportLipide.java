package org.bodywired.api.model.nutriment;

public class ApportLipide extends AbstractApportNutriment {

	private double grasSature;
	private double grasMonoInsature;
	private double grasPolyInsature;

	public double getGrasSature() {
		return grasSature;
	}

	public void setGrasSature(double grasSature) {
		this.grasSature = grasSature;
	}

	public double getGrasMonoInsature() {
		return grasMonoInsature;
	}

	public void setGrasMonoInsature(double grasMonoInsature) {
		this.grasMonoInsature = grasMonoInsature;
	}

	public double getGrasPolyInsature() {
		return grasPolyInsature;
	}

	public void setGrasPolyInsature(double grasPolyInsature) {
		this.grasPolyInsature = grasPolyInsature;
	}

	public ApportLipide() {
		super();
		grasMonoInsature = 0;
		grasPolyInsature = 0;
		grasSature = 0;
	}

}
