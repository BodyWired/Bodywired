package org.bodywired.api.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.bodywired.api.model.Declinaison;
import org.bodywired.api.model.classement.Etat;

public interface DeclinaisonDao {

	Etat getEtat(@Param("eta") Etat etat);

	List<Etat> getEtats();
	
	Integer sauvegarderEtat(@Param("eta") Etat etat);
	
	Integer modifierEtat(@Param("eta") Etat etat);
	
	Integer supprimerEtat(@Param("eta_id") Integer id);

	Integer sauvegarderDeclinaison(@Param("dec") Declinaison declinaison);
	
	Integer supprimerDeclinaison(@Param("dec_id") Integer id);

	Integer ajouterEtatALaDeclinaison(@Param("dec") Declinaison declinaison,
			@Param("eta") Etat etat);

	Integer supprimerEtatDeclinaison(@Param("eta_id") Integer eta_id, @Param("dec_id") Integer dec_id);

}
