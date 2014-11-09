package org.bodywired.api.wrapper;

import org.springframework.stereotype.Component;

@Component
public class RechercheWrapper {

	private String filtre;
	private Integer offset;
	private Integer limit;

	public String getFiltre() {
		return filtre;
	}

	public void setFiltre(String filtre) {
		this.filtre = filtre;
	}

	public Integer getOffset() {
		return offset;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}
}
