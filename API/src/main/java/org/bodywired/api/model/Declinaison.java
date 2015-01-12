package org.bodywired.api.model;


public class Declinaison extends AbstractBaseModel {

	private String nom;

	public Declinaison(String nom, ApportNutritionnel apportNutritionnel) {
		this.nom = nom;
		this.apportNutritionnel = apportNutritionnel;
//		sousDeclinaisons = new ArrayList<Declinaison>();
	}

	Aliment aliment;
//	List<Declinaison> sousDeclinaisons;
	ApportNutritionnel apportNutritionnel;
	

	public String getNom() {
		return nom;
	}
	
	public void setAliment(Aliment aliment) {
		this.aliment = aliment;
	}
	
	public Aliment getAliment() {
		return aliment;
	}

//	public Declinaison addSousDeclinaisons(Declinaison declinaison) {
//		int index = sousDeclinaisons.indexOf(declinaison);
//		if (index >= 0) {
//			return sousDeclinaisons.get(index);
//		} else {
//			sousDeclinaisons.add(declinaison);
//			return declinaison;
//		}
//	}
	
//	public List<Declinaison> getSousDeclinaisons() {
//		return sousDeclinaisons;
//	}

}
