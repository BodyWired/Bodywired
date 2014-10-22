package org.bodywired.api.dao;

import org.apache.ibatis.annotations.Param;
import org.bodywired.api.model.Aliment;
import org.bodywired.api.model.Declinaison;

public interface AlimentDao {
	
	public Integer sauvegarderAliment(@Param("ali") Aliment aliment);
	
	public Integer sauvegarderDeclinaison(@Param("dec") Declinaison declinaison);

}
