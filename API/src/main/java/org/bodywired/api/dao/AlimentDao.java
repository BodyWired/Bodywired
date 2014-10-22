package org.bodywired.api.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.bodywired.api.model.Aliment;
import org.bodywired.api.model.Declinaison;
import org.bodywired.api.model.classement.CategorieAliment;
import org.bodywired.api.wrapper.RechercheWrapper;

public interface AlimentDao {

	public Integer sauvegarderAliment ( @Param ( "ali" ) Aliment aliment );

	public Integer sauvegarderDeclinaison ( @Param ( "dec" ) Declinaison declinaison );

	// TODO
	public List <CategorieAliment> getCategoriesAliments ();

	public List <Aliment> getAliments ( RechercheWrapper wrapper );

	public int getTotal ();

}
