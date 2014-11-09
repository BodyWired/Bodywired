package org.bodywired.api.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.bodywired.api.model.Aliment;
import org.bodywired.api.wrapper.RechercheWrapper;

public interface AlimentDao {

	public Integer sauvegarderAliment(@Param("ali") Aliment aliment);

	public List<Aliment> rechercherAliments(RechercheWrapper wrapper);

	public Integer getTotalAliments();

	public Aliment getAliment(@Param("nom") String nom);

}
