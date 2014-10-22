package org.bodywired.api.model;

import java.util.ArrayList;
import java.util.List;

import org.bodywired.api.model.nutriment.Cholesterol;
import org.bodywired.api.model.nutriment.Eau;
import org.bodywired.api.model.nutriment.Glucide;
import org.bodywired.api.model.nutriment.Lipide;
import org.bodywired.api.model.nutriment.Mineral;
import org.bodywired.api.model.nutriment.OligoElement;
import org.bodywired.api.model.nutriment.Proteine;
import org.bodywired.api.model.nutriment.Vitamine;

public class ApportNutritionnel extends AbstractBaseModel {
	public static final int quantite = 100;
	
	Declinaison declinaison;
	
	private double calories;
	Lipide lipide;
	Glucide glucide;
	Proteine proteine;
	Eau eau;
	Cholesterol cholesterol;
	
	List<Vitamine> vitamines;
	List<Mineral> mineraux;
	List<OligoElement> oligoElements;
	
	public ApportNutritionnel(double calories) {
		this.calories = calories;
		vitamines = new ArrayList<>();
		mineraux = new ArrayList<>();
		oligoElements = new ArrayList<>();
	}
	
	public double getCalories() {
		return calories;
	}

	public Lipide getLipide() {
		return lipide;
	}

	public void setLipide(Lipide lipide) {
		this.lipide = lipide;
	}

	public Glucide getGlucide() {
		return glucide;
	}

	public void setGlucide(Glucide glucide) {
		this.glucide = glucide;
	}

	public Proteine getProteine() {
		return proteine;
	}

	public void setProteine(Proteine proteine) {
		this.proteine = proteine;
	}

	public Eau getEau() {
		return eau;
	}

	public void setEau(Eau eau) {
		this.eau = eau;
	}

	public Cholesterol getCholesterol() {
		return cholesterol;
	}

	public void setCholesterol(Cholesterol cholesterol) {
		this.cholesterol = cholesterol;
	}

	public List<Vitamine> getVitamines() {
		return vitamines;
	}

	public void setVitamines(List<Vitamine> vitamines) {
		this.vitamines = vitamines;
	}

	public List<Mineral> getMineraux() {
		return mineraux;
	}

	public void setMineraux(List<Mineral> mineraux) {
		this.mineraux = mineraux;
	}

	public List<OligoElement> getOligoElements() {
		return oligoElements;
	}

	public void setOligoElements(List<OligoElement> oligoElements) {
		this.oligoElements = oligoElements;
	}

	public static int getQuantite() {
		return quantite;
	}
	
}
