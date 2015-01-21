package org.bodywired.api.dao;

import org.apache.ibatis.annotations.Param;
import org.bodywired.api.model.Aliment;
import org.bodywired.api.wrapper.RechercheWrapper;
import org.bodywired.api.wrapper.ResultatRechercheWrapper;

public interface AlimentDao {

	public Integer sauvegarderAliment(@Param("ali") Aliment aliment);

	public ResultatRechercheWrapper rechercherAliments(RechercheWrapper wrapper);

	public Aliment getAliment(@Param("nom") String nom);

}
