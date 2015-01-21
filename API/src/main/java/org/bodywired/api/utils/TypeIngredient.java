package org.bodywired.api.utils;

public enum TypeIngredient {
	ALIMENT("ALI"), RECETTE("REC");
	
	private String codeBDD;
	private TypeIngredient (String code) {
		codeBDD = code;
	}
	
	
	public String getCodeBDD () {
		return codeBDD;
	}
}
