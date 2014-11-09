package org.bodywired.api.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.bodywired.api.model.Declinaison;
import org.bodywired.api.model.classement.Etat;

public interface DeclinaisonDao {
	Integer sauvegarderEtat(@Param("eta") Etat etat);

	Etat getEtat(@Param("eta") Etat etat);

	Integer sauvegarderDeclinaison(@Param("dec") Declinaison declinaison);

	Integer ajouterEtatALaDeclinaison(@Param("dec") Declinaison declinaison,
			@Param("eta") Etat etat);

	List<Etat> getEtats();

}
