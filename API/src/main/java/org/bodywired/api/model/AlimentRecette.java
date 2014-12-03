package org.bodywired.api.model;

public class AlimentRecette {

		private Aliment aliment;

		private Recette recette;
		
		private Integer quantite;
		
		public AlimentRecette() {}

		public AlimentRecette(Aliment aliment, Recette recette, Integer quantite) {
			this.aliment = aliment;
			this.recette = recette;
			this.quantite = quantite;
		}

		public Aliment getAliment() {
			return aliment;
		}

		public void setAliment(Aliment aliment) {
			this.aliment = aliment;
		}

		public Recette getRecette() {
			return recette;
		}

		public void setRecette(Recette recette) {
			this.recette = recette;
		}

		public Integer getQuantite() {
			return quantite;
		}

		public void setQuantite(Integer quantite) {
			this.quantite = quantite;
		}
}
