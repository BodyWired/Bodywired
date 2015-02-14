package org.bodywired.api.wrapper;

import java.util.List;

import org.bodywired.api.model.Aliment;

public class ResultatRechercheWrapper {

	private Integer total;

	private List<Aliment> aliments;

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public List<Aliment> getAliments() {
		return aliments;
	}

	public void setAliments(List<Aliment> aliments) {
		this.aliments = aliments;
	}

}
