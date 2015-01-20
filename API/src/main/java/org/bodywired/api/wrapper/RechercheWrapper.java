package org.bodywired.api.wrapper;

import org.springframework.stereotype.Component;

import com.wordnik.swagger.annotations.ApiParam;

@Component
// @ApiModel(description = "Wrapper pour la recherche d'un aliment")
public class RechercheWrapper {

	// @ApiModelProperty("la recherche")
	@ApiParam(name = "filtre", required = true)
	private String filtre;

	// @ApiModelProperty("le décalage")
	private Integer offset;

	// @ApiModelProperty("limite de résultat")
	private Integer limite;

	private Integer idCategorie;

	public Integer getIdCategorie() {
		return idCategorie;
	}

	public void setIdCategorie(Integer idCategorie) {
		this.idCategorie = idCategorie;
	}

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

	public Integer getLimite() {
		return limite;
	}

	public void setLimite(Integer limite) {
		this.limite = limite;
	}

}
