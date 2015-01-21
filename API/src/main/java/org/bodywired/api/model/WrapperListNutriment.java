package org.bodywired.api.model;

import java.util.List;

import org.bodywired.api.model.nutriment.Calorie;
import org.bodywired.api.model.nutriment.Cholesterol;
import org.bodywired.api.model.nutriment.Eau;
import org.bodywired.api.model.nutriment.Glucide;
import org.bodywired.api.model.nutriment.Lipide;
import org.bodywired.api.model.nutriment.Mineral;
import org.bodywired.api.model.nutriment.OligoElement;
import org.bodywired.api.model.nutriment.Proteine;
import org.bodywired.api.model.nutriment.Vitamine;

public class WrapperListNutriment {

	private List<Vitamine> vitamines;
	private List<OligoElement> oligosElements;
	private List<Mineral> mineraux;

	private Calorie calorie;
	private Cholesterol cholesterol;
	private Eau eau;
	private Glucide glucide;
	private Lipide lipide;
	private Proteine proteine;

	public WrapperListNutriment() {
		calorie = new Calorie();
		cholesterol = new Cholesterol();
		eau = new Eau();
		glucide = new Glucide();
		lipide = new Lipide();
		proteine = new Proteine();
	}

	public List<Vitamine> getVitamines() {
		return vitamines;
	}

	public void setVitamines(List<Vitamine> vitamines) {
		this.vitamines = vitamines;
	}

	public List<OligoElement> getOligosElements() {
		return oligosElements;
	}

	public void setOligosElements(List<OligoElement> oligosElements) {
		this.oligosElements = oligosElements;
	}

	public List<Mineral> getMineraux() {
		return mineraux;
	}

	public void setMineraux(List<Mineral> mineraux) {
		this.mineraux = mineraux;
	}

	public Calorie getCalorie() {
		return calorie;
	}

	public void setCalorie(Calorie calorie) {
		this.calorie = calorie;
	}

	public Cholesterol getCholesterol() {
		return cholesterol;
	}

	public void setCholesterol(Cholesterol cholesterol) {
		this.cholesterol = cholesterol;
	}

	public Eau getEau() {
		return eau;
	}

	public void setEau(Eau eau) {
		this.eau = eau;
	}

	public Glucide getGlucide() {
		return glucide;
	}

	public void setGlucide(Glucide glucide) {
		this.glucide = glucide;
	}

	public Lipide getLipide() {
		return lipide;
	}

	public void setLipide(Lipide lipide) {
		this.lipide = lipide;
	}

	public Proteine getProteine() {
		return proteine;
	}

	public void setProteine(Proteine proteine) {
		this.proteine = proteine;
	}

}
