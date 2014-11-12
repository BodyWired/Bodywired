package org.bodywired.api.model;

public class AbstractBaseModel {

	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		if (id != null) {
			return id.hashCode();
		}
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (id != null && (obj instanceof AbstractBaseModel)) {
			return id.equals(((AbstractBaseModel) obj).getId());
		}
		return super.equals(obj);
	}
}
