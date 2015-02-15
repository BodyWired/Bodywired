package org.bodywired.api.wrapper;

import java.util.LinkedList;
import java.util.List;

import org.bodywired.api.model.nutriment.AbstractNutriment;
import org.bodywired.api.model.nutriment.Calorie;
import org.bodywired.api.model.nutriment.Cholesterol;
import org.bodywired.api.model.nutriment.Eau;
import org.bodywired.api.model.nutriment.Glucide;
import org.bodywired.api.model.nutriment.Lipide;
import org.bodywired.api.model.nutriment.Mineral;
import org.bodywired.api.model.nutriment.OligoElement;
import org.bodywired.api.model.nutriment.Proteine;
import org.bodywired.api.model.nutriment.Vitamine;

public class NutrimentReponseWrapper {

	public static NutrimentReponseWrapper from(List<AbstractNutriment> nutriments) {
		NutrimentReponseWrapper response = new NutrimentReponseWrapper();
		for (AbstractNutriment abstractNutriment : nutriments) {
			switch (abstractNutriment.getRef()) {
			case CAL:
				response.setCalorie((Calorie) abstractNutriment);
				break;
			case CHO:
				response.setCholesterol((Cholesterol) abstractNutriment);
				break;
			case EAU:
				response.setEau((Eau) abstractNutriment);
				break;
			case GLU:
				response.setGlucide((Glucide) abstractNutriment);
				break;
			case LIP:
				response.setLipide((Lipide) abstractNutriment);
				break;
			case MIN:
				response.getMineraux().add((Mineral) abstractNutriment);
				break;
			case OEL:
				response.getOligoElements().add((OligoElement) abstractNutriment);
				break;
			case PRO:
				response.setProteine((Proteine) abstractNutriment);
				break;
			case VIT:
				response.getVitamines().add((Vitamine) abstractNutriment);
				break;
			}
		}
		return response;
	}

	private Calorie calorie;

	private Cholesterol cholesterol;

	private Eau eau;

	private Glucide glucide;

	private Lipide lipide;

	private List<Mineral> mineraux;
	private List<OligoElement> oligoElements;
	private Proteine proteine;
	private List<Vitamine> vitamines;

	public NutrimentReponseWrapper() {
		vitamines = new LinkedList<Vitamine>();
		mineraux = new LinkedList<Mineral>();
		oligoElements = new LinkedList<OligoElement>();
	}
	public Calorie getCalorie() {
		return calorie;
	}
	public Cholesterol getCholesterol() {
		return cholesterol;
	}
	public Eau getEau() {
		return eau;
	}

	public Glucide getGlucide() {
		return glucide;
	}

	public Lipide getLipide() {
		return lipide;
	}

	public List<Mineral> getMineraux() {
		return mineraux;
	}

	public List<OligoElement> getOligoElements() {
		return oligoElements;
	}

	public Proteine getProteine() {
		return proteine;
	}

	public List<Vitamine> getVitamines() {
		return vitamines;
	}

	public void setCalorie(Calorie calorie) {
		this.calorie = calorie;
	}

	public void setCholesterol(Cholesterol coCholesterol) {
		this.cholesterol = coCholesterol;
	}

	public void setEau(Eau eau) {
		this.eau = eau;
	}

	public void setGlucide(Glucide glucide) {
		this.glucide = glucide;
	}

	public void setLipide(Lipide lipide) {
		this.lipide = lipide;
	}

	public void setMineraux(List<Mineral> mineraux) {
		this.mineraux = mineraux;
	}

	public void setOligoElements(List<OligoElement> oligoElements) {
		this.oligoElements = oligoElements;
	}

	public void setProteine(Proteine proteine) {
		this.proteine = proteine;
	}

	public void setVitamines(List<Vitamine> vitamines) {
		this.vitamines = vitamines;
	}
}
